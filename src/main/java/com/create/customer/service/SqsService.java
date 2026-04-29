package com.create.customer.service;

import com.create.customer.events.CustomerCreatedEvent;

public interface SqsService {
    void sendCustomerCreatedEvent(CustomerCreatedEvent event);
}
