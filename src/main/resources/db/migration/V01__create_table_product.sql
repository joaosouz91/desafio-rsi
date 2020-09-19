CREATE TABLE products (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(1000),
    price DECIMAL(10,2) NOT NULL,
    status TINYINT(1) NOT NULL
)   ENGINE=InnoDB
    DEFAULT CHARSET=utf8;

INSERT INTO products (name, description, price, status) values (
    'Playstation 5 digital',
    'Suporte a saída de 120 Hz em telas 4K. Com uma TV HDR...',
    4499.90,
    1
);

INSERT INTO products (name, description, price, status) values (
    'Playstation 5 mídia física',
    'Suporte a saída de 120 Hz em telas 4K. Com uma TV HDR...',
    4999.90,
    1
)
