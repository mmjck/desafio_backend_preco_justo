package com.fair_price.shop.domain.useCases.customer;

import org.springframework.stereotype.Service;

import com.fair_price.shop.adapters.controllers.model.customer.CustomerRequest;
import com.fair_price.shop.adapters.gateway.database.CustomerGateway;
import com.fair_price.shop.adapters.gateway.database.entities.DiscountEntity;
import com.fair_price.shop.domain.factory.impl.CustomerFactory;
import com.fair_price.shop.domain.useCases.discount.CreateDiscountUseCase;
import com.fair_price.shop.domain.useCases.dto.order.CustomerDTO;

import jakarta.transaction.Transactional;

@Service
public class CreateCustomerUseCase {
    private final CustomerGateway gateway;
    private final CustomerFactory factory;

    private final CreateDiscountUseCase createDiscountUseCase;

    public CreateCustomerUseCase(CustomerFactory factory, CustomerGateway gateway,
            CreateDiscountUseCase createDiscountUseCase) {
        this.factory = factory;
        this.gateway = gateway;
        this.createDiscountUseCase = createDiscountUseCase;
    }

    @Transactional
    public CustomerDTO call(CustomerRequest request) {
        var entity = factory.create(request);

        var data = gateway.create(entity);

        if (request.isHasDiscount()) {
            var discount = createDiscountUseCase.call(DiscountEntity.builder().customerEntity(data).build());
            data.setDiscount(discount);
        }

        return CustomerDTO.mapper(data);
    }
}
