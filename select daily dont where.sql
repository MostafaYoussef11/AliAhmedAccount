SELECT 
(SELECT IFNULL(SUM(Debit),0) FROM casher WHERE id_masary_pay IS NOT NULL) AS POS_PAY ,
(SELECT IFNULL(SUM(Debit),0) FROM casher WHERE id_Receipt IS NOT NULL) AS Recipt , 
(SELECT IFNULL(SUM(Debit),0) FROM casher WHERE id_salesInvoic IS NOT NULL) AS Sales ,
(SELECT IFNULL(SUM(Debit),0) FROM casher WHERE id_Send_Receive IS NOT NULL) AS Send_VfCash ,
(SELECT IFNULL(SUM(Debit),0) FROM casher WHERE id_Solf IS NOT NULL) AS Solfa ,
(SELECT IFNULL(SUM(Creditor),0) FROM casher WHERE id_masary_sell IS NOT NULL) AS POS_Sell ,
(SELECT IFNULL(SUM(Creditor),0) FROM casher WHERE id_PaymentReceipt IS NOT NULL) AS Payment, 
(SELECT IFNULL(SUM(Creditor),0) FROM casher WHERE id_purchaseInvoice IS NOT NULL) AS Purchases,
(SELECT IFNULL(SUM(Creditor),0) FROM casher WHERE id_Send_Receive IS NOT NULL) AS Receive_VfCash, 
(SELECT IFNULL(SUM(Creditor),0) FROM casher WHERE id_fees IS NOT NULL) AS Feeses
