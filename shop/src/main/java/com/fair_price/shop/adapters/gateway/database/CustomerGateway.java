package com.fair_price.shop.adapters.gateway.database;

import java.util.List;
import java.util.Optional;

import com.fair_price.shop.adapters.gateway.database.entities.CustomerEntity;

public interface CustomerGateway {
    public CustomerEntity create(CustomerEntity data);
    public Optional<CustomerEntity> findbyId(int id);
    public List<CustomerEntity> list();
}
