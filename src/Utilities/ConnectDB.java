/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mosta
 */
public class ConnectDB {
  private static Connection con;
  private static Statement stmt;
  
  private static void SetConnection(){
      try{
        Class .forName("com.mysql.jdbc.Driver");
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ali?useUnicode=yes&characterEncoding=UTF-8", "root","");

      }catch(ClassNotFoundException | SQLException ex){
          Tools.showErrorMsg(ex.getMessage());
      }
  
  }
  
  public static boolean chakeusername(String username, String passwod){
      boolean isTrue = false;
      try{
          SetConnection();
          stmt = (Statement) con.createStatement();
          String sql = "Select * from users where username = '"+username+"' and passwod ='"+passwod+"' ;";
          ResultSet rst = stmt.executeQuery(sql);
          if(rst.next()){
              isTrue = true;
          }
          con.close();
          return isTrue;
      }catch(SQLException ex){
         //  System.err.println(ex.getMessage());
           Tools.showErrorMsg(ex.getMessage());
           return isTrue;
       }
  
  }
  public static boolean ExucuteAnyQuery(String sql){
      try{
          SetConnection();
          stmt = (Statement) con.createStatement();
          stmt.execute(sql);
          con.close();
          return true;
      }catch(SQLException ex){
         //  System.err.println(ex.getMessage());
           Tools.showErrorMsg(ex.getMessage());
           return false;
       }
      
  
  }
  
  
  public static void fillAndCenterTable(String sql , JTable table , String[] coulmnName){
       try{
           SetConnection();
           stmt = (Statement) con.createStatement();
           ResultSet rst = stmt.executeQuery(sql);
           ResultSetMetaData rstmd = rst.getMetaData();
           Vector v = new Vector();
           int count = rstmd.getColumnCount();
           DefaultTableModel model = (DefaultTableModel) table.getModel();
           Tools.CenterTable(coulmnName, table);
           model.setRowCount(0);
           while(rst.next()){
               v = new Vector(count);
               for(int i =1 ; i<=count ; i++){
                   v.add(rst.getString(i));
               }
               model.addRow(v);
           }
           con.close();
       }catch(SQLException ex){
           Tools.showErrorMsg(ex.getMessage());
       }
   } 
  
  public static String LastId(String tableName,String CoulmName){
       try{
           String Id = null;
           SetConnection();
           stmt = (Statement) con.createStatement();
           stmt.executeQuery("SELECT MAX("+CoulmName+")+1 AS lastId FROM "+tableName);
           ResultSet rst = stmt.getResultSet();
           while(rst.next()){
               Id = rst.getString("lastId");
           }
           con.close();
           if(Id == null || Id.equals("0")){
               return "1";
           }else{
               return Id;
           }
       }
       catch(SQLException ex){
           Tools.showErrorMsg(ex.getMessage());
           return null;
       }
   } 
 public static void fillCombo(String tableName , String coulmName , JComboBox combo){
       try{
           SetConnection();
           stmt = (Statement) con.createStatement();
           ResultSet rst;
           String sql = "SELECT "+coulmName+" FROM "+tableName +";";
           rst = stmt.executeQuery(sql);
           rst.last();
           int c = rst.getRow();
           rst.beforeFirst();
           String values[] = new String[c];
           int i = 0;
           while(rst.next()){
               values[i]=rst.getString(1);
               i++;
           }
           con.close();
           ((JLabel)combo.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
           combo.setModel(new DefaultComboBoxModel(values));
       }catch(SQLException ex){
           Tools.showErrorMsg(ex.getMessage());
       
       }
   }
 
 public static String getIdFromName(String sql){
       try{
           String id = "";
           SetConnection();
           stmt = (Statement) con.createStatement();
           //String sql = "SELECT "+tablename+" AS id FROM "+tablename+" where name"+tablename+" ='"+name+"';";
           ResultSet rst = stmt.executeQuery(sql);
           while(rst.next()){
               id = rst.getString("id");
           }
           con.close();
           return id;
       
       }catch(SQLException ex){
           return "";
       }
   
   }   
  public static String getIdFrmName(String tablename , String name){
       try{
           String id = "";
           SetConnection();
           stmt = (Statement) con.createStatement();
           String sql = "SELECT id_"+tablename+" AS id FROM "+tablename+" where name_"+tablename+" ='"+name+"';";
           ResultSet rst = stmt.executeQuery(sql);
           while(rst.next()){
               id = rst.getString("id");
           }
           con.close();
           return id;
       
       }catch(SQLException ex){
           return "";
       }
   
   }
 
}
