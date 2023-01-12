/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Utilities.AutoSuggestor;
import Utilities.ConnectDB;
import Utilities.Tools;
import java.awt.Color;
import java.awt.Window;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author mosta
 */
public class numberPhoneClass {
    
  public ArrayList<String> PhoneNumberList(){
       ArrayList<String> numbers;
        numbers = new ArrayList<String>();
        try {
            Connection conn = ConnectDB.getCon();
            com.mysql.jdbc.Statement stmt = (com.mysql.jdbc.Statement) conn.createStatement();
            //String sql_items = "SELECT name_items FROM `items`";
            ResultSet rst = stmt.executeQuery("SELECT numbers  FROM phone_numbers");
            String elment;
            while (rst.next()) {
                elment = rst.getString(1);
                numbers.add(elment);
                
            }
           
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(PosClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numbers;
    }
  public void txtNumberClient(JTextField other,JTextField textField,Window mainWindow ,int windowX , int windowY ){
        AutoSuggestor autoSuggestor = new AutoSuggestor(other,textField, mainWindow, null, Color.WHITE.brighter(), Color.BLUE, Color.red, 0.75f);
        ArrayList<String> listNumber = new ArrayList<String>();
               listNumber = PhoneNumberList();
        autoSuggestor.setDictionary(listNumber);
        autoSuggestor.setLocation(windowY, windowX);
    } 
  public void SavePhoneNumber(String phone) {
        Connection con = ConnectDB.getCon();
      if(phone.length() == 0){
             //System.out.println("الرقم غير مكتوب"); 
        }
      else{  
            ArrayList<String> Phones = PhoneNumberList();
            //System.out.println("Size List : " + Phones.size());
            
            
            int index = IndexOnList(Phones, phone);
            if(index != 0){
              //System.out.println("الرقم مسجل" + index); 
            }else{
                if(phone.trim().length() < 11){
                   // System.out.println("الرقم غير مقبول اقل من 11 ");
                }
                else{
                   try{
                    con.setAutoCommit(false);
                    String sql_inser_num = "INSERT INTO `phone_numbers` (`numbers`) VALUES(?)";
                    PreparedStatement pst = con.prepareStatement(sql_inser_num , Statement.RETURN_GENERATED_KEYS);
                    pst.setString(1, phone.trim());
                    int rowAffect = pst.executeUpdate();
                    if(rowAffect == 1){
                       con.commit();
                       con.close();
                       //System.out.println(phone);
                    }else{
                        con.rollback();
                        con.close();
                        //System.err.println("not Save :" + phone);
                    }
                    
                  }catch(SQLException ex){
                     // System.out.println("الرقم مسجل" + ex.getMessage());  
                 }
                }

            }
         }
    }
    private static int IndexOnList(ArrayList<String> Phones ,String phone ){
       int index = 0;
       int size = Phones.size();
       for(int i = 0 ; i < size ; i++){
           phone = phone.trim();
           if(phone.equals(Phones.get(i))){
               index = i;
           }
       }
       return index;
    }
   
}
