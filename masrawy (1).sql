-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 09, 2022 at 02:35 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 7.3.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `masrawy`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `id_account` int(11) NOT NULL,
  `name_account` varchar(255) NOT NULL,
  `balance_account` double(10,2) DEFAULT NULL,
  `id_type` int(11) NOT NULL,
  `now_balance` double(10,2) DEFAULT NULL,
  `isEnable` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `accountworkgroup`
--

CREATE TABLE `accountworkgroup` (
  `id_account` int(11) NOT NULL,
  `id_workgroup` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `assets`
--

CREATE TABLE `assets` (
  `id_assets` int(11) NOT NULL,
  `price_assets` double(10,2) NOT NULL,
  `notes` varchar(1024) NOT NULL,
  `date_assets` date NOT NULL,
  `id_workgroup` int(11) DEFAULT NULL,
  `isRelay` bit(1) DEFAULT NULL,
  `id_clear` int(11) DEFAULT NULL,
  `id_daily` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `clear`
--

CREATE TABLE `clear` (
  `id_clear` int(11) NOT NULL,
  `date_clear` date NOT NULL,
  `notes` varchar(1024) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `creditors`
--

CREATE TABLE `creditors` (
  `id_credit` int(11) NOT NULL,
  `date_credit` date NOT NULL,
  `amount` double(10,2) NOT NULL,
  `id_account` int(11) NOT NULL,
  `id_clear` int(11) NOT NULL,
  `note` varchar(1024) NOT NULL,
  `isFiltering` bit(1) NOT NULL DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `daily`
--

CREATE TABLE `daily` (
  `id_daily` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `date_day` date DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `note` varchar(1024) DEFAULT NULL,
  `name_table` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `deal`
--

CREATE TABLE `deal` (
  `id_deal` int(11) NOT NULL,
  `name_deal` varchar(255) NOT NULL,
  `type_deal` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `discount`
--

CREATE TABLE `discount` (
  `id_discount` int(11) NOT NULL,
  `date_discount` date DEFAULT NULL,
  `amount_discount` double(10,2) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `expens`
--

CREATE TABLE `expens` (
  `id_expens` int(11) NOT NULL,
  `date_expens` date NOT NULL,
  `price_expens` double(10,2) NOT NULL,
  `id_workgroup` int(11) NOT NULL,
  `note` varchar(1024) DEFAULT NULL,
  `id_Suppliers` int(11) NOT NULL DEFAULT 1,
  `isRelay` bit(1) DEFAULT b'0',
  `id_clear` int(11) DEFAULT NULL,
  `id_daily` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `exports`
--

CREATE TABLE `exports` (
  `id_exports` int(11) NOT NULL,
  `date_exports` date NOT NULL,
  `price_export` double(10,2) NOT NULL,
  `id_account` int(11) NOT NULL,
  `note` varchar(1024) DEFAULT NULL,
  `id_Suppliers` int(11) NOT NULL,
  `isFiltering` bit(1) NOT NULL,
  `id_daily` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `exsuppliers`
--

CREATE TABLE `exsuppliers` (
  `id_exSuppliers` int(11) NOT NULL,
  `date_exSuppliers` date NOT NULL,
  `id_Suppliers` int(11) NOT NULL,
  `price_exSuppliers` double(10,2) NOT NULL,
  `note` varchar(512) NOT NULL,
  `id_daily` int(11) NOT NULL,
  `IsActive` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `imports`
--

CREATE TABLE `imports` (
  `id_import` int(11) NOT NULL,
  `date_imports` date NOT NULL,
  `wight_imports` double(10,2) NOT NULL,
  `caliber` int(11) NOT NULL,
  `price_imports` double(10,2) NOT NULL,
  `amount_imports` double(10,2) NOT NULL,
  `id_workgroup` int(11) NOT NULL,
  `image` mediumblob DEFAULT NULL,
  `isRelay` bit(1) DEFAULT b'0',
  `id_clear` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `imsuppliers`
--

CREATE TABLE `imsuppliers` (
  `id_ImSuppliers` int(11) NOT NULL,
  `id_Suppliers` int(11) NOT NULL,
  `id_expens` int(11) NOT NULL,
  `IsActive` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
--

CREATE TABLE `suppliers` (
  `id_Suppliers` int(11) NOT NULL,
  `name_Suppliers` varchar(255) NOT NULL,
  `old_Balance` double(10,2) DEFAULT NULL,
  `now_balance` double(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(255) NOT NULL,
  `passwod` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `workgroup`
--

CREATE TABLE `workgroup` (
  `id_workgroup` int(11) NOT NULL,
  `name_workgroup` varchar(255) NOT NULL,
  `id_deal` int(11) DEFAULT NULL,
  `isActive` bit(1) NOT NULL DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `_type`
--

CREATE TABLE `_type` (
  `id_type` int(11) NOT NULL,
  `name_type` varchar(512) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id_account`),
  ADD KEY `id_type` (`id_type`);

--
-- Indexes for table `accountworkgroup`
--
ALTER TABLE `accountworkgroup`
  ADD PRIMARY KEY (`id_account`,`id_workgroup`),
  ADD KEY `Constr_work_fk` (`id_workgroup`);

--
-- Indexes for table `assets`
--
ALTER TABLE `assets`
  ADD PRIMARY KEY (`id_assets`),
  ADD KEY `fk_work` (`id_workgroup`),
  ADD KEY `fk_clear` (`id_clear`);

--
-- Indexes for table `clear`
--
ALTER TABLE `clear`
  ADD PRIMARY KEY (`id_clear`);

--
-- Indexes for table `creditors`
--
ALTER TABLE `creditors`
  ADD PRIMARY KEY (`id_credit`),
  ADD KEY `id_account` (`id_account`),
  ADD KEY `fk_clear2` (`id_clear`);

--
-- Indexes for table `daily`
--
ALTER TABLE `daily`
  ADD PRIMARY KEY (`id_daily`);

--
-- Indexes for table `deal`
--
ALTER TABLE `deal`
  ADD PRIMARY KEY (`id_deal`);

--
-- Indexes for table `discount`
--
ALTER TABLE `discount`
  ADD PRIMARY KEY (`id_discount`);

--
-- Indexes for table `expens`
--
ALTER TABLE `expens`
  ADD PRIMARY KEY (`id_expens`),
  ADD KEY `id_workgroup` (`id_workgroup`),
  ADD KEY `fk_clear_exp` (`id_clear`),
  ADD KEY `fk_supplierExpens` (`id_Suppliers`);

--
-- Indexes for table `exports`
--
ALTER TABLE `exports`
  ADD PRIMARY KEY (`id_exports`),
  ADD KEY `id_account` (`id_account`),
  ADD KEY `fk_supllier_export` (`id_Suppliers`);

--
-- Indexes for table `exsuppliers`
--
ALTER TABLE `exsuppliers`
  ADD PRIMARY KEY (`id_exSuppliers`),
  ADD KEY `id_Suppliers` (`id_Suppliers`),
  ADD KEY `id_daily` (`id_daily`);

--
-- Indexes for table `imports`
--
ALTER TABLE `imports`
  ADD PRIMARY KEY (`id_import`),
  ADD KEY `id_workgroup` (`id_workgroup`),
  ADD KEY `fk_clear_im` (`id_clear`);

--
-- Indexes for table `imsuppliers`
--
ALTER TABLE `imsuppliers`
  ADD PRIMARY KEY (`id_ImSuppliers`),
  ADD KEY `id_Suppliers` (`id_Suppliers`),
  ADD KEY `id_expens` (`id_expens`);

--
-- Indexes for table `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`id_Suppliers`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `workgroup`
--
ALTER TABLE `workgroup`
  ADD PRIMARY KEY (`id_workgroup`),
  ADD KEY `fkdeal` (`id_deal`);

--
-- Indexes for table `_type`
--
ALTER TABLE `_type`
  ADD PRIMARY KEY (`id_type`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `daily`
--
ALTER TABLE `daily`
  MODIFY `id_daily` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `account_ibfk_2` FOREIGN KEY (`id_type`) REFERENCES `_type` (`id_type`);

--
-- Constraints for table `accountworkgroup`
--
ALTER TABLE `accountworkgroup`
  ADD CONSTRAINT `Constr_account_fk` FOREIGN KEY (`id_account`) REFERENCES `account` (`id_account`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Constr_work_fk` FOREIGN KEY (`id_workgroup`) REFERENCES `workgroup` (`id_workgroup`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `assets`
--
ALTER TABLE `assets`
  ADD CONSTRAINT `fk_clear` FOREIGN KEY (`id_clear`) REFERENCES `clear` (`id_clear`),
  ADD CONSTRAINT `fk_work` FOREIGN KEY (`id_workgroup`) REFERENCES `workgroup` (`id_workgroup`);

--
-- Constraints for table `creditors`
--
ALTER TABLE `creditors`
  ADD CONSTRAINT `creditors_ibfk_1` FOREIGN KEY (`id_account`) REFERENCES `account` (`id_account`),
  ADD CONSTRAINT `fk_clear2` FOREIGN KEY (`id_clear`) REFERENCES `clear` (`id_clear`);

--
-- Constraints for table `expens`
--
ALTER TABLE `expens`
  ADD CONSTRAINT `expens_ibfk_1` FOREIGN KEY (`id_workgroup`) REFERENCES `workgroup` (`id_workgroup`),
  ADD CONSTRAINT `fk_clear_exp` FOREIGN KEY (`id_clear`) REFERENCES `clear` (`id_clear`),
  ADD CONSTRAINT `fk_supplierExpens` FOREIGN KEY (`id_Suppliers`) REFERENCES `suppliers` (`id_Suppliers`);

--
-- Constraints for table `exports`
--
ALTER TABLE `exports`
  ADD CONSTRAINT `exports_ibfk_1` FOREIGN KEY (`id_account`) REFERENCES `account` (`id_account`),
  ADD CONSTRAINT `fk_supllier_export` FOREIGN KEY (`id_Suppliers`) REFERENCES `suppliers` (`id_Suppliers`);

--
-- Constraints for table `exsuppliers`
--
ALTER TABLE `exsuppliers`
  ADD CONSTRAINT `exsuppliers_ibfk_1` FOREIGN KEY (`id_Suppliers`) REFERENCES `suppliers` (`id_Suppliers`),
  ADD CONSTRAINT `exsuppliers_ibfk_2` FOREIGN KEY (`id_daily`) REFERENCES `daily` (`id_daily`);

--
-- Constraints for table `imports`
--
ALTER TABLE `imports`
  ADD CONSTRAINT `fk_clear_im` FOREIGN KEY (`id_clear`) REFERENCES `clear` (`id_clear`),
  ADD CONSTRAINT `imports_ibfk_1` FOREIGN KEY (`id_workgroup`) REFERENCES `workgroup` (`id_workgroup`);

--
-- Constraints for table `imsuppliers`
--
ALTER TABLE `imsuppliers`
  ADD CONSTRAINT `imsuppliers_ibfk_1` FOREIGN KEY (`id_Suppliers`) REFERENCES `suppliers` (`id_Suppliers`),
  ADD CONSTRAINT `imsuppliers_ibfk_2` FOREIGN KEY (`id_expens`) REFERENCES `expens` (`id_expens`);

--
-- Constraints for table `workgroup`
--
ALTER TABLE `workgroup`
  ADD CONSTRAINT `fkdeal` FOREIGN KEY (`id_deal`) REFERENCES `deal` (`id_deal`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
