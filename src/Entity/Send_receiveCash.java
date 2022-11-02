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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author mosta
 */
public class Send_receiveCash {
    public enum type_Send_Receive {Send, Receive} ;
    private double value_Send_Receive , amount_Send_Receive;
    private int id_VF_cash , id_client ,id_Suppliers ;
    private String Number_VF_cash, Number_client , name_client , name_supplier;
    private boolean take_money;
    private Connection con;
    private PreparedStatement pstmt_send , pstmt_casher , pstmt_number , pstmt_Client;
    private Statement stmt; 
    private final VFCashClass vf = new VFCashClass();
    private final ClientPerson client = new ClientPerson();
    private final CasherClass casher = new CasherClass();
    private final Suppliers suppliers = new Suppliers();
    /**
     INSERT INTO `send_receive` (
     * , `type_Send_Receive`, `value_Send_Receive`, `amount_Send_Receive`, `id_VF_cash`, 
     * `Number_client`, `id_client`, `id_Suppliers`, `take_money`) VALUES (NULL, 'curdate()'
     * , 'curtime().000000', NULL, '', '', NULL, NULL, '1', '1', '1'
     *  value)
     * amount
     *  Number_VF
     *  Number_client
     *
     * @param table
     * @param type  'Send'  or 'Recieve' */
    
    //fill Table Send Transaction only
    public void fillTableSend(JTable table){
        String[] columnName = {"المبلغ", "القيمة", "المحفظة", "الوقت", "التاريخ", "م"};
        String sql_select = "SELECT send.amount_Send_Receive , send.value_Send_Receive , v.number_VF_cash , send.time_Send_Receive"
                + " , send.date_Send_Receive , send.id_Send_Receive FROM send_receive AS send INNER JOIN vf_cash AS v ON "
                + "send.id_VF_cash = v.id_VF_cash WHERE send.type_Send_Receive = 'Send' ORDER BY send.id_Send_Receive DESC; ";
        ConnectDB.fillAndCenterTable(sql_select, table, columnName);
    }
   public void fillTableRecive(JTable table){
        String[] columnName = { "استلم النقديه", "المبلغ", "القيمة", "المحفظة", "الوقت", "التاريخ", "المسلسل"};
        String sql_select = "SELECT send.take_money , send.amount_Send_Receive , send.value_Send_Receive , v.number_VF_cash , send.time_Send_Receive"
                + " , send.date_Send_Receive , send.id_Send_Receive FROM send_receive AS send INNER JOIN vf_cash AS v ON "
                + "send.id_VF_cash = v.id_VF_cash WHERE send.type_Send_Receive = 'Receive' ORDER BY send.date_Send_Receive DESC; ";
        ConnectDB.fillAndCenterTable(sql_select, table, columnName);
    }
    public void SetDataSend(double value , double amount , String Number_VF , String Number_client , String name_client) throws SQLException{
        this.value_Send_Receive = value;
        this.amount_Send_Receive = amount;
        this.Number_VF_cash = Number_VF;
        this.id_VF_cash = vf.getId_VF_ByNumber(Number_VF);
        if(name_client.length() == 0) {
            this.id_client = 1 ;
        }
        else
        {
            this.id_client = Integer.parseInt(client.getIdByName(name_client));
        }
        this.Number_client = Number_client;
    }
    
    
    
    
    
//    public boolean SaveSendTransaction() throws SQLException{
//        boolean isSaved = false;
//        int id_send = GetIntInsertTransaction("Send");
//        int rowAffectCasher = casher.SavedCasherTransaction(TypeCasherTransaction.Send_VF, amount_Send_Receive, "تحويل فودافون كاش " + Number_client, id_send);
//        if(rowAffectCasher == 1){
//              isSaved = true;
//        }
//        return isSaved;
//    }
    
//    
//    public boolean SaveRecieveTransaction() throws SQLException{
//        boolean isSaved = false;
//          int id_send = GetIntInsertTransaction("Receive");
//          int rowAffectCasher = casher.SavedCasherTransaction(TypeCasherTransaction.Receive_VF, amount_Send_Receive, "تحويل فودافون كاش " + Number_client, id_send);
//          if(rowAffectCasher == 1){
//                isSaved = true;
//          }  
//    
//        return isSaved;
//    }
    
    // Returen id_SentRecive
    
    public boolean SaveSendAndReciveTransaction(String type) throws SQLException{
           boolean isSaved = false;
           try{
                con = ConnectDB.getCon();
                con.setAutoCommit(false);
                String sql_insertSend = "INSERT INTO `send_receive` ( `type_Send_Receive`, `value_Send_Receive`, `amount_Send_Receive`, `id_VF_cash`,"
                        + "`Number_client`, `id_client`) VALUES (?,?,?,?,?,?)";
                pstmt_send = con.prepareStatement(sql_insertSend, Statement.RETURN_GENERATED_KEYS);
                pstmt_send.setString(1, type);
                pstmt_send.setDouble(2, value_Send_Receive);
                pstmt_send.setDouble(3, amount_Send_Receive);
                pstmt_send.setInt(4, id_VF_cash);
                pstmt_send.setString(5, Number_client);
                pstmt_send.setInt(6, id_client);
                int rowAffectSend = pstmt_send.executeUpdate();
                ResultSet rst = pstmt_send.getGeneratedKeys();
                if(rowAffectSend == 1){
                    int id_send = 0;
                    while(rst.next()){
                        id_send = rst.getInt(1);
                    }
                  switch(type){
                      case "Receive" :
                        pstmt_casher = casher.SavedCasherTransaction(TypeCasherTransaction.Receive_VF, amount_Send_Receive, "تحويل فودافون كاش " + Number_client, id_send, con);
                        break;
                       case "Send":
                        pstmt_casher = casher.SavedCasherTransaction(TypeCasherTransaction.Send_VF, amount_Send_Receive, "تحويل فودافون كاش " + Number_client, id_send, con);
                        break;
                  } 
                  if(pstmt_casher.executeUpdate() == 1){
                      Tools.SavePhoneNumber(Number_VF_cash);
                      isSaved = true;
                      con.commit();
                      con.close();
                  }    
                }
                
           }catch(SQLException ex){
                con.rollback();
                con.close();
                Logger.getLogger(Send_receiveCash.class.getName()).log(Level.SEVERE, null, ex);
               
           }
           finally{
               if(pstmt_send != null || pstmt_casher != null){
                   pstmt_casher.close();
                   pstmt_send.close();
               }
               if(con != null){
                   con.close();
               }
              
           }
         return isSaved;
    }
    
   public boolean SaveRecivedFromSupplier(double value , double amount , String  number_Vf , String name_supplier){
        boolean isSave = false;
        Connection conn = null;
       try {
            
            conn = ConnectDB.getCon();
            conn.setAutoCommit(false);
            int id_vf = vf.getId_VF_ByNumber(number_Vf);
            String id_suplier = suppliers.getIdByName(name_supplier);
            String sql_recive = "INSERT INTO `send_receive` (`type_Send_Receive`, `value_Send_Receive`, "
                              + "`amount_Send_Receive`, `id_VF_cash`,`id_Suppliers`) VALUES (?,?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql_recive, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, "Receive");
            pstm.setDouble(2, value);
            pstm.setDouble(3, amount);
            pstm.setInt(4, id_vf);
            pstm.setString(5, id_suplier);
            int row_affect = pstm.executeUpdate();
            int id_recive = 0;
            ResultSet rst = pstm.getGeneratedKeys();
            if(row_affect == 1){
                while(rst.next()){
                    id_recive = rst.getInt(1);
                }
                String sql_supaccount = "INSERT INTO `suppliersaccount` (`Creditor`, `id_Suppliers`,"
                                      + " `id_Send_Receive`, `note`) VALUES (?,?,?,?)";
                PreparedStatement pstm_supaccount = conn.prepareStatement(sql_supaccount, Statement.RETURN_GENERATED_KEYS);
                pstm_supaccount.setDouble(1, amount);
                pstm_supaccount.setString(2, id_suplier);
                pstm_supaccount.setInt(3, id_recive);
                pstm_supaccount.setString(4, "شحن محفظة " + number_Vf);
                int row_sup = pstm_supaccount.executeUpdate();
                if(row_sup == 1){
                    isSave = true;
                    conn.commit();
                    conn.close();
                }
                
            }}
        catch (SQLException ex) {
            try {
                conn.rollback();
                conn.close();
                
                Logger.getLogger(Send_receiveCash.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(Send_receiveCash.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally{
            try {
                
                conn.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(Send_receiveCash.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        return isSave;
   }
   
   public boolean SaveSendToClient(double value , double amount , int id_vf , String number_client , int id_client){
       boolean isSave =false; 
       try {
            con = ConnectDB.getCon();
            con.setAutoCommit(false);
            String Sql_insert_send = "INSERT INTO `send_receive` ( `type_Send_Receive`, `value_Send_Receive`, `amount_Send_Receive`, `id_VF_cash`,"
                        + "`Number_client`, `id_client`) VALUES (?,?,?,?,?,?)";
            pstmt_send = con.prepareStatement(Sql_insert_send, Statement.RETURN_GENERATED_KEYS);
            pstmt_send.setString(1, "Send");
            pstmt_send.setDouble(2, value);
            pstmt_send.setDouble(3, amount);
            pstmt_send.setInt(4, id_vf);
            pstmt_send.setString(5, number_client);
            pstmt_send.setInt(6, id_client);
            int rowAffect = pstmt_send.executeUpdate();
            if(rowAffect == 1){
              String Sql_insert_client = "INSERT INTO `clientaccount` (`Debit`,`id_client`, `note`) VALUES (?,?,?)";
              pstmt_Client = con.prepareStatement(Sql_insert_client, Statement.RETURN_GENERATED_KEYS);
              pstmt_Client.setDouble(1, amount);
              pstmt_Client.setInt(2, id_client);
              pstmt_Client.setString(3, "تحويل فودافون كاش رقم " + number_client);
              int rowClientAffect = pstmt_Client.executeUpdate();
              if(rowClientAffect == 1){
                Tools.SavePhoneNumber(number_client);
                con.commit();
                con.close();
                isSave = true;
              }else{
                  con.rollback();
                  con.close();
                  isSave = false;
              }
            }else{
                con.rollback();
                con.close();
                isSave = false;
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(Send_receiveCash.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   
       return isSave;
   }
}
