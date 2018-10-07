drop table recursive;
drop table booking;
drop table employee;
drop table conferenceroom;

create table employee(id int primary key, firstname varchar2(20), lastname varchar2(20), department varchar2(20), email varchar2(30), username varchar2(10), password varchar2(10));
create table conferenceroom(id int primary key, name varchar2(20), capacity int, projector varchar2(1), phone varchar2(1), system varchar2(1));
create table booking(id int primary key, roomid int, foreign key(roomid) references conferenceroom(id), bookingdate date, startslot int, endslot int, recursive varchar2(1), employeeid int, foreign key(employeeid) references employee(id), status varchar2(10));
create table recursive(bookingid int, foreign key(bookingid) references booking(id), fromdate date, todate date, recurrence varchar2(1)); 