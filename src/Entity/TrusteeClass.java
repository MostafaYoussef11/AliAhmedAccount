/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Utilities.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author mosta
 */
public class TrusteeClass {

    private String id_trustee , name_trustee;
    private double balance;
    
    
    public int getLastId(){
        String id = ConnectDB.LastId("trustee", "id_trustee");
        return Integer.parseInt(id);
    }

    public String getId_trustee() {
        return id_trustee;
    }

    public void setId_trustee(String id_trustee) {
        this.id_trustee = id_trustee;
    }

    public String getName_trustee() {
        return name_trustee;
    }

    public void setName_trustee(String name_trustee) {
        this.name_trustee = name_trustee;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public boolean Save(){
         boolean isSave = false;
        try {
           
            String SQl = "INSERT INTO `trustee` (`name_trustee`, `balance`) VALUES (?,?) ;";
            Connection con = ConnectDB.getCon();
            PreparedStatement pstmt = con.prepareStatement(SQl, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, getName_trustee());
            pstmt.setDouble(2, getBalance());
            int rowAffect = pstmt.executeUpdate();
            if(rowAffect == 1){
                isSave = true;
                
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(TrusteeClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return isSave;
    }
    
    public boolean UpdateBalance(int id_trustee){
        String SQL = "UPDATE trustee SET balance = "+ getBalance() +" WHERE id_trustee ="+id_trustee;
        return ConnectDB.ExucuteAnyQuery(SQL);
    }
    
    public double getOldBalance (int id_trustee){
        String SQL = "SELECT balance AS id FROM trustee WHERE id_trustee = "+id_trustee;
        String StBalance = ConnectDB.getIdFromName(SQL);
        return Double.parseDouble(StBalance);
    }
    
    public void FillTable(JTable table){
        String SQL = "SELECT balance , name_trustee , id_trustee FROM trustee ";
        String[] columnName = {"الرصيد", "الاسم", "م"};
        ConnectDB.fillAndCenterTable(SQL, table, columnName);
    }
    
}
