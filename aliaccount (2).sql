-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 09, 2022 at 10:34 AM
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
-- Stand-in structure for view `calcbalanceitems`
-- (See below for the actual view)
--
CREATE TABLE `calcbalanceitems` (
`id` bigint(255)
,`name_items` varchar(255)
,`firstBalance` double(10,2)
,`purchase` double(10,2)
,`Sales` double(10,2)
,`nowBalance` double(10,2)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `calcbalance_purchase`
-- (See below for the actual view)
--
CREATE TABLE `calcbalance_purchase` (
`id` bigint(255)
,`name_items` varchar(255)
,`qyt_pursh` double(23,6)
,`firstbalance` bigint(255)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `calcbalance_sales`
-- (See below for the actual view)
--
CREATE TABLE `calcbalance_sales` (
`id` bigint(255)
,`name_items` varchar(255)
,`qyt_sales` double(23,6)
,`firstbalance` bigint(255)
);

-- --------------------------------------------------------

--
-- Table structure for table `casher`
--

CREATE TABLE `casher` (
  `id_casher` bigint(255) NOT NULL,
  `date_casher` date DEFAULT NULL,
  `Debit` double(10,2) DEFAULT NULL,
  `Creditor` double(10,2) DEFAULT NULL,
  `note` text DEFAULT NULL,
  `id_purchaseInvoice` bigint(255) DEFAULT NULL,
  `id_Receipt` bigint(255) DEFAULT NULL,
  `id_salesInvoic` bigint(255) DEFAULT NULL,
  `id_PaymentReceipt` bigint(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `casher`
--

INSERT INTO `casher` (`id_casher`, `date_casher`, `Debit`, `Creditor`, `note`, `id_purchaseInvoice`, `id_Receipt`, `id_salesInvoic`, `id_PaymentReceipt`) VALUES
(1, '2022-06-06', NULL, 2010000.00, 'فاتورة مشتريات رقم  1', 1, NULL, NULL, NULL),
(4, '2022-06-06', NULL, 5000.00, 'فاتورة مشتريات رقم  2', 2, NULL, NULL, NULL),
(9, '2022-06-08', 50000.00, NULL, 'دفعة من حساب  احمد محمد', NULL, 1, NULL, NULL),
(10, '2022-06-08', NULL, 100500.00, 'فاتورة مشتريات رقم  3', 3, NULL, NULL, NULL),
(14, '2022-06-09', NULL, 1500.00, 'ايصال دفع رقم 1', NULL, NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `id_client` bigint(255) NOT NULL,
  `name_client` varchar(512) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `address` varchar(512) DEFAULT NULL,
  `idNational` varchar(14) DEFAULT NULL,
  `firstBalance` double NOT NULL,
  `isActive` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`id_client`, `name_client`, `phone`, `address`, `idNational`, `firstBalance`, `isActive`) VALUES
(1, 'عميل نقدي', NULL, NULL, NULL, 0, 1),
(2, 'احمد محمد', '01127333021', 'العدوة', '12345698745625', 100000, 1),
(3, 'مصطفي يوسف', '01221788873', 'النزل', '28611182800257', 350000, 1),
(4, 'حسن خالد', '01004020365', 'كوم امبو', '', 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `clientaccount`
--

CREATE TABLE `clientaccount` (
  `id_ClientAccount` bigint(255) NOT NULL,
  `date_ClientAccount` date DEFAULT NULL,
  `Debit` double(10,2) DEFAULT NULL,
  `Creditor` double(10,2) DEFAULT NULL,
  `id_client` bigint(255) DEFAULT NULL,
  `id_salesInvoic` bigint(255) DEFAULT NULL,
  `id_Receipt` bigint(255) DEFAULT NULL,
  `note` text DEFAULT NULL,
  `isActive` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `clientaccount`
--

INSERT INTO `clientaccount` (`id_ClientAccount`, `date_ClientAccount`, `Debit`, `Creditor`, `id_client`, `id_salesInvoic`, `id_Receipt`, `note`, `isActive`) VALUES
(1, '2022-06-08', NULL, 50000.00, 2, NULL, 1, 'دفعة من حساب  احمد محمد', 1);

-- --------------------------------------------------------

--
-- Stand-in structure for view `debitandcreditorclient`
-- (See below for the actual view)
--
CREATE TABLE `debitandcreditorclient` (
`date_ClientAccount` date
,`Debit` double(10,2)
,`Creditor` double(10,2)
,`id_client` bigint(255)
,`name_client` varchar(512)
,`id_salesInvoic` bigint(255)
,`id_Receipt` bigint(255)
,`note` text
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `debitandcreditorsupplier`
-- (See below for the actual view)
--
CREATE TABLE `debitandcreditorsupplier` (
`date_suppliersAccount` date
,`Debit` double(10,2)
,`Creditor` double(10,2)
,`id_Suppliers` bigint(255)
,`name_Suppliers` varchar(512)
,`id_purchaseInvoice` bigint(255)
,`id_paymentReceipt` bigint(255)
,`note` text
);

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `id_items` bigint(255) NOT NULL,
  `name_items` varchar(255) DEFAULT NULL,
  `firstbalance` bigint(255) DEFAULT NULL,
  `id` bigint(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`id_items`, `name_items`, `firstbalance`, `id`) VALUES
(1, 'زئبق', 20, 1),
(2, 'مادة اصلي', 5, 2);

-- --------------------------------------------------------

--
-- Table structure for table `itemsonpurchaseinvoice`
--

CREATE TABLE `itemsonpurchaseinvoice` (
  `id` bigint(255) DEFAULT NULL,
  `id_items` bigint(255) DEFAULT NULL,
  `name_items` varchar(512) DEFAULT NULL,
  `qyt` double(10,2) DEFAULT NULL,
  `name_unit` varchar(512) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `discount` double(10,2) DEFAULT NULL,
  `Amount` double(10,2) DEFAULT NULL,
  `id_purchaseInvoice` bigint(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `itemsonpurchaseinvoice`
--

INSERT INTO `itemsonpurchaseinvoice` (`id`, `id_items`, `name_items`, `qyt`, `name_unit`, `price`, `discount`, `Amount`, `id_purchaseInvoice`) VALUES
(1, 1, 'زئبق', 20.00, 'اسطوانة', 100500.00, 0.00, 2010000.00, 1),
(1, 2, 'مادة اصلي', 5.00, 'شكارة', 1000.00, 0.00, 5000.00, 2),
(1, 1, 'زئبق', 1.00, 'اسطوانة', 100500.00, 0.00, 100500.00, 3),
(2, 1, 'زئبق', 3.00, 'اسطوانة', 100500.00, 0.00, 301500.00, 4);

-- --------------------------------------------------------

--
-- Table structure for table `itemsonsalesinvoice`
--

CREATE TABLE `itemsonsalesinvoice` (
  `id` bigint(255) DEFAULT NULL,
  `id_items` bigint(255) DEFAULT NULL,
  `name_items` varchar(512) DEFAULT NULL,
  `qyt` double(10,2) DEFAULT NULL,
  `name_unit` varchar(512) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `discount` double(10,2) DEFAULT NULL,
  `Amount` double(10,2) DEFAULT NULL,
  `id_salesInvoic` bigint(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `paymentreceipt`
--

CREATE TABLE `paymentreceipt` (
  `id_PaymentReceipt` bigint(255) NOT NULL,
  `date_PaymentReceipt` date DEFAULT NULL,
  `amount` double(10,2) DEFAULT NULL,
  `id_Suppliers` bigint(255) DEFAULT NULL,
  `isActive` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `paymentreceipt`
--

INSERT INTO `paymentreceipt` (`id_PaymentReceipt`, `date_PaymentReceipt`, `amount`, `id_Suppliers`, `isActive`) VALUES
(1, '2022-06-09', 1500.00, 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `priselist`
--

CREATE TABLE `priselist` (
  `id_priceList` bigint(255) NOT NULL,
  `last_edit` date DEFAULT NULL,
  `purchase_price_low` double(10,2) DEFAULT NULL,
  `purchase_price_high` double(10,2) DEFAULT NULL,
  `sale_price_low` double(10,2) DEFAULT NULL,
  `sale_price_high` double(10,2) DEFAULT NULL,
  `id_items` bigint(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `priselist`
--

INSERT INTO `priselist` (`id_priceList`, `last_edit`, `purchase_price_low`, `purchase_price_high`, `sale_price_low`, `sale_price_high`, `id_items`) VALUES
(1, '2022-05-27', 3000.00, 100500.00, 3500.00, 117250.00, 1),
(2, '2022-05-27', 250.00, 5000.00, 500.00, 16750.00, 1);

-- --------------------------------------------------------

--
-- Table structure for table `purchaseinvoice`
--

CREATE TABLE `purchaseinvoice` (
  `id_purchaseInvoice` bigint(255) NOT NULL,
  `date_purchaseInvoice` date DEFAULT NULL,
  `type_purchaseInvoic` varchar(512) DEFAULT NULL,
  `id_Suppliers` bigint(255) DEFAULT NULL,
  `totalAmount` double(10,2) DEFAULT NULL,
  `discount` double(10,2) DEFAULT NULL,
  `amountCash` double(10,2) DEFAULT NULL,
  `amountLater` double(10,2) DEFAULT NULL,
  `note` text DEFAULT NULL,
  `isActive` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `purchaseinvoice`
--

INSERT INTO `purchaseinvoice` (`id_purchaseInvoice`, `date_purchaseInvoice`, `type_purchaseInvoic`, `id_Suppliers`, `totalAmount`, `discount`, `amountCash`, `amountLater`, `note`, `isActive`) VALUES
(1, '2022-06-06', 'كاش', 1, 2010000.00, 0.00, 2010000.00, 0.00, 'فاتورة مشتريات رقم  1', 1),
(2, '2022-06-06', 'كاش', 1, 5000.00, 0.00, 5000.00, 0.00, 'فاتورة مشتريات رقم  2', 1),
(3, '2022-06-08', 'كاش', 1, 100500.00, 0.00, 100500.00, 0.00, 'فاتورة مشتريات رقم  3', 1),
(4, '2022-06-09', 'آجل', 3, 301500.00, 0.00, 0.00, 301500.00, 'فاتورة مشتريات رقم  4', 1);

-- --------------------------------------------------------

--
-- Table structure for table `receipt`
--

CREATE TABLE `receipt` (
  `id_Receipt` bigint(255) NOT NULL,
  `date_Receipt` date DEFAULT NULL,
  `amount` double(10,2) DEFAULT NULL,
  `id_client` bigint(255) DEFAULT NULL,
  `isActive` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `receipt`
--

INSERT INTO `receipt` (`id_Receipt`, `date_Receipt`, `amount`, `id_client`, `isActive`) VALUES
(1, '2022-06-08', 50000.00, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `salesinvoic`
--

CREATE TABLE `salesinvoic` (
  `id_salesInvoic` bigint(255) NOT NULL,
  `date_salesInvoic` date DEFAULT NULL,
  `type_salesInvoic` varchar(512) DEFAULT NULL,
  `id_client` bigint(255) DEFAULT NULL,
  `totalAmount` double(10,2) DEFAULT NULL,
  `discount` double(10,2) DEFAULT NULL,
  `amountCash` double(10,2) DEFAULT NULL,
  `amountLater` double(10,2) DEFAULT NULL,
  `note` text DEFAULT NULL,
  `isActive` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
--

CREATE TABLE `suppliers` (
  `id_Suppliers` bigint(255) NOT NULL,
  `name_Suppliers` varchar(512) NOT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `address` varchar(512) DEFAULT NULL,
  `idNational` varchar(14) DEFAULT NULL,
  `firstBalance` double(10,2) DEFAULT NULL,
  `isActive` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `suppliers`
--

INSERT INTO `suppliers` (`id_Suppliers`, `name_Suppliers`, `phone`, `address`, `idNational`, `firstBalance`, `isActive`) VALUES
(1, 'مورد نقدي', NULL, NULL, NULL, NULL, 1),
(2, 'ممدوح الزين', '0112725232', 'القاهرة', '25611182800548', 0.00, 0),
(3, 'محمد خليل', '0100000000', 'الجيزة', NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `suppliersaccount`
--

CREATE TABLE `suppliersaccount` (
  `id_supplliersAccount` bigint(255) NOT NULL,
  `date_suppliersAccount` date DEFAULT NULL,
  `Debit` double(10,2) DEFAULT NULL,
  `Creditor` double(10,2) DEFAULT NULL,
  `id_Suppliers` bigint(255) DEFAULT NULL,
  `id_purchaseInvoice` bigint(255) DEFAULT NULL,
  `id_paymentReceipt` bigint(255) DEFAULT NULL,
  `note` text DEFAULT NULL,
  `isActive` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `suppliersaccount`
--

INSERT INTO `suppliersaccount` (`id_supplliersAccount`, `date_suppliersAccount`, `Debit`, `Creditor`, `id_Suppliers`, `id_purchaseInvoice`, `id_paymentReceipt`, `note`, `isActive`) VALUES
(1, '2022-06-09', NULL, 301500.00, 3, 4, NULL, 'فاتورة مشتريات رقم  4', 1),
(2, '2022-06-09', 1500.00, NULL, 3, NULL, 1, 'ايصال دفع رقم 1', 1);

-- --------------------------------------------------------

--
-- Table structure for table `unit`
--

CREATE TABLE `unit` (
  `id` bigint(255) NOT NULL,
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
`id_items` bigint(255)
,`name_items` varchar(255)
,`firstbalance` bigint(255)
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
  `passwod` bigint(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `passwod`) VALUES
('ali', 123),
('0', 0);

-- --------------------------------------------------------

--
-- Structure for view `calcbalanceitems`
--
DROP TABLE IF EXISTS `calcbalanceitems`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `calcbalanceitems`  AS SELECT `pur`.`id` AS `id`, `pur`.`name_items` AS `name_items`, ifnull(cast(`pur`.`firstbalance` as double(10,2)),0) AS `firstBalance`, ifnull(cast(`pur`.`qyt_pursh` as double(10,2)),0) AS `purchase`, ifnull(cast(`sal`.`qyt_sales` as double(10,2)),0) AS `Sales`, cast(ifnull(`pur`.`firstbalance`,0) + ifnull(`pur`.`qyt_pursh`,0) - ifnull(`sal`.`qyt_sales`,0) as double(10,2)) AS `nowBalance` FROM (`calcbalance_purchase` `pur` left join `calcbalance_sales` `sal` on(`sal`.`id` = `pur`.`id`)) ;

-- --------------------------------------------------------

--
-- Structure for view `calcbalance_purchase`
--
DROP TABLE IF EXISTS `calcbalance_purchase`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `calcbalance_purchase`  AS SELECT `items`.`id_items` AS `id`, `items`.`name_items` AS `name_items`, sum(case when `p`.`name_items` = (select `un`.`low` from `unititems` `un` where `un`.`id_items` = `p`.`id_items`) then `p`.`qyt` / (select `un`.`val` from `unititems` `un` where `un`.`id_items` = `p`.`id_items`) else `p`.`qyt` end) AS `qyt_pursh`, `items`.`firstbalance` AS `firstbalance` FROM (`items` join `itemsonpurchaseinvoice` `p` on(`p`.`id_items` = `items`.`id_items`)) GROUP BY `items`.`id_items` ORDER BY `items`.`id_items` ASC ;

-- --------------------------------------------------------

--
-- Structure for view `calcbalance_sales`
--
DROP TABLE IF EXISTS `calcbalance_sales`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `calcbalance_sales`  AS SELECT `items`.`id_items` AS `id`, `items`.`name_items` AS `name_items`, sum(case when `i`.`name_unit` = (select `un`.`low` from `unititems` `un` where `un`.`id_items` = `i`.`id_items`) then `i`.`qyt` / (select `u`.`val` from `unititems` `u` where `u`.`id_items` = `i`.`id_items`) else `i`.`qyt` end) AS `qyt_sales`, `items`.`firstbalance` AS `firstbalance` FROM (`items` join `itemsonsalesinvoice` `i` on(`i`.`id_items` = `items`.`id_items`)) GROUP BY `items`.`id_items` ORDER BY `items`.`id_items` ASC ;

-- --------------------------------------------------------

--
-- Structure for view `debitandcreditorclient`
--
DROP TABLE IF EXISTS `debitandcreditorclient`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `debitandcreditorclient`  AS SELECT `ac`.`date_ClientAccount` AS `date_ClientAccount`, `ac`.`Debit` AS `Debit`, `ac`.`Creditor` AS `Creditor`, `ac`.`id_client` AS `id_client`, `c`.`name_client` AS `name_client`, `ac`.`id_salesInvoic` AS `id_salesInvoic`, `ac`.`id_Receipt` AS `id_Receipt`, `ac`.`note` AS `note` FROM (`clientaccount` `ac` join `client` `c` on(`ac`.`id_client` = `c`.`id_client`)) WHERE `ac`.`isActive` = 1 ;

-- --------------------------------------------------------

--
-- Structure for view `debitandcreditorsupplier`
--
DROP TABLE IF EXISTS `debitandcreditorsupplier`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `debitandcreditorsupplier`  AS SELECT `ac`.`date_suppliersAccount` AS `date_suppliersAccount`, `ac`.`Debit` AS `Debit`, `ac`.`Creditor` AS `Creditor`, `ac`.`id_Suppliers` AS `id_Suppliers`, `sup`.`name_Suppliers` AS `name_Suppliers`, `ac`.`id_purchaseInvoice` AS `id_purchaseInvoice`, `ac`.`id_paymentReceipt` AS `id_paymentReceipt`, `ac`.`note` AS `note` FROM (`suppliersaccount` `ac` join `suppliers` `sup` on(`ac`.`id_Suppliers` = `sup`.`id_Suppliers`)) WHERE `ac`.`isActive` = 1 ;

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
-- Indexes for table `casher`
--
ALTER TABLE `casher`
  ADD PRIMARY KEY (`id_casher`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id_client`);

--
-- Indexes for table `clientaccount`
--
ALTER TABLE `clientaccount`
  ADD PRIMARY KEY (`id_ClientAccount`),
  ADD KEY `id_salesInvoic` (`id_salesInvoic`),
  ADD KEY `id_client` (`id_client`),
  ADD KEY `id_Receipt` (`id_Receipt`);

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`id_items`),
  ADD UNIQUE KEY `namtitm` (`name_items`),
  ADD KEY `fkitemsunit` (`id`);

--
-- Indexes for table `itemsonpurchaseinvoice`
--
ALTER TABLE `itemsonpurchaseinvoice`
  ADD KEY `id_items` (`id_items`),
  ADD KEY `id_purchaseInvoice` (`id_purchaseInvoice`);

--
-- Indexes for table `itemsonsalesinvoice`
--
ALTER TABLE `itemsonsalesinvoice`
  ADD KEY `id_items` (`id_items`),
  ADD KEY `id_salesInvoic` (`id_salesInvoic`);

--
-- Indexes for table `paymentreceipt`
--
ALTER TABLE `paymentreceipt`
  ADD PRIMARY KEY (`id_PaymentReceipt`),
  ADD KEY `id_Suppliers` (`id_Suppliers`);

--
-- Indexes for table `priselist`
--
ALTER TABLE `priselist`
  ADD PRIMARY KEY (`id_priceList`),
  ADD KEY `id_items` (`id_items`);

--
-- Indexes for table `purchaseinvoice`
--
ALTER TABLE `purchaseinvoice`
  ADD PRIMARY KEY (`id_purchaseInvoice`),
  ADD KEY `id_Suppliers` (`id_Suppliers`);

--
-- Indexes for table `receipt`
--
ALTER TABLE `receipt`
  ADD PRIMARY KEY (`id_Receipt`),
  ADD KEY `id_client` (`id_client`);

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
-- Indexes for table `suppliersaccount`
--
ALTER TABLE `suppliersaccount`
  ADD PRIMARY KEY (`id_supplliersAccount`),
  ADD KEY `id_Suppliers` (`id_Suppliers`),
  ADD KEY `id_purchaseInvoice` (`id_purchaseInvoice`),
  ADD KEY `id_paymentReceipt` (`id_paymentReceipt`);

--
-- Indexes for table `unit`
--
ALTER TABLE `unit`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `casher`
--
ALTER TABLE `casher`
  MODIFY `id_casher` bigint(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `clientaccount`
--
ALTER TABLE `clientaccount`
  MODIFY `id_ClientAccount` bigint(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `purchaseinvoice`
--
ALTER TABLE `purchaseinvoice`
  MODIFY `id_purchaseInvoice` bigint(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `suppliersaccount`
--
ALTER TABLE `suppliersaccount`
  MODIFY `id_supplliersAccount` bigint(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `clientaccount`
--
ALTER TABLE `clientaccount`
  ADD CONSTRAINT `clientaccount_ibfk_1` FOREIGN KEY (`id_salesInvoic`) REFERENCES `salesinvoic` (`id_salesInvoic`),
  ADD CONSTRAINT `clientaccount_ibfk_2` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`),
  ADD CONSTRAINT `clientaccount_ibfk_3` FOREIGN KEY (`id_Receipt`) REFERENCES `receipt` (`id_Receipt`);

--
-- Constraints for table `items`
--
ALTER TABLE `items`
  ADD CONSTRAINT `fkitemsunit` FOREIGN KEY (`id`) REFERENCES `unit` (`id`);

--
-- Constraints for table `itemsonpurchaseinvoice`
--
ALTER TABLE `itemsonpurchaseinvoice`
  ADD CONSTRAINT `itemsonpurchaseinvoice_ibfk_1` FOREIGN KEY (`id_items`) REFERENCES `items` (`id_items`),
  ADD CONSTRAINT `itemsonpurchaseinvoice_ibfk_3` FOREIGN KEY (`id_purchaseInvoice`) REFERENCES `purchaseinvoice` (`id_purchaseInvoice`);

--
-- Constraints for table `itemsonsalesinvoice`
--
ALTER TABLE `itemsonsalesinvoice`
  ADD CONSTRAINT `itemsonsalesinvoice_ibfk_1` FOREIGN KEY (`id_items`) REFERENCES `items` (`id_items`),
  ADD CONSTRAINT `itemsonsalesinvoice_ibfk_3` FOREIGN KEY (`id_salesInvoic`) REFERENCES `salesinvoic` (`id_salesInvoic`);

--
-- Constraints for table `priselist`
--
ALTER TABLE `priselist`
  ADD CONSTRAINT `priselist_ibfk_1` FOREIGN KEY (`id_items`) REFERENCES `items` (`id_items`);

--
-- Constraints for table `purchaseinvoice`
--
ALTER TABLE `purchaseinvoice`
  ADD CONSTRAINT `purchaseinvoice_ibfk_1` FOREIGN KEY (`id_Suppliers`) REFERENCES `suppliers` (`id_Suppliers`);

--
-- Constraints for table `receipt`
--
ALTER TABLE `receipt`
  ADD CONSTRAINT `receipt_ibfk_1` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`);

--
-- Constraints for table `salesinvoic`
--
ALTER TABLE `salesinvoic`
  ADD CONSTRAINT `salesinvoic_ibfk_1` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`);

--
-- Constraints for table `suppliersaccount`
--
ALTER TABLE `suppliersaccount`
  ADD CONSTRAINT `suppliersaccount_ibfk_1` FOREIGN KEY (`id_Suppliers`) REFERENCES `suppliers` (`id_Suppliers`),
  ADD CONSTRAINT `suppliersaccount_ibfk_2` FOREIGN KEY (`id_purchaseInvoice`) REFERENCES `purchaseinvoice` (`id_purchaseInvoice`),
  ADD CONSTRAINT `suppliersaccount_ibfk_3` FOREIGN KEY (`id_paymentReceipt`) REFERENCES `paymentreceipt` (`id_PaymentReceipt`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
