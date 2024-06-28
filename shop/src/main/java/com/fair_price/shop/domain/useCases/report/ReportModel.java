package com.fair_price.shop.domain.useCases.report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReportModel {
    private Integer duckId;
    private int padding;
    private String name;
    private String customerName;
    private String status;
    private String hasDiscount;
    private float price;
}
