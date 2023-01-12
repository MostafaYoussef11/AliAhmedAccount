/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;
//01096931332
import Entity.PosClass;
import Frams.MainFrame;
import Frams.SearchPurchaseFrame;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Color;
import java.awt.Component;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author mosta
 */
public class Tools {
 // private AudioInputStream audioInputStream;
  //Method To Message Show User           رسائل المستخدم
  public static void showErrorMsg(String msg){
      JOptionPane.showMessageDialog(null, msg, "خطأ", JOptionPane.ERROR_MESSAGE);
  }  
  public static void showInfoMsg(String msg , String title){
      JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
  }
  //Methods Open new JFrame       فتح النوافذ
  public static void openJFram(JFrame f){
      f.setLocationRelativeTo(null);
      f.setVisible(true);
  
  }
  public static void openJFram(JFrame f , String title){
      f.setTitle(title);
      openJFram(f);
  }
  public static void openJFram(JFrame f , String title , String nameicon){
      //ImageIcon icon = new ImageIcon();
      f.setIconImage(new ImageIcon("E:\\Masrawy\\Account\\dist\\Icons\\"+nameicon+".png").getImage());
      openJFram(f, title);
  }
  // This Method used to JLable Setting Font and Alignment   
  public static JLabel lable(String text){
        JLabel lab = new JLabel();
        lab.setPreferredSize(new Dimension(100, 40));
        lab.setAlignmentX(CENTER_ALIGNMENT);
        lab.setAlignmentY(CENTER_ALIGNMENT);
        lab.setHorizontalAlignment(JLabel.CENTER);
        lab.setText(text);
        lab.setFont(font(14f));
        return lab;
        
  }
  //Method To Button Enable and Disable   حالات الزر تعطيل و تشغيل علي حسب حالة النافذة
  public static void disableButOpen(JPanel btnPanel){
      for (Component c : btnPanel.getComponents()) {
          if(c.getName().equals("save") || c.getName().equals("exit") || c.getName().equals("search")){
            c.setEnabled(true);
          }else{
              c.setEnabled(false);
          }
      }
  }
  public static void selectButtonTable(JPanel btnPanel){
      for (Component c : btnPanel.getComponents()) {
          if(c.getName().equals("edit") || c.getName().equals("exit") || c.getName().equals("new")){
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
    if(txtPanal != null){
        for(Component c : txtPanal.getComponents()){
            if(c.isDisplayable()){
                c.setEnabled(true);
            }
        }
    }
  }
  // Center Cell on The Table 
  public static void CenterTable(String[] coulmnName , JTable table){
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        int count = coulmnName.length;
        for(int i = 0 ; i< count ; i++){
            table.getColumn(coulmnName[i]).setCellRenderer(center);
        }


        
    }
  // Set The Background on The JFrame
  public static void setBackground(JLabel background , Dimension dim , String nameImage){
        background.setLocation(0, 0);
        background.setSize(dim);
        background.setPreferredSize(dim);
        ImageIcon bg = new ImageIcon(new ImageIcon("E:\\Masrawy\\Account\\dist\\Icons\\"+nameImage).getImage().getScaledInstance(dim.width, dim.height, Image.SCALE_DEFAULT));
        background.setIcon(bg);
  }
  //طرق الدفع الماتحة سواء نقدي او علي الحساب
  public static String payMethod(PaymentMethod payMethod){
        String paymethod = "";
        switch(payMethod){
            case cash:
               paymethod = "كاش";
               break;
            case deferred:
               paymethod = "آجل";
               break; 
            case installments:
                paymethod = "دفعة من الحساب";
                break;
            default:
                paymethod = "كاش";
                break;
        }
        return paymethod; 
    
    }
  public static PaymentMethod getPayment(String paymentMethod){
        PaymentMethod p;
        switch(paymentMethod){
            case "كاش":
               p = PaymentMethod.cash;
               break;
            case "دفعة من الحساب":
               p = PaymentMethod.installments;
               break; 
            case "آجل":
                p = PaymentMethod.deferred;
                break;
            default:
                p = PaymentMethod.cash;
                break;
        }
    
        return p;
    
    }
  //Center JDateChoose توسيط الكتابة في مربع التاريخ
  public static void CenterJDateChos(JDateChooser txtDate){
       JTextFieldDateEditor dateEditor = (JTextFieldDateEditor)txtDate.getComponent(1);
       dateEditor.setHorizontalAlignment(JTextField.CENTER);
       txtDate.setDate(new Date());
       txtDate.setFont(new Font("Dialog", Font.BOLD, 11));
    }
  public static String dateSql(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        return sdf.format(date);
    }
  public static Date dateFrmJTable(String date){
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      Date dat = null;
      try {
           dat = sdf.parse(date);
      } catch (ParseException ex) {
          Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
          
      }
      return dat;
    }
  public static void Printer(String sql ,InputStream stream , HashMap para){        
        try {
            JasperDesign jd = JRXmlLoader.load(stream); 
            JRDesignQuery designQuery = new JRDesignQuery();
            designQuery.setText(sql);
            jd.setQuery(designQuery);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            Connection con = ConnectDB.getCon();
            JasperPrint jp = JasperFillManager.fillReport(jr, para, con);
            JasperViewer.viewReport(jp, false);
            
        } catch (NumberFormatException | JRException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
  public static Font font(Float size){
       Font font = null ;
        try {
            //E:\Masrawy\Account\dist\Fonts
            InputStream stream = new BufferedInputStream(new FileInputStream("E:\\Masrawy\\Account\\Fonts\\vip.ttf"));
            font = Font.createFont(Font.TRUETYPE_FONT, stream); 
        } catch (FileNotFoundException ex) {
            font = Font.getFont("Tahoma") ;
            Logger.getLogger(SearchPurchaseFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FontFormatException | IOException  ex) {
            font = Font.getFont("Tahoma") ;
            Logger.getLogger(SearchPurchaseFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return font.deriveFont(Font.BOLD, size);
        //3170333000100887
    }
  public static String getDayName(Date date){
        String day_ar ="";
        SimpleDateFormat sdf = new SimpleDateFormat("EEEEE");
        String day_En = sdf.format(date);
        switch(day_En){
            case "Monday":
                day_ar = "الاثنين";
                break;
            case "Tuesday" :
                day_ar = "الثلاثاء";
                break;
            case "Wednesday":
                day_ar = "الأربعاء";
                break;
            case "Thursday":
                day_ar = "الخميس";
                break;
            case "Friday":
                day_ar = "الجمعة";
                break;
            case "Saturday":
                day_ar = "السبت";
                break;
            case "Sunday":
                day_ar = "الأحد";
                break;
            default:
                day_ar = day_En;
                break;
        }
        return day_ar;
    }
  public static void SearchField(JTable table , JTextField txtsearch){
       TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);
        txtsearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
               String text = txtsearch.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
              String text = txtsearch.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    
    }
 

}
