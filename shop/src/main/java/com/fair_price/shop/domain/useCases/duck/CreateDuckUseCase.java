package com.fair_price.shop.domain.useCases.duck;

import org.springframework.stereotype.Service;

import com.fair_price.shop.adapters.controllers.model.duck.DuckRequest;
import com.fair_price.shop.adapters.gateway.database.DuckGateway;
import com.fair_price.shop.adapters.gateway.database.entities.DuckEntity;
import com.fair_price.shop.domain.factory.impl.DuckFactory;
import com.fair_price.shop.domain.useCases.dto.duck.DuckDTO;

import jakarta.transaction.Transactional;

@Service
public class CreateDuckUseCase {
    private final DuckGateway gateway;
    private final DuckFactory factory;
    
    
    public CreateDuckUseCase(DuckGateway gateway, DuckFactory factory) {
        this.gateway = gateway;
        this.factory = factory;
    }

    /**
     * Methods for manipulating strings.
     *
     * @param DuckRequest duckRequest
     * @return Duckentity
     * @since 1.0
     */
    @Transactional
    public DuckDTO call(DuckRequest duckRequest){
        DuckEntity entity = factory.create(duckRequest);
        DuckEntity response = gateway.create(entity);

        return DuckDTO.mapper(response);
    }


}
