package com.fair_price.shop.adapters.gateway.database.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fair_price.shop.adapters.gateway.database.dtos.ListOrderDTO;

import jakarta.persistence.Column;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Table(name = "orders")
@Entity
@Builder
@Setter
@Getter
@Transactional
@SqlResultSetMapping(name = "OrdersEntity.CustomerOrderDTO",

                classes = {
                                @ConstructorResult(targetClass = ListOrderDTO.class, columns = {
                                                @ColumnResult(name = "id", type = Integer.class),
                                                @ColumnResult(name = "duckId", type = Integer.class),
                                                @ColumnResult(name = "customerId", type = Integer.class),
                                                @ColumnResult(name = "duckParentId", type = Integer.class),
                                                @ColumnResult(name = "status", type = String.class),
                                                @ColumnResult(name = "price", type = Float.class),
                                                @ColumnResult(name = "hasDiscount", type = Boolean.class),
                                                @ColumnResult(name = "customerName", type = String.class),
                                }) })

// Raw Query
//
//
// SELECT  o.id as id,  o.price as price,  d.id as duckId,  d.status as status,  d.parent_id as duckParentId, c.id as customerId,  c.name as customerName,  
// CASE  WHEN dis.customer_id IS NOT NULL THEN TRUE  ELSE FALSE  END AS hasDiscount from ducks d 
// RIGHT JOIN orders o ON o.duck_id = d.id 
// LEFT JOIN customers c ON o.customer_id = c.id  
// LEFT JOIN discounts dis ON dis.customer_id=o.customer_id;
//
//
@NamedNativeQuery(query = " SELECT "
                + " o.id as id, "
                + " o.price as price, "
                + " d.id as duckId, "
                + " d.status as status, "
                + " d.parent_id as duckParentId,"
                + " c.id as customerId, "
                + " c.name as customerName, "
                + " CASE "
                + " WHEN dis.customer_id IS NOT NULL THEN TRUE "
                + " ELSE FALSE "
                + " END AS hasDiscount "
                + " from ducks d"
                + " RIGHT JOIN orders o ON o.duck_id = d.id"
                + " LEFT JOIN customers c ON o.customer_id = c.id "
                + " LEFT JOIN discounts dis ON dis.customer_id=o.customer_id;", resultSetMapping = "OrdersEntity.CustomerOrderDTO", name = "OrdersEntity.ListOrdersDetails")

public class OrdersEntity implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(name = "duck_id")
        private int duckId;

        @Column(name = "customer_id")
        private int customerId;

        private float price;


        @Column(name = "created_at")
        @CreationTimestamp
        private LocalDateTime createdAt;

        @ManyToOne
        @JoinColumn(name = "customer_id", insertable = false, updatable = false)
        private CustomerEntity customerEntity;

        @ManyToOne
        @JoinColumn(name = "duck_id", insertable = false, updatable = false)
        private DuckEntity jobEntity;

}
