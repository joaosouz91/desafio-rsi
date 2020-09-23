CREATE TABLE user_account (
	id BIGINT(20) PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	password VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE permission (
	id BIGINT(20) PRIMARY KEY,
	description VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user_account_permission (
	id_user_account BIGINT(20) NOT NULL,
	id_permission BIGINT(20) NOT NULL,
	PRIMARY KEY (id_user_account, id_permission),
	FOREIGN KEY (id_user_account) REFERENCES user_account(id),
	FOREIGN KEY (id_permission) REFERENCES permission(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO user_account (id, name, email, password) values (1, 'Administrador', 'admin@rsi.com.br', '$2a$10$wXMQuCymO7uSjx3y4h6Siu3UfBYz0O69E0xAX2yjtwcjOMa5rNogy');
INSERT INTO user_account (id, name, email, password) values (2, 'Maria Silva', 'maria@rsi.com.br', '$2a$10$wXMQuCymO7uSjx3y4h6Siu3UfBYz0O69E0xAX2yjtwcjOMa5rNogy');

INSERT INTO permission (id, description) values (1, 'ROLE_SEARCH_ORDER');
INSERT INTO permission (id, description) values (2, 'ROLE_CREATE_ORDER');
INSERT INTO permission (id, description) values (3, 'ROLE_UPDATE_ORDER');
INSERT INTO permission (id, description) values (4, 'ROLE_REMOVE_ORDER');

INSERT INTO permission (id, description) values (5, 'ROLE_SEARCH_CUSTOMER');
INSERT INTO permission (id, description) values (6, 'ROLE_CREATE_CUSTOMER');
INSERT INTO permission (id, description) values (7, 'ROLE_UPDATE_CUSTOMER');
INSERT INTO permission (id, description) values (8, 'ROLE_REMOVE_CUSTOMER');

INSERT INTO permission (id, description) values (9, 'ROLE_SEARCH_PRODUCT');
INSERT INTO permission (id, description) values (10, 'ROLE_CREATE_PRODUCT');
INSERT INTO permission (id, description) values (11, 'ROLE_UPDATE_PRODUCT');
INSERT INTO permission (id, description) values (12, 'ROLE_REMOVE_PRODUCT');


-- admin -- EVERYTHING
INSERT INTO user_account_permission (id_user_account, id_permission) values (1, 1);
INSERT INTO user_account_permission (id_user_account, id_permission) values (1, 2);
INSERT INTO user_account_permission (id_user_account, id_permission) values (1, 3);
INSERT INTO user_account_permission (id_user_account, id_permission) values (1, 4);
INSERT INTO user_account_permission (id_user_account, id_permission) values (1, 5);
INSERT INTO user_account_permission (id_user_account, id_permission) values (1, 6);
INSERT INTO user_account_permission (id_user_account, id_permission) values (1, 7);
INSERT INTO user_account_permission (id_user_account, id_permission) values (1, 8);
INSERT INTO user_account_permission (id_user_account, id_permission) values (1, 9);
INSERT INTO user_account_permission (id_user_account, id_permission) values (1, 10);
INSERT INTO user_account_permission (id_user_account, id_permission) values (1, 11);
INSERT INTO user_account_permission (id_user_account, id_permission) values (1, 12);

-- maria -- ONLY SEARCH
INSERT INTO user_account_permission (id_user_account, id_permission) values (2, 1);
INSERT INTO user_account_permission (id_user_account, id_permission) values (2, 5);
INSERT INTO user_account_permission (id_user_account, id_permission) values (2, 9);
