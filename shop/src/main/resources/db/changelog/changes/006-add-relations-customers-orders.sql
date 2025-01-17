--liquibase formatted sql
--changeset add-orders_customer:006


ALTER TABLE orders
ADD CONSTRAINT fk_orders_customer
FOREIGN KEY (customer_id) REFERENCES customers (id)
ON DELETE CASCADE;


-- rollback ALTER TABLE discounts DROP CONSTRAINT fk_orders_customer;
