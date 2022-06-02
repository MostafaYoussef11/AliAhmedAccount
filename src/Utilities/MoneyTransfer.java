/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Entity.ClientPerson;
import Entity.Suppliers;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author mosta
 */
public abstract class MoneyTransfer {
    private String date_process;
    private double Debit , Creditor , amount , newBalance;
    private String note;
    private int id_client , id_Suppliers , id_Receipt , id_PaymentReceipt  ;
    
    public boolean Save(){return false;};
    public boolean Delete(){return false;};
    public boolean Update(){return false;};

    public double getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(double newBalance) {
        this.newBalance = newBalance;
    }
    
    public void SetIdClientFromName(String name_client){
        String idclient = new ClientPerson().getIdByName(name_client);
        setId_client(Integer.parseInt(idclient));
    }
    public void SetIdSuppliersFromName(String name_suppliers){
        String id_Supplier = new Suppliers().getIdByName(name_suppliers);
        int id_Supp = Integer.parseInt(id_Supplier);
        this.id_Suppliers = id_Supp;
    }
    public String getDate_process() {
        return date_process;
    }

    public void setDate_process(String date_process) {
        this.date_process = date_process;
    }
    public void setDate_process(Date date_process) {
        this.date_process = new SimpleDateFormat("YYYY-MM-dd").format(date_process);
    }
    public double getDebit() {
        return Debit;
    }

    public void setDebit(double Debit) {
        this.Debit = Debit;
    }

    public double getCreditor() {
        return Creditor;
    }

    public void setCreditor(double Creditor) {
        this.Creditor = Creditor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_Suppliers() {
        return id_Suppliers;
    }

    public void setId_Suppliers(int id_Suppliers) {
        this.id_Suppliers = id_Suppliers;
    }

    public int getId_Receipt() {
        return id_Receipt;
    }

    public void setId_Receipt(int id_Receipt) {
        this.id_Receipt = id_Receipt;
    }

    public int getId_PaymentReceipt() {
        return id_PaymentReceipt;
    }

    public void setId_PaymentReceipt(int id_PaymentReceipt) {
        this.id_PaymentReceipt = id_PaymentReceipt;
    }
    public String getLastPaymentReceiptId(){
        return ConnectDB.LastId("PaymentReceipt", "id_PaymentReceipt");
    }
    public String getLastReceiptId(){
        return ConnectDB.LastId("Receipt", "id_Receipt");
    }
}
