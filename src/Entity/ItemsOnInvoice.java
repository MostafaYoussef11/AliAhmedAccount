/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author mosta
 */
public class ItemsOnInvoice {
    public int id ,id_Invoic;
    public double qyt , price , discount , Amount;
    public String name_items , name_unit , id_items;
    public ItemsOnInvoice(int id , String id_items , String name_items , double qyt , String name_unit , double price , double discount , double Amount , int id_Invoic){
            this.Amount = Amount;
            this.discount = discount;
            this.id = id;
            this.id_items = id_items;
            this.id_Invoic = id_Invoic;
            this.name_unit = name_unit;
            this.name_items = name_items;
            this.price = price;
            this.qyt = qyt;
    }
    
}
