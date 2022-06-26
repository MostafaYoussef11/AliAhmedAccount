-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 27, 2022 at 01:32 AM
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
(64, '2022-06-27', 100.00, NULL, 'فاتورة مبيعات نقدية18', NULL, NULL, 18, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `categoryutilites`
--

CREATE TABLE `categoryutilites` (
  `id_category` int(11) NOT NULL,
  `name_category` varchar(512) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `categoryutilites`
--

INSERT INTO `categoryutilites` (`id_category`, `name_category`) VALUES
(1, 'كروت اتصالات'),
(2, 'كروت اورنج'),
(3, 'كروت WE'),
(4, 'كروت فودافون'),
(9, 'اتصالات حكاية');

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
  `newbalance` double(10,2) GENERATED ALWAYS AS (`oldBalance` + `totalimport` - `totalexport`) VIRTUAL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `finallyday`
--

INSERT INTO `finallyday` (`dateFinally`, `oldBalance`, `totalimport`, `totalexport`) VALUES
('2022-06-18', 11000, 0, 0),
('2022-06-19', 11000, 225, 10695),
('2022-06-20', 530, 0, 0),
('2022-06-21', 530, 100, 0),
('2022-06-22', 630, 305, 0),
('2022-06-23', 935, 0, 0),
('2022-06-24', 935, 0, 0),
('2022-06-25', 935, 0, 0),
('2022-06-26', 935, 415, 0),
('2022-06-27', 1350, 100, 0);

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
  `id_utility_masary` int(11) DEFAULT NULL,
  `price_masary_pay` double(10,2) NOT NULL,
  `id_client` bigint(20) DEFAULT NULL,
  `discount_of_balance` double(10,2) DEFAULT NULL,
  `amount_masary_pay` double(10,2) DEFAULT NULL,
  `id_pos` int(11) NOT NULL DEFAULT 1,
  `balance` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `masary_pay`
--

INSERT INTO `masary_pay` (`id_masary_pay`, `date_masary_pay`, `id_utility_masary`, `price_masary_pay`, `id_client`, `discount_of_balance`, `amount_masary_pay`, `id_pos`, `balance`) VALUES
(8, '2022-06-26', 8, 9.80, 1, 9.80, 10.00, 1, 10990.2),
(9, '2022-06-26', 15, 25.00, 1, 25.00, 25.00, 1, 10965.2),
(10, '2022-06-26', 8, 10.00, 1, 10.00, 10.00, 1, 10955.2),
(12, '2022-06-26', 8, 10.00, 1, 10.00, 10.00, 1, 10945.2),
(13, '2022-06-26', 8, 10.00, 1, 10.00, 10.00, 1, 10935.2),
(14, '2022-06-26', 8, 10.00, 1, 10.00, 10.00, 1, 10925.2),
(15, '2022-06-26', 19, 10.00, 1, 14.30, 15.00, 1, 10910.900000000001),
(16, '2022-06-26', 19, 100.00, 1, 143.00, 150.00, 1, 10767.9);

-- --------------------------------------------------------

--
-- Table structure for table `masary_sell`
--

CREATE TABLE `masary_sell` (
  `id_masary_sell` int(11) NOT NULL,
  `date_masary_sell` date DEFAULT NULL,
  `amount_masary_sell` double(10,2) DEFAULT NULL,
  `id_Suppliers` bigint(20) DEFAULT NULL,
  `id_pos` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `masary_sell`
--

INSERT INTO `masary_sell` (`id_masary_sell`, `date_masary_sell`, `amount_masary_sell`, `id_Suppliers`, `id_pos`) VALUES
(1, '2022-06-24', 1000.00, 1, 1),
(2, '2022-06-26', 10000.00, 1, 1);

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
(1, 'مصاري');

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
  `isActive` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `suppliers`
--

INSERT INTO `suppliers` (`id_Suppliers`, `name_Suppliers`, `phone`, `address`, `idNational`, `firstBalance`, `isActive`) VALUES
(1, 'مورد نقدي', NULL, NULL, NULL, NULL, 1),
(2, 'صالح - هاي كوليتي', '01114040701', 'دراو', '', 0.00, 1);

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
('0', 0);

-- --------------------------------------------------------

--
-- Table structure for table `utility_masary`
--

CREATE TABLE `utility_masary` (
  `id_utility_masary` int(11) NOT NULL,
  `name_utility_masary` varchar(512) DEFAULT NULL,
  `note_utility` varchar(512) DEFAULT NULL,
  `cost_by_perse` double(10,2) DEFAULT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `utility_masary`
--

INSERT INTO `utility_masary` (`id_utility_masary`, `name_utility_masary`, `note_utility`, `cost_by_perse`, `price`) VALUES
(20, 'كروت اورنج', 'LE 2.50', 0.00, 2.5),
(21, 'كروت اورنج', 'LE 5', 0.00, 5),
(22, 'كروت اورنج', 'LE 7', 0.00, 7),
(23, 'كروت اورنج', 'LE 9', 0.00, 9),
(24, 'كروت اورنج', 'LE 10', 0.00, 10),
(25, 'كروت اورنج', 'LE 13.5', 0.00, 13.5),
(26, 'كروت اورنج', 'LE 15', 0.00, 15),
(27, 'كروت اورنج', 'LE 18.5', 0.00, 18.5),
(28, 'كروت اورنج', 'LE 20', 0.00, 20),
(29, 'كروت اورنج', 'LE 25', 0.00, 25),
(30, 'كروت اورنج', 'LE 28', 0.00, 28),
(31, 'كروت اورنج', 'LE 35', 0.00, 35),
(32, 'كروت اورنج', 'LE 42', 0.00, 42),
(33, 'كروت اورنج', 'LE 50', 0.00, 50),
(34, 'كروت اورنج', 'LE 100', 0.00, 100),
(35, 'كروت اتصالات', '2.5', 0.00, 2.5),
(36, 'كروت اتصالات', '5', 0.00, 5),
(37, 'كروت اتصالات', '7', 0.00, 7),
(38, 'كروت اتصالات', '9', 0.00, 9),
(39, 'كروت اتصالات', '10', 0.00, 10),
(40, 'كروت اتصالات', '13.5', 0.00, 13.5),
(41, 'كروت اتصالات', '15', 0.00, 15),
(42, 'كروت اتصالات', '25', 0.00, 25),
(43, 'كروت اتصالات', '30', 0.00, 30),
(44, 'كروت اتصالات', '50', 0.00, 50),
(45, 'كروت اتصالات', '100', 0.00, 0),
(46, 'كروت فودافون', 'فكة 2.5', 0.00, 0),
(47, 'كروت فودافون', 'فكة5', 0.00, 0),
(48, 'كروت فودافون', 'فكة 9', 0.00, 0),
(49, 'كروت فودافون', 'فكة 10', 0.00, 0),
(50, 'كروت فودافون', 'فكة 13.5', 0.00, 0),
(51, 'كروت فودافون', 'فكة 15', 0.00, 0),
(52, 'كروت فودافون', 'كارت 10', 0.00, 0),
(53, 'كروت فودافون', 'كارت 15', 0.00, 0),
(54, 'كروت فودافون', 'كارت 25', 0.00, 0),
(55, 'كروت فودافون', 'كارت 50', 0.00, 0),
(56, 'كروت فودافون', 'كارت 100', 0.00, 0),
(57, 'كروت WE', '5 كارت', 0.00, 0),
(58, 'كروت WE', '7 كارت', 0.00, 0),
(59, 'كروت WE', '9 كارت', 0.00, 0),
(60, 'كروت WE', '10 كارت', 0.00, 0),
(61, 'كروت WE', '15 كارت', 0.00, 0),
(62, 'كروت WE', '25 كارت', 0.00, 0),
(63, 'كروت WE', '30 كارت', 0.00, 0),
(64, 'كروت WE', '40 كارت', 0.00, 0),
(65, 'كروت WE', '50 كارت', 0.00, 0),
(66, 'كروت WE', '60 كارت', 0.00, 0),
(67, 'كروت WE', '75 كارت', 0.00, 0),
(68, 'كروت WE', '75 كارت', 0.00, 0),
(69, 'كروت WE', '100 كارت', 0.00, 0),
(70, 'كروت WE', '150 كارت', 0.00, 0),
(71, 'اتصالات حكاية', 'حكاية 25', 0.00, 25);

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
  ADD KEY `fk_pos_sell` (`id_pos`);

--
-- Indexes for table `paymentreceipt`
--
ALTER TABLE `paymentreceipt`
  ADD PRIMARY KEY (`id_PaymentReceipt`),
  ADD KEY `id_Suppliers` (`id_Suppliers`);

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
-- Indexes for table `utility_masary`
--
ALTER TABLE `utility_masary`
  ADD PRIMARY KEY (`id_utility_masary`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `casher`
--
ALTER TABLE `casher`
  MODIFY `id_casher` bigint(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65;

--
-- AUTO_INCREMENT for table `categoryutilites`
--
ALTER TABLE `categoryutilites`
  MODIFY `id_category` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `clientaccount`
--
ALTER TABLE `clientaccount`
  MODIFY `id_ClientAccount` bigint(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `masary_pay`
--
ALTER TABLE `masary_pay`
  MODIFY `id_masary_pay` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `masary_sell`
--
ALTER TABLE `masary_sell`
  MODIFY `id_masary_sell` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `pos`
--
ALTER TABLE `pos`
  MODIFY `id_pos` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

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
-- AUTO_INCREMENT for table `utility_masary`
--
ALTER TABLE `utility_masary`
  MODIFY `id_utility_masary` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;

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
-- Constraints for table `suppliersaccount`
--
ALTER TABLE `suppliersaccount`
  ADD CONSTRAINT `suppliersaccount_ibfk_1` FOREIGN KEY (`id_Suppliers`) REFERENCES `suppliers` (`id_Suppliers`),
  ADD CONSTRAINT `suppliersaccount_ibfk_2` FOREIGN KEY (`id_purchaseInvoice`) REFERENCES `purchaseinvoice` (`id_purchaseInvoice`),
  ADD CONSTRAINT `suppliersaccount_ibfk_3` FOREIGN KEY (`id_paymentReceipt`) REFERENCES `paymentreceipt` (`id_PaymentReceipt`);

DELIMITER $$
--
-- Events
--
CREATE DEFINER=`root`@`localhost` EVENT `finalDay` ON SCHEDULE EVERY '0 1' DAY_HOUR STARTS '2022-06-19 00:00:00' ON COMPLETION NOT PRESERVE ENABLE DO INSERT INTO `finallyday`( `dateFinally`, `oldBalance`, `totalimport`, `totalexport` ) VALUES( CURRENT_DATE(),( SELECT f.newbalance FROM finallyday AS f WHERE f.dateFinally = CURRENT_DATE() - INTERVAL 1 DAY), ( SELECT COALESCE(SUM(c.Debit), 0) FROM casher AS c WHERE c.date_casher = CURRENT_DATE()), ( SELECT COALESCE(SUM(c.Creditor), 0) FROM casher AS c WHERE c.date_casher = CURRENT_DATE()) )$$

CREATE DEFINER=`root`@`localhost` EVENT `updatebalance` ON SCHEDULE EVERY 1 SECOND STARTS '2022-06-19 07:53:05' ON COMPLETION NOT PRESERVE ENABLE DO UPDATE finallyday SET totalimport = (SELECT COALESCE(SUM(c.Debit), 0) FROM casher AS c WHERE c.date_casher = CURRENT_DATE()) , totalexport = (SELECT COALESCE(SUM(c.Creditor), 0) FROM casher AS c WHERE c.date_casher = CURRENT_DATE()) WHERE dateFinally = CURRENT_DATE()$$

DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
