/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import javax.swing.JTable;

/**
 *
 * @author mosta
 */
public interface Ent {
    public boolean Save();
    public boolean update(String id);
    public boolean delete(String id);
    public void filTable(JTable table);
    public String lastid();
}
