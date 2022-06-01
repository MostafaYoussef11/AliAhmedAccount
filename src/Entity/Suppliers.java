/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Utilities.ConnectDB;
import Utilities.Person;

/**
 *
 * @author mosta
 */
public class Suppliers extends Person{
    
    public Suppliers() {
        super("Suppliers");
    }
    
        public String getFirstBalance(String id_Supplier){
        String firstbalance = ConnectDB.getIdFromName("SELECT firstBalance AS id FROM suppliers WHERE id_Suppliers="+id_Supplier);
        return firstbalance;
    }
    public double calcBalanceSupplier(String nameSupplier){
        double debit , Creditor  , First_Balance = 0;
        String id_Supplier = getIdByName(nameSupplier);
        String firstBalance = getFirstBalance(id_Supplier);
        if(firstBalance == null){
            First_Balance = 0;
        }else{
            First_Balance = Double.parseDouble(firstBalance);
        }
         
        String sqlDebit = ConnectDB.getIdFromName("SELECT SUM(Debit) as id FROM debitandcreditorsupplier WHERE id_Suppliers="+id_Supplier);
        if(sqlDebit == null){
            debit = 0 ;
        }else{
            debit = Double.parseDouble(sqlDebit);
        }
        String sqlCreditor = ConnectDB.getIdFromName("SELECT SUM(Creditor) as id FROM debitandcreditorsupplier WHERE id_Suppliers="+id_Supplier);
        if(sqlCreditor == null){
            Creditor = 0 ;
        }else{
            Creditor = Double.parseDouble(sqlCreditor);
        }
        double newBalance = First_Balance + Creditor - debit;
        return newBalance;
    }

    
}
