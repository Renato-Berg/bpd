CREATE TABLE clientes (
  idCliente bigint(20) NOT NULL AUTO_INCREMENT,
  nome varchar(100),
  email varchar(100),
  senha varchar(20),
  rua varchar(100),
  cidade varchar(100),
  bairro varchar(100),
  cep varchar(100),
  estado varchar(100),
  PRIMARY KEY (idCliente)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;