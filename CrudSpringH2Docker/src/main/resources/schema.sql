create table if not exists Employee
(
   id integer not null,
   first_name varchar(255) not null,
   last_name varchar(255) not null,
   email_id varchar(255) not null,
   primary key(id)
);