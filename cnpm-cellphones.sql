-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: Mar 06, 2021 at 07:47 AM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cnpm-cellphones`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `full_name` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `type` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `address` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `phone` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `email` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `id` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `full_name` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `birth_day` date DEFAULT NULL,
  `phone` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `email` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `password` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`id`, `full_name`, `birth_day`, `phone`, `email`, `password`, `created_date`, `updated_date`) VALUES
('dfa67e1a-96e9-4186-8c53-3769e8a84384', 'huy', '2000-06-24', '0979487154', 'huy@gmail.com', 'huy123', '2021-03-06', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `merchandise`
--

CREATE TABLE `merchandise` (
  `id` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `name` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `type` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `branch` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `import_price` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE `order` (
  `id` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `type` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `customer_id` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `status` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order_detail`
--

CREATE TABLE `order_detail` (
  `id` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `order_id` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `merchandise_id` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `receipt`
--

CREATE TABLE `receipt` (
  `id` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `order_id` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `employee_id` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `name` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `roles_detail`
--

CREATE TABLE `roles_detail` (
  `id` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `roles_id` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `employee_id` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `salary`
--

CREATE TABLE `salary` (
  `id` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `employee_id` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `salary_detail`
--

CREATE TABLE `salary_detail` (
  `id` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `employee_id` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `payment_status` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `description` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `work_shift`
--

CREATE TABLE `work_shift` (
  `id` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `shift` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `work_table`
--

CREATE TABLE `work_table` (
  `id` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `employee_id` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `shift_id` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `date` date DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
