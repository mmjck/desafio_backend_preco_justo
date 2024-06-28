package com.fair_price.shop.domain.useCases.dto.order;

import com.fair_price.shop.adapters.gateway.database.entities.CustomerEntity;

public record CustomerDTO(
    int id,
    String name,
    boolean hasDiscount
) {
    public static CustomerDTO mapper(CustomerEntity entity) {
        return new CustomerDTO(entity.getId(), entity.getName(), entity.getDiscount() != null);
    }
}
