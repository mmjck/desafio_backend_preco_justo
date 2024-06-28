package com.fair_price.shop.adapters.gateway.database.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListOrderDTO {
        private int id;
        private String duckName;
        private String customerName;
        private Float price;
        
        private String status;
        private Boolean hasDiscount;

        private int customerId;
        private int duckId;
        private Integer duckParentId;
}