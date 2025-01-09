create table if not exists socks(
	id bigint primary key,
	color integer,
	cotton integer,
	amount bigint
);

create sequence if not exists socks_seq
	start with 1
	increment by 1
	minvalue 0
	no maxvalue 
	cache 1;
	
insert into socks(id, color, cotton, amount) values
	(nextval('socks_seq'), X'FF8CCB5E'::bit(32)::integer, 50, 35),
	(nextval('socks_seq'), X'FFA7FC00'::bit(32)::integer, 75, 5),
	(nextval('socks_seq'), X'FFE32636'::bit(32)::integer, 95, 25),	
	(nextval('socks_seq'), X'FF30D5C8'::bit(32)::integer, 10, 5),
	(nextval('socks_seq'), X'FF240935'::bit(32)::integer, 10, 5);
