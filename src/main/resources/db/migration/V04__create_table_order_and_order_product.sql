CREATE TABLE `order` (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_costumer BIGINT(20) NOT NULL,
    id_costumer_address BIGINT(20) NOT NULL,
    creation_date DATE NOT NULL,
    end_date DATE,
    order_discount DECIMAL(4,2),
    total_price DECIMAL(10,2) NOT NULL,
    status TINYINT(1) NOT NULL
)   ENGINE=InnoDB
    DEFAULT CHARSET=utf8;

CREATE TABLE line_item (
    id BIGINT(20) AUTO_INCREMENT NOT NULL,
    id_order BIGINT(20) NOT NULL,
    id_product BIGINT(20) NOT NULL,
    selling_price DECIMAL(10,2) NOT NULL,
    quantity INT(10) NOT NULL,
    FOREIGN KEY (id_order) REFERENCES `order`(id),
    FOREIGN KEY (id_product) REFERENCES product(id),
    PRIMARY KEY (id, id_order, id_product)
)   ENGINE=InnoDB
    DEFAULT CHARSET=utf8;

INSERT INTO `order` (id_costumer, id_costumer_address, creation_date, end_date, order_discount, total_price, status) values (
    1,
    1,
    '2017-06-10',
    '2017-07-26',
    10.4,
    4999.90,
    2
);

INSERT INTO line_item (id, id_order, id_product, selling_price, quantity)
values (
    1,
    1,
    2,
    4999.90,
    2
);
