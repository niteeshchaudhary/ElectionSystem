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

create table if not exists elections(
ename varchar(30) not null,
vdate date not null,
time time not null,
contact_no varchar(25) not null, 
createdby varchar(30) not null,
status varchar(2) not null,
primary key(ename,vdate),
foreign key(createdby) references ecusers(Id)
);

create table if not exists candidates(
ename varchar(30) not null,
vdate date not null,
Id varchar(20) not null,
menifesto text(800) not null,
votes int default(0),check(votes>=0),
primary key(ename,vdate,Id),
foreign key(Id) references voters(Id)
foreign key(ename,vdate) references elections(ename,vdate)

create table if not exists voting_history(
ename varchar(30) not null,
vdate date not null,
Id varchar(20) not null,
voted boolean default(false),
primary key(ename,vdate,Id),
foreign key(Id) references voters(Id),
foreign key(ename,vdate) references elections(ename,vdate)
);




insert into ecusers values('admin@gmail.com','admin');