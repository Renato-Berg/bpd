CREATE TABLE pedidoItens (
  idItem bigint(20) NOT NULL AUTO_INCREMENT,
  idPedido bigint(20) NOT NULL,
  idProduto bigint(20) NOT NULL,
  produto varchar(100),
  quantidade integer,
  valor double(11,2),
  subtotal double(11,2),
  PRIMARY KEY (idItem)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;