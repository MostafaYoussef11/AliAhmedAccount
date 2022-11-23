/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Utilities.ConnectDB;
import Utilities.Person;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author mosta
 */
public class Suppliers extends Person{
    private String name_supplier_type;
    private int id_supplier_type;
    public Suppliers() {
        super("Suppliers");
    }

    public int getId_supplier_type() {
        return id_supplier_type;
    }

    public void setId_supplier_type(int id_supplier_type) {
        this.id_supplier_type = id_supplier_type;
    }
    
    public String getFirstBalance(String id_Supplier){
        String firstbalance = ConnectDB.getIdFromName("SELECT firstBalance AS id FROM suppliers WHERE id_Suppliers="+id_Supplier);
        return firstbalance;
    }
    public double calcBalanceSupplier(String nameSupplier) throws SQLException{
        double debit , Creditor  , First_Balance = 0;
        String id_Supplier = getIdByName(nameSupplier);
        String firstbalance = getFirstBalance(id_Supplier);
        if(firstbalance == null){
            First_Balance = 0;
        }else{
            First_Balance = Double.parseDouble(firstbalance);
        }
         
        String sqlDebit = ConnectDB.getIdFromName("SELECT COALESCE(SUM(Debit),0) as id FROM debitandcreditorsupplier WHERE id_Suppliers="+id_Supplier);
        if(sqlDebit == null){
            debit = 0 ;
        }else{
            debit = Double.parseDouble(sqlDebit);
        }
        String sqlCreditor = ConnectDB.getIdFromName("SELECT COALESCE(SUM(Creditor),0) as id FROM debitandcreditorsupplier WHERE id_Suppliers="+id_Supplier);
        if(sqlCreditor == null){
            Creditor = 0 ;
        }else{
            Creditor = Double.parseDouble(sqlCreditor);
        }
        double newBalance = First_Balance + Creditor - debit;
        return newBalance;
    }
    public void fillComboTypeSupplier(JComboBox comboBox){
        ConnectDB.fillCombo("supplier_type", "name_supplier_type", comboBox);
    }
    public String getIdSupplliersAccountFromIdPayReceipt(String id_paymentReceipt){
        String sql = "SELECT `id_supplliersAccount` AS id FROM `suppliersaccount` WHERE `id_paymentReceipt`="+id_paymentReceipt;
        return ConnectDB.getIdFromName(sql);
    }
    public void SetNameSupplierType(String name_supplier_type){
        this.name_supplier_type = name_supplier_type;
    }
    private String getIdSupplierType(){
        String sql_select_id = "SELECT id_supplier_type AS id FROM supplier_type WHERE name_supplier_type ='"+name_supplier_type+"'";
        return ConnectDB.getIdFromName(sql_select_id);
    }
    public String getIdSupplierType(String nameSupplierType){
        String sql_select_id = "SELECT id_supplier_type AS id FROM supplier_type WHERE name_supplier_type ='"+nameSupplierType+"'";
        return ConnectDB.getIdFromName(sql_select_id);
    }
    @Override
    public boolean Save(){
        String sql;
        sql = "INSERT INTO Suppliers"+
            " VALUES ("+id_person+",'"+name+"','"+phone+"','"+address+"','"+idInternational+"',"+firstBalance+","+getIdSupplierType()+" , isActive = 1)";
        return ConnectDB.ExucuteAnyQuery(sql);
    }
    @Override
    public boolean Update(String id){
      String sql = "update "+tableName+" set name_"+tableName+"='"+name+"' , phone='"+phone+"' , address='"+address+"' , idNational = '"+idInternational+"' , firstBalance='"+firstBalance+"',"+"id_supplier_type ="+getIdSupplierType()+" where id_"+tableName+" ="+id;
      return ConnectDB.ExucuteAnyQuery(sql);
    }
    
    @Override
    public void filTable(JTable table) {
        String sql = "select firstBalance , t.name_supplier_type ,idNational , address , phone , name_"+tableName+" , id_"+tableName+" from Suppliers AS s INNER JOIN supplier_type AS t ON s.id_supplier_type = t.id_supplier_type where s.isActive = 1";
        String[] coulmnName = {"رصيد سابق","النوع", "رقم قومي", "عنوان", "هاتف", "الاسم", "مسلسل"};
        ConnectDB.fillAndCenterTable(sql, table, coulmnName);
    }
    public void FillComboAllNameSupplierByType(JComboBox comboName){
        ConnectDB.fillCombo(tableName + " WHERE isActive = 1 AND id_supplier_type = "+id_supplier_type , "name_"+tableName, comboName);
    } 
}
