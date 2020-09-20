CREATE TABLE costumer_address (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_costumer BIGINT(20) NOT NULL,
    street VARCHAR(120) NOT NULL,
    number VARCHAR(12) NOT NULL,
    cep VARCHAR(9) NOT NULL,
    district VARCHAR(40) NOT NULL,
    city VARCHAR(20) NOT NULL,
    state VARCHAR(3) NOT NULL,
    country VARCHAR(40) NOT NULL,
    address_type TINYINT(1) NOT NULL
)   ENGINE=InnoDB
    DEFAULT CHARSET=utf8;

INSERT INTO costumer_address (id_costumer, street, number, cep, district, city, state, country, address_type) values (
    1,
    'Rua Galeão de Melo',
    '236',
    '05971-054',
    'Vila Mariana',
    'São Paulo',
    'SP',
    'Brasil',
    0
);
