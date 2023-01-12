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
import javax.swing.JTable;

/**
 *
 * @author mosta
 */
public class goldClasses {
    private boolean isSaveAssets, isSaveImport, isSaveExpens, isSaveExportAccount = false;
    private Connection con;
    private PreparedStatement pstmt , pstmtCasher ;
    private CasherClass casher;
    
    public int getIdDeal(int id_workGroup){
        String id = ConnectDB.getIdFromName("SELECT `id_deal` as id FROM `workgroup` WHERE `id_workgroup` = "+id_workGroup);
        return  Integer.parseInt(id);
    }
    public void FillExpenesTable(String nameWork , JTable table){
        String id_workgroup = getIdWorkGroup(nameWork);
        String sql = "SELECT `note` , `price_expens` , `date_expens` , `id_expens` FROM `expens` WHERE `isRelay` = 0 AND `id_workgroup` = "+ id_workgroup;
        String[] nameColumn = {"البيان", "المبلغ", "التاريخ", "م"};
        ConnectDB.fillAndCenterTable(sql, table, nameColumn);
    }
    public boolean disableExpensById(String id_expens){
        String sql = "UPDATE `expens` SET `isRelay`= 1 WHERE `id_expens` = "+id_expens;
        return ConnectDB.ExucuteAnyQuery(sql);
    }
    
    public void FillImportAccountsTable(JTable table){
        String sql = "SELECT cr.note, ac.name_account, cr.amount, cr.date_credit FROM creditors AS cr INNER JOIN account AS ac ON ac.id_account = cr.id_account ORDER BY cr.date_credit DESC";
        String[] columsName = {"البيان", "الاسم", "المبلغ", "التاريخ"};
        ConnectDB.fillAndCenterTable(sql, table, columsName);
    }
    
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
                if(id_expens == 0){
                    rowAffect = 1;
                }else{
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
    public void FillComboAccount(JComboBox combo){
       // String sql = "SELECT `name_workgroup` FROM `workgroup` WHERE`isActive`= 1";
        ConnectDB.fillCombo("account WHERE isEnable = 1", "name_account", combo);
    
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
    public double GetSumAssest(String id_workGroup){
        String sql = "SELECT COALESCE(SUM( `price_assets`) , 0) AS id FROM `assets` WHERE `isRelay` = 0  AND `id_workgroup` = "+id_workGroup;
        String str_sum_amount = ConnectDB.getIdFromName(sql);
        return Double.parseDouble(str_sum_amount);
    }
    
    public  boolean SaveingClear( boolean isRent , int id_account , double rentLoder ,int id_workGroup , String note , double loaderShare , double thridShare , double oneWorkerShare){
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
                System.out.println("Save on Clear");
                boolean saveWorkerShare = insertClearWorker(connection , id_workGroup, id_clear ,oneWorkerShare,note );
                if(saveWorkerShare){
                    System.out.println("Save Worker");
                    if(insertClearGhorbal(connection,id_workGroup, id_clear, thridShare, note)){
                        System.out.println("Save Ghorbal");
                        if(insertClearCar(connection,id_workGroup, id_clear, thridShare, note)){
                            System.out.println("Save Car");
                            if(UpdateExpensAndImports(connection,id_workGroup, id_clear)){
                                System.out.println("Update Expens and Import");
                                if(insertClearLodar(isRent , connection,id_account,id_workGroup,rentLoder,loaderShare,id_clear,note)){
                                    System.out.println("Save Lodar");
                                    connection.commit();
                                    connection.close();
                                    isSave = true;
                                }
                            }
//                            connection.commit();
//                            connection.close();
//                            isSave = true;
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
    private boolean insertClearWorker(Connection conn ,int id_workGroup , int id_clear , double amount , String note){
        boolean isSave = false;
        try {
            //Connection conn = ConnectDB.getCon();
           // conn.setAutoCommit(false);
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
                //conn.commit();
                //conn.close();
            }
          //  System.out.println("Count Worker : " + count_worker);
        } catch (SQLException ex) {
            Logger.getLogger(goldClasses.class.getName()).log(Level.SEVERE, null, ex);
        }
         return isSave;
    }
    private boolean insertClearGhorbal(Connection conn ,int id_workGroup , int id_clear , double amount , String note){
        boolean isSave = false;
        try {
           // Connection conn = ConnectDB.getCon();
           // conn.setAutoCommit(false);
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
                //conn.commit();
                //conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(goldClasses.class.getName()).log(Level.SEVERE, null, ex);
        }
         return isSave;
    }
    private boolean insertClearCar(Connection conn ,int id_workGroup , int id_clear , double amount , String note){
        boolean isSave = false;
        try {
            //Connection conn = ConnectDB.getCon();
            //conn.setAutoCommit(false);
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
              //  conn.commit();
               // conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(goldClasses.class.getName()).log(Level.SEVERE, null, ex);
        }
         return isSave;
    }
    private boolean UpdateExpensAndImports(Connection connection ,int id_workGroup , int id_clear){
        boolean isUpDate = false;
        try {
           // Connection connection = ConnectDB.getCon();
          //  connection.setAutoCommit(false);
            String sqlUdateIdClear = "UPDATE `imports` SET `id_clear`= ? WHERE `id_workgroup` = ? AND `isRelay`= 0";
            PreparedStatement pst = connection.prepareStatement(sqlUdateIdClear);
            pst.setInt(1, id_clear);
            pst.setInt(2, id_workGroup);
            int isUpdateImport = pst.executeUpdate();
            if(isUpdateImport > 0){
                System.out.println("Update Row import = " + isUpdateImport);
                sqlUdateIdClear = "UPDATE `expens` SET `id_clear`= ? WHERE `id_workgroup` = ? AND `isRelay` = 0";
                PreparedStatement pst_Expens = connection.prepareStatement(sqlUdateIdClear);
                pst_Expens.setInt(1, id_clear);
                pst_Expens.setInt(2, id_workGroup);
                int isUpdateEpens = pst_Expens.executeUpdate();
                if(isUpdateEpens > 0){
                    System.out.println("Update Row Epens = " + isUpdateEpens);
                    String Sql_updateIsRelyImport = "UPDATE `imports` SET `isRelay`= 1 WHERE `id_clear` = ? And `id_workgroup`= ?";
                    PreparedStatement pst_update_im = connection.prepareStatement(Sql_updateIsRelyImport);
                    pst_update_im.setInt(1, id_clear);
                    pst_update_im.setInt(2, id_workGroup);
                    int isUpdateIs = pst_update_im.executeUpdate();
                    if(isUpdateIs > 0){
                        System.out.println("Update Row import = " + isUpdateIs);
                        String sql_update_isRely_expen = "UPDATE `expens` SET `isRelay` = 1 WHERE `id_clear` = ? And `id_workgroup`= ?";
                        PreparedStatement pst_update_Expens = connection.prepareStatement(sql_update_isRely_expen);
                        pst_update_Expens.setInt(1, id_clear);
                        pst_update_Expens.setInt(2, id_workGroup);
                        int updteExpens = pst_update_Expens.executeUpdate();
                        if(updteExpens > 0){
                            System.out.println("Update Row Expens = " + updteExpens);
                           // connection.commit();
                           // connection.close();
                            isUpDate = true;
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(goldClasses.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return isUpDate;//
    }
    private boolean insertClearLodar(boolean isRent , Connection connection , int id_acount ,int id_workGroup , double rentLoder ,double amount , int id_clear , String note){
        boolean isSave = false;
        try {
            //Connection connection = ConnectDB.getCon();
           // con.setAutoCommit(false);
            String sql_select_Lodar = "SELECT ac.`id_account` As id FROM `account` ac INNER JOIN `accountworkgroup` cw ON ac.`id_account` = cw.`id_account` WHERE ac.`id_type` = 3 AND isEnable = 1 AND cw.`id_workgroup`= "+id_workGroup;
            int id_acount_lodar = Integer.parseInt(ConnectDB.getIdFromName(sql_select_Lodar));
            String name_loderAccount = ConnectDB.getIdFromName("SELECT name_account AS id FROM id_account = " + id_acount_lodar);
            String sql_insert = "INSERT INTO `creditors` (`amount`, `id_account`, `id_clear`, `note`) VALUES (?,?,?,?)";
            PreparedStatement pstmLoder = connection.prepareStatement(sql_insert, Statement.RETURN_GENERATED_KEYS);
            pstmLoder.setDouble(1, amount);
            pstmLoder.setInt(2,id_acount);
            pstmLoder.setInt(3, id_clear);
            pstmLoder.setString(4, note);
            int rowAffect = pstmLoder.executeUpdate();
           //String notRent = 
            if(rowAffect == 1){
                String sql_inser_export = "INSERT INTO `exports` (`price_export`, `id_account`, `note`, `id_Suppliers`) VALUES (?,?,?,?)";
                PreparedStatement pst_export = connection.prepareStatement(sql_inser_export, Statement.RETURN_GENERATED_KEYS);
                pst_export.setDouble(1, rentLoder);
                pst_export.setInt(2, id_acount);
                
                pst_export.setString(3, "ايجار لودر " + name_loderAccount);
                pst_export.setInt(4, 1);
                int rowAffectExport = pst_export.executeUpdate();
                if(isRent){
                    isSave = true;
                }else{
                    if(rowAffectExport == 1){
                        String sql_insert2 = "INSERT INTO `creditors` (`amount`, `id_account`, `id_clear`, `note`) VALUES (?,?,?,?)";
                        PreparedStatement pstmLoder2 = connection.prepareStatement(sql_insert2, Statement.RETURN_GENERATED_KEYS);
                        pstmLoder2.setDouble(1, rentLoder);
                        pstmLoder2.setInt(2,id_acount_lodar);
                        pstmLoder2.setInt(3, id_clear);
                        pstmLoder2.setString(4,  "ايجار اللودر " );
                        int rowAffectRentLoder = pstmLoder2.executeUpdate();
                        if(rowAffectRentLoder == 1){
                            //connection.commit();
                            //connection.close();
                            isSave = true;
                        }
                    }
                }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(goldClasses.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSave;
    }
    public boolean SaveCreditAccount(String nameAccount , double amount , String note){
        boolean isSave = false;
        casher = new CasherClass();
        try {
            
            con = ConnectDB.getCon();
            con.setAutoCommit(false);
            String sqlInsertCredit = "INSERT INTO `creditors` (`amount`, `id_account`, `note`) VALUES (?,?,?)";
            pstmt = con.prepareStatement(sqlInsertCredit, Statement.RETURN_GENERATED_KEYS);
            pstmt.setDouble(1, amount);
            int id_account = Integer.parseInt(getIdAccount(nameAccount));
            pstmt.setInt(2, id_account);
            pstmt.setString(3, note);
            int rowAffect = pstmt.executeUpdate();
            if(rowAffect == 1){
                int id_credit = 0;
                ResultSet rst = pstmt.getGeneratedKeys();
                while(rst.next()){
                    id_credit = rst.getInt(1);
                }
                pstmtCasher = casher.SavedCasherTransaction(TypeCasherTransaction.accountImport, amount, note, id_credit, con);
                int rowCAsher = pstmtCasher.executeUpdate();
                if(rowCAsher == 1){
                    con.commit();
                    con.close();
                    isSave = true;
                }else{
                    con.rollback();
                    con.close();
                    isSave = false;
                }
            }
        } catch (SQLException ex) {
            
            Logger.getLogger(goldClasses.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSave;
    }
    
    public double GetBalanceAccount(String nameAccount){
        String id_account = getIdAccount(nameAccount);
        String Sql_firstBalance = "SELECT `balance_account` AS id FROM `account` WHERE `id_account`="+id_account;
        double firstBalance = Double.parseDouble(ConnectDB.getIdFromName(Sql_firstBalance));
        String SqlSumCredit = "SELECT COALESCE(SUM(`amount`),0) AS id FROM `creditors` WHERE `id_account`="+id_account;
        double Creditors = Double.parseDouble(ConnectDB.getIdFromName(SqlSumCredit));
        String SqlSumExport = "SELECT COALESCE(SUM(`price_export`),0) AS id FROM `exports` WHERE `id_account`="+id_account;
        double debit = Double.parseDouble(ConnectDB.getIdFromName(SqlSumExport));
        double balance =  firstBalance+Creditors-debit;
        return balance;
    }
}

