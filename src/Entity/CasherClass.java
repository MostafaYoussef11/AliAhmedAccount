/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Utilities.ConnectDB;
import Utilities.Tools;
import Utilities.saveData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author mosta
 */
public class CasherClass {
    private String TableName = "casher";
    private Connection con , conn;
    private PreparedStatement pstm;
    
  public String getNoteFromIdPaymentRecipt(String id_PaymentRecipt){
      String note = "";
      String sql = "SELECT note AS id FROM "+TableName+" WHERE id_PaymentReceipt="+id_PaymentRecipt;
      note = ConnectDB.getIdFromName(sql);
      return note;
  } 
  public String getNoteFromIdRecipt(String id_recipt){
     String sql = "SELECT note AS id FROM "+TableName+" WHERE id_Receipt="+id_recipt;
     return ConnectDB.getIdFromName(sql);
  }
  
  public String getIdCasherFromIdPaymentRecipt(String id_PaymentRecipt){
      String id_casher = "";
      String sql = "SELECT id_casher  AS id FROM "+TableName+" WHERE id_PaymentReceipt="+id_PaymentRecipt;
      id_casher = ConnectDB.getIdFromName(sql);
      return id_casher;
  }  
  
  
  public boolean Delete(String id_cahser){
     boolean isDel = false;

     try{
         con = ConnectDB.getCon();
         String sql = "DELETE FROM "+TableName+" WHERE id_casher="+id_cahser;
         pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
         int row = pstm.executeUpdate();
         if(row == 1){
             isDel = true;
         }
     }catch(SQLException ex){
         isDel = false;
     }
     return isDel;
  }
  
  public boolean DeleteByIdPaymentRecipt(String id_PaymentRecipt){
      String id_casher = getIdCasherFromIdPaymentRecipt(id_PaymentRecipt);
      return Delete(id_casher);
  } 
  
  public String getNowBalanceCasher(){
      String sql = "select newbalance as id from finallyday where dateFinally = CURRENT_DATE()";
      String now_balance = ConnectDB.getIdFromName(sql);      
      if(now_balance.isEmpty() || now_balance.equals("0")){
          //finesh the day rum event finally Day 
          String sql_finaly_day = "INSERT INTO `finallyday`( `dateFinally`, `oldBalance`, `totalimport`,"
                  + " `totalexport`,`time_insert` ) VALUES( CURRENT_DATE(),"
                  + "IFNULL(( SELECT f.newbalance FROM finallyday AS f WHERE f.dateFinally = CURRENT_DATE() - INTERVAL 1 DAY),0)"
                  + ", ( SELECT COALESCE(SUM(c.Debit), 0) FROM casher AS c WHERE c.date_casher = CURRENT_DATE()),"
                  + " ( SELECT COALESCE(SUM(c.Creditor), 0) FROM casher AS c WHERE c.date_casher = CURRENT_DATE())"
                  + ",current_timestamp())"; 
         boolean is_insert = ConnectDB.ExucuteAnyQuery(sql_finaly_day);
         if(is_insert){
             now_balance = ConnectDB.getIdFromName(sql);
         }
 
      }

      double nbalance = Double.parseDouble(now_balance);
      NumberFormat formatter =  new DecimalFormat("0.00");
      formatter.format(nbalance);
      
      return " " + nbalance;
  }
  
  
  public PreparedStatement SavedCasherTransaction(TypeCasherTransaction type , double amount , String note , int id , Connection con){
      int rowAffect = 0;
      String sql_insert_casher = getSQlStatement(type);
      PreparedStatement pstmt = null;
      try{
         // con = ConnectDB.getCon();
          pstmt = con.prepareStatement(sql_insert_casher);
          pstmt.setDouble(1, amount);
          switch (type) {
              case CasherTwoCredit:
                  pstmt.setString(2, note);
                  pstmt.setInt(3, saveData.getIdUser());
                  break;
              case CasherTwoDebit:
                  pstmt.setString(2, note);
                  pstmt.setInt(3, saveData.getIdUser());
                  break;
              default:
                  pstmt.setString(2, note);
                  pstmt.setInt(3, saveData.getIdUser());
                  pstmt.setInt(4, id);
                  break;
          }
          //rowAffect = pstm.executeUpdate();
      } catch (SQLException ex) {
              Logger.getLogger(CasherClass.class.getName()).log(Level.SEVERE, null, ex);
      }
      return pstmt;
  
  }
 public double getBalanceCasherTwo(){
     double BalanceCasher;
     BalanceCasher = 0.00;
     String sql = "Select ifnull(Balance,0) as id from cashertwobalance";
     String balance = ConnectDB.getIdFromName(sql);
     if(!(balance.equals(""))){
        BalanceCasher = Double.parseDouble(balance);
     }else{
         BalanceCasher = 0.00;
     }
     return BalanceCasher;
 } 
  
  
 public boolean SaveTransactionBetweenCasher(double amount , TypeCasherTransaction type){
     boolean isSave = false;
     PreparedStatement pstmcash ;
     int rowCahserAffect = 0;
     
     String sql_insert = "";
     try{
         conn = ConnectDB.getCon();
         conn.setAutoCommit(false);
         switch(type){
             case CasherTwoCredit:
                 pstmcash = SavedCasherTransaction(type, amount, "ترحيل الي جرد الثاني", 0 , conn);
                 rowCahserAffect = pstmcash.executeUpdate();
                 sql_insert = "INSERT INTO `cashertwo` (`Debit`) VALUES (?)";
                 break;
             case CasherTwoDebit:
                 pstmcash = SavedCasherTransaction(type, amount, "سحب من جرد الثاني", 0 ,conn);
                 rowCahserAffect = pstmcash.executeUpdate();
                 sql_insert = "INSERT INTO `cashertwo` (`Creditor`) VALUES (?)";
                 break;                
         }
         PreparedStatement _pstm = conn.prepareStatement(sql_insert);
         _pstm.setDouble(1, amount);
         
         if(rowCahserAffect == 1){
             int rowSave = _pstm.executeUpdate();
             if(rowSave == 1){
                conn.commit();
                conn.close();
                isSave = true;
             } 
         }
     }  catch (SQLException ex) {
             Logger.getLogger(CasherClass.class.getName()).log(Level.SEVERE, null, ex);
     }
     return isSave;
 }
 
 
  public String getSQlStatement(TypeCasherTransaction type){
     String sql_insert_casher = "";
      switch(type){
          case PosPay:  //id_masary_pay  17 Row
              sql_insert_casher = "INSERT INTO `casher` (`Debit`, `note`,`id_users`, `id_masary_pay`) VALUES (?,?,?,?)";
              break;
          case Receipt : //id_Receipt 21
              sql_insert_casher = "INSERT INTO `casher` (`Debit`, `note`,`id_users`,`id_Receipt`) VALUES (?,?,?,?)";
              break;
          case SalesInvoic: //id_salesInvoic
              sql_insert_casher = "INSERT INTO `casher` (`Debit`, `note`,`id_users`,`id_salesInvoic`) VALUES (?,?,?,?)";
              break; 
          case Send_VF: // id_Send_Receive 3 
               sql_insert_casher = "INSERT INTO `casher` (`Debit`, `note`,`id_users`,`id_Send_Receive`) VALUES (?,?,?,?)";
              break; 
          case CasherTwoDebit: // 
               sql_insert_casher = "INSERT INTO `casher` (`Debit`, `note`,`id_users`) VALUES (?,?,?)";
              break; 
          case Solfa: // id_Solf 1 
              sql_insert_casher = "INSERT INTO `casher` (`Debit`, `note`,`id_users`,`id_Solf`) VALUES (?,?,?,?)";
              break; 
          case excess: // في حالة الزيادة
               sql_insert_casher = "INSERT INTO `casher` (`Debit`, `note`,`id_users`,`id_deficiency_excess`) VALUES (?,?,?,?)";
              break;  
          case importsGold: // imports mony from gold
              sql_insert_casher = "INSERT INTO `casher` (`Debit`, `note`,`id_users`,`id_imports`) VALUES (?,?,?,?)";
              break;
          case getLoans :
              sql_insert_casher = "INSERT INTO `casher` (`Debit`, `note`,`id_users`,`id_loans`) VALUES (?,?,?,?)";
              break;
          case FilterOhda:
              sql_insert_casher = "INSERT INTO `casher` (`Debit`, `note`,`id_users`,`id_ohda`) VALUES (?,?,?,?)"; 
              break;
           
          case PosSell: // id_masary_sell 1
              sql_insert_casher = "INSERT INTO `casher` (`Creditor`, `note`,`id_users`,`id_masary_sell`) VALUES (?,?,?,?)";
              break;
          case PaymentReceipt: // id_PaymentReceipt
              sql_insert_casher = "INSERT INTO `casher` (`Creditor`, `note`,`id_users`,`id_PaymentReceipt`) VALUES (?,?,?,?)";
              break;
          case PurchaseInvoice://id_purchaseInvoice
              sql_insert_casher = "INSERT INTO `casher` (`Creditor`, `note`,`id_users`,`id_purchaseInvoice`) VALUES (?,?,?,?)";
              break;
          case Receive_VF: // id_Send_Receive
              sql_insert_casher = "INSERT INTO `casher` (`Creditor`, `note`,`id_users`,`id_Send_Receive`) VALUES (?,?,?,?)";
              break;  
          case Fees: //id_fees
              sql_insert_casher = "INSERT INTO `casher` (`Creditor`, `note`,`id_users`,`id_fees`) VALUES (?,?,?,?)";
              break;  
          case CasherTwoCredit:
              sql_insert_casher = "INSERT INTO `casher` (`Creditor`, `note`,`id_users`) VALUES (?,?,?)";
              break;
          case deficiency :  // في حالة العجز
               sql_insert_casher = "INSERT INTO `casher` (`Creditor`, `note`,`id_users`,`id_deficiency_excess`) VALUES (?,?,?,?)";
               break;
          case Paydebt:
              sql_insert_casher = "INSERT INTO `casher` (`Creditor`, `note`,`id_users`,`id_paydebt`) VALUES (?,?,?,?)";
              break;
          case Ohda: // العهد و المدخرات
              sql_insert_casher = "INSERT INTO `casher` (`Creditor`, `note`,`id_users`,`id_ohda`) VALUES (?,?,?,?)";
              break;
              
          case accountExport: // مسحوبات العمال و المعدات
              sql_insert_casher = "INSERT INTO `casher` (`Creditor`, `note`,`id_users`,`id_account`) VALUES (?,?,?,?)";
              break;
          case expensGold :// مصاريف الشغل
              sql_insert_casher = "INSERT INTO `casher` (`Creditor`, `note`,`id_users`,`id_expens`) VALUES (?,?,?,?)";
              break;
          case assetsGold : // تجهيزات و اساسيات
             sql_insert_casher = "INSERT INTO `casher` (`Creditor`, `note`,`id_users`,`id_assets`) VALUES (?,?,?,?)";
              break;
          
          case paymentLoans:
               sql_insert_casher = "INSERT INTO `casher` (`Creditor`, `note`,`id_users`,`id_tableOfLoans`) VALUES (?,?,?,?)";
              break; 
      }
      return sql_insert_casher;
  }
  
 
}
