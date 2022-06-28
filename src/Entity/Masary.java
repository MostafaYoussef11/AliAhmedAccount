/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Utilities.ConnectDB;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author mosta
 */
public class Masary {
    private double newbalance = 0.00;
    private double pay = 0.00;
    private double sell = 0.00;
    private double firstbalance = 0.00 ;
    private Connection con;
    private PreparedStatement pstmt;
    private int id_utility_masary;
    private String utility_masary;
    private double price_masary_pay;  //القيمة 
    private int id_client; 
    private double discount_of_balance; // المخصوم من الرصيد Discount of Balance
    private double amount_masary_pay; // المطلوب من العميل 
    private String phone;
    public void fillTable(JTable jTable){
        String sql_select_masary = "SELECT mp.balance , mp.amount_masary_pay , mp.discount_of_balance , c.name_client , mp.price_masary_pay , ifnull(mp.phone,u.note_utility), u.name_utility_masary, mp.time_masary_pay ,mp.date_masary_pay , mp.id_masary_pay FROM masary_pay AS mp INNER JOIN client AS c on mp.id_client = c.id_client LEFT JOIN utility_masary AS u ON mp.id_utility_masary = u.id_utility_masary ORDER BY mp.id_masary_pay DESC";
        String ColumnName[] = {"الرصيد", "المدفوع", "م الرصيد", "العميل", "القيمة", "البيان", "الخدمة","الوقت", "التاريخ", "م"};
        ConnectDB.fillAndCenterTable(sql_select_masary, jTable, ColumnName);
    }
    
    public double getfirstBalance(){
        String sql_get_sum_pay = "SELECT SUM(discount_of_balance) AS id FROM masary_pay";
        String sum_pay = ConnectDB.getIdFromName(sql_get_sum_pay);
        String sql_get_sum_sell = "SELECT SUM(amount_masary_sell) AS id FROM masary_sell";
        String sum_sell = ConnectDB.getIdFromName(sql_get_sum_sell);
        if (sum_pay != null) {
            pay = Double.parseDouble(sum_pay);
        } else {
            pay = 0;
            System.out.print("pay = null");
        }
        if(sum_sell != null) {
            sell = Double.parseDouble(sum_sell);
        } else {
            sell = 0;
            System.out.print("sell = null");
        }
        firstbalance = sell - pay;
        return firstbalance;
    }
    
    public boolean Save(){
     boolean isSave = false;
     try{
         con = ConnectDB.getCon();
         con.setAutoCommit(false);
         String sql_insert_masaryPay;
         sql_insert_masaryPay = "INSERT INTO masary_pay ( `id_utility_masary`, `price_masary_pay`, `id_client`, `discount_of_balance`, `amount_masary_pay`,`balance`,`phone`) VALUES (?,?,?,?,?,?,?)";
         pstmt = (PreparedStatement) con.prepareStatement(sql_insert_masaryPay, Statement.RETURN_GENERATED_KEYS);
         pstmt.setInt(1, id_utility_masary);
         pstmt.setDouble(2, price_masary_pay);
         pstmt.setInt(3, id_client);
         pstmt.setDouble(4, discount_of_balance);
         pstmt.setDouble(5, amount_masary_pay);
         newbalance = getfirstBalance() - discount_of_balance;
         pstmt.setDouble(6, newbalance);
         pstmt.setString(7, utility_masary);
         int rowAffact = pstmt.executeUpdate();  
         ResultSet rst = pstmt.getGeneratedKeys();
         if(rst.next()){
            int id_masary_pay = rst.getInt(1);
            if(rowAffact == 1){
                 if(id_client == 1){
                     String sql_insert_casher = "INSERT INTO `casher` (`Debit`, `note`,`id_masary_pay`) VALUES (?,?,?)";
                     pstmt = (PreparedStatement) con.prepareStatement(sql_insert_casher);
                     pstmt.setDouble(1,amount_masary_pay);
                     pstmt.setString(2, utility_masary);
                     pstmt.setInt(3, id_masary_pay);
                     if(pstmt.executeUpdate() == 1){
                        con.commit();
                        isSave = true;
                        con.close();
                     }

                 }
         }
         }
         
         
         
     }catch(SQLException ex){
         try {
             con.rollback();
             con.close();
         } catch (SQLException ex1) {
             Logger.getLogger(Masary.class.getName()).log(Level.SEVERE, null, ex1);
         }
         isSave = false;
         Logger.getLogger(Masary.class.getName()).log(Level.SEVERE, null, ex);
     }
    
      return  isSave;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    
    

    public void setId_utility_masary(String note) {
        this.id_utility_masary = new masary_Utilites().getIdByNote(note);
    }

    public double getPrice_masary_pay() {
        return price_masary_pay;
    }

    public void setPrice_masary_pay(double price_masary_pay) {
        this.price_masary_pay = price_masary_pay;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public double getDiscount_of_balance() {
        return discount_of_balance;
    }

    public void setDiscount_of_balance(double discount_of_balance) {
        this.discount_of_balance = discount_of_balance;
    }

    public double getAmount_masary_pay() {
        return amount_masary_pay;
    }

    public void setAmount_masary_pay(double amount_masary_pay) {
        this.amount_masary_pay = amount_masary_pay;
    }

    public String getUtility_masary() {
        return utility_masary;
    }

    public void setUtility_masary(String utility_masary) {
        this.utility_masary = utility_masary;
    }
    

}
