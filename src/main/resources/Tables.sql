--h2 is typically used to setup a test database, not a prod database.
--first, drop your tables (to reset your database for testing)
--then create your tables
drop table User;

create table User(
    id int primary key,
    username varchar(255),
    password varchar(255),
    code varchar(255)
);

