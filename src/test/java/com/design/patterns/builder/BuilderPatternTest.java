package com.design.patterns.builder;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class BuilderPatternTest {

    @Test
    void should_build_customer_object() {
        // Arrange
        Customer customer = new Customer.CustomerBuilder("Alice", "ABC Group")
                .setHasCar(true)
                .setHasBike(false)
                .build();
        log.info("Customer object {}", customer);

        // Assert
        assertEquals("Alice", customer.getName());
        assertEquals("ABC Group", customer.getCompany());
        assertTrue(customer.isHasCar());
        assertFalse(customer.isHasBike());
    }
}
