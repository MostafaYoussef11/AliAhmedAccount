/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Utilities.ConnectDB;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author mosta
 */
public class masarySellClass {
    private double value_masary_sell;
    private double amount_masary_sell;
    private int id_recharge_type;
    private String name_recharge_type;
    private int id_supplier;
    private int id_pos;
    
    public void fillComboRechargeType(JComboBox box){
        ConnectDB.fillCombo("recharge_type", "name_recharge_type", box);
    }
    public void fillTable(JTable jTable){
        String sql_select = "SELECT rt.name_recharge_type , ms.amount_masary_sell , ms.time_masary_sell , ms.date_masary_sell ,ms.id_masary_sell  FROM masary_sell AS ms INNER JOIN recharge_type AS rt ON ms.id_recharge_type = rt.id_recharge_type ORDER BY ms.id_masary_sell DESC ";
        String[] columnName = {"نوع التوريد", "المبلغ", "الوقت", "التاريخ", "م"};
        ConnectDB.fillAndCenterTable(sql_select, jTable, columnName);
    } 
    
    
}
