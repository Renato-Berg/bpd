CREATE TABLE pedidos (
  idPedido bigint(20) NOT NULL AUTO_INCREMENT,
  idCliente bigint(20) NOT NULL,
  data date,
  status varchar(100),
  sessao varchar(100),
  PRIMARY KEY (idPedido)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;