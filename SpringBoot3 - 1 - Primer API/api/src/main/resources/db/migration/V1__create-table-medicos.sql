create table medicos(
	id bigint not null auto_increment,
	nombre varchar(100) not null,
	email varchar(100) not null unique,
	documento varchar(100) not null unique,
	especialidad varchar(100) not null,
	calle varchar(100) not null,
	distrito varchar(100),
	piso varchar(6),
	numero int not null,
	ciudad varchar(100) not null,
	primary key(id)
);