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
}
