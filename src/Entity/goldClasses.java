/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Utilities.ConnectDB;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
    private boolean isSaveAssets, isSaveImport, isSaveExpens, isSaveExportAccount = false;
    private Connection con;
    private PreparedStatement pstmt ;
    private CasherClass casher;
    
    
    public boolean SaveExportAccount(double amount , int idAccount , int idSupplier , String note , int id_export){
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
                  PreparedStatement pstmCasher = casher.SavedCasherTransaction(TypeCasherTransaction.accountExport, amount, note, id_export, con);
                  rowAffected = pstmCasher.executeUpdate();

               }else{
                   String SqlInsertSupplierAccount = "INSERT INTO `suppliersaccount` (`Creditor`, `id_Suppliers`,`id_exports`, `note`) VALUES (?,?,?,?)";
                   PreparedStatement pstmtSuppAccount = con.prepareStatement(SqlInsertSupplierAccount , Statement.RETURN_GENERATED_KEYS);
                   pstmtSuppAccount.setDouble(1, amount);
                   pstmtSuppAccount.setInt(2, idSupplier);
                   pstmtSuppAccount.setInt(3, id_export);
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
                con.rollback();
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(goldClasses.class.getName()).log(Level.SEVERE, null, ex1);
            }
            isSaveExportAccount = false;
            Logger.getLogger(goldClasses.class.getName()).log(Level.SEVERE, null, ex);

        }
        
      return isSaveExportAccount;
    }
    
    public boolean SaveExpensGold(double price , int id_work_group , String note , int id_suppliers , int id_expens){
        try {
            con = ConnectDB.getCon();
            con.setAutoCommit(false);
            casher = new CasherClass();
            String sqlInsertExpens = "INSERT INTO `expens` (`price_expens`, `id_workgroup`, `note`, `id_Suppliers`)"
                    + " VALUES (?,?,?,?)";
            pstmt = con.prepareStatement(sqlInsertExpens, Statement.RETURN_GENERATED_KEYS);
            pstmt.setDouble(1, price);
            pstmt.setInt(2, id_work_group);
            pstmt.setString(3, note);
            pstmt.setInt(4, id_suppliers);
            int rowExpensAffect = pstmt.executeUpdate();
            if(rowExpensAffect == 1){
                // if Cash money insert into Casher
                int rowAffect = 0;
                if(id_suppliers == 1){
                    PreparedStatement pstmtCash = casher.SavedCasherTransaction(TypeCasherTransaction.expensGold, price, note, id_expens, con);
                    rowAffect = pstmtCash.executeUpdate();
                }else{
                    String SqlInsertSupplierAccount = "INSERT INTO `suppliersaccount` (`Creditor`, `id_Suppliers`,`id_expens`, `note`) VALUES (?,?,?,?)";
                    PreparedStatement pstmtSuppliers = con.prepareStatement(SqlInsertSupplierAccount, Statement.RETURN_GENERATED_KEYS);
                    pstmtSuppliers.setDouble(1, price);
                    pstmtSuppliers.setInt(2, id_suppliers);
                    pstmtSuppliers.setInt(3, id_expens);
                    pstmtSuppliers.setString(4, note);
                    rowAffect = pstmtSuppliers.executeUpdate();
                }
                if(rowAffect == 1){
                   con.commit();
                   con.close();
                   isSaveExpens = true;
                }
            }
        } catch (SQLException ex) {
            try {
                con.rollback();
                con.close();
                Logger.getLogger(goldClasses.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(goldClasses.class.getName()).log(Level.SEVERE, null, ex1);
            }
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
    
    public boolean SaveImportGold(double wight , int caliber , double price , double amount , int id_workGroup , String pathImage , int id_imports){
        try {
            casher = new CasherClass();
            con = ConnectDB.getCon();
            con.setAutoCommit(false);
            String sqlInsertInImports = "INSERT INTO `imports` "
                    + "(`wight_imports`, `caliber`, `price_imports`, `amount_imports`, `id_workgroup`, `path`)"
                    + " VALUES (?,?,?,?,?,?)";
            pstmt = con.prepareStatement(sqlInsertInImports, Statement.RETURN_GENERATED_KEYS);
            pstmt.setDouble(1, wight);
            pstmt.setInt(2, caliber);
            pstmt.setDouble(3, price);
            pstmt.setDouble(4, amount);
            pstmt.setInt(5, id_workGroup);
            InputStream input = new FileInputStream(pathImage);
            pstmt.setString(6, pathImage);
            int rowImportAffect = pstmt.executeUpdate();
            if(rowImportAffect == 1 ){
                String note = "فاتورة وزن " + wight ;
                PreparedStatement pstmtCasher = casher.SavedCasherTransaction(TypeCasherTransaction.importsGold, amount, note, id_imports, con);
                int rowCasherAffect = pstmtCasher.executeUpdate();
                if(rowCasherAffect == 1){
                    con.commit();
                    con.close();
                    isSaveImport = true;
                }else{
                    con.rollback();
                    con.close();
                    isSaveImport = false;
                }
            }else{
                con.rollback();
                con.close();
                isSaveImport = false;
            }    
            
            
        } catch (SQLException | FileNotFoundException ex) {
            try {
                con.rollback();
                con.close();
                Logger.getLogger(goldClasses.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(goldClasses.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
        }
        return isSaveImport;
    }

    public String getPathImage(String id_imports){
        String Sql = "Select path as id From imports where id_import ="+id_imports;
        return ConnectDB.getIdFromName(Sql);
    }
    //
    public boolean SaveAssets(double price , int id_workgroup , String note ,int id_suppliers , int id_assets){
        try {
            casher = new CasherClass();
            con = ConnectDB.getCon();
            con.setAutoCommit(false);
            String sqlInsertAssets = "INSERT INTO `assets` (`price_assets`, `notes`, `id_workgroup`)"
                    + " VALUES (?,?,?)";
            pstmt = con.prepareStatement(sqlInsertAssets, Statement.RETURN_GENERATED_KEYS); 
            pstmt.setDouble(1, price);
            pstmt.setString(2, note);
            pstmt.setInt(3, id_workgroup);
            int rowAssetsAffect = pstmt.executeUpdate();
            if(rowAssetsAffect == 1){
                int rowAffect = 0;
                if(id_suppliers == 1){
                    PreparedStatement pstmtCash = casher.SavedCasherTransaction(TypeCasherTransaction.assetsGold, price, note, id_assets, con);
                    rowAffect = pstmtCash.executeUpdate();
                }else{
                    String SqlInsertSupplierAccount = "INSERT INTO `suppliersaccount` (`Creditor`, `id_Suppliers`,`id_assets`, `note`) VALUES (?,?,?,?)";
                    PreparedStatement pstmtSuppliers = con.prepareStatement(SqlInsertSupplierAccount, Statement.RETURN_GENERATED_KEYS);
                    pstmtSuppliers.setDouble(1, price);
                    pstmtSuppliers.setInt(2, id_suppliers);
                    pstmtSuppliers.setInt(3, id_assets);
                    pstmtSuppliers.setString(4, note);
                    rowAffect = pstmtSuppliers.executeUpdate();
                }
                if(rowAffect == 1){
                   con.commit();
                   con.close();
                   isSaveAssets = true;
                }
            }
            
        } catch (SQLException ex) {
            try {
                con.rollback();
                con.close();
                Logger.getLogger(goldClasses.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(goldClasses.class.getName()).log(Level.SEVERE, null, ex1);
            }
            isSaveAssets = false;
        }
        return isSaveAssets;
    }
}


/*
INSERT INTO `suppliersaccount` (`Creditor`, `id_Suppliers`,`id_account `, `note`) VALUES (?,?,?,?)

**/