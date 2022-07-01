/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Utilities.ConnectDB;
import javax.swing.JComboBox;

/**
 *
 * @author mosta
 */
public class PosClass {
    private int id_pos;
    private String name_pos; 
    
    public void fillCombo(JComboBox comb){
        ConnectDB.fillCombo("pos", "name_pos", comb);
    }
    
    public String getIDFromNamePos(String name){
       return ConnectDB.getIdFrmName("pos", name);
    }
}
