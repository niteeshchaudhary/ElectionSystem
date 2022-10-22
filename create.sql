--CREATE USER if not exists  'election'@'localhost' IDENTIFIED BY 'election'; 
CREATE DATABASE if not exists elections;
--grant all on elections to 'election'@'localhost';

use elections;
-- voters

create table if not exists voters(Id varchar(20) primary key,
password varchar(20) not null
);

-- managingteam
create table if not exists  ecusers(Id varchar(20) primary key,
password varchar(20) not null
);



create table if not exists votersdetails (Id varchar(20) primary key,
name varchar(30) not null,
--photo_url varchar(100),
dob date not null,
phone_no varchar(25) not null, 
address varchar(100),
foreign key(Id) references voters(Id)
);

create table if not exists  ecusersdetails (Id varchar(20) primary key,
name varchar(30) not null,
--photo_url varchar(100),
phone_no varchar(25) not null, 
address varchar(100),
foreign key(Id) references ecusers(Id)
);

insert into ecusers values('admin','admin');