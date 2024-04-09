-- Create a new database
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'accounts_db')
    BEGIN
        CREATE DATABASE accounts_db;
    END;
GO

-- Switch context to the new database
USE accounts_db;
GO


-- Create a new schema in the database
IF NOT EXISTS (SELECT * FROM sys.schemas WHERE name = 'account_schema')
    BEGIN
        EXEC('CREATE SCHEMA account_schema');
    END;
GO