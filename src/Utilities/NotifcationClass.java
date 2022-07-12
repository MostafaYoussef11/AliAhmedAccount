/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Frams.ListNotification;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;

import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JWindow;



/**
 *
 * @author mosta
 */
public final class NotifcationClass extends TimerTask{
    NotifacionList notif;
    private Connection con;
    private  JWindow autoPoupWindo;
    private  JPanel AllFieldpanel;
    
    TimerTask task;
    int count = 0;
    int size = 0;
    int temp_size = 0;
    private Window main;
    private JLabel button;
    public NotifcationClass(Window main , JLabel button) {
        this.main = main;
        this.button = button;
        button.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button.setIcon(new ImageIcon(getClass().getResource("/Icons/bell.png"))); 
                        showPopuMenuNotification();
                      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.  
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                       
                       //showPopuMenuNotification();
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                });
    }

    private ArrayList fillArrayList() throws SQLException{
        ArrayList<NotifacionList> notifList = new ArrayList<NotifacionList>();
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rst = null;
        try {          
            connection = ConnectDB.getCon();
            String datePay = Tools.dateSql(new Date());
            String sql = "SELECT note FROM `notifcation` WHERE date_notifcation = ?";//where date_notifcation = "+datePay;
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, datePay);
            rst = pstm.executeQuery();
            while(rst.next()){
                notif = new NotifacionList(rst.getString(1));
                notifList.add(notif);
            }
            ConnectDB.close(connection);
        } catch (SQLException ex) {
            ConnectDB.close(connection);
            Logger.getLogger(NotifcationClass.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if(rst != null){
                rst.close();
            }
            if(pstm != null){
                 pstm.close();
            }
            if(connection != null){
                ConnectDB.close(connection);
            }
        
        }
        return notifList;
    }

    @Override
    public void run() {
        try {
            ArrayList<NotifacionList> notification = fillArrayList();
            temp_size = size;
            size = notification.size();
            if(temp_size != size){
                if(autoPoupWindo != null){autoPoupWindo.setVisible(false);}
                button.setIcon(new ImageIcon(getClass().getResource("/Icons/notification.png")));
                autoPoupWindo = new JWindow(main);
                autoPoupWindo.setOpacity(0.75f);
                AllFieldpanel = new JPanel(new GridLayout(0,1));
                AllFieldpanel.setBackground(new Color(0.76f,0.98f,0.91f, 0.4f));
                for(int i = 0 ; i < size ; i++){
                    //String dateSt = notification.get(i).datePay;
                    String note = notification.get(i).Note;
                    AllFieldpanel.add(new ListNotification(note));    
                }
                
            }else{ 
                System.out.println("arrySize == oldSize");
                return;
            }
            count++;
            System.out.println("count : " + count + " Array Size = " + size + " Old size = " + temp_size);
        } catch (SQLException ex) {
            Logger.getLogger(NotifcationClass.class.getName()).log(Level.SEVERE, null, ex);
        }
                
}
    
    public void showPopuMenuNotification(){
        if(autoPoupWindo.isVisible()){
            autoPoupWindo.setVisible(false);
            autoPoupWindo.dispose();
        }
        else{
            autoPoupWindo.add(AllFieldpanel);
            int height = (31 * size) - 1;
            autoPoupWindo.setSize(320,height);
            autoPoupWindo.setLocation(button.getLocation().x + 32 , button.getLocation().y-height +35 );
            autoPoupWindo.setVisible(true);
            autoPoupWindo.revalidate();
            autoPoupWindo.repaint();               
        }
   
    }
}





class NotifacionList{
    String Note;
    public NotifacionList(String Note ) {
        this.Note = Note;
    }
 
}
