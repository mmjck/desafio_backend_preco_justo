package com.fair_price.shop.domain.useCases.dto.duck;

import java.time.LocalDateTime;

import com.fair_price.shop.adapters.gateway.database.entities.DuckEntity;

public record DuckDTO (
    int id,
    Integer parentId,
    float price,
    LocalDateTime createdAt)
{
     public static DuckDTO mapper(DuckEntity entity) {
        return  new DuckDTO(entity.getId(), entity.getParentId(), entity.getPrice(), entity.getCreatedAt());
    }
}
