package com.fair_price.shop.adapters.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fair_price.shop.adapters.controllers.model.duck.DuckRequest;
import com.fair_price.shop.domain.useCases.duck.CreateDuckUseCase;


@RestController
public class DuckController {
     private final CreateDuckUseCase useCase;

    public DuckController(CreateDuckUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping(value = "/duck")
    public ResponseEntity<Object> create(@RequestBody @Validated DuckRequest entity) {
        try {
            var response = useCase.call(entity);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
