--liquibase formatted sql
--changeset create-discount:003

CREATE TABLE IF NOT EXISTS discounts (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  customer_id INTEGER NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- rollback DROP TABLE discounts;
