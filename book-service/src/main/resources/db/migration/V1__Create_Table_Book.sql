create table book (
    id serial primary key,
    autor varchar(40) not null,
    launch_date date not null,
    price numeric(10,2) not null,
    title varchar(80) not null
)