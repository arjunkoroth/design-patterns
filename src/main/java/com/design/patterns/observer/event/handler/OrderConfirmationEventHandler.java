package com.design.patterns.observer.event.handler;

import com.design.patterns.observer.entity.OrderEntity;
import com.design.patterns.observer.entity.repository.OrderEventsRepository;
import com.design.patterns.observer.event.OrderPlacedEvent;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class OrderConfirmationEventHandler {

    private final OrderEventsRepository orderEventsRepository;

    @EventListener
    @Transactional
    public void handleOrderPlacedEvent(OrderPlacedEvent orderPlacedEvent) {
        OrderEntity orderEntity = orderPlacedEvent.getOrderEntity();
        log.info("Saving order details {}", orderEntity);
        orderEventsRepository.save(orderEntity);
        log.info("Saved order details with order Id: {}", orderEntity.getOrderId());
    }
}
