package com.design.patterns.factory.service;

import com.design.patterns.factory.enums.PaymentStatus;

import java.math.BigDecimal;

public interface PaymentService {
    public PaymentStatus doPayment(BigDecimal amount);
    public default BigDecimal applyDiscount() {
        return BigDecimal.ZERO;
    }
}
