create table if not exists user (
    id identity,
    username varchar(100) not null unique,
    password varchar(1000) not null
);
create table if not exists authority (
    id identity,
    authority_name varchar(100) not null unique
);
create table if not exists user_authority (
    user_id bigint not null,
    authority_id bigint not null,
    constraint fk_user foreign key (user_id) references user(id),
    constraint fk_authority foreign key (authority_id) references authority(id)
);

CREATE TABLE IF NOT EXISTS HARDWARE (
    ID IDENTITY,
    CODE VARCHAR(10) NOT NULL UNIQUE,
    HARDWARE_NAME VARCHAR(100) NOT NULL,
    PRICE INT,
    HARDWARE_TYPE VARCHAR(100) NOT NULL,
    STOCK INT
    );

CREATE TABLE IF NOT EXISTS REVIEWS (
    ID IDENTITY,
    RATING  VARCHAR(100) NOT NULL,
    TITLE VARCHAR(100) NOT NULL,
    TEXT VARCHAR(100),
    HARDWARE_ID INT NOT NULL,
    FOREIGN KEY (HARDWARE_ID) REFERENCES HARDWARE(ID)
    );
