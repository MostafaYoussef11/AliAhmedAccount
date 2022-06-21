/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Frams.MainFrame;
import java.util.Timer;
import java.util.TimerTask;



/**
 *
 * @author mosta
 */

public final class CheckCasher{
    private String now_balance;
    int cont = 0;
    Timer t = new Timer();
    public CheckCasher() {
        now_balance = new CasherClass().getNowBalanceCasher(); 
        
    }
    

    public String getNow_balance() {
        return now_balance;
    }
 

    public void runTimer(){
        t.schedule(new TimerTask() {

            @Override
            public void run() {
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
              now_balance = new CasherClass().getNowBalanceCasher();
              runTimer();
            }
        }, 2000);
    }
//    @Override
//    public void run() {
//      
//        
//    }
   
    
}
