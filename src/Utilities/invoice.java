/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Entity.ItemsOnInvoice;
import java.util.Date;
import java.util.Vector;
import javax.swing.JComboBox;

/**
 *
 * @author mosta
 */
public abstract class invoice {
    private String id_invoice ;
    private String date_invoice;
    private String id_items ;
    private String name_items;
    private String id_unit;   
    private double Amount ;
    private String id_client;
    private String id_Supplier;
    private PaymentMethod paymentMethod;
    private double CashAmount;
    private double Discont;
    private double RemainingAmount;
    private boolean isActive;
    private String note;
    private String tableName;
     private Vector<ItemsOnInvoice> Values;
    public invoice(String tableName) {
        this.tableName = tableName;
    }

    public boolean Save(){
       String sql = "";
       return ConnectDB.ExucuteAnyQuery(sql);
    
    }
    
    public String getLastId(){
        return ConnectDB.LastId(tableName, "id_"+tableName);
    }
    public String getLastIdItemsOnInvoice(String id_salesInvoic){
            return ConnectDB.LastId("itemsonsalesinvoice WHERE id_salesInvoic="+id_salesInvoic, "id");
    };

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getId_invoice() {
        return id_invoice;
    }

    public void setId_invoice(String id_invoice) {
        this.id_invoice = id_invoice;
    }

    public String getDate_invoice() {
        return date_invoice;
    }

    public void setDate_invoice(Date date_invoice) {
        this.date_invoice = Tools.dateSql(date_invoice);
    }

    public double getRemainingAmount() {
        return RemainingAmount;
    }

    public void setRemainingAmount(double RemainingAmount) {
        this.RemainingAmount = RemainingAmount;
    }

    public void setDate_invoice(String date_invoice) {
        this.date_invoice = date_invoice;
    }

    public String getId_items() {
        return id_items;
    }

    public void setId_items(String id_items) {
        this.id_items = id_items;
    }


    public String getId_unit() {
        return id_unit;
    }

    public void setId_unit(String id_unit) {
        this.id_unit = id_unit;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double Amount) {
        this.Amount = Amount;
    }

    public String getId_client() {
        return id_client;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }

    public String getId_Supplier() {
        return id_Supplier;
    }

    public void setId_Supplier(String id_Supplier) {
        this.id_Supplier = id_Supplier;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getCashAmount() {
        return CashAmount;
    }

    public void setCashAmount(double CashAmount) {
        this.CashAmount = CashAmount;
    }

    public boolean getisActive() {
        return isActive;
    }

    public void setisActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getName_items() {
        return name_items;
    }

    public void setName_items(String name_items) {
        this.name_items = name_items;
    }
   
    
    public double getDiscont() {
        return Discont;
    }

    public void setDiscont(double Discont) {
        this.Discont = Discont;
    }
    
    public Vector<ItemsOnInvoice> getValues() {
        return Values;
    }
    public void setValues(Vector<ItemsOnInvoice> Values) {
        this.Values = Values;
    }

   
}
