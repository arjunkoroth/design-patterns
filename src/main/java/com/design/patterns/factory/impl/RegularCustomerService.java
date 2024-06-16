package com.design.patterns.factory.impl;

import com.design.patterns.factory.service.CustomerService;
import com.design.patterns.factory.dto.CustomerDTO;
import com.design.patterns.factory.entity.CustomerEntity;
import com.design.patterns.factory.entity.repository.CustomerRepository;
import com.design.patterns.factory.enums.CustomerType;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.design.patterns.factory.enums.CustomerType.REGULAR;

@Service
@Slf4j
@AllArgsConstructor
public class RegularCustomerService implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public UUID saveCustomer(CustomerDTO customerDto) {
        log.info("Saving customer {} of type {}", customerDto.getName(), getCustomerType());
        CustomerEntity customerEntity = CustomerEntity.builder()
                .customerName(customerDto.getName())
                .customerType(getCustomerType().name())
                .build();
        customerRepository.save(customerEntity);
        return customerEntity.getCustomerId();
    }

    @Override
    public CustomerType getCustomerType() {
        return REGULAR;
    }
}
