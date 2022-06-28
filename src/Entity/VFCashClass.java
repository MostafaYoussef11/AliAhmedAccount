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
    
    
}
