create table usuario (
    id_usuario int, primary key(id_usuario),
    nome varchar(50)
);

create table favorito (
    data_fav date,
    id_questão int,
    id_usuario int, 
    PRIMARY KEY (id_questão, id_usuario),
    FOREIGN KEY (id_questão) REFERENCES questão(id_questão) ON DELETE CASCADE,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE
);

create table categoria (
    id_categoria int, primary key (id_categoria),
    titulo varchar(50),
    tamanho int
);

create table questão (
    id_questão int, primary key (id_questão),
    descrição text,
    titulo varchar(50),
    dificuldade int,
    exemplos text
);

create table caso_de_teste (
    id int,
    codigo text,
    id_questão int
);

create table submissão (
    id_submissão int, primary key (id_submissão),
	id_questão int,
    resultado text,
    FOREIGN KEY (id_questão) REFERENCES questão(id_questão) ON DELETE CASCADE
);

create table possuir(
    id_categoria int,
    id_questão int,
    PRIMARY KEY (id_categoria, id_questão),
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria) ON DELETE CASCADE,
    FOREIGN KEY (id_questão) REFERENCES questão(id_questão) ON DELETE CASCADE
);



------------------------SQLITE3 VERSION------------------------
CREATE TABLE usuario (
    id_usuario INTEGER PRIMARY KEY,
    nome TEXT
);

CREATE TABLE favorito (
    data_fav DATE,
    id_questao INTEGER,
    id_usuario INTEGER, 
    PRIMARY KEY (id_questao, id_usuario),
    FOREIGN KEY (id_questao) REFERENCES questao(id_questao) ON DELETE CASCADE,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE
);

CREATE TABLE categoria (
    id_categoria INTEGER PRIMARY KEY,
    titulo TEXT,
    tamanho INTEGER
);

CREATE TABLE questao (
    id_questao INTEGER PRIMARY KEY,
    descricao TEXT,
    titulo TEXT,
    dificuldade INTEGER,
    exemplos TEXT
);

CREATE TABLE caso_de_teste (
    id INTEGER,
    codigo TEXT,
    id_questao INTEGER
);

CREATE TABLE submissao (
    id_submissao INTEGER PRIMARY KEY,
    id_questao INTEGER,
    resultado TEXT,
    FOREIGN KEY (id_questao) REFERENCES questao(id_questao) ON DELETE CASCADE
);

CREATE TABLE possuir (
    id_categoria INTEGER,
    id_questao INTEGER,
    PRIMARY KEY (id_categoria, id_questao),
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria) ON DELETE CASCADE,
    FOREIGN KEY (id_questao) REFERENCES questao(id_questao) ON DELETE CASCADE
);
