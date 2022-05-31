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
    public int id , id_items , id_unit , id_Invoic;
    public double qyt , price , discount , Amount;
    public String name_items;
    public ItemsOnInvoice(int id , int id_items , String name_items , double qyt , int id_unit , double price , double discount , double Amount , int id_Invoic){
            this.Amount = Amount;
            this.discount = discount;
            this.id = id;
            this.id_items = id_items;
            this.id_Invoic = id_Invoic;
            this.id_unit = id_unit;
            this.name_items = name_items;
            this.price = price;
            this.qyt = qyt;
    }
    
}
