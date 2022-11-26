/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/accountmasrawy?useUnicode=yes&characterEncoding=UTF-8", "root","");

      }catch(ClassNotFoundException | SQLException ex){
         // Tools.showErrorMsg(ex.getMessage());
          Logger.getLogger("SetConnection").log(Level.SEVERE, null, ex);
      }
  
  }
  public static Connection getCon(){
      SetConnection();
      return con;
  }
 
  public static boolean chakeusername(String username, String passwod){
      boolean isTrue = false;
      int idUser = 0;
      try{
          SetConnection();
          stmt = con.createStatement();
          String sql = "Select * from users where username = '"+username+"' and passwod ='"+passwod+"' ;";
          ResultSet rst = stmt.executeQuery(sql);
          if(rst.next()){
              isTrue = true;
              idUser = rst.getInt(1);
          }
          saveData.SetSaveData(idUser, username);
          con.close();
          return isTrue;
      }catch(SQLException ex){
         //  System.err.println(ex.getMessage());
            Logger.getLogger("chakeUserName").log(Level.SEVERE, null, ex);
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
            Logger.getLogger("ExucutQuery").log(Level.SEVERE, null, ex);
           return false;
       }
      
  
  }
  
  
  public static void fillAndCenterTable(String sql , JTable table , String[] columnName){
       try{
           SetConnection();
           stmt = (Statement) con.createStatement();
           ResultSet rst = stmt.executeQuery(sql);
           ResultSetMetaData rstmd = rst.getMetaData();
           Vector v = new Vector();
           int count = rstmd.getColumnCount();
           DefaultTableModel model = (DefaultTableModel) table.getModel();
//           table.setOpaque(false);
//           table.setShowGrid(false);
           Tools.CenterTable(columnName, table);
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
           Logger.getLogger("FillAndCenterTable").log(Level.SEVERE, null, ex);
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
           Logger.getLogger("getLastId").log(Level.SEVERE, null, ex);
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
           String[] items = {"لا توجد اختيارات"};
           combo.setModel(new DefaultComboBoxModel(items));
           
           Logger.getLogger("FillCombo").log(Level.SEVERE, null, ex);
                //throw ex ;
       }
   }
  public static String[] getColumn(String tableName , String coulmName ){
      String values[];
      values = new String[0];
      try{
           SetConnection();
           stmt = (Statement) con.createStatement();
           ResultSet rst;
           String sql = "SELECT "+coulmName+" FROM "+tableName +";";
           rst = stmt.executeQuery(sql);
           rst.last();
           int c = rst.getRow();
           rst.beforeFirst();
           values = new String[c];
           int i = 0;
           while(rst.next()){
               values[i]=rst.getString(1);
               i++;
           }
           con.close();
       }catch(SQLException ex){
           Logger.getLogger("FillCombo").log(Level.SEVERE, null, ex);
       
       }
       return values;
   }
    public static String[] getColumn(String sql ){
      String values[];
      values = new String[0];
      try{
           SetConnection();
           stmt = (Statement) con.createStatement();
           ResultSet rst;
           //String sql = "SELECT "+coulmName+" FROM "+tableName +";";
           rst = stmt.executeQuery(sql);
           rst.last();
           int c = rst.getRow();
           rst.beforeFirst();
           values = new String[c];
           int i = 0;
           while(rst.next()){
               values[i]=rst.getString(1);
               i++;
           }
           con.close();
       }catch(SQLException ex){
           Logger.getLogger("FillCombo").log(Level.SEVERE, null, ex);
       
       }
       return values;
   }
 public static void fillComboUnit( String id , JComboBox combo){
       try{
           SetConnection();
           stmt = (Statement) con.createStatement();
           ResultSet rst;
          // String id = getIdFromName("select id from items where name_items='"+itemName+"'");
           String sqlhigh = "SELECT high , low from unit where id="+id;
           rst = stmt.executeQuery(sqlhigh);
           rst.last();
           int c = rst.getRow()+1;
           rst.beforeFirst();
           String values[] = new String[c];
           int i = 0;
           while(rst.next()){
               values[i]=rst.getString(1);
               values[i+1] = rst.getString(2);
               i++;
           }
           con.close();
           ((JLabel)combo.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
           combo.setModel(new DefaultComboBoxModel(values));
       }catch(SQLException ex){
           Logger.getLogger("FillCombUnit").log(Level.SEVERE, null, ex);
       
       }
   }
 public static String getIdFromName(String sql){
     String id = "";
     try{
           
           SetConnection();
           stmt = (Statement) con.createStatement();
           ResultSet rst = stmt.executeQuery(sql);
           
           rst.last();
           int count = rst.getRow();
           rst.beforeFirst();
           if(count > 0){
             while(rst.next()){
                id = rst.getString("id");
             }
           }
           else{
               id = "0";
           }
           
           con.close();
       }catch(SQLException ex){
         // Logger.getLogger("getIdFromName").log(Level.SEVERE, null, ex);
           System.err.println(ex.getMessage());
           id = "";
       }
     return id;
   
   }   
  public static String getIdFrmName(String tablename , String name) throws SQLException{
      Connection conn = null;
      Statement stm = null;
      ResultSet rs = null;
      String id = "";
      try{
           
           conn = getCon();
           stm = conn.createStatement();
           String sql = "SELECT id_"+tablename+" AS id FROM "+tablename+" where name_"+tablename+" ='"+name+"';";
           rs = stm.executeQuery(sql);
           while(rs.next()){
               id = rs.getString("id");
           }
           conn.close();
       }
       catch(SQLException ex){
              close(conn);
              Logger.getLogger("getIdFromName").log(Level.SEVERE, null, ex);
          
       }
       finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            if(conn != null){
                close(conn);
            }
       }
        return id;
   }
   public static void close(Connection connection) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
        connection = null;
    }
  
// public static void fillPosCombo(JComboBox box){
//     fillCombo("pos", "name_pos", box);
// }
 
}
