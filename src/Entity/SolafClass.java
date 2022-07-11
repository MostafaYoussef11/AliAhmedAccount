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
public class SolafClass {
       
        // Variable To Data
       private Date datePay_Solf;// date NOT null,
       private String datePay_st;
       private double  amount_Solf ;//double(10,2) NOT null,
       private String name_Solf ; // varchar(255) NOT null,
       private String note_Solf ;//varchar(512)
       //Variable To Connection
       private Connection con;
       private PreparedStatement pstmt;
       private final CasherClass casher = new CasherClass();
       private TypeCasherTransaction type = TypeCasherTransaction.Solfa;
       
       public void fillTable(JTable table){
           String sql_Select = "SELECT `note_Solf` , `datePay_Solf`, `name_Solf`,  `amount_Solf`, `date_Solf` ,  `id_Solf`   FROM `solf` ORDER BY datePay_Solf ";
           String [] Column_Names = { "البيان", "تاريخ الاستحقاق", "الاسم", "المبلغ", "تاريخ الاستلام", "م"};
           ConnectDB.fillAndCenterTable(sql_Select, table, Column_Names);
       }
       
       public void SetDateSolfa(Date datePay_Solf , double amount_Solf , String name_Solf , String note_Solf ){
         this.datePay_st =  Tools.dateSql(datePay_Solf);
         this.amount_Solf = amount_Solf;
         this.name_Solf = name_Solf;
         this.note_Solf = note_Solf;
       }
       
       public boolean Save(){
           boolean isSave = false;
           try{
               con = ConnectDB.getCon();
               con.setAutoCommit(false);
               String sql_insert = "INSERT INTO `solf` (`datePay_Solf`, `amount_Solf`, `name_Solf`, `note_Solf`) VALUES(?,?,?,?)";
               pstmt = con.prepareStatement(sql_insert, Statement.RETURN_GENERATED_KEYS);
               pstmt.setString(1, datePay_st);
               pstmt.setDouble(2, amount_Solf);
               pstmt.setString(3, name_Solf);
               pstmt.setString(4, note_Solf);
               int rowAffected = pstmt.executeUpdate();
               ResultSet rst = pstmt.getGeneratedKeys();
               if(rowAffected == 1){
                   int id_Solfa = 0;
                   while (rst.next()) {                       
                       id_Solfa = rst.getInt(1);
                      
                   }
                  int rowCasher = casher.SavedCasherTransaction(type, amount_Solf, name_Solf + " " + note_Solf, id_Solfa);
                  if(rowCasher == 1){
                      isSave = true;
                      con.commit();
                      con.close();
                      
                  }
               }
           } catch (SQLException ex) {
               try {
                   isSave = false;
                   con.rollback();
                   con.close();
                   Logger.getLogger(SolafClass.class.getName()).log(Level.SEVERE, null, ex);
               } catch (SQLException ex1) {
                   Logger.getLogger(SolafClass.class.getName()).log(Level.SEVERE, null, ex1);
               }
           }
           return isSave;
       }
       
       

}
