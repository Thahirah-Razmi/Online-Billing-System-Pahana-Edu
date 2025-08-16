-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Aug 16, 2025 at 10:08 AM
-- Server version: 8.0.36
-- PHP Version: 8.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `online_billing_system_pahana_edu`
--

-- --------------------------------------------------------

--
-- Table structure for table `billing`
--

DROP TABLE IF EXISTS `billing`;
CREATE TABLE IF NOT EXISTS `billing` (
  `id` int NOT NULL AUTO_INCREMENT,
  `customer_account_number` varchar(10) DEFAULT NULL,
  `customer_name` varchar(100) DEFAULT NULL,
  `books_purchased` text,
  `total_amount` decimal(10,2) DEFAULT NULL,
  `bill_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `generated_by` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `billing`
--

INSERT INTO `billing` (`id`, `customer_account_number`, `customer_name`, `books_purchased`, `total_amount`, `bill_date`, `generated_by`) VALUES
(1, 'PAH002', 'Nimal Perera', 'Clean Code(qty-1), Think and Grow Rich(qty-1)', 34200.00, '2025-07-31 12:26:54', 'Admin'),
(2, 'PAH005', 'Rizwan Mohamed', 'Harry Potter and the Philosopher\'s Stone(qty-1), වෙරළෙක පැතුම්(qty-1)', 14475.00, '2025-07-31 12:26:54', 'Staff1'),
(3, 'PAH006', 'Amaya Perera', 'වෙරළෙක පැතුම්(qty-2), Clean Code(qty-1), Think and Grow Rich(qty-1)', 31650.00, '2025-07-31 15:15:47', 'Admin'),
(4, 'PAH005', 'Rizwan Mohamed', 'பொன்னியின் செல்வன்(qty-3)', 3600.00, '2025-07-31 15:26:50', 'Admin'),
(5, 'PAH003', 'Tharindu Jayasuriya', 'Clean Code(qty-1)', 13800.00, '2025-08-08 15:24:15', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
CREATE TABLE IF NOT EXISTS `books` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `category` varchar(100) DEFAULT NULL,
  `author` varchar(150) DEFAULT NULL,
  `language` varchar(100) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`id`, `title`, `category`, `author`, `language`, `price`) VALUES
(1, 'Clean Code', 'Programming', 'Robert C. Martin', 'English', 13800.00),
(2, 'Harry Potter and the Philosopher\'s Stone', 'Fantasy', 'J.K. Rowling', 'English', 8850.00),
(3, 'Think and Grow Rich', 'Self-Help', 'Napoleon Hill', 'English', 6600.00),
(4, 'වෙරළෙක පැතුම්', 'Novel', 'සන්ජය පෙරේරා', 'Sinhala', 5625.00),
(5, 'பொன்னியின் செல்வன்', 'Historical Fiction', 'கல்கி கிருஷ்ணமூர்த்தி', 'Tamil', 1200.00);

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
CREATE TABLE IF NOT EXISTS `customers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account_number` varchar(10) NOT NULL,
  `name` varchar(100) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_account_number` (`account_number`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`id`, `account_number`, `name`, `address`, `telephone`, `email`, `created_at`) VALUES
(1, 'PAH001', 'Ayesha Fernando', '123 Galle Road, Colombo', '0771234567', 'ayesha@example.com', '2025-08-15 15:06:31'),
(2, 'PAH002', 'Nimal Perera', '45 Kandy Street, Kandy', '0712345678', 'nimal.perera@example.com', '2025-08-15 15:06:31'),
(3, 'PAH003', 'Tharindu Jayasuriya', '78 Main Street, Gampaha', '0753456789', 'tharindu.j@example.com', '2025-08-15 15:06:31'),
(4, 'PAH004', 'Shanika Silva', '12 Beach Road, Negombo', '0764567890', 'shanika.s@example.com', '2025-08-15 15:06:31'),
(5, 'PAH005', 'Rizwan Mohamed', '99 Temple Road, Batticaloa', '0785678902', 'rizwan.m@example.com', '2025-08-15 15:06:31'),
(6, 'PAH006', 'Amaya Perera', '123 Flower Road, Colombo 07', '0771534267', 'amaya.perera@example.com', '2025-08-15 15:06:31');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`),
  UNIQUE KEY `idx_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `email`, `password`, `role`) VALUES
(1, 'admin', 'admin@gmail.com', 'Admin@123', 'Admin'),
(2, 'staff1', 'staff1@gmail.com', 'Staff1@123', 'Cashier'),
(3, 'Emily', 'emily@gmail.com', 'Emily@123', 'Manager');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
