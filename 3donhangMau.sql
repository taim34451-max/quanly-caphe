use polycoffee;
go

-- 1. xóa bớt các đơn rác thử nghiệm bị lỗi billdetail trước đó
delete from billdetail where billid in (select billid from bill where status = 'PENDING');
delete from bill where status = 'PENDING';
go

-- 2. tạo lại đơn 1 (bàn 06)
insert into bill (tablenumber, status, total, createddate, note)
values (N'Bàn 06', 'PENDING', 145000, getdate(), N'Giao gấp cho bàn VIP');

declare @id1 int = scope_identity();

insert into billdetail (billid, productid, quantity, price, note)
values 
(@id1, 1, 2, 65000, N'Size M - Cà Phê Sữa Đá - Ít đường'),
(@id1, 2, 1, 15000, N'Bánh Croissant');
go

-- 3. tạo lại đơn 2 (bàn 03)
insert into bill (tablenumber, status, total, createddate, note)
values (N'Bàn 03', 'PENDING', 314000, getdate(), N'Giao gấp cho bàn VIP');

declare @id2 int = scope_identity();

insert into billdetail (billid, productid, quantity, price, note)
values 
(@id2, 1, 2, 65000, N'Size L - Trà Xanh Đậu Đỏ - Ít đường'),
(@id2, 1, 2, 65000, N'Size L - Trà Thạch Vải - 30% Đường 70% Đá'),
(@id2, 2, 1, 35000, N'Bánh Tiramisu'),
(@id2, 2, 1, 19000, N'Bánh Mì Que Gà');
go

-- 4. kiểm tra xem billdetail đã có dữ liệu chưa
select b.billid, b.tablenumber, b.status, bd.productid, bd.quantity, bd.price, bd.note 
from bill b 
inner join billdetail bd on b.billid = bd.billid
where b.status = 'PENDING';
go