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
    private CasherClass casher = new CasherClass();
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
        String date_proc = getDate_process();
        double  amount = getAmount();
        int id_recipt_payment = getId_PaymentReceipt();
        int id_Sup = getId_Suppliers();
        String not = getNote();
        boolean isSave = false;
        try{
            con = ConnectDB.getCon();
            con.setAutoCommit(false);
            String sql = "INSERT INTO `paymentreceipt` (`id_PaymentReceipt`, `date_PaymentReceipt`, `amount`, `id_Suppliers`) "
                    + "VALUES (?, ?, ?, ?)";
            pstm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, id_recipt_payment);
            pstm.setString(2, date_proc);
            pstm.setDouble(3, amount);
            pstm.setInt(4, id_Sup);
            int row_effect = pstm.executeUpdate();
            if(row_effect == 1){
               sql = "INSERT INTO `casher` (`date_casher`,`Creditor`, `note`,`id_PaymentReceipt`) VALUES (?,?,?,?)";
               pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
               pstm.setString(1, date_proc);
               pstm.setDouble(2, amount);
               pstm.setString(3, not);
               pstm.setInt(4, id_recipt_payment);
               row_effect = pstm.executeUpdate();
               if(row_effect == 1){
                   String sql_insert_sup_account = "INSERT INTO `suppliersaccount` (`date_suppliersAccount`, `Debit`,`id_Suppliers`, `id_paymentReceipt`, `note`) VALUES (?,?,?,?,?)";
                   pstm = con.prepareStatement(sql_insert_sup_account, Statement.RETURN_GENERATED_KEYS);
                   pstm.setString(1,date_proc );
                   pstm.setDouble(2, amount);
                   pstm.setInt(3, id_Sup);
                   pstm.setInt(4, id_recipt_payment);
                   pstm.setString(5, not);
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
                            pstm.setInt(1,getId_Suppliers() );
                            row = pstm.executeUpdate();
                            if(row > 0 ){
                                sql = "UPDATE purchaseinvoice SET isActive = 0 WHERE id_Suppliers = ?";
                                pstm = (PreparedStatement) con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                                pstm.setInt(1, getId_Suppliers());
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
                    sql = "DELETE FROM suppliersaccount WHERE id_Suppliers = ?";
                try {
                    pstm = (PreparedStatement) con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    pstm.setInt(1, getId_Suppliers());
                    rowEffect = pstm.executeUpdate();
                    if(rowEffect > 0){
                        sql = "DELETE FROM purchaseinvoice WHERE id_Suppliers = ?";
                        pstm = (PreparedStatement) con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                        pstm.setInt(1, getId_Suppliers());
                        int row = pstm.executeUpdate();
                        if(row > 0){
                            sql = "DELETE FROM paymentreceipt WHERE id_Suppliers = ? ";
                            pstm = (PreparedStatement) con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                            pstm.setInt(1,getId_Suppliers() );
                            row = pstm.executeUpdate();
                            if(row > 0 ){
                              sql = "DELETE FROM suppliers WHERE id_Suppliers = ?";
                              pstm = (PreparedStatement) con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                              pstm.setInt(1, getId_Suppliers());
                              row = pstm.executeUpdate();
                              if(row == 1 ){
                                    isclear = true;
                                }

                            }

                        }
                    }

                 } catch (SQLException ex) {
                    Logger.getLogger(recipt.class.getName()).log(Level.SEVERE, null, ex);
                 }
                break;
            case Payment:
                isclear = true;
                break;
        }
        return isclear;
    }
    
    @Override
    public boolean Delete(String id) {
        setId_PaymentReceipt(Integer.parseInt(id));
        boolean isDel = false;
        int row_affect = 0;
        try{
            con = ConnectDB.getCon();
            con.setAutoCommit(false);
            String sql_delete = "DELETE FROM suppliersaccount WHERE `id_PaymentReceipt`=?";
            pstm = con.prepareStatement(sql_delete, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, id);
            int rowDelete = pstm.executeUpdate();
            if(rowDelete == 1){
                String sql = "DELETE FROM `paymentreceipt` WHERE `id_PaymentReceipt`=?";
                pstm = con.prepareStatement(sql ,Statement.RETURN_GENERATED_KEYS);
                pstm.setInt(1, getId_PaymentReceipt());
                row_affect = pstm.executeUpdate();            
                if(row_affect == 1){
                  if(casher.DeleteByIdPaymentRecipt(id)){ 
                          isDel = true;
                          con.commit();
                          con.close();
                     }
            }
           }
            
        }catch(SQLException ex){
            isDel = false;
            try{
                con.rollback();
                con.close();
            }catch(SQLException exception){
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, exception);
            }
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,null,ex);
        }
        
        return isDel; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Update(String id) {
        boolean isUpdate = false;
        //Update suppliersaccount  --- Update paymentreceipt --- Update casher
        try{
            con = ConnectDB.getCon();
            con.setAutoCommit(false);
            String sql_Update_suppliersaccount= "UPDATE `suppliersaccount` SET date_suppliersAccount='"+getDate_process()+"' , "
                    + "Debit = "+getAmount()+" , id_Suppliers = "+ getId_Suppliers()+", note ='"+getNote()+"'  WHERE id_paymentReceipt ="+id;
            pstm = con.prepareStatement(sql_Update_suppliersaccount, Statement.RETURN_GENERATED_KEYS);
            int rowAffect = pstm.executeUpdate();
            if(rowAffect == 1){
                //Update paymentreceipt
                String sql_paymentreceipt_update = "UPDATE paymentreceipt SET date_PaymentReceipt='"+getDate_process()+"' , amount = "
                        + getAmount() +" , id_Suppliers ="+getId_Suppliers() +" WHERE id_PaymentReceipt = "+id;
                pstm = con.prepareStatement(sql_paymentreceipt_update,Statement.RETURN_GENERATED_KEYS);
                int rowAffect_pay = pstm.executeUpdate();
                if(rowAffect_pay == 1){
                    //Update casher
                    String sql_casher_update = "UPDATE casher SET date_casher = '"+getDate_process()+"' , Creditor ="+getAmount()+", note ='"+getNote()+"' WHERE id_PaymentReceipt = "+id;
                    pstm = con.prepareStatement(sql_casher_update, Statement.RETURN_GENERATED_KEYS);
                    int rowAffectCasher = pstm.executeUpdate();
                    if(rowAffectCasher == 1){
                        con.commit();
                        con.close();
                        isUpdate = true;
                    }
                }
            }
        }catch(SQLException ex){
            try {
                con.rollback();
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(PaymentRecipt.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
      return isUpdate; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
