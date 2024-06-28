--liquibase formatted sql
--changeset abed:007


ALTER TABLE orders
ADD CONSTRAINT fk_orders_duck
FOREIGN KEY (duck_id) REFERENCES ducks (id)
ON DELETE CASCADE;


-- rollback ALTER TABLE discounts DROP CONSTRAINT fk_orders_duck;
