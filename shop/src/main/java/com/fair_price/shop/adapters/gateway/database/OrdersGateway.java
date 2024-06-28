package com.fair_price.shop.adapters.gateway.database;

import java.util.List;

import com.fair_price.shop.adapters.gateway.database.dtos.ListOrderDTO;
import com.fair_price.shop.adapters.gateway.database.entities.OrdersEntity;

public interface OrdersGateway {
    public OrdersEntity create(OrdersEntity entity);
    public List<ListOrderDTO> findAll();

}
