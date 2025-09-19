IF DB_ID(N'Registration') IS NULL
    CREATE DATABASE [Registration];
GO

USE [Registration];
GO

IF OBJECT_ID(N'dbo.branch_cases', N'U') IS NOT NULL
    DROP TABLE dbo.branch_cases;
GO

CREATE TABLE dbo.branch_cases (
    id            INT IDENTITY(1,1) PRIMARY KEY,
    city          NVARCHAR(100)  NOT NULL,
    street_name   NVARCHAR(200)  NOT NULL,
    street_number NVARCHAR(50)   NULL
);
GO

INSERT INTO dbo.branch_cases (city, street_name, street_number) VALUES
    (N'თბილისი', N'მარჯანიშვილის', N'7'),
    (N'თბილისი', N'ცინცაძის',      N'12'),
    (N'თბილისი', N'პეკინის',       N'2'),
    (N'თბილისი', N'ჩიტაიას',       N'31');
GO



