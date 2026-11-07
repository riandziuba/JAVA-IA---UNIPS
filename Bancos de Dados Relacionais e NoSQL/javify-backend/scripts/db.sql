create table plan(
	id SERIAL primary key,
	name VARCHAR(50) not null,
	price DECIMAL(10,2) not null,
	has_advertising BOOLEAN not null default false,
	members_limit INT not null default 1,
	offline_mode BOOLEAN not null default false
);

create table adress(
	postal_code varchar(8) primary key,
	street varchar(150),
	neighborhood varchar(100)
);

create table signature(
	id SERIAL primary key,
	plan_id INT not null references plan(id),
	is_active BOOLEAN not null default true
);

create table credit_card(
	id SERIAL primary key,
	signature_id INT not null references signature(id),
	cardholder_name VARCHAR(100),
	last_four_digits VARCHAR(4),
	gateway_token VARCHAR(255),
	expiration_date DATE
);

create table "user"(
	id BIGSERIAL primary key, 
	name VARCHAR(100) not null,
	email varchar not null unique,
	password_hash varchar (60) not null,
	is_holder BOOLEAN not null default true,
	postal_code varchar(8) references adress(postal_code),
	signature_id int references signature(id)
);

create table user_phones(
	id SERIAL primary key,
	user_id BIGSERIAL not null references "user"(id),
	number varchar(20),
	type VARCHAR(20) check (type in ('CELLPHONE', 'BUSINESS', 'LANDLINE'))
);

alter table "user" add column birthday date;