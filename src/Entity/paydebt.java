/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Utilities.ConnectDB;
import Utilities.Tools;
import Utilities.saveData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mosta
 */
public class paydebt {
 
    /**
     INSERT INTO `paydebt` ( `id_Solf`, `amount`, `datePay_Solf`) VALUES
     **/
    
    private int id_paydebt , id_Solf  ;
    private double amount ;
    private String datePay_Solf;
    
    private Connection con;
    private PreparedStatement pstm , pstm_casher , pstm_Update_notifaction;
    private ResultSet rst;
    
    private CasherClass casher;
    private SolafClass  solf;
    
    public paydebt(){
        casher = new CasherClass();
        solf = new SolafClass();
    }
 
    public boolean savePay(int id_Solf , double amount , Date datePay) throws SQLException{
        boolean isSave = false;
        String date_pay = Tools.dateSql(datePay);
        double Solfa_amount = 0 ; 
        //int id_paydebt;
        try {
            Solfa_amount = solf.getAmountSolfa(id_Solf);
            con = ConnectDB.getCon();
            con.setAutoCommit(false);
            String sql = "INSERT INTO `paydebt` ( `id_Solf`, `amount`, `datePay_Solf`) VALUES(?,?,?)";
            pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, id_Solf);
            pstm.setDouble(2, amount);
            pstm.setString(3, date_pay);
            int row_affect_paydebt = pstm.executeUpdate();
            if(row_affect_paydebt == 1){
                rst = pstm.getGeneratedKeys();
                while(rst.next()){
                    id_paydebt = rst.getInt(1);
                }
              // insert on casher table 
                String sql_casher = casher.getSQlStatement(TypeCasherTransaction.Paydebt);
                pstm_casher = con.prepareStatement(sql_casher, Statement.RETURN_GENERATED_KEYS);
                pstm_casher.setDouble(1, amount); // credit
                pstm_casher.setString(2, "سداد مبلغ السلفة" + " " + amount);  // note
                pstm_casher.setInt(3, saveData.getIdUser()); // id_user
                pstm_casher.setInt(4, id_paydebt);
                int row_cahser_Affect = pstm_casher.executeUpdate();
                // update Or Delete notification
                if(row_cahser_Affect == 1){
                // لو لم يتم دفع المبلغ بالكامل
                   if(amount < Solfa_amount){
                  // update Notification
                    double newAmount = Solfa_amount - amount;
                    String Sql_Update = "UPDATE `notifcation` SET  `date_notifcation`= ?,`note`= ? WHERE `id_Solf` = ?";
                    pstm_Update_notifaction = con.prepareStatement(Sql_Update, Statement.RETURN_GENERATED_KEYS);
                    pstm_Update_notifaction.setString(1, date_pay);
                    pstm_Update_notifaction.setString(2, "موعد سداد مبلغ " + newAmount + " " + solf.getNameSolfa(id_Solf));
                    pstm_Update_notifaction.setInt(3, id_Solf);
                    isSave = pstm_Update_notifaction.execute();
                    if(isSave){
                        con.commit();
                        con.close();
                    }
                  }
              // المبلغ كامل
              else{
                  String sql_delete = "DELETE FROM `notifcation` WHERE id_Solf = ? ";
                  pstm_Update_notifaction = con.prepareStatement(sql_delete);
                  pstm_Update_notifaction.setInt(1, id_Solf);
                  isSave = pstm_Update_notifaction.execute();
                  if(isSave){
                      con.commit();
                      con.close();
                  }
              
              }
            }
          }
            
        } catch (SQLException ex) {
            con.rollback();
            con.close();
            Logger.getLogger(paydebt.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(pstm != null || pstm_casher != null || pstm_Update_notifaction != null){
                pstm.close();
                pstm_Update_notifaction.close();
                pstm_casher.close();
            }
            if(con != null){
                con.close();
            }
            if(rst != null){
                rst.close();
            }
        }
        
      return isSave;  
    }
    
    public boolean updateNotification(int id_solf){
      boolean isUpdate = false;
//      try{
//          con = ConnectDB.getCon();
//          String sql_update = "";
//      
//      }  
      return isUpdate;
    }
}
