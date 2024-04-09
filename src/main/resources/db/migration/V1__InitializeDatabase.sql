create table account_schema.users
(
    ID INT PRIMARY KEY,
    name     varchar(255) unique not null,
    password varchar(255) not null,
    role     varchar(255) not null
);

create sequence account_schema.users_seq
START WITH 1000;

insert into account_schema.users(id, name, password, role)
values  (1, 'miki', 'mecava', 'ROLE_GameMaster'),
        (2, 'bubica', 'maaalaa', 'ROLE_User'),
        (3, 'admin', 'admin', 'ROLE_GameMaster');
