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

/**
 *
 * @author mosta
 */
public class TransactionVCashAndPos {


    private enum type_transaction {Deposit,Withdraw};
    private int id_pos;
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

    private void Setname_pos(String name_pos){
        this.name_pos = name_pos;
    }
    private int getIdPos() {
        pc = new PosClass();
        String id_pos_St =  pc.getIdPosFromNamePos(name_pos);
        int idPos = Integer.parseInt(id_pos_St);
        return idPos;
    }
    
    public boolean SaveDeposit(){
        boolean isSave = false;
        try{
            con = ConnectDB.getCon();
            con.setAutoCommit(false);
            ///set data
            id_pos = getIdPos();
            pp = new posPay(id_pos) {};
            
            String sql = "";
        
        }catch(SQLException ex){
        
        }
    
        return isSave;
    }
    
    
    
}
