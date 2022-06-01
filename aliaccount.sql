-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 01, 2022 at 11:25 PM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.2.31

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
-- Table structure for table `casher`
--

CREATE TABLE `casher` (
  `id_casher` int(11) NOT NULL,
  `date_casher` date DEFAULT NULL,
  `Debit` double(10,2) DEFAULT NULL,
  `Creditor` double(10,2) DEFAULT NULL,
  `note` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `casher`
--

INSERT INTO `casher` (`id_casher`, `date_casher`, `Debit`, `Creditor`, `note`) VALUES
(1, '2022-06-01', 117250.00, NULL, 'فاتورة مبيعات نقدية رقم  1'),
(2, '2022-06-01', 0.00, NULL, 'فاتورة مبيعات رقم  2 - دفعة من الحساب - احمد محمد');

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
  `firstBalance` double NOT NULL,
  `isActive` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`id_client`, `name_client`, `phone`, `address`, `idNational`, `firstBalance`, `isActive`) VALUES
(1, 'عميل نقدي', NULL, NULL, NULL, 0, 1),
(2, 'احمد محمد', '01127333021', 'العدوة', '12345698745625', 45000, 1),
(3, 'مصطفي يوسف', '01221788873', 'النزل', '28611182800257', 350000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `clientaccount`
--

CREATE TABLE `clientaccount` (
  `id_ClientAccount` int(11) NOT NULL,
  `date_ClientAccount` date DEFAULT NULL,
  `Debit` double(10,2) DEFAULT NULL,
  `Creditor` double(10,2) DEFAULT NULL,
  `id_client` int(11) DEFAULT NULL,
  `id_salesInvoic` int(11) DEFAULT NULL,
  `id_Receipt` int(11) DEFAULT NULL,
  `note` text DEFAULT NULL,
  `isActive` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `clientaccount`
--

INSERT INTO `clientaccount` (`id_ClientAccount`, `date_ClientAccount`, `Debit`, `Creditor`, `id_client`, `id_salesInvoic`, `id_Receipt`, `note`, `isActive`) VALUES
(1, '2022-06-01', 117250.00, NULL, 2, 2, NULL, 'فاتورة مبيعات رقم  2 - دفعة من الحساب - احمد محمد', 1);

-- --------------------------------------------------------

--
-- Stand-in structure for view `debitandcreditorclient`
-- (See below for the actual view)
--
CREATE TABLE `debitandcreditorclient` (
`date_ClientAccount` date
,`Debit` double(10,2)
,`Creditor` double(10,2)
,`id_client` int(11)
,`name_client` varchar(512)
,`id_salesInvoic` int(11)
,`id_Receipt` int(11)
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
,`id_Suppliers` int(11)
,`name_Suppliers` varchar(512)
,`id_purchaseInvoice` int(11)
,`id_paymentReceipt` int(11)
,`note` text
);

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
-- Table structure for table `itemsonpurchaseinvoice`
--

CREATE TABLE `itemsonpurchaseinvoice` (
  `id` int(11) DEFAULT NULL,
  `id_items` int(11) DEFAULT NULL,
  `name_items` varchar(512) DEFAULT NULL,
  `qyt` double(10,2) DEFAULT NULL,
  `id_unit` int(11) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `discount` double(10,2) DEFAULT NULL,
  `Amount` double(10,2) DEFAULT NULL,
  `id_purchaseInvoice` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `itemsonsalesinvoice`
--

INSERT INTO `itemsonsalesinvoice` (`id`, `id_items`, `name_items`, `qyt`, `id_unit`, `price`, `discount`, `Amount`, `id_salesInvoic`) VALUES
(1, 1, 'زئبق', 1.00, 1, 117250.00, 0.00, 117250.00, 1),
(1, 1, 'زئبق', 1.00, 1, 117250.00, 0.00, 117250.00, 2);

-- --------------------------------------------------------

--
-- Table structure for table `paymentreceipt`
--

CREATE TABLE `paymentreceipt` (
  `id_PaymentReceipt` int(11) NOT NULL,
  `date_PaymentReceipt` date DEFAULT NULL,
  `amount` double(10,2) DEFAULT NULL,
  `id_Suppliers` int(11) DEFAULT NULL,
  `isActive` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `priselist`
--

CREATE TABLE `priselist` (
  `id_priceList` int(11) NOT NULL,
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

INSERT INTO `priselist` (`id_priceList`, `last_edit`, `purchase_price_low`, `purchase_price_high`, `sale_price_low`, `sale_price_high`, `id_items`) VALUES
(1, '2022-05-27', 3000.00, 100500.00, 3500.00, 117250.00, 1),
(2, '2022-05-27', 0.00, 0.00, 500.00, 16750.00, 1);

-- --------------------------------------------------------

--
-- Table structure for table `purchaseinvoice`
--

CREATE TABLE `purchaseinvoice` (
  `id_purchaseInvoice` int(11) NOT NULL,
  `date_purchaseInvoice` date DEFAULT NULL,
  `type_purchaseInvoic` varchar(512) DEFAULT NULL,
  `id_Suppliers` int(11) DEFAULT NULL,
  `totalAmount` double(10,2) DEFAULT NULL,
  `discount` double(10,2) DEFAULT NULL,
  `amountCash` double(10,2) DEFAULT NULL,
  `amountLater` double(10,2) DEFAULT NULL,
  `note` text DEFAULT NULL,
  `isActive` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `receipt`
--

CREATE TABLE `receipt` (
  `id_Receipt` int(11) NOT NULL,
  `date_Receipt` date DEFAULT NULL,
  `amount` double(10,2) DEFAULT NULL,
  `id_client` int(11) DEFAULT NULL,
  `isActive` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `note` text DEFAULT NULL,
  `isActive` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `salesinvoic`
--

INSERT INTO `salesinvoic` (`id_salesInvoic`, `date_salesInvoic`, `type_salesInvoic`, `id_client`, `totalAmount`, `discount`, `amountCash`, `amountLater`, `note`, `isActive`) VALUES
(1, '2022-06-01', 'كاش', 1, 117250.00, 0.00, 117250.00, 0.00, 'فاتورة مبيعات نقدية رقم  1', 1),
(2, '2022-06-01', 'دفعة من الحساب', 2, 117250.00, 0.00, 0.00, 117250.00, 'فاتورة مبيعات رقم  2 - دفعة من الحساب - احمد محمد', 1);

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
  `firstBalance` double(10,2) DEFAULT NULL,
  `isActive` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `suppliers`
--

INSERT INTO `suppliers` (`id_Suppliers`, `name_Suppliers`, `phone`, `address`, `idNational`, `firstBalance`, `isActive`) VALUES
(1, 'مورد نقدي', NULL, NULL, NULL, NULL, 1),
(2, 'ممدوح الزين', '0112725232', 'القاهرة', '25611182800548', 50000.00, 1),
(3, 'محمد خليل', '0100000000', 'الجيزة', NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `suppliersaccount`
--

CREATE TABLE `suppliersaccount` (
  `id_supplliersAccount` int(11) NOT NULL,
  `date_suppliersAccount` date DEFAULT NULL,
  `Debit` double(10,2) DEFAULT NULL,
  `Creditor` double(10,2) DEFAULT NULL,
  `id_Suppliers` int(11) DEFAULT NULL,
  `id_purchaseInvoice` int(11) DEFAULT NULL,
  `id_paymentReceipt` int(11) DEFAULT NULL,
  `note` text DEFAULT NULL,
  `isActive` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
-- Structure for view `debitandcreditorclient`
--
DROP TABLE IF EXISTS `debitandcreditorclient`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `debitandcreditorclient`  AS  select `ac`.`date_ClientAccount` AS `date_ClientAccount`,`ac`.`Debit` AS `Debit`,`ac`.`Creditor` AS `Creditor`,`ac`.`id_client` AS `id_client`,`c`.`name_client` AS `name_client`,`ac`.`id_salesInvoic` AS `id_salesInvoic`,`ac`.`id_Receipt` AS `id_Receipt`,`ac`.`note` AS `note` from (`clientaccount` `ac` join `client` `c` on(`ac`.`id_client` = `c`.`id_client`)) where `ac`.`isActive` = 1 ;

-- --------------------------------------------------------

--
-- Structure for view `debitandcreditorsupplier`
--
DROP TABLE IF EXISTS `debitandcreditorsupplier`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `debitandcreditorsupplier`  AS  select `ac`.`date_suppliersAccount` AS `date_suppliersAccount`,`ac`.`Debit` AS `Debit`,`ac`.`Creditor` AS `Creditor`,`ac`.`id_Suppliers` AS `id_Suppliers`,`sup`.`name_Suppliers` AS `name_Suppliers`,`ac`.`id_purchaseInvoice` AS `id_purchaseInvoice`,`ac`.`id_paymentReceipt` AS `id_paymentReceipt`,`ac`.`note` AS `note` from (`suppliersaccount` `ac` join `suppliers` `sup` on(`ac`.`id_Suppliers` = `sup`.`id_Suppliers`)) where `ac`.`isActive` = 1 ;

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
  ADD KEY `id_unit` (`id_unit`),
  ADD KEY `id_purchaseInvoice` (`id_purchaseInvoice`);

--
-- Indexes for table `itemsonsalesinvoice`
--
ALTER TABLE `itemsonsalesinvoice`
  ADD KEY `id_items` (`id_items`),
  ADD KEY `id_unit` (`id_unit`),
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
  MODIFY `id_casher` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `clientaccount`
--
ALTER TABLE `clientaccount`
  MODIFY `id_ClientAccount` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `purchaseinvoice`
--
ALTER TABLE `purchaseinvoice`
  MODIFY `id_purchaseInvoice` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `suppliersaccount`
--
ALTER TABLE `suppliersaccount`
  MODIFY `id_supplliersAccount` int(11) NOT NULL AUTO_INCREMENT;

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
  ADD CONSTRAINT `itemsonpurchaseinvoice_ibfk_2` FOREIGN KEY (`id_unit`) REFERENCES `unit` (`id`),
  ADD CONSTRAINT `itemsonpurchaseinvoice_ibfk_3` FOREIGN KEY (`id_purchaseInvoice`) REFERENCES `purchaseinvoice` (`id_purchaseInvoice`);

--
-- Constraints for table `itemsonsalesinvoice`
--
ALTER TABLE `itemsonsalesinvoice`
  ADD CONSTRAINT `itemsonsalesinvoice_ibfk_1` FOREIGN KEY (`id_items`) REFERENCES `items` (`id_items`),
  ADD CONSTRAINT `itemsonsalesinvoice_ibfk_2` FOREIGN KEY (`id_unit`) REFERENCES `unit` (`id`),
  ADD CONSTRAINT `itemsonsalesinvoice_ibfk_3` FOREIGN KEY (`id_salesInvoic`) REFERENCES `salesinvoic` (`id_salesInvoic`);

--
-- Constraints for table `paymentreceipt`
--
ALTER TABLE `paymentreceipt`
  ADD CONSTRAINT `paymentreceipt_ibfk_1` FOREIGN KEY (`id_Suppliers`) REFERENCES `suppliers` (`id_Suppliers`);

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
