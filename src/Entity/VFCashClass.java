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
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author mosta
 */
public class VFCashClass {
    private int id_VF_cash;
    private String number_VF_cash ;
    private String name_owner ;
    private String id_international ;
    private String sim;
    private double balance ;
    
    private Connection con;
    private PreparedStatement pstmt;
    
    public void fillTable(JTable jTable){
        String columnName[] = {"الرصيد", "SIM ID", "الرقم القومي", "الاسم", "رقم الخط", "م"};
        String sql_select = "SELECT `balance` ,  `sim`,`id_international`, `name_owner`,`number_VF_cash`,  `id_VF_cash` FROM `vf_cash`";
        ConnectDB.fillAndCenterTable(sql_select, jTable, columnName);
    }
    public boolean Save(){
       boolean isSave = false;
        try {
            Connection con = ConnectDB.getCon();
            String sql_insert = "INSERT INTO `vf_cash` (`number_VF_cash`, `name_owner`, `id_international`, `sim`,`balance`) VALUES (?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql_insert);
            pstmt.setString(1, number_VF_cash);
            pstmt.setString(2, name_owner);
            pstmt.setString(3, id_international);
            pstmt.setString(4, sim);
            pstmt.setDouble(5, balance);
           
            if(pstmt.executeUpdate() == 1){
                isSave = true;
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(VFCashClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSave;
    }
    public void fillCombo(JComboBox comboBox){
        ConnectDB.fillCombo("VF_cash", "number_VF_cash", comboBox);
    }
    
    //setter and getter Method
    public int getId_VF_cash() {
        return id_VF_cash;
    }
    public void setId_VF_cash(int id_VF_cash) {
        this.id_VF_cash = id_VF_cash;
    }
    public String getNumber_VF_cash() {
        return number_VF_cash;
    }
    public void setNumber_VF_cash(String number_VF_cash) {
        this.number_VF_cash = number_VF_cash;
    }
    public String getName_owner() {
        return name_owner;
    }
    public void setName_owner(String name_owner) {
        this.name_owner = name_owner;
    }
    public String getId_international() {
        return id_international;
    }
    public void setId_international(String id_international) {
        this.id_international = id_international;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public String getSim() {
        return sim;
    }
    public void setSim(String sim) {
        this.sim = sim;
    }
    
   
    public double getLamiteOfMonth_SendAndWithdraw(int id){
        String sql_select_sSend = "SELECT IFNULL(SUM(s.value_Send_Receive),0) as id FROM send_receive AS s "
                                + "WHERE Month(s.date_Send_Receive) = Month(now()) AND s.type_Send_Receive = 'Send'"
                + " AND id_VF_cash = "+id;
        String sql_select_sWithdraw = "SELECT IFNULL(SUM(v.price),0) as id FROM vf_transaction_po AS v "
                                    + "WHERE Month(v.date_transaction) = Month(now()) AND v.type_transaction = 'Withdraw'"
                + " AND id_VF_cash = "+id;
        
        String St_TotalSend = ConnectDB.getIdFromName(sql_select_sSend);
        String St_TotalWithdraw = ConnectDB.getIdFromName(sql_select_sWithdraw);
        double TotalSend = Double.parseDouble(St_TotalSend);
        double TotalWithdraw = Double.parseDouble(St_TotalWithdraw);
        double Total =  TotalSend + TotalWithdraw;
        return Total ;
    }
    
    
    
    
    
    
    
    
    
//    
//    private double getFirstBalanceWallet(String number){
//        String fbalance = ConnectDB.getIdFromName("SELECT balance AS id FROM vf_cash WHERE number_VF_cash ='"+number+"'");
//        return Double.parseDouble(fbalance);
//    }
//    private double getTotalDeposit(String number){
//        int idVFcash = getId_VF_ByNumber(number);
//        String sql_sum_deposit = "SELECT IFNULL(SUM(price),0) AS id FROM vf_transaction_po WHERE type_transaction = 'Deposit' And id_VF_cash = "+idVFcash;
//        return Double.parseDouble(ConnectDB.getIdFromName(sql_sum_deposit));
//    }
//    private double getTotalWithdraw(String number){
//        int idVFcash = getId_VF_ByNumber(number);
//        String sql_sum_withdraw = "SELECT IFNULL(SUM(price),0) AS id FROM vf_transaction_po WHERE type_transaction = 'Withdraw' And id_VF_cash = "+idVFcash;
//        return Double.parseDouble(ConnectDB.getIdFromName(sql_sum_withdraw));
//    }
//    public double getNowBalance(String number){
//        double fBalance= getFirstBalanceWallet(number);
//        double deposit = getTotalDeposit(number);  // ايداع  من المكنة
//        double withdraw = getTotalWithdraw(number);// سحب من المكنة
//        //double Send = 
//        double nowBalance = fBalance + deposit - withdraw;
//        return nowBalance;
//    }
    public int getId_VF_ByNumber(String number){
        String sql_select = "SELECT id_VF_cash  AS id FROM vf_cash WHERE number_VF_cash ='"+number+"'";
        String id = ConnectDB.getIdFromName(sql_select);
        return Integer.parseInt(id);
    }
    public String getNowBalanceWalletFromTable(String numberVF){
        String sql_select = "SELECT balance  AS id FROM vf_cash WHERE number_VF_cash ='"+numberVF+"'";
        return ConnectDB.getIdFromName(sql_select);
    }
    public double getNowBalance(String Number){
        String sql_select ="SELECT NowBalance as id FROM totalcash where Number = '"+Number+"'";
        String StnowBlance = ConnectDB.getIdFromName(sql_select);
        return Double.parseDouble(StnowBlance);
    
    
    }
//    public int UpdateBalanceDeposit_Recieve(String Number , double addToBalance){
//        int saved = 0;
//        try {
//            int id = getId_VF_ByNumber(Number);
//            double nBalance = getNowBalance(Number);
//            con = ConnectDB.getCon();
//            String sqlupdate = "UPDATE `vf_cash` SET `now_balance` = ? WHERE `vf_cash`.`id_VF_cash` = ?";
//            pstmt = con.prepareStatement(sqlupdate);
//            double nowBalance = nBalance + addToBalance;
//            pstmt.setDouble(1, nowBalance);
//            pstmt.setInt(2, id);
//            saved = pstmt.executeUpdate();
//            con.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(VFCashClass.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    
//        return saved;
//    }
//    
//    public int UpdateBalanceWithdraw_Send(String Number , double SubFromBalance){
//        int saved = 0;
//        try {
//            int id = getId_VF_ByNumber(Number);
//            double nBalance = Double.parseDouble(getNowBalanceWalletFromTable(Number));
//            con = ConnectDB.getCon();
//            String sqlupdate = "UPDATE `vf_cash` SET `now_balance` = ? WHERE `vf_cash`.`id_VF_cash` = ?";
//            pstmt = con.prepareStatement(sqlupdate);
//            double nowBalance = nBalance - SubFromBalance;
//            pstmt.setDouble(1, nowBalance);
//            pstmt.setInt(2, id);
//            saved = pstmt.executeUpdate();
//            con.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(VFCashClass.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    
//        return saved;
//    }
}
