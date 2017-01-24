DROP TABLE clientes CASCADE CONSTRAINTS
;
DROP TABLE contacorrente CASCADE CONSTRAINTS
;

CREATE TABLE clientes
(
	cpf       VARCHAR2(15) NOT NULL,
	nome      VARCHAR2(50) NOT NULL,
	endereco  VARCHAR2(200) NOT NULL
)
;


CREATE TABLE contacorrente
(
	cpf      VARCHAR2(15) NOT NULL,
	agencia  VARCHAR2(5) NOT NULL,
	conta    VARCHAR2(7) NOT NULL
)
;



ALTER TABLE clientes ADD CONSTRAINT PK_clientes 
	PRIMARY KEY (cpf)
;

ALTER TABLE contacorrente ADD CONSTRAINT PK_contacorrente 
	PRIMARY KEY (agencia, conta)
;



ALTER TABLE contacorrente ADD CONSTRAINT FK_contacorrente_clientes 
	FOREIGN KEY (cpf) REFERENCES clientes (cpf)
;
