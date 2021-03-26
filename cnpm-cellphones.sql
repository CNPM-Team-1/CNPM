-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: Mar 22, 2021 at 09:35 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.2.34

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

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `full_name`, `type`, `address`, `phone`, `email`, `created_date`, `updated_date`) VALUES
('17f95de3-3569-40dc-97a7-d6e8f4743f21', 'Huy', 'Nhà cung cấp', '555 Pasteur, Quận 8, TP.HCM', '0979487154', 'auhaihuy246@gmail.com', '2021-03-14', '2021-03-17'),
('4ddb0779-2394-440a-b015-75d7a1290f2a', 'Hải', 'Khách hàng', '54A/87/22 Lê Trọng Tấn, BS Avenue, New York', '09798541258', 'hai@gmail.com', '2021-03-14', NULL),
('7f91758c-02ac-4538-a606-d9dbb47b5397', 'Winnie The Pooh', 'Khách hàng', 'High St, Hartfield TN7 4AE, United Kingdom', '1892771155', 'pooh@gmail.com', '2021-03-14', '2021-03-14'),
('df9648a5-65b3-4cf3-bd72-af2796de1a82', 'Âu', 'Nhà cung cấp', 'hhh12sssaaa', '0979487150', 'au@gmail.com', '2021-03-14', '2021-03-18');

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
  `password` char(60) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`id`, `full_name`, `birth_day`, `phone`, `email`, `password`, `created_date`, `updated_date`) VALUES
('1a2e63bb-76d5-4375-9c84-96d246522224', 'Admin', '2021-03-19', '0908215136', 'admin@gmail.com', '$2a$09$OKsae4EItSmPiqZPkaXYIuYOM5kgVkhMbqxXjdMIv0LqA5RMN9A9u', '2021-03-18', NULL),
('81915f39-dd3e-42e7-8db0-d7dc8bb3f783', 'Fast Account', '2021-03-21', '0908706541', 'a', '$2a$09$ndFgZtuc4Q.s.xOJEGB6uOZ7xnFcySX.5DRATm65PDHrdgyGPuhry', '2021-03-21', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `employee_roles`
--

CREATE TABLE `employee_roles` (
  `id` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `roles_id` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `employee_id` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `employee_roles`
--

INSERT INTO `employee_roles` (`id`, `roles_id`, `employee_id`) VALUES
('b828ec0d-6c00-4762-ab12-b79251f19383', 'c3faf74c-563b-48dc-9eaf-d5277273899e', '1a2e63bb-76d5-4375-9c84-96d246522224'),
('58bfa103-ea60-4384-a6e7-df54fb0a84f1', 'c3faf74c-563b-48dc-9eaf-d5277273899e', '81915f39-dd3e-42e7-8db0-d7dc8bb3f783');

-- --------------------------------------------------------

--
-- Table structure for table `merchandise`
--

CREATE TABLE `merchandise` (
  `id` text COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `name` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `type` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `branch` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `import_price` int(20) DEFAULT NULL,
  `price` int(20) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `merchandise`
--

INSERT INTO `merchandise` (`id`, `name`, `type`, `branch`, `import_price`, `price`, `created_date`, `updated_date`) VALUES
('6cee9f21-345e-4348-b516-da65870f1bed', 'iphone 12', 'điện thoại', 'apple', 20000000, 21000000, NULL, NULL),
('407730fb-f46a-437b-9641-22c6f076696f', 'ốp lưng iphone 12', 'ốp lưng', NULL, 15000, 89000, NULL, NULL),
('421b6d29-f3c1-4cf3-9917-37fe0aa86ec8', 'ốp lưng iphone 11', 'ốp lưng', NULL, 15000, 89000, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `customer_id` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `customer_id`, `status`, `type`, `created_date`, `updated_date`) VALUES
('2185eb17-85fb-480a-8f14-0c5fe0cae39d', '4ddb0779-2394-440a-b015-75d7a1290f2a', 'COMPLETED', 'xuất', NULL, NULL),
('a631029f-52c0-4cb6-8d5e-71abae9d4a81', '7f91758c-02ac-4538-a606-d9dbb47b5397', 'COMPLETED', 'xuất', NULL, NULL);

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

--
-- Dumping data for table `order_detail`
--

INSERT INTO `order_detail` (`id`, `order_id`, `merchandise_id`, `quantity`, `amount`) VALUES
('f66f0a2b-4c26-4b91-8f10-c9150a9cd2b7', '2185eb17-85fb-480a-8f14-0c5fe0cae39d', '6cee9f21-345e-4348-b516-da65870f1bed', 1, 21000000),
('5217aacf-f9bd-480f-ab3d-47b8bd4ea163', '2185eb17-85fb-480a-8f14-0c5fe0cae39d', '407730fb-f46a-437b-9641-22c6f076696f', 1, 89000),
('6bdef7a0-7cbf-460d-8b40-6f0ea9e2b0b1', 'a631029f-52c0-4cb6-8d5e-71abae9d4a81', '421b6d29-f3c1-4cf3-9917-37fe0aa86ec8', 2, 178000);

-- --------------------------------------------------------

--
-- Table structure for table `permissions`
--

CREATE TABLE `permissions` (
  `code` text COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `name` text COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `permissions`
--

INSERT INTO `permissions` (`code`, `name`, `created_date`, `updated_date`) VALUES
('MERCHANDISE_MANAGEMENT', 'Quản lí hàng hoá', NULL, NULL),
('EMPLOYEE__MANAGEMENT', 'Quản lí nhân viên', NULL, NULL),
('CUSTOMER_MANAGEMENT', 'Quản lí khách hàng', NULL, NULL),
('ORDER_MANAGEMENT', 'Quản lí đơn hàng', NULL, NULL),
('RECEIPT_MANAGEMENT', 'Quản lí hoá đơn', NULL, NULL),
('SALARY_MANAGEMENT', 'Quản lí lương', NULL, NULL),
('WORK_SHIFT_MANAGEMENT', 'Quản lí lịch làm', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `receipt`
--

CREATE TABLE `receipt` (
  `id` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `order_id` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `employee_id` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `update_date` date DEFAULT NULL
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

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`, `created_date`, `updated_date`) VALUES
('c3faf74c-563b-48dc-9eaf-d5277273899e', 'Admin', '2021-03-18', NULL),
('5cac2fdb-a7ac-44ef-b2de-586f8f506637', 'Sale', '2021-03-18', NULL),
('cae80e8f-d562-445b-a502-487c91dccf37', 'Admin Sale', '2021-03-18', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `roles_detail`
--

CREATE TABLE `roles_detail` (
  `id` text COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `role_id` text COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `permission_code` text COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `employee_id` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `roles_id` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `roles_detail`
--

INSERT INTO `roles_detail` (`id`, `role_id`, `permission_code`, `employee_id`, `roles_id`) VALUES
('22b643f0-ed03-4f17-82a4-2fde2c9166d6', 'c3faf74c-563b-48dc-9eaf-d5277273899e', 'EMPLOYEE__MANAGEMENT', NULL, NULL),
('e7f5ac7d-e4cc-4c7b-8b50-b10ebed6ff2b', 'c3faf74c-563b-48dc-9eaf-d5277273899e', 'CUSTOMER_MANAGEMENT', NULL, NULL),
('3d10a5d8-ef26-4ca2-809e-dea171394016', 'c3faf74c-563b-48dc-9eaf-d5277273899e', 'MERCHANDISE_MANAGEMENT', NULL, NULL),
('a7484a52-98a2-48e4-b9e7-a0996d42c400', 'c3faf74c-563b-48dc-9eaf-d5277273899e', 'ORDER_MANAGEMENT', NULL, NULL),
('06aabe59-14f8-496e-be3c-82770a79e326', 'c3faf74c-563b-48dc-9eaf-d5277273899e', 'RECEIPT_MANAGEMENT', NULL, NULL),
('f0e3e26d-a15e-46c8-935d-660764b70ae6', 'c3faf74c-563b-48dc-9eaf-d5277273899e', 'WORK_SHIFT_MANAGEMENT', NULL, NULL),
('debf70f2-a983-487e-909a-82b86b192d87', 'c3faf74c-563b-48dc-9eaf-d5277273899e', 'SALARY_MANAGEMENT', NULL, NULL),
('02c2e789-8b79-49d4-968e-5d8a6a85bf9d', '5cac2fdb-a7ac-44ef-b2de-586f8f506637', 'ORDER_MANAGEMENT', NULL, NULL),
('b6f92abd-8fac-4189-beae-6fbbedeb7484', 'cae80e8f-d562-445b-a502-487c91dccf37', 'MERCHANDISE_MANAGEMENT', NULL, NULL),
('d957b1d3-4103-4bfe-b189-4873c848ada7', 'cae80e8f-d562-445b-a502-487c91dccf37', 'CUSTOMER_MANAGEMENT', NULL, NULL),
('7c7944b4-f329-4abf-8433-810e9aaf33e8', 'cae80e8f-d562-445b-a502-487c91dccf37', 'RECEIPT_MANAGEMENT', NULL, NULL),
('adffa01e-73a6-4f3a-8e11-b3792ca7b71c', 'cae80e8f-d562-445b-a502-487c91dccf37', 'SALARY_MANAGEMENT', NULL, NULL),
('b08a14e3-c88f-4cbb-9ca5-60211d1ff61c', 'cae80e8f-d562-445b-a502-487c91dccf37', 'WORK_SHIFT_MANAGEMENT', NULL, NULL),
('90be569c-34e5-4580-beb4-c8e8329d949b', 'cae80e8f-d562-445b-a502-487c91dccf37', 'ORDER_MANAGEMENT', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `salary`
--

CREATE TABLE `salary` (
  `id` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `employee_id` text COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `update_date` datetime DEFAULT NULL
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
  `shift` text COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `start_time` text COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `end_time` text COLLATE utf8mb4_vietnamese_ci NOT NULL,
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

--
-- Indexes for dumped tables
--

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
