package com.fair_price.shop.adapters.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.fair_price.shop.adapters.controllers.model.orders.OrdersRequest;
import com.fair_price.shop.domain.useCases.orders.CreateOrderUseCase;
import com.fair_price.shop.domain.useCases.orders.ListOrderUseCase;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class OrdersController {
    private final CreateOrderUseCase createOrderUseCase;
    private final ListOrderUseCase listOrderUseCase;
    
    
    public OrdersController(CreateOrderUseCase createOrderUseCase, ListOrderUseCase listOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
        this.listOrderUseCase = listOrderUseCase;
    }
    
    
    @GetMapping(value = "/orders")
    public ResponseEntity<Object> findAll() {
        try {
            var response = listOrderUseCase.call();
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PostMapping("/orders")
    public ResponseEntity<Object> create(@RequestBody @Validated OrdersRequest entity) {
        try {
            var response = createOrderUseCase.call(entity);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


  
    
}
