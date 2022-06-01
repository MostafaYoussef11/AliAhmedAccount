/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Utilities.ConnectDB;
import Utilities.MoneyTransfer;
import Utilities.TypeOfFilter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;

/**
 *
 * @author mosta
 */
public class PaymentRecipt extends MoneyTransfer{
    private TypeOfFilter type;
    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rst;
    public void fillTalble(JTable table){
        String sql = "SELECT r.amount , c.name_Suppliers , r.date_PaymentReceipt , r.id_PaymentReceipt FROM paymentreceipt r INNER JOIN suppliers c on r.id_Suppliers = c.id_Suppliers where r.isActive = 1";
        String[] coulmnName = { "المبلغ", "العميل", "التاريخ", "رقم"};
        ConnectDB.fillAndCenterTable(sql, table, coulmnName);
    }

    
}
