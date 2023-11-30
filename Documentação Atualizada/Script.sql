CREATE TABLE categoria (
                           id_categoria INTEGER PRIMARY KEY AUTOINCREMENT,
                           titulo VARCHAR(50),
                           descricao TEXT
);


CREATE TABLE questao (
                         id_questao INTEGER PRIMARY KEY AUTOINCREMENT,
                         descricao TEXT,
                         titulo VARCHAR(50),
                         dificuldade INTEGER,
                         exemplos TEXT
                         favorito INTEGER,
                         id_categoria INTEGER,
                         FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria) ON DELETE CASCADE
);

CREATE TABLE caso_de_teste (
                               id_caso_de_teste INTEGER PRIMARY KEY AUTOINCREMENT,
                               input TEXT,
                               output TEXT,
                               id_questao INTEGER,
                               FOREIGN KEY (id_questao) REFERENCES questão(id_questao) ON DELETE CASCADE
);
