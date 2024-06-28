package com.fair_price.shop.domain.useCases.discount;

import org.springframework.stereotype.Service;

import com.fair_price.shop.adapters.gateway.database.DiscountsGateway;
import com.fair_price.shop.adapters.gateway.database.entities.DiscountEntity;

@Service
public class CreateDiscountUseCase {
    private final DiscountsGateway gateway;

    public CreateDiscountUseCase(DiscountsGateway gateway) {
        this.gateway = gateway;
    }

    public DiscountEntity call(DiscountEntity entity){
        return gateway.create(entity);
    }
}
