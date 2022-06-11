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
    private PreparedStatement pstm;
    private ResultSet rst;
    public void fillTalble(JTable table){
        String sql = "SELECT r.amount , c.name_client , r.date_Receipt , r.id_Receipt FROM receipt r INNER JOIN client c on r.id_client = c.id_client where r.isActive = 1";
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
            String sqlInser = "INSERT INTO `casher` (`date_casher`, `Debit`, `note`,`id_Receipt`) VALUES (?,?,?,?)";
            pstm = (PreparedStatement) con.prepareStatement(sqlInser, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, getDate_process());
            pstm.setDouble(2, getAmount());
            pstm.setString(3, getNote());
            pstm.setInt(4, getId_Receipt());
            int rowAffect = pstm.executeUpdate();
            if(rowAffect == 1){
                String sqlInserrecipt = "INSERT INTO `receipt` (`id_Receipt`, `date_Receipt`, `amount`, `id_client`) VALUES (?,?,?,?)";
                pstm = (PreparedStatement) con.prepareStatement(sqlInserrecipt, Statement.RETURN_GENERATED_KEYS);
                pstm.setInt(1, getId_Receipt());
                pstm.setString(2, getDate_process());
                pstm.setDouble(3, getAmount());
                pstm.setInt(4, getId_client());
                int roweffect = pstm.executeUpdate();
                rst = pstm.getGeneratedKeys();
                if(roweffect == 1){
                    String sqlInserClienAccount = "INSERT INTO `clientaccount` (`date_ClientAccount`,`Creditor`, `id_client`,`id_Receipt`, `note`) "
                                                + "VALUES (?,?,?,?,?)";
                    pstm = (PreparedStatement) con.prepareStatement(sqlInserClienAccount, Statement.RETURN_GENERATED_KEYS);
                    pstm.setString(1, getDate_process());
                    pstm.setDouble(2, getAmount());
                    pstm.setInt(3, getId_client());
                    pstm.setInt(4, getId_Receipt());
                    pstm.setString(5, getNote());
                    int row = pstm.executeUpdate();
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
                pstm = (PreparedStatement) con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstm.setInt(1, getId_client());
                rowEffect = pstm.executeUpdate();
                if(rowEffect > 0){
                    sql = "UPDATE client SET firstBalance = ? WHERE id_client = ?";
                    pstm = (PreparedStatement) con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    pstm.setDouble(1, getNewBalance());
                    pstm.setInt(2, getId_client());
                    int row = pstm.executeUpdate();
                    if(row == 1){
                        sql = "UPDATE receipt SET isActive = 0 WHERE id_client = ? ";
                        pstm = (PreparedStatement) con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                        pstm.setInt(1, getId_client());
                        row = pstm.executeUpdate();
                        if(row > 0 ){
                            sql = "UPDATE salesinvoic SET isActive = 0 WHERE id_client = ?";
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
