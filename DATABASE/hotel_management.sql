-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Feb 20, 2025 at 12:56 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotel_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `bookingID` int(11) NOT NULL,
  `costumerName` varchar(255) NOT NULL,
  `roomID` int(11) NOT NULL,
  `checkIn` date NOT NULL,
  `checkOut` date NOT NULL,
  `status` varchar(255) NOT NULL,
  `total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`bookingID`, `costumerName`, `roomID`, `checkIn`, `checkOut`, `status`, `total`) VALUES
(1, 'Aldrie Baquiran', 2, '2025-02-15', '2025-02-17', 'Cancelled', 400),
(2, 'Aldrie Baquiran', 2, '2025-02-17', '2025-02-18', 'Cancelled', 200),
(3, 'Aldrie Baquiran', 3, '2025-02-15', '2025-02-17', 'Complete', 600),
(4, 'Default Name', 6, '2025-02-18', '2025-02-19', 'Complete', 100),
(5, 'Default Name', 5, '2025-02-18', '2025-02-21', 'Complete', 600),
(6, 'Default Name', 4, '2025-02-19', '2025-02-22', 'Complete', 1500),
(7, 'Default Name', 6, '2025-02-16', '2025-02-18', 'Complete', 200),
(8, 'Default Name', 5, '2025-02-12', '2025-02-20', 'Cancelled', 1600),
(9, 'Aldrie Baquiran', 5, '2025-02-16', '2025-02-18', 'Complete', 400),
(10, 'Zeth Pagkaliwagan', 4, '2025-02-16', '2025-02-18', 'Complete', 1000),
(11, 'Clarisse Ganda', 6, '2025-02-17', '2025-02-22', 'Cancelled', 500),
(12, 'Clarisse Ganda', 6, '2025-02-18', '2025-02-20', 'Complete', 200),
(13, 'Clarisse Ganda', 3, '2025-02-18', '2025-02-20', 'Confirm', 600),
(14, 'Zeth Pagkaliwagan', 5, '2025-02-18', '2025-02-27', 'Confirm', 1800),
(15, 'Zeth Pagkaliwagan', 4, '2025-02-18', '2025-02-19', 'Complete', 500),
(16, 'Aldrie Baquiran', 6, '2025-02-18', '2025-02-19', 'Check In', 100),
(17, 'Yeoj Valdez', 9, '2025-02-19', '2025-02-21', 'Check In', 600),
(18, 'Yeoj Valdez', 12, '2025-02-19', '2025-02-20', 'Confirm', 600),
(19, 'Aldrie Baquiran', 11, '2025-03-17', '2025-03-20', 'Confirm', 1500),
(20, 'Ryan Paulo', 4, '2025-02-18', '2025-02-20', 'Confirm', 1000),
(21, 'Ryan Paulo', 2, '2025-02-19', '2025-02-21', 'Check In', 400),
(22, 'Aldrie Baquiran', 7, '2025-02-19', '2025-02-20', 'Cancelled', 100),
(23, 'John Boiser', 7, '2025-02-20', '2025-02-21', 'Check In', 100);

-- --------------------------------------------------------

--
-- Table structure for table `costumer`
--

CREATE TABLE `costumer` (
  `costumerID` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `contact` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `costumer`
--

INSERT INTO `costumer` (`costumerID`, `name`, `contact`, `email`, `password`) VALUES
(1, 'Admin', 'admin', 'admin', 'admin'),
(2, 'Aldrie Baquiran', '0906703738', 'aldrie', 'aldrie15'),
(3, 'Zeth Pagkaliwagan', '0972672628', 'zeth', 'zeth123'),
(4, 'Clarisse Ganda', '09048927992', 'clarisse', 'clarisse123'),
(5, 'Yeoj Valdez', ' 09754790765', 'yeoj', 'yeoj123'),
(6, 'Ryan Paulo', '09738012829', 'ryan', 'ryan123'),
(7, 'John Boiser', '09738827878', 'boiser', 'boiser123');

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `roomID` int(11) NOT NULL,
  `roomType` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `availability` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`roomID`, `roomType`, `price`, `availability`) VALUES
(1, 'Single', 100, 'Under Maintenance'),
(2, 'Double', 200, 'Booked'),
(3, 'Triple', 300, 'Booked'),
(4, 'Suite', 500, 'Booked'),
(5, 'Double', 200, 'Booked'),
(6, 'Double', 100, 'Booked'),
(7, 'Single', 100, 'Booked'),
(9, 'Triple', 300, 'Booked'),
(10, 'Single', 100, 'Available'),
(11, 'Suite', 500, 'Booked'),
(12, 'Suite', 600, 'Booked'),
(13, 'Triple', 300, 'Available'),
(14, 'Double', 200, 'Available'),
(15, 'Suite', 500, 'Available'),
(16, 'Suite', 500, 'Available');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`bookingID`),
  ADD KEY `roomID` (`roomID`);

--
-- Indexes for table `costumer`
--
ALTER TABLE `costumer`
  ADD PRIMARY KEY (`costumerID`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`roomID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `bookingID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `costumer`
--
ALTER TABLE `costumer`
  MODIFY `costumerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `room`
--
ALTER TABLE `room`
  MODIFY `roomID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `booking`
--
ALTER TABLE `booking`
  ADD CONSTRAINT `booking_ibfk_1` FOREIGN KEY (`roomID`) REFERENCES `room` (`roomID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
