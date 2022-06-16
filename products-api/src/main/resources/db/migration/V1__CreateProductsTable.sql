CREATE TABLE products (
    id UUID PRIMARY KEY,
    description VARCHAR NOT NULL,
    price DECIMAL NOT NULL,
    created_at TIMESTAMP NOT NULL
)