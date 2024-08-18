package com.design.patterns.observer.mapper;

import com.design.patterns.observer.domain.OrderDetails;
import com.design.patterns.observer.entity.OrderEntity;
import com.design.patterns.observer.entity.OrderStatus;
import com.design.patterns.observer.entity.PaymentEntity;
import com.design.patterns.observer.entity.PaymentStatus;
import org.springframework.stereotype.Component;

@Component
public class OrderEntityMapper {

    public OrderEntity mapToOrderEntity(OrderDetails orderDetails, PaymentEntity paymentEntity) {
        OrderStatus orderStatus = OrderStatus.getOrderStatusByOrderAmount(orderDetails.getAmount());
        return OrderEntity.builder()
                .itemName(orderDetails.getItemName())
                .orderStatus(orderStatus)
                .paymentEntity(paymentEntity)
                .build();
    }
}
