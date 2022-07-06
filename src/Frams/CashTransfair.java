/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frams;

import Entity.VFCashClass;
import Utilities.Tools;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Window;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author mosta
 */
public class CashTransfair {
    
    private final  Window container;
    private JPanel CashTransfPanel;
    private JWindow VCashPopUpWindow;
    private int windowX , windowY;
    private Dimension dim;
    JComboBox<String> combo ;
    private JLabel lableBut;
    private int contOpen;
    public CashTransfair(Window container , JLabel labelButt){
        this.container = container;
        CashTransfPanel = new JPanel(new GridLayout(3, 2));
        CashTransfPanel.setBackground(Color.WHITE.brighter());
        dim = new Dimension(300, 100);
        this.lableBut = labelButt;
        contOpen = 0;    
        VCashPopUpWindow = new JWindow(container);
        VCashPopUpWindow.setOpacity(0.5f);
        addComponentToPanel();
        VCashPopUpWindow.getContentPane().add(CashTransfPanel);
    }
    
    public void ShowPopUpWindow(){
        if(contOpen > 0){
            VCashPopUpWindow.setVisible(false);
            contOpen = 0;
        }else{
           
            VCashPopUpWindow.setMinimumSize(dim);
            VCashPopUpWindow.setSize(dim);
            windowX = container.getX() + lableBut.getX() + 65;
            windowY = container.getY()+lableBut.getY()- 65;
            VCashPopUpWindow.setLocation(windowX,windowY);
            VCashPopUpWindow.revalidate();
            VCashPopUpWindow.repaint();
            VCashPopUpWindow.setVisible(true);   
            contOpen++;
        }

    }
    
    private void addComponentToPanel(){
        JTextField txtNumber = new JTextField(20);
        Border border = new BevelBorder(BevelBorder.RAISED, Color.WHITE.brighter(), Color.BLACK.darker());
        txtNumber.setBorder(border);
        Tools.txtNumberClient(txtNumber, VCashPopUpWindow, windowX, windowY);
        txtNumber.setHorizontalAlignment(JTextField.CENTER);
        txtNumber.setSize(100, 35);
        txtNumber.setLocation(0, 0);
        CashTransfPanel.add(txtNumber,0);
        String[] items = {"item","item","item","item","item"};
        combo = new JComboBox<>(items);
        combo.setLocation(0, 100);
        new VFCashClass().fillCombo(combo);
        CashTransfPanel.add(combo,1);
    
    }
}
class customJtext extends JTextField{

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.black);
        g2.drawRoundRect(0, 0, getWidth()-5, getHeight()-5,20 ,20 );//hDiameter and vDiamater are both 15
        
        super.paintBorder(g);
       // super.paintBorder(g); //To change body of generated methods, choose Tools | Templates.
    }
    

}
