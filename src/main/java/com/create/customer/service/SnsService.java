package com.create.customer.service;

import com.create.customer.events.CustomerCreatedEvent;

public interface SnsService {

    void sendCustomerCreatedEvent(CustomerCreatedEvent event);

}