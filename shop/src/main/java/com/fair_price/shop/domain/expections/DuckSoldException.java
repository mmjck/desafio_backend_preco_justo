package com.fair_price.shop.domain.expections;

public class DuckSoldException extends RuntimeException {
    public DuckSoldException() {
        super("Duck not available for sale");
    }
}
