package com.fair_price.shop.adapters.controllers.model.orders;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
public class OrdersRequest {
    @JsonProperty(value = "customer_id")
    private Integer customerId;
    
    @JsonProperty(value = "duck_id")
    private Integer duckId;

    @JsonProperty(value = "price")
    private Float price;


}
