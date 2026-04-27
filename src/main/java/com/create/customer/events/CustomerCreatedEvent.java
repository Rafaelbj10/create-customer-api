package com.create.customer.events;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CustomerCreatedEvent {

    private UUID customerId;
    private String cpf;
    private BigDecimal monthlyIncome;
    private LocalDateTime timestamp;


}
