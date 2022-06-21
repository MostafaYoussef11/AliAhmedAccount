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


/**
 *
 * @author mosta
 */
public class CasherClass {
    private String TableName = "casher";
    private Connection con;
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
//      double debit_day = 0.00;
//      double credit_day = 0.00;
//      String sql_old_balance = "SELECT oldBalance as id FROM  finallyday WHERE dateFinally = CURRENT_DATE() ";
//      String oldBalanceStr = ConnectDB.getIdFromName(sql_old_balance);
//      double old_balance = Double.parseDouble(oldBalanceStr);
//      String sql_total_debit = "SELECT COALESCE(SUM(c.Debit), 0) as id FROM casher AS c WHERE c.date_casher = CURRENT_DATE() " ;
//      String debit_st = ConnectDB.getIdFromName(sql_total_debit);
//      if(debit_st != null){
//          debit_day = Double.parseDouble(debit_st);
//      }
//      String sql_total_credit = "SELECT COALESCE(SUM(c.Creditor), 0) AS id FROM casher AS c WHERE c.date_casher = CURRENT_DATE() " ;
//      String credit_st = ConnectDB.getIdFromName(sql_total_credit);
//      if(credit_st != null){
//          credit_day = Double.parseDouble(credit_st);
//      }
//      double now_balance = old_balance + debit_day - credit_day;
      String sql = "select newbalance as id from finallyday where dateFinally = CURRENT_DATE()";
      String now_balance = ConnectDB.getIdFromName(sql);
      return "الجرد : " + " " + now_balance;
  }
}
