
--liquibase formatted sql
--changeset add-relation-discount-customer:004

ALTER TABLE discounts
ADD CONSTRAINT fk_discounts_customer
FOREIGN KEY (customer_id) REFERENCES customers (id)
ON DELETE CASCADE;


-- rollback ALTER TABLE discounts DROP CONSTRAINT fk_discounts_customer;
