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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author mosta
 */
public class FeesClass {
    private String name_expenseCategory;
    private Connection con;
    private PreparedStatement pstmt;
    
    public void fillComboFromCategory(JComboBox combo){
        ConnectDB.fillCombo("expensescategory", "name_expensesCategory", combo);
    }
    public ArrayList<String> notsFees(){
        ArrayList<String> Notes;
        Notes = new ArrayList<String>();
        try {
            Connection conn = ConnectDB.getCon();
            com.mysql.jdbc.Statement stmt = (com.mysql.jdbc.Statement) conn.createStatement();
            ResultSet rst = stmt.executeQuery("SELECT note  FROM fees GROUP BY note ");
            String elment;
            while (rst.next()) {
                elment = rst.getString(1);
                Notes.add(elment);
                
            }
           
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(PosClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Notes;
    
    }
    public void FillTable(JTable jTable){
        String sql = "SELECT f.amount , f.note , f.date_fees , f.id_fees FROM fees AS f";
        String[] columnName = {"المبلغ", "التاريخ", "البيان", "م"};
        ConnectDB.fillAndCenterTable(sql, jTable, columnName);
    
    }
    
    public boolean SaveFees(String note , String category , double amount){
        /**
         *      INSERT INTO `fees` (`amount`, `note`, `id_expensesCategory`) VALUES 
         */
        boolean isSave = false;
        try{
            con = ConnectDB.getCon();
            con.setAutoCommit(false);
            String sql_insert = "INSERT INTO `fees` (`amount`, `note`, `id_expensesCategory`) VALUES (?,?,?)";
            int id_expeses =Integer.parseInt(ConnectDB.getIdFrmName("expensesCategory", category));
            pstmt = con.prepareStatement(sql_insert, Statement.RETURN_GENERATED_KEYS);
            pstmt.setDouble(1, amount);
            pstmt.setString(2, note);
            pstmt.setInt(3, id_expeses);
            int rowAffect = pstmt.executeUpdate();
            ResultSet rst = pstmt.getGeneratedKeys();
            if(rowAffect == 1){
                int id_fees = 0;
                while(rst.next()){
                   id_fees = rst.getInt(1);
                }
               int rowAffectCasher =  new CasherClass().SavedCasherTransaction(TypeCasherTransaction.Fees, amount, note, id_fees);
               if(rowAffectCasher == 1){
                   con.commit();
                   con.close();
                   isSave = true;
               }
            }

        } catch (SQLException ex) {
            try {
                con.rollback();
                con.close();
                Logger.getLogger(FeesClass.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(FeesClass.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
        return isSave;
    }
}
