/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frams;

import Utilities.Tools;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author mosta
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    Font font = Tools.font(18f);
    public MainFrame() {
        initComponents();
        background.setLocation(0, 0);
        background.setSize(dim);
        background.setPreferredSize(dim);
        ImageIcon bg = new ImageIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/icons/bg.jpg")).getImage().getScaledInstance(dim.width, dim.height, Image.SCALE_DEFAULT));
        background.setIcon(bg);
        int x = dim.width - 220;
        client.setLocation(x - 20, 40);
        client.setFont(font);
        items.setLocation(x - 20 , 250);
        items.setFont(font);
        suppliers.setLocation(x - 20 , 460);
        suppliers.setFont(font);
        x = x - 260;
        Purchases.setLocation(x, 40);
        Purchases.setFont(font);
        sales.setLocation(x, 250);
        sales.setFont(font);
        Reports.setLocation(x, 460);
        Reports.setFont(font);
        x = x - 240;
        Payments.setLocation(x, 40);
        Payments.setFont(font);
        Receipts.setLocation(x, 250);
        Receipts.setFont(font);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Receipts = new javax.swing.JLabel();
        client = new javax.swing.JLabel();
        items = new javax.swing.JLabel();
        suppliers = new javax.swing.JLabel();
        Payments = new javax.swing.JLabel();
        Purchases = new javax.swing.JLabel();
        Reports = new javax.swing.JLabel();
        sales = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);

        Receipts.setFont(new java.awt.Font("VIP Hala Bold", 1, 18)); // NOI18N
        Receipts.setForeground(new java.awt.Color(0, 51, 255));
        Receipts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Receipts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/cash.png"))); // NOI18N
        Receipts.setText("تحصيلات");
        Receipts.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Receipts.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Receipts.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Receipts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReceiptsMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ReceiptsMouseExited(evt);
            }
        });
        getContentPane().add(Receipts);
        Receipts.setBounds(310, 250, 220, 190);

        client.setFont(new java.awt.Font("VIP Hala Bold", 1, 18)); // NOI18N
        client.setForeground(new java.awt.Color(0, 51, 255));
        client.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        client.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/clint.png"))); // NOI18N
        client.setText("العملاء");
        client.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        client.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        client.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        client.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clientMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                clientMouseExited(evt);
            }
        });
        getContentPane().add(client);
        client.setBounds(790, 40, 220, 190);

        items.setFont(new java.awt.Font("VIP Hala Bold", 1, 18)); // NOI18N
        items.setForeground(new java.awt.Color(0, 51, 255));
        items.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        items.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/shelves.png"))); // NOI18N
        items.setText("الاصناف");
        items.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        items.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        items.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        items.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemsMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                itemsMouseExited(evt);
            }
        });
        getContentPane().add(items);
        items.setBounds(790, 250, 220, 190);

        suppliers.setFont(new java.awt.Font("VIP Hala Bold", 1, 18)); // NOI18N
        suppliers.setForeground(new java.awt.Color(0, 51, 255));
        suppliers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        suppliers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/courier.png"))); // NOI18N
        suppliers.setText("الموردين");
        suppliers.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        suppliers.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        suppliers.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        suppliers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                suppliersMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                suppliersMouseExited(evt);
            }
        });
        getContentPane().add(suppliers);
        suppliers.setBounds(790, 470, 220, 190);

        Payments.setFont(new java.awt.Font("VIP Hala Bold", 1, 18)); // NOI18N
        Payments.setForeground(new java.awt.Color(0, 51, 255));
        Payments.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Payments.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/payment_1.png"))); // NOI18N
        Payments.setText("مدفوعات");
        Payments.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Payments.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Payments.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Payments.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PaymentsMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PaymentsMouseExited(evt);
            }
        });
        getContentPane().add(Payments);
        Payments.setBounds(310, 40, 220, 190);

        Purchases.setFont(new java.awt.Font("VIP Hala Bold", 1, 18)); // NOI18N
        Purchases.setForeground(new java.awt.Color(0, 51, 255));
        Purchases.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Purchases.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/selling.png"))); // NOI18N
        Purchases.setText("مشتريات");
        Purchases.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Purchases.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Purchases.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Purchases.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PurchasesMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PurchasesMouseExited(evt);
            }
        });
        getContentPane().add(Purchases);
        Purchases.setBounds(550, 40, 220, 190);

        Reports.setFont(new java.awt.Font("VIP Hala Bold", 1, 18)); // NOI18N
        Reports.setForeground(new java.awt.Color(0, 51, 255));
        Reports.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Reports.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/software.png"))); // NOI18N
        Reports.setText("تقارير");
        Reports.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Reports.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Reports.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Reports.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReportsMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ReportsMouseExited(evt);
            }
        });
        getContentPane().add(Reports);
        Reports.setBounds(550, 470, 220, 190);

        sales.setFont(new java.awt.Font("VIP Hala Bold", 1, 18)); // NOI18N
        sales.setForeground(new java.awt.Color(0, 51, 255));
        sales.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/buy.png"))); // NOI18N
        sales.setText("مبيعات");
        sales.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        sales.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sales.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        sales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salesMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                salesMouseExited(evt);
            }
        });
        getContentPane().add(sales);
        sales.setBounds(550, 250, 220, 190);

        background.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(background);
        background.setBounds(0, 0, 0, 0);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientMouseClicked
        // TODO add your handling code here:
        clickPanel(client);
        ClintFrame c = new ClintFrame();
        Tools.openJFram(c, "العملاء");
    }//GEN-LAST:event_clientMouseClicked

    private void clientMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientMouseExited
        // TODO add your handling code here:
        exitPanel(client);
    }//GEN-LAST:event_clientMouseExited

    private void itemsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemsMouseClicked
        // TODO add your handling code here:
        clickPanel(items);
        itemsFrame items = new itemsFrame();
        Tools.openJFram(items, "الاصناف");
        
        
    }//GEN-LAST:event_itemsMouseClicked

    private void itemsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemsMouseExited
        // TODO add your handling code here:
        exitPanel(items);
    }//GEN-LAST:event_itemsMouseExited

    private void ReceiptsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReceiptsMouseClicked
        // TODO add your handling code here:
        clickPanel(Receipts);
        ReciptFrame r = new ReciptFrame();
        Tools.openJFram(r, "استلام نقدية");
    }//GEN-LAST:event_ReceiptsMouseClicked

    private void ReceiptsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReceiptsMouseExited
        // TODO add your handling code here:
        exitPanel(Receipts);
    }//GEN-LAST:event_ReceiptsMouseExited

    private void suppliersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_suppliersMouseClicked
        // TODO add your handling code here:
        clickPanel(suppliers);
        SuppliersFrame sf = new SuppliersFrame();
        Tools.openJFram(sf, "الموردين");
    }//GEN-LAST:event_suppliersMouseClicked

    private void suppliersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_suppliersMouseExited
        // TODO add your handling code here:
        exitPanel(suppliers);
    }//GEN-LAST:event_suppliersMouseExited

    private void PaymentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PaymentsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_PaymentsMouseClicked

    private void PaymentsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PaymentsMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_PaymentsMouseExited

    private void PurchasesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PurchasesMouseClicked
        // TODO add your handling code here:
        purchaseInvoiceFrame purchfrm = new purchaseInvoiceFrame();
        Tools.openJFram(purchfrm, "فواتير المشتريات");
    }//GEN-LAST:event_PurchasesMouseClicked

    private void PurchasesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PurchasesMouseExited
        // TODO add your handling code here:
        exitPanel(Purchases);
    }//GEN-LAST:event_PurchasesMouseExited

    private void ReportsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReportsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ReportsMouseClicked

    private void ReportsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReportsMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_ReportsMouseExited

    private void salesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salesMouseClicked
        // TODO add your handling code here:
        clickPanel(sales);
        SalesInvoiceFrame sInvoice = new SalesInvoiceFrame();
        Tools.openJFram(sInvoice, "فاتورة مبيعات");
    }//GEN-LAST:event_salesMouseClicked

    private void salesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salesMouseExited
        // TODO add your handling code here:
        exitPanel(sales);
    }//GEN-LAST:event_salesMouseExited
    
    private void clickPanel(JLabel lable){
        lable.setBorder(BorderFactory.createLoweredBevelBorder());
    }
    private void exitPanel(JLabel lable){
        lable.setBorder(BorderFactory.createRaisedSoftBevelBorder());
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Payments;
    private javax.swing.JLabel Purchases;
    private javax.swing.JLabel Receipts;
    private javax.swing.JLabel Reports;
    private javax.swing.JLabel background;
    private javax.swing.JLabel client;
    private javax.swing.JLabel items;
    private javax.swing.JLabel sales;
    private javax.swing.JLabel suppliers;
    // End of variables declaration//GEN-END:variables
}
