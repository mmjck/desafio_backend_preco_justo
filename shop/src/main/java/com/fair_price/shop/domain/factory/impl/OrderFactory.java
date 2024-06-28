package com.fair_price.shop.domain.factory.impl;

import com.fair_price.shop.adapters.controllers.model.orders.OrdersRequest;
import com.fair_price.shop.adapters.gateway.database.entities.OrdersEntity;
import com.fair_price.shop.domain.factory.BaseFactory;

public class OrderFactory implements BaseFactory<OrdersRequest, OrdersEntity>{

    @Override
    public OrdersEntity create(OrdersRequest request) {
        return OrdersEntity.builder()
            .customerId(request.getCustomerId())
            .duckId(request.getDuckId())
            .build();
    }
    
}
