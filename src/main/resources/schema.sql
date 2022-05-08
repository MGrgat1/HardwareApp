create table if not exists hardware (
    id identity,
    code varchar(10) not null unique,
    hardware_name varchar(100) not null,
    price int not null,
    hardware_type varchar(100) not null,
    items_remaining int not null
    );