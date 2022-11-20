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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

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


    public void FillComboWorkGroup(JComboBox combo){
       // String sql = "SELECT `name_workgroup` FROM `workgroup` WHERE`isActive`= 1";
        ConnectDB.fillCombo("workgroup WHERE`isActive`= 1", "name_workgroup", combo);
    
    }
    
    public double GetSumWights(String id_workGroup){
        String sql = "SELECT COALESCE(SUM(`wight_imports`),0) AS id FROM `imports` WHERE `isRelay` = 0 AND `id_workgroup` = "+id_workGroup;
        String str_sum_wight = ConnectDB.getIdFromName(sql);
        return Double.parseDouble(str_sum_wight);
    }
    public double GetSumAmount(String id_workGroup){
        String sql = "SELECT COALESCE(SUM(`amount_imports`),0) AS id FROM `imports` WHERE `isRelay` = 0 AND `id_workgroup` = "+id_workGroup;
        String str_sum_amount = ConnectDB.getIdFromName(sql);
        return Double.parseDouble(str_sum_amount);
    }
    public double GetSumExpens(String id_workGroup){
        //
        String sql = "SELECT COALESCE(SUM(price_expens),0) As id FROM `expens` WHERE isRelay = 0 AND `id_workgroup` = "+id_workGroup;
        String str_sum_amount = ConnectDB.getIdFromName(sql);
        return Double.parseDouble(str_sum_amount);
    }
    public int GetCountWorker(String id_workGroup){
    //SELECT COUNT(`id_account`) FROM `account` WHERE `isEnable` =1 AND `id_type` = 1
        String sql = "SELECT COUNT(ac.id_account) AS id FROM account ac INNER JOIN accountworkgroup cw ON ac.id_account = cw.id_account WHERE ac.id_type = 1 AND ac.isEnable = 1 AND cw.id_workgroup = "+id_workGroup;
        String str_Count = ConnectDB.getIdFromName(sql);
        return Integer.parseInt(str_Count);
    }
    
    
    public boolean SaveingClear(int id_workGroup , String note , double loaderShare , double thridShare , double oneWorkerShare){
        boolean isSave = false;
        try {
            Connection connection = ConnectDB.getCon();
            connection.setAutoCommit(false);
            //INSERT INTO `clear` (`notes`, `loadershare`, `ghorbalshare`, `carshare`, `workershare`) VALUES (?,?,?,?,?)
            String insertSql = "INSERT INTO `clear` (`notes`, `loadershare`, `ghorbalshare`, `carshare`, `workershare`) VALUES (?,?,?,?,?)";
            PreparedStatement pstmtClear = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            pstmtClear.setString(1,note);
            pstmtClear.setDouble(2, loaderShare);
            pstmtClear.setDouble(3, thridShare);
            pstmtClear.setDouble(4, thridShare);
            pstmtClear.setDouble(5, oneWorkerShare);
            int rowAffect = pstmtClear.executeUpdate();
            if(rowAffect == 1){
                int id_clear = 0;
                ResultSet rst = pstmtClear.getGeneratedKeys();
                while(rst.next()){
                    id_clear = rst.getInt(1);
                }
                boolean saveWorkerShare = insertClearWorker(id_workGroup, id_clear ,oneWorkerShare,note );
                if(saveWorkerShare){
                   // String sql_insert = "INSERT INTO `creditors` (`amount`, `id_account`, `id_clear`, `note`) VALUES (?,?,?,?)";
                    if(insertClearGhorbal(id_workGroup, id_clear, thridShare, note)){
                        if(insertClearCar(id_workGroup, id_clear, thridShare, note)){
                            connection.commit();
                            connection.close();
                            isSave = true;
                        }

                    }

                }
            }
            
          //  System.out.println("Count Worker : " + count_worker);
        } catch (SQLException ex) {
            Logger.getLogger(goldClasses.class.getName()).log(Level.SEVERE, null, ex);
        }
         return isSave;
    }
    public boolean insertClearWorker(int id_workGroup , int id_clear , double amount , String note){
        boolean isSave = false;
        try {
            Connection conn = ConnectDB.getCon();
            conn.setAutoCommit(false);
            String sql = "SELECT ac.`id_account` FROM `account` ac INNER JOIN `accountworkgroup` cw ON ac.`id_account` = cw.`id_account` WHERE ac.`id_type` = 1 AND cw.`id_workgroup`= "+id_workGroup;
            String[] ids_Worker = ConnectDB.getColumn(sql);
            int count_worker = ids_Worker.length;
            int c = 0;
            for(int i = 0 ; i < count_worker ; i++){
                String sql_insert = "INSERT INTO `creditors` (`amount`, `id_account`, `id_clear`, `note`) VALUES (?,?,?,?)";
                PreparedStatement pstmWorker = conn.prepareStatement(sql_insert, Statement.RETURN_GENERATED_KEYS);
                pstmWorker.setDouble(1, amount);
                pstmWorker.setInt(2, Integer.parseInt(ids_Worker[i]));
                pstmWorker.setInt(3, id_clear);
                pstmWorker.setString(4, note);
                c = c + pstmWorker.executeUpdate();
            }
            if(c == count_worker){
                isSave = true;
                conn.commit();
                conn.close();
            }
          //  System.out.println("Count Worker : " + count_worker);
        } catch (SQLException ex) {
            Logger.getLogger(goldClasses.class.getName()).log(Level.SEVERE, null, ex);
        }
         return isSave;
    }
    public boolean insertClearGhorbal(int id_workGroup , int id_clear , double amount , String note){
        boolean isSave = false;
        try {
            Connection conn = ConnectDB.getCon();
            conn.setAutoCommit(false);
            String sql = "SELECT ac.`id_account` As id FROM `account` ac INNER JOIN `accountworkgroup` cw ON ac.`id_account` = cw.`id_account` WHERE ac.`id_type` = 4 AND  isEnable = 1 AND cw.`id_workgroup`= "+id_workGroup;
            String sql_insert = "INSERT INTO `creditors` (`amount`, `id_account`, `id_clear`, `note`) VALUES (?,?,?,?)";
            PreparedStatement pstmWorker = conn.prepareStatement(sql_insert, Statement.RETURN_GENERATED_KEYS);
            pstmWorker.setDouble(1, amount);
            pstmWorker.setInt(2, Integer.parseInt(ConnectDB.getIdFromName(sql)));
            pstmWorker.setInt(3, id_clear);
            pstmWorker.setString(4, note);
            int rowAffect = pstmWorker.executeUpdate();
            if(rowAffect == 1){
                isSave = true;
                conn.commit();
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(goldClasses.class.getName()).log(Level.SEVERE, null, ex);
        }
         return isSave;
    }
    public boolean insertClearCar(int id_workGroup , int id_clear , double amount , String note){
        boolean isSave = false;
        try {
            Connection conn = ConnectDB.getCon();
            conn.setAutoCommit(false);
            String sql = "SELECT ac.`id_account` As id FROM `account` ac INNER JOIN `accountworkgroup` cw ON ac.`id_account` = cw.`id_account` WHERE ac.`id_type` = 2 AND isEnable = 1 AND cw.`id_workgroup`= "+id_workGroup;
            String sql_insert = "INSERT INTO `creditors` (`amount`, `id_account`, `id_clear`, `note`) VALUES (?,?,?,?)";
            PreparedStatement pstmWorker = conn.prepareStatement(sql_insert, Statement.RETURN_GENERATED_KEYS);
            pstmWorker.setDouble(1, amount);
            pstmWorker.setInt(2, Integer.parseInt(ConnectDB.getIdFromName(sql)));
            pstmWorker.setInt(3, id_clear);
            pstmWorker.setString(4, note);
            int rowAffect = pstmWorker.executeUpdate();
            if(rowAffect == 1){
                 isSave = true;
                conn.commit();
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(goldClasses.class.getName()).log(Level.SEVERE, null, ex);
        }
         return isSave;
    }
    
}


//SELECT ac.`id_account` FROM `account` ac INNER JOIN `accountworkgroup` cw ON ac.`id_account` = cw.`id_account` WHERE ac.`id_type` = 1 AND cw.`id_workgroup`= 1



/*
INSERT INTO `suppliersaccount` (`Creditor`, `id_Suppliers`,`id_account `, `note`) VALUES (?,?,?,?)

**/