/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.awt.Component;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author mosta
 */
public class Tools {
    
  public static void showErrorMsg(String msg){
      JOptionPane.showMessageDialog(null, msg, "خطأ", JOptionPane.ERROR_MESSAGE);
  }  
  public static void showInfoMsg(String msg , String title){
      JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
  }
  public static void openJFram(JFrame f){
      f.setLocationRelativeTo(null);
      f.setVisible(true);
  
  }
  public static void openJFram(JFrame f , String title){
      f.setTitle(title);
      openJFram(f);
  }
  public static void openJFram(JFrame f , String title , String nameicon){
      ImageIcon icon = new ImageIcon();
      f.setIconImage(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getClass().getResource("/icons/"+nameicon+".png")).getImage());
      openJFram(f, title);
  }
  
  
  public static JLabel lable(String text){
        JLabel lab = new JLabel();
        lab.setPreferredSize(new Dimension(100, 40));
        lab.setAlignmentX(CENTER_ALIGNMENT);
        lab.setAlignmentY(CENTER_ALIGNMENT);
        lab.setFont(new Font("VIP Hala Bold", Font.BOLD, 14));
        lab.setHorizontalAlignment(JLabel.CENTER);
        lab.setText(text);
        return lab;
  }
  
  public static void disableButOpen(JPanel btnPanel){
      for (Component c : btnPanel.getComponents()) {
          if(c.getName().equals("save") || c.getName().equals("exit")){
            c.setEnabled(true);
          }else{
              c.setEnabled(false);
          }
      }
  }

  public static void EditButton(JPanel btPanel , JPanel txtPanal){
    for (Component c : btPanel.getComponents()) {
          if(c.getName().equals("save") || c.getName().equals("edit")){
            c.setEnabled(false);
          }else{
              c.setEnabled(true);
          }
      }
    for(Component c : txtPanal.getComponents()){
        if(c.isDisplayable()){
            c.setEnabled(true);
        }
    }
  
  }
 public static void CenterTable(String[] coulmnName , JTable table){
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        int count = coulmnName.length;
        for(int i = 0 ; i< count ; i++){
            table.getColumn(coulmnName[i]).setCellRenderer(center);
        }
    }
  
}
