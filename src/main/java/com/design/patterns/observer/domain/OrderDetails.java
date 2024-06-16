package com.design.patterns.observer.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OrderDetails {
    private String itemName;
    private BigDecimal amount;
}
