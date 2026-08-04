create database PolyCoffee;
use PolyCoffee;
drop database PolyCoffee; 

drop table users
go

create table users(
	IDUser nvarchar(10) primary key,
	UserName nvarchar(50),
	UserPass nvarchar(50),
	UserPhone varchar(10),
	UserEmail nvarchar(50),
	Role int,
	UserImg nvarchar(255),
	UserActive bit
);

drop table category
go

create table category(
	IDCate nvarchar(10) primary key,
	Catename nvarchar(50),
	Catetype nvarchar(50)
);
go

drop table drink
go

create table drink(
	IDDrink nvarchar(10) primary key,
	DrinkName nvarchar(50),
	DrinkPrice decimal(10,2),
	DrinkIMG nvarchar(50),
	DrinkActive bit,
	DrinkDescription nvarchar(100),
	IDCate nvarchar(10),
	foreign key (IDCate) references category(IDCate)
)
go
drop table bill
go 

create table bill(
	IDBill nvarchar(10) primary key,
	CreatedDate date,
	Status nvarchar(20),
	IDUser nvarchar(10),
	foreign key (IDUser) references users(IDUser)
)
go
drop table BillDetail
go
create table BillDetail(
	IDBillDetail nvarchar(10) primary key,
	IDBill nvarchar(10),
	IDDrink nvarchar(10),
	Price decimal(10,2),
	Quantity int,
	Total as (Quantity * Price),
	foreign key (IDBill) references bill(IDBill),
	foreign key (IDDrink) references drink(IDDrink)
)
go

-- USERS
insert into users values
('CUS01','Nguyen Van A','123456','0901234567','a@gmail.com',1,'',1),
('CUS02','Tran Thi B','123456','0901234568','b@gmail.com',1,'',1),
('EMP01','Le Van C','123456','0901234569','c@gmail.com',2,'',1),
('EMP02','Pham Thi D','123456','0901234570','d@gmail.com',2,'',1),
('ADM01','Admin','admin123','0901234571','admin@gmail.com',3,'',1);
go

-- CATEGORY
insert into category values
('CAT01',N'Cà phê',N'Đồ uống'),
('CAT02',N'Trà sữa',N'Đồ uống'),
('CAT03',N'Trà trái cây',N'Đồ uống'),
('CAT04',N'Bánh ngọt',N'Đồ ăn');
go

-- DRINK
insert into drink values
('DR01',N'Cà phê đen',20000,'',1,N'Cà phê đen truyền thống','CAT01'),
('DR02',N'Cà phê sữa',25000,'',1,N'Cà phê pha sữa đặc','CAT01'),
('DR03',N'Bạc xỉu',30000,'',1,N'Nhiều sữa ít cà phê','CAT01'),
('DR04',N'Trà sữa trân châu',35000,'',1,N'Trà sữa truyền thống','CAT02'),
('DR05',N'Trà đào cam sả',40000,'',1,N'Trà đào thơm mát','CAT03'),
('DR06',N'Bánh tiramisu',45000,'',1,N'Bánh ngọt Ý','CAT04');
go

-- BILL
insert into bill values
('BILL01','2026-08-01',N'Đã thanh toán','CUS01'),
('BILL02','2026-08-01',N'Đã thanh toán','CUS02'),
('BILL03','2026-08-02',N'Đang xử lý','CUS01');
go

-- BILL DETAIL
insert into BillDetail(IDBillDetail,IDBill,IDDrink,Price,Quantity) values
('BD01','BILL01','DR01',20000,2),
('BD02','BILL01','DR06',45000,1),
('BD03','BILL02','DR04',35000,2),
('BD04','BILL02','DR05',40000,1),
('BD05','BILL03','DR02',25000,3);
go

insert into BillDetail values
('BD06','BILL03','DR01',20000,3)
go

select * from BillDetail

delete from BillDetail;
delete from Bill;
delete from Drink;
delete from Category;
delete from Users;
go

create or alter proc tongdoanhthungay @CreatedDate date
as begin
	select CreatedDate,Sum(Total) as TongDoanhThuNgay
	from bill b inner join BillDetail bd on b.IDBill=bd.IDBill
	where CreatedDate = @CreatedDate
	group by CreatedDate
end
go

exec tongdoanhthungay '2026-08-01'
go

create or alter proc tongdoanhthuthang @CreatedDate date
as begin
	select Sum(Total) as TongDoanhThuThang
	from bill b inner join BillDetail bd on b.IDBill=bd.IDBill
	where month(CreatedDate) = month(@CreatedDate) and year(CreatedDate) = year(@CreatedDate)
end
go

exec tongdoanhthuthang '2026-08-01'
go

create or alter proc mathangbantheongay @DrinkName nvarchar(50), @CreatedDate date
as begin
	select DrinkName, sum(Quantity) as SoLuongBan
	from Drink d inner join BillDetail bd on d.IDDrink=bd.IDDrink inner join bill b on b.IDBill=bd.IDBill
	where DrinkName = @DrinkName and CreatedDate = @CreatedDate
	group by DrinkName
end
go 

exec mathangbantheongay N'Cà phê đen','2026-08-02'
go

create or alter proc mathangbantheothang @DrinkName nvarchar(50), @CreatedDate date
as begin
	select DrinkName, sum(Quantity) as SoLuongBan
	from Drink d inner join BillDetail bd on d.IDDrink=bd.IDDrink inner join bill b on b.IDBill=bd.IDBill
	where DrinkName = @DrinkName and month(CreatedDate) = month(@CreatedDate) and year(CreatedDate) = year(@CreatedDate)
	group by DrinkName
end
go 

exec mathangbantheothang N'Cà phê đen','2026-08-02'
go


