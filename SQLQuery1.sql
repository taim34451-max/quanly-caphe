create database PolyCoffee
use PolyCoffee;
use test;
drop database PolyCoffee; 


CREATE TABLE Users (
    UserId INT IDENTITY(1,1) PRIMARY KEY,
    UserName NVARCHAR(100) NOT NULL UNIQUE,
    Password NVARCHAR(100) NOT NULL DEFAULT '123456', -- Cột Password đã được thêm chuẩn
    UserPhone VARCHAR(15) NULL,
    Role VARCHAR(20) DEFAULT 'BARISTA'
);
GO

-- 4. TẠO LẠI BẢNG Product (Giá tiền chuẩn VNĐ DECIMAL 12,2)
CREATE TABLE Product (
    ProductId INT IDENTITY(1,1) PRIMARY KEY,
    ProductName NVARCHAR(100) NOT NULL,
    Category NVARCHAR(50) NULL,
    IsAvailable BIT DEFAULT 1,
    ProductIMG nvarchar(50)
);  GO


Create table SizePrice(
    ProductId int foreign key references Product(ProductId),
    SizeS decimal(12,2),
    SizeM decimal(12,2),
    SizeL decimal(12,2)
);



-- 5. TẠO LẠI BẢNG Bill (Có DATETIME chính xác giờ:phút:giây và cột Note)
CREATE TABLE Bill (
    BillId INT IDENTITY(1,1) PRIMARY KEY,
    UserId INT NULL FOREIGN KEY REFERENCES Users(UserId),
    TableNumber VARCHAR(10) NOT NULL,
    Total DECIMAL(12,2) NOT NULL DEFAULT 0,
    Status VARCHAR(20) NOT NULL DEFAULT 'PENDING', -- PENDING, MAKING, COMPLETED, CANCELLED
    CreatedDate DATETIME NOT NULL DEFAULT GETDATE(),
    Note NVARCHAR(255) NULL
);
GO

-- 6. TẠO LẠI BẢNG BillDetail (Ghi chú chi tiết món)
CREATE TABLE BillDetail -- CART TEMPORARY 
    (
    DetailId INT IDENTITY(1,1) PRIMARY KEY,
    BillId INT NOT NULL FOREIGN KEY REFERENCES Bill(BillId) ON DELETE CASCADE,
    ProductId INT NOT NULL FOREIGN KEY REFERENCES Product(ProductId),
    Quantity INT NOT NULL DEFAULT 1,
    Price DECIMAL(12,2) NOT NULL,
    Note NVARCHAR(255) NULL
);
GO



INSERT INTO Product
    (ProductName, Category, IsAvailable, ProductIMG)
VALUES
-- Cà phê
(N'Phindi Hạnh Nhân', N'Cà phê', 1, N'PHINDI_HANH_NHAN.jpg'),
(N'Trà Sen Vàng', N'Trà', 1, N'tsv_cu_nang.jpg'),
(N'Freeze Trà Xanh', N'Freeze', 1, N'freeze_tra_xanh.jpg'),
(N'Cà Phê Sữa Đá', N'Cà phê', 1, N'PHIN_SUA_DA.jpg'),
(N'Phin Sữa Đá', N'Cà phê', 1, N'phin_sua_da.jpg'),
(N'Phin Đen Đá', N'Cà phê', 1, N'phin_den_da.jpg'),
(N'Bạc Xỉu Đá', N'Cà phê', 1, N'bac_siu_1.jpg'),
(N'Phin Sữa Nóng', N'Cà phê', 1, N'PHIN_SUA_NONG.jpg'),

-- Bánh
(N'Bánh Phô Mai Trà Xanh', N'Bánh', 1, N'banh_pho_mai_tra_xanh.jpg'),
(N'Bánh Mì Que Gà', N'Bánh', 1, N'bmq_ga.jpg'),
(N'Bánh Tiramisu', N'Bánh', 1, N'BANH_TIRAMISU.jpg'),
(N'Bánh Chuối', N'Bánh', 1, N'BANH_CHUOI.jpg'),

-- Trà
(N'Trà Thanh Đào', N'Trà', 1, N'tra_thanh_dao.jpg'),
(N'Trà Thạch Vải', N'Trà', 1, N'TRA_THACH_VAI_1.jpg'),

-- Freeze
(N'Cookies & Cream', N'Freeze', 1, N'FREEZE_COOKIES.jpg'),

-- Trà
(N'Trà Xanh Đậu Đỏ', N'Trà', 1, N'TRA_XANH_DAU_DO.jpg');
GO


-- =====================================================
-- INSERT SIZE PRICE
-- Các món có S/M/L
-- =====================================================

INSERT INTO SizePrice (ProductId, SizeS, SizeM, SizeL)
VALUES

-- 1. Phindi Hạnh Nhân
(1, 49000, 55000, 59000),

-- 2. Trà Sen Vàng
(2, 45000, 55000, 65000),

-- 3. Freeze Trà Xanh
(3, 55000, 65000, 75000),

-- 4. Cà Phê Sữa Đá
(4, 29000, 35000, 39000),

-- 5. Phin Sữa Đá
(5, 29000, 35000, 39000),

-- 6. Phin Đen Đá
(6, 29000, 35000, 39000),

-- 7. Bạc Xỉu Đá
(7, 33000, 39000, 45000),

-- 8. Phin Sữa Nóng
(8, 29000, 35000, 39000),

-- 13. Trà Thanh Đào
(13, 45000, 55000, 65000),

-- 14. Trà Thạch Vải
(14, 45000, 55000, 65000),

-- 15. Cookies & Cream
(15, 55000, 65000, 75000),

-- 16. Trà Xanh Đậu Đỏ
(16, 45000, 55000, 65000);
GO


-- =====================================================
-- CÁC MÓN BÁNH
-- JS chỉ có price -> chỉ lưu giá vào SizeS
-- =====================================================

INSERT INTO SizePrice (ProductId, SizeS, SizeM, SizeL)
VALUES
-- 9. Bánh Phô Mai Trà Xanh
(9, 25000, NULL, NULL),

-- 10. Bánh Mì Que Gà
(10, 19000, NULL, NULL),

-- 11. Bánh Tiramisu
(11, 35000, 35000, 35000),

-- 12. Bánh Chuối
(12, 29000, Null, NULL);
GO


USE PolyCoffee;
GO

-- =========================================
-- 1. USERS
-- =========================================

INSERT INTO Users (UserName, Password, UserPhone, Role)
VALUES
(N'admin', N'123456', '0901234567', 'ADMIN'),
(N'barista01', N'123456', '0902345678', 'BARISTA'),
(N'barista02', N'123456', '0903456789', 'BARISTA'),
(N'cashier01', N'123456', '0904567890', 'CASHIER');

GO


-- =========================================
-- 2. BILL
-- =========================================

INSERT INTO Bill
    (UserId, TableNumber, Total, Status, CreatedDate, Note)
VALUES

-- Bill 1
(2, '01', 114000, 'COMPLETED',
 DATEADD(HOUR, -3, GETDATE()),
 N'Khách dùng tại quán'),

-- Bill 2
(3, '02', 100000, 'COMPLETED',
 DATEADD(HOUR, -2, GETDATE()),
 NULL),

-- Bill 3
(2, '03', 130000, 'MAKING',
 DATEADD(MINUTE, -30, GETDATE()),
 N'Ít đá'),

-- Bill 4
(4, '04', 84000, 'PENDING',
 DATEADD(MINUTE, -10, GETDATE()),
 NULL),

-- Bill 5
(2, '05', 94000, 'COMPLETED',
 DATEADD(DAY, -1, GETDATE()),
 N'Mang đi');

GO


-- =========================================
-- 3. BILL DETAIL
-- =========================================

-- Bill 1
-- Phindi Hạnh Nhân M: 55k x 1
-- Phin Sữa Đá M: 35k x 1
-- Bánh Tiramisu: 35k x 1
-- Tổng thực tế = 125k
-- =========================================

INSERT INTO BillDetail
    (BillId, ProductId, Quantity, Price, Note)
VALUES
(1, 1, 1, 55000, NULL),
(1, 5, 1, 35000, NULL),
(1, 11, 1, 35000, NULL);


-- Bill 2
-- Trà Sen Vàng M: 55k x 1
-- Bánh Mì Que Gà: 19k x 1
-- Phin Đen Đá S: 29k x 1
-- Tổng = 103k
-- =========================================

INSERT INTO BillDetail
    (BillId, ProductId, Quantity, Price, Note)
VALUES
(2, 2, 1, 55000, NULL),
(2, 10, 1, 19000, NULL),
(2, 6, 1, 29000, NULL);


-- Bill 3
-- Freeze Trà Xanh M: 65k x 1
-- Bạc Xỉu Đá M: 39k x 1
-- Cookies & Cream S: 55k x 1
-- Tổng = 159k
-- =========================================

INSERT INTO BillDetail
    (BillId, ProductId, Quantity, Price, Note)
VALUES
(3, 3, 1, 65000, N'Ít đá'),
(3, 7, 1, 39000, NULL),
(3, 15, 1, 55000, NULL);


-- Bill 4
-- Trà Thanh Đào S: 45k
-- Bánh Chuối: 29k
-- Tổng = 74k
-- =========================================

INSERT INTO BillDetail
    (BillId, ProductId, Quantity, Price, Note)
VALUES
(4, 13, 1, 45000, NULL),
(4, 12, 1, 29000, NULL);


-- Bill 5
-- Phin Sữa Nóng M: 35k
-- Trà Thạch Vải S: 45k
-- Bánh Phô Mai Trà Xanh: 25k
-- Tổng = 105k
-- =========================================

INSERT INTO BillDetail
    (BillId, ProductId, Quantity, Price, Note)
VALUES
(5, 8, 1, 35000, NULL),
(5, 14, 1, 45000, NULL),
(5, 9, 1, 25000, NULL);

GO
