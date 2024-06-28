package com.fair_price.shop.adapters.gateway.database;

import java.util.List;
import java.util.Optional;

import com.fair_price.shop.adapters.gateway.database.entities.DuckEntity;

public interface DuckGateway {
    public DuckEntity create(DuckEntity entity);
    public DuckEntity update(DuckEntity entity);
    public Optional<DuckEntity> findById(int entity);

    public List<DuckEntity> findAll();
}
