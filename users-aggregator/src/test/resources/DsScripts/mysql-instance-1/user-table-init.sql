create table users
(
    ldap_login bigint,
    login varchar(100),
    name     varchar(100),
    surname  varchar(100)
);

insert into users (ldap_login, login, name, surname)
values (1, 'login1', 'name1', 'surname1'),
       (123, 'login2', 'name2', 'surname2');