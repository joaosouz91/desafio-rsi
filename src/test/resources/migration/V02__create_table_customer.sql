CREATE TABLE customer (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(120) NOT NULL,
    cpf_cnpj VARCHAR(14) NOT NULL,
    phone_one VARCHAR(11) NOT NULL,
    phone_two VARCHAR(11),
    status TINYINT(1) NOT NULL
)   ENGINE=InnoDB
    DEFAULT CHARSET=utf8;

INSERT INTO customer (name, cpf_cnpj, phone_one, phone_two, status) values (
    'João Victor de Souza',
    '41664084794',
    '11953106218',
    null,
    1
);

INSERT INTO customer (name, cpf_cnpj, phone_one, phone_two, status) values (
   'Desenvolvedores LTDA',
   '66441397000165',
   '11963154895',
   '11987856412',
   1
);
