/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Utilities.ConnectDB;
import Utilities.Tools;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public abstract class PosSell {
    
    // Variable Seter by User
    private double value_masary_sell; // The value add to balance 
    private double amount_masary_sell; // the amount discount from casher 
    private String name_recharge_type; // name of type
    private String name_Suppliers; // name Supplier
    private String number_VF_cash; // this is Number the wallet
    // get by method
    private int id_recharge_type ; // The Type recharge Cash or supplier or VFcash
    private int id_Suppliers ;  // this is id to Supplier if type recharge
    private int id_pos ; // id pos recharge ex Masary or fawry ..
    private String name_pos; // name pos
    private int id_VF_cash; // this is id VF-cash
   
    // variable Use To Connection 
    private Connection con;
    private PreparedStatement pstmt , pstmc;
    private ResultSet rst;
    //abstract Method
    public void fillTable(JTable jTable){
        String sql_select = "SELECT rt.name_recharge_type , ms.amount_masary_sell , ms.time_masary_sell , ms.date_masary_sell ,ms.id_masary_sell  FROM masary_sell AS ms INNER JOIN recharge_type AS rt ON ms.id_recharge_type = rt.id_recharge_type WHERE ms.id_pos ="+id_pos+" ORDER BY ms.id_masary_sell DESC ";
        String[] columnName = {"نوع التوريد", "المبلغ", "الوقت", "التاريخ", "م"};
        ConnectDB.fillAndCenterTable(sql_select, jTable, columnName);

    };
     
    //constractor method
    public PosSell(int id_pos) {
        this.name_pos = ConnectDB.getIdFromName("SELECT name_pos AS id FROM pos WHERE id_pos = "+id_pos);
        this.id_pos = id_pos;//Integer.parseInt("SELECT id_pos AS id FROM pos where name_pos='"+name_pos+"'");
    }
    public void fillComboRechargeType(JComboBox box){
        ConnectDB.fillCombo("recharge_type", "name_recharge_type", box);
    };
   
    public void SetDataAndgeter(double value,double amount , String  name_recharge_type , String name_Suppliers , String number_VF_cash ) throws SQLException{ 
        this.value_masary_sell = value;
        this.amount_masary_sell = amount;
        this.name_Suppliers = name_Suppliers;
        this.name_recharge_type = name_recharge_type;
        this.number_VF_cash = number_VF_cash;
        id_recharge_type = Integer.parseInt(ConnectDB.getIdFrmName("recharge_type", name_recharge_type));
        if(name_Suppliers != null) id_Suppliers = Integer.parseInt(new Suppliers().getIdByName(name_Suppliers));
        if(number_VF_cash != null) id_VF_cash = Integer.parseInt(ConnectDB.getIdFromName("SELECT id_VF_cash AS id FROM VF_cash WHERE number_VF_cash ='"+number_VF_cash+"'"));
        
    }
    public String getName_pos() {
        return name_pos;
    }
    public void setName_pos(String name_pos) {
        this.name_pos = name_pos;
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
    public double getValue_masary_sell() {
        return value_masary_sell;
    }
    public void setValue_masary_sell(double value_masary_sell) {
        this.value_masary_sell = value_masary_sell;
    }
    public double getAmount_masary_sell() {
        return amount_masary_sell;
    }
    public void setAmount_masary_sell(double amount_masary_sell) {
        this.amount_masary_sell = amount_masary_sell;
    }
    public int getId_recharge_type() {
        return id_recharge_type;
    }
    public void setId_recharge_type(int id_recharge_type) {
        this.id_recharge_type = id_recharge_type;
    }
    public String getName_recharge_type() {
        return name_recharge_type;
    }
    public void setName_recharge_type(String name_recharge_type) {
        this.name_recharge_type = name_recharge_type;
    }
    public int getId_Suppliers() {
        return id_Suppliers;
    }
    public void setId_Suppliers(int id_Suppliers) {
        this.id_Suppliers = id_Suppliers;
    }
    public String getName_Suppliers() {
        return name_Suppliers;
    }
    public void setName_Suppliers(String name_Suppliers) {
        this.name_Suppliers = name_Suppliers;
    }
    public int getId_pos() {
        return id_pos;
    }
    public void setId_pos(int id_pos) {
        this.id_pos = id_pos;
    }
    

    public boolean SaveCash() {
        boolean isSave = false;
        try {
            con = ConnectDB.getCon();
            con.setAutoCommit(false);
            String sql_insert_ms = "INSERT INTO `masary_sell` (`value_masary_sell`, `amount_masary_sell`,"
                    + " `id_recharge_type`, `id_Suppliers`,`id_pos`) "
                    + "VALUES (?,?,?,?,?)";
            pstmt = con.prepareStatement(sql_insert_ms, Statement.RETURN_GENERATED_KEYS);
            pstmt.setDouble(1, value_masary_sell);
            pstmt.setDouble(2, amount_masary_sell);
            pstmt.setInt(3, id_recharge_type);
            pstmt.setInt(4, 1);
            pstmt.setInt(5, id_pos);
            int row_inserted = pstmt.executeUpdate();
            rst = pstmt.getGeneratedKeys();
            if(rst.next()){
                int id_masary_sell = rst.getInt(1);
                if(row_inserted == 1){
                    
//                    //error
//                String sql_insert_casher ="INSERT INTO `vf_transaction_po` ("
//                                  + " `type_transaction`, `id_VF_cash`, "
//                                  + "`id_pos`, `price`, `id_masary_sell`) "
//                                  + "VALUES (?,?,?,?,?)";
//                pstmc = con.prepareStatement(sql_insert_casher);
//                pstmc.setString(1, "Withdraw");
//                pstmc.setInt(2, id_VF_cash);
//                pstmc.setInt(3, id_pos);
//                pstmc.setDouble(4, amount_masary_sell);
//                pstmc.setInt(5, id_masary_sell);
                //int row_insert = 0;
                PreparedStatement pstmCasher = new CasherClass().SavedCasherTransaction(TypeCasherTransaction.PosSell, amount_masary_sell, name_pos, id_masary_sell,con);
                int row_insert = pstmCasher.executeUpdate();
                if(row_insert == 1){
                  con.commit();
                  con.close();
                  isSave =true;
                }
                }
            }
            
            
        } catch (SQLException ex) {
            try {
                Logger.getLogger(PosSell.class.getName()).log(Level.SEVERE, null, ex);
                con.rollback();
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(PosSell.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
       return isSave;
    }
    public boolean SaveAccountSupplier() {
        boolean isSave = false;
        try {
            con = ConnectDB.getCon();
            con.setAutoCommit(false);
            String sql_insert_ms = "INSERT INTO `masary_sell` (`value_masary_sell`, `amount_masary_sell`,"
                    + " `id_recharge_type`, `id_Suppliers`,`id_pos`) "
                    + "VALUES (?,?,?,?,?)";
            pstmt = con.prepareStatement(sql_insert_ms, Statement.RETURN_GENERATED_KEYS);
            pstmt.setDouble(1, value_masary_sell);
            pstmt.setDouble(2, amount_masary_sell);
            pstmt.setInt(3, id_recharge_type);
            pstmt.setInt(4, id_Suppliers);
            pstmt.setInt(5, id_pos);
            int row_inserted = pstmt.executeUpdate();
            rst = pstmt.getGeneratedKeys();
            if(rst.next()){
                int id_masary_sell = rst.getInt(1);
                if(row_inserted == 1){
                    String sql_insert_supp_account = "INSERT INTO `suppliersaccount` (`Creditor`, `id_Suppliers`, `id_masary_sell`, `note`) VALUES (?,?,?,?)";
                    pstmc = con.prepareStatement(sql_insert_supp_account);
                    pstmc.setDouble(1, amount_masary_sell);
                    pstmc.setInt(2, id_Suppliers);
                    pstmc.setInt(3, id_masary_sell);
                    pstmc.setString(4, "شحن ماكينة"+name_pos);
                    int row_insert = pstmc.executeUpdate();
                    if(row_insert == 1){
                        isSave = true;
                        con.commit();
                        con.close();
                    }               
                }
            }

            
        } catch (SQLException ex) {
            try {
                con.rollback();
                con.close();
                Logger.getLogger(PosSell.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(PosSell.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
       return isSave; 
    }
    
    public void setDataUseSave(double value , double amount , String number_VF_cash , int id_pos){
            this.value_masary_sell = value;
            this.amount_masary_sell = amount;
            this.number_VF_cash = number_VF_cash;
            this.id_VF_cash = new VFCashClass().getId_VF_ByNumber(number_VF_cash);
            this.id_pos = id_pos;
    } 
    
    public boolean SaveVFCash(){
        boolean isSave = false;
        try {
            con = ConnectDB.getCon();
            con.setAutoCommit(false);
            String sql_insert_ms = "INSERT INTO `masary_sell` (`value_masary_sell`, `amount_masary_sell`,"
                    + " `id_recharge_type`, `id_Suppliers`,`id_pos`) "
                    + "VALUES (?,?,?,?,?)";
            pstmt = con.prepareStatement(sql_insert_ms, Statement.RETURN_GENERATED_KEYS);
            pstmt.setDouble(1, value_masary_sell);  //value   it`s mean the values add to balance Pos
            pstmt.setDouble(2, amount_masary_sell);  // amount  it`s meaen the values dicount from wallet and the cost
            pstmt.setInt(3, 3);
            pstmt.setInt(4, 1);
            pstmt.setInt(5, id_pos);
            int row_inserted = pstmt.executeUpdate();
            rst = pstmt.getGeneratedKeys();
            if(rst.next()){         
                int id_vF_cash = new VFCashClass().getId_VF_ByNumber(number_VF_cash);
                int id_masary_pay = rst.getInt(1);
                if(row_inserted == 1){
                    String sql_inser_ = "INSERT INTO `vf_transaction_po` ("
                                      + " `type_transaction`, `id_VF_cash`, "
                                      + "`id_pos`, `price`, `id_masary_sell`) "
                                      + "VALUES (?,?,?,?,?)";
                    pstmt = con.prepareStatement(sql_inser_);
                    pstmt.setString(1, "Withdraw");
                    pstmt.setInt(2, id_vF_cash);
                    pstmt.setInt(3, id_pos);
                    pstmt.setDouble(4, amount_masary_sell);
                    pstmt.setInt(5, id_masary_pay);

                    if(pstmt.executeUpdate() == 1){
                        con.commit();
                        con.close();
                        isSave =true;
                    }
                }    
            }      
        } catch (SQLException ex) {
            try {
                Logger.getLogger(PosSell.class.getName()).log(Level.SEVERE, null, ex);
                con.rollback();
                con.close();
            } catch (SQLException ex1) {
                Logger.getLogger(PosSell.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
       return isSave;
    }
    
}
