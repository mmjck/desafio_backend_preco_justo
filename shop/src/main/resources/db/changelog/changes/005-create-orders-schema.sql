--liquibase formatted sql
--changeset abed:005


CREATE TABLE IF NOT EXISTS orders (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  customer_id INTEGER NOT NULL,
  duck_id INTEGER NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- rollback DROP TABLE orders;
