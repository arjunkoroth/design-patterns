package com.design.patterns.observer.entity;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.Range;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Added a logic to get payment status by different amount ranges.
 * This can be used to simulate all the test cases.
 */
@AllArgsConstructor
public enum PaymentStatus {
    SUCCESS(Range.of(BigDecimal.valueOf(100), BigDecimal.valueOf(200))),
    FAILED(Range.of(BigDecimal.valueOf(300), BigDecimal.valueOf(500)));

    private final Range<BigDecimal> amountRange;

    public static PaymentStatus getPaymentStatusByOrderAmount(BigDecimal amount) {
        return Arrays.stream(values())
                .filter(paymentStatus -> paymentStatus.amountRange.contains(amount))
                .findFirst()
                .orElse(SUCCESS);
    }
}
