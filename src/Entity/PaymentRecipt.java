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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author mosta
 */
public class PaymentRecipt extends MoneyTransfer{
    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rst;
    private TypeOfFilter type_filter;
    
    public void fillTalble(JTable table){
        String sql = "SELECT r.amount , c.name_Suppliers , r.date_PaymentReceipt , r.id_PaymentReceipt FROM paymentreceipt r INNER JOIN suppliers c on r.id_Suppliers = c.id_Suppliers where r.isActive = 1";
        String[] coulmnName = { "المبلغ", "العميل", "التاريخ", "رقم"};
        ConnectDB.fillAndCenterTable(sql, table, coulmnName);
    }
    
    public void setTypeOfFiltter(TypeOfFilter typeOfFilter){
        this.type_filter = typeOfFilter;
    }

    public TypeOfFilter getType_filter() {
        return type_filter;
    }
    
    @Override
    public boolean Save() {
        boolean isSave = false;
        try{
            con = ConnectDB.getCon();
            con.setAutoCommit(false);
            String sql = "INSERT INTO `paymentreceipt` (`id_PaymentReceipt`, `date_PaymentReceipt`, `amount`, `id_Suppliers`) "
                    + "VALUES (?, ?, ?, ?)";
            pstm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, getId_PaymentReceipt());
            pstm.setString(2, getDate_process());
            pstm.setDouble(3, getAmount());
            pstm.setInt(4, getId_Suppliers());
            int row_effect = pstm.executeUpdate();
            if(row_effect == 1){
               sql = "INSERT INTO `casher` (`date_casher`,`Creditor`, `note`) VALUES (?,?,?)";
               pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
               pstm.setString(1, getDate_process());
               pstm.setDouble(2, getAmount());
               pstm.setString(3, getNote());
               row_effect = pstm.executeUpdate();
               if(row_effect == 1){
                   sql = "INSERT INTO `suppliersaccount` (`date_suppliersAccount`, `Debit`,`id_Suppliers`, `id_paymentReceipt`, `note`) VALUES (?,?,?,?,?)";
                   pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                   pstm.setString(1, getDate_process());
                   pstm.setDouble(2, getAmount());
                   pstm.setInt(3, getId_Suppliers());
                   pstm.setInt(4, getId_PaymentReceipt());
                   pstm.setString(5, getNote());
                   row_effect = pstm.executeUpdate();
                   if(row_effect == 1){
                       boolean isFilter = FilterAccount(type_filter);
                       if(isFilter){
                            con.commit();
                            isSave = true;
                       }
                
                   }
               }
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(PaymentRecipt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSave; //To change body of generated methods, choose Tools | Templates.
    }
    
    private boolean FilterAccount(TypeOfFilter type){
        boolean isclear = false;
        String sql = "";
        int rowEffect = 0;
        switch(type){
            case Clear:
                sql = "UPDATE suppliersaccount SET isActive = 0 WHERE id_Suppliers = ?";
       
            try {
                pstm = (PreparedStatement) con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstm.setInt(1, getId_Suppliers());
                rowEffect = pstm.executeUpdate();
                if(rowEffect > 0){
                    sql = "UPDATE suppliers SET firstBalance = ? WHERE id_Suppliers = ?";
                    pstm = (PreparedStatement) con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    pstm.setDouble(1, getNewBalance());
                    pstm.setInt(2, getId_Suppliers());
                    int row = pstm.executeUpdate();
                    if(row == 1){
                        sql = "UPDATE paymentreceipt SET isActive = 0 WHERE id_Suppliers = ? ";
                        pstm = (PreparedStatement) con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                        pstm.setInt(1, getId_client());
                        row = pstm.executeUpdate();
                        if(row > 0 ){
                            sql = "UPDATE purchaseinvoice SET isActive = 0 WHERE id_Suppliers = ?";
                            pstm = (PreparedStatement) con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                            pstm.setInt(1, getId_client());
                            row = pstm.executeUpdate();
                            if(row > 0 ){
                                isclear = true;
                            }
                            
                        }
                        
                    }
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(recipt.class.getName()).log(Level.SEVERE, null, ex);
            }
       
                break;
            case End :
                break;
            case Payment:
                isclear = true;
                break;
        }
        return isclear;
    }
    
}
