create table IF NOT EXISTS users (
    user_id INT PRIMARY KEY,
    name varchar(225) not null,
    last_name varchar(225) not null,
    email varchar(225) not null,
    user_name varchar(225) not null,
    password varchar(225) not null check(length(password)>5),
    active boolean
);
create table IF NOT EXISTS roles (
    role_id INT PRIMARY KEY,
    role varchar(225) ,
);
create table IF NOT EXISTS user_role (
user_id INT, FOREIGN KEY(user_id)REFERENCES users(user_id),
    role_id INT ,FOREIGN KEY(role_id)REFERENCES roles(role_id)

);
insert into roles (role_id,role) values (1,'ADMIN');
insert into roles (role_id,role) values (2,'USER');