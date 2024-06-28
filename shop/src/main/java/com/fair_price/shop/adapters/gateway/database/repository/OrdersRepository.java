package com.fair_price.shop.adapters.gateway.database.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fair_price.shop.adapters.gateway.database.dtos.ListOrderDTO;
import com.fair_price.shop.adapters.gateway.database.entities.OrdersEntity;

public interface OrdersRepository extends JpaRepository<OrdersEntity, Integer> { 
        @Query(name = "OrdersEntity.ListOrdersDetails", nativeQuery = true)
        List<ListOrderDTO> findAllTransformed();

}
