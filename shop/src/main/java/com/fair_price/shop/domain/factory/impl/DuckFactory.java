package com.fair_price.shop.domain.factory.impl;

import com.fair_price.shop.adapters.controllers.model.duck.DuckRequest;
import com.fair_price.shop.adapters.gateway.database.entities.DuckEntity;
import com.fair_price.shop.domain.factory.BaseFactory;

public class DuckFactory implements BaseFactory<DuckRequest, DuckEntity> {

    @Override
    public DuckEntity create(DuckRequest request) {
        return DuckEntity
                .builder()
                .price(request.getPrice())
                .parentId(request.getParentId())
                .build();
    }

}
