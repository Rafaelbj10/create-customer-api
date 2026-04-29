package com.create.customer.service.impl;

import com.create.customer.events.CustomerCreatedEvent;
import com.create.customer.service.SnsService;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class SnsServiceImpl implements SnsService {

    private final SnsClient snsClient;
    private final Tracer tracer;

    @Value("${aws.sns.customer-created-topic-arn}")
    private String topicArn;

    @Override
    public void sendCustomerCreatedEvent(CustomerCreatedEvent event) {

        Span span = tracer.nextSpan().name("sns.publish.customer-created").start();

        try (Tracer.SpanInScope ws = tracer.withSpan(span)) {

            span.tag("customerId", event.getCustomerId().toString());
            span.tag("messaging.system", "sns");
            span.tag("messaging.destination", topicArn);

            String messageBody = """
                    {
                        "customerId": "%s",
                        "cpf": "%s",
                        "monthlyIncome": %s,
                        "timestamp": "%s"
                    }
                    """.formatted(
                    event.getCustomerId(),
                    event.getCpf(),
                    event.getMonthlyIncome(),
                    event.getTimestamp()
            );

            PublishRequest request = PublishRequest.builder()
                    .topicArn(topicArn)
                    .message(messageBody)
                    .build();

            snsClient.publish(request);

            log.info("CustomerCreatedEvent published to SNS topic customerId={}",
                    event.getCustomerId());

        } catch (Exception e) {
            span.tag("error", e.getMessage());
            log.error("Failed to publish CustomerCreatedEvent customerId={} error={}",
                    event.getCustomerId(), e.getMessage());
        } finally {
            span.end();
        }
    }
}