/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Utilities.ConnectDB;
import Utilities.Tools;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author mosta
 */
public class OhdaClass {
    /*
     INSERT INTO `ohda` ( `date_filtter`,`amount_ohda`, `name_recipient`,
     `note_ohda`, `isActive`)
     VALUES (NULL, 'curdate()', 'curtime().000000', NULL, '', '', NULL, '1')
     **/

    private String date_filtter;
    private String name_recipient;
    private String note_ohda;
    private boolean isActive;
    private double amount_ohda;

    //variable connection
    private Connection con;
    private PreparedStatement pstm_ohda, pstm_Casher;
    private ResultSet rst;
    private CasherClass cash;

    public int getIdTrustee(String name_trustee) {
        int id_trustee = 0;
        try {
            String id_st_trustee = ConnectDB.getIdFrmName("trustee", name_trustee);
            id_trustee = Integer.parseInt(id_st_trustee);
        } catch (SQLException ex) {
            Logger.getLogger(OhdaClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id_trustee;
    }

    public boolean SaveOhda(Date date_filtter, double amount_ohda, int id_trustee, String note_ohda) {
        boolean isSaved = false;
        cash = new CasherClass();
        try {
            con = ConnectDB.getCon();
            con.setAutoCommit(false);
            String sql = "INSERT INTO `ohda` ( `date_filtter`,`amount_ohda`, `id_trustee`,`note_ohda`) VALUES (?,?,?,?)";
            pstm_ohda = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            String date_filter = Tools.dateSql(date_filtter);
            pstm_ohda.setString(1, date_filter);
            pstm_ohda.setDouble(2, amount_ohda);
            pstm_ohda.setInt(3, id_trustee);
            pstm_ohda.setString(4, note_ohda);
            int rowOhda = pstm_ohda.executeUpdate();
            if (rowOhda == 1) {
                rst = pstm_ohda.getGeneratedKeys();
                int id_ohda = 0;
                while (rst.next()) {
                    id_ohda = rst.getInt(1);
                }
                pstm_Casher = cash.SavedCasherTransaction(TypeCasherTransaction.Ohda, amount_ohda, note_ohda, id_ohda, con);
                int rowAffectCash = pstm_Casher.executeUpdate();
                if (rowAffectCash == 1) {
                    con.commit();
                    con.close();
                    isSaved = true;
                } else {
                    con.rollback();
                    con.close();
                    isSaved = false;
                }
            } else {
                con.rollback();
                con.close();
                isSaved = false;
            }

        } catch (SQLException ex) {
            try {
                con.rollback();
                con.close();
                isSaved = false;
            } catch (SQLException ex1) {
                Logger.getLogger(OhdaClass.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            // con.close();
        }

        return isSaved;
    }

    public void FillTable(JTable table) {
        String SQlSelect = "SELECT note_ohda , amount_ohda , name_trustee , id_ohda FROM ohda as o inner join trustee as t on o.id_trustee = t.id_trustee  ";
        String[] columnName = {"ملاحظات", "المبلغ", "المستلم", "م"};
        ConnectDB.fillAndCenterTable(SQlSelect, table, columnName);

    }

    public double getBalanceOhda(String name_trstee) {
        int id_trustee = getIdTrustee(name_trstee);
        double balance = 0; //id_trustee 
        String sql = "Select Sum(amount_ohda) as id from ohda where id_trustee  = " + id_trustee + " And isActive=1";
        String balanceString = ConnectDB.getIdFromName(sql);
        if (balanceString == null) {
            balance = 0.00;
        } else {
            balance = Double.parseDouble(balanceString);
        }
        return balance;
    }

    public boolean SaveFilterOhda(double amount_ohda , String note_ohda , int id , String name_trstee , boolean isActive){
        boolean isSave = false;
        cash = new CasherClass();
        try {  
            con = ConnectDB.getCon();
            con.setAutoCommit(false);
            pstm_Casher = cash.SavedCasherTransaction(TypeCasherTransaction.FilterOhda, amount_ohda, note_ohda, id, con);
            int rowAffect = pstm_Casher.executeUpdate();
            if(rowAffect == 1){
                double newBalance = getBalanceOhda(name_trstee) - amount_ohda;
                int rowUpdate =0 ;
                String sqlUpdate = "";
                if(isActive){
                    sqlUpdate = "DELETE ohda where id_ohda = ? ";
                    pstm_ohda = con.prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
                    pstm_ohda.setInt(1, id);
                }else{
                    sqlUpdate = "UPDATE ohda SET amount_ohda = ? where id_ohda = ?";
                    pstm_ohda = con.prepareStatement(sqlUpdate, Statement.RETURN_GENERATED_KEYS);
                    pstm_ohda.setDouble(1, newBalance);
                    //pstm_ohda.setBoolean(2, isActive);
                    pstm_ohda.setInt(2, id);
                }

                rowUpdate = pstm_ohda.executeUpdate();
                if(rowUpdate == 1){
                    con.commit();
                    con.close();
                    isSave =true;
                }else{
                    con.rollback();
                    con.close();
                    isSave = false;
                }
                
            }
           
        } catch (SQLException ex) {
            try {
                con.rollback();
                con.close();
                isSave = false;
                Logger.getLogger(OhdaClass.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(OhdaClass.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
         return isSave;
    }
    
    
    public int getIdOhdaFromIdTrus(int id_turste){
       int id_ohda = 0;
       String SqlOhda = "select id_ohda as id from ohda where id_trustee ="+id_turste+" and isActive = 1 ";
       id_ohda = Integer.parseInt(ConnectDB.getIdFromName(SqlOhda));
       return id_ohda;
    }
}
