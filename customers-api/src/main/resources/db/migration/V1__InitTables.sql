CREATE TABLE customers (
    id UUID PRIMARY KEY,
    name VARCHAR NOT NULL,
    email VARCHAR NOT NULL
);

CREATE TABLE addresses (
    id UUID PRIMARY KEY,
    customer_id UUID REFERENCES customers(id),
    line_one VARCHAR,
    line_two VARCHAR,
    city VARCHAR,
    state VARCHAR,
    zip_code VARCHAR
);