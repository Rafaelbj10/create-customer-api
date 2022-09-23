# cadastro-de-clientes

Crie um schema chamado digibank em seu mysql com as credenciais conforma configurado na properties.

crie a tabela cliente com o comando abaixo:
create table digibank.TB_CLIENTE
(
ID int auto_increment
primary key,
NAME varchar(255) null,
CPF varchar(11)  null,
RG varchar(9)   null,
ADDRESS varchar(255) null,
CEP varchar(255) null,
EMAIL varchar(100) null,
TELEPHONE varchar(15)  null,
DESCRIPTION varchar(255) null,
BIRTH_DATE date null
);
