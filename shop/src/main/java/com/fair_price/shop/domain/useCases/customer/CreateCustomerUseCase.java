package com.fair_price.shop.domain.useCases.customer;

import org.springframework.stereotype.Service;

import com.fair_price.shop.adapters.controllers.model.customer.CustomerRequest;
import com.fair_price.shop.adapters.gateway.database.CustomerGateway;
import com.fair_price.shop.adapters.gateway.database.entities.DiscountEntity;
import com.fair_price.shop.domain.factory.impl.CustomerFactory;
import com.fair_price.shop.domain.useCases.discount.CreateRegisterDiscountUseCase;
import com.fair_price.shop.domain.useCases.dto.order.CustomerDTO;

import jakarta.transaction.Transactional;

@Service
public class CreateCustomerUseCase {
    private final CustomerGateway gateway;
    private final CustomerFactory factory;

    private final CreateRegisterDiscountUseCase createRegisterDiscountUseCase;

    public CreateCustomerUseCase(CustomerFactory factory, CustomerGateway gateway,
            CreateRegisterDiscountUseCase createRegisterDiscountUseCase) {
        this.factory = factory;
        this.gateway = gateway;
        this.createRegisterDiscountUseCase = createRegisterDiscountUseCase;
    }

    @Transactional
    public CustomerDTO call(CustomerRequest request) {
        var entity = factory.create(request);

        var data = gateway.create(entity);

        if (request.isHasDiscount()) {
            var discount = createRegisterDiscountUseCase.call(DiscountEntity.builder().customerEntity(data).build());
            data.setDiscount(discount);
        }

        return CustomerDTO.mapper(data);
    }
}
