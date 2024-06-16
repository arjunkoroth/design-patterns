package com.design.patterns.factory.impl;

import com.design.patterns.factory.service.PaymentService;
import com.design.patterns.factory.enums.PaymentStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.design.patterns.factory.enums.PaymentStatus.SUCCESS;

@Service(GooglePay.BEAN_ID)
@Slf4j
public class GooglePay implements PaymentService {

    public static final String BEAN_ID = "googlePay";

    @Override
    public PaymentStatus doPayment(BigDecimal amount) {
        log.info("Google pay is initiating the payment for amount {}", amount);
        log.info("Applied Discount of amount {}", applyDiscount());
        return SUCCESS;
    }

    @Override
    public BigDecimal applyDiscount() {
        return BigDecimal.TEN;
    }
}
