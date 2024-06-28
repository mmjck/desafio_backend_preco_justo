package com.fair_price.shop.adapters.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.fair_price.shop.adapters.controllers.model.customer.CustomerRequest;
import com.fair_price.shop.domain.useCases.customer.CreateCustomerUseCase;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class CustomerController {
    
    private final CreateCustomerUseCase useCase;

    public CustomerController(CreateCustomerUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/customer")
    public ResponseEntity<Object> create(@RequestBody CustomerRequest entity) {
        try {
            var response = useCase.call(entity);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
}
