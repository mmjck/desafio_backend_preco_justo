package com.fair_price.shop.adapters.gateway.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fair_price.shop.adapters.gateway.database.entities.DuckEntity;

public interface DuckRepository extends JpaRepository<DuckEntity, Integer>{ }
