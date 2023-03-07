drop table if exists UserData;

create table UserData(
    id int primary key auto_increment,
    username varchar(255),
    password varchar(255),
    code int
);

