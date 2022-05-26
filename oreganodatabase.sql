CREATE DATABASE oreganodatabase;

use oreganodatabase;

create table Customer (
C_id int NOT NULL primary key,
C_name varchar(32) DEFAULT NULL,
Phone int,
Address varchar(32) DEFAULT NULL);
ALTER TABLE Customer MODIFY C_name  varchar(32) NOT NULL;
ALTER TABLE Customer MODIFY Phone int DEFAULT NULL;
Alter table Customer modify C_id int NOT NULL auto_increment;
ALTER TABLE Customer MODIFY Address  varchar(100) DEFAULT NULL;

create table Orders (
O_id int NOT NULL,
C_id int NOT NULL,
total_price int NOT NULL,
discount int DEFAULT NULL,
Notes varchar(100) DEFAULT NULL,
Elapsed_time real,
Table_No int DEFAULT NULL,
Primary Key (O_id),
FOREIGN KEY (C_id)
      REFERENCES Customer (C_id)
	ON DELETE NO ACTION
	ON UPDATE CASCADE);
Alter table Orders modify O_id int NOT NULL auto_increment;
SHOW CREATE TABLE orders;
ALTER TABLE Orders DROP FOREIGN KEY orders_ibfk_1;
ALTER TABLE Orders ADD FOREIGN KEY (c_id) REFERENCES Customer(c_id) ON DELETE NO ACTION
	ON UPDATE CASCADE;



CREATE table Meals (
meal_id int NOT NULL,
name varchar(30) NOT NULL,
Price int NOT NULL);
alter table Meals modify meal_id int not null primary key;
alter table Meals modify meal_id varchar(10) not null;

CREATE table Order_line (
line_id int not null,
O_id int not null,
m_id varchar(10) not null,
quantity int default 1,
Primary Key (line_id),
FOREIGN KEY (O_id) REFERENCES Orders (O_id)
	ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (m_id) REFERENCES Meals (meal_id)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION);
SHOW CREATE TABLE order_line;
ALTER TABLE Order_line DROP FOREIGN KEY order_line_ibfk_1;
ALTER TABLE Order_line ADD FOREIGN KEY (o_id) REFERENCES orders(o_id) ON DELETE NO ACTION
	ON UPDATE CASCADE;
ALTER TABLE Order_line MODIFY m_id varchar(10) not null;
Alter table Order_line modify O_id int NOT NULL auto_increment;
ALTER TABLE Order_line MODIFY line_id int not null auto_increment;
    
    
    update supplies set TypeName="Ahmad",Quantity=99,ExpireDate="asdasdasd" where TypeId=22;
    select * from supplies;
Create table Roles (
role_id varchar(10) not NULL,
role_name varchar(30) not null,
base_salary int not null,
overtime_hours_price int not null,
primary key (role_id));
create table Employees (
e_id int not NULL,
e_name varchar(20) not null,
birthdate date,
phone int not null,
r_id varchar(10) not NULL,
overtime_hours int default 0,
primary key (e_id),
foreign key (r_id) references Roles (role_id)
ON DELETE NO ACTION ON UPDATE CASCADE);
ALTER table Employees ADD bonus int default 0;
Alter table Employees modify e_id int NOT NULL auto_increment;
drop table Employees;
create table Employees (
e_id int not NULL,
e_name varchar(20) not null,
birthdate date,
phone int not null,
r_id varchar(10) not NULL,

overtime_hours int default 0,
primary key (e_id),
foreign key (r_id) references Roles (role_id)
ON DELETE NO ACTION ON UPDATE CASCADE);
ALTER table Employees ADD bonus int default 0;
insert into employees values (11, "Mohammad Ayoub", '2022-01-22', 0598931957, "Ch_01", 0, 0);
insert into employees values (01, "Mohammad Ayoub", '1991-6-12', 0598931957, "Ch_01", 0 );
create table supplies (
TypeId int not NULL,
TypeName varchar(30) not NULL, 
Quantity int default 1,
ExpireDate varchar(10) not null,
primary key(TypeId));

create table Expenses (
BillId int not NULL,
BillDate varchar(10),
TotalPay int not null,
primary key (BillId));

create table exp_for_emp (
BillId int not NULL,
EId int not NULL,
BaseSalary int not null,
OvertimePrice int default 0, #the price of the overtime hours that the employee worked
primary key(BillId, EId),
foreign key(BillId) references Expenses (BillId) ON update cascade ON DELETE NO ACTION,
foreign key(EId) references Employees (e_id) ON update cascade ON DELETE NO ACTION);


create table exp_for_sup (
BillId int not NULL,
TypeId int not NULL,
PricePerUnit int not null,
TypeQuant int default 1, #how many pieces did the restaurant buy from this type
Primary key(BillId, TypeId),
foreign key(BillId) references Expenses (BillId) ON update cascade ON DELETE NO ACTION,
foreign key(TypeId) references Supplies (TypeId) ON update cascade ON DELETE NO ACTION);


insert into Meals values ("B_01", "Classic Burger", 14);
insert into Meals values ("B_02", "Mix cheese Burger", 16);
insert into Meals values ("B_03", "Oregano Burger", 17);
insert into Meals values ("C_01", "Crispy Burger", 15);
insert into Meals values ("C_02", "Dallas Burger", 17);
insert into Meals values ("C_03", "Brazilian Burger", 17);
insert into Meals values ("P_01", "vegetable pizza", 25);
insert into Meals values ("P_02", "Salami Pizza", 25);
insert into Meals values ("P_03", "Oregano Pizza", 30);
insert into Meals values ("Z_01", "Zenger Sandwich", 12);
insert into Meals values ("Z_02", "Zenger meal", 17);
insert into Meals values ("W_01", "Bufflo Winges", 23);
insert into Meals values ("W_02", "BBQ Winges", 23);

insert into roles values ("Ch_01", "master chef", 3500, 10);
insert into roles values ("Ch_02", "chef assistant", 3000, 8);
insert into roles values ("Ca_00", "Cashier", 2500, 8);
insert into roles values ("Cl_00", "cleaning", 2000, 7);
insert into roles values ("Wa_00", "Waiter", 2200, 7);
select * from employees;
insert into employees values (111, "Mohammad", '2022-01-22', 1957, "Ch_01", 0, 0);
insert into employees values (22, "Moayyad Salman",'1995-1-2', 0593192357, "Ch_02", 14, 0);
insert into employees values (33, "Qatada Ahmad", '2000-1-2', 0568337744, "Ca_00", 0, 450);
insert into employees values (44, "Huthaifa Salman", 22/7/2003, 0592335757, "Cl_00", 11, 0);
insert into employees values (55, "Omar Msameh", 19/12/1998, 0598221107, "Wa_00", 0, 0);
insert into employees values (66, "Laith Karmi", 25/12/1998, 0599315334, "Wa_00", 0, 300);

insert into customer (C_name, phone, address) values ("Abdulhadi", 0598415844, "Shweke, main street");
insert into customer (C_name, phone, address) values ("Nareeman", 0597515039, "downtown, near thabet thabet statue");
insert into customer (C_name, phone) values ("Raha Salameh", 0569212337);

select * from supplies;
update  supplies set Type_name = "Fries"   where Type_id =77;

insert into supplies values (77, "potato",15,"2022-01-22");
insert into supplies values (88, "potato",15,"2022-01-22");
insert into supplies values (66, "potato",15,"2022-01-22");
insert into supplies values (55, "potato",15,"2022-01-22");
select * from expenses;
insert into expenses values (1,"2022-1-2", 1500);
insert into expenses values (2,"2022-1-6", 750);
sET SQL_SAFE_UPDATES = 0;
select * from exp_for_sup;
insert into exp_for_sup values (1,66,3, 60);
insert into exp_for_sup values (2,77,15, 52);
insert into exp_for_sup values (3,88,25, 552);
insert into exp_for_sup values (4,55,15, 52);
select * FROM supplies WHERE TypeId=12 and TypeName="laith " and Quantity=88 and ExpireDate ="asd";


update  supplies set Type_name = '"apple"' where Type_id = "7";
select * from supplies where typeId=88;
Insert into supplies (TypeId, TypeName,Quantity, ExpireDate) values (91,"dy",15,"hfg");

select * from employees;
select * from exp_for_emp;
insert into exp_for_emp values (6,22,3000, 60);
insert into exp_for_emp values (2,44,15, 52);
insert into exp_for_emp values (3,33,15, 52);
insert into exp_for_emp values (4,66,15, 52);
insert into exp_for_emp values (5,55,15, 52);
select * from employees;
update  exp_for_sup set TypeQuant = 100 where TypeId = 77 AND BillId=2;
select * from exp_for_emp;
insert into exp_for_emp values (1,22,3000, 60);
insert into exp_for_emp values (2,33,15, 52);
