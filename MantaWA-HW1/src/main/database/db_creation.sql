DROP DATABASE IF EXISTS hoteldb;
-- Database Creation
CREATE DATABASE hoteldb OWNER hoteladmin ENCODING = 'UTF8';

-- Connect to hotel db to create data for it's 'public' schema
\c hoteldb hoteladmin

-- Generating uuid values requires a plug-in. In Postgres, a plug-in is 
-- known as an extension. To install an extension, call CREATE EXTENSION. To 
-- avoid re-installing, add IF NOT EXISTS. 
-- The extension for uuids is an open-source library built in C: uuid ossp.
-- When creating uuids, use function 'uuid_generate_v4' that generates an 
-- uuid deriving it entirely from random numbers. See: 
-- https://www.postgresql.org/docs/devel/uuid-ossp.html for details
 CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


-- Create new Domains
CREATE DOMAIN emailaddress AS VARCHAR(254)
	CONSTRAINT properemail CHECK ((( VALUE )::text~* '^[A-Za-z0-9._%]+@[A-Za-z0-9.]+[.][A-Za-z]+$'::text));

CREATE DOMAIN pwd AS VARCHAR(254)
	CONSTRAINT properpassword CHECK (((VALUE)::text~* '[A-Za-z0-9._%!?&]{8,}'::text));

CREATE DOMAIN phone_number AS TEXT CHECK ( VALUE ~ '^[0 -9]{10}$' );


-- Create new data types
CREATE TYPE roletype AS ENUM (
	'Hotel Manager', 
	'Front Office'
	);

CREATE TYPE paymentmethod AS ENUM (
	'Visa',
	'MasterCard',
	'Maestro',
	'American Express',
	'PayPal',
	'Cash'
);

CREATE TYPE category AS ENUM (
	'Single',
	'Double',
	'Triple',
	'Quad',
	'Twin',
	'Suite'
);



-- Table creation

-- User
CREATE TABLE HotelUser(
	Email EMAILADDRESS PRIMARY KEY,
 	Password PWD NOT NULL,
	Name VARCHAR(50) NOT NULL,
	Surname VARCHAR(50) NOT NULL,
	RoleName ROLETYPE NOT NULL
);


-- Log
CREATE TABLE Log(
	HotelUser EMAILADDRESS,
	Login TIMESTAMP,
	Logout TIMESTAMP,
	PRIMARY KEY (HotelUser, Login, Logout), 
	FOREIGN KEY (HotelUser) REFERENCES HotelUser(Email)
);

-- Payment
CREATE TABLE Payment(
    PaymentID UUID PRIMARY KEY,
	TotalAmount NUMERIC(8,2) NOT NULL CHECK (TotalAmount>0),
	Method PAYMENTMETHOD NOT NULL,
	Date DATE NOT NULL,
	Complete BOOLEAN NOT NULL,
	HotelUser EMAILADDRESS,
	FOREIGN KEY (HotelUser) REFERENCES HotelUser(Email)
);

-- Customer
CREATE TABLE Customer(
	PersonID UUID PRIMARY KEY,
	Name VARCHAR(50) NOT NULL,	
	Surname VARCHAR(50) NOT NULL,
	BirthDate DATE NOT NULL,
	DocumentNumber TEXT,
	PhoneNumber PHONE_NUMBER,
	Email EMAILADDRESS,
	Password PWD,
	Address TEXT,
	RegistrationDate DATE NOT NULL,
	HotelUser EMAILADDRESS,
	FOREIGN KEY (HotelUser) REFERENCES HotelUser(Email)
);

-- Booking
CREATE TABLE Booking(
	BookingID UUID PRIMARY KEY,
	PersonID UUID NOT NULL,
	Checkin TIMESTAMP NOT NULL, 
	Checkout TIMESTAMP NOT NULL,
	-- PaymentID SERIAL NOT NULL,
    PaymentID UUID NOT NULL,
	Date DATE NOT NULL,
	Requests TEXT,
	FOREIGN KEY (PersonID) REFERENCES Customer(PersonID),
	FOREIGN KEY (PaymentID) REFERENCES Payment(PaymentID)
);

-- Room
CREATE TABLE Room(
	RoomNumber TEXT PRIMARY KEY,
	Beds INT NOT NULL,
	Price NUMERIC(8,2) NOT NULL CHECK (Price > 0),
	RoomType CATEGORY NOT NULL,
	Description TEXT NOT NULL
);

-- Staying
CREATE TABLE Staying( 
	BookingID UUID,
	RoomNumber TEXT,
	PersonID UUID,
	PRIMARY KEY (BookingID, RoomNumber, PersonID),
	FOREIGN KEY (BookingID) REFERENCES Booking(BookingID),
	FOREIGN KEY (RoomNumber) REFERENCES Room(RoomNumber),
	FOREIGN KEY (PersonID) REFERENCES Customer(PersonID)
);