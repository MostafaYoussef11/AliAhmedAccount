/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accountali;

import Frams.Login;
import Frams.MasarySellFrame;
import Frams.VFCashFrame;
import Frams.menuCashFrame;
import Utilities.Tools;


/**
 *
 * @author mosta
 */
public class AccountAli {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Login l = new Login();
        l.setLocationRelativeTo(null);
        l.setVisible(true); 
        //Tools.openJFram(new menuCashFrame());
       
    }
    
}
