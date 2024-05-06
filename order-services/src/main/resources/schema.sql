DROP TABLE IF EXISTS purchase_order;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE purchase_order
(
    id UUID  DEFAULT uuid_generate_v4() PRIMARY KEY,
    customer_id int,
    product_id int,
    quantity int,
    unit_price int,
    amount int,
    status VARCHAR(50) NOT NULL CHECK ( status <> '' ),
    delivery_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    version SERIAL NOT NULL
);