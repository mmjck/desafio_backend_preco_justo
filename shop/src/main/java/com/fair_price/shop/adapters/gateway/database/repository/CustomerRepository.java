package com.fair_price.shop.adapters.gateway.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.fair_price.shop.adapters.gateway.database.entities.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer>{ } 
