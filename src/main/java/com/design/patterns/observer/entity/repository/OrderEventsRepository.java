package com.design.patterns.observer.entity.repository;

import com.design.patterns.observer.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderEventsRepository extends JpaRepository<OrderEntity, UUID> {
}
