package com.design.patterns.factory.impl;

import com.design.patterns.factory.service.PaymentService;
import com.design.patterns.factory.enums.PaymentStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.design.patterns.factory.enums.PaymentStatus.SUCCESS;

@Service(CredPay.BEAN_ID)
@Slf4j
public class CredPay implements PaymentService {

    public static final String BEAN_ID = "credPay";

    @Override
    public PaymentStatus doPayment(BigDecimal amount) {
        log.info("CRED pay is initiating the payment for amount {}", amount);
        return SUCCESS;
    }
}
