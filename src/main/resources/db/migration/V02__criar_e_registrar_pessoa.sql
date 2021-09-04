CREATE TABLE pessoa (
  codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(50) NOT NULL,
  logradouro VARCHAR(50) NOT NULL,
  numero INT(10) NOT NULL,
  complemento VARCHAR(30),
  cep VARCHAR(10),
  bairro VARCHAR(30),
  cidade VARCHAR(20),
  estado VARCHAR(20),
  ativo BOOLEAN
) ENGINE=InnoDB DEFAULT CHARSET=utf8;