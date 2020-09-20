CREATE TABLE costumer (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(120) NOT NULL,
    phone_one VARCHAR(11) NOT NULL,
    phone_two VARCHAR(11),
    status TINYINT(1) NOT NULL
)   ENGINE=InnoDB
    DEFAULT CHARSET=utf8;

INSERT INTO costumer (name, phone_one, phone_two, status) values (
    'João Victor de Souza',
    '11953106218',
    null,
    1
);

INSERT INTO costumer (name, phone_one, phone_two, status) values (
   'Camila Benites',
   '11963154895',
   '11987856412',
   1
);
