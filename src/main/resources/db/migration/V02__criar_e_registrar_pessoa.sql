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

INSERT INTO pessoa (
  nome,
  logradouro,
  numero,
  complemento,
  cep,
  bairro,
  cidade,
  estado,
  ativo
) values (
  'Emilio de Aquino Calvet',
  'rua Vicente Carlos Santiago',
  1606,
  'Casa da Caixa',
  '62900-000',
  'Campo Federal',
  'Russas',
  'Ceará',
  1
);

INSERT INTO pessoa (
  nome,
  logradouro,
  numero,
  complemento,
  cep,
  bairro,
  cidade,
  estado,
  ativo
) values (
  'Carmem Lúcia Neo Alves',
  'rua Vicente Carlos Santiago',
  1606,
  'Casa da Caixa',
  '62900-000',
  'Campo Federal',
  'Russas',
  'Ceará',
  1
);
INSERT INTO pessoa (
  nome,
  logradouro,
  numero,
  complemento,
  cep,
  bairro,
  cidade,
  estado,
  ativo
) values (
  'Lua',
  'rua Vicente Carlos Santiago',
  1606,
  'Casa da Caixa',
  '62900-000',
  'Campo Federal',
  'Russas',
  'Ceará',
  1
);

INSERT INTO pessoa (
  nome,
  logradouro,
  numero,
  complemento,
  cep,
  bairro,
  cidade,
  estado,
  ativo
) values (
  'Nicolau',
  'rua Vicente Carlos Santiago',
  1606,
  'Casa da Caixa',
  '62900-000',
  'Campo Federal',
  'Russas',
  'Ceará',
  1
);
INSERT INTO pessoa (
  nome,
  logradouro,
  numero,
  complemento,
  bairro,
  cidade,
  estado,
  ativo
) values (
  'Rosileny de Aquino Ferreira',
  'rua Vicente Nobre de Macêdo',
  444,
  'Casa 6',
  'Messejana',
  'Fortaleza',
  'Ceará',
  1
);

INSERT INTO pessoa (
  nome,
  logradouro,
  numero,
  bairro,
  cidade,
  estado,
  ativo
) values (
  'Samia Leite Calvet',
  'rua Dr. José Plutarco',
  7,
  'Cidade dos Funcionários',
  'Fortaleza',
  'Ceará',
  1
);
INSERT INTO pessoa (
  nome,
  logradouro,
  numero,
  bairro,
  cidade,
  estado,
  ativo
) values (
  'Samuel Leite Calvet',
  'rua Dr. José Plutarco',
  7,
  'Cidade dos Funcionários',
  'Fortaleza',
  'Ceará',
  1
);

INSERT INTO pessoa (
  nome,
  logradouro,
  numero,
  ativo
) values (
  'Amanda Lais do Nascimento Calvet',
  'alguma rua da itália',
  1234,
  1
);

INSERT INTO pessoa (
  nome,
  logradouro,
  numero,
  bairro,
  cidade,
  estado,
  ativo
) values (
  'Atila Silva Calvet',
  'rua Tabelião Joaqui Coelho',
  526,
  'Sapiranga',
  'Fortaleza',
  'Ceará',
  1
);

INSERT INTO pessoa (
  nome,
  logradouro,
  numero,
  bairro,
  cidade,
  estado,
  ativo
) values (
  'Valdelice Maria Leite Calvet',
  'rua Tabelião Joaqui Coelho',
  526,
  'Sapiranga',
  'Fortaleza',
  'Ceará',
  1
);