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
    combustivel enum('gasolina', 'flex', 'diesel', 'hibrido') not null,
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
    venda enum('concluida', 'pendente', 'cancelada')
        default 'pendente',
        
    foreign key (id_cliente)
        references clientes(id_clientes),
        
    foreign key (id_funcionario)
        references funcionarios(id_funcionario),
        
    foreign key (id_veiculo)
        references veiculos(id_veiculos)
);

create table usuarios (
    id_usuario int auto_increment primary key,
    id_funcionario int not null unique,
    usuario varchar(50) not null unique,
    senha varchar(255) not null,
    tipo enum('gerente', 'vendedor') default 'vendedor',

    constraint fk_usuario_funcionario
        foreign key (id_funcionario)
        references funcionarios(id_funcionario)
);


USE autofix;

insert into veiculos
(marca, modelo, ano_fabricacao, cor, placa,
 quilometragem, preco, valor_estimado, categoria, combustivel)
values

-- HATCH (1 a 9)
('Fiat', 'Argo Drive 1.0', 2025, 'Branco', 'ABC1D23', 15000, 89000.00, 82000.00, 'hatch', 'flex'),

('Volkswagen', 'Polo Track 1.0 MPI', 2025, 'Prata', 'DEF2G34', 12000, 95000.00, 88000.00, 'hatch', 'flex'),

('Chevrolet', 'Onix LT 1.0 Turbo', 2022, 'Preto', 'GHI3J45', 35000, 82000.00, 75000.00, 'hatch', 'flex'),

('Hyundai', 'HB20 Comfort Plus 1.0', 2019, 'Vermelho', 'JKL4M56', 68000, 68000.00, 62000.00, 'hatch', 'flex'),

('Renault', 'Sandero Zen 1.0', 2017, 'Branco', 'MNO5P67', 95000, 48000.00, 44000.00, 'hatch', 'flex'),

('Peugeot', '208 Allure 1.6', 2020, 'Cinza', 'PQR6S78', 57000, 72000.00, 66000.00, 'hatch', 'flex'),

('Citroen', 'C3 Attraction 1.6', 2018, 'Azul', 'STU7V89', 88000, 55000.00, 50000.00, 'hatch', 'flex'),

('Toyota', 'Etios XLS 1.5', 2016, 'Prata', 'VWX8Y90', 105000, 52000.00, 47000.00, 'hatch', 'flex'),

('Honda', 'Fit EX 1.5', 2015, 'Preto', 'YZA9B12', 112000, 68000.00, 62000.00, 'hatch', 'flex'),

-- SUV (10 a 18)
('Renault', 'Duster Dynamique 2.0', 2019, 'Branco', 'BCD1E23', 78000, 82000.00, 75000.00, 'suv', 'flex'),

('Ford', 'EcoSport Freestyle 1.5', 2017, 'Prata', 'CDE2F34', 92000, 68000.00, 62000.00, 'suv', 'flex'),

('Jeep', 'Renegade Longitude 1.8', 2018, 'Vermelho', 'EFG3H45', 85000, 78000.00, 71000.00, 'suv', 'flex'),

('Nissan', 'Kicks SV 1.6 CVT', 2019, 'Cinza', 'FGH4J56', 72000, 90000.00, 82000.00, 'suv', 'flex'),

('Honda', 'HR-V EX 1.8', 2016, 'Branco', 'HIJ5K67', 110000, 95000.00, 87000.00, 'suv', 'flex'),

('Chevrolet', 'Tracker LT 1.0 Turbo', 2020, 'Preto', 'IJK6L78', 65000, 105000.00, 96000.00, 'suv', 'flex'),

('Caoa Chery', 'Tiggo 5X TXS 1.5 Turbo', 2019, 'Azul', 'KLM7N89', 69000, 95000.00, 87000.00, 'suv', 'flex'),

('Hyundai', 'Tucson GLS 2.0', 2014, 'Prata', 'LMN8P90', 125000, 65000.00, 59000.00, 'suv', 'flex'),

('Toyota', 'Corolla Cross XRV Hybrid 1.8', 2021, 'Branco', 'MNP9Q12', 48000, 155000.00, 142000.00, 'suv', 'hibrido'),

-- PICAPE (19 a 24)
('Fiat', 'Strada Freedom CD 1.3', 2021, 'Branco', 'NPQ1R23', 55000, 100000.00, 92000.00, 'picape', 'flex'),

('Volkswagen', 'Saveiro Robust CS 1.6', 2020, 'Prata', 'PQR2S34', 78000, 78000.00, 71000.00, 'picape', 'flex'),

('Chevrolet', 'Montana LTZ 1.2 Turbo', 2023, 'Preto', 'QRS3T45', 28000, 120000.00, 110000.00, 'picape', 'flex'),

('Fiat', 'Toro Freedom 1.8 AT', 2018, 'Vermelho', 'RST4U56', 87000, 105000.00, 96000.00, 'picape', 'flex'),

('Chevrolet', 'S10 LT 2.8 CTDI', 2014, 'Branco', 'STU5V67', 145000, 120000.00, 108000.00, 'picape', 'diesel'),

('Ford', 'Ranger XLS 2.2 Diesel', 2015, 'Prata', 'TUV6W78', 138000, 125000.00, 113000.00, 'picape', 'diesel'),

-- WAGON (25 a 28)
('Fiat', 'Palio Weekend Adventure 1.8', 2014, 'Verde', 'UVW7X89', 115000, 52000.00, 47000.00, 'wagon', 'flex'),

('Volkswagen', 'SpaceFox Comfortline 1.6', 2014, 'Prata', 'VWX8Z90', 108000, 58000.00, 53000.00, 'wagon', 'flex'),

('Peugeot', '207 SW XR Sport 1.4', 2011, 'Preto', 'WXY9A12', 132000, 42000.00, 38000.00, 'wagon', 'flex'),

('Toyota', 'Corolla Fielder XEi 1.8', 2009, 'Prata', 'XYZ1B23', 155000, 45000.00, 41000.00, 'wagon', 'gasolina'),

-- SEDAN (29 a 40)
('Chevrolet', 'Prisma LT 1.4', 2017, 'Branco', 'ZAB2C34', 96000, 62000.00, 56000.00, 'sedan', 'flex'),

('Volkswagen', 'Voyage Comfortline 1.6', 2017, 'Prata', 'ABC3D45', 102000, 58000.00, 53000.00, 'sedan', 'flex'),

('Fiat', 'Cronos Drive 1.3', 2021, 'Cinza', 'BCD4E56', 52000, 82000.00, 75000.00, 'sedan', 'flex'),

('Toyota', 'Corolla GLi 1.8', 2016, 'Preto', 'CDE5F67', 98000, 100000.00, 92000.00, 'sedan', 'flex'),

('Honda', 'Civic EXL 2.0', 2015, 'Branco', 'DEF6G78', 118000, 95000.00, 87000.00, 'sedan', 'flex'),

('Nissan', 'Versa SV 1.6', 2020, 'Prata', 'EFG7H89', 62000, 85000.00, 78000.00, 'sedan', 'flex'),

('Hyundai', 'HB20S Comfort Plus 1.0', 2021, 'Azul', 'FGH8J90', 49000, 82000.00, 75000.00, 'sedan', 'flex'),

('Chevrolet', 'Cruze LT 1.4 Turbo', 2016, 'Cinza', 'GHI9K12', 105000, 90000.00, 82000.00, 'sedan', 'flex'),

('Kia', 'Cerato SX 1.6', 2014, 'Preto', 'HIJ1L23', 125000, 62000.00, 56000.00, 'sedan', 'gasolina'),

('BMW', '320i Sport GP 2.0 Turbo', 2013, 'Branco', 'IJK2M34', 135000, 105000.00, 96000.00, 'sedan', 'gasolina'),

('Audi', 'A3 Sedan Attraction 1.4 TFSI', 2015, 'Prata', 'JKL3N45', 112000, 110000.00, 100000.00, 'sedan', 'gasolina'),

('Toyota', 'Prius 1.8 Hybrid', 2016, 'Azul', 'KLM4P56', 92000, 115000.00, 105000.00, 'sedan', 'hibrido');