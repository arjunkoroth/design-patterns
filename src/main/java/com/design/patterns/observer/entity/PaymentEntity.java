package com.design.patterns.observer.entity;

import com.design.patterns.util.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payments", schema = "design_patterns")
public class PaymentEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID paymentId;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "PAYMENT_STATUS")
    @Enumerated(value = EnumType.STRING)
    private PaymentStatus paymentStatus;

    @OneToOne(mappedBy = "paymentEntity", cascade = CascadeType.ALL)
    private OrderEntity orderEntity;
}
