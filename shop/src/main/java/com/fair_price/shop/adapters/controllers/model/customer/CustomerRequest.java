package com.fair_price.shop.adapters.controllers.model.customer;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CustomerRequest {
    @NonNull
    private String name;
    
    @JsonProperty("has_discount")
    @Nullable private boolean hasDiscount;
}
