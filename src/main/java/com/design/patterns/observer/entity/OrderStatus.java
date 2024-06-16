package com.design.patterns.observer.entity;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.Range;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Added a logic to get order status by different amount ranges.
 * This can be used to simulate all the test cases.
 */
@AllArgsConstructor
public enum OrderStatus {
    ORDER_PLACED(Range.of(BigDecimal.valueOf(100), BigDecimal.valueOf(200))),
    ORDER_PENDING(Range.of(BigDecimal.valueOf(201), BigDecimal.valueOf(300))),
    ORDER_FAILED(Range.of(BigDecimal.valueOf(301), BigDecimal.valueOf(500)));

    private final Range<BigDecimal> amountRange;

    public static OrderStatus getOrderStatusByOrderAmount(BigDecimal amount) {
        return Arrays.stream(values())
                .filter(orderStatus -> orderStatus.amountRange.contains(amount))
                .findFirst()
                .orElse(ORDER_PLACED);
    }
}
