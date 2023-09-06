/**
Sistema os para gestao de OS
@author Poliana caroline
*/


create database dbsistema;
use dbsistema;

create table usuarios (
    id int primary key auto_increment,
    nome varchar(50) not null,
    login varchar(15) not null unique,
    senha varchar(250) not null,
    perfil varchar(10) not null
);
select*from servicos;


insert into usuarios(nome, login, senha, perfil) values ('Admininistador', 'admin', md5('admin'), 'admin');

create table clientes (
id int primary key auto_increment,
nome varchar(50) not null,
cpf varchar(11) not null unique,
endereco varchar(30) not null,
numero varchar(10) not null,
complemento varchar(20),
bairro varchar(50) not null,
cep varchar(20) not null,
cidade varchar(30) not null,
uf char(2),
fone varchar(12) not null,
email varchar(50) not null unique
);


create table servicos (
       os int primary key auto_increment,
       dataOS timestamp default current_timestamp,
       equipamento varchar(200) not null,
       defeito varchar(200) not null,
       valor decimal(10,2),
       id int not null,
       foreign key (id) references clientes(id)
       );
       
create table fornecedores(
idfornecedor int primary key auto_increment,
razaosocial varchar(50) not null,
nomefantasia varchar(50),
cnpj varchar(14)unique,
logradouro varchar(35) not null,
numero varchar(10) not null,
complemento varchar(20),
cep varchar(20),
bairro varchar(50) not null,
referencia varchar(50) not null,
cidade varchar(30) not null,
uf char(2),
telefone varchar(12),
celular varchar(12) not null,
email varchar(50) not null unique
);



create table produtos(
codigoproduto int primary key auto_increment,
barcode varchar(100)unique,
descricao varchar(100),
foto  longblob not null,
estoque int,
estoquemin int,
valor decimal(10,2),
medida char(2),
armazenagem varchar (25),
idfornecedor int not null,
nome varchar(100) not null,
foreign key (idfornecedor) references fornecedores(idfornecedor)

);








