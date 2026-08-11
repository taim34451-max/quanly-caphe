USE master;
GO

-- =============================================
-- CREATE DATABASE
-- =============================================
CREATE DATABASE PolyCoffee;
GO

USE PolyCoffee;
GO


-- =============================================
-- TABLE: Users
-- =============================================
CREATE TABLE Users (
    UserId INT IDENTITY(1,1) PRIMARY KEY,
    UserName NVARCHAR(100) NOT NULL UNIQUE,
    Password NVARCHAR(100) NOT NULL DEFAULT '123456',
    UserPhone VARCHAR(15) NULL,
    Role VARCHAR(20) NULL DEFAULT 'BARISTA'
);
GO


-- =============================================
-- TABLE: Product
-- =============================================
CREATE TABLE Product (
    ProductId INT IDENTITY(1,1) PRIMARY KEY,
    ProductName NVARCHAR(100) NOT NULL,
    Category NVARCHAR(50) NULL,
    IsAvailable BIT NULL DEFAULT 1,
    ProductIMG NVARCHAR(50) NULL,
    DrinkDescription VARCHAR(255) NULL,
    Price NUMERIC(38,2) NULL
);
GO


-- =============================================
-- TABLE: SizePrice
-- =============================================
CREATE TABLE SizePrice (
    SizeId INT IDENTITY(1,1) PRIMARY KEY,
    ProductId INT NULL,
    SizeS NUMERIC(38,2) NULL,
    SizeM NUMERIC(38,2) NULL,
    SizeL NUMERIC(38,2) NULL,

    CONSTRAINT FK_SizePrice_Product
        FOREIGN KEY (ProductId)
        REFERENCES Product(ProductId)
);
GO


-- =============================================
-- TABLE: Bill
-- =============================================
CREATE TABLE Bill (
    BillId INT IDENTITY(1,1) PRIMARY KEY,
    UserId INT NULL,
    TableNumber VARCHAR(10) NOT NULL,
    Total DECIMAL(12,2) NOT NULL DEFAULT 0,
    Status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    CreatedDate DATETIME NOT NULL DEFAULT GETDATE(),
    Note NVARCHAR(255) NULL,

    CONSTRAINT FK_Bill_User
        FOREIGN KEY (UserId)
        REFERENCES Users(UserId)
);
GO


-- =============================================
-- TABLE: BillDetail
-- =============================================
CREATE TABLE BillDetail (
    DetailId INT IDENTITY(1,1) PRIMARY KEY,
    BillId INT NOT NULL,
    ProductId INT NOT NULL,
    Quantity INT NOT NULL DEFAULT 1,
    Price NUMERIC(12,2) NULL,
    Note NVARCHAR(255) NULL,

    CONSTRAINT FK_BillDetail_Bill
        FOREIGN KEY (BillId)
        REFERENCES Bill(BillId)
        ON DELETE CASCADE,

    CONSTRAINT FK_BillDetail_Product
        FOREIGN KEY (ProductId)
        REFERENCES Product(ProductId)
);
GO


-- =============================================
-- DATA: Users
-- =============================================
SET IDENTITY_INSERT Users ON;

INSERT INTO Users
    (UserId, UserName, Password, UserPhone, Role)
VALUES
    (1, N'admin', N'123456', N'0901234567', N'ADMIN'),
    (2, N'barista01', N'123456', N'0902345678', N'BARISTA'),
    (3, N'barista02', N'123456', N'0903456789', N'BARISTA'),
    (4, N'cashier01', N'123456', N'0904567890', N'USER'),
    (5, N'DuyAnh', N'123456', N'0000000000', N'USER');

SET IDENTITY_INSERT Users OFF;
GO


-- =============================================
-- DATA: Product
-- =============================================
SET IDENTITY_INSERT Product ON;

INSERT INTO Product
    (ProductId, ProductName, Category, IsAvailable, ProductIMG, DrinkDescription, Price)
VALUES
    (1, N'Phindi Hạnh Nhân', N'Cà phê', 1, N'1', NULL, NULL),
    (2, N'Cà Phê Sữa Đá', N'Cà phê', 1, N'2', NULL, NULL),
    (3, N'Phin Sữa Nóng', N'Cà phê', 1, N'3', NULL, NULL),
    (4, N'Phin Sữa Đá', N'Cà phê', 1, N'4', NULL, NULL),
    (5, N'Phin Đen Đá', N'Cà phê', 1, N'5', NULL, NULL),
    (6, N'Bạc Xỉu Đá', N'Cà phê', 1, N'6', NULL, NULL),
    (7, N'Trà Sen Vàng (Củ Năng)', N'Trà', 1, N'7', NULL, NULL),
    (8, N'Trà Thanh Đào', N'Trà', 1, N'8', NULL, NULL),
    (9, N'Trà Thạch Vải', N'Trà', 1, N'9', NULL, NULL),
    (10, N'Trà Xanh Đậu Đỏ', N'Trà', 1, N'10', NULL, NULL),
    (11, N'Freeze Trà Xanh', N'Freeze', 1, N'11', NULL, NULL),
    (12, N'Cookies & Cream', N'Freeze', 1, N'12', NULL, NULL),
    (13, N'Bánh Mì Que (Gà)', N'Bánh', 1, N'13', NULL, 19000.00),
    (14, N'Bánh Phô Mai Trà Xanh', N'Bánh', 1, N'14', NULL, 29000.00),
    (15, N'Bánh Tiramisu', N'Bánh', 1, N'15', NULL, 35000.00),
    (16, N'Bánh Chuối', N'Bánh', 1, N'16', NULL, 25000.00);

SET IDENTITY_INSERT Product OFF;
GO


-- =============================================
-- DATA: SizePrice
-- =============================================
SET IDENTITY_INSERT SizePrice ON;

INSERT INTO SizePrice
    (ProductId, SizeS, SizeM, SizeL, SizeId)
VALUES
    (1, 49000.00, 55000.00, 59000.00, 1),
    (2, 45000.00, 55000.00, 65000.00, 2),
    (3, 55000.00, 65000.00, 75000.00, 3),
    (4, 29000.00, 35000.00, 39000.00, 4),
    (5, 29000.00, 35000.00, 39000.00, 5),
    (6, 29000.00, 35000.00, 39000.00, 6),
    (7, 33000.00, 39000.00, 45000.00, 7),
    (8, 29000.00, 35000.00, 39000.00, 8),
    (13, NULL, NULL, NULL, 9),
    (14, NULL, NULL, NULL, 10),
    (15, NULL, NULL, NULL, 11),
    (16, NULL, NULL, NULL, 12),
    (9, 45000.00, 55000.00, 65000.00, 13),
    (10, 45000.00, 55000.00, 65000.00, 14),
    (11, 55000.00, 65000.00, 75000.00, 15),
    (12, 45000.00, 55000.00, 65000.00, 16);

SET IDENTITY_INSERT SizePrice OFF;
GO


-- =============================================
-- DATA: Bill
-- =============================================
SET IDENTITY_INSERT Bill ON;

INSERT INTO Bill
    (BillId, UserId, TableNumber, Total, Status, CreatedDate, Note)
VALUES
    (1, 2, '01', 114000.00, 'COMPLETED',
        '2026-08-08 05:12:46.083', N'Khách dùng tại quán'),

    (2, 3, '02', 100000.00, 'COMPLETED',
        '2026-08-08 06:12:46.083', NULL),

    (3, 2, '03', 130000.00, 'MAKING',
        '2026-08-08 07:42:46.083', N'Ít đá'),

    (4, 4, '04', 84000.00, 'PENDING',
        '2026-08-08 08:02:46.083', NULL),

    (5, 2, '05', 94000.00, 'COMPLETED',
        '2026-08-07 08:12:46.083', N'Mang đi');

SET IDENTITY_INSERT Bill OFF;
GO


-- =============================================
-- DATA: BillDetail
-- =============================================
SET IDENTITY_INSERT BillDetail ON;

INSERT INTO BillDetail
    (DetailId, BillId, ProductId, Quantity, Price, Note)
VALUES
    (1, 1, 1, 1, 55000.00, NULL),
    (2, 1, 5, 1, 35000.00, NULL),
    (3, 1, 11, 1, 35000.00, NULL),

    (4, 2, 2, 1, 55000.00, NULL),
    (5, 2, 10, 1, 19000.00, NULL),
    (6, 2, 6, 1, 29000.00, NULL),

    (7, 3, 3, 1, 65000.00, N'Ít đá'),
    (8, 3, 7, 1, 39000.00, NULL),
    (9, 3, 15, 1, 55000.00, NULL),

    (10, 4, 13, 1, 45000.00, NULL),
    (11, 4, 12, 1, 29000.00, NULL),

    (12, 5, 8, 1, 35000.00, NULL),
    (13, 5, 14, 1, 45000.00, NULL),
    (14, 5, 9, 1, 25000.00, NULL);

SET IDENTITY_INSERT BillDetail OFF;
GO