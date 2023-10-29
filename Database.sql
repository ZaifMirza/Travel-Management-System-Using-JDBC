-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 29, 2023 at 08:20 AM
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
-- Database: `java-individual`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `booking_id` int(100) NOT NULL,
  `route_id` int(100) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `phone_number` bigint(10) NOT NULL,
  `vehicle_id` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`booking_id`, `route_id`, `first_name`, `last_name`, `phone_number`, `vehicle_id`) VALUES
(102, 4, 'ayan', 'mirza', 1234567878, 11),
(105, 4, 'sad', 'jhg', 7891475623, 15),
(110, 5, 'sfgs', 'ghdshd', 1234678945, 17),
(127, 2, 'zaif', 'mirza', 1234567890, 17),
(134, 5, 'satyam', 'yadav', 1592634872, 30),
(145, 1, 'hamza', 'payawala', 4567891533, 29),
(160, 10, 'das', 'dsad', 1234567891, 48),
(181, 5, 'abc', 'cba', 1234567890, 1);

-- --------------------------------------------------------

--
-- Table structure for table `routes`
--

CREATE TABLE `routes` (
  `route_id` int(11) NOT NULL,
  `route_name` varchar(50) NOT NULL,
  `start_location` varchar(50) NOT NULL,
  `end_location` varchar(50) NOT NULL,
  `distance` int(11) NOT NULL,
  `fare` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `routes`
--

INSERT INTO `routes` (`route_id`, `route_name`, `start_location`, `end_location`, `distance`, `fare`) VALUES
(1, 'Ahmedabad to Vadodara', 'Ahmedabad', 'Vadodara', 110, 85),
(2, 'Surat to Rajkot', 'Surat', 'Rajkot', 440, 250),
(3, 'Gandhinagar to Junagadh', 'Gandhinagar', 'Junagadh', 340, 200),
(4, 'Bhavnagar to jamnagar', 'Bhavnagar', 'jamnagar', 240, 200),
(5, 'Anand to Bhuj', 'Anand', 'Bhuj', 400, 230),
(6, 'Mehsana to Porbandar', 'Mehsana', 'Porbandar', 430, 250),
(7, 'Palanpur to Navsari', 'Palanpur', 'Navsari', 430, 250),
(8, 'Valsad to Amreli', 'Valsad', 'Amreli', 520, 450),
(9, 'Nadiad to Surendranagar', 'Nadiad', 'Surendranagar', 140, 100),
(10, 'Patan to Bhuj', 'Patan', 'Bhuj', 310, 170);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`booking_id`);

--
-- Indexes for table `routes`
--
ALTER TABLE `routes`
  ADD PRIMARY KEY (`route_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `booking_id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=186;

--
-- AUTO_INCREMENT for table `routes`
--
ALTER TABLE `routes`
  MODIFY `route_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
