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
import javax.swing.JTable;

/**
 *
 * @author mosta
 */
public class TransactionVCashAndPos {


    public enum type_transaction {Deposit,Withdraw};
    private int id_pos;
    private int id_VF_cash;
    private String name_pos;
    private String number;
    private double befor_balance;
    private double after_balance;
    private double price;
    
    private CasherClass casher;
    private posPay pp;
    private PosSell ps;
    private PosClass pc;
    private Connection con;
    private PreparedStatement pstm;

    public void Setname_pos(String name_pos){
        this.name_pos = name_pos;
    }
    private int getIdPos() throws SQLException {
        pc = new PosClass();
        String id_pos_St =  pc.getIdPosFromNamePos(name_pos);
        int idPos = Integer.parseInt(id_pos_St);
        return idPos;
    }
    
    public boolean SaveDeposit() throws SQLException{
        id_pos = getIdPos();
        pp = new posPay(id_pos) {};
        pp.setPrice_masary_pay(price);
        pp.setDiscount_of_balance(price);
        pp.setPhone(number);
        return pp.SaveVFCash();
    }
    public void Set_Price(String price){
        this.price = Double.parseDouble(price);
    }
    public void Set_number(String number){
        this.number =  number;
    }
    
    public void fillTable(JTable table , type_transaction type){
        String type_trans ="";
        String[] columnName = {"المبلغ", "الرقم", "المكنة", "التاريخ", "م"};
        if(type == type_transaction.Deposit) type_trans = "Deposit"; else type_trans = "Withdraw";
        String sql = "SELECT trans.price, v.number_VF_cash, pos.name_pos, trans.date_transaction, trans.id_transaction FROM vf_transaction_po AS trans INNER JOIN pos ON trans.id_pos = pos.id_pos INNER JOIN vf_cash AS v ON trans.id_VF_cash = v.id_VF_cash WHERE trans.type_transaction = '"+type_trans+"' ORDER BY trans.date_transaction DESC";
        ConnectDB.fillAndCenterTable(sql, table, columnName);
    }
    public void fillTableATM(JTable table ){
       // String type_trans ="";  "المبلغ", "الرقم", "التاريخ", "مسلسل"
        String[] columnName = {"المبلغ", "الرقم", "التاريخ", "مسلسل"};
        //if(type == type_transaction.Deposit) type_trans = "Deposit"; else type_trans = "Withdraw";
        String sql = "SELECT trans.price, v.number_VF_cash, trans.date_transaction, trans.id_transaction FROM vf_transaction_po AS trans INNER JOIN vf_cash AS v ON trans.id_VF_cash = v.id_VF_cash WHERE trans.type_transaction = 'Withdraw' And trans.id_pos = 6 ORDER BY trans.id_transaction DESC";//SELECT trans.price, v.number_VF_cash, trans.date_transaction, trans.id_transaction FROM vf_transaction_po AS trans INNER JOIN vf_cash AS v ON trans.id_VF_cash = v.id_VF_cash WHERE trans.type_transaction = 'Withdraw' And trans.id_pos = null ORDER BY trans.id_transaction DESC";
        ConnectDB.fillAndCenterTable(sql, table, columnName);
    }
    public boolean SaveWithdrawATM(String numberVF , double amount , double value ){
        boolean isSave = false;
        VFCashClass vf = new VFCashClass();
        casher = new CasherClass();
        int id_vf = vf.getId_VF_ByNumber(numberVF);
        try {
            con = ConnectDB.getCon();
            con.setAutoCommit(false);
            String sql_vf_trans = "INSERT INTO `vf_transaction_po` (`type_transaction`, `id_VF_cash`,`id_pos` ,`price`) VALUES (?,?,?,?)";
            pstm = con.prepareStatement(sql_vf_trans, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, "Withdraw");
            pstm.setInt(2, id_vf);
            pstm.setInt(3, 6);
            pstm.setDouble(4, value);
            
            int rowAffect = pstm.executeUpdate();
            int id_trans = 0;
            ResultSet rst = pstm.getGeneratedKeys();
            while(rst.next()){
                id_trans = rst.getInt(1);
            }
            if(rowAffect == 1){
               PreparedStatement pstmt_Cash = casher.SavedCasherTransaction(TypeCasherTransaction.Send_VF, amount,"  سحب من ال ATM  من رقم "+ number, id_trans, con);
               int Row_casher = pstmt_Cash.executeUpdate();
               if(Row_casher == 1){
                   con.commit();
                   con.close();
                   isSave = true;
               }else{
                   con.rollback();
                   con.close();
                   isSave = false;
               }
            }else{
               con.rollback();
               con.close();
               isSave = false;
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(TransactionVCashAndPos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSave;
    }

}
