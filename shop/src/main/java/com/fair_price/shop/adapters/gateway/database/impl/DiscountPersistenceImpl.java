package com.fair_price.shop.adapters.gateway.database.impl;
import org.springframework.stereotype.Service;
import com.fair_price.shop.adapters.gateway.database.DiscountsGateway;
import com.fair_price.shop.adapters.gateway.database.entities.DiscountEntity;
import com.fair_price.shop.adapters.gateway.database.repository.DiscountsRepository;

@Service
public class DiscountPersistenceImpl implements DiscountsGateway {
    private DiscountsRepository repository;

    public DiscountPersistenceImpl(DiscountsRepository repository) {
        this.repository = repository;
    }

    @Override
    public DiscountEntity create(DiscountEntity entity) {
        return this.repository.save(entity);
    }

}
