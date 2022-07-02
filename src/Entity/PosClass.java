/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Utilities.ConnectDB;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

/**
 *
 * @author mosta
 */
public class PosClass {
    private int id_pos;
    private String name_pos; 
    private Connection con;
    private Statement stmt;
    private ResultSet rst;
    public void fillCombo(JComboBox comb){
        ConnectDB.fillCombo("pos", "name_pos", comb);
    }
    
    public String getIdPosFromNamePos(String namePos){
       return ConnectDB.getIdFrmName("pos", namePos);
    }
    public void fillCombNoteUtitilyByIdPos(int id_pos,JComboBox box){
        ConnectDB.fillCombo("utility_masary WHERE id_pos="+id_pos,"note_utility" , box);
    }
    public List<String> NoteUtiliteyList(int id_pos){
       List<String> Utilities = new ArrayList<String>();
        try {
            
            con = ConnectDB.getCon();
            stmt = (Statement) con.createStatement();
            String sql = "SELECT note_utility FROM utility_masary WHERE id_pos="+id_pos;
            //int i = 0;
            rst = stmt.executeQuery(sql);
            String elment;
            while (rst.next()) {
                elment = rst.getString(1);
                Utilities.add(elment);
            }
           
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(PosClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Utilities;
    }
}
