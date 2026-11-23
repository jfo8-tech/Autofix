create database if not exists autofix;
use autofix;

create table clientes (
id_clientes int auto_increment primary key,
nome varchar(100) not null,
cpf varchar(14) not null unique,
telefone varchar(20),
email varchar(100),
endereco varchar(150),
data_cadastro date not null
);

create table funcionarios (
id_funcionario int auto_increment primary key,
nome varchar(100) not null,
cpf varchar(14) not null unique,
telefone varchar(20),
email varchar(100),
cargo varchar(50) not null,
salario decimal(10,2) not null,
data_admissao date not null
);

create table veiculos (
id_veiculos int auto_increment primary key,
marca varchar(50) not null,
modelo varchar(80) not null,
ano_fabricacao int not null,  
cor varchar(30),
placa varchar(10) unique,
quilometragem int default 0,
preco decimal(10,2) not null,
categoria enum('hatch', 'suv', 'picape', 'wagon', 'sedan') not null,
combustivel enum('gasolina', 'flex', 'disel', 'hibrido') not null,
disponibilidade enum('disponivel', 'vendido', 'reservado') 
	default 'disponivel'
); 

create table vendas (
id_venda int auto_increment primary key,
id_cliente int not null,
id_funcionario int not null,
id_veiculo int not null,
data_venda date not null,
preco decimal(10,2),
pagamento varchar(50) not null,
venda enum('concuida', 'pendente', 'cancelada')
	default 'pendente',
    
foreign key (id_cliente)
	references clientes(id_cliente),
    
foreign key (id_funcionario)
	references clientes(id_cliente),
    
foreign key (id_veiculo)
	references veiculos(id_veiculo)
);

create table usuarios (
id_usuario int auto_increment primary key,
id_funcionario int not null unique,
usuaario varchar(50) not null unique,
senha varchar(255) not null,
tipo enum('gerente', 'vendedor') default 'vendedor',

constraint fk_usuario_funcionario
	foreign key (id_funcionario)
    references funcionarios(id_funcionario)
);