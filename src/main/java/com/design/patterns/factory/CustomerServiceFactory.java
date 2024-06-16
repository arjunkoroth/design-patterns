package com.design.patterns.factory;

import com.design.patterns.factory.enums.CustomerType;
import com.design.patterns.factory.service.CustomerService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CustomerServiceFactory {

    @Autowired
    List<CustomerService> customerServices;

    private static final Map<CustomerType, CustomerService> customerServiceMap = new HashMap<>();

    @PostConstruct
    public void initCustomerService() {
        customerServices.forEach(customerService -> customerServiceMap.putIfAbsent(customerService.getCustomerType(), customerService));
    }

    public CustomerService getCustomerService(CustomerType customerType) {
        return customerServiceMap.get(customerType);
    }
}
