package com.fair_price.duck.modules.customers;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fair_price.shop.adapters.gateway.database.DuckGateway;
import com.fair_price.shop.domain.useCases.customer.CreateCustomerUseCase;

@ExtendWith(MockitoExtension.class)
public class CreateCustomerUseCaseTest {
    @InjectMocks
    private CreateCustomerUseCase useCase;

    @InjectMocks
    private DuckGateway gateway;

    @Test
    @DisplayName("Should not be able to create a customer")
    public void applyJobWithCandidateNotFound() {
        try {
            useCase.call(null);
        } catch (Exception e) {
            System.out.println(e);
            assertInstanceOf(Exception.class, e);
        }
    }
}
