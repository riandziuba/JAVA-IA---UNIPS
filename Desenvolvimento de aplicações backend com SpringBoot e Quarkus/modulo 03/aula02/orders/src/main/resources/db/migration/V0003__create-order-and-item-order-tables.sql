CREATE TABLE `Order` (
    id BIGINT PRIMARY KEY,
    dateTime DATETIME NOT NULL,
    status ENUM('DONE', 'PAID', 'CONFIRMED', 'READY', 'GO_OUT_FOR_DELIVERY', 'DELIVERED') NOT NULL,
    customerName VARCHAR(100) NOT NULL,
    customerDocument VARCHAR(14) NOT NULL,
    customerPhone VARCHAR(16) NOT NULL,
    customerAddress VARCHAR(300) NOT NULL
);

CREATE TABLE Order_SEQ (
    next_val BIGINT
);

CREATE TABLE ItemOrder (
    id BIGINT PRIMARY KEY,
    amount BIGINT NOT NULL,
    unitaryPrice DECIMAL(9, 2) NOT NULL,
    observation VARCHAR(300),
    order_id BIGINT NOT NULL,
    itemMenu_id BIGINT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES `Order`(id),
    FOREIGN KEY (itemMenu_id) REFERENCES ItemMenu(id)
);

CREATE TABLE ItemOrder_SEQ (
    next_val BIGINT
);
