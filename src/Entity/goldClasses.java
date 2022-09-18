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

/**
 *
 * @author mosta
 */
public class goldClasses {
    private boolean isSaveExpens, isSaveExportAccount = false;
    private Connection con;
    private PreparedStatement pstmt ;
    private CasherClass casher;
    
    
    public boolean SaveExportAccount(double amount , int idAccount , int idSupplier , String note ){
        try{
            con = ConnectDB.getCon();
            con.setAutoCommit(false);
            casher = new CasherClass();
            //Save on export Table
            String sqlInsertExport = "INSERT INTO `exports` "
                    + "(`price_export`, `id_account`, `note`, `id_Suppliers`)"
                    + " VALUES (?,?,?,?)";
            pstmt = con.prepareStatement(sqlInsertExport,Statement.RETURN_GENERATED_KEYS);
            pstmt.setDouble(1, amount);
            pstmt.setInt(2, idAccount);
            pstmt.setString(3, note);
            pstmt.setInt(4,idSupplier);
            //saved in exports table
            int rowAffectExport =  pstmt.executeUpdate();
            if(rowAffectExport == 1){
              // save on Casher table
               int rowAffected = 0;
               if(idSupplier == 1){
                  PreparedStatement pstmCasher = casher.SavedCasherTransaction(TypeCasherTransaction.accountExport, amount, note, idAccount, con);
                  rowAffected = pstmCasher.executeUpdate();

               }else{
                   String SqlInsertSupplierAccount = "INSERT INTO `suppliersaccount` (`Creditor`, `id_Suppliers`,`id_account `, `note`) VALUES (?,?,?,?)";
                   PreparedStatement pstmtSuppAccount = con.prepareStatement(SqlInsertSupplierAccount , Statement.RETURN_GENERATED_KEYS);
                   pstmtSuppAccount.setDouble(1, amount);
                   pstmtSuppAccount.setInt(2, idSupplier);
                   pstmtSuppAccount.setInt(3, idAccount);
                   pstmtSuppAccount.setString(4, note);
                   rowAffected = pstmtSuppAccount.executeUpdate();
                   
               } 
              if(rowAffected == 1){
                      con.commit();
                      con.close();
                      isSaveExportAccount = true;
               }
            }
           
        }catch(SQLException ex){
            try {
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(goldClasses.class.getName()).log(Level.SEVERE, null, ex1);
            }
            isSaveExportAccount = false;
            Logger.getLogger(goldClasses.class.getName()).log(Level.SEVERE, null, ex);

        }
        
      return isSaveExportAccount;
    }
    
    public boolean SaveExpensGold(){
        try {
            con = ConnectDB.getCon();
            con.setAutoCommit(false);
            casher = new CasherClass();
            
        } catch (SQLException ex) {
            Logger.getLogger(goldClasses.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSaveExpens;
    }
    
    public String getIdAccount(String nameAccount){
        String id = "";
        try {
            id = ConnectDB.getIdFrmName("account", nameAccount);
        } catch (SQLException ex) {
            Logger.getLogger(goldClasses.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    public String getIdWorkGroup(String nameWorkGroup){
        String id = "";
        try {   
            id = ConnectDB.getIdFrmName("workgroup", nameWorkGroup);
        } catch (SQLException ex) {
            Logger.getLogger(goldClasses.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
}


/*
INSERT INTO `suppliersaccount` (`Creditor`, `id_Suppliers`,`id_account `, `note`) VALUES (?,?,?,?)

**/