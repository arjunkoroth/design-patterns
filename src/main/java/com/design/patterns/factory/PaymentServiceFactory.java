package com.design.patterns.factory;

import com.design.patterns.factory.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class PaymentServiceFactory {

    private final Map<String, PaymentService> paymentServiceMap;

    public PaymentService getPaymentService(String paymentServiceId) {
        return paymentServiceMap.get(paymentServiceId);
    }
}
