-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 26, 2022 at 07:56 PM
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
-- Database: `aliaccount`
--

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `id_client` int(11) NOT NULL,
  `name_client` varchar(512) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `address` varchar(512) DEFAULT NULL,
  `idNational` varchar(14) DEFAULT NULL,
  `firstBalance` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`id_client`, `name_client`, `phone`, `address`, `idNational`, `firstBalance`) VALUES
(1, 'احمد محمد', '01127333021', 'العدوة', '12345698745625', 20000),
(2, 'مصطفي يوسف', '01221788873', 'النزل', '28611182800257', 0),
(3, 'test Client', '0100255164', 'ادفو', '28611162800253', 0);

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `id_items` int(11) NOT NULL,
  `name_items` varchar(255) DEFAULT NULL,
  `firstbalance` int(11) DEFAULT NULL,
  `id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`id_items`, `name_items`, `firstbalance`, `id`) VALUES
(1, 'زئبق', 20, 1),
(2, 'مادة اصلي', 5, 2);

-- --------------------------------------------------------

--
-- Table structure for table `itemsonsalesinvoice`
--

CREATE TABLE `itemsonsalesinvoice` (
  `id` int(11) DEFAULT NULL,
  `id_items` int(11) DEFAULT NULL,
  `name_items` varchar(512) DEFAULT NULL,
  `qyt` double(10,2) DEFAULT NULL,
  `id_unit` int(11) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `discount` double(10,2) DEFAULT NULL,
  `Amount` double(10,2) DEFAULT NULL,
  `id_salesInvoic` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `priselist`
--

CREATE TABLE `priselist` (
  `last_edit` date DEFAULT NULL,
  `purchase_price_low` double(10,2) DEFAULT NULL,
  `purchase_price_high` double(10,2) DEFAULT NULL,
  `sale_price_low` double(10,2) DEFAULT NULL,
  `sale_price_high` double(10,2) DEFAULT NULL,
  `id_items` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `priselist`
--

INSERT INTO `priselist` (`last_edit`, `purchase_price_low`, `purchase_price_high`, `sale_price_low`, `sale_price_high`, `id_items`) VALUES
('2022-05-26', 2900.00, 97150.00, 3200.00, 107200.00, 1),
('2022-05-31', 2500.00, 83750.00, 2800.00, 93800.00, 1),
('2022-05-26', 150.00, 3750.00, 175.00, 4375.00, 2),
('2022-06-01', 1000.00, 33500.00, 1200.00, 40200.00, 1);

-- --------------------------------------------------------

--
-- Table structure for table `salesinvoic`
--

CREATE TABLE `salesinvoic` (
  `id_salesInvoic` int(11) NOT NULL,
  `date_salesInvoic` date DEFAULT NULL,
  `type_salesInvoic` varchar(512) DEFAULT NULL,
  `id_client` int(11) DEFAULT NULL,
  `totalAmount` double(10,2) DEFAULT NULL,
  `discount` double(10,2) DEFAULT NULL,
  `amountCash` double(10,2) DEFAULT NULL,
  `amountLater` double(10,2) DEFAULT NULL,
  `note` varchar(1024) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `salesinvoic`
--

INSERT INTO `salesinvoic` (`id_salesInvoic`, `date_salesInvoic`, `type_salesInvoic`, `id_client`, `totalAmount`, `discount`, `amountCash`, `amountLater`, `note`) VALUES
(1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
--

CREATE TABLE `suppliers` (
  `id_Suppliers` int(11) NOT NULL,
  `name_Suppliers` varchar(512) NOT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `address` varchar(512) DEFAULT NULL,
  `idNational` varchar(14) DEFAULT NULL,
  `firstBalance` double(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `suppliers`
--

INSERT INTO `suppliers` (`id_Suppliers`, `name_Suppliers`, `phone`, `address`, `idNational`, `firstBalance`) VALUES
(1, 'ممدوح النوري', '0101220535', 'القاهرة', '28911182800541', 0.00);

-- --------------------------------------------------------

--
-- Table structure for table `unit`
--

CREATE TABLE `unit` (
  `id` int(11) NOT NULL,
  `low` varchar(512) DEFAULT NULL,
  `high` varchar(512) DEFAULT NULL,
  `val` double(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `unit`
--

INSERT INTO `unit` (`id`, `low`, `high`, `val`) VALUES
(1, 'كيلو', 'اسطوانة', 33.50),
(2, 'كيلو', 'شكارة', 25.00),
(3, 'كيس', 'لفة', 20.00);

-- --------------------------------------------------------

--
-- Stand-in structure for view `unititems`
-- (See below for the actual view)
--
CREATE TABLE `unititems` (
`id_items` int(11)
,`name_items` varchar(255)
,`firstbalance` int(11)
,`low` varchar(512)
,`high` varchar(512)
,`val` double(10,2)
);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(255) DEFAULT NULL,
  `passwod` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `passwod`) VALUES
('ali', 123),
('0', 0);

-- --------------------------------------------------------

--
-- Structure for view `unititems`
--
DROP TABLE IF EXISTS `unititems`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `unititems`  AS  (select `i`.`id_items` AS `id_items`,`i`.`name_items` AS `name_items`,`i`.`firstbalance` AS `firstbalance`,`u`.`low` AS `low`,`u`.`high` AS `high`,`u`.`val` AS `val` from (`items` `i` join `unit` `u` on(`i`.`id` = `u`.`id`))) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id_client`);

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`id_items`),
  ADD UNIQUE KEY `namtitm` (`name_items`),
  ADD KEY `fkitemsunit` (`id`);

--
-- Indexes for table `itemsonsalesinvoice`
--
ALTER TABLE `itemsonsalesinvoice`
  ADD KEY `id_items` (`id_items`),
  ADD KEY `id_unit` (`id_unit`),
  ADD KEY `id_salesInvoic` (`id_salesInvoic`);

--
-- Indexes for table `priselist`
--
ALTER TABLE `priselist`
  ADD KEY `id_items` (`id_items`);

--
-- Indexes for table `salesinvoic`
--
ALTER TABLE `salesinvoic`
  ADD PRIMARY KEY (`id_salesInvoic`),
  ADD KEY `id_client` (`id_client`);

--
-- Indexes for table `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`id_Suppliers`);

--
-- Indexes for table `unit`
--
ALTER TABLE `unit`
  ADD PRIMARY KEY (`id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `items`
--
ALTER TABLE `items`
  ADD CONSTRAINT `fkitemsunit` FOREIGN KEY (`id`) REFERENCES `unit` (`id`);

--
-- Constraints for table `itemsonsalesinvoice`
--
ALTER TABLE `itemsonsalesinvoice`
  ADD CONSTRAINT `itemsonsalesinvoice_ibfk_1` FOREIGN KEY (`id_items`) REFERENCES `items` (`id_items`),
  ADD CONSTRAINT `itemsonsalesinvoice_ibfk_2` FOREIGN KEY (`id_unit`) REFERENCES `unit` (`id`),
  ADD CONSTRAINT `itemsonsalesinvoice_ibfk_3` FOREIGN KEY (`id_salesInvoic`) REFERENCES `salesinvoic` (`id_salesInvoic`);

--
-- Constraints for table `priselist`
--
ALTER TABLE `priselist`
  ADD CONSTRAINT `priselist_ibfk_1` FOREIGN KEY (`id_items`) REFERENCES `items` (`id_items`);

--
-- Constraints for table `salesinvoic`
--
ALTER TABLE `salesinvoic`
  ADD CONSTRAINT `salesinvoic_ibfk_1` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
