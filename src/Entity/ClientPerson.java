/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Utilities.ConnectDB;
import Utilities.Person;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author mosta
 */
public class ClientPerson extends Person{

    public ClientPerson() {
        super("client");
        this.tableName = "client";
    }

    public String getFirstBalance(String idClient){
        String firstbalance = ConnectDB.getIdFromName("SELECT firstBalance AS id FROM client WHERE id_client="+idClient);
        return firstbalance;
    }
    public double calcBalanceClient(String nameClient){
        double debit , Creditor = 0;
        String id_client = getIdByName(nameClient);
        double First_Balance = Double.parseDouble(getFirstBalance(id_client));
        String sqlDebit = ConnectDB.getIdFromName("SELECT SUM(Debit) as id FROM debitandcreditorclient WHERE id_client="+id_client);
        if(sqlDebit == null){
            debit = 0 ;
        }else{
            debit = Double.parseDouble(sqlDebit);
        }
        String sqlCreditor = ConnectDB.getIdFromName("SELECT SUM(Creditor) as id FROM debitandcreditorclient WHERE id_client="+id_client);
        if(sqlCreditor == null){
            Creditor = 0 ;
        }else{
            Creditor = Double.parseDouble(sqlCreditor);
        }
        double newBalance = First_Balance + debit - Creditor;
        return newBalance;
    }
    public void FillComboNameClient(JComboBox combo){
      ConnectDB.fillCombo("client WHERE id_client != 1 AND isActive = 1", "name_client", combo);
    }
    public void FillComboCashClient(JComboBox combo){
        ConnectDB.fillCombo("client WHERE id_client = 1 ", "name_client", combo);
    }

    @Override
    public void filTable(JTable table) {
       // super.filTable(table); //To change body of generated methods, choose Tools | Templates.
               String sql = "select firstBalance , idNational , address , phone , name_client , id_client from  client where id_client != 1 ";
        String[] coulmnName = {"رصيد سابق", "رقم قومي", "عنوان", "هاتف", "الاسم", "مسلسل"};
        ConnectDB.fillAndCenterTable(sql, table, coulmnName);
        
    }
    
}
