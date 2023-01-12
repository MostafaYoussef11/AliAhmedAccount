///6720216579322
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author mosta
 */
public class MillClass {
    private boolean isSave = false;
    private Connection con;
    private PreparedStatement pstmt , pstmtCasher , pstmtSupplier;
 public enum TypeCash {Cash , HafCash , Debit};   
 public void fillComboTypeExpens(JComboBox combo){
     ConnectDB.fillCombo("typeexpensmill", "name_typeexpensMill", combo);
 } 
 public void fillTableExpensMill(JTable table){
     String sql = "SELECT `note` , te.name_typeexpensMill , `amount`,`date_MillExpenes` , `id_MillExpenes`FROM `millexpenes` INNER JOIN typeexpensmill AS te ON te.id_typeexpensMill = millexpenes.id_typeexpensMill ORDER BY date_MillExpenes DESC";
     String[] ColumnsName = {"البيان", "البند", "المبلغ", "التاريخ", "م"};
     ConnectDB.fillAndCenterTable(sql, table, ColumnsName);
 
 }
 
 public boolean SaveTypeEpenesMill(String name_type){
        try {
            con = ConnectDB.getCon();
            String sql = "INSERT INTO `typeexpensmill` (`name_typeexpensMill`) VALUES (?)";
            pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, name_type);
            int rowAffect = pstmt.executeUpdate();
            if(rowAffect == 1){
                con.close();
                isSave = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MillClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSave;
     
 } 
 private int id_typeEpens(String nameType){
     int id = 0;  
     try {
            id = Integer.parseInt(ConnectDB.getIdFrmName("typeexpensmill", nameType));
        } catch (SQLException ex) {
            Logger.getLogger(MillClass.class.getName()).log(Level.SEVERE, null, ex);
        }
     return id;
 }
 private int id_Supplier (String nameSupplier){
     int id_Supplier = 0;   
     try {
            id_Supplier = Integer.parseInt(new Suppliers().getIdByName(nameSupplier));
            
        } catch (SQLException ex) {
            Logger.getLogger(MillClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id_Supplier;
 }
 public boolean SaveExpenes(TypeCash type ,double PayAmount,double Fullamount , String nameType ,String nameSupplier , String Note){
        try {
            CasherClass casher = new CasherClass();
            SupplierAccountClass supAccount = new SupplierAccountClass();
            int id_type = id_typeEpens(nameType);
            int id_Supplier = id_Supplier(nameSupplier);
            con = ConnectDB.getCon();
            con.setAutoCommit(false);
            String sqlInsert = "INSERT INTO `millexpenes` ( `amount`, `id_typeexpensMill`, `id_Suppliers`, `note`) VALUES (?,?,?,?)";
            pstmt = con.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            pstmt.setDouble(1, Fullamount);
            pstmt.setInt(2, id_type);
            pstmt.setInt(3, id_Supplier);
            pstmt.setString(4, Note);
            int rowAffect = pstmt.executeUpdate();
            if(rowAffect == 1){
                int id_expens = 0;
                ResultSet rst = pstmt.getGeneratedKeys();
                while(rst.next()){
                    id_expens = rst.getInt(1);
                }
                int rowCashe = 0;
                int RowSupp = 0;
                switch(type){
                    case Cash:
                         pstmtCasher =  casher.SavedCasherTransaction(TypeCasherTransaction.MillExpens, Fullamount, Note, id_expens, con);
                         rowCashe = pstmtCasher.executeUpdate();
                         if(rowCashe == 1){
                             con.commit();
                             con.close();
                             isSave = true;
                         }else{
                              con.rollback();
                              con.close();
                          }
                         break;
                    case HafCash:
                        pstmtCasher = casher.SavedCasherTransaction(TypeCasherTransaction.MillExpens, PayAmount, Note, id_expens, con);
                        rowCashe = pstmtCasher.executeUpdate();
                        if(rowCashe == 1){
                          pstmtSupplier = supAccount.SavedSupplierAccountTransaction(TypeCasherTransaction.MillExpens, (Fullamount-PayAmount),nameSupplier, Note, id_expens, con);
                          RowSupp = pstmtSupplier.executeUpdate();
                          if(RowSupp == 1){
                             con.commit();
                             con.close();
                             isSave = true;
                          }else{
                              con.rollback();
                              con.close();
                          }
                        }
                        break;
                    case Debit:
                        pstmtSupplier = supAccount.SavedSupplierAccountTransaction(TypeCasherTransaction.MillExpens, Fullamount,nameSupplier, Note, id_expens, con);
                        RowSupp = pstmtSupplier.executeUpdate();
                          if(RowSupp == 1){
                             con.commit();
                             con.close();
                             isSave = true;
                          }else{
                              con.rollback();
                              con.close();
                          }
                        
                        break;
                }
            }
            

        } catch (SQLException ex) {
            Logger.getLogger(MillClass.class.getName()).log(Level.SEVERE, null, ex);
        }
     
 
    return isSave;
 }
 
 
    
}
