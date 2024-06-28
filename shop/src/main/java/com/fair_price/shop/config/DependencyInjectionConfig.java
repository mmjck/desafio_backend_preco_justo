package com.fair_price.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fair_price.shop.domain.factory.impl.CustomerFactory;
import com.fair_price.shop.domain.factory.impl.DuckFactory;
import com.fair_price.shop.domain.factory.impl.OrderFactory;

@Configuration
public class DependencyInjectionConfig {
    @Bean
    CustomerFactory customerFactory(){
        return new CustomerFactory();
    }

    @Bean
    DuckFactory duckFactory(){
        return new DuckFactory();
    }

    @Bean
    OrderFactory orderFactory(){
        return new OrderFactory();
    }
}
