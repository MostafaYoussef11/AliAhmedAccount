-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 11, 2022 at 03:24 PM
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
  `id_users` int(11) NOT NULL,
  `id_purchaseInvoice` bigint(255) DEFAULT NULL,
  `id_Receipt` bigint(255) DEFAULT NULL,
  `id_salesInvoic` bigint(255) DEFAULT NULL,
  `id_PaymentReceipt` bigint(255) DEFAULT NULL,
  `id_masary_pay` int(11) DEFAULT NULL,
  `id_masary_sell` int(11) DEFAULT NULL,
  `id_Send_Receive` int(11) DEFAULT NULL,
  `id_fees` int(11) DEFAULT NULL,
  `id_Solf` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
-- Table structure for table `cashertwo`
--

CREATE TABLE `cashertwo` (
  `id_CasherTwo` int(11) NOT NULL,
  `date_Casher` date DEFAULT curdate(),
  `time_Casher` time DEFAULT curtime(),
  `Debit` double(10,2) DEFAULT 0.00,
  `Creditor` double(10,2) DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Stand-in structure for view `cashertwobalance`
-- (See below for the actual view)
--
CREATE TABLE `cashertwobalance` (
`Balance` double(19,2)
);

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
(10, 'شحن', 1),
(11, 'فودافون كاش', 1);

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
-- Table structure for table `expensescategory`
--

CREATE TABLE `expensescategory` (
  `id_expensesCategory` int(11) NOT NULL,
  `name_expensesCategory` varchar(512) DEFAULT NULL,
  `fBalance` double(10,2) DEFAULT NULL,
  `NBalance` double(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `expensescategory`
--

INSERT INTO `expensescategory` (`id_expensesCategory`, `name_expensesCategory`, `fBalance`, `NBalance`) VALUES
(1, 'ايجارات', 0.00, 0.00),
(2, 'مرتبات', 0.00, 0.00),
(3, 'مصروفات نثرية', 0.00, 0.00);

-- --------------------------------------------------------

--
-- Table structure for table `fees`
--

CREATE TABLE `fees` (
  `id_fees` int(11) NOT NULL,
  `date_fees` date DEFAULT curdate(),
  `time_fees` time DEFAULT curtime(),
  `amount` double(10,2) NOT NULL,
  `note` varchar(255) NOT NULL,
  `id_expensesCategory` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `phone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
-- Table structure for table `notifcation`
--

CREATE TABLE `notifcation` (
  `id_notifcation` int(11) NOT NULL,
  `date_notifcation` date DEFAULT NULL,
  `note` varchar(512) DEFAULT NULL,
  `isRead` tinyint(1) NOT NULL DEFAULT 0,
  `id_Solf` int(11) NOT NULL
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

-- --------------------------------------------------------

--
-- Table structure for table `phone_numbers`
--

CREATE TABLE `phone_numbers` (
  `id_phone_number` int(11) NOT NULL,
  `numbers` varchar(14) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
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
(2, '2022-06-19', 30.00, 30.00, 75.00, 75.00, 6975488921007),
(3, '2022-06-19', 38.00, 38.00, 80.00, 80.00, 6875422190370),
(4, '2022-06-19', 39.00, 39.00, 80.00, 80.00, 6949810520961),
(5, '2022-06-19', 37.00, 37.00, 75.00, 75.00, 6949810520954),
(7, '2022-06-19', 25.00, 25.00, 50.00, 50.00, 6949810504466),
(8, '2022-06-19', 18.00, 18.00, 35.00, 35.00, 6973224871319),
(9, '2022-06-19', 18.00, 18.00, 35.00, 35.00, 6973224870053),
(10, '2022-06-19', 17.00, 17.00, 30.00, 30.00, 6956116774684),
(11, '2022-06-19', 22.00, 22.00, 40.00, 40.00, 2018014204206),
(12, '2022-06-19', 11.00, 11.00, 25.00, 25.00, 6949810502219),
(13, '2022-06-19', 15.00, 15.00, 30.00, 30.00, 6970462516446),
(14, '2022-06-19', 20.00, 20.00, 40.00, 40.00, 6921042107615),
(15, '2022-06-19', 12.00, 12.00, 30.00, 30.00, 6921042111322),
(16, '2022-06-19', 19.00, 19.00, 40.00, 40.00, 6944561153543),
(17, '2022-06-19', 17.00, 17.00, 35.00, 35.00, 9988854278909),
(18, '2022-06-19', 30.00, 30.00, 65.00, 65.00, 6922252566452),
(19, '2022-06-19', 35.00, 35.00, 70.00, 70.00, 6922154678772),
(20, '2022-06-19', 35.00, 35.00, 75.00, 75.00, 6905112458258),
(21, '2022-06-19', 22.00, 22.00, 50.00, 50.00, 6973224870381),
(22, '2022-06-19', 22.00, 22.00, 45.00, 45.00, 6973224870305),
(23, '2022-06-19', 15.00, 15.00, 35.00, 35.00, 6933138622018),
(24, '2022-06-19', 15.00, 15.00, 30.00, 30.00, 6965468450061),
(25, '2022-06-19', 18.00, 18.00, 45.00, 45.00, 6999881512181),
(26, '2022-06-19', 14.00, 14.00, 35.00, 35.00, 8434350001004),
(27, '2022-06-19', 21.50, 21.50, 40.00, 40.00, 7720190427021),
(28, '2022-06-19', 11.00, 11.00, 30.00, 30.00, 4414136520176),
(29, '2022-06-19', 11.00, 11.00, 30.00, 30.00, 4416336520178),
(30, '2022-06-19', 40.00, 40.00, 75.00, 75.00, 6971410555944),
(31, '2022-06-19', 50.00, 50.00, 100.00, 100.00, 6949810510177);

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

-- --------------------------------------------------------

--
-- Table structure for table `send_receive`
--

CREATE TABLE `send_receive` (
  `id_Send_Receive` int(11) NOT NULL,
  `date_Send_Receive` date NOT NULL DEFAULT curdate(),
  `time_Send_Receive` time NOT NULL DEFAULT curtime(),
  `type_Send_Receive` enum('Send','Receive') DEFAULT NULL,
  `value_Send_Receive` double(10,2) NOT NULL,
  `amount_Send_Receive` double(10,2) NOT NULL,
  `id_VF_cash` int(11) DEFAULT NULL,
  `Number_client` varchar(512) DEFAULT NULL,
  `id_client` bigint(255) DEFAULT 1,
  `id_Suppliers` bigint(255) DEFAULT 1,
  `take_money` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `solf`
--

CREATE TABLE `solf` (
  `id_Solf` int(11) NOT NULL,
  `date_Solf` date DEFAULT curdate(),
  `time_Solf` time DEFAULT curtime(),
  `datePay_Solf` date NOT NULL,
  `amount_Solf` double(10,2) NOT NULL,
  `name_Solf` varchar(255) NOT NULL,
  `note_Solf` varchar(512) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Triggers `solf`
--
DELIMITER $$
CREATE TRIGGER `addNotifcationSolfa` AFTER INSERT ON `solf` FOR EACH ROW INSERT INTO `notifcation` ( `date_notifcation`, `note` , `id_Solf`) VALUES (
NEW.datePay_Solf , CONCAT( 'موعد استحقاق مبلغ' ,'  ', NEW.amount_Solf , ' ' , NEW.name_Solf 
), NEW.id_Solf)
$$
DELIMITER ;

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
(5, 'عاصم', '01141182640', 'ادفو', NULL, 0.00, 6, 1),
(6, 'محمد كرار', '01146212212', 'ادفو', '', 0.00, 3, 1);

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
-- Stand-in structure for view `totalcash`
-- (See below for the actual view)
--
CREATE TABLE `totalcash` (
`id` int(11)
,`Number` varchar(12)
,`FirstBalance` double(10,2)
,`deposit` double(19,2)
,`Withdraw` double(19,2)
,`Send` double(19,2)
,`Receive` double(19,2)
,`NowBalance` double(19,2)
);

-- --------------------------------------------------------

--
-- Table structure for table `unit`
--

CREATE TABLE `unit` (
  `id` bigint(255) NOT NULL,
  `low` varchar(512) DEFAULT NULL,
  `high` varchar(512) DEFAULT NULL,
  `val` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `unit`
--

INSERT INTO `unit` (`id`, `low`, `high`, `val`) VALUES
(1, 'قطعة', 'قطعة', 1);

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
,`val` int(11)
);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id_users` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `passwod` bigint(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id_users`, `username`, `passwod`) VALUES
(0, 'applecationProgramer', 1127333021),
(2, '0', 0);

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
(39, 'كروت اتصالات', 'كرت  _ 10', 10.00, 11, 1),
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
(76, 'كروت اتصالات', 'كرت  _ 10', 10.00, 11, 2),
(77, 'كروت اتصالات', 'كرت  _ 10', 10.00, 11, 3),
(78, 'كروت اتصالات', 'كرت  _ 10', 10.00, 11, 4),
(79, 'كروت اتصالات', 'كرت  _ 10', 10.00, 11, 5),
(80, 'فودافون كاش', 'ايداع', 1.00, 1, 1),
(81, 'فودافون كاش', 'ايداع', 1.00, 1, 2),
(82, 'فودافون كاش', 'ايداع', 1.00, 1, 3),
(83, 'فودافون كاش', 'ايداع', 1.00, 1, 5),
(84, 'فودافون كاش', 'سحب', 1.00, 0.01, 1),
(85, 'فودافون كاش', 'سحب', 1.00, 0.01, 2),
(86, 'فودافون كاش', 'سحب', 1.00, 0.01, 3),
(87, 'فودافون كاش', 'سحب', 1.00, 0.01, 5),
(88, 'شحن', 'شحن فودافون', 1.43, 1.5, 1),
(89, 'شحن', 'شحن اورنج', 1.43, 1.5, 1),
(90, 'شحن', 'شحن WE', 1.00, 1, 1);

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
(1, '010-14864061', 'احمد بغدادي جاد عثمان', '29804222800197', '89200-22022-89364-0978', 0.00),
(2, '010-32064920', 'اشرف محمد احمد محمد', '28710022800194', '89200-22022-81925-1975', 0.00);

-- --------------------------------------------------------

--
-- Table structure for table `vf_transaction_po`
--

CREATE TABLE `vf_transaction_po` (
  `id_transaction` int(11) NOT NULL,
  `date_transaction` date DEFAULT curdate(),
  `time_transaction` time DEFAULT curtime(),
  `type_transaction` enum('Deposit','Withdraw') DEFAULT NULL,
  `id_VF_cash` int(11) DEFAULT NULL,
  `id_pos` int(11) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `id_masary_pay` int(11) DEFAULT NULL,
  `id_masary_sell` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
-- Structure for view `cashertwobalance`
--
DROP TABLE IF EXISTS `cashertwobalance`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `cashertwobalance`  AS SELECT sum(`c`.`Debit`) - sum(`c`.`Creditor`) AS `Balance` FROM `cashertwo` AS `c` ;

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
-- Structure for view `totalcash`
--
DROP TABLE IF EXISTS `totalcash`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `totalcash`  AS SELECT `v`.`id_VF_cash` AS `id`, `v`.`number_VF_cash` AS `Number`, `v`.`balance` AS `FirstBalance`, (select ifnull(sum(`trans1`.`price`),0) from `vf_transaction_po` `trans1` where `v`.`id_VF_cash` = `trans1`.`id_VF_cash` and `trans1`.`type_transaction` = 'Deposit') AS `deposit`, (select ifnull(sum(`trans2`.`price`),0) from `vf_transaction_po` `trans2` where `v`.`id_VF_cash` = `trans2`.`id_VF_cash` and `trans2`.`type_transaction` = 'Withdraw') AS `Withdraw`, (select ifnull(sum(`sr1`.`value_Send_Receive`),0) from `send_receive` `sr1` where `v`.`id_VF_cash` = `sr1`.`id_VF_cash` and `sr1`.`type_Send_Receive` = 'Send') AS `Send`, (select ifnull(sum(`sr2`.`value_Send_Receive`),0) from `send_receive` `sr2` where `v`.`id_VF_cash` = `sr2`.`id_VF_cash` and `sr2`.`type_Send_Receive` = 'Receive') AS `Receive`, (select `v`.`balance` + `deposit` - `Withdraw` + `Receive` - `Send`) AS `NowBalance` FROM ((`vf_cash` `v` left join `vf_transaction_po` `trans` on(`v`.`id_VF_cash` = `trans`.`id_VF_cash`)) left join `send_receive` `sr` on(`v`.`id_VF_cash` = `sr`.`id_VF_cash`)) GROUP BY `v`.`id_VF_cash` ;

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
  ADD PRIMARY KEY (`id_casher`),
  ADD KEY `fk_user` (`id_users`);

--
-- Indexes for table `cashertwo`
--
ALTER TABLE `cashertwo`
  ADD PRIMARY KEY (`id_CasherTwo`);

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
  ADD KEY `id_Receipt` (`id_Receipt`),
  ADD KEY `fk_masary` (`id_masary_pay`);

--
-- Indexes for table `expensescategory`
--
ALTER TABLE `expensescategory`
  ADD PRIMARY KEY (`id_expensesCategory`),
  ADD UNIQUE KEY `name_expensesCategory` (`name_expensesCategory`);

--
-- Indexes for table `fees`
--
ALTER TABLE `fees`
  ADD PRIMARY KEY (`id_fees`),
  ADD KEY `id_expensesCategory` (`id_expensesCategory`);

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
-- Indexes for table `notifcation`
--
ALTER TABLE `notifcation`
  ADD PRIMARY KEY (`id_notifcation`),
  ADD KEY `id_solf` (`id_Solf`);

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
  ADD PRIMARY KEY (`id_phone_number`),
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
-- Indexes for table `send_receive`
--
ALTER TABLE `send_receive`
  ADD PRIMARY KEY (`id_Send_Receive`),
  ADD KEY `id_Suppliers` (`id_Suppliers`),
  ADD KEY `id_client` (`id_client`),
  ADD KEY `id_VF_cash` (`id_VF_cash`);

--
-- Indexes for table `solf`
--
ALTER TABLE `solf`
  ADD PRIMARY KEY (`id_Solf`);

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
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_users`);

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
-- Indexes for table `vf_transaction_po`
--
ALTER TABLE `vf_transaction_po`
  ADD PRIMARY KEY (`id_transaction`),
  ADD KEY `id_VF_cash` (`id_VF_cash`),
  ADD KEY `id_pos` (`id_pos`),
  ADD KEY `id_masary_pay` (`id_masary_pay`),
  ADD KEY `id_masary_sell` (`id_masary_sell`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `casher`
--
ALTER TABLE `casher`
  MODIFY `id_casher` bigint(255) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `cashertwo`
--
ALTER TABLE `cashertwo`
  MODIFY `id_CasherTwo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `categoryutilites`
--
ALTER TABLE `categoryutilites`
  MODIFY `id_category` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `clientaccount`
--
ALTER TABLE `clientaccount`
  MODIFY `id_ClientAccount` bigint(255) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `expensescategory`
--
ALTER TABLE `expensescategory`
  MODIFY `id_expensesCategory` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `fees`
--
ALTER TABLE `fees`
  MODIFY `id_fees` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `masary_pay`
--
ALTER TABLE `masary_pay`
  MODIFY `id_masary_pay` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `masary_sell`
--
ALTER TABLE `masary_sell`
  MODIFY `id_masary_sell` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `notifcation`
--
ALTER TABLE `notifcation`
  MODIFY `id_notifcation` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `phone_numbers`
--
ALTER TABLE `phone_numbers`
  MODIFY `id_phone_number` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pos`
--
ALTER TABLE `pos`
  MODIFY `id_pos` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `purchaseinvoice`
--
ALTER TABLE `purchaseinvoice`
  MODIFY `id_purchaseInvoice` bigint(255) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `recharge_type`
--
ALTER TABLE `recharge_type`
  MODIFY `id_recharge_type` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `send_receive`
--
ALTER TABLE `send_receive`
  MODIFY `id_Send_Receive` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `solf`
--
ALTER TABLE `solf`
  MODIFY `id_Solf` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `suppliersaccount`
--
ALTER TABLE `suppliersaccount`
  MODIFY `id_supplliersAccount` bigint(255) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `supplier_type`
--
ALTER TABLE `supplier_type`
  MODIFY `id_supplier_type` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id_users` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `utility_masary`
--
ALTER TABLE `utility_masary`
  MODIFY `id_utility_masary` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=91;

--
-- AUTO_INCREMENT for table `vf_cash`
--
ALTER TABLE `vf_cash`
  MODIFY `id_VF_cash` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `vf_transaction_po`
--
ALTER TABLE `vf_transaction_po`
  MODIFY `id_transaction` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `casher`
--
ALTER TABLE `casher`
  ADD CONSTRAINT `fk_user` FOREIGN KEY (`id_users`) REFERENCES `users` (`id_users`);

--
-- Constraints for table `clientaccount`
--
ALTER TABLE `clientaccount`
  ADD CONSTRAINT `clientaccount_ibfk_1` FOREIGN KEY (`id_salesInvoic`) REFERENCES `salesinvoic` (`id_salesInvoic`),
  ADD CONSTRAINT `clientaccount_ibfk_2` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`),
  ADD CONSTRAINT `clientaccount_ibfk_3` FOREIGN KEY (`id_Receipt`) REFERENCES `receipt` (`id_Receipt`),
  ADD CONSTRAINT `fk_masary` FOREIGN KEY (`id_masary_pay`) REFERENCES `masary_pay` (`id_masary_pay`);

--
-- Constraints for table `fees`
--
ALTER TABLE `fees`
  ADD CONSTRAINT `fees_ibfk_1` FOREIGN KEY (`id_expensesCategory`) REFERENCES `expensescategory` (`id_expensesCategory`);

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
-- Constraints for table `notifcation`
--
ALTER TABLE `notifcation`
  ADD CONSTRAINT `fk_solf` FOREIGN KEY (`id_Solf`) REFERENCES `solf` (`id_Solf`);

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
-- Constraints for table `send_receive`
--
ALTER TABLE `send_receive`
  ADD CONSTRAINT `send_receive_ibfk_1` FOREIGN KEY (`id_Suppliers`) REFERENCES `suppliers` (`id_Suppliers`),
  ADD CONSTRAINT `send_receive_ibfk_2` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`),
  ADD CONSTRAINT `send_receive_ibfk_3` FOREIGN KEY (`id_VF_cash`) REFERENCES `vf_cash` (`id_VF_cash`);

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

--
-- Constraints for table `vf_transaction_po`
--
ALTER TABLE `vf_transaction_po`
  ADD CONSTRAINT `vf_transaction_po_ibfk_1` FOREIGN KEY (`id_VF_cash`) REFERENCES `vf_cash` (`id_VF_cash`),
  ADD CONSTRAINT `vf_transaction_po_ibfk_2` FOREIGN KEY (`id_pos`) REFERENCES `pos` (`id_pos`),
  ADD CONSTRAINT `vf_transaction_po_ibfk_3` FOREIGN KEY (`id_masary_pay`) REFERENCES `masary_pay` (`id_masary_pay`),
  ADD CONSTRAINT `vf_transaction_po_ibfk_4` FOREIGN KEY (`id_masary_sell`) REFERENCES `masary_sell` (`id_masary_sell`);

DELIMITER $$
--
-- Events
--
CREATE DEFINER=`root`@`localhost` EVENT `finalDay` ON SCHEDULE EVERY 24 HOUR STARTS '2022-07-03 00:00:00' ON COMPLETION PRESERVE ENABLE DO INSERT INTO `finallyday`( `dateFinally`, `oldBalance`, `totalimport`, `totalexport`,`time_insert` ) VALUES( CURRENT_DATE(),( SELECT f.newbalance FROM finallyday AS f WHERE f.dateFinally = CURRENT_DATE() - INTERVAL 1 DAY), ( SELECT COALESCE(SUM(c.Debit), 0) FROM casher AS c WHERE c.date_casher = CURRENT_DATE()), ( SELECT COALESCE(SUM(c.Creditor), 0) FROM casher AS c WHERE c.date_casher = CURRENT_DATE()),current_timestamp())$$

DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
