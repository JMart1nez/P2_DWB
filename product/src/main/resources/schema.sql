CREATE DATABASE db_product;
USE db_product;

CREATE TABLE category(
    category_id INT NOT NULL AUTO_INCREMENT,
    category VARCHAR(100) NOT NULL,
    tag VARCHAR(100) NOT NULL,
    parent_category_id INT,
    status TINYINT NOT NULL,
    PRIMARY KEY (category_id)
);

CREATE UNIQUE INDEX ux_category ON category(category);
CREATE UNIQUE INDEX ux_tag ON category(tag);

INSERT INTO category (category, tag, parent_category_id, status) VALUES ('Ropa', 'RP', NULL, 1);
INSERT INTO category (category, tag, parent_category_id, status) VALUES ('Calzado', 'CLZD', 1, 1);