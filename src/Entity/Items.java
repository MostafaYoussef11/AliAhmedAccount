/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Utilities.ConnectDB;
import Utilities.Ent;
import Utilities.Tools;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author mosta
 */
public class Items implements Ent{
    String low;
    String hight ;
    String id;
    String nameItem;
    String id_items;
    double val ;
    int firstbalance;
    private String id_priceList;
    private String last_edit; 
    private double purchase_price_low, purchase_price_high, sale_price_low, sale_price_high;
    
    public void SetAll(String id , String low , String hight , double val){
        this.low = low;
        this.hight = hight;
        this.val = val;
        this.id = id;
    }
    
    
    @Override
    public boolean Save() {
        String sql = "INSERT INTO unit VALUES("+id+",'"+low+"' , '"+hight+"',"+val+");";
        return ConnectDB.ExucuteAnyQuery(sql);
    }

    @Override
    public boolean update(String id) {
        String sql = "UPDATE `unit` SET `low`='"+low+"',`high`='"+hight+"',`val`="+val+" WHERE id="+id;
        return ConnectDB.ExucuteAnyQuery(sql);
    }

    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM `unit` WHERE id = "+id;
        return ConnectDB.ExucuteAnyQuery(sql);
    
    }

    @Override
    public void filTable(JTable table) {
        String sql = "Select val , low , high , id From unit;";
        String[] coulmName = { "القيمة", "الصغري", "الكبري", "id"};
        ConnectDB.fillAndCenterTable(sql, table, coulmName);
    
    }

    @Override
    public String lastid() {
        return ConnectDB.LastId("unit", "id");
    }

    public void fillComboHigh(JComboBox jComboBox){
        ConnectDB.fillCombo("unit", "high", jComboBox);
    
    }
    public void fillComboLow(JComboBox jComboBox){
        ConnectDB.fillCombo("unit", "low", jComboBox);
    }

    public String getIdUnitFromNameItems(String nameItem){
        String sql = "Select id from items where name_items='"+nameItem+"';";
        return ConnectDB.getIdFromName(sql);
    }
    
    public String getIdFromNameUnit(String high){
        String sql = "Select id from unit where high ='"+high+"'";
        return ConnectDB.getIdFromName(sql);
    }
    public String getHightUnitFromId(String id){
        String sql = "Select high as id from unit where id="+id;
        return ConnectDB.getIdFromName(sql);
    }
    public String getlowUnitFromId(String id){
        String sql = "Select low as id from unit where id="+id;
        return ConnectDB.getIdFromName(sql);
    }
    public double getValFromId(String id){
        String sql = "Select val as id from unit where id="+id;
        String value =  ConnectDB.getIdFromName(sql);
        return Double.valueOf(value);
    }
    public void setItemData(String idItems , String name_item , String high , int firstbalance){
            this.id_items = idItems;
            this.nameItem = name_item;
            this.id = getIdFromNameUnit(high);
            this.firstbalance = firstbalance;
    
    }
    public String lastiditem(){
        return ConnectDB.LastId("items", "id_items");
    
    }
    public boolean saveItems(){
        String sql = "INSERT INTO items values("+id_items+",'"+nameItem+"',"+firstbalance+","+id+")";
        return ConnectDB.ExucuteAnyQuery(sql);
    }
    public boolean updateItems(String id_item){
        //UPDATE `items` SET `name_items`='زئبق',`firstbalance`=20,`id`=1 WHERE `id_items`= 1
        String sql = "UPDATE items SET name_items='"+nameItem+"' , firstbalance="+firstbalance+", id="+id+" WHERE id_items="+id_item;
        return ConnectDB.ExucuteAnyQuery(sql);
    }
    public boolean deleteItems(String id_item){
        String sql = "DELETE FROM items WHERE id_items="+id_item;
        return ConnectDB.ExucuteAnyQuery(sql);
    }
    public void fillTableItems(JTable jtable){
        String sql = "Select i.firstbalance , u.high , i.name_items , i.id_items from items i inner join unit u on i.id = u.id ";
        String coulmname[] = { "رصيد اول المدة", "الوحدة", "الاسم", "مسلسل"};
        ConnectDB.fillAndCenterTable(sql, jtable, coulmname);
    }
    public void fillComboNameItems(JComboBox comboName){
        ConnectDB.fillCombo("items", "name_items", comboName);
    }
    public String getIdItemsFromName(String nameItem){
        String sql = "SELECT id_items as id FROM items WHERE name_items='"+nameItem+"';";
        return ConnectDB.getIdFromName(sql);
    }
//    public String getIdUnitFromName(String nameItem){
//        String sql = "SELECT id FROM items WHERE name_items='"+nameItem+"';";
//        return ConnectDB.getIdFromName(sql);
//    }
    public String getLastIdPriceList(){
        return ConnectDB.LastId("priselist", "id_priceList");
    }
    public void setDataPriceList(Date last_edit ,double purchase_price_low , double purchase_price_high , double sale_price_low , double sale_price_high , String name_item  ){
        this.purchase_price_high = purchase_price_high;
        this.purchase_price_low = purchase_price_low;
        this.sale_price_high = sale_price_high;
        this.sale_price_low = sale_price_low;
        this.id_items = getIdItemsFromName(name_item);
        this.last_edit = Tools.dateSql(last_edit);
        this.id_priceList = getLastIdPriceList();
    }
    public void fillTableListPrice(JTable table){
        String[] columnName = {"سعر البيع", "سعر الشراء", "الصنف", "التاريخ", "م"};
        String sql = "select p.sale_price_high , p.purchase_price_high , i.name_items , p.last_edit , p.id_priceList from priselist p inner join items i on p.id_items = i.id_items order by last_edit DESC  ";
        ConnectDB.fillAndCenterTable(sql, table, columnName);
    }
    public boolean SaveListPrice(){
       String sql = "INSERT INTO priselist VALUES("+id_priceList+",'"+last_edit+"' , "+purchase_price_low+","+purchase_price_high
               +","+sale_price_low+","+sale_price_high+","+id_items+");";
       return ConnectDB.ExucuteAnyQuery(sql);
    }
    public boolean deleteListPrice(){
        String sql = "DELETE FROM `priselist` WHERE id_priceList="+getId_priceList();
        return ConnectDB.ExucuteAnyQuery(sql);
    }
    public boolean updateListPrice(){
        String sql = "UPDATE priselist SET last_edit='"+last_edit+"' , purchase_price_low = "+purchase_price_low+", "
                + "purchase_price_high = "+purchase_price_high + ", sale_price_low = "+sale_price_low+","
                + "sale_price_high = "+sale_price_high+", id_items = "+id_items+" WHERE id_priceList ="+getId_priceList();
        return ConnectDB.ExucuteAnyQuery(sql);
    }
    
    public String getSalesPriceHight(String nameItems){
        String id_items = getIdItemsFromName(nameItems);
        String sql = "select sale_price_high as id from priselist where id_items="+id_items+" ORDER BY last_edit DESC LIMIT 1 ; ";
        return ConnectDB.getIdFromName(sql);
    }
    public String getSalesPriceLow(String nameItems){
        String id_items = getIdItemsFromName(nameItems);
        String  price = "0.0";
        if(!(id_items == null)){
            String sql = "select sale_price_low as id from priselist where id_items="+id_items+" ORDER BY last_edit DESC LIMIT 1 ; ";
            try{
                price = ConnectDB.getIdFromName(sql);
            }catch(Exception ex){
                System.err.println(ex.getMessage());
                price = "0";
            }        
        }
        return price;
    }
    
    public String getSalesPriceHightOrLow(String nameItems , String type){
        String id_items = getIdItemsFromName(nameItems);
        String sql ="";
        String low = ConnectDB.getIdFromName("select low as id from unititems where id_items="+id_items);
        String high = ConnectDB.getIdFromName("select high as id from unititems where id_items="+id_items);
        if(type.equals(low)){
          sql = "select sale_price_low as id from priselist where id_items="+id_items+" ORDER BY last_edit DESC LIMIT 1 ; ";
        }else if(type.equals(high)){
          sql = "select sale_price_high as id from priselist where id_items="+id_items+" ORDER BY last_edit DESC LIMIT 1 ; ";
        }
        else{
            Tools.showErrorMsg("خطأ في الوحدة");
        }
        return ConnectDB.getIdFromName(sql);
    }

    public String getId_priceList() {
        return id_priceList;
    }

    public void setId_priceList(String id_priceList) {
        this.id_priceList = id_priceList;
    }
    public String getPurchasePriceHightOrLow(String nameItems , String type){
        String id_items = getIdItemsFromName(nameItems);
        String sql ="";
        String low = ConnectDB.getIdFromName("select low as id from unititems where id_items="+id_items);
        String high = ConnectDB.getIdFromName("select high as id from unititems where id_items="+id_items);
        if(type.equals(low)){
          sql = "select purchase_price_low as id from priselist where id_items="+id_items+" ORDER BY last_edit DESC LIMIT 1 ; ";
        }else if(type.equals(high)){
          sql = "select purchase_price_high as id from priselist where id_items="+id_items+" ORDER BY last_edit DESC LIMIT 1 ; ";
        }
        else{
            Tools.showErrorMsg("خطأ في الوحدة");
        }
        return ConnectDB.getIdFromName(sql);
    }
    public String getNameItemFromId(String id_item){
        String sql = "Select name_items as id from items where id_items="+id_item;
        return  ConnectDB.getIdFromName(sql);
    }
    

}
