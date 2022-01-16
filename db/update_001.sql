create table person (
                        id serial primary key not null,
                        login varchar(2000),
                        password varchar(2000)
);
create table employee(
    id serial primary key ,
    firstname varchar (255),
    secondname varchar (255),
    inn varchar (255),
    hire_date timestamp,
    person_id int references person(id)
);

insert into employee (firstname, secondname, inn, hire_date, person_id)
values ('firstname', 'secondname', '123123', Current_Timestamp , 1);

insert into person (login, password) values ('parsentev', '123');
insert into person (login, password) values ('ban', '123');
insert into person (login, password) values ('ivan', '123');