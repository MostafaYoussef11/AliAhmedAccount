/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Timer;
import java.util.TimerTask;



/**
 *
 * @author mosta
 */

public final class CheckCasher {
    private String now_balance;
    int cont = 0;
    Timer t = new Timer();
    public CheckCasher() {
        now_balance =new CasherClass().getNowBalanceCasher(); 
        this.runTimer();
    }
    

    public String getNow_balance() {
        return now_balance;
    }
    public void runTimer(){
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                now_balance = new CasherClass().getNowBalanceCasher();
                cont++;
                System.out.println(cont+" : "+now_balance);
                runTimer();
            }
        }, 2000);
    }
   
    
}
