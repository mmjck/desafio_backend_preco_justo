package com.fair_price.shop.adapters.gateway.database.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fair_price.shop.adapters.gateway.database.CustomerGateway;
import com.fair_price.shop.adapters.gateway.database.entities.CustomerEntity;
import com.fair_price.shop.adapters.gateway.database.repository.CustomerRepository;

@Service
public class CustomerPersistenceImpl implements CustomerGateway{
    private final CustomerRepository repository;

    
    public CustomerPersistenceImpl(CustomerRepository repository) {
        this.repository = repository;
    }


    @Override
    public CustomerEntity create(CustomerEntity data) {
        return repository.save(data);
    }


    @Override
    public List<CustomerEntity> list() {
        return repository.findAll();
    }


    @Override
    public Optional<CustomerEntity> findbyId(int id) {
        return this.repository.findById(id);
    }
    
}
