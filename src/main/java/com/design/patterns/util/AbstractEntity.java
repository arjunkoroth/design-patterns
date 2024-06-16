package com.design.patterns.util;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@MappedSuperclass
public abstract class AbstractEntity {

    @Column(name = "CREATED_AT")
    @CreationTimestamp
    protected Instant createdAt;

    @Column(name = "UPDATED_AT")
    @CreationTimestamp
    protected Instant updatedAt;
}
