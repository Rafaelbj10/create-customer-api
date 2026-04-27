package com.create.customer.service.impl;

import com.create.customer.events.CustomerCreatedEvent;
import com.create.customer.service.SqsService;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class SqsServiceImpl implements SqsService {

    private final SqsClient sqsClient;
    private final Tracer tracer;

    @Value("${aws.sqs.customer-created-queue-url}")
    private String queueUrl;

    @Override
    public void sendCustomerCreatedEvent(CustomerCreatedEvent event) {
        Span span = tracer.nextSpan().name("sqs.send.customer-created").start();

        try (Tracer.SpanInScope ws = tracer.withSpan(span)) {
            span.tag("customerId", event.getCustomerId().toString());
            span.tag("messaging.system", "sqs");
            span.tag("messaging.destination", queueUrl);

            String messageBody = """
                    {
                        "customerId": "%s",
                        "cpf": "%s",
                        "monthlyIncome": %s,
                        "timestamp": "%s"
                    }
                    """.formatted(event.getCustomerId(), event.getCpf(), event.getMonthlyIncome(), event.getTimestamp());

            SendMessageRequest request = SendMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .messageBody(messageBody)
                    .build();

            sqsClient.sendMessage(request);

            log.info("CustomerCreatedEvent sent to SQS customerId={}", event.getCustomerId());

        } catch (Exception e) {
            span.tag("error", e.getMessage());
            log.error("Failed to send CustomerCreatedEvent to SQS customerId={} error={}",
                    event.getCustomerId(), e.getMessage());
        } finally {
            span.end();
        }
    }
}