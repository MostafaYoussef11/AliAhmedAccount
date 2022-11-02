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
public class recipt extends MoneyTransfer{
    private TypeOfFilter type;
    private Connection con;
    private PreparedStatement pstm , pstm_Casher;
    private ResultSet rst;
    
    public void fillTalble(JTable table){
        String sql = "SELECT r.amount , c.name_client , r.date_Receipt , r.id_Receipt FROM receipt r INNER JOIN client c on r.id_client = c.id_client where r.isActive = 1 ORDER BY r.id_Receipt DESC";
        String[] coulmnName = { "المبلغ", "العميل", "التاريخ", "رقم"};
        ConnectDB.fillAndCenterTable(sql, table, coulmnName);
    }
    public TypeOfFilter getType() {
        return type;
    }
    public void setType(TypeOfFilter type) {
        this.type = type;
    }
    @Override
    public boolean Save() {
        boolean isSaved = false;
        try{
            con = ConnectDB.getCon();
            con.setAutoCommit(false);
            int rowAffect;
            pstm_Casher = new CasherClass().SavedCasherTransaction(TypeCasherTransaction.Receipt, getAmount(), getNote(), getId_Receipt() ,con);
            rowAffect = pstm_Casher.executeUpdate();
            if(rowAffect == 1){
                String sqlInserrecipt = "INSERT INTO `receipt` (`id_Receipt`, `date_Receipt`, `amount`, `id_client`) VALUES (?,?,?,?)";
                pstm = (PreparedStatement) con.prepareStatement(sqlInserrecipt, Statement.RETURN_GENERATED_KEYS);
                pstm.setInt(1, getId_Receipt());
                pstm.setString(2, getDate_process());
                pstm.setDouble(3, getAmount());
                pstm.setInt(4, getId_client());
                int roweffectReceipt = pstm.executeUpdate();
                rst = pstm.getGeneratedKeys();
                if(roweffectReceipt == 1){
                    String sqlInserClienAccount = "INSERT INTO `clientaccount` (`date_ClientAccount`,`Creditor`, `id_client`,`id_Receipt`, `note`) "
                                                + "VALUES (?,?,?,?,?)";
                    PreparedStatement pstm_client_account = (PreparedStatement) con.prepareStatement(sqlInserClienAccount, Statement.RETURN_GENERATED_KEYS);
                    pstm_client_account.setString(1, getDate_process());
                    pstm_client_account.setDouble(2, getAmount());
                    pstm_client_account.setInt(3, getId_client());
                    pstm_client_account.setInt(4, getId_Receipt());
                    pstm_client_account.setString(5, getNote());
                    int row = pstm_client_account.executeUpdate();
                    if(row == 1){
                        if(FilterAccount(type)){
                             con.commit();
                             con.close();
                             isSaved = true;
                        }

                    }
                }
                
            }
        }catch(SQLException exception){
            try {
                con.rollback();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(recipt.class.getName()).log(Level.SEVERE, null, ex);
            }
            Logger.getLogger(recipt.class.getName()).log(Level.SEVERE, null, exception);
        }
        return isSaved;
            //return super.Save(); //To change body of generated methods, choose Tools | Templates.
    }
    private boolean FilterAccount(TypeOfFilter type){
        boolean isclear = false;
        String sql = "";
        int rowEffect = 0;
        switch(type){
            case Clear:
                sql = "UPDATE clientaccount SET isActive = 0 WHERE id_client = ?";
       
            try {
                PreparedStatement pstm_Update_clear = (PreparedStatement) con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstm_Update_clear.setInt(1, getId_client());
                rowEffect = pstm_Update_clear.executeUpdate();
                if(rowEffect > 0){
                    sql = "UPDATE client SET firstBalance = ? WHERE id_client = ?";
                    PreparedStatement pstm_up_client = (PreparedStatement) con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    pstm_up_client.setDouble(1, getNewBalance());
                    pstm_up_client.setInt(2, getId_client());
                    int row = pstm_up_client.executeUpdate();
                    if(row == 1){
                        sql = "UPDATE receipt SET isActive = 0 WHERE id_client = ? ";
                        PreparedStatement pstm_update_receipt = (PreparedStatement) con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                        pstm_update_receipt.setInt(1, getId_client());
                        row = pstm_update_receipt.executeUpdate();
                        if(row > 0 ){
                            sql = "UPDATE salesinvoic SET isActive = 0 WHERE id_client = ?";
                            PreparedStatement pstm_sales_invoice = (PreparedStatement) con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                            pstm_sales_invoice.setInt(1, getId_client());
                            row = pstm_sales_invoice.executeUpdate();
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
    
    
    //update casher , receipt , clientaccount

    @Override
    public boolean Update(String id) {
        //Update Casher
        try{
            con = ConnectDB.getCon();
            con.setAutoCommit(false);
            String sql = "UPDATE casher SET ";
        }catch(SQLException ex){
        
        }
        
        return super.Update(id); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
