/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

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
    private PreparedStatement pstmt_send , pstmt_casher , pstmt_number;
    private Statement stmt; 
    private final VFCashClass vf = new VFCashClass();
    private final ClientPerson client = new ClientPerson();
    /**
     INSERT INTO `send_receive` (
     * , `type_Send_Receive`, `value_Send_Receive`, `amount_Send_Receive`, `id_VF_cash`, 
     * `Number_client`, `id_client`, `id_Suppliers`, `take_money`) VALUES (NULL, 'curdate()'
     * , 'curtime().000000', NULL, '', '', NULL, NULL, '1', '1', '1'
     * @param value)
     * @param amount
     * @param Number_VF
     * @param Number_client
     * @param name_client*/
    public void SetDataReceive(double value , double amount , String Number_VF , String Number_client , String name_client){
        this.value_Send_Receive = value;
        this.amount_Send_Receive = amount;
        this.Number_VF_cash = Number_VF;
        this.id_VF_cash = vf.getId_VF_ByNumber(Number_VF);
        this.id_client = Integer.parseInt(client.getIdByName(name_client));
        this.Number_client = Number_client;
    }
    
    
}
