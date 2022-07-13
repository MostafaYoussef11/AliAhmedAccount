/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Utilities.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author mosta
 */
public class deficiency_excess {
    private int id_deficiency_excess;
    private double  amount;
    private String note;
    /**
     INSERT INTO `deficiency_excess`(`debit`, `note`)
     **/
    public void setData(double amount, String note){
        this.amount = amount;
        this.note = note;
    }
    
    
    // ترحيل العجز 
    public boolean SaveDeficiency () throws SQLException{
        boolean isSave = false;
        Connection con = ConnectDB.getCon();
        String sql = "INSERT INTO `deficiency_excess`(`debit`, `note`) VALUES(?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        pstmt.setDouble(1, amount);
        pstmt.setString(2, note);
        int rowAffect = pstmt.executeUpdate();
        if(rowAffect == 1){
            ResultSet rst = pstmt.getGeneratedKeys();
            while (rst.next()) {
                this.id_deficiency_excess = rst.getInt(1);
            }
           int inserCasher =  new CasherClass().SavedCasherTransaction(TypeCasherTransaction.deficiency, amount, note, id_deficiency_excess);
           if(inserCasher == 1){
               isSave = true;
           }
        }
    
        return isSave;
    }
    // ترحيل الزيادة
    public boolean SaveExcess () throws SQLException{
        boolean isSave = false;
        Connection con = ConnectDB.getCon();
        String sql = "INSERT INTO `deficiency_excess`(`credit`, `note`) VALUES(?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        pstmt.setDouble(1, amount);
        pstmt.setString(2, note);
        int rowAffect = pstmt.executeUpdate();
        if(rowAffect == 1){
            ResultSet rst = pstmt.getGeneratedKeys();
            while (rst.next()) {
                this.id_deficiency_excess = rst.getInt(1);
            }
           int inserCasher =  new CasherClass().SavedCasherTransaction(TypeCasherTransaction.excess, amount, note, id_deficiency_excess);
           if(inserCasher == 1){
               isSave = true;
           }
        }
    
        return isSave;
    }
}
