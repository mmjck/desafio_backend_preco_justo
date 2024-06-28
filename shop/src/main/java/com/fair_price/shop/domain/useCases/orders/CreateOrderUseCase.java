package com.fair_price.shop.domain.useCases.orders;


import org.springframework.stereotype.Service;

import com.fair_price.shop.adapters.controllers.model.orders.OrdersRequest;
import com.fair_price.shop.adapters.gateway.database.CustomerGateway;
import com.fair_price.shop.adapters.gateway.database.DuckGateway;
import com.fair_price.shop.adapters.gateway.database.OrdersGateway;
import com.fair_price.shop.adapters.gateway.database.entities.CustomerEntity;
import com.fair_price.shop.adapters.gateway.database.entities.OrdersEntity;
import com.fair_price.shop.adapters.gateway.database.entities.StatusDuck;
import com.fair_price.shop.domain.expections.DuckNotFoundException;
import com.fair_price.shop.domain.expections.DuckSoldException;
import com.fair_price.shop.domain.expections.UserNotFoundException;
import com.fair_price.shop.domain.useCases.dto.order.OrderDTO;

import jakarta.transaction.Transactional;

@Service
public class CreateOrderUseCase {
    private final OrdersGateway ordersGateway;
    private final CustomerGateway customerGateway;
    private final DuckGateway duckGateway;

    public CreateOrderUseCase(OrdersGateway ordersGateway, CustomerGateway customerGateway, DuckGateway duckGateway)  {
        this.customerGateway = customerGateway;
        this.ordersGateway = ordersGateway;
        this.duckGateway = duckGateway;

    }

    @Transactional
    public OrderDTO call(OrdersRequest ordersRequest) throws Exception {
        

        var duck = this.duckGateway.findById(ordersRequest.getDuckId()).orElseThrow(() -> new DuckNotFoundException());
        CustomerEntity customer = this.customerGateway.findbyId(ordersRequest.getCustomerId()).orElseThrow(() -> new UserNotFoundException());
        
        if(duck.getStatus() == StatusDuck.SOLD){
            throw  new DuckSoldException();
        }

        var entity = OrdersEntity.builder()
            .customerId(ordersRequest.getCustomerId())
            .duckId(ordersRequest.getDuckId())
            .build();
        

        if(customer.getDiscount() != null){
            entity.setPrice((float)(duck.getPrice() * 0.8));
        }else {
            entity.setPrice(duck.getPrice());
        }

        duck.setStatus(StatusDuck.SOLD);

        var response = ordersGateway.create(entity);
        this.duckGateway.update(duck);
        
        
        return OrderDTO.mapper(response);
    }
}
