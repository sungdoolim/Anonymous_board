create table memolist (age number,dimg number, memo varchar(20),name varchar(20),phone varchar(20),year number)
drop table memolist

alter table memolist add datetime date default sysdate

create table memolist(title varchar(20), content varchar(100),writer varchar(20))

select * from MEMOLIST

drop table users

select idnum from users where id='1234'

alter table memolist add id number

alter table users add idnum number

update users set idnum=0 where id='1234' 

alter table users drop column datetime

create table users(id varchar(20),name varchar(20),pw varchar(20),state number)

select * from users

insert into users values('123','123test','123123',1)


select * from memolist order by datetime


