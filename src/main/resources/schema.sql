create table if not exists cloth (
  id identity,
  name varchar(50) not null,
  yearcreated int not null,
  price numeric not null,
  brand varchar(50) not null,
  created_at timestamp not null
);