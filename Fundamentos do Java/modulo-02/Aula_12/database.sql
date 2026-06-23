CREATE TABLE menu_item (
                          id bigint primary key auto_increment,
                          name varchar(100) NOT NULL,
                          description varchar(1000),
                          category ENUM('APPETIZER', 'MAIN_COURSE', 'DRINKS', 'DESSERT') NOT NULL,
                          price decimal(9,2) NOT NULL,
                          price_with_discount decimal(9,2)
);
