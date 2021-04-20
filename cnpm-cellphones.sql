-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: Apr 20, 2021 at 11:25 AM
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

INSERT INTO `customer` (`id`, `full_name`, `type`, `address`, `phone`, `email`, `created_date`, `updated_date`) VALUES
('1305a70d-6a75-4b67-817b-a72639f07070', 'Cassandra R Santibanez', 'Khách hàng', '2133 Joanne Lane, Woburn, Massachusetts', '6179703129', 'dina2001@gmail.com', '2021-03-25', NULL),
('17f95de3-3569-40dc-97a7-d6e8f4743f21', 'Huy', 'Nhà cung cấp', '555 Pasteur, Quận 8, TP.HCM', '0979487154', 'auhaihuy246@gmail.com', '2021-03-14', '2021-03-17'),
('1b9cdded-1e5e-4e94-8c5e-fa5c4e44f3ce', 'Alfonso H Jeffcoat', 'Nhà cung cấp', '2462 McVaney Road, Charlotte, North Carolina', '7045628176', 'carlos2003@yahoo.com', '2021-03-25', NULL),
('245d626f-7bf0-4192-a857-7aab71e12697', 'John', 'Khách hàng', '221/3G Phan Văn Khoẻ, P.5, Q.6, TP.HCM', '0893012341', 'john@gmail.com', '2021-03-24', NULL),
('4ddb0779-2394-440a-b015-75d7a1290f2a', 'Hải', 'Khách hàng', '54A/87/22 Lê Trọng Tấn, BS Avenue, New York', '09798541258', 'hai@gmail.com', '2021-03-14', NULL),
('510821e0-2347-4d45-b01f-25be3768a32e', 'John Bennet', 'Khách hàng', '1730 Roguski Road, Ruston', '9891436574', 'Bennet123@gmail.com', '2021-03-28', '2021-03-28'),
('7f91758c-02ac-4538-a606-d9dbb47b5397', 'Winnie The Pooh', 'Khách hàng', 'High St, Hartfield TN7 4AE, United Kingdom', '1892771155', 'pooh@gmail.com', '2021-03-14', '2021-03-14'),
('d52e95f1-b7c4-457d-9bda-a858c9911b63', 'Ronnie K Walters', 'Khách hàng', '1584 James Avenue, Wichita, Kansas', '3166802141', 'hazle2016@gmail.com', '2021-03-25', NULL);

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
('1a2e63bb-76d5-4375-9c84-96d246522224', 'Admin', '2021-03-19', '0908215136', 'admin@gmail.com', '$2a$09$OKsae4EItSmPiqZPkaXYIuYOM5kgVkhMbqxXjdMIv0LqA5RMN9A9u', '2021-03-18', NULL),
('81915f39-dd3e-42e7-8db0-d7dc8bb3f783', 'Fast Account', '2021-03-21', '0908706541', 'a', '$2a$09$ndFgZtuc4Q.s.xOJEGB6uOZ7xnFcySX.5DRATm65PDHrdgyGPuhry', '2021-03-21', NULL);

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
('58bfa103-ea60-4384-a6e7-df54fb0a84f1', 'c3faf74c-563b-48dc-9eaf-d5277273899e', '81915f39-dd3e-42e7-8db0-d7dc8bb3f783'),
('b828ec0d-6c00-4762-ab12-b79251f19383', 'c3faf74c-563b-48dc-9eaf-d5277273899e', '1a2e63bb-76d5-4375-9c84-96d246522224');

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

INSERT INTO `imports` (`id`, `orders_id`, `employee_id`, `description`, `created_date`, `updated_date`) VALUES
('0bff0823-3497-4639-a2ae-a27fbc9199fe', '000b7b2d-ef8a-404f-b95c-14c12e6e356d', '81915f39-dd3e-42e7-8db0-d7dc8bb3f783', 'Mua hàng từ nhà cung cấp Huy', '2021-04-01', NULL),
('a9639d3f-617b-4e9c-893f-96f1ef159c9f', '34b6849c-2456-465e-950c-96c17b6843cf', '81915f39-dd3e-42e7-8db0-d7dc8bb3f783', 'Mua hàng từ nhà cung cấp Alfonso H Jeffcoat', '2021-04-01', NULL),
('af43ac4c-8e3f-42c5-a6e9-d975a185e3b4', '000b7b2d-ef8a-404f-b95c-14c12e6e356d', '81915f39-dd3e-42e7-8db0-d7dc8bb3f783', 'Mua hàng từ nhà cung cấp Huy', '2021-04-01', NULL),
('ea6e2cd7-596c-439d-8b51-99279d6f9c86', '65f17e68-d13b-46da-a4f5-439c4cafff7e', '81915f39-dd3e-42e7-8db0-d7dc8bb3f783', 'test import', NULL, NULL);

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

INSERT INTO `imports_detail` (`id`, `imports_id`, `merchandise_id`, `quantity`, `amount`) VALUES
('1ddf35f8-e4ee-4b2f-a0ac-fb945e8b65dd', '0bff0823-3497-4639-a2ae-a27fbc9199fe', 'ef41a828-c592-4c4f-a218-71ebfbd2a5ad', 2, 5780000),
('781a4af3-f273-44c7-b64a-97f44ec0e4cf', '0bff0823-3497-4639-a2ae-a27fbc9199fe', 'd458b1b7-95d1-429a-845e-36a78b7de133', 1, 21700000),
('7a93825d-21c5-457e-96cc-5ef3d8dc426d', 'ea6e2cd7-596c-439d-8b51-99279d6f9c86', 'ef41a828-c592-4c4f-a218-71ebfbd2a5ad', 2, 5780000),
('7db5ef58-45d6-4e15-872f-7acbfdd0b16c', 'af43ac4c-8e3f-42c5-a6e9-d975a185e3b4', '281a8ad7-f247-479b-9129-bed4dc0b6493', 1, 2790000),
('7f8348bf-2a0f-41fe-a4d7-32ddbc0e9c9d', 'a9639d3f-617b-4e9c-893f-96f1ef159c9f', '421b6d29-f3c1-4cf3-9917-37fe0aa86ec8', 30, 1500000),
('8b20263c-0da4-427e-b41f-c12e30f99c10', '0bff0823-3497-4639-a2ae-a27fbc9199fe', '281a8ad7-f247-479b-9129-bed4dc0b6493', 2, 5580000);

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
('120c1cfc-5459-4738-a88b-b9020fb4155a', 'Xiaomi Mi 10T Pro 5G', 'Điện thoại', 'Xiaomi', 19, '10000000', '11300000', '2021-03-26', '2021-04-13'),
('123915d3-cef5-4168-98ab-2bd461ddb961', 'Oppo A73', 'Điện thoại', 'Oppo', 17, '4700000', '4890000', '2021-03-25', NULL),
('281a8ad7-f247-479b-9129-bed4dc0b6493', 'Samsung Galaxy Buds Live Đen', 'Tai nghe', 'Samsung', 90, '2700000', '2790000', '2021-03-24', '2021-04-13'),
('329d09d1-0483-4459-865f-5d6dd5700ddb', 'Loa Bluetooth JBL Charge 4', 'Loa', 'JBL', 3, '2400000', '2490000', '2021-03-25', NULL),
('3bf786c5-6ac4-41e0-bfd9-5750e3c191f4', 'Xiaomi Redmi Note 10', 'Điện thoại', 'Xiaomi', 42, '5000000', '5490000', '2021-03-25', NULL),
('407730fb-f46a-437b-9641-22c6f076696f', 'Cáp Type-C Xiaomi ZMI AL701', 'Cáp', 'Xiaomi', 26, '45000', '50000', NULL, NULL),
('421b6d29-f3c1-4cf3-9917-37fe0aa86ec8', 'Ốp lưng iphone 12', 'Ốp lưng', 'DiB', 85, '89000', '50000', NULL, '2021-04-01'),
('59077bfb-4303-48ed-a6a4-d05a23307922', 'Energizer 10000 mAh UE10022 ', 'Pin dự phòng', 'Energizer', 3, '350000', '390000', '2021-03-25', NULL),
('64d812e0-ca72-4ea9-bf0e-bbe30017c5e4', 'Sạc nhanh Apple iPhone 20W', 'Sạc', 'Apple', 7, '500000', '550000', '2021-03-25', '2021-03-26'),
('6cee9f21-345e-4348-b516-da65870f1bed', 'Samsung Galaxy A02s', 'Điện thoại', 'Samsung', 40, '2900000', '3000000', '2021-03-24', '2021-03-27'),
('d458b1b7-95d1-429a-845e-36a78b7de133', 'iPad Pro 11 2020 WiFi 128GB', 'Máy tính bảng', 'Apple', 5, '20000000', '21700000', '2021-03-26', '2021-04-13'),
('ef41a828-c592-4c4f-a218-71ebfbd2a5ad', 'Vsmart Joy 4', 'Điện thoại', 'Vsmart', 6, '2800000', '2890000', '2021-03-26', '2021-04-01');

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

INSERT INTO `orders` (`id`, `type`, `customer_id`, `employee_id`, `description`, `status`, `created_date`, `updated_date`) VALUES
('000b7b2d-ef8a-404f-b95c-14c12e6e356d', 'Nhập hàng', '17f95de3-3569-40dc-97a7-d6e8f4743f21', '81915f39-dd3e-42e7-8db0-d7dc8bb3f783', 'Mua hàng từ nhà cung cấp Huy', 'Hoàn tất', '2021-03-31', '2021-04-01'),
('20e0ec8f-64bf-4e9d-9e64-fba185771faa', 'Bán hàng', '245d626f-7bf0-4192-a857-7aab71e12697', '81915f39-dd3e-42e7-8db0-d7dc8bb3f783', 'Khách hàng John mua hàng', 'Hoàn tất', '2021-04-26', '2021-03-26'),
('34b6849c-2456-465e-950c-96c17b6843cf', 'Nhập hàng', '1b9cdded-1e5e-4e94-8c5e-fa5c4e44f3ce', '81915f39-dd3e-42e7-8db0-d7dc8bb3f783', 'Mua hàng từ nhà cung cấp Alfonso H Jeffcoat', 'Chưa hoàn tất', '2021-04-01', NULL),
('3f94a669-7115-4b58-82a4-8ba0d9b7f39c', 'Nhập hàng', '17f95de3-3569-40dc-97a7-d6e8f4743f21', '81915f39-dd3e-42e7-8db0-d7dc8bb3f783', 'Mua hàng từ nhà cung cấp Huy', 'Hoàn tất', '2021-04-26', '2021-03-26'),
('58421cd1-ae5a-494d-aa3f-5d109a2e9cb1', 'Bán hàng', 'd52e95f1-b7c4-457d-9bda-a858c9911b63', '81915f39-dd3e-42e7-8db0-d7dc8bb3f783', 'Khách hàng Ronnie K Walters mua hàng', 'Hoàn tất', '2021-03-26', '2021-03-26'),
('65f17e68-d13b-46da-a4f5-439c4cafff7e', 'Nhập hàng', '17f95de3-3569-40dc-97a7-d6e8f4743f21', '81915f39-dd3e-42e7-8db0-d7dc8bb3f783', 'Mua hàng từ nhà cung cấp Huy', 'Hoàn tất', '2021-04-27', NULL),
('89fc1e3a-f927-499d-ab54-2430ff4a06f0', 'Nhập hàng', '17f95de3-3569-40dc-97a7-d6e8f4743f21', '81915f39-dd3e-42e7-8db0-d7dc8bb3f783', 'Mua hàng từ nhà cung cấp Huy', 'Hoàn tất', '2021-03-26', '2021-03-26'),
('96cc9fac-778a-4bf9-8e4c-f69f18756968', 'Bán hàng', '4ddb0779-2394-440a-b015-75d7a1290f2a', '81915f39-dd3e-42e7-8db0-d7dc8bb3f783', 'Khách hàng Hải mua hàng', 'Chưa hoàn tất', '2021-03-27', NULL),
('b2889966-af80-4f89-a348-43c98f07585a', 'Bán hàng', '7f91758c-02ac-4538-a606-d9dbb47b5397', '81915f39-dd3e-42e7-8db0-d7dc8bb3f783', 'Khách hàng Winnie The Pooh mua hàng', 'Hoàn tất', '2021-04-13', '2021-04-13'),
('f5afa833-6fb2-4281-8f7c-b86b71e229ea', 'Bán hàng', '510821e0-2347-4d45-b01f-25be3768a32e', '81915f39-dd3e-42e7-8db0-d7dc8bb3f783', 'Khách hàng John Bennet mua hàng', 'Hoàn tất', '2021-04-13', '2021-04-13');

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

INSERT INTO `orders_detail` (`id`, `orders_id`, `merchandise_id`, `quantity`, `amount`) VALUES
('2119e1c9-875d-4918-9c9f-adedcc38042b', '000b7b2d-ef8a-404f-b95c-14c12e6e356d', 'd458b1b7-95d1-429a-845e-36a78b7de133', 1, 21700000),
('218cc914-77d1-4b8e-8be0-3f02d34d3df2', '89fc1e3a-f927-499d-ab54-2430ff4a06f0', 'd458b1b7-95d1-429a-845e-36a78b7de133', 1000, 2170000),
('24214e8e-68c7-4bb5-9f32-5cc6687f8458', '3f94a669-7115-4b58-82a4-8ba0d9b7f39c', '3bf786c5-6ac4-41e0-bfd9-5750e3c191f4', 30, 164700000),
('3c161948-a2db-4bc6-85ec-13e4917a5296', '3f94a669-7115-4b58-82a4-8ba0d9b7f39c', '6cee9f21-345e-4348-b516-da65870f1bed', 40, 120000000),
('4269e428-9844-473a-9a59-8337ba510f9c', '3f94a669-7115-4b58-82a4-8ba0d9b7f39c', '123915d3-cef5-4168-98ab-2bd461ddb961', 30, 2147483647),
('76b4f0d6-e769-4152-a0dc-a03356aaaa0e', '65f17e68-d13b-46da-a4f5-439c4cafff7e', 'ef41a828-c592-4c4f-a218-71ebfbd2a5ad', 2, 5780000),
('92e44503-4abe-43a4-8ab7-aa0235e4abd7', '34b6849c-2456-465e-950c-96c17b6843cf', '407730fb-f46a-437b-9641-22c6f076696f', 20, 1000000),
('98e094e8-884f-4a90-bae1-da55d921670a', '58421cd1-ae5a-494d-aa3f-5d109a2e9cb1', '123915d3-cef5-4168-98ab-2bd461ddb961', 1, 4890000),
('9b9731f5-2267-4dfb-9d32-e8a608a10bde', '96cc9fac-778a-4bf9-8e4c-f69f18756968', 'ef41a828-c592-4c4f-a218-71ebfbd2a5ad', 1, 2890000),
('a782cf4b-4c57-44d8-a8d1-289442b9f95b', '58421cd1-ae5a-494d-aa3f-5d109a2e9cb1', '64d812e0-ca72-4ea9-bf0e-bbe30017c5e4', 1, 550000),
('a9e534e7-f0b2-46bc-9b51-41bb6ed909bc', '58421cd1-ae5a-494d-aa3f-5d109a2e9cb1', 'ef41a828-c592-4c4f-a218-71ebfbd2a5ad', 1, 2890000),
('aa50d610-489b-460e-ac97-62707202bdff', 'b2889966-af80-4f89-a348-43c98f07585a', 'd458b1b7-95d1-429a-845e-36a78b7de133', 1, 21700000),
('aaa38f25-00dd-4174-ae32-87d4cba4789c', '89fc1e3a-f927-499d-ab54-2430ff4a06f0', '329d09d1-0483-4459-865f-5d6dd5700ddb', 50, 124500000),
('c18ae26b-103f-4017-9d0f-51a1ef30811d', 'f5afa833-6fb2-4281-8f7c-b86b71e229ea', '281a8ad7-f247-479b-9129-bed4dc0b6493', 2, 5580000),
('db3404aa-51ec-4458-b520-c74d5d9affce', '20e0ec8f-64bf-4e9d-9e64-fba185771faa', '281a8ad7-f247-479b-9129-bed4dc0b6493', 2, 5580000),
('e571d2f0-ea26-4e04-b7cc-a077010e7b10', '000b7b2d-ef8a-404f-b95c-14c12e6e356d', '281a8ad7-f247-479b-9129-bed4dc0b6493', 3, 8370000),
('e5813016-54a6-4d0d-9c3d-ded7ba364c7a', '34b6849c-2456-465e-950c-96c17b6843cf', '421b6d29-f3c1-4cf3-9917-37fe0aa86ec8', 30, 1500000),
('ebab5777-fe21-4c1a-b80f-4e27a4002b30', '000b7b2d-ef8a-404f-b95c-14c12e6e356d', 'ef41a828-c592-4c4f-a218-71ebfbd2a5ad', 2, 5780000),
('f4e47c47-dbbc-4c9c-ad39-1207892799bd', 'b2889966-af80-4f89-a348-43c98f07585a', '120c1cfc-5459-4738-a88b-b9020fb4155a', 1, 11300000);

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
('EMPLOYEE__MANAGEMENT', 'Quản lí nhân viên', NULL, NULL),
('MERCHANDISE_MANAGEMENT', 'Quản lí hàng hoá', NULL, NULL),
('ORDER_MANAGEMENT', 'Quản lí đơn hàng', NULL, NULL),
('RECEIPT_MANAGEMENT', 'Quản lí hoá đơn', NULL, NULL),
('SALARY_MANAGEMENT', 'Quản lí lương', NULL, NULL),
('WORK_SHIFT_MANAGEMENT', 'Quản lí lịch làm', NULL, NULL);

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

INSERT INTO `receipt` (`id`, `order_id`, `employee_id`, `description`, `created_date`, `updated_date`) VALUES
('0db1c36b-06d8-42be-89f9-0c9e384c7e82', 'f5afa833-6fb2-4281-8f7c-b86b71e229ea', '81915f39-dd3e-42e7-8db0-d7dc8bb3f783', 'Khách hàng John Bennet mua hàng', '2021-04-13', NULL),
('1ed0e52d-9c4b-464d-b3cd-9bcac0140195', 'b2889966-af80-4f89-a348-43c98f07585a', '81915f39-dd3e-42e7-8db0-d7dc8bb3f783', 'Khách hàng Winnie The Pooh mua hàng', '2021-04-13', NULL),
('2675d78c-f2ba-4ea0-8195-ea41c30989c6', '89fc1e3a-f927-499d-ab54-2430ff4a06f0', '81915f39-dd3e-42e7-8db0-d7dc8bb3f783', 'Mua hàng từ nhà cung cấp Huy', '2021-03-26', NULL),
('581f640c-6f0c-40ba-a21b-74174b209804', '20e0ec8f-64bf-4e9d-9e64-fba185771faa', '81915f39-dd3e-42e7-8db0-d7dc8bb3f783', 'Khách hàng John mua hàng', '2021-04-26', NULL),
('8dfbce98-f1f1-41d4-b529-2f18c96af898', '3f94a669-7115-4b58-82a4-8ba0d9b7f39c', '81915f39-dd3e-42e7-8db0-d7dc8bb3f783', 'Mua hàng từ nhà cung cấp Huy', '2021-03-26', NULL),
('9c8c8503-dcc6-4f3b-a37f-091334a8e769', '58421cd1-ae5a-494d-aa3f-5d109a2e9cb1', '81915f39-dd3e-42e7-8db0-d7dc8bb3f783', 'Khách hàng Ronnie K Walters mua hàng', '2021-03-26', NULL);

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
('5cac2fdb-a7ac-44ef-b2de-586f8f506637', 'Sale', '2021-03-18', NULL),
('c3faf74c-563b-48dc-9eaf-d5277273899e', 'Admin', '2021-03-18', NULL),
('cae80e8f-d562-445b-a502-487c91dccf37', 'Admin Sale', '2021-03-18', NULL);

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
('02c2e789-8b79-49d4-968e-5d8a6a85bf9d', '5cac2fdb-a7ac-44ef-b2de-586f8f506637', 'ORDER_MANAGEMENT'),
('06aabe59-14f8-496e-be3c-82770a79e326', 'c3faf74c-563b-48dc-9eaf-d5277273899e', 'RECEIPT_MANAGEMENT'),
('22b643f0-ed03-4f17-82a4-2fde2c9166d6', 'c3faf74c-563b-48dc-9eaf-d5277273899e', 'EMPLOYEE__MANAGEMENT'),
('348cb263-9f28-4c50-ac77-2b52ba10f0b3', 'cae80e8f-d562-445b-a502-487c91dccf37', 'ORDER_MANAGEMENT'),
('3d10a5d8-ef26-4ca2-809e-dea171394016', 'c3faf74c-563b-48dc-9eaf-d5277273899e', 'MERCHANDISE_MANAGEMENT'),
('7c7944b4-f329-4abf-8433-810e9aaf33e8', 'cae80e8f-d562-445b-a502-487c91dccf37', 'RECEIPT_MANAGEMENT'),
('8803db8b-1fcc-4580-898e-31df4ab0c0b8', 'cae80e8f-d562-445b-a502-487c91dccf37', 'WORK_SHIFT_MANAGEMENT'),
('90be569c-34e5-4580-beb4-c8e8329d949b', 'cae80e8f-d562-445b-a502-487c91dccf37', 'ORDER_MANAGEMENT'),
('a7484a52-98a2-48e4-b9e7-a0996d42c400', 'c3faf74c-563b-48dc-9eaf-d5277273899e', 'ORDER_MANAGEMENT'),
('adffa01e-73a6-4f3a-8e11-b3792ca7b71c', 'cae80e8f-d562-445b-a502-487c91dccf37', 'SALARY_MANAGEMENT'),
('b08a14e3-c88f-4cbb-9ca5-60211d1ff61c', 'cae80e8f-d562-445b-a502-487c91dccf37', 'WORK_SHIFT_MANAGEMENT'),
('b6f92abd-8fac-4189-beae-6fbbedeb7484', 'cae80e8f-d562-445b-a502-487c91dccf37', 'MERCHANDISE_MANAGEMENT'),
('bf50c104-4381-496b-a865-f591e22c1f4a', 'cae80e8f-d562-445b-a502-487c91dccf37', 'RECEIPT_MANAGEMENT'),
('cbee691e-127b-441f-b7f4-6f50fd00b3ee', 'cae80e8f-d562-445b-a502-487c91dccf37', 'SALARY_MANAGEMENT'),
('d957b1d3-4103-4bfe-b189-4873c848ada7', 'cae80e8f-d562-445b-a502-487c91dccf37', 'CUSTOMER_MANAGEMENT'),
('debf70f2-a983-487e-909a-82b86b192d87', 'c3faf74c-563b-48dc-9eaf-d5277273899e', 'SALARY_MANAGEMENT'),
('e7f5ac7d-e4cc-4c7b-8b50-b10ebed6ff2b', 'c3faf74c-563b-48dc-9eaf-d5277273899e', 'CUSTOMER_MANAGEMENT'),
('f0e3e26d-a15e-46c8-935d-660764b70ae6', 'c3faf74c-563b-48dc-9eaf-d5277273899e', 'WORK_SHIFT_MANAGEMENT'),
('fa0fbbb8-2c05-40ed-8184-cdfc00d4f5c1', 'cae80e8f-d562-445b-a502-487c91dccf37', 'MERCHANDISE_MANAGEMENT');

-- --------------------------------------------------------

--
-- Table structure for table `work_shift`
--

CREATE TABLE `work_shift` (
  `id` varchar(225) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `shift` text COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `start_time` text COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `end_time` text COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `work_table`
--

CREATE TABLE `work_table` (
  `id` varchar(225) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `employee_id` varchar(225) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `shift_id` varchar(225) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `date` date NOT NULL,
  `created_date` date DEFAULT NULL,
  `updated_date` date DEFAULT NULL,
  `day_of_week` varchar(255) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

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
  ADD KEY `FK3kf10ivtrjrffnb1hw2bc7ehi` (`employee_id`),
  ADD KEY `FKb6l71g0wk8uxnk7vqwrdchmgx` (`shift_id`);

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
