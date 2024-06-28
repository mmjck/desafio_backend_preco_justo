package com.fair_price.shop.adapters.gateway.database.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fair_price.shop.adapters.gateway.database.OrdersGateway;
import com.fair_price.shop.adapters.gateway.database.dtos.ListOrderDTO;
import com.fair_price.shop.adapters.gateway.database.entities.OrdersEntity;
import com.fair_price.shop.adapters.gateway.database.repository.OrdersRepository;

@Service
public class OrdersPersistenceImpl implements OrdersGateway {
    private OrdersRepository repository;

    public OrdersPersistenceImpl(OrdersRepository repository) {
        this.repository = repository;
    }

    @Override
    public OrdersEntity create(OrdersEntity entity) {
        return this.repository.save(entity);
    }

    @Override
    public List<ListOrderDTO> findAll() {
        return this.repository.findAllTransformed();
    }

}
