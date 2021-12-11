-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: Apr 23, 2021 at 11:02 AM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

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
  `id` varchar(225) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `full_name` varchar(225) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `type` varchar(225) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `address` varchar(225) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `phone` varchar(225) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `email` varchar(225) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `customer`
--
-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `id` varchar(225) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `full_name` varchar(225) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `birth_day` date NOT NULL,
  `phone` varchar(225) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `email` varchar(225) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `password` char(60) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`id`, `full_name`, `birth_day`, `phone`, `email`, `password`, `created_date`, `updated_date`) VALUES
('81915f39-dd3e-42e7-8db0-d7dc8bb3f783', 'Admin', '2021-03-21', '0908706541', 'a', '$2a$09$ndFgZtuc4Q.s.xOJEGB6uOZ7xnFcySX.5DRATm65PDHrdgyGPuhry', '2021-03-21', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `employee_roles`
--

CREATE TABLE `employee_roles` (
  `id` varchar(225) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `roles_id` varchar(225) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `employee_id` varchar(225) COLLATE utf8mb4_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `employee_roles`
--

INSERT INTO `employee_roles` (`id`, `roles_id`, `employee_id`) VALUES
('58bfa103-ea60-4384-a6e7-df54fb0a84f1', 'c3faf74c-563b-48dc-9eaf-d5277273899e', '81915f39-dd3e-42e7-8db0-d7dc8bb3f783');

-- --------------------------------------------------------

--
-- Table structure for table `imports`
--

CREATE TABLE `imports` (
  `id` varchar(225) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `orders_id` varchar(225) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `employee_id` varchar(225) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `imports`
--


-- --------------------------------------------------------

--
-- Table structure for table `imports_detail`
--

CREATE TABLE `imports_detail` (
  `id` varchar(225) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `imports_id` varchar(225) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `merchandise_id` varchar(225) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `quantity` bigint(225) NOT NULL,
  `amount` bigint(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `imports_detail`
--



-- --------------------------------------------------------

--
-- Table structure for table `merchandise`
--

CREATE TABLE `merchandise` (
  `id` varchar(225) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `name` text COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `type` text COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `branch` text COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `quantity` int(11) NOT NULL,
  `import_price` text COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `price` text COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `merchandise`
--

INSERT INTO `merchandise` (`id`, `name`, `type`, `branch`, `quantity`, `import_price`, `price`, `created_date`, `updated_date`) VALUES
('0b04793e-8184-43a9-8e2d-e21bd1573052', 'Ốp lưng iphone X', 'Điện thoại', 'DiB', 0, '15000', '90000', '2021-03-22', '2021-03-25'),
('120c1cfc-5459-4738-a88b-b9020fb4155a', 'Xiaomi Mi 10T Pro 5G', 'Điện thoại', 'Xiaomi', 20, '10000000', '11300000', '2021-03-26', NULL),
('123915d3-cef5-4168-98ab-2bd461ddb961', 'Oppo A73', 'Điện thoại', 'Oppo', 17, '4700000', '4890000', '2021-03-25', NULL),
('281a8ad7-f247-479b-9129-bed4dc0b6493', 'Samsung Galaxy Buds Live Đen', 'Tai nghe', 'Samsung', 500, '2700000', '2790000', '2021-03-24', '2021-04-08'),
('329d09d1-0483-4459-865f-5d6dd5700ddb', 'Loa Bluetooth JBL Charge 4', 'Loa', 'JBL', 3, '2400000', '2490000', '2021-03-25', NULL),
('3bf786c5-6ac4-41e0-bfd9-5750e3c191f4', 'Xiaomi Redmi Note 10', 'Điện thoại', 'Xiaomi', 42, '5000000', '5490000', '2021-03-25', NULL),
('407730fb-f46a-437b-9641-22c6f076696f', 'Cáp Type-C Xiaomi ZMI AL701', 'Cáp', 'Xiaomi', 26, '45000', '50000', NULL, NULL),
('421b6d29-f3c1-4cf3-9917-37fe0aa86ec8', 'Ốp lưng iphone 12', 'Ốp lưng', 'DiB', 85, '89000', '50000', NULL, '2021-04-01'),
('59077bfb-4303-48ed-a6a4-d05a23307922', 'Energizer 10000 mAh UE10022 ', 'Pin dự phòng', 'Energizer', 3, '350000', '390000', '2021-03-25', NULL),
('64d812e0-ca72-4ea9-bf0e-bbe30017c5e4', 'Sạc nhanh Apple iPhone 20W', 'Sạc', 'Apple', 7, '500000', '550000', '2021-03-25', '2021-03-26'),
('6cee9f21-345e-4348-b516-da65870f1bed', 'Samsung Galaxy A02s', 'Điện thoại', 'Samsung', 38, '2900000', '3000000', '2021-03-24', '2021-04-07'),
('d458b1b7-95d1-429a-845e-36a78b7de133', 'iPad Pro 11 2020 WiFi 128GB', 'Máy tính bảng', 'Apple', 6, '20000000', '21700000', '2021-03-26', '2021-04-01'),
('ef41a828-c592-4c4f-a218-71ebfbd2a5ad', 'Vsmart Joy 4', 'Điện thoại', 'Vsmart', 6, '2800000', '2890000', '2021-03-26', '2021-04-07');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `type` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `customer_id` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `employee_id` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `description` text COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `status` varchar(255) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `orders`
--

-- --------------------------------------------------------

--
-- Table structure for table `orders_detail`
--

CREATE TABLE `orders_detail` (
  `id` varchar(225) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `orders_id` varchar(225) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `merchandise_id` varchar(225) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `quantity` bigint(225) NOT NULL,
  `amount` bigint(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `orders_detail`
--
-- --------------------------------------------------------

--
-- Table structure for table `permissions`
--

CREATE TABLE `permissions` (
  `code` varchar(225) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `name` text COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `permissions`
--

INSERT INTO `permissions` (`code`, `name`, `created_date`, `updated_date`) VALUES
('CUSTOMER_MANAGEMENT', 'Quản lí khách hàng', NULL, NULL),
('EMPLOYEE_MANAGEMENT', 'Quản lí nhân viên', NULL, NULL),
('IMPORT_MANAGEMENT', 'Quản lí nhập hàng', NULL, NULL),
('MERCHANDISE_MANAGEMENT', 'Quản lí hàng hoá', NULL, NULL),
('ORDER_MANAGEMENT', 'Quản lí đơn hàng', NULL, NULL),
('RECEIPT_MANAGEMENT', 'Quản lí hoá đơn', NULL, NULL),
('ROLES_MANAGEMENT', 'Quản lí chức vụ', NULL, NULL),
('STATISTIC', 'Thống kê', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `receipt`
--

CREATE TABLE `receipt` (
  `id` varchar(225) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `order_id` varchar(225) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `employee_id` varchar(225) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `description` text COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `receipt`
--

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` varchar(225) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `name` text COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`, `created_date`, `updated_date`) VALUES
('c3faf74c-563b-48dc-9eaf-d5277273899e', 'Admin', '2021-03-18', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `roles_detail`
--

CREATE TABLE `roles_detail` (
  `id` varchar(225) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `roles_id` varchar(225) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `permission_code` varchar(225) COLLATE utf8mb4_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `roles_detail`
--

INSERT INTO `roles_detail` (`id`, `roles_id`, `permission_code`) VALUES
('2d580a4f-b783-42b6-a28b-854004601ff7', 'c3faf74c-563b-48dc-9eaf-d5277273899e', 'RECEIPT_MANAGEMENT'),
('539903c5-a678-4970-aa81-f68e63e04241', 'c3faf74c-563b-48dc-9eaf-d5277273899e', 'EMPLOYEE_MANAGEMENT'),
('6a2903ab-6973-4abc-8b28-318afdab70dd', 'c3faf74c-563b-48dc-9eaf-d5277273899e', 'MERCHANDISE_MANAGEMENT'),
('6e441ea0-289a-429b-8486-717fd101ac0a', 'c3faf74c-563b-48dc-9eaf-d5277273899e', 'ORDER_MANAGEMENT'),
('9a8f9ddd-9b0e-4019-a23e-9040edc98c82', 'c3faf74c-563b-48dc-9eaf-d5277273899e', 'STATISTIC'),
('b10dafe9-79a3-42fa-b668-ada88a9b74fe', 'c3faf74c-563b-48dc-9eaf-d5277273899e', 'IMPORT_MANAGEMENT'),
('b4949ea9-ba7d-40da-bbbd-cf7b6a3138d7', 'c3faf74c-563b-48dc-9eaf-d5277273899e', 'ROLES_MANAGEMENT'),
('b9bc5841-694b-4342-9116-a5ae77a72393', 'c3faf74c-563b-48dc-9eaf-d5277273899e', 'CUSTOMER_MANAGEMENT');

-- --------------------------------------------------------

--
-- Table structure for table `work_shift`
--

CREATE TABLE `work_shift` (
  `id` varchar(225) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `name` text COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `start_time` text COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `end_time` text COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `work_shift`
--

-- --------------------------------------------------------

--
-- Table structure for table `work_table`
--

CREATE TABLE `work_table` (
  `id` varchar(225) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `employee_id` varchar(225) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `shift_id` varchar(225) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `day_of_week` text COLLATE utf8mb4_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Dumping data for table `work_table`
--

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `employee_roles`
--
ALTER TABLE `employee_roles`
  ADD PRIMARY KEY (`id`),
  ADD KEY `roles_id` (`roles_id`),
  ADD KEY `employee_id` (`employee_id`);

--
-- Indexes for table `imports`
--
ALTER TABLE `imports`
  ADD PRIMARY KEY (`id`),
  ADD KEY `orders_id` (`orders_id`),
  ADD KEY `employee_id` (`employee_id`);

--
-- Indexes for table `imports_detail`
--
ALTER TABLE `imports_detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `imports_id` (`imports_id`),
  ADD KEY `merchandise_id` (`merchandise_id`);

--
-- Indexes for table `merchandise`
--
ALTER TABLE `merchandise`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `employee_id` (`employee_id`);

--
-- Indexes for table `orders_detail`
--
ALTER TABLE `orders_detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `orders_id` (`orders_id`),
  ADD KEY `merchandise_id` (`merchandise_id`);

--
-- Indexes for table `permissions`
--
ALTER TABLE `permissions`
  ADD PRIMARY KEY (`code`);

--
-- Indexes for table `receipt`
--
ALTER TABLE `receipt`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `employee_id` (`employee_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `roles_detail`
--
ALTER TABLE `roles_detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `role_id` (`roles_id`),
  ADD KEY `permission_code` (`permission_code`);

--
-- Indexes for table `work_shift`
--
ALTER TABLE `work_shift`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `work_table`
--
ALTER TABLE `work_table`
  ADD PRIMARY KEY (`id`),
  ADD KEY `employee_id` (`employee_id`),
  ADD KEY `shift_id` (`shift_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `employee_roles`
--
ALTER TABLE `employee_roles`
  ADD CONSTRAINT `FK4gexbjj0or2y67pho78k79d73` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  ADD CONSTRAINT `FK6dlmwykbhsc2u3lbqcimgyn7u` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`);

--
-- Constraints for table `imports`
--
ALTER TABLE `imports`
  ADD CONSTRAINT `FKi9526tte2kg2u3ap0xu99iot6` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `FKt25dfdu7b9tsxrdybak9m0eij` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`);

--
-- Constraints for table `imports_detail`
--
ALTER TABLE `imports_detail`
  ADD CONSTRAINT `FK21a8mv3d10xfjin1jw1fa9l96` FOREIGN KEY (`merchandise_id`) REFERENCES `merchandise` (`id`),
  ADD CONSTRAINT `FK8eck9rhbo4htn3qs928od7du6` FOREIGN KEY (`imports_id`) REFERENCES `imports` (`id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FK624gtjin3po807j3vix093tlf` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  ADD CONSTRAINT `FKog5v9ga2g2ukytypn4ocip6b4` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`);

--
-- Constraints for table `orders_detail`
--
ALTER TABLE `orders_detail`
  ADD CONSTRAINT `FK4n6aiyrp9yfjmb6tlrnbeykmd` FOREIGN KEY (`merchandise_id`) REFERENCES `merchandise` (`id`),
  ADD CONSTRAINT `FKlt3mrhyikkt94xukyqrv652jd` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`);

--
-- Constraints for table `receipt`
--
ALTER TABLE `receipt`
  ADD CONSTRAINT `FKniyenjjqfhfpa7gc13wwk233f` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  ADD CONSTRAINT `FKo8fi6dx59tstuoahtrp0dpnom` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

--
-- Constraints for table `roles_detail`
--
ALTER TABLE `roles_detail`
  ADD CONSTRAINT `FK6ohmx9gj2w2vy0q6mj5ff2ses` FOREIGN KEY (`permission_code`) REFERENCES `permissions` (`code`),
  ADD CONSTRAINT `FKjqtx4flv7bq6mpcw17sr3vbw4` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`);

--
-- Constraints for table `work_table`
--
ALTER TABLE `work_table`
  ADD CONSTRAINT `FK3kf10ivtrjrffnb1hw2bc7ehi` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  ADD CONSTRAINT `FKb6l71g0wk8uxnk7vqwrdchmgx` FOREIGN KEY (`shift_id`) REFERENCES `work_shift` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
