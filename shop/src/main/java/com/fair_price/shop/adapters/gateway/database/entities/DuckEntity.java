package com.fair_price.shop.adapters.gateway.database.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ducks")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DuckEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private float price;

    @Column(name = "parent_id", nullable = true)
    private Integer parentId;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

  

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusDuck status;

    @PrePersist
    public void prePersist() {
        status = StatusDuck.AVAILABLE; 
    }

}
