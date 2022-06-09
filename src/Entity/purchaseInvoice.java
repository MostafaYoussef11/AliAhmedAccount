/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Utilities.ConnectDB;
import Utilities.Tools;
import Utilities.invoice;
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
public class purchaseInvoice extends invoice{
    private Connection con;
    private PreparedStatement pstmt , pstcasher , pstItems;
    public purchaseInvoice() {
        
        super("purchaseInvoice");
    }

    @Override
    public String getLastIdItemsOnInvoice(String id_Invoic){
            return ConnectDB.LastId("itemsonpurchaseinvoice WHERE id_purchaseInvoice="+id_Invoic, "id");
    };

    @Override
    public boolean Save() {
        boolean isSave = false;
        try{
            con = ConnectDB.getCon();
            con.setAutoCommit(false);
            String sql_insert_purchase_invoice = "INSERT INTO `purchaseinvoice` (`id_purchaseInvoice`, `date_purchaseInvoice`, "
                    + "`type_purchaseInvoic`, `id_Suppliers`, `totalAmount`, `discount`, `amountCash`, `amountLater`, `note`) "
                    + "VALUES (?,?,?,?,?,?,?,?,?) ";
            pstmt = con.prepareStatement(sql_insert_purchase_invoice,Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, getId_invoice());
            pstmt.setString(2, getDate_invoice());
            pstmt.setString(3, Tools.payMethod(getPaymentMethod()));
            pstmt.setString(4, getId_Supplier());
            pstmt.setDouble(5, getAmount());
            pstmt.setDouble(6, getDiscont());
            pstmt.setDouble(7, getCashAmount());
            pstmt.setDouble(8, getRemainingAmount());
            pstmt.setString(9, getNote());
            int row_inserted = pstmt.executeUpdate();
            if(row_inserted == 1){
                int rowAffected = 0 ;
                switch(getPaymentMethod()){  
                    case cash:
                      String sqlInsertCasher ="INSERT INTO `casher` (`date_casher`, `Creditor`, `note`,`id_purchaseInvoice`) VALUES (?,?,?,?)";
                      pstcasher = con.prepareStatement(sqlInsertCasher, Statement.RETURN_GENERATED_KEYS);
                      pstcasher.setString(1, getDate_invoice());
                      pstcasher.setDouble(2, getCashAmount());
                      pstcasher.setString(3,getNote());
                      pstcasher.setString(4, getId_invoice());
                      rowAffected = pstcasher.executeUpdate();
                      break;
                    case deferred:
                        String sqlInserClientAccount = "INSERT INTO `suppliersaccount` (`date_suppliersAccount`, `Creditor`,`id_Suppliers`, `id_purchaseInvoice`,`note`) "
                                + "VALUES (?,?,?,?,?)";
                        pstcasher = con.prepareStatement(sqlInserClientAccount, Statement.RETURN_GENERATED_KEYS);
                        pstcasher.setString(1, getDate_invoice());
                        pstcasher.setDouble(2, getRemainingAmount());
                        pstcasher.setString(3, getId_Supplier());
                        pstcasher.setString(4, getId_invoice());
                        pstcasher.setString(5, getNote());
                        rowAffected = pstcasher.executeUpdate();
                        break;
                    case installments:
                      String sqlInsertCasherInsallmen ="INSERT INTO `casher` (`date_casher`, `Creditor`, `note`,`id_purchaseInvoice`) VALUES (?,?,?,?)";
                      pstcasher = con.prepareStatement(sqlInsertCasherInsallmen, Statement.RETURN_GENERATED_KEYS);
                      pstcasher.setString(1, getDate_invoice());
                      pstcasher.setDouble(2, getCashAmount());
                      pstcasher.setString(3,getNote());
                      pstcasher.setString(4, getId_invoice());
                      pstcasher.executeUpdate();
                      String sqlInserClientAccountInstallmen = "INSERT INTO `suppliersaccount` (`date_suppliersAccount`, `Creditor`,`id_Suppliers`, `id_purchaseInvoice`,`note`) "
                                + "VALUES (?,?,?,?,?)";
                      pstcasher = con.prepareStatement(sqlInserClientAccountInstallmen, Statement.RETURN_GENERATED_KEYS);
                      pstcasher.setString(1, getDate_invoice());
                      pstcasher.setDouble(2, getRemainingAmount());
                      pstcasher.setString(3, getId_Supplier());
                      pstcasher.setString(4, getId_invoice());
                      pstcasher.setString(5, getNote());
                      rowAffected = pstcasher.executeUpdate();
                      break;
                }
                if(rowAffected == 1){
                    getValues().forEach((i)->getValuesFromVector(i));
                    con.commit();
                    isSave = true;
                }else{
                    con.rollback();
                    isSave = false;
                }
                
            }
            con.close();
        }catch(SQLException ex){
           try{
              if(con != null) con.rollback();
            }
           catch(SQLException e){
                System.out.println(e.getMessage());
            }
            isSave =false;
            System.out.println(ex.getMessage());
        }
      finally {
            try {
                if(pstmt != null) pstmt.close();
                if(con != null) con.close(); 
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return isSave;
    }
    private void getValuesFromVector(ItemsOnInvoice i){
        try {
           String Sql = "INSERT INTO `itemsonpurchaseinvoice` (`id`, `id_items`, `name_items`, `qyt`, `name_unit`,"
                   + " `price`, `discount`, `Amount`, `id_purchaseInvoice`)"
                      + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstItems = con.prepareStatement(Sql, Statement.RETURN_GENERATED_KEYS);
            pstItems.setInt(1,i.id);
            pstItems.setInt(2, i.id_items);
            pstItems.setString(3, i.name_items);
            pstItems.setDouble(4, i.qyt);
            pstItems.setString(5, i.name_unit);
            pstItems.setDouble(6, i.price);
            pstItems.setDouble(7, i.discount);
            pstItems.setDouble(8, i.Amount);
            pstItems.setInt(9, i.id_Invoic);
            pstItems.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SalesInvoic.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    public static void fillTable(JTable jTable){
        String sql = "SELECT p.amountLater , p.amountCash , p.discount , p.totalAmount , s.name_Suppliers , p.type_purchaseInvoic , p.date_purchaseInvoice , p.id_purchaseInvoice FROM purchaseinvoice p INNER JOIN suppliers s on p.id_Suppliers = s.id_Suppliers";
        String[] colunm_names = { "الباقي", "المبلغ المدفوع", "الخصم", "الاجمالي", "المورد", "نوع الفاتورة", "التاريخ", "م"};
        ConnectDB.fillAndCenterTable(sql, jTable, colunm_names);
    }
}
