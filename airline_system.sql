/*
MySQL Backup
Database: airlinesystem
Backup Time: 2023-03-01 00:36:25
*/

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `airlinesystem`.`account`;
DROP TABLE IF EXISTS `airlinesystem`.`inventory`;
DROP TABLE IF EXISTS `airlinesystem`.`ticket`;
CREATE TABLE `account` (
  `Acc_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Email` varchar(24) NOT NULL,
  `Pass` varchar(24) NOT NULL,
  `FName` varchar(24) NOT NULL,
  `MName` varchar(24) NOT NULL,
  `LName` varchar(24) NOT NULL,
  `DOB` date DEFAULT NULL,
  `Mobile_No` varchar(16) NOT NULL,
  `CreatedOn` date DEFAULT NULL,
  `LastLogin` date DEFAULT NULL,
  `IsActive` int(11) DEFAULT NULL,
  PRIMARY KEY (`Acc_ID`) USING BTREE,
  UNIQUE KEY `email` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
CREATE TABLE `inventory` (
  `Inventory_ID` int(11) DEFAULT NULL,
  `Account_ID` int(11) DEFAULT NULL,
  `Ticket_ID` int(11) DEFAULT NULL,
  KEY `FK_ACCID` (`Account_ID`),
  KEY `FK_TKTID` (`Ticket_ID`),
  CONSTRAINT `FK_ACCID` FOREIGN KEY (`Account_ID`) REFERENCES `account` (`Acc_ID`),
  CONSTRAINT `FK_TKTID` FOREIGN KEY (`Ticket_ID`) REFERENCES `ticket` (`Ticket_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
CREATE TABLE `ticket` (
  `Ticket_ID` int(11) NOT NULL,
  `FlightCode` varchar(5) DEFAULT NULL,
  `IssueDate` date DEFAULT NULL,
  `ExpireDate` date DEFAULT NULL,
  `Airline_Name` varchar(24) DEFAULT NULL,
  `Seat_No` varchar(6) DEFAULT NULL,
  `FromWhere` varchar(60) DEFAULT NULL,
  `FromCode` varchar(4) DEFAULT NULL,
  `ToWhere` varchar(60) DEFAULT NULL,
  `ToCode` varchar(4) DEFAULT NULL,
  `DepartureTime` varchar(8) DEFAULT NULL,
  `DepartureDate` date DEFAULT NULL,
  `ReachingTime` varchar(8) DEFAULT NULL,
  `ReachingDate` date DEFAULT NULL,
  `Gate` varchar(3) DEFAULT NULL,
  `Terminal` varchar(3) DEFAULT NULL,
  `Price` int(11) DEFAULT NULL,
  `DiscountPrice` int(11) DEFAULT NULL,
  `isBought` int(11) DEFAULT NULL,
  PRIMARY KEY (`Ticket_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
BEGIN;
LOCK TABLES `airlinesystem`.`account` WRITE;
DELETE FROM `airlinesystem`.`account`;
INSERT INTO `airlinesystem`.`account` (`Acc_ID`,`Email`,`Pass`,`FName`,`MName`,`LName`,`DOB`,`Mobile_No`,`CreatedOn`,`LastLogin`,`IsActive`) VALUES (1, 'abc@xyz.com', 'abc123', 'John', '', 'Smith', '2000-02-08', '+8801880056404', '2023-01-10', '2023-01-15', 0),(2, 'xyz@abc.com', 'xyz123', 'alaa', 'maz', 'mett', '2000-02-08', '+123123123121', '2023-01-12', '2023-01-13', 0),(3, 'abc@abc.com', '123123', 'aaa', '', '213123', '2000-02-08', '+8801317272697', '2023-01-13', NULL, NULL);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `airlinesystem`.`inventory` WRITE;
DELETE FROM `airlinesystem`.`inventory`;
INSERT INTO `airlinesystem`.`inventory` (`Inventory_ID`,`Account_ID`,`Ticket_ID`) VALUES (1, 1, 10001);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `airlinesystem`.`ticket` WRITE;
DELETE FROM `airlinesystem`.`ticket`;
INSERT INTO `airlinesystem`.`ticket` (`Ticket_ID`,`FlightCode`,`IssueDate`,`ExpireDate`,`Airline_Name`,`Seat_No`,`FromWhere`,`FromCode`,`ToWhere`,`ToCode`,`DepartureTime`,`DepartureDate`,`ReachingTime`,`ReachingDate`,`Gate`,`Terminal`,`Price`,`DiscountPrice`,`isBought`) VALUES (10001, 'ABC', '2023-01-12', '2023-01-31', 'US_Flight', 'K-12', 'USA', 'US1', 'Egypt', 'EG7', '9:30-PM', '2023-01-16', '2:00-AM', '2023-01-17', 'A2', '1', 750, 699, 1),(10002, 'XYZ', '2023-01-12', '2023-01-31', 'BD_Flight', 'F-10', 'Bangladesh', 'BD8', 'Singapore', 'SP3', '7:30-PM', '2023-01-15', '10:00-PM', '2023-01-15', 'C3', '2', 500, 460, 0),(10003, 'XYZ', '2023-01-12', '2023-01-31', 'NG_Flight', 'F-13', 'Bangladesh', 'BD5', 'Nigeria', 'NG3', '7:30-PM', '2023-01-15', '11:00-PM', '2023-01-15', 'C3', '2', 700, 560, 0),(10004, 'XYZ', '2023-01-12', '2023-01-31', 'BD_Flight', 'L-13', 'Bangladesh', 'BD5', 'Nigeria', 'NG3', '9:30-PM', '2023-01-19', '01:00-AM', '2023-01-20', 'B1', '1', 700, 660, 0),(10005, 'ABC', '2023-01-12', '2023-01-31', 'US_Flight', 'H-12', 'USA', 'US1', 'Bangladesh', 'BD7', '9:30-PM', '2023-01-16', '2:00-AM', '2023-01-17', 'A2', '1', 750, 699, 0);
UNLOCK TABLES;
COMMIT;
