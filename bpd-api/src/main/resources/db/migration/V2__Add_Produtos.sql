CREATE TABLE produtos (
  idProduto bigint(20) NOT NULL AUTO_INCREMENT,
  idCategoria bigint(20) NOT NULL,
  produto varchar(100),
  preco double(11,2),
  quantidade integer,
  descricao varchar(100),
  foto varchar(100),
  PRIMARY KEY (idProduto)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;