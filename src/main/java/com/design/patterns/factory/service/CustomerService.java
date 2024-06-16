package com.design.patterns.factory.service;

import com.design.patterns.factory.dto.CustomerDTO;
import com.design.patterns.factory.enums.CustomerType;

import java.util.UUID;

public interface CustomerService {

    public UUID saveCustomer(CustomerDTO customerDto);
    public CustomerType getCustomerType();
}
