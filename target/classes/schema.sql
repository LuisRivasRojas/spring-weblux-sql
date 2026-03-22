CREATE TABLE IF NOT EXISTS product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price NUMERIC(10,2),
    stock INTEGER,
    category VARCHAR(50),
    brand VARCHAR(50),
    status VARCHAR(1) DEFAULT 'A',
);