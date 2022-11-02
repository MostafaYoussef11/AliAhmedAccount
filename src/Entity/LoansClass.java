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

/**
 *
 * @author mosta
 */
public class LoansClass {
   
   private int id_loans  , countOfMonth;
   private String name_loans;
   private double amount , priceOfMonth , feesOfLoans , sumOfAmount;
   private boolean isSaveLoan;
   private CasherClass casher;
   private Connection con;
   private PreparedStatement pstmt , pstmtCasher , pstmttableofloans;
   private ResultSet rst;
    public int getId_loans() {
        return id_loans;
    }

    public void setId_loans(int id_loans) {
        this.id_loans = id_loans;
    }

    public int getCountOfMonth() {
        return countOfMonth;
    }

    public void setCountOfMonth(int countOfMonth) {
        this.countOfMonth = countOfMonth;
    }

    public String getName_loans() {
        return name_loans;
    }

    public void setName_loans(String name_loans) {
        this.name_loans = name_loans;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPriceOfMonth() {
        return priceOfMonth;
    }

    public void setPriceOfMonth(double priceOfMonth) {
        this.priceOfMonth = priceOfMonth;
    }

    public double getFeesOfLoans() {
        return feesOfLoans;
    }

    public void setFeesOfLoans(double feesOfLoans) {
        this.feesOfLoans = feesOfLoans;
    }

    public double getSumOfAmount() {
        double sumamount = getPriceOfMonth()* getCountOfMonth() ;
        sumamount = sumamount - getFeesOfLoans();
        return sumamount;
    }

    
   
    public boolean Save(boolean onCasher){
        
       try {
           con = ConnectDB.getCon();
           con.setAutoCommit(false);
           casher = new CasherClass();
           String SQLinsertLoan = "INSERT INTO `loans` (`name_loans`, `amount`, `countOfMonth`,"
                   + " `priceOfMonth`, `feesOfLoans`, `sumOfAmount`) "
                   + "VALUES (?,?,?,?,?,?)";
           pstmt = con.prepareStatement(SQLinsertLoan, Statement.RETURN_GENERATED_KEYS);
           pstmt.setString(1, name_loans);
           pstmt.setDouble(2, amount);
           pstmt.setInt(3, countOfMonth);
           pstmt.setDouble(4, priceOfMonth);
           pstmt.setDouble(5, feesOfLoans);
           pstmt.setDouble(6, getSumOfAmount());
           
           int rowLoanAffect = pstmt.executeUpdate();
           if(rowLoanAffect == 1){
               rst = pstmt.getGeneratedKeys();
               while (rst.next()) {                   
                   id_loans = rst.getInt(1);
               }
               String sqlInserttalbleLoans = "INSERT INTO `tableofloans` (`InstallmentNo`,"
                       + " `amountOfImstallment`, `id_loans`) VALUES (?,?,?)";
               pstmttableofloans = con.prepareStatement(sqlInserttalbleLoans, Statement.RETURN_GENERATED_KEYS);
               int rowAffect = 0;
               int rowCasher = 0 ;
               for(int i = 1 ; i <= countOfMonth ; i++ ){
                   pstmttableofloans.setInt(1, i);
                   pstmttableofloans.setDouble(2, priceOfMonth);
                   pstmttableofloans.setInt(3, id_loans);
                   rowAffect = pstmttableofloans.executeUpdate();
               }
               if(rowAffect == 1 ){
                    if(onCasher){
                        pstmtCasher =  casher.SavedCasherTransaction(TypeCasherTransaction.getLoans, amount, name_loans, id_loans, con);
                        rowCasher = pstmtCasher.executeUpdate();
                        if(rowCasher == 1){
                            con.commit();
                            con.close();
                            isSaveLoan = true;
                        }else{
                            con.rollback();
                            con.close();
                            isSaveLoan = false;
                        }
                    }else{
                        con.commit();
                        con.close();
                        isSaveLoan = true;
                    }
               }else{
                   con.rollback();
                   con.close();
                   isSaveLoan = false;
               }
               
               
           }else{
               con.rollback();
               con.close();
               isSaveLoan = false;
           }
           
           
       } catch (SQLException ex) {
           try {
               con.rollback();
               con.close();
               isSaveLoan = false;
               Logger.getLogger(LoansClass.class.getName()).log(Level.SEVERE, null, ex);
           } catch (SQLException ex1) {
               Logger.getLogger(LoansClass.class.getName()).log(Level.SEVERE, null, ex1);
           }
       }
       return isSaveLoan;
    }
   
   
   /**
    INSERT INTO `loans` (`id_loans`, `name_loans`, `amount`, 
    * `countOfMonth`, `priceOfMonth`, `feesOfLoans`, `sumOfAmount`, `isActive`) 
    * VALUES (NULL, '', '', '', '', NULL, NULL, '1')

    **/
   public String[] getInfoLoanByName(String name_loans){
        String[] row = new String[2];
       try {
          
           String id_loans = ConnectDB.getIdFrmName("loans", name_loans);
           con = ConnectDB.getCon();
           String sql = "select InstallmentNo , amountOfImstallment from tableofloans where isActive = 1 AND id_loans = " +id_loans + " limit 1";
           Statement stmt = con.createStatement();
           //stmt.execute(sql);
           ResultSet rst = stmt.executeQuery(sql);
           rst.beforeFirst();
           while (rst.next()) {
               row[0] = rst.getString(1);
               row[1] = rst.getString(2);
           }
           con.close();
       } catch (SQLException ex) {
           Logger.getLogger(LoansClass.class.getName()).log(Level.SEVERE, null, ex);
       }
       return row;
   }
   
   
   
    
}
