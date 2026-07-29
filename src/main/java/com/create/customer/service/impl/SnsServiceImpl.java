package com.create.customer.service.impl;

import com.create.customer.events.CustomerCreatedEvent;
import com.create.customer.service.SnsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.MessageAttributeValue;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.SnsException;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class SnsServiceImpl implements SnsService {

    private final SnsClient snsClient;
    private final Tracer tracer;
    private final ObjectMapper objectMapper;

    @Value("${aws.sns.customer-created-topic-arn}")
    private String topicArn;

    @Override
    public void sendCustomerCreatedEvent(CustomerCreatedEvent event) {

        Span span = tracer.nextSpan()
                .name("sns.publish.customer-created")
                .start();
        log.info("Sending to topicArn: {}", topicArn);
        span = tracer.nextSpan().name("sns.publish.customer-created").start();

        try (Tracer.SpanInScope ws = tracer.withSpan(span)) {

            span.tag("customerId", event.getCustomerId().toString());
            span.tag("messaging.system", "sns");
            span.tag("messaging.destination", topicArn);

            String messageBody = objectMapper.writeValueAsString(event);

            PublishRequest request = PublishRequest.builder()
                    .topicArn(topicArn)
                    .message(messageBody)
                    .messageAttributes(Map.of(
                            "eventType", MessageAttributeValue.builder()
                                    .dataType("String")
                                    .stringValue("CustomerCreated")
                                    .build()
                    ))
                    .build();

            snsClient.publish(request);

            log.info(
                    "Published CustomerCreatedEvent customerId={} topicArn={}",
                    event.getCustomerId(),
                    topicArn
            );

        } catch (SnsException | JsonProcessingException e) {

            span.error(e);

            log.error(
                    "Failed to publish CustomerCreatedEvent customerId={}",
                    event.getCustomerId(),
                    e
            );

            throw new RuntimeException("Error publishing SNS event", e);

        } finally {
            span.end();
        }
    }
}