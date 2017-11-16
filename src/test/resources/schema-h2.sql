CREATE SCHEMA IF NOT EXISTS api;

-- DDL para as dados dos times
DROP TABLE IF EXISTS api.time;
CREATE TABLE IF NOT EXISTS api.time
(
	id INTEGER NOT NULL,
	nome CHAR(255),
	PRIMARY KEY (id)
);


-- DDL para as dados dos clientes
DROP TABLE IF EXISTS api.cliente;
CREATE TABLE IF NOT EXISTS api.cliente
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	nome CHAR(255) NOT NULL,
	email CHAR(255) NOT NULL UNIQUE,
	data_nascimento DATE NOT NULL,
	id_time_coracao INTEGER NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (id_time_coracao) REFERENCES api.time(id)
);