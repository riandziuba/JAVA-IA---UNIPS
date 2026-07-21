CREATE TABLE ItemMenu (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(1000),
    category ENUM('APPETIZER', 'MAIN_ENTRANCE', 'BEVERAGES', 'DESSERTS') NOT NULL,
    price DECIMAL(9, 2) NOT NULL,
    priceWithDiscount DECIMAL(9, 2)
);

CREATE TABLE ItemMenu_SEQ (
    next_val BIGINT
);
