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
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author mosta
 */
public class bankAccounts {
  private Connection con;
  private PreparedStatement pstmt , pstmt_Casher;
  private String number_acount , name_bank;
  private double first_debit , first_creditor;
  public enum typeTransaction {debit , credit};
  //Set Information Bank
  public void SetInfo(String number_acount , String name_bank , double first_debit , double first_creditor){
      this.number_acount = number_acount;
      this.name_bank = name_bank;
      this.first_creditor = first_creditor;
      this.first_debit = first_debit;
  }
  
  public void FillComboNameBank(JComboBox combo){
      //String sql = "";
      ConnectDB.fillCombo("nameBank", "name", combo);
  }
  public void FillJTable(JTable table){
     String sql = "SELECT `first_creditor` , `first_debit` , `name_bank` , `number_acount` FROM `bankaccount`";
     String[] CoulmnNames = { "دائن", "مدين", "البنك", "رقم الحساب"};
     ConnectDB.fillAndCenterTable(sql, table, CoulmnNames);
  
  }  
  //save new account Bank
  public boolean SaveNewAccount(){
     boolean isSave = false;
      try {
          con = ConnectDB.getCon();
          String sql = "INSERT INTO `bankaccount` (`number_acount`, `name_bank`, `first_debit`, `first_creditor`) VALUES (?,?,?,?)";
          pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
          pstmt.setString(1, number_acount);
          pstmt.setString(2, name_bank);
          pstmt.setDouble(3, first_debit);
          pstmt.setDouble(4, first_creditor);
          int rowAffect = pstmt.executeUpdate();
          if(rowAffect == 1){
              isSave = true;
          }
          con.close();
      } catch (SQLException ex) {
          Logger.getLogger(bankAccounts.class.getName()).log(Level.SEVERE, null, ex);
      }finally{
         try {
             con.close();
         } catch (SQLException ex) {
             Logger.getLogger(bankAccounts.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
    return isSave;
  }  
  
  
  
  public double getFBalance(String numberAccount){
      double balance = 0;
      double fDebit , fCredit= 0.00;
      String st_first_debit = ConnectDB.getIdFromName("select first_debit as id from bankaccount where number_acount ='"+numberAccount+"'");
      String st_first_credit = ConnectDB.getIdFromName("select first_creditor as id from bankaccount where number_acount ='"+numberAccount+"'");
      if(st_first_debit != null){
          fDebit = Double.parseDouble(st_first_debit);
      }else{
          fDebit = 0;
      }
      if(st_first_credit != null){
          fCredit = Double.parseDouble(st_first_credit);
      }else{
          fCredit = 0.00;
      }
      balance = fDebit - fCredit;
      return balance;
  }

  public double getNowBalance(String numberAccount){
      double nowBalance = 0.0;
      String id_bank = ConnectDB.getIdFromName("SELECT `id_bank`As id FROM `bankaccount` WHERE `name_bank` = '"+numberAccount+"'");
      String str_sum_Debit = ConnectDB.getIdFromName("SELECT COALESCE(SUM(`debit`),0) AS id FROM `banktransaction` WHERE  `id_bank` = " + id_bank);
      String str_sum_Credit = ConnectDB.getIdFromName("SELECT COALESCE(SUM(``credit``),0) AS id FROM `banktransaction` WHERE  `id_bank` = " + id_bank);
      double sumDebit = Double.parseDouble(str_sum_Debit);
      double sumCredit = Double.parseDouble(str_sum_Credit);
      nowBalance = getFBalance(numberAccount)+ sumDebit - sumCredit;
      return nowBalance;
  }
  


public boolean SaveTransactionBanck(String numberAccount , double amount , String particulars ,typeTransaction type){
    boolean isSave = false;  
    try {
          con = ConnectDB.getCon();
          con.setAutoCommit(false);
          String sqlBank = "";
          switch(type){
              case debit :
                  //INSERT INTO `banktransaction` ( `id_bank`, `particulars`, `debit`, `credit`, `balance`) VALUES (?,?,?,?)
                  sqlBank = "INSERT INTO `banktransaction` ( `id_bank`, `particulars`, `debit`,`balance`) VALUES (?,?,?,?)";
                  break;
              case credit:
                  sqlBank = "INSERT INTO `banktransaction` ( `id_bank`, `particulars`, `credit`, `balance`) VALUES (?,?,?,?)";
                  break;
          
          }
          
    
      } catch (SQLException ex) {
          Logger.getLogger(bankAccounts.class.getName()).log(Level.SEVERE, null, ex);
      }
    return isSave;
};
}

