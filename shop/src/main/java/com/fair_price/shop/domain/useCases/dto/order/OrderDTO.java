package com.fair_price.shop.domain.useCases.dto.order;

import java.time.LocalDateTime;

import com.fair_price.shop.adapters.gateway.database.entities.OrdersEntity;

public record OrderDTO(
    int id,
    int customerId,
    int duckId,
    float price,
    LocalDateTime createdAt
) {
    public static OrderDTO mapper(OrdersEntity entity) {
        return  new OrderDTO(entity.getId(), entity.getCustomerId(), entity.getDuckId(), entity.getPrice(), entity.getCreatedAt());
    }
} 
