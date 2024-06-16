package com.design.patterns.factory.entity;

import com.design.patterns.util.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers", schema = "design_patterns")
@Builder
public class CustomerEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID customerId;

    @Column(name = "NAME")
    private String customerName;

    @Column(name = "CUSTOMER_TYPE")
    private String customerType;
}
