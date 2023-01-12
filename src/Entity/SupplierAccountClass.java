/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Utilities.saveData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mosta
 */
public class SupplierAccountClass {
    
    /**
     INSERT INTO `suppliersaccount` (`Creditor`, `id_Suppliers`,  `id_MillExpenes`, `note`) VALUES ()

     **/
   
    
  public String getSQlStatement(TypeCasherTransaction type){
     String sql_insert_casher = "";
      switch(type){
          case PosSell: // id_masary_sell 1
              sql_insert_casher = "";
              break;
          case PaymentReceipt: // id_PaymentReceipt
              sql_insert_casher = "";
              break;
          case PurchaseInvoice://id_purchaseInvoice
              sql_insert_casher = "";
              break;
          case Receive_VF: // id_Send_Receive
              sql_insert_casher = "";
              break;  
          case Fees: //id_fees
              sql_insert_casher = "";
              break;  
          case Paydebt:
              sql_insert_casher = "";
              break;
          case Ohda: // العهد و المدخرات
              sql_insert_casher = "";
              break;
              
          case accountExport: // مسحوبات العمال و المعدات
              sql_insert_casher = "";
              break;
          case expensGold :// مصاريف الشغل
              sql_insert_casher = "";
              break;
          case assetsGold : // تجهيزات و اساسيات
             sql_insert_casher = "";
              break;
              
          case ChefrExpens:
              sql_insert_casher = "";
              break;    
          case MillExpens:
              sql_insert_casher = "INSERT INTO `suppliersaccount` (`Creditor`, `id_Suppliers`,  `id_MillExpenes`, `note`) VALUES (?,?,?,?)";
              break;  
      }
      return sql_insert_casher;
  }
  
    public PreparedStatement SavedSupplierAccountTransaction(TypeCasherTransaction type ,double amount ,String nameSuppliers ,String note , int id , Connection con){
     
      String sql_insert_casher = getSQlStatement(type);
      PreparedStatement pstmt = null;
      try{
         // con = ConnectDB.getCon();
          int id_supplier = Integer.parseInt(new Suppliers().getIdByName(nameSuppliers));
          pstmt = con.prepareStatement(sql_insert_casher);
          pstmt.setDouble(1, amount);
          pstmt.setInt(2, id_supplier);
          pstmt.setInt(3, saveData.getIdUser());
          pstmt.setString(4, note);
      
      } catch (SQLException ex) {
              Logger.getLogger(CasherClass.class.getName()).log(Level.SEVERE, null, ex);
      }
      return pstmt;
  
  }
}
