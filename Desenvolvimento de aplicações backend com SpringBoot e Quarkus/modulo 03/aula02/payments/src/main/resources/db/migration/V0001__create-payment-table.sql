CREATE TABLE Payment (
    id BIGINT PRIMARY KEY,
    price DECIMAL(9, 2) NOT NULL,
    status ENUM('CREATED', 'CONFIRMED', 'CANCELED') NOT NULL,
    orderId BIGINT NOT NULL
);

CREATE TABLE Payment_SEQ (
    next_val BIGINT
);
