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
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author mosta
 */
public class masary_Utilites {
    /**
     INSERT INTO `utility_masary` (`id_utility_masary`, `name_utility_masary`, `note_utility`, `cost_by_perse`) VALUES ('', NULL, NULL, NULL)

     */
   private Connection con;
   private PreparedStatement pstm;
   private Statement stmt;
   private int id_utility_masary , id_category;
   private String name_utility_masary , note_utility ;
   private double cost_by_perse ,price;

    public int getId_utility_masary() {
        return id_utility_masary;
    }

    public int getId_category() {
        return id_category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setId_category(String name_category) {
        String id_cat = ConnectDB.getIdFromName("SELECT id_category AS id FROM categoryutilites WHERE name_category='"+name_category+"'");
        int id_Catogray = Integer.parseInt(id_cat);
        this.id_category = id_Catogray;
    }

    public void setId_utility_masary(int id_utility_masary) {
        this.id_utility_masary = id_utility_masary;
    }

    public String getName_utility_masary() {
        return name_utility_masary;
    }

    public void setName_utility_masary(String name_utility_masary) {
        this.name_utility_masary = name_utility_masary;
    }

    public String getNote_utility() {
        return note_utility;
    }

    public void setNote_utility(String note_utility) {
        this.note_utility = note_utility;
    }

    public double getCost_by_perse() {
        return cost_by_perse;
    }
    public double getCost(String noteUtilites) {
        String cost = ConnectDB.getIdFromName("select cost_by_perse as id from utility_masary where note_utility = '"+noteUtilites+"'");
        double costbyperse;
        try{
            if(cost.isEmpty()){
                costbyperse = 0;
            }else{
                 costbyperse = Double.parseDouble(cost);
            }
            
        }catch(NumberFormatException ex){
            costbyperse = 0;
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return costbyperse;
    }
    public void setCost_by_perse(double cost_by_perse) {
        this.cost_by_perse = cost_by_perse;
    }
   
    public void fillTable(JTable table){
        String sql = "SELECT `cost_by_perse`, `note_utility`, `name_utility_masary`,`id_utility_masary` FROM `utility_masary`";
        String[] coulmnName = {"التكلفة", "البيان", "الخدمة", "م"};
        ConnectDB.fillAndCenterTable(sql, table, coulmnName);
    
    }
    public boolean Save(){
       boolean isSave = false;
        try {  
           con = ConnectDB.getCon();
           String sql_Save = "INSERT INTO `utility_masary` (`name_utility_masary`, `note_utility`, `cost_by_perse`,`price`) VALUES ( ?, ?, ?,? )";
           pstm = con.prepareStatement(sql_Save, Statement.RETURN_GENERATED_KEYS);
           
           pstm.setString(1, name_utility_masary);
           pstm.setString(2, note_utility);
           pstm.setDouble(3, cost_by_perse);
           pstm.setDouble(4, price);
           int rowAffact = pstm.executeUpdate();
           if(rowAffact == 1){
               isSave = true;
           }
       } catch (SQLException ex) {
           Logger.getLogger(masary_Utilites.class.getName()).log(Level.SEVERE, null, ex);
       }
        
        return isSave;
    }   
    
    public boolean Delete(){
        String sql = "DELETE FROM utility_masary WHERE id_utility_masary ="+id_utility_masary;
        return ConnectDB.ExucuteAnyQuery(sql);
    }
   
    public void fillComboCategoryUtilites(JComboBox combo){
        ConnectDB.fillCombo("categoryutilites", "name_category", combo);
    }
    public void fillComboUtilites(JComboBox comb ,String nameCatogry){
        ConnectDB.fillCombo("utility_masary WHERE name_utility_masary = '"+nameCatogry+"'", "note_utility", comb);
    }
    
    public int getIdByNote(String note){
        int id ;
        if(note.isEmpty()){
            return 0;
        }else{
            String sql_select = "SELECT id_utility_masary AS id FROM utility_masary WHERE note_utility='"+note+"'";
            String id_st = ConnectDB.getIdFromName(sql_select);
            if(id_st != null) {
                id = Integer.parseInt(id_st);
            } else {
                id = 0;
            }
            return id;
        }
        
    }
    
    public String getPriceByNote(String note){
        String sql_selectPrice = "SELECT price As id FROM utility_masary WHERE note_utility='"+note+"'";
        return ConnectDB.getIdFromName(sql_selectPrice);
    }
    
    public boolean isRequierPhoneNumber(String name_utility){
        boolean isRequier = false;
        String sql_select = "SELECT requier_phone AS id FROM categoryutilites WHERE name_category='"+name_utility+"'";
        String requierStr = ConnectDB.getIdFromName(sql_select);
        if(requierStr.equals("1")) isRequier = true;
        return isRequier;
    }
}
