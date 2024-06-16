package com.design.patterns.observer.event;

import com.design.patterns.observer.entity.OrderEntity;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OrderPlacedEvent extends ApplicationEvent {

    private final OrderEntity orderEntity;

    public OrderPlacedEvent(Object source, OrderEntity orderEntity) {
        super(source);
        this.orderEntity = orderEntity;
    }
}
