CREATE TABLE cambio (
   id serial primary key,
   from_currency char(3) not null,
   to_currency char(3) not null,
   conversion_factor numeric(65,2) not null
)