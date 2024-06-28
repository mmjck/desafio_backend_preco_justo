--liquibase formatted sql
--changeset create-customer:001

CREATE TABLE IF NOT EXISTS customers (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL, 
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- rollback DROP TABLE customers;
