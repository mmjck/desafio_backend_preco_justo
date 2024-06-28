package com.fair_price.shop.domain.factory;

public interface BaseFactory <R, T>{
    T create(R data);
}
