/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Utilities.ConnectDB;
import Utilities.Tools;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 *
 * @author mosta
 */
public class OhdaClass {
    /*
    INSERT INTO `ohda` ( `date_filtter`,`amount_ohda`, `name_recipient`,
    `note_ohda`, `isActive`)
    VALUES (NULL, 'curdate()', 'curtime().000000', NULL, '', '', NULL, '1')
    **/
    private String date_filtter; 
    private String name_recipient;
    private String note_ohda;
    private boolean isActive;
    private double amount_ohda;
    
    //variable connection
    private Connection con;
    private PreparedStatement pstm_ohda , pstm_Casher;
    private ResultSet rst;
    
    private boolean SaveOhda(Date date_filtter , double amount_ohda , String name_recipient , String note_ohda){
        boolean isSaved = false;
         try{
             con = ConnectDB.getCon();
             con.setAutoCommit(false);
             String sql = "INSERT INTO `ohda` ( `date_filtter`,`amount_ohda`, `name_recipient`,`note_ohda`) VALUES (?,?,?,?)";
             pstm_ohda = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
             String date_filter = Tools.dateSql(date_filtter);
             pstm_ohda.setString(1, date_filter);
             pstm_ohda.setDouble(2, amount_ohda);
             pstm_ohda.setString(3, name_recipient);
             pstm_ohda.setString(4, note_ohda);
             int rowOhda = pstm_ohda.executeUpdate();
             if(rowOhda == 1){
                 rst = pstm_ohda.getGeneratedKeys();
                 int id_ohda =0 ;
                 while(rst.next()){
                     id_ohda = rst.getInt(1);
                 }
                 
             }
             
         }catch(SQLException ex){
         
         }finally{
         
         }
        
        return isSaved;
    }
    
    
    
    
}
