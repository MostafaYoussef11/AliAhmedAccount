-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 03, 2022 at 12:24 AM
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
-- Database: `accountmasrawy`
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
  `date_casher` date NOT NULL DEFAULT curdate(),
  `Debit` double(10,2) DEFAULT NULL,
  `Creditor` double(10,2) DEFAULT NULL,
  `note` text DEFAULT NULL,
  `id_purchaseInvoice` bigint(255) DEFAULT NULL,
  `id_Receipt` bigint(255) DEFAULT NULL,
  `id_salesInvoic` bigint(255) DEFAULT NULL,
  `id_PaymentReceipt` bigint(255) DEFAULT NULL,
  `id_masary_pay` int(11) DEFAULT NULL,
  `id_masary_sell` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `casher`
--

INSERT INTO `casher` (`id_casher`, `date_casher`, `Debit`, `Creditor`, `note`, `id_purchaseInvoice`, `id_Receipt`, `id_salesInvoic`, `id_PaymentReceipt`, `id_masary_pay`, `id_masary_sell`) VALUES
(21, '2022-06-19', NULL, 10695.00, 'فاتورة مشتريات رقم  1', 1, NULL, NULL, NULL, NULL, NULL),
(41, '2022-06-19', 75.00, NULL, 'فاتورة مبيعات نقدية1', NULL, NULL, 1, NULL, NULL, NULL),
(42, '2022-06-19', 75.00, NULL, 'فاتورة مبيعات نقدية2', NULL, NULL, 2, NULL, NULL, NULL),
(43, '2022-06-19', 75.00, NULL, 'فاتورة مبيعات نقدية3', NULL, NULL, 3, NULL, NULL, NULL),
(44, '2022-06-21', 50.00, NULL, 'فاتورة مبيعات نقدية4', NULL, NULL, 4, NULL, NULL, NULL),
(45, '2022-06-21', 50.00, NULL, 'فاتورة مبيعات نقدية5', NULL, NULL, 5, NULL, NULL, NULL),
(46, '2022-06-22', 70.00, NULL, 'فاتورة مبيعات نقدية6', NULL, NULL, 6, NULL, NULL, NULL),
(47, '2022-06-22', 25.00, NULL, 'فاتورة مبيعات نقدية7', NULL, NULL, 7, NULL, NULL, NULL),
(48, '2022-06-22', 25.00, NULL, 'فاتورة مبيعات نقدية8', NULL, NULL, 8, NULL, NULL, NULL),
(49, '2022-06-22', 25.00, NULL, 'فاتورة مبيعات نقدية9', NULL, NULL, 9, NULL, NULL, NULL),
(50, '2022-06-22', 25.00, NULL, 'فاتورة مبيعات نقدية10', NULL, NULL, 10, NULL, NULL, NULL),
(51, '2022-06-22', 25.00, NULL, 'فاتورة مبيعات نقدية11', NULL, NULL, 11, NULL, NULL, NULL),
(52, '2022-06-22', 30.00, NULL, 'فاتورة مبيعات نقدية12', NULL, NULL, 12, NULL, NULL, NULL),
(53, '2022-06-22', 40.00, NULL, 'فاتورة مبيعات نقدية13', NULL, NULL, 13, NULL, NULL, NULL),
(54, '2022-06-22', 40.00, NULL, 'فاتورة مبيعات نقدية14', NULL, NULL, 14, NULL, NULL, NULL),
(55, '2022-06-26', 10.00, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL),
(56, '2022-06-26', 10.00, NULL, 'كرت اتصالات 10', NULL, NULL, NULL, NULL, 12, NULL),
(57, '2022-06-26', 10.00, NULL, 'كرت اتصالات 10', NULL, NULL, NULL, NULL, 13, NULL),
(58, '2022-06-26', 10.00, NULL, 'كرت اتصالات 10', NULL, NULL, NULL, NULL, 14, NULL),
(59, '2022-06-26', 15.00, NULL, 'شحن اتصالات', NULL, NULL, NULL, NULL, 15, NULL),
(60, '2022-06-26', 150.00, NULL, 'شحن اتصالات', NULL, NULL, NULL, NULL, 16, NULL),
(61, '2022-06-26', 100.00, NULL, 'فاتورة مبيعات نقدية15', NULL, NULL, 15, NULL, NULL, NULL),
(62, '2022-06-26', 60.00, NULL, 'فاتورة مبيعات نقدية16', NULL, NULL, 16, NULL, NULL, NULL),
(63, '2022-06-26', 50.00, NULL, 'فاتورة مبيعات نقدية17', NULL, NULL, 17, NULL, NULL, NULL),
(64, '2022-06-27', 100.00, NULL, 'فاتورة مبيعات نقدية18', NULL, NULL, 18, NULL, NULL, NULL),
(65, '2022-06-27', 22.00, NULL, '10', NULL, NULL, NULL, NULL, 17, NULL),
(66, '2022-06-27', 26.00, NULL, '25', NULL, NULL, NULL, NULL, 18, NULL),
(67, '2022-06-27', 15.00, NULL, '13.5', NULL, NULL, NULL, NULL, 19, NULL),
(68, '2022-06-27', 15.00, NULL, '01127333021', NULL, NULL, NULL, NULL, 20, NULL),
(69, '2022-06-27', 15.00, NULL, '13.5', NULL, NULL, NULL, NULL, 21, NULL),
(70, '2022-06-27', 75.00, NULL, '01127333021', NULL, NULL, NULL, NULL, 22, NULL),
(71, '2022-06-27', 120.00, NULL, '0112543658', NULL, NULL, NULL, NULL, 23, NULL),
(72, '2022-06-27', 52.50, NULL, '01237525212', NULL, NULL, NULL, NULL, 24, NULL),
(73, '2022-06-28', 3.00, NULL, '2.5', NULL, NULL, NULL, NULL, 25, NULL),
(74, '2022-06-28', 3.00, NULL, '2.5', NULL, NULL, NULL, NULL, 26, NULL),
(75, '2022-06-28', 26.00, NULL, '25', NULL, NULL, NULL, NULL, 27, NULL),
(76, '2022-06-28', 16.50, NULL, '15', NULL, NULL, NULL, NULL, 28, NULL),
(77, '2022-06-28', 52.00, NULL, 'LE 50', NULL, NULL, NULL, NULL, 29, NULL),
(78, '2022-06-28', NULL, 700.00, 'ايصال دفع رقم 1', NULL, NULL, NULL, 1, NULL, NULL),
(79, '2022-06-28', 11.00, NULL, '10', NULL, NULL, NULL, NULL, 30, NULL),
(80, '2022-06-29', NULL, 1000.00, 'شحن ماكينةمصاري', NULL, NULL, NULL, NULL, NULL, 5),
(81, '2022-06-29', 30.00, NULL, 'حكاية 25', NULL, NULL, NULL, NULL, 31, NULL),
(82, '2022-06-29', 1500.00, NULL, '01127333021', NULL, NULL, NULL, NULL, 32, NULL),
(83, '2022-06-29', 11.00, NULL, 'كرت  _ 10', NULL, NULL, NULL, NULL, 33, NULL),
(84, '2022-06-29', NULL, 1000.00, 'شحن ماكينة Bee', NULL, NULL, NULL, NULL, NULL, 11),
(85, '2022-06-29', 15.00, NULL, '01127333021', NULL, NULL, NULL, NULL, 34, NULL),
(86, '2022-06-29', NULL, 1000.00, 'شحن ماكينة Fawry', NULL, NULL, NULL, NULL, NULL, 12),
(87, '2022-06-29', 1100.00, NULL, 'كرت _ 10', NULL, NULL, NULL, NULL, 35, NULL),
(88, '2022-06-29', NULL, 500.00, 'شحن ماكينة Fawry', NULL, NULL, NULL, NULL, NULL, 13),
(89, '2022-06-29', 11.00, NULL, 'كرت _ 10', NULL, NULL, NULL, NULL, 36, NULL),
(90, '2022-06-29', NULL, 0.00, 'شحن ماكينة أمان', NULL, NULL, NULL, NULL, NULL, 14),
(91, '2022-06-29', 110.00, NULL, 'كرت _ 10', NULL, NULL, NULL, NULL, 37, NULL),
(92, '2022-06-29', NULL, 500.00, 'شحن ماكينة أمان', NULL, NULL, NULL, NULL, NULL, 15),
(93, '2022-06-29', 110.00, NULL, 'كرت _ 10', NULL, NULL, NULL, NULL, 38, NULL),
(94, '2022-06-29', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 39, NULL),
(95, '2022-06-29', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 40, NULL),
(96, '2022-06-29', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 41, NULL),
(97, '2022-06-29', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 42, NULL),
(98, '2022-06-29', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 43, NULL),
(99, '2022-06-29', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 44, NULL),
(100, '2022-06-29', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 45, NULL),
(101, '2022-06-29', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 46, NULL),
(102, '2022-06-29', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 47, NULL),
(103, '2022-06-29', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 48, NULL),
(104, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 49, NULL),
(105, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 50, NULL),
(106, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 51, NULL),
(107, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 52, NULL),
(108, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 53, NULL),
(109, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 54, NULL),
(110, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 55, NULL),
(111, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 56, NULL),
(112, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 57, NULL),
(113, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 58, NULL),
(114, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 59, NULL),
(115, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 60, NULL),
(116, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 61, NULL),
(117, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 62, NULL),
(118, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 63, NULL),
(119, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 64, NULL),
(120, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 65, NULL),
(121, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 66, NULL),
(122, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 67, NULL),
(123, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 68, NULL),
(124, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 69, NULL),
(125, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 70, NULL),
(126, '2022-06-30', 11.00, NULL, '10', NULL, NULL, NULL, NULL, 71, NULL),
(127, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 72, NULL),
(128, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 73, NULL),
(129, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 74, NULL),
(130, '2022-06-30', NULL, 20000.00, 'ايصال دفع رقم 2', NULL, NULL, NULL, 2, NULL, NULL),
(131, '2022-06-30', 150.00, NULL, '', NULL, NULL, NULL, NULL, 75, NULL),
(132, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 76, NULL),
(133, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 77, NULL),
(134, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 78, NULL),
(135, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 79, NULL),
(136, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 80, NULL),
(137, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 81, NULL),
(138, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 82, NULL),
(139, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 83, NULL),
(140, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 84, NULL),
(141, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 85, NULL),
(142, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 86, NULL),
(143, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 87, NULL),
(144, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 88, NULL),
(145, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 89, NULL),
(146, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 90, NULL),
(147, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 91, NULL),
(148, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 92, NULL),
(149, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 93, NULL),
(150, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 94, NULL),
(151, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 95, NULL),
(152, '2022-06-30', 15000.00, NULL, '', NULL, NULL, NULL, NULL, 96, NULL),
(153, '2022-06-30', 7500.00, NULL, '', NULL, NULL, NULL, NULL, 97, NULL),
(154, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 98, NULL),
(155, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 99, NULL),
(156, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 100, NULL),
(157, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 101, NULL),
(158, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 102, NULL),
(159, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 103, NULL),
(160, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 104, NULL),
(161, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 105, NULL),
(162, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 106, NULL),
(163, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 107, NULL),
(164, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 108, NULL),
(165, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 109, NULL),
(166, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 110, NULL),
(167, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 111, NULL),
(168, '2022-06-30', 10.00, NULL, '10', NULL, NULL, NULL, NULL, 112, NULL),
(169, '2022-07-01', 15.00, NULL, '01127333021', NULL, NULL, NULL, NULL, 113, NULL),
(170, '2022-07-01', 1.50, NULL, '01063734632', NULL, NULL, NULL, NULL, 114, NULL);

--
-- Triggers `casher`
--
DELIMITER $$
CREATE TRIGGER `ubdate_balance_casher` AFTER INSERT ON `casher` FOR EACH ROW BEGIN
     DECLARE oldbalance DOUBLE ;
    DECLARE totalDebit double ;
    DECLARE totalCredit double;
    -- DECLARE newbalance DOUBLE ;
        -- set totalDebit
    SELECT
        SUM(casher.Debit)
    INTO @totalDebit
FROM
    casher
WHERE
    casher.date_casher = CURRENT_DATE() ;
    -- set new balance
  SELECT Sum(casher.Creditor) INTO @totalCredit FROM casher WHERE casher.date_casher =CURRENT_DATE();
SELECT finallyday.oldBalance INTO @oldbalance FROM finallyday WHERE finallyday.dateFinally = CURRENT_DATE();
UPDATE
    finallyday
SET
   finallyday.totalimport = @totalDebit , finallyday.totalexport = @totalCredit , finallyday.newbalance = (@oldbalance + @totalDebit - @totalCredit) WHERE finallyday.dateFinally = CURRENT_DATE() ;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `categoryutilites`
--

CREATE TABLE `categoryutilites` (
  `id_category` int(11) NOT NULL,
  `name_category` varchar(512) DEFAULT NULL,
  `requier_phone` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `categoryutilites`
--

INSERT INTO `categoryutilites` (`id_category`, `name_category`, `requier_phone`) VALUES
(1, 'كروت اتصالات', 0),
(2, 'كروت اورنج', 0),
(3, 'كروت WE', 0),
(4, 'كروت فودافون', 0),
(9, 'اتصالات حكاية', 0),
(10, 'شحن', 1);

-- --------------------------------------------------------

--
-- Table structure for table `charagewallet`
--

CREATE TABLE `charagewallet` (
  `id_charageWallet` int(11) NOT NULL,
  `date_charageWallet` date DEFAULT curdate(),
  `time_charageWallet` time DEFAULT curtime(),
  `id_VF_Cash` int(11) NOT NULL,
  `amount` double(10,2) NOT NULL,
  `id_pos` int(11) NOT NULL,
  `balance_befor` double(10,2) DEFAULT NULL,
  `balance_after` double(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
(1, 'عميل نقدي', NULL, NULL, NULL, 0, 1);

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
  `id_masary_pay` int(11) DEFAULT NULL,
  `note` text DEFAULT NULL,
  `isActive` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
-- Table structure for table `finallyday`
--

CREATE TABLE `finallyday` (
  `dateFinally` date NOT NULL,
  `oldBalance` double NOT NULL,
  `totalimport` double NOT NULL,
  `totalexport` double NOT NULL,
  `newbalance` double(10,2) GENERATED ALWAYS AS (`oldBalance` + `totalimport` - `totalexport`) VIRTUAL,
  `time_insert` time NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `finallyday`
--

INSERT INTO `finallyday` (`dateFinally`, `oldBalance`, `totalimport`, `totalexport`, `time_insert`) VALUES
('2022-06-18', 11000, 0, 0, '02:11:15'),
('2022-06-19', 11000, 225, 10695, '02:11:15'),
('2022-06-20', 530, 0, 0, '02:11:15'),
('2022-06-21', 530, 100, 0, '02:11:15'),
('2022-06-22', 630, 305, 0, '02:11:15'),
('2022-06-23', 935, 0, 0, '02:11:15'),
('2022-06-24', 935, 0, 0, '02:11:15'),
('2022-06-25', 935, 0, 0, '02:11:15'),
('2022-06-26', 935, 415, 0, '02:11:15'),
('2022-06-27', 1350, 440.5, 0, '02:11:15'),
('2022-06-28', 1790.5, 111.5, 700, '02:11:15'),
('2022-06-29', 1202, 2987, 4000, '02:18:11'),
('2022-06-30', 189, 23261, 20000, '00:00:00'),
('2022-07-01', 3450, 16.5, 0, '00:00:00'),
('2022-07-02', 3466.5, 0, 0, '21:43:15'),
('2022-07-03', 3466.5, 0, 0, '00:00:00');

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
(2018014204206, 'كبل رجال الاعمال', 0, 1),
(4414136520176, 'سماعة شعبي D21', 0, 1),
(4416336520178, 'سماعة شعبي D21_2', 0, 1),
(6875422190370, 'سماعة ستار - St 666', 0, 1),
(6905112458258, 'شاحن ستار ST-037', 0, 1),
(6921042107615, 'كبل ميكرو ikaku KSC-037', 0, 1),
(6921042111322, 'كبل ميكرو KaKu', 0, 1),
(6922154678772, 'ST-031 شاحن ستار ', 0, 1),
(6922252566452, 'ST - 038 شاحن ستار ', 0, 1),
(6933138622018, 'شاحن ميكرو ليدنيو ', 0, 1),
(6944561153543, 'كبل ستار ميكرو A02', 0, 1),
(6949810502219, 'كبل ستار L21A', 0, 1),
(6949810504466, 'شاحن سيارة فاست', 0, 1),
(6949810510177, 'شاحن سيارة Smart 3.0', 0, 1),
(6949810520954, 'شاحن ستار ميكرو - 777', 0, 1),
(6949810520961, 'شاحن ستار تايب سي  - 777', 0, 1),
(6956116774684, 'كبل Joyroom s-L352', 0, 1),
(6965468450061, 'سماعة JY-325', 0, 1),
(6970462516446, 'كبل ميكرو Foneng', 0, 1),
(6971410555944, 'شاحن Earldom 2.1', 0, 1),
(6973224870053, 'كبل DenMen  تايب سي', 0, 1),
(6973224870305, 'شاحن DENMEN ميكرو', 0, 1),
(6973224870381, 'شاحن DENMEN تايب سي', 0, 1),
(6973224871319, 'كبل D15T تايب سي', 0, 1),
(6975488921007, 'سماعة ستار - ST555', 0, 1),
(6999881512181, 'سماعة سامسنج عضم ', 0, 1),
(7720190427021, 'سماعة ستار R4', 0, 1),
(8434350001004, 'سماعة DP-H01', 0, 1),
(9988854278909, 'كبل ستار 015', 0, 1);

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
(1, 8434350001004, 'سماعة DP-H01', 50.00, 'قطعة', 13.50, 0.00, 675.00, 1),
(2, 7720190427021, 'سماعة ستار R4', 50.00, 'قطعة', 21.50, 0.00, 1075.00, 1),
(3, 6999881512181, 'سماعة سامسنج عضم ', 50.00, 'قطعة', 18.00, 0.00, 900.00, 1),
(4, 9988854278909, 'كبل ستار 015', 25.00, 'قطعة', 17.00, 0.00, 425.00, 1),
(5, 6922252566452, 'ST - 038 شاحن ستار ', 30.00, 'قطعة', 30.00, 0.00, 900.00, 1),
(6, 6922154678772, 'ST-031 شاحن ستار ', 30.00, 'قطعة', 35.00, 0.00, 1050.00, 1),
(7, 6944561153543, 'كبل ستار ميكرو A02', 20.00, 'قطعة', 19.00, 0.00, 380.00, 1),
(8, 4416336520178, 'سماعة شعبي D21_2', 30.00, 'قطعة', 14.00, 0.00, 420.00, 1),
(9, 6949810504466, 'شاحن سيارة فاست', 30.00, 'قطعة', 25.00, 0.00, 750.00, 1),
(10, 6922154678772, 'ST-031 شاحن ستار ', 20.00, 'قطعة', 35.00, 0.00, 700.00, 1),
(11, 6949810520954, 'شاحن ستار ميكرو - 777', 30.00, 'قطعة', 37.00, 0.00, 1110.00, 1),
(12, 6949810520961, 'شاحن ستار تايب سي  - 777', 20.00, 'قطعة', 39.00, 0.00, 780.00, 1),
(13, 6975488921007, 'سماعة ستار - ST555', 10.00, 'قطعة', 30.00, 0.00, 300.00, 1),
(14, 6875422190370, 'سماعة ستار - St 666', 10.00, 'قطعة', 38.00, 0.00, 380.00, 1),
(15, 4414136520176, 'سماعة شعبي D21', 30.00, 'قطعة', 11.00, 0.00, 330.00, 1),
(16, 6949810502219, 'كبل ستار L21A', 20.00, 'قطعة', 11.00, 0.00, 220.00, 1),
(17, 6965468450061, 'سماعة JY-325', 20.00, 'قطعة', 15.00, 0.00, 300.00, 1);

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

--
-- Dumping data for table `itemsonsalesinvoice`
--

INSERT INTO `itemsonsalesinvoice` (`id`, `id_items`, `name_items`, `qyt`, `name_unit`, `price`, `discount`, `Amount`, `id_salesInvoic`) VALUES
(1, 6975488921007, 'سماعة ستار - ST555', 1.00, 'قطعة', 75.00, 0.00, 75.00, 1),
(1, 6971410555944, 'شاحن Earldom 2.1', 1.00, 'قطعة', 75.00, 0.00, 75.00, 2),
(1, 6971410555944, 'شاحن Earldom 2.1', 1.00, 'قطعة', 75.00, 0.00, 75.00, 3),
(1, 6949810504466, 'شاحن سيارة فاست', 1.00, 'قطعة', 50.00, 0.00, 50.00, 4),
(1, 6949810504466, 'شاحن سيارة فاست', 1.00, 'قطعة', 50.00, 0.00, 50.00, 5),
(1, 6922154678772, 'ST-031 شاحن ستار ', 1.00, 'قطعة', 70.00, 0.00, 70.00, 6),
(1, 6949810502219, 'كبل ستار L21A', 1.00, 'قطعة', 25.00, 0.00, 25.00, 7),
(1, 6949810502219, 'كبل ستار L21A', 1.00, 'قطعة', 25.00, 0.00, 25.00, 8),
(1, 6949810502219, 'كبل ستار L21A', 1.00, 'قطعة', 25.00, 0.00, 25.00, 9),
(1, 6949810502219, 'كبل ستار L21A', 1.00, 'قطعة', 25.00, 0.00, 25.00, 10),
(1, 6949810502219, 'كبل ستار L21A', 1.00, 'قطعة', 25.00, 0.00, 25.00, 11),
(1, 6921042111322, 'كبل ميكرو KaKu', 1.00, 'قطعة', 30.00, 0.00, 30.00, 12),
(1, 6944561153543, 'كبل ستار ميكرو A02', 1.00, 'قطعة', 40.00, 0.00, 40.00, 13),
(1, 6944561153543, 'كبل ستار ميكرو A02', 1.00, 'قطعة', 40.00, 0.00, 40.00, 14),
(1, 6949810510177, 'شاحن سيارة Smart 3.0', 1.00, 'قطعة', 100.00, 0.00, 100.00, 15),
(1, 6949810510177, 'شاحن سيارة Smart 3.0', 1.00, 'قطعة', 60.00, 0.00, 60.00, 16),
(1, 6949810510177, 'شاحن سيارة Smart 3.0', 1.00, 'قطعة', 50.00, 0.00, 50.00, 17),
(1, 6949810510177, 'شاحن سيارة Smart 3.0', 1.00, 'قطعة', 100.00, 0.00, 100.00, 18);

-- --------------------------------------------------------

--
-- Table structure for table `masary_pay`
--

CREATE TABLE `masary_pay` (
  `id_masary_pay` int(11) NOT NULL,
  `date_masary_pay` date NOT NULL DEFAULT curdate(),
  `time_masary_pay` time DEFAULT curtime(),
  `id_utility_masary` int(11) DEFAULT NULL,
  `price_masary_pay` double(10,2) NOT NULL,
  `id_client` bigint(20) DEFAULT NULL,
  `discount_of_balance` double(10,2) DEFAULT NULL,
  `amount_masary_pay` double(10,2) DEFAULT NULL,
  `id_pos` int(11) NOT NULL DEFAULT 1,
  `balance` double NOT NULL,
  `phone` varchar(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `masary_pay`
--

INSERT INTO `masary_pay` (`id_masary_pay`, `date_masary_pay`, `time_masary_pay`, `id_utility_masary`, `price_masary_pay`, `id_client`, `discount_of_balance`, `amount_masary_pay`, `id_pos`, `balance`, `phone`) VALUES
(17, '2022-06-27', '00:00:00', 39, 2.00, 1, 20.00, 22.00, 1, 10747.9, NULL),
(18, '2022-06-27', '00:00:00', 42, 1.00, 1, 25.00, 26.00, 1, 10722.9, NULL),
(20, '2022-06-27', '00:00:00', NULL, 10.00, 1, 14.30, 15.00, 1, 10695.1, '01127333021'),
(21, '2022-06-27', '00:00:00', 40, 1.00, 1, 13.50, 15.00, 1, 10927.2, '13.5'),
(22, '2022-06-27', '00:00:00', 72, 50.00, 1, 71.50, 75.00, 1, 10855.7, '01127333021'),
(23, '2022-06-27', '00:00:00', 72, 80.00, 1, 114.40, 120.00, 1, 10741.3, '0112543658'),
(24, '2022-06-27', '00:00:00', 72, 35.00, 1, 50.05, 52.50, 1, 10691.25, '01237525212'),
(25, '2022-06-28', '00:00:00', 35, 1.00, 1, 2.50, 3.00, 1, 10688.75, '2.5'),
(26, '2022-06-28', '00:00:00', 35, 1.00, 1, 2.50, 3.00, 1, 10686.25, '2.5'),
(27, '2022-06-28', '00:00:00', 42, 1.00, 1, 25.00, 26.00, 1, 10661.25, '25'),
(28, '2022-06-28', '00:00:00', 41, 1.00, 1, 15.00, 16.50, 1, 10646.25, '15'),
(29, '2022-06-28', '00:00:00', 33, 1.00, 1, 50.00, 52.00, 1, 10596.25, 'LE 50'),
(30, '2022-06-28', '16:19:50', 39, 1.00, 1, 10.00, 11.00, 1, 10586.25, '10'),
(31, '2022-06-29', '17:11:59', 71, 1.00, 1, 25.00, 30.00, 1, 41561.25, 'حكاية 25'),
(32, '2022-06-29', '17:12:20', 72, 1000.00, 1, 1430.00, 1500.00, 1, 40131.25, '01127333021'),
(33, '2022-06-29', '20:28:01', 76, 1.00, 1, 10.00, 11.00, 2, -10, 'كرت  _ 10'),
(34, '2022-06-29', '20:43:49', 73, 10.00, 1, 14.30, 15.00, 2, 975.7, '01127333021'),
(35, '2022-06-29', '21:31:31', 77, 100.00, 1, 1000.00, 1100.00, 3, 0, 'كرت _ 10'),
(36, '2022-06-29', '21:32:41', 77, 1.00, 1, 10.00, 11.00, 3, 490, 'كرت _ 10'),
(37, '2022-06-29', '22:23:52', 79, 10.00, 1, 100.00, 110.00, 5, -100, 'كرت _ 10'),
(38, '2022-06-29', '22:24:54', 79, 10.00, 1, 100.00, 110.00, 5, 300, 'كرت _ 10'),
(39, '2022-06-29', '23:34:08', 39, 10.00, 1, 10.00, 10.00, 1, 40121.25, '10'),
(40, '2022-06-29', '23:34:47', 39, 10.00, 1, 10.00, 10.00, 1, 40111.25, '10'),
(41, '2022-06-29', '23:38:01', 39, 10.00, 1, 10.00, 10.00, 1, 40101.25, '10'),
(42, '2022-06-29', '23:58:47', 39, 10.00, 1, 10.00, 10.00, 1, 40091.25, '10'),
(43, '2022-06-29', '23:59:22', 39, 10.00, 1, 10.00, 10.00, 1, 40081.25, '10'),
(44, '2022-06-29', '23:59:25', 39, 10.00, 1, 10.00, 10.00, 1, 40071.25, '10'),
(45, '2022-06-29', '23:59:27', 39, 10.00, 1, 10.00, 10.00, 1, 40061.25, '10'),
(46, '2022-06-29', '23:59:29', 39, 10.00, 1, 10.00, 10.00, 1, 40051.25, '10'),
(47, '2022-06-29', '23:59:30', 39, 10.00, 1, 10.00, 10.00, 1, 40041.25, '10'),
(48, '2022-06-29', '23:59:32', 39, 10.00, 1, 10.00, 10.00, 1, 40031.25, '10'),
(49, '2022-06-30', '00:01:21', 39, 10.00, 1, 10.00, 10.00, 1, 40021.25, '10'),
(50, '2022-06-30', '00:01:24', 39, 10.00, 1, 10.00, 10.00, 1, 40011.25, '10'),
(51, '2022-06-30', '00:38:48', 39, 10.00, 1, 10.00, 10.00, 1, 40001.25, '10'),
(52, '2022-06-30', '00:39:14', 39, 10.00, 1, 10.00, 10.00, 1, 39991.25, '10'),
(53, '2022-06-30', '00:40:20', 39, 10.00, 1, 10.00, 10.00, 1, 39981.25, '10'),
(54, '2022-06-30', '00:43:54', 39, 10.00, 1, 10.00, 10.00, 1, 39971.25, '10'),
(55, '2022-06-30', '00:44:56', 39, 10.00, 1, 10.00, 10.00, 1, 39961.25, '10'),
(56, '2022-06-30', '00:45:01', 39, 10.00, 1, 10.00, 10.00, 1, 39951.25, '10'),
(57, '2022-06-30', '00:59:13', 39, 10.00, 1, 10.00, 10.00, 1, 39941.25, '10'),
(58, '2022-06-30', '01:11:14', 39, 10.00, 1, 10.00, 10.00, 1, 39931.25, '10'),
(59, '2022-06-30', '02:39:49', 39, 10.00, 1, 10.00, 10.00, 1, 39921.25, '10'),
(60, '2022-06-30', '02:39:52', 39, 10.00, 1, 10.00, 10.00, 1, 39911.25, '10'),
(61, '2022-06-30', '02:39:54', 39, 10.00, 1, 10.00, 10.00, 1, 39901.25, '10'),
(62, '2022-06-30', '02:39:55', 39, 10.00, 1, 10.00, 10.00, 1, 39891.25, '10'),
(63, '2022-06-30', '02:39:56', 39, 10.00, 1, 10.00, 10.00, 1, 39881.25, '10'),
(64, '2022-06-30', '02:39:57', 39, 10.00, 1, 10.00, 10.00, 1, 39871.25, '10'),
(65, '2022-06-30', '02:39:59', 39, 10.00, 1, 10.00, 10.00, 1, 39861.25, '10'),
(66, '2022-06-30', '11:45:36', 39, 10.00, 1, 10.00, 10.00, 1, 39851.25, '10'),
(67, '2022-06-30', '11:45:44', 39, 10.00, 1, 10.00, 10.00, 1, 39841.25, '10'),
(68, '2022-06-30', '11:45:45', 39, 10.00, 1, 10.00, 10.00, 1, 39831.25, '10'),
(69, '2022-06-30', '11:45:47', 39, 10.00, 1, 10.00, 10.00, 1, 39821.25, '10'),
(70, '2022-06-30', '11:45:49', 39, 10.00, 1, 10.00, 10.00, 1, 39811.25, '10'),
(71, '2022-06-30', '11:46:34', 39, 1.00, 1, 10.00, 11.00, 1, 39801.25, '10'),
(72, '2022-06-30', '12:07:37', 39, 10.00, 1, 10.00, 10.00, 1, 39791.25, '10'),
(73, '2022-06-30', '12:10:13', 39, 10.00, 1, 10.00, 10.00, 1, 39781.25, '10'),
(74, '2022-06-30', '13:15:31', 39, 10.00, 1, 10.00, 10.00, 1, 39771.25, '10'),
(75, '2022-06-30', '13:19:38', 73, 100.00, 1, 143.00, 150.00, 2, 832.7, ''),
(76, '2022-06-30', '13:19:48', 39, 10.00, 1, 10.00, 10.00, 1, 39761.25, '10'),
(77, '2022-06-30', '13:25:37', 39, 10.00, 1, 10.00, 10.00, 1, 39751.25, '10'),
(78, '2022-06-30', '13:25:53', 39, 10.00, 1, 10.00, 10.00, 1, 39741.25, '10'),
(79, '2022-06-30', '13:25:53', 39, 10.00, 1, 10.00, 10.00, 1, 39731.25, '10'),
(80, '2022-06-30', '13:31:51', 39, 10.00, 1, 10.00, 10.00, 1, 39721.25, '10'),
(81, '2022-06-30', '13:31:52', 39, 10.00, 1, 10.00, 10.00, 1, 39711.25, '10'),
(82, '2022-06-30', '13:31:53', 39, 10.00, 1, 10.00, 10.00, 1, 39701.25, '10'),
(83, '2022-06-30', '13:31:54', 39, 10.00, 1, 10.00, 10.00, 1, 39691.25, '10'),
(84, '2022-06-30', '13:31:54', 39, 10.00, 1, 10.00, 10.00, 1, 39681.25, '10'),
(85, '2022-06-30', '13:31:54', 39, 10.00, 1, 10.00, 10.00, 1, 39671.25, '10'),
(86, '2022-06-30', '13:31:55', 39, 10.00, 1, 10.00, 10.00, 1, 39661.25, '10'),
(87, '2022-06-30', '13:31:55', 39, 10.00, 1, 10.00, 10.00, 1, 39651.25, '10'),
(88, '2022-06-30', '13:31:55', 39, 10.00, 1, 10.00, 10.00, 1, 39641.25, '10'),
(89, '2022-06-30', '13:31:55', 39, 10.00, 1, 10.00, 10.00, 1, 39631.25, '10'),
(90, '2022-06-30', '13:31:56', 39, 10.00, 1, 10.00, 10.00, 1, 39621.25, '10'),
(91, '2022-06-30', '13:31:56', 39, 10.00, 1, 10.00, 10.00, 1, 39611.25, '10'),
(92, '2022-06-30', '13:31:56', 39, 10.00, 1, 10.00, 10.00, 1, 39601.25, '10'),
(93, '2022-06-30', '13:31:57', 39, 10.00, 1, 10.00, 10.00, 1, 39591.25, '10'),
(94, '2022-06-30', '13:31:57', 39, 10.00, 1, 10.00, 10.00, 1, 39581.25, '10'),
(95, '2022-06-30', '13:31:57', 39, 10.00, 1, 10.00, 10.00, 1, 39571.25, '10'),
(96, '2022-06-30', '13:32:47', 73, 10000.00, 1, 14300.00, 15000.00, 1, 25271.25, ''),
(97, '2022-06-30', '13:33:18', 73, 5000.00, 1, 7150.00, 7500.00, 1, 18121.25, ''),
(98, '2022-06-30', '13:33:31', 39, 10.00, 1, 10.00, 10.00, 1, 18111.25, '10'),
(99, '2022-06-30', '13:33:32', 39, 10.00, 1, 10.00, 10.00, 1, 18101.25, '10'),
(100, '2022-06-30', '13:33:35', 39, 10.00, 1, 10.00, 10.00, 1, 18091.25, '10'),
(101, '2022-06-30', '13:34:32', 39, 10.00, 1, 10.00, 10.00, 1, 18081.25, '10'),
(102, '2022-06-30', '13:35:01', 39, 10.00, 1, 10.00, 10.00, 1, 18071.25, '10'),
(103, '2022-06-30', '13:35:03', 39, 10.00, 1, 10.00, 10.00, 1, 18061.25, '10'),
(104, '2022-06-30', '13:35:04', 39, 10.00, 1, 10.00, 10.00, 1, 18051.25, '10'),
(105, '2022-06-30', '13:35:05', 39, 10.00, 1, 10.00, 10.00, 1, 18041.25, '10'),
(106, '2022-06-30', '13:35:06', 39, 10.00, 1, 10.00, 10.00, 1, 18031.25, '10'),
(107, '2022-06-30', '13:35:07', 39, 10.00, 1, 10.00, 10.00, 1, 18021.25, '10'),
(108, '2022-06-30', '13:35:08', 39, 10.00, 1, 10.00, 10.00, 1, 18011.25, '10'),
(109, '2022-06-30', '13:35:08', 39, 10.00, 1, 10.00, 10.00, 1, 18001.25, '10'),
(110, '2022-06-30', '13:35:09', 39, 10.00, 1, 10.00, 10.00, 1, 17991.25, '10'),
(111, '2022-06-30', '13:35:10', 39, 10.00, 1, 10.00, 10.00, 1, 17981.25, '10'),
(112, '2022-06-30', '14:43:06', 39, 10.00, 1, 10.00, 10.00, 1, 17971.25, '10'),
(113, '2022-07-01', '16:24:47', 73, 10.00, 1, 14.30, 15.00, 1, 17956.95, '01127333021'),
(114, '2022-07-01', '16:30:21', 73, 1.00, 1, 1.43, 1.50, 1, 17955.52, '01063734632');

-- --------------------------------------------------------

--
-- Table structure for table `masary_sell`
--

CREATE TABLE `masary_sell` (
  `id_masary_sell` int(11) NOT NULL,
  `date_masary_sell` date DEFAULT curdate(),
  `time_masary_sell` time DEFAULT curtime(),
  `value_masary_sell` double(10,2) DEFAULT 0.00,
  `amount_masary_sell` double(10,2) DEFAULT NULL,
  `id_recharge_type` int(11) DEFAULT NULL,
  `id_Suppliers` bigint(20) DEFAULT NULL,
  `id_VF_cash` int(11) DEFAULT NULL,
  `id_pos` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `masary_sell`
--

INSERT INTO `masary_sell` (`id_masary_sell`, `date_masary_sell`, `time_masary_sell`, `value_masary_sell`, `amount_masary_sell`, `id_recharge_type`, `id_Suppliers`, `id_VF_cash`, `id_pos`) VALUES
(1, '2022-06-24', '00:00:00', 0.00, 1000.00, 1, 1, NULL, 1),
(2, '2022-06-26', '00:00:00', 0.00, 10000.00, 1, 1, NULL, 1),
(5, '2022-06-29', '15:08:14', 1000.00, 1000.00, 1, 1, NULL, 1),
(9, '2022-06-29', '15:20:20', 30000.00, 30000.00, 2, 3, NULL, 1),
(10, '2022-06-29', '19:20:57', 500.00, 0.00, 2, 4, NULL, 2),
(11, '2022-06-29', '20:43:15', 1000.00, 1000.00, 1, 1, NULL, 2),
(12, '2022-06-29', '21:27:15', 1000.00, 1000.00, 1, 1, NULL, 3),
(13, '2022-06-29', '21:32:30', 500.00, 500.00, 1, 1, NULL, 3),
(14, '2022-06-29', '22:22:38', 5000.00, 0.00, 1, 1, NULL, 5),
(15, '2022-06-29', '22:24:20', 500.00, 500.00, 1, 1, NULL, 5);

--
-- Triggers `masary_sell`
--
DELIMITER $$
CREATE TRIGGER `upDate_balance` AFTER INSERT ON `masary_sell` FOR EACH ROW BEGIN
    DECLARE
        oldbalance DOUBLE ; DECLARE newbalance DOUBLE ;
        -- set old balance
    SELECT
        masary_pay.balance
    INTO @oldbalance
FROM
    masary_pay
WHERE
    masary_pay.id_masary_pay = (SELECT MAX(mpay.id_masary_pay) FROM masary_pay AS mpay)  ;
    -- set new balance
    IF @oldbalance != NULL THEN
UPDATE
    masary_pay
SET
    masary_pay.balance = @oldbalance +(
    SELECT
        masary_sell.value_masary_sell
    FROM
        masary_sell AS sell
    WHERE
        sell.id_masary_sell = (SELECT MAX(sell2.id_masary_sell) FROM masary_sell AS sell2)
) ;
END IF ;
END
$$
DELIMITER ;

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
(1, '2022-06-28', 700.00, 2, 1),
(2, '2022-06-30', 20000.00, 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `phone_numbers`
--

CREATE TABLE `phone_numbers` (
  `numbers` varchar(14) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `phone_numbers`
--

INSERT INTO `phone_numbers` (`numbers`) VALUES
('st01063734632'),
('st01127333021');

-- --------------------------------------------------------

--
-- Table structure for table `pos`
--

CREATE TABLE `pos` (
  `id_pos` int(11) NOT NULL,
  `name_pos` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `pos`
--

INSERT INTO `pos` (`id_pos`, `name_pos`) VALUES
(1, 'مصاري'),
(2, 'Bee'),
(3, 'Fawry'),
(4, 'OPay'),
(5, 'أمان');

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
(2, '2022-06-19', 30.00, 360.00, 75.00, 900.00, 6975488921007),
(3, '2022-06-19', 38.00, 456.00, 80.00, 960.00, 6875422190370),
(4, '2022-06-19', 39.00, 468.00, 80.00, 960.00, 6949810520961),
(5, '2022-06-19', 37.00, 444.00, 75.00, 900.00, 6949810520954),
(7, '2022-06-19', 25.00, 300.00, 50.00, 600.00, 6949810504466),
(8, '2022-06-19', 18.00, 216.00, 35.00, 420.00, 6973224871319),
(9, '2022-06-19', 18.00, 216.00, 35.00, 420.00, 6973224870053),
(10, '2022-06-19', 17.00, 204.00, 30.00, 360.00, 6956116774684),
(11, '2022-06-19', 22.00, 264.00, 40.00, 480.00, 2018014204206),
(12, '2022-06-19', 11.00, 132.00, 25.00, 300.00, 6949810502219),
(13, '2022-06-19', 15.00, 180.00, 30.00, 360.00, 6970462516446),
(14, '2022-06-19', 20.00, 240.00, 40.00, 480.00, 6921042107615),
(15, '2022-06-19', 12.00, 144.00, 30.00, 360.00, 6921042111322),
(16, '2022-06-19', 19.00, 228.00, 40.00, 480.00, 6944561153543),
(17, '2022-06-19', 17.00, 204.00, 35.00, 420.00, 9988854278909),
(18, '2022-06-19', 30.00, 360.00, 65.00, 780.00, 6922252566452),
(19, '2022-06-19', 35.00, 420.00, 70.00, 840.00, 6922154678772),
(20, '2022-06-19', 35.00, 420.00, 75.00, 900.00, 6905112458258),
(21, '2022-06-19', 22.00, 264.00, 50.00, 600.00, 6973224870381),
(22, '2022-06-19', 22.00, 264.00, 45.00, 540.00, 6973224870305),
(23, '2022-06-19', 15.00, 180.00, 35.00, 420.00, 6933138622018),
(24, '2022-06-19', 15.00, 180.00, 30.00, 360.00, 6965468450061),
(25, '2022-06-19', 18.00, 216.00, 45.00, 540.00, 6999881512181),
(26, '2022-06-19', 14.00, 168.00, 35.00, 420.00, 8434350001004),
(27, '2022-06-19', 21.50, 258.00, 40.00, 480.00, 7720190427021),
(28, '2022-06-19', 11.00, 132.00, 30.00, 360.00, 4414136520176),
(29, '2022-06-19', 11.00, 132.00, 30.00, 360.00, 4416336520178),
(30, '2022-06-19', 40.00, 480.00, 75.00, 900.00, 6971410555944),
(31, '2022-06-19', 50.00, 600.00, 100.00, 1200.00, 6949810510177);

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
(1, '2022-06-19', 'كاش', 1, 10695.00, 0.00, 10695.00, 0.00, 'فاتورة مشتريات رقم  1', 1);

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

-- --------------------------------------------------------

--
-- Table structure for table `recharge_type`
--

CREATE TABLE `recharge_type` (
  `id_recharge_type` int(11) NOT NULL,
  `name_recharge_type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `recharge_type`
--

INSERT INTO `recharge_type` (`id_recharge_type`, `name_recharge_type`) VALUES
(1, 'نقدي'),
(2, 'مندوب'),
(3, 'خط كاش');

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

--
-- Dumping data for table `salesinvoic`
--

INSERT INTO `salesinvoic` (`id_salesInvoic`, `date_salesInvoic`, `type_salesInvoic`, `id_client`, `totalAmount`, `discount`, `amountCash`, `amountLater`, `note`, `isActive`) VALUES
(1, '2022-06-19', 'كاش', 1, 75.00, 0.00, 75.00, 0.00, 'فاتورة مبيعات نقدية1', 1),
(2, '2022-06-19', 'كاش', 1, 75.00, 0.00, 75.00, 0.00, 'فاتورة مبيعات نقدية2', 1),
(3, '2022-06-19', 'كاش', 1, 75.00, 0.00, 75.00, 0.00, 'فاتورة مبيعات نقدية3', 1),
(4, '2022-06-21', 'كاش', 1, 50.00, 0.00, 50.00, 0.00, 'فاتورة مبيعات نقدية4', 1),
(5, '2022-06-21', 'كاش', 1, 50.00, 0.00, 50.00, 0.00, 'فاتورة مبيعات نقدية5', 1),
(6, '2022-06-22', 'كاش', 1, 70.00, 0.00, 70.00, 0.00, 'فاتورة مبيعات نقدية6', 1),
(7, '2022-06-22', 'كاش', 1, 25.00, 0.00, 25.00, 0.00, 'فاتورة مبيعات نقدية7', 1),
(8, '2022-06-22', 'كاش', 1, 25.00, 0.00, 25.00, 0.00, 'فاتورة مبيعات نقدية8', 1),
(9, '2022-06-22', 'كاش', 1, 25.00, 0.00, 25.00, 0.00, 'فاتورة مبيعات نقدية9', 1),
(10, '2022-06-22', 'كاش', 1, 25.00, 0.00, 25.00, 0.00, 'فاتورة مبيعات نقدية10', 1),
(11, '2022-06-22', 'كاش', 1, 25.00, 0.00, 25.00, 0.00, 'فاتورة مبيعات نقدية11', 1),
(12, '2022-06-22', 'كاش', 1, 30.00, 0.00, 30.00, 0.00, 'فاتورة مبيعات نقدية12', 1),
(13, '2022-06-22', 'كاش', 1, 40.00, 0.00, 40.00, 0.00, 'فاتورة مبيعات نقدية13', 1),
(14, '2022-06-22', 'كاش', 1, 40.00, 0.00, 40.00, 0.00, 'فاتورة مبيعات نقدية14', 1),
(15, '2022-06-26', 'كاش', 1, 100.00, 0.00, 100.00, 0.00, 'فاتورة مبيعات نقدية15', 1),
(16, '2022-06-26', 'كاش', 1, 60.00, 0.00, 60.00, 0.00, 'فاتورة مبيعات نقدية16', 1),
(17, '2022-06-26', 'كاش', 1, 50.00, 0.00, 50.00, 0.00, 'فاتورة مبيعات نقدية17', 1),
(18, '2022-06-27', 'كاش', 1, 100.00, 0.00, 100.00, 0.00, 'فاتورة مبيعات نقدية18', 1);

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
  `id_supplier_type` int(11) NOT NULL,
  `isActive` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `suppliers`
--

INSERT INTO `suppliers` (`id_Suppliers`, `name_Suppliers`, `phone`, `address`, `idNational`, `firstBalance`, `id_supplier_type`, `isActive`) VALUES
(1, 'مورد نقدي', NULL, NULL, NULL, NULL, 0, 1),
(2, 'صالح - هاي كوليتي', '01114040701', 'دراو', '', 0.00, 1, 1),
(3, 'نور العزالي', '01128331345', 'ادفو', '', 0.00, 4, 1),
(4, 'ريمون', '01211158058', 'كوم امبو', NULL, 0.00, 5, 1),
(5, 'عاصم', '01141182640', 'ادفو', NULL, 1500.00, 6, 1),
(6, 'محمد كرار', '01146212212', 'ادفو', '', 20000.00, 3, 1);

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
  `id_masary_sell` int(11) DEFAULT NULL,
  `note` text DEFAULT NULL,
  `isActive` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `suppliersaccount`
--

INSERT INTO `suppliersaccount` (`id_supplliersAccount`, `date_suppliersAccount`, `Debit`, `Creditor`, `id_Suppliers`, `id_purchaseInvoice`, `id_paymentReceipt`, `id_masary_sell`, `note`, `isActive`) VALUES
(3, '2022-06-28', 700.00, NULL, 2, NULL, 1, NULL, 'ايصال دفع رقم 1', 1),
(4, NULL, NULL, 30000.00, 3, NULL, NULL, 9, 'شحن ماكينةمصاري', 1),
(5, NULL, NULL, 0.00, 4, NULL, NULL, 10, 'شحن ماكينةBee', 1),
(6, '2022-06-30', 20000.00, NULL, 3, NULL, 2, NULL, 'ايصال دفع رقم 2', 1);

-- --------------------------------------------------------

--
-- Table structure for table `supplier_type`
--

CREATE TABLE `supplier_type` (
  `id_supplier_type` int(11) NOT NULL,
  `name_supplier_type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `supplier_type`
--

INSERT INTO `supplier_type` (`id_supplier_type`, `name_supplier_type`) VALUES
(0, NULL),
(1, 'بضائع'),
(5, 'مندوب Bee'),
(6, 'مندوب Opay'),
(2, 'مندوب امان'),
(3, 'مندوب فوري'),
(4, 'مندوب مصاري');

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
(1, 'قطعة', 'دستة', 12.00);

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
('0', 0),
('mostafa', 2800);

-- --------------------------------------------------------

--
-- Table structure for table `utility_masary`
--

CREATE TABLE `utility_masary` (
  `id_utility_masary` int(11) NOT NULL,
  `name_utility_masary` varchar(512) DEFAULT NULL,
  `note_utility` varchar(512) DEFAULT NULL,
  `cost_by_perse` double(10,2) DEFAULT NULL,
  `price` double NOT NULL,
  `id_pos` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `utility_masary`
--

INSERT INTO `utility_masary` (`id_utility_masary`, `name_utility_masary`, `note_utility`, `cost_by_perse`, `price`, `id_pos`) VALUES
(20, 'كروت اورنج', 'LE 2.50', 2.50, 3, 1),
(21, 'كروت اورنج', 'LE 5', 5.00, 6, 1),
(22, 'كروت اورنج', 'LE 7', 7.00, 8, 1),
(23, 'كروت اورنج', 'LE 9', 9.00, 10, 1),
(24, 'كروت اورنج', 'LE 10', 10.00, 11, 1),
(25, 'كروت اورنج', 'LE 13.5', 13.50, 15, 1),
(26, 'كروت اورنج', 'LE 15', 15.00, 16, 1),
(27, 'كروت اورنج', 'LE 18.5', 18.50, 20, 1),
(28, 'كروت اورنج', 'LE 20', 20.00, 22, 1),
(29, 'كروت اورنج', 'LE 25', 25.00, 26, 1),
(30, 'كروت اورنج', 'LE 28', 28.00, 30, 1),
(31, 'كروت اورنج', 'LE 35', 35.00, 37, 1),
(32, 'كروت اورنج', 'LE 42', 42.00, 45, 1),
(33, 'كروت اورنج', 'LE 50', 50.00, 52, 1),
(34, 'كروت اورنج', 'LE 100', 100.00, 105, 1),
(35, 'كروت اتصالات', '2.5', 2.50, 3, 1),
(36, 'كروت اتصالات', '5', 5.00, 6, 1),
(37, 'كروت اتصالات', '7', 7.00, 8, 1),
(38, 'كروت اتصالات', '9', 9.00, 10, 1),
(39, 'كروت اتصالات', '10', 10.00, 11, 1),
(40, 'كروت اتصالات', '13.5', 13.50, 15, 1),
(41, 'كروت اتصالات', '15', 15.00, 16, 1),
(42, 'كروت اتصالات', '25', 25.00, 26, 1),
(43, 'كروت اتصالات', '30', 30.00, 32, 1),
(44, 'كروت اتصالات', '50', 50.00, 52, 1),
(45, 'كروت اتصالات', '100', 100.00, 105, 1),
(46, 'كروت فودافون', 'فكة 2.5', 2.50, 3, 1),
(47, 'كروت فودافون', 'فكة5', 5.00, 6, 1),
(48, 'كروت فودافون', 'فكة 9', 9.00, 10, 1),
(49, 'كروت فودافون', 'فكة 10', 10.00, 11, 1),
(50, 'كروت فودافون', 'فكة 13.5', 13.50, 15, 1),
(51, 'كروت فودافون', 'فكة 15', 15.00, 16, 1),
(52, 'كروت فودافون', 'كارت 10', 10.00, 11, 1),
(53, 'كروت فودافون', 'كارت 15', 15.00, 16, 1),
(54, 'كروت فودافون', 'كارت 25', 25.00, 26, 1),
(55, 'كروت فودافون', 'كارت 50', 50.00, 52, 1),
(56, 'كروت فودافون', 'كارت 100', 100.00, 105, 1),
(57, 'كروت WE', '5 كارت', 5.00, 6, 1),
(58, 'كروت WE', '7 كارت', 7.00, 8, 1),
(59, 'كروت WE', '9 كارت', 9.00, 10, 1),
(60, 'كروت WE', '10 كارت', 10.00, 11, 1),
(61, 'كروت WE', '15 كارت', 15.00, 16, 1),
(62, 'كروت WE', '25 كارت', 25.00, 26, 1),
(63, 'كروت WE', '30 كارت', 30.00, 32, 1),
(64, 'كروت WE', '40 كارت', 40.00, 42, 1),
(65, 'كروت WE', '50 كارت', 50.00, 52, 1),
(66, 'كروت WE', '60 كارت', 60.00, 65, 1),
(67, 'كروت WE', '75 كارت', 75.00, 80, 1),
(69, 'كروت WE', '100 كارت', 100.00, 105, 1),
(70, 'كروت WE', '150 كارت', 150.00, 155, 1),
(71, 'اتصالات حكاية', 'حكاية 25', 25.00, 30, 1),
(72, 'شحن', 'شحن اتصالات', 1.43, 1.5, 1),
(73, 'شحن', 'شحن اتصالات', 1.43, 1.5, 2),
(74, 'شحن', 'شحن اورنج', 1.43, 1.5, 2),
(75, 'شحن', 'شحن وي', 1.00, 1, 2),
(76, 'كروت اتصالات', 'كرت  _ 10', 10.00, 11, 2),
(77, 'كروت اتصالات', 'كرت _ 10', 10.00, 11, 3),
(78, 'كروت اتصالات', 'كرت 10', 10.00, 11, 4),
(79, 'كروت اتصالات', 'كرت _ 10', 10.00, 11, 5);

-- --------------------------------------------------------

--
-- Table structure for table `vf_cash`
--

CREATE TABLE `vf_cash` (
  `id_VF_cash` int(11) NOT NULL,
  `number_VF_cash` varchar(12) DEFAULT NULL,
  `name_owner` varchar(255) NOT NULL,
  `id_international` varchar(14) NOT NULL,
  `sim` varchar(22) DEFAULT NULL,
  `balance` double(10,2) DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `vf_cash`
--

INSERT INTO `vf_cash` (`id_VF_cash`, `number_VF_cash`, `name_owner`, `id_international`, `sim`, `balance`) VALUES
(1, '010-14864061', 'احمد بغدادي جاد عثمان', '29804222800197', '89200-22022-89364-0978', 0.00);

-- --------------------------------------------------------

--
-- Structure for view `calcbalanceitems`
--
DROP TABLE IF EXISTS `calcbalanceitems`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `calcbalanceitems`  AS  select `pur`.`id` AS `id`,`pur`.`name_items` AS `name_items`,ifnull(cast(`pur`.`firstbalance` as double(10,2)),0) AS `firstBalance`,ifnull(cast(`pur`.`qyt_pursh` as double(10,2)),0) AS `purchase`,ifnull(cast(`sal`.`qyt_sales` as double(10,2)),0) AS `Sales`,cast(ifnull(`pur`.`firstbalance`,0) + ifnull(`pur`.`qyt_pursh`,0) - ifnull(`sal`.`qyt_sales`,0) as double(10,2)) AS `nowBalance` from (`calcbalance_purchase` `pur` left join `calcbalance_sales` `sal` on(`sal`.`id` = `pur`.`id`)) ;

-- --------------------------------------------------------

--
-- Structure for view `calcbalance_purchase`
--
DROP TABLE IF EXISTS `calcbalance_purchase`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `calcbalance_purchase`  AS  select `items`.`id_items` AS `id`,`items`.`name_items` AS `name_items`,sum(case when `p`.`name_items` = (select `un`.`low` from `unititems` `un` where `un`.`id_items` = `p`.`id_items`) then `p`.`qyt` / (select `un`.`val` from `unititems` `un` where `un`.`id_items` = `p`.`id_items`) else `p`.`qyt` end) AS `qyt_pursh`,`items`.`firstbalance` AS `firstbalance` from (`items` join `itemsonpurchaseinvoice` `p` on(`p`.`id_items` = `items`.`id_items`)) group by `items`.`id_items` order by `items`.`id_items` ;

-- --------------------------------------------------------

--
-- Structure for view `calcbalance_sales`
--
DROP TABLE IF EXISTS `calcbalance_sales`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `calcbalance_sales`  AS  select `items`.`id_items` AS `id`,`items`.`name_items` AS `name_items`,sum(case when `i`.`name_unit` = (select `un`.`low` from `unititems` `un` where `un`.`id_items` = `i`.`id_items`) then `i`.`qyt` / (select `u`.`val` from `unititems` `u` where `u`.`id_items` = `i`.`id_items`) else `i`.`qyt` end) AS `qyt_sales`,`items`.`firstbalance` AS `firstbalance` from (`items` join `itemsonsalesinvoice` `i` on(`i`.`id_items` = `items`.`id_items`)) group by `items`.`id_items` order by `items`.`id_items` ;

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
-- Indexes for table `categoryutilites`
--
ALTER TABLE `categoryutilites`
  ADD PRIMARY KEY (`id_category`);

--
-- Indexes for table `charagewallet`
--
ALTER TABLE `charagewallet`
  ADD PRIMARY KEY (`id_charageWallet`),
  ADD KEY `id_pos` (`id_pos`),
  ADD KEY `id_VF_Cash` (`id_VF_Cash`);

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
  ADD KEY `id_Receipt` (`id_Receipt`),
  ADD KEY `fk_masary` (`id_masary_pay`);

--
-- Indexes for table `finallyday`
--
ALTER TABLE `finallyday`
  ADD PRIMARY KEY (`dateFinally`);

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
-- Indexes for table `masary_pay`
--
ALTER TABLE `masary_pay`
  ADD PRIMARY KEY (`id_masary_pay`),
  ADD KEY `id_utility_masary` (`id_utility_masary`),
  ADD KEY `id_client` (`id_client`),
  ADD KEY `fk_pos` (`id_pos`);

--
-- Indexes for table `masary_sell`
--
ALTER TABLE `masary_sell`
  ADD PRIMARY KEY (`id_masary_sell`),
  ADD KEY `id_Suppliers` (`id_Suppliers`),
  ADD KEY `fk_pos_sell` (`id_pos`),
  ADD KEY `fk_recharge_type` (`id_recharge_type`),
  ADD KEY `fk_vf_cash` (`id_VF_cash`);

--
-- Indexes for table `paymentreceipt`
--
ALTER TABLE `paymentreceipt`
  ADD PRIMARY KEY (`id_PaymentReceipt`),
  ADD KEY `id_Suppliers` (`id_Suppliers`);

--
-- Indexes for table `phone_numbers`
--
ALTER TABLE `phone_numbers`
  ADD UNIQUE KEY `numbers` (`numbers`);

--
-- Indexes for table `pos`
--
ALTER TABLE `pos`
  ADD PRIMARY KEY (`id_pos`);

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
-- Indexes for table `recharge_type`
--
ALTER TABLE `recharge_type`
  ADD PRIMARY KEY (`id_recharge_type`);

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
  ADD PRIMARY KEY (`id_Suppliers`),
  ADD KEY `fk_supplier_type` (`id_supplier_type`);

--
-- Indexes for table `suppliersaccount`
--
ALTER TABLE `suppliersaccount`
  ADD PRIMARY KEY (`id_supplliersAccount`),
  ADD KEY `id_Suppliers` (`id_Suppliers`),
  ADD KEY `id_purchaseInvoice` (`id_purchaseInvoice`),
  ADD KEY `id_paymentReceipt` (`id_paymentReceipt`),
  ADD KEY `fk_masary_account` (`id_masary_sell`);

--
-- Indexes for table `supplier_type`
--
ALTER TABLE `supplier_type`
  ADD PRIMARY KEY (`id_supplier_type`),
  ADD UNIQUE KEY `name_supplier_type` (`name_supplier_type`);

--
-- Indexes for table `unit`
--
ALTER TABLE `unit`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `utility_masary`
--
ALTER TABLE `utility_masary`
  ADD PRIMARY KEY (`id_utility_masary`),
  ADD KEY `fk_pos_utiltiy` (`id_pos`);

--
-- Indexes for table `vf_cash`
--
ALTER TABLE `vf_cash`
  ADD PRIMARY KEY (`id_VF_cash`),
  ADD UNIQUE KEY `number_VF_cash` (`number_VF_cash`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `casher`
--
ALTER TABLE `casher`
  MODIFY `id_casher` bigint(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=171;

--
-- AUTO_INCREMENT for table `categoryutilites`
--
ALTER TABLE `categoryutilites`
  MODIFY `id_category` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `charagewallet`
--
ALTER TABLE `charagewallet`
  MODIFY `id_charageWallet` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `clientaccount`
--
ALTER TABLE `clientaccount`
  MODIFY `id_ClientAccount` bigint(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `masary_pay`
--
ALTER TABLE `masary_pay`
  MODIFY `id_masary_pay` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=115;

--
-- AUTO_INCREMENT for table `masary_sell`
--
ALTER TABLE `masary_sell`
  MODIFY `id_masary_sell` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `pos`
--
ALTER TABLE `pos`
  MODIFY `id_pos` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `purchaseinvoice`
--
ALTER TABLE `purchaseinvoice`
  MODIFY `id_purchaseInvoice` bigint(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `recharge_type`
--
ALTER TABLE `recharge_type`
  MODIFY `id_recharge_type` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `suppliersaccount`
--
ALTER TABLE `suppliersaccount`
  MODIFY `id_supplliersAccount` bigint(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `supplier_type`
--
ALTER TABLE `supplier_type`
  MODIFY `id_supplier_type` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `utility_masary`
--
ALTER TABLE `utility_masary`
  MODIFY `id_utility_masary` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=80;

--
-- AUTO_INCREMENT for table `vf_cash`
--
ALTER TABLE `vf_cash`
  MODIFY `id_VF_cash` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `charagewallet`
--
ALTER TABLE `charagewallet`
  ADD CONSTRAINT `charagewallet_ibfk_1` FOREIGN KEY (`id_pos`) REFERENCES `pos` (`id_pos`),
  ADD CONSTRAINT `charagewallet_ibfk_2` FOREIGN KEY (`id_VF_Cash`) REFERENCES `vf_cash` (`id_VF_cash`);

--
-- Constraints for table `clientaccount`
--
ALTER TABLE `clientaccount`
  ADD CONSTRAINT `clientaccount_ibfk_1` FOREIGN KEY (`id_salesInvoic`) REFERENCES `salesinvoic` (`id_salesInvoic`),
  ADD CONSTRAINT `clientaccount_ibfk_2` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`),
  ADD CONSTRAINT `clientaccount_ibfk_3` FOREIGN KEY (`id_Receipt`) REFERENCES `receipt` (`id_Receipt`),
  ADD CONSTRAINT `fk_masary` FOREIGN KEY (`id_masary_pay`) REFERENCES `masary_pay` (`id_masary_pay`);

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
-- Constraints for table `masary_pay`
--
ALTER TABLE `masary_pay`
  ADD CONSTRAINT `fk_pos` FOREIGN KEY (`id_pos`) REFERENCES `pos` (`id_pos`),
  ADD CONSTRAINT `masary_pay_ibfk_1` FOREIGN KEY (`id_utility_masary`) REFERENCES `utility_masary` (`id_utility_masary`),
  ADD CONSTRAINT `masary_pay_ibfk_2` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`);

--
-- Constraints for table `masary_sell`
--
ALTER TABLE `masary_sell`
  ADD CONSTRAINT `fk_pos_sell` FOREIGN KEY (`id_pos`) REFERENCES `pos` (`id_pos`),
  ADD CONSTRAINT `fk_recharge_type` FOREIGN KEY (`id_recharge_type`) REFERENCES `recharge_type` (`id_recharge_type`),
  ADD CONSTRAINT `fk_vf_cash` FOREIGN KEY (`id_VF_cash`) REFERENCES `vf_cash` (`id_VF_cash`),
  ADD CONSTRAINT `masary_sell_ibfk_1` FOREIGN KEY (`id_Suppliers`) REFERENCES `suppliers` (`id_Suppliers`);

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
-- Constraints for table `suppliers`
--
ALTER TABLE `suppliers`
  ADD CONSTRAINT `fk_supplier_type` FOREIGN KEY (`id_supplier_type`) REFERENCES `supplier_type` (`id_supplier_type`);

--
-- Constraints for table `suppliersaccount`
--
ALTER TABLE `suppliersaccount`
  ADD CONSTRAINT `fk_masary_account` FOREIGN KEY (`id_masary_sell`) REFERENCES `masary_sell` (`id_masary_sell`),
  ADD CONSTRAINT `suppliersaccount_ibfk_1` FOREIGN KEY (`id_Suppliers`) REFERENCES `suppliers` (`id_Suppliers`),
  ADD CONSTRAINT `suppliersaccount_ibfk_2` FOREIGN KEY (`id_purchaseInvoice`) REFERENCES `purchaseinvoice` (`id_purchaseInvoice`),
  ADD CONSTRAINT `suppliersaccount_ibfk_3` FOREIGN KEY (`id_paymentReceipt`) REFERENCES `paymentreceipt` (`id_PaymentReceipt`);

--
-- Constraints for table `utility_masary`
--
ALTER TABLE `utility_masary`
  ADD CONSTRAINT `fk_pos_utiltiy` FOREIGN KEY (`id_pos`) REFERENCES `pos` (`id_pos`);

DELIMITER $$
--
-- Events
--
CREATE DEFINER=`root`@`localhost` EVENT `finalDay` ON SCHEDULE EVERY 1 DAY STARTS '2022-06-19 00:00:00' ON COMPLETION PRESERVE ENABLE DO INSERT INTO `finallyday`( `dateFinally`, `oldBalance`, `totalimport`, `totalexport`,`time_insert` ) VALUES( CURRENT_DATE(),( SELECT f.newbalance FROM finallyday AS f WHERE f.dateFinally = CURRENT_DATE() - INTERVAL 1 DAY), ( SELECT COALESCE(SUM(c.Debit), 0) FROM casher AS c WHERE c.date_casher = CURRENT_DATE()), ( SELECT COALESCE(SUM(c.Creditor), 0) FROM casher AS c WHERE c.date_casher = CURRENT_DATE()),current_timestamp())$$

DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
