create database db_banking_app;

use db_banking_app;

create table tbl_user
(
id int not null auto_increment primary key,
account_number int8 not null,
name varchar(255) not null,
address varchar(255) not null,
amount double not null
);

describe tbl_user;

select * from tbl_user;

insert into tbl_user
(account_number, name, address, amount)
values
(?,?,?,?);

select * from tbl_user where account_number = ?;

update tbl_user
set
amount = ?
where id = ?;

-- use the following syntax if primary key is not called to update or delete
-- SET SQL_SAFE_UPDATES=0;
delete from tbl_user where id = 5;
