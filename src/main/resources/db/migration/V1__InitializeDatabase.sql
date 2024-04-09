create table account_schema.users
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    username     varchar(255) unique not null,
    password varchar(255) not null,
    role     varchar(255) not null
);

insert into account_schema.users(username, password, role)
values  ('john', 'smith', 'ROLE_GameMaster'),
        ('jane', 'doe', 'ROLE_User'),
        ('admin', 'admin', 'ROLE_GameMaster');
