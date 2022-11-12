--Insert operations

--User
INSERT INTO HotelUser(Email,Password,Name,Surname,RoleName) VALUES
('manager@hotelmanta.com','Qhuy%E!Y', 'Steven', 'Wade', 'Hotel Manager'),
('sophieB@hotelmanta.com', 'Yr%HuGW?', 'Sophie', 'Barker', 'Front Office'),
('alexkendall@hotelmanta.com', 'Y_KlsuGI!', 'Alex', 'Kendall', 'Front Office');


--Log
INSERT INTO Log(HotelUser,Login,Logout) VALUES
('manager@hotelmanta.com', '2020-01-03 07:00:00', '2020-01-03 13:30:00'),
('manager@hotelmanta.com', '2020-01-03 15:20:00', '2020-01-04 00:30:00'),
('sophieB@hotelmanta.com', '2020-01-03 07:30:00', '2020-01-03 12:20:00'),
('alexkendall@hotelmanta.com', '2020-01-03 13:40:00', '2020-01-03 23:15:00');


--Custumer
INSERT INTO Customer(PersonID, Name, Surname, BirthDate, DocumentNumber, PhoneNumber, Email, Password, Address, RegistrationDate, HotelUser) VALUES
('5fcf65d6-d684-468c-8b12-ae35ce9f5989','Martin','Peterson','1990-04-08','447981887','1617555016', 'martinPeterson45@gmail.com', 'Yr%LsGW?', '4180 Gerald L. Bates Drive, Boston','2021-07-10', NULL),
('81eac059-cfaf-44d4-b844-a504c6441764','Irma','Santos','1989-10-05', '585623008','1808200239', 'santsoIr39@gmail.com', 'St%HuLW&','2792 Arron Smith Drive, Honolulu','2019-06-07', NULL),
('99143c37-335a-4cfa-8178-5efc1caadd13','Lindsay','Johnson', '2008-2-28', NULL, '6384289327', 'linson@gmail.com', 'PO%OpG&W', NULL, '2018-03-30', 'sophieB@hotelmanta.com'),
('9446ae13-7f29-461a-8574-f50f736d1d3c','Terry','Johnson', '1990-08-13', '480924587', '1289529239', 'terryjhonson@gmail.com', 'GW%DfO?E', '60431 Mueller Terrace, New York', '2018-04-05', 'alexkendall@hotelmanta.com');


--Payment
INSERT INTO Payment(PaymentID, TotalAmount, Method, Date, Complete, HotelUser) VALUES
('35215188-a7af-4e6e-976b-3c90cf0e06c8','250.00','American Express','2021-07-10','YES','sophieB@hotelmanta.com'),
('1e0765ad-cbab-4647-b18f-c4de9257dd69','672.00','Visa','2019-06-07','YES','alexkendall@hotelmanta.com'),
('e9727d23-9ba7-495f-9eb7-305f4f4df9a2','595.00','Cash','2018-04-05','NO','sophieB@hotelmanta.com'),
('d5f7aa64-d23a-4941-9f21-b7bd655df510','420.00','American Express','2021-06-19','YES','sophieB@hotelmanta.com');


--Booking
INSERT INTO Booking(BookingID, PersonID, Checkin, Checkout, PaymentID, Date, Requests) VALUES
('00667ffc-5b56-11ec-8384-db025eed1a6d','5fcf65d6-d684-468c-8b12-ae35ce9f5989','2021-08-03 14:00:00','2021-08-07 10:00:00','35215188-a7af-4e6e-976b-3c90cf0e06c8','2021-07-10','Lunch and dinner included'),
('036499e6-5b56-11ec-966b-c7e7f193ea0c','81eac059-cfaf-44d4-b844-a504c6441764','2019-06-20 14:00:00','2019-06-26 10:00:00','1e0765ad-cbab-4647-b18f-c4de9257dd69','2019-06-07','Later check-out, if possible'),
('a8bfa3c2-cce6-4514-ad47-36c96504f19a','9446ae13-7f29-461a-8574-f50f736d1d3c','2018-04-05 14:00:00','2018-04-11 10:00:00','e9727d23-9ba7-495f-9eb7-305f4f4df9a2','2018-03-30', NULL),
('465e6a0b-4118-4fac-a76d-ec95fa9f4cdc','5fcf65d6-d684-468c-8b12-ae35ce9f5989','2021-06-17 14:00:00','2021-06-19 10:00:00','d5f7aa64-d23a-4941-9f21-b7bd655df510','2021-04-20', NULL);


--Room
INSERT INTO Room(RoomNumber, Beds, Price, RoomType, Description) VALUES
('A100', '1', '50.00', 'Single', 'A single room has one single bed for single occupancy.'),
('A107', '2', '90.00', 'Double', 'A double room has one double bed for double occupancy.'),
('A146', '1', '96.00', 'Twin', 'A twin room has two single beds for double occupancy.'),
('A136', '1', '85.00', 'Double', 'A double room has one double bed for double occupancy.'),
('A120', '2', '70.00', 'Double', 'A double room has one double bed for double occupancy.'),
('A110', '3', '100.00', 'Triple', 'A triple room has three separate single beds and can be occupied by 3 guests.'),
('A111', '4', '150.00', 'Quad', 'A quad room has 4 separate single beds and can accommodate 4 persons together in the same room.'),
('A112', '2', '200.00', 'Suite', 'A suite room is comprised of more than 1 room.');


--Staying
INSERT INTO Staying(BookingID, RoomNumber, PersonID) VALUES 
('00667ffc-5b56-11ec-8384-db025eed1a6d', 'A100', '5fcf65d6-d684-468c-8b12-ae35ce9f5989'),
('036499e6-5b56-11ec-966b-c7e7f193ea0c', 'A146', '81eac059-cfaf-44d4-b844-a504c6441764'),
('465e6a0b-4118-4fac-a76d-ec95fa9f4cdc', 'A120', '5fcf65d6-d684-468c-8b12-ae35ce9f5989'),
('a8bfa3c2-cce6-4514-ad47-36c96504f19a', 'A107', '9446ae13-7f29-461a-8574-f50f736d1d3c');