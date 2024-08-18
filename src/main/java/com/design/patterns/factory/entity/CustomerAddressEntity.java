package com.design.patterns.factory.entity;

import com.design.patterns.util.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customer_address", schema = "design_patterns")
public class CustomerAddressEntity extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID addressId;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "ADDRESS_TYPE")
    private String addressType;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
}
