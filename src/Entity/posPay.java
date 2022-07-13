/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Utilities.ConnectDB;
import Utilities.Tools;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;
import java.nio.file.FileVisitResult;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;


/**
 *
 * @author mosta
 */
public abstract class posPay {
    
    private Connection con;
    private PreparedStatement pstmt;
    private double newbalance ;
    private double pay ;
    private double sell ;
    private double firstbalance ;
    private int id_utility_masary;
    private String utility_masary;
    private double price_masary_pay;  //القيمة  - عدد الكروت - صافي الشحن
    private int id_client; 
    private double discount_of_balance; // المخصوم من الرصيد Discount of Balance
    private double amount_masary_pay; // المطلوب من العميل 
    private String phone;
    private int id_pos;
    private boolean is_requer_phone_num;
    private String id_category;
    
    //constractor Method
    public posPay(int id_pos){
        this.id_pos = id_pos;
    }

    public String getId_category() {
        return id_category;
    }

    public void setId_category(String id_category) {
        this.id_category = id_category;
    }

    public boolean isIs_requer_phone_num() {
        return is_requer_phone_num;
    }

    public void setIs_requer_phone_num(boolean is_requer_phone_num) {
        this.is_requer_phone_num = is_requer_phone_num;
    }
    
    public enum charage{ cash , mob};
    //Abstract Method
    public void fillTable(JTable jTable){
        String sql_select_masary = "SELECT mp.balance , mp.amount_masary_pay , mp.discount_of_balance , c.name_client , mp.price_masary_pay , ifnull(mp.phone,u.note_utility), u.name_utility_masary, mp.time_masary_pay ,mp.date_masary_pay , mp.id_masary_pay FROM masary_pay AS mp INNER JOIN client AS c on mp.id_client = c.id_client LEFT JOIN utility_masary AS u ON mp.id_utility_masary = u.id_utility_masary WHERE mp.id_pos = "+id_pos+" ORDER BY mp.id_masary_pay DESC";
        String ColumnName[] = {"الرصيد", "المدفوع", "م الرصيد", "العميل", "القيمة", "البيان", "الخدمة","الوقت", "التاريخ", "م"};
        ConnectDB.fillAndCenterTable(sql_select_masary, jTable, ColumnName);

    };
    
    //Save Methods
    
    //this method when cash money transaction thie id_client = 1
    public boolean SaveCasher(){
        boolean isSave = false;
        try{
         con = ConnectDB.getCon();
         con.setAutoCommit(false);
         String sql_insert_masaryPay;
         sql_insert_masaryPay = "INSERT INTO masary_pay "
                 + "( `id_utility_masary`, `price_masary_pay`, `id_client`, "
                 + "`discount_of_balance`, `amount_masary_pay`, `id_pos` ,`balance`,`phone`) VALUES (?,?,?,?,?,?,?,?)";
         pstmt = (PreparedStatement) con.prepareStatement(sql_insert_masaryPay, Statement.RETURN_GENERATED_KEYS);
         pstmt.setInt(1, id_utility_masary);          //id_utility_masary
         pstmt.setDouble(2, price_masary_pay);        //price_masary_pay
         pstmt.setInt(3, 1);                          // id_casher
         pstmt.setDouble(4, discount_of_balance);     // discountOfBalance
         pstmt.setDouble(5, amount_masary_pay);        // amount_masary_pay
         newbalance = getfirstBalance() - discount_of_balance; 
         pstmt.setInt(6, id_pos);
         DecimalFormat format = new DecimalFormat("0.00");
         String newBalance = format.format(newbalance);
            //System.out.println(newBalance);
         pstmt.setString(7, newBalance);
         pstmt.setString(8, utility_masary);
         int rowAffact = pstmt.executeUpdate();  
         ResultSet rst = pstmt.getGeneratedKeys();
        
         if(rst.next()){
            int id_masary_pay = rst.getInt(1);
            if(rowAffact == 1){
                 int rowAffectCasher = new CasherClass().SavedCasherTransaction(TypeCasherTransaction.PosPay, amount_masary_pay, utility_masary, id_masary_pay);
                 if(rowAffectCasher == 1){
                     con.commit();
                     isSave = true;
                     con.close();
                     
                     
//                    if(is_requer_phone_num){
//                      if(phone.length() == 0){
//                             System.out.println("الرقم غير مكتوب"); 
//                        }else{  
//                            ArrayList<String> Phones = Tools.PhoneNumberList();
//                            if(Phones.contains(phone)){
//                              System.out.println("الرقم مسجل"); 
//                            }
//                           String sql_inser_num = "INSERT INTO `phone_numbers` (`numbers`) VALUES(?)";
//                           PreparedStatement pst = con.prepareStatement(sql_inser_num);
//                           pst.setString(1, phone.trim());
//                           try{
//                               pst.executeUpdate();
//                            }catch(SQLException ex){
//                               //throw new ArrayIndexOutOfBoundsException(phone);
//                              System.out.println("الرقم مسجل");  
//                            }finally{
//                                 con.commit();
//                                 isSave = true;
//                                 con.close();                      
//                           }
//                         }
//                      if(con != null){
//                         con.commit();
//                         isSave = true;
//                         con.close();
//                      }
//
//                    }
//                    else{
//                       con.commit();
//                       isSave = true;
//                       con.close();
//                    }
// 
                     
                 }
            }
         }

     }catch(SQLException ex){
         try {
             con.rollback();
             con.close();
         } catch (SQLException ex1) {
             Logger.getLogger(MasaryPay.class.getName()).log(Level.SEVERE, null, ex1);
         }
         isSave = false;
         Logger.getLogger(MasaryPay.class.getName()).log(Level.SEVERE, null, ex);
     }
        return isSave;
    }
    //Save when id_clien not equal 1 it's mean this transaction is client depit not casher
    public boolean SaveClients(){
    boolean isSave = false;
    try{
         con = ConnectDB.getCon();
         con.setAutoCommit(false);
         String sql_insert_masaryPay;
         sql_insert_masaryPay = "INSERT INTO masary_pay "
                 + "( `id_utility_masary`, `price_masary_pay`, `id_client`, "
                 + "`discount_of_balance`, `amount_masary_pay`, `id_pos` ,`balance`,`phone`) VALUES (?,?,?,?,?,?,?,?)";
         pstmt = con.prepareStatement(sql_insert_masaryPay, Statement.RETURN_GENERATED_KEYS);
         pstmt.setInt(1, id_utility_masary);
         pstmt.setDouble(2, price_masary_pay);
         pstmt.setInt(3, 1);
         pstmt.setDouble(4, discount_of_balance);
         pstmt.setDouble(5, amount_masary_pay);
         DecimalFormat format = new DecimalFormat("0.00");
         
         newbalance = getfirstBalance() - discount_of_balance;
         String newBalance = format.format(newbalance);
         
         pstmt.setInt(6, id_pos);
         pstmt.setString(7, newBalance);
         pstmt.setString(8, utility_masary);
         int rowAffact = pstmt.executeUpdate();  
         ResultSet rst = pstmt.getGeneratedKeys();
         int id_masary_pay = 0;
         if(rst.next()){
            id_masary_pay = rst.getInt(1);
         }
         if(rowAffact == 1){
             /*
             INSERT INTO `clientaccount` (`Debit`, `id_client` , `id_masary_pay`,`note`) VALUES (?,?,?,?):
             **/
                 String sql_insert_clientAccount = "INSERT INTO `clientaccount` (`Debit`, `id_client` , `id_masary_pay`,`note`) VALUES (?,?,?,?)";
                 PreparedStatement pstmtm = con.prepareStatement(sql_insert_clientAccount);
                 pstmtm.setDouble(1,amount_masary_pay);
                 pstmtm.setInt(2, id_client);
                 pstmtm.setInt(3, id_masary_pay);
                 pstmtm.setString(4, utility_masary);
                 if(pstmtm.executeUpdate() == 1){
                    if(is_requer_phone_num){
                        if(phone.length() < 10){
                                System.out.println("Entity.posPay.SaveClients()" + "  Number Phoen is Less Than 10 "+phone);
                        }else{
                            String sql_inser_num = "INSERT INTO `phone_numbers` (`numbers`) VALUES(?)";
                            PreparedStatement pst = con.prepareStatement(sql_inser_num);
                            pst.setString(1, phone.trim());
                            pst.executeUpdate();
                        }

                    }
                    con.commit();
                    isSave = true;
                    con.close();
                 }
            }
    }
    catch(SQLException ex){
      try {
            con.rollback();
            con.close();
         } 
      catch (SQLException ex1) {
            Logger.getLogger(MasaryPay.class.getName()).log(Level.SEVERE, null, ex1);
        }
        isSave = false;
        Logger.getLogger(MasaryPay.class.getName()).log(Level.SEVERE, null, ex);
    }
    return isSave;    
 }
public boolean SaveVFCash(){
    boolean isSave = false;
    try{
         con = ConnectDB.getCon();
         con.setAutoCommit(false);
         String sql_insert_masaryPay;
         sql_insert_masaryPay = "INSERT INTO masary_pay "
                 + "( `id_utility_masary`, `price_masary_pay`, `id_client`, "
                 + "`discount_of_balance`, `amount_masary_pay`, `id_pos` ,`balance`,`phone`) VALUES (?,?,?,?,?,?,?,?)";
         pstmt = (PreparedStatement) con.prepareStatement(sql_insert_masaryPay, Statement.RETURN_GENERATED_KEYS);
         pstmt.setInt(1, new masary_Utilites(id_pos).getIdByNote("ايداع"));
         pstmt.setDouble(2, price_masary_pay);
         pstmt.setInt(3, 1);
         pstmt.setDouble(4, discount_of_balance);
         pstmt.setDouble(5, 0);
         newbalance = getfirstBalance() - discount_of_balance;
         DecimalFormat format = new DecimalFormat("0.00");
         String newBalance = format.format(newbalance);
         pstmt.setInt(6, id_pos);
         pstmt.setString(7, newBalance);
         pstmt.setString(8, "ايداع فودافون كاش");
         int rowAffact = pstmt.executeUpdate();  
         ResultSet rst = pstmt.getGeneratedKeys();
         if(rst.next()){
            
            int id_VF_cash = new VFCashClass().getId_VF_ByNumber(phone);
            int id_masary_pay = rst.getInt(1);
            if(rowAffact == 1){
                String sql_inser_ = "INSERT INTO `vf_transaction_po` ("
                                  + " `type_transaction`, `id_VF_cash`, "
                                  + "`id_pos`, `price`, `id_masary_pay`) "
                                  + "VALUES (?,?,?,?,?)";
                pstmt = con.prepareStatement(sql_inser_);
                pstmt.setString(1, "Deposit");
                pstmt.setInt(2, id_VF_cash);
                pstmt.setInt(3, id_pos);
                pstmt.setDouble(4, discount_of_balance);
                pstmt.setInt(5, id_masary_pay);

                if(pstmt.executeUpdate() == 1){
                        con.commit();
                        con.close();
                        isSave =true; 
                }
            }
     }

    }
    catch(SQLException ex){
      try {
            con.rollback();
            con.close();
         } 
      catch (SQLException ex1) {
            Logger.getLogger(MasaryPay.class.getName()).log(Level.SEVERE, null, ex1);
        }
        isSave = false;
        Logger.getLogger(MasaryPay.class.getName()).log(Level.SEVERE, null, ex);
    }
    return isSave;        
    
}
    
    // get Balance Machein befor transaction
    public double getfirstBalance(){
        String sql_get_sum_pay = "SELECT SUM(discount_of_balance) AS id FROM masary_pay WHERE id_pos="+id_pos;
        String sum_pay = ConnectDB.getIdFromName(sql_get_sum_pay);
        String sql_get_sum_sell = "SELECT SUM(value_masary_sell) AS id FROM masary_sell WHERE id_pos="+id_pos;
        String sum_sell = ConnectDB.getIdFromName(sql_get_sum_sell);
        if (sum_pay != null) {
            pay = Double.parseDouble(sum_pay);
        } else {
            pay = 0;
//            System.out.print("pay = null");
        }
        if(sum_sell != null) {
            sell = Double.parseDouble(sum_sell);
        } else {
            sell = 0;
//            System.out.print("sell = null");
        }
        firstbalance = sell - pay;
        DecimalFormat format = new DecimalFormat("0.00");
        String fbalance = format.format(firstbalance);
        return Double.parseDouble(fbalance);
    }
    
    //method Fill JList phoneNumber
//    public List<String> Phoneslist(charage charahe){
//         List<String> model = null;
//        try {
//            con = ConnectDB.getCon();
//            Statement stm = (Statement) con.createStatement();
//            model = new ArrayList<>();
//            String sql = "";
//            switch(charahe){
//                case cash:
//                    sql = "Select number_VF_cash from VF_cash";
//                    break;
//                case mob:
//                    sql = "SELECT phone FROM masary_pay GROUP BY phone";
//                    break;
//            }
//            ResultSet rst = stm.executeQuery(sql);
//            String value;
//            while (rst.next()) {
//                value = rst.getString(1);
//                model.add(value);
//            }
//            con.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(posPay.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return model;
//    }
    
    // Geter and Setter Methods
    //this method set id utility by note
    public void setId_utility_masary(String note) {
        this.id_utility_masary = new masary_Utilites(id_pos).getIdByNote(note);
    }
    public double getNewbalance() {
        return newbalance;
    }
    public void setNewbalance(double newbalance) {
        this.newbalance = newbalance;
    }
    public double getPay() {
        return pay;
    }
    public void setPay(double pay) {
        this.pay = pay;
    }
    public double getSell() {
        return sell;
    }
    public void setSell(double sell) {
        this.sell = sell;
    }
    public double getFirstbalance() {
        return firstbalance;
    }
    public void setFirstbalance(double firstbalance) {
        this.firstbalance = firstbalance;
    }
    public int getId_utility_masary() {
        return id_utility_masary;
    }
    public void setId_utility_masary(int id_utility_masary) {
        this.id_utility_masary = id_utility_masary;
    }
    public String getUtility_masary() {
        return utility_masary;
    }
    public void setUtility_masary(String utility_masary) {
        this.utility_masary = utility_masary;
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
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public int getId_pos() {
        return id_pos;
    }
    public void setId_pos(int id_pos) {
        this.id_pos = id_pos;
    }
    

    
}
