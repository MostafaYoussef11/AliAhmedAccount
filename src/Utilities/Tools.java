/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Frams.MainFrame;
import Frams.SearchPurchaseFrame;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Component;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
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
    AudioInputStream audioInputStream;
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
        lab.setHorizontalAlignment(JLabel.CENTER);
        lab.setText(text);
        lab.setFont(font(14f));
        return lab;
        
  }
  
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
 public static void CenterTable(String[] coulmnName , JTable table){
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
//        center.setOpaque(false);
        int count = coulmnName.length;
        for(int i = 0 ; i< count ; i++){
            table.getColumn(coulmnName[i]).setCellRenderer(center);
        }


        
    }
  public static void setBackground(JLabel background , Dimension dim , String nameImage){
        background.setLocation(0, 0);
        background.setSize(dim);
        background.setPreferredSize(dim);
        ImageIcon bg = new ImageIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/icons/"+nameImage)).getImage().getScaledInstance(dim.width, dim.height, Image.SCALE_DEFAULT));
        background.setIcon(bg);
  }
 
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
            InputStream stream = new BufferedInputStream(new FileInputStream("Fonts/vip.ttf"));
            font = Font.createFont(Font.TRUETYPE_FONT, stream); 
        } catch (FileNotFoundException ex) {
            font = Font.getFont("Tahoma") ;
            Logger.getLogger(SearchPurchaseFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FontFormatException | IOException  ex) {
            font = Font.getFont("Tahoma") ;
            Logger.getLogger(SearchPurchaseFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return font.deriveFont(Font.BOLD, size);
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
    public static void runMedia(String bip){
        JFXPanel jfx = new JFXPanel();
        String uri = new File(bip).toURI().toString();
        MediaPlayer media =  new MediaPlayer(new Media(uri));
        media.setStartTime(Duration.seconds(2));
        media.play();
    } 
    
    
    public void playSound(String name) {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(getClass().getResource(name).getPath()).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            if(clip.isRunning()){
                Tools.showErrorMsg("test");
            }
        } catch(IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            System.out.println("Error with playing sound.");
        }
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
 /**   
    public static void SearchFieldFromList(JList table , JTextField txtsearch){

        ArrayList list = table.g`
        txtsearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
               String text = txtsearch.getText();

                if (text.trim().length() == 0) {
//                    rowSorter.setRowFilter(null);
                       Collections.sort(table.getModel());
                       fireContentsChanged(this, 0, songs.size());
                } else {
                    //rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
              String text = txtsearch.getText();
                if (text.trim().length() == 0) {
                    //rowSorter.setRowFilter(null);
                } else {
                    //rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    
    }**/

}
