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

Exemplo de request para cadastrar novo cliente:
{
"birthDate": "1993-07-26",
"cep": "06172006",
"cpf": "12345679",
"description": "Cadastro de cliente",
"email": "email@gmail.com",
"name": "Nome completo",
"rg": "4897623488",
"telephone": "11987665349"
}
