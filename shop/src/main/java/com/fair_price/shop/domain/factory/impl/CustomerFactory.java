package com.fair_price.shop.domain.factory.impl;


import com.fair_price.shop.adapters.controllers.model.customer.CustomerRequest;
import com.fair_price.shop.adapters.gateway.database.entities.CustomerEntity;

import com.fair_price.shop.domain.factory.BaseFactory;

public class CustomerFactory implements BaseFactory<CustomerRequest, CustomerEntity>{

    @Override
    public CustomerEntity create(CustomerRequest request) {
        return  CustomerEntity.builder().name(request.getName()).build();
    }
    
}
