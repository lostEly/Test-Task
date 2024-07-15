create table users
(
    user_id  bigint,
    login varchar(100),
    first_name     varchar(100),
    last_name  varchar(100)
);

insert into users (user_id, login, first_name, last_name)
values (1, 'login1', 'first_name1', 'last_name1'),
       (2, 'login2', 'first_name2', 'last_name2');