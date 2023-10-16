create table usuario (
    id_usuario int, 
	primary key(id_usuario),
    nome varchar(50)
);

create table favorito (
    id_fav int, primary key (id_fav),
    descrição text,
    data_fav date,
    id_questão int
    id_usuario int
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
    resultado text
);

create table possuir(
    id_categoria int,
    id_questão int
);

------------------------------favorito------------------------------
alter table favorito
ADD constraint fav_question_id_fk foreign key (id_questão) references questão(id_questão) 
ON UPDATE NO ACTION ON DELETE CASCADE,
ADD CONSTRAINT fav_id_usuario_fk foreign key (id_usuario) references usuario(id_usuario)
ON UPDATE NO ACTION ON DELETE CASCADE;

------------------------------possuir------------------------------
alter table possuir
ADD CONSTRAINT pk_poss PRIMARY KEY(id_categoria,id_questão);

alter table possuir
ADD constraint poss_categ_id_FK foreign key (id_categoria) references categoria(id_categoria)
ON UPDATE NO ACTION ON DELETE CASCADE;

alter table possuir
ADD CONSTRAINT poss_question_id_fk foreign key (id_questão) references questão(id_questão)
ON UPDATE NO ACTION ON DELETE CASCADE;

------------------------------submissão------------------------------
alter table submissão
ADD CONSTRAINT  sub_question_id_fk foreign key (id_questão) references questão(id_questão)
ON UPDATE NO ACTION ON DELETE CASCADE;

------------------------------caso de teste------------------------------
alter table caso_de_teste
ADD CONSTRAINT  caso_de_teste_question_id_fk foreign key (id_questão) references questão(id_questão)
ON UPDATE NO ACTION ON DELETE CASCADE;