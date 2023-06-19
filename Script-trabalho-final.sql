CREATE TABLE clientes(
	ID serial4 PRIMARY KEY NOT NULL,
	documento VARCHAR(120) NOT NULL UNIQUE ,
	nome VARCHAR(120) NOT NULL,
	rg INTEGER ,
	email VARCHAR(50),
	telefone VARCHAR(15)
);

CREATE TABLE endereco(
	id_cliente int,
	cep INTEGER NOT NULL,
	rua VARCHAR(150) NOT NULL,
	numero INTEGER NOT NULL,
	bairro VARCHAR(50) NOT NULL,
	cidade VARCHAR(50) NOT NULL,
	estado VARCHAR(15) NOT NULL,
	FOREIGN KEY (id_cliente)
		REFERENCES clientes(id)
);