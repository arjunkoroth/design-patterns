package com.design.patterns.observer.service;

import com.design.patterns.observer.domain.OrderDetails;
import com.design.patterns.observer.entity.OrderEntity;
import com.design.patterns.observer.entity.OrderStatus;
import com.design.patterns.observer.entity.PaymentEntity;
import com.design.patterns.observer.entity.PaymentStatus;
import com.design.patterns.observer.event.OrderPlacedEvent;
import com.design.patterns.observer.mapper.OrderEntityMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.design.patterns.observer.entity.OrderStatus.ORDER_PLACED;
import static com.design.patterns.observer.entity.PaymentStatus.SUCCESS;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService {

    private final ApplicationEventPublisher orderEventPublisher;
    private final OrderEntityMapper orderEntityMapper;

    public void placeOrder(OrderDetails orderDetails) {
        log.info("Placing Order with item: {}", orderDetails.getItemName());
        PaymentEntity paymentEntity = buildPaymentEntity(orderDetails.getAmount());
        OrderEntity orderEntity = orderEntityMapper.mapToOrderEntity(orderDetails, paymentEntity);
        orderEventPublisher.publishEvent(new OrderPlacedEvent(this, orderEntity));
    }

    private PaymentEntity buildPaymentEntity(BigDecimal amount) {
        PaymentStatus paymentStatus = PaymentStatus.getPaymentStatusByOrderAmount(amount);
        return PaymentEntity.builder()
                .amount(amount)
                .paymentStatus(paymentStatus)
                .build();
    }
}
