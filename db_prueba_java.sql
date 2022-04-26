create database pruebajava;

use pruebaJava;

create table facturacion(
	idfacturacion int primary key,
    fact_condicion varchar(20),
    fact_fecha date,
    fact_numero int,
    fact_estado varchar(10)
);

create table clientes(
	idcliente int primary key,
    cli_nombre varchar(20),
    cli_ci int,
    cli_telefono varchar(13),
    cli_direccion varchar(40),
    cli_tipo varchar(20)
);
alter table clientes change nombre cli_nombre varchar(30);
alter table clientes change ci cli_ci int;
alter table clientes change telefono cli_telefono varchar(13);
alter table clientes change direccion cli_direccion varchar(30);
alter table clientes change tipo cli_tipo varchar(1);

select * from producto;

create table producto(
	idproducto int primary key,
	pro_nombre varchar(20),
    pro_cantidad int,
    pro_iva varchar(10),
    pro_categoria varchar(20),
    pro_precio int
);

alter table categoria change  cat_vombre cat_nombre varchar(25);
alter table categoria change catidad cat_cantidad int;
alter table categoria change iva cat_iva int;
alter table categoria change categoria cat_categoria varchar(10);

create table deatalle_facturacion(
	cantidad int,
    precio int
);

create table personales(
	idpersonal int primary key,
    per_nombre varchar(20),
    per_apellido varchar(20),
	per_clave varchar(45),
    per_tipo varchar(15),
    per_estado varchar(1),
	per_telefono varchar(13),
    per_direccion varchar(40)
);
alter table personales change nombre  per_nombre int;
alter table personales change apellido per_apellido varchar(30);
alter table personales change clave  per_clave varchar(30);
alter table personales change tipo  per_tipo varchar(30);
alter table personales change estado  per_estado varchar(30);
alter table personales change telefono  per_telefono varchar(30);
alter table personales change direccion  per_direccion varchar(30);

alter table personales modify  per_nombre varchar(30);
alter table personales modify  per_apellido varchar(30);
alter table personales modify  per_clave varchar(30);
alter table personales modify  per_tipo varchar(30);
alter table personales modify  per_estado varchar(30);
alter table personales modify  per_telefono varchar(30);
alter table personales modify  per_direccion varchar(30);

insert into personales values (1, "Gustavo", "Galeano", "root", "Administrador", "A", "+595976117832", "Barrio San Juan Bautista");
create table categoria(
	idcategoria int primary key,
    cat_nombre varchar(20),
    cat_descripcion varchar(30)
);
alter table categoria modify  cat_nombre varchar(20);
alter table categoria add column cat_descripcion int;
drop table categoria;

ALTER TABLE categoria DROP COLUMN cat_descripcion;

select * from categoria;

alter table facturacion add column idcliente int;
alter table facturacion add foreign key (idcliente) references clientes(idcliente);
alter table facturacion add column idpersonal int;
alter table facturacion add foreign key (idpersonal) references personales(idpersonal);

alter table deatalle_facturacion add column facturacion_idfacturacion int; 
alter table deatalle_facturacion add column facturacion_idproducto int;
alter table deatalle_facturacion add foreign key (facturacion_idfacturacion) references facturacion(idfacturacion);
alter table deatalle_facturacion add foreign key (facturacion_idproducto) references producto(idproducto);

alter table producto add column idcategoria int;
alter table producto modify  catogoria varchar(30);
alter table producto add foreign key (idcategoria) references categoria(idcategoria);
alter table categoria drop estado;

insert into personales(idpersonal, per_nombre, per_apellido, per_clave, per_tipo, per_estado, per_telefono, per_direccion) value (1, "gustavo", "galeano", "1234", " Gerente", "A", "0986117832", "Barrio  Barcequillo" );

select * from producto;
select * from categoria;

select * from personales;
