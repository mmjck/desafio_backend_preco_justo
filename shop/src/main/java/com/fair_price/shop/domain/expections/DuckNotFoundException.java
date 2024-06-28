package com.fair_price.shop.domain.expections;

public class DuckNotFoundException extends RuntimeException {
    public DuckNotFoundException() {
        super("Duck not found");
    }
}
