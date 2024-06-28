package com.fair_price.shop.adapters.gateway.database.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fair_price.shop.adapters.gateway.database.DuckGateway;
import com.fair_price.shop.adapters.gateway.database.entities.DuckEntity;
import com.fair_price.shop.adapters.gateway.database.repository.DuckRepository;

@Service
public class DuckPersistenceImpl implements DuckGateway{
    private final DuckRepository repository;

    public DuckPersistenceImpl(DuckRepository repository) {
        this.repository = repository;
    }

    @Override
    public DuckEntity create(DuckEntity entity) {
        return repository.save(entity);
    }

    @Override
    public DuckEntity update(DuckEntity entity) {
        return this.repository.saveAndFlush(entity);
    }

    @Override
    public Optional<DuckEntity> findById(int id) {
        return this.repository.findById(id);

    }

    @Override
    public List<DuckEntity> findAll() {
        return this.repository.findAll();
    }   
    
}
