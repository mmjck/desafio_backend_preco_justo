package com.fair_price.shop.adapters.controllers.model.customer;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CustomerRequest {
    private String name;
    
    @JsonProperty("has_discount")
    @Nullable private boolean hasDiscount;
}
