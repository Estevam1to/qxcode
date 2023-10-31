CREATE TABLE questão (
                         id_questão INTEGER PRIMARY KEY AUTOINCREMENT,
                         descrição TEXT,
                         titulo VARCHAR(50),
                         dificuldade INTEGER,
                         exemplos TEXT
);

CREATE TABLE categoria (
                           id_categoria INTEGER PRIMARY KEY AUTOINCREMENT,
                           titulo VARCHAR(50),
                           descrição TEXT
);

CREATE TABLE caso_de_teste (
                               id_caso_de_teste INTEGER PRIMARY KEY,
                               input TEXT,
                               output TEXT,
                               id_questão INTEGER,
                               FOREIGN KEY (id_questão) REFERENCES questão(id_questão) ON DELETE CASCADE
);

CREATE TABLE submissão (
                           id_submissão INTEGER PRIMARY KEY,
                           id_questão INTEGER,
                           arq_teste TEXT,
                           FOREIGN KEY (id_questão) REFERENCES questão(id_questão) ON DELETE CASCADE
);

CREATE TABLE possuir_categoria (
                                   id_categoria INTEGER,
                                   id_questão INTEGER,
                                   PRIMARY KEY (id_categoria, id_questão),
                                   FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria) ON DELETE CASCADE,
                                   FOREIGN KEY (id_questão) REFERENCES questão(id_questão) ON DELETE CASCADE
);

CREATE TABLE trilhas (
                         id_trilha INTEGER PRIMARY KEY,
                        dificuldade INTEGER,
                         titulo TEXT,
                         descricao TEXT
);

CREATE TABLE trilhas_questao (
                                 id_trilha INTEGER,
                                 id_questão INTEGER,
                                 PRIMARY KEY (id_trilha, id_questão),
                                 FOREIGN KEY (id_trilha) REFERENCES trilhas(id_trilha) ON DELETE CASCADE,
                                 FOREIGN KEY (id_questão) REFERENCES questão(id_questão) ON DELETE CASCADE
);

CREATE TABLE favorito (
                          id_questão INTEGER PRIMARY KEY,
                          FOREIGN KEY (id_questão) REFERENCES questão(id_questão) ON DELETE CASCADE
);



