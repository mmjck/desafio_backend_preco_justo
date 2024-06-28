package com.fair_price.shop.domain.useCases.orders;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fair_price.shop.adapters.gateway.database.OrdersGateway;
import com.fair_price.shop.adapters.gateway.database.dtos.ListOrderDTO;
import com.fair_price.shop.domain.factory.impl.OrderFactory;

@Service
public class ListOrderUseCase {
    private final OrdersGateway gateway;

    public ListOrderUseCase(OrdersGateway gateway, OrderFactory factory) {
        this.gateway = gateway;
    }

    public List<ListOrderDTO> call() {
        return this.gateway.findAll();
    }

}
