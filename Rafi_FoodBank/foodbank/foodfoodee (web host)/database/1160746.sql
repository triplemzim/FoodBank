-- phpMyAdmin SQL Dump
-- version 3.5.8
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 08, 2016 at 02:55 AM
-- Server version: 5.5.32
-- PHP Version: 5.4.17

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `1160746`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
  `customer_id` int(5) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `last_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `location_id` int(5) NOT NULL,
  `address` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`customer_id`),
  KEY `location_id` (`location_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=8 ;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`customer_id`, `first_name`, `last_name`, `username`, `password`, `location_id`, `address`) VALUES
(1, 'Rashid', 'Abid', 'rafi', 'rafi', 1, 'Titumir Hall,BUET'),
(2, 'Muhim', 'Muktadir', 'zim', 'zim', 3, 'Shahi Masjid, Lalbagh'),
(3, 'Samil', 'Salman', 'salman', 'salman', 5, '6A,Dhanmondi'),
(4, 'Sahed', 'Neloy', 'neloy', 'neloy', 2, 'Girls School, Azimpur'),
(5, 'Rayhan', 'Chowdhury', 'rc', 'rc', 1, 'Bakshibazar'),
(6, 'Rauful', 'Islam', 'shoron', 'shoron', 2, 'Lalbagh Kella'),
(7, 'Mottakin', 'Chowdhury', 'motta', 'motta', 4, 'New Market');

-- --------------------------------------------------------

--
-- Table structure for table `food`
--

CREATE TABLE IF NOT EXISTS `food` (
  `food_id` int(5) NOT NULL AUTO_INCREMENT,
  `food_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `hotel_id` int(6) NOT NULL,
  `menu_type` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `price` int(5) NOT NULL,
  PRIMARY KEY (`food_id`),
  KEY `hotel_id` (`hotel_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=38 ;

--
-- Dumping data for table `food`
--

INSERT INTO `food` (`food_id`, `food_name`, `hotel_id`, `menu_type`, `price`) VALUES
(1, 'Porota', 1, 'Breakfast', 10),
(2, 'Kacchi', 1, 'Lunch', 150),
(3, 'Plain Rice Package', 1, 'Dinner', 100),
(4, 'Beef Sizzling', 2, 'Lunch', 200),
(5, 'Fried Rice with Chicken', 2, 'Lunch', 160),
(7, 'Rice with Beef Vuna', 2, 'Dinner', 250),
(8, 'Kacchi', 3, 'Lunch', 150),
(9, 'Morog Polao', 3, 'Lunch', 140),
(10, 'Kacchi', 3, 'Dinner', 150),
(11, 'Morog Polao', 3, 'Dinner', 140),
(12, 'Beef Khichuri', 4, 'Lunch', 200),
(13, 'Chicken Khichuri', 4, 'Lunch', 150),
(14, 'Beef Khichuri', 4, 'Dinner', 200),
(15, 'Chicken Khichuri', 4, 'Dinner', 150),
(16, 'Platter1- Rice,Beef,Noodles', 5, 'Lunch', 1800),
(17, 'Platter2- Rice, Shrimp, Chicken,Noodles', 5, 'Lunch', 2000),
(18, 'Thai Soup with Onton', 5, 'Dinner', 400),
(19, 'Dinner Platter- Rice,Chicken,Beef,Prawn,Dessert', 5, 'Dinner', 3000),
(20, 'Pizza Barcelona', 6, 'Snacks', 900),
(21, 'Pizza Pepperoni', 6, 'Snacks', 1000),
(22, 'Beef Pasta', 6, 'Dinner', 700),
(23, 'Tawa Fry', 7, 'Snacks', 150),
(24, 'Rice with Curry', 7, 'Lunch', 200),
(25, 'Rice with Chicken Curry', 7, 'Dinner', 250),
(26, 'Chocolate Hot Cake', 8, 'Cakes', 200),
(27, 'Vanilla Cake', 8, 'Cakes', 250),
(28, 'Black Forest', 8, 'Cakes', 700),
(29, 'Thai Soup with Chicken Fry', 9, 'Dinner', 250),
(30, 'Fried Rice with Beef Steaks', 9, 'Dinner', 300),
(31, 'Fried Rice with Chicken Gravy', 9, 'Lunch', 300),
(32, 'Rice Package', 10, 'Lunch', 100),
(33, 'Fish Package', 10, 'Lunch', 200),
(34, 'Chicken Package', 10, 'Lunch', 200),
(35, 'Rice Package', 10, 'Dinner', 250),
(36, 'Chicken Package', 10, 'Dinner', 250),
(37, 'Beef Package', 10, 'Dinner', 300);

-- --------------------------------------------------------

--
-- Table structure for table `hotel`
--

CREATE TABLE IF NOT EXISTS `hotel` (
  `hotel_id` int(5) NOT NULL AUTO_INCREMENT,
  `hotel_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `location_id` int(5) NOT NULL,
  `hotel_address` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`hotel_id`),
  KEY `location_id` (`location_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=11 ;

--
-- Dumping data for table `hotel`
--

INSERT INTO `hotel` (`hotel_id`, `hotel_name`, `location_id`, `hotel_address`) VALUES
(1, 'Star Hotel', 5, '2/A, Dhanmondi'),
(2, 'Baburchi Restaurant', 5, 'Jhigatola, Dhanmondi'),
(3, 'Nanna Biriani', 1, 'Chankharpul'),
(4, 'Candle Light Chinese Restaurant', 1, 'Chankharpul'),
(5, 'Plate 21', 2, 'Shahi Mosjid, Lalbagh'),
(6, 'Pizza King', 2, 'Lalbagh Fort, Lalbagh'),
(7, 'Jannat Restaurant', 3, 'Girl''s School, Azimpur'),
(8, 'Sumi Hot Cake & Bakeries', 3, 'Index Plaza, Azimpur'),
(9, 'Midnight Sun Restaurant', 4, 'Bolaka Cinema Hall, Nilkhet'),
(10, 'Mama Hotel', 4, 'Newmarket Area');

-- --------------------------------------------------------

--
-- Table structure for table `location`
--

CREATE TABLE IF NOT EXISTS `location` (
  `location_id` int(5) NOT NULL,
  `region` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`location_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `location`
--

INSERT INTO `location` (`location_id`, `region`) VALUES
(1, 'Chankharpul'),
(2, 'Lalbagh'),
(3, 'Azimpur'),
(4, 'Nilkhet'),
(5, 'Dhanmondi');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE IF NOT EXISTS `transaction` (
  `transaction_id` int(5) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL,
  `customer_address` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `order_string` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `order_time` datetime NOT NULL,
  `total_price` int(10) NOT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `customer_id` (`customer_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=10 ;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`transaction_id`, `customer_id`, `customer_address`, `order_string`, `order_time`, `total_price`) VALUES
(4, 2, 'Shahi Masjid, Lalbagh', '17-18', '2016-06-08 08:10:05', 4400),
(5, 2, 'Shahi Masjid, Lalbagh', '34', '2016-06-08 08:25:36', 600),
(6, 2, 'Shahi Masjid, Lalbagh', '2-3', '2016-06-08 08:28:19', 135500),
(7, 1, 'Titumir Hall,BUET', '10-32', '2016-06-08 08:34:47', 950),
(8, 1, 'Titumir Hall,BUET', '30', '2016-06-08 08:35:40', 1500),
(9, 2, 'Shahi Masjid, Lalbagh', '1', '2016-06-08 09:12:00', 220);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `customer`
--
ALTER TABLE `customer`
  ADD CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`);

--
-- Constraints for table `food`
--
ALTER TABLE `food`
  ADD CONSTRAINT `food_ibfk_1` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `hotel`
--
ALTER TABLE `hotel`
  ADD CONSTRAINT `hotel_ibfk_1` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
