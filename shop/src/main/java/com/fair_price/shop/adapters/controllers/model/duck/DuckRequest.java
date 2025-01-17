package com.fair_price.shop.adapters.controllers.model.duck;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Builder
@Getter
@Setter
public class DuckRequest {
    
    @NonNull
    private Float price;

    
    
    @JsonProperty(value = "parent_id")
    private Integer parentId;
}
