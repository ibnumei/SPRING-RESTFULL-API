CREATE DATABASE spring_restful_api;

USE spring_restful_api;

CREATE TABLE users
(
    username         VARCHAR(100) NOT NULL,
    password         VARCHAR(100) NOT NULL,
    name             VARCHAR(100) NOT NULL,
    token            VARCHAR(100),
    token_expired_at BIGINT,
    balance          integer
    PRIMARY KEY (username),
    UNIQUE (token)
);

CREATE TABLE category
(
    id         VARCHAR(100) NOT NULL,
    username   VARCHAR(100) NOT NULL,
    category_name VARCHAR(100) NOT NULL,
    PRIMARY KEY (id),
	FOREIGN KEY fk_users_category (username) REFERENCES users (username)
);

CREATE TABLE transaction
(
    id         VARCHAR(100) NOT NULL,
    username   VARCHAR(100) NOT NULL,
    categoryId   VARCHAR(100) NOT NULL,
    type VARCHAR(10) NOT NULL,
    amount BIGINT NOT NULL,
    description TEXT,
    created_date TIMESTAMP,
    PRIMARY KEY (id),
	FOREIGN KEY fk_users_transaction (username) REFERENCES users (username),
    FOREIGN KEY fk_category_transaction (categoryId) REFERENCES category (id)

);