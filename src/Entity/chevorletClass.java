/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Utilities.ConnectDB;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mosta
 */
public class chevorletClass {
    Connection con;
    PreparedStatement pstmt , pstmtCasher;
    boolean isSave = false ;
    public enum typeCash {Cash , hafCash , Debit};
    
    public boolean SaveTypeExpens(String name_type){
        try {
            con = ConnectDB.getCon();
            //con.setAutoCommit(false);
            String sqlInsert = "INSERT INTO `typeexpenschefr` (`name_typeExpensChefr`) VALUES (?)";
            pstmt = con.prepareStatement(sqlInsert);
            pstmt.setString(1, name_type);
            int rowAffect = pstmt.executeUpdate();
            if(rowAffect == 1){
                isSave = true;
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(chevorletClass.class.getName()).log(Level.SEVERE, null, ex);
        }
       return isSave;
    }
    private int getid_typeExpensChefr(String typeExpens){
        int id_type = 0;
        try {
            String id = ConnectDB.getIdFrmName("typeExpensChefr", typeExpens);
            id_type = Integer.parseInt(id);
        } catch (SQLException ex) {
            Logger.getLogger(chevorletClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id_type;
    }
    public boolean SaveExpens(typeCash type , double amount , String typeExpens , String nameSupplier , String note){
        try {
            int id_supplier =Integer.parseInt(new Suppliers().getIdByName(nameSupplier));
            System.out.println("idSuppliers : " + id_supplier);
            con = ConnectDB.getCon();
            con.setAutoCommit(false);
            String sqlInsert = "INSERT INTO `chevroletexpens` (`amount`, `id_typeExpensChefr`, `id_Suppliers`, `note`) VALUES (?,?,?,?)";
            pstmt = con.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            pstmt.setDouble(1, amount);
            pstmt.setInt(2, getid_typeExpensChefr(typeExpens));
            pstmt.setInt(3,id_supplier);
            pstmt.setString(4, note);
            int rowAffect = pstmt.executeUpdate();
            if(rowAffect == 1){
                int rowCasher = 0;
                switch(type){
                    case Cash:
                        ResultSet rst = pstmt.getGeneratedKeys();
                        int id_expens = 0;
                        while(rst.next()){
                            id_expens = rst.getInt(1);
                        }
                        pstmtCasher = new CasherClass().SavedCasherTransaction(TypeCasherTransaction.ChefrExpens, amount, note, id_expens, con);
                        rowCasher = pstmtCasher.executeUpdate();
                        break;
                }
                if(rowCasher == 1){
                    con.commit();
                    con.close();
                    isSave = true;
                }
            }else{
                con.rollback();
                con.close();
            }
            
        
        } catch (SQLException ex) {
            Logger.getLogger(chevorletClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isSave;
    }
    public boolean SaveImport(int id_workgroup , double amount , String note){
        if(id_workgroup == 0){
        
        }else{
            try {
                con = ConnectDB.getCon();
                con.setAutoCommit(false);
                String sql = "INSERT INTO `chevroletimports` (`id_workgroup`, `amount`, `note` ) VALUES (?,?,?)";
                pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstmt.setInt(1, id_workgroup);
                pstmt.setDouble(2,amount);
                pstmt.setString(3, note);
                int rowAffect = pstmt.executeUpdate();
                if(rowAffect == 1){
                    goldClasses gold = new goldClasses();
                    boolean saveExpens = gold.SaveExpensGold(amount, id_workgroup, note, id_workgroup, 0);
                    if(saveExpens){
                        //con.close();
                        con.commit();
                        con.close();
                        isSave = true;
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(chevorletClass.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
        }
        return isSave;
    }
}
