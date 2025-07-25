CREATE DATABASE pahana_edu_db;
USE pahana_edu_db;

CREATE TABLE users (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE customers (
    account_number VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL,
    telephone VARCHAR(15) NOT NULL,
    units_consumed INT NOT NULL
);

CREATE TABLE items (
    item_id VARCHAR(10) PRIMARY KEY,
    item_name VARCHAR(100) NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE bills (
    bill_id INT AUTO_INCREMENT PRIMARY KEY,
    account_number VARCHAR(10),
    total_amount DECIMAL(10, 2) NOT NULL,
    bill_date DATE NOT NULL,
    FOREIGN KEY (account_number) REFERENCES customers(account_number)
);