--liquibase formatted sql
--changeset abed:002

CREATE TABLE IF NOT EXISTS ducks (
  id INTEGER PRIMARY KEY AUTO_INCREMENT, 
  status VARCHAR(10) DEFAULT 'AVAILABLE' CHECK (
    status IN ('AVAILABLE', 'SOLD')
  ),
  parent_id INTEGER DEFAULT NULL,
  price FLOAT NOT NULL, 
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- rollback DROP TABLE ducks;
