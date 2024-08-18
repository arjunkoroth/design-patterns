package com.design.patterns.observer.entity;

import com.design.patterns.util.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders", schema = "design_patterns")
@ToString
public class OrderEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID orderId;

    @Column(name = "ITEM_NAME")
    private String itemName;

    @Column(name = "ORDER_STATUS")
    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private PaymentEntity paymentEntity;
}
