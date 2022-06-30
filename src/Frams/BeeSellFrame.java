/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frams;

import Entity.BeeSell;
import Entity.CasherClass;
import Entity.PosSell;
import Entity.Suppliers;
import Entity.VFCashClass;
import Entity.masarySellClass;
import Utilities.Tools;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author mosta
 */
public class BeeSellFrame extends javax.swing.JFrame {

    /**
     * Creates new form MasarySellFrame
     */
    VFCashClass vcash;
    Font font;
    BeeSell Bee;
    Suppliers suppliers;
    Dimension dim = new Dimension(870, 600);
   // PosSell masary;
    public BeeSellFrame(int type_supplier) {
        initComponents();
        setSize(dim);
        Tools.setBackground(background, dim, "16165.jpg");
        font = Tools.font(32f);
        txt_title.setFont(font);
        Bee = new BeeSell();
        suppliers = new Suppliers();
        suppliers.setId_supplier_type(type_supplier);
        vcash = new VFCashClass();
        newSell();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_title = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        comb_recharge_type = new javax.swing.JComboBox<String>();
        jLabel2 = new javax.swing.JLabel();
        comb_to_account = new javax.swing.JComboBox<String>();
        jLabel3 = new javax.swing.JLabel();
        txt_value = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_amount = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_note = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btPanel = new javax.swing.JPanel();
        btnew = new javax.swing.JButton();
        btsave = new javax.swing.JButton();
        btedit = new javax.swing.JButton();
        btupdate = new javax.swing.JButton();
        btdel = new javax.swing.JButton();
        btexit = new javax.swing.JButton();
        btprint = new javax.swing.JButton();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        txt_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_title.setText("شحن ماكينة Bee");
        getContentPane().add(txt_title);
        txt_title.setBounds(10, 11, 830, 50);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("التوريد");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        comb_recharge_type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comb_recharge_type.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comb_recharge_typeItemStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("من حساب");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        comb_to_account.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("القيمة");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_value.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_value.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_value.setText("0.00");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("المبلغ");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_amount.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_amount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_amount.setText("0.00");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("البيان");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_note.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_note.setText("شحن ماكينة مصاري");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_value, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comb_to_account, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_note))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(comb_recharge_type, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_amount)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comb_recharge_type)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comb_to_account)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_value, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_amount, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_note))
                .addContainerGap())
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 67, 830, 92);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "نوع التوريد", "المبلغ", "الوقت", "التاريخ", "م"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
        }

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 165, 830, 317);

        btPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btPanel.setOpaque(false);

        btnew.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/plus.png"))); // NOI18N
        btnew.setText("جديد");
        btnew.setName("new"); // NOI18N
        btnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnewActionPerformed(evt);
            }
        });

        btsave.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btsave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/save.png"))); // NOI18N
        btsave.setText("حفظ");
        btsave.setName("save"); // NOI18N
        btsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsaveActionPerformed(evt);
            }
        });

        btedit.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btedit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/pencil.png"))); // NOI18N
        btedit.setText("تعديل");
        btedit.setName("edit"); // NOI18N
        btedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bteditActionPerformed(evt);
            }
        });

        btupdate.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btupdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/exchange.png"))); // NOI18N
        btupdate.setText("تحديث");
        btupdate.setName("update"); // NOI18N
        btupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btupdateActionPerformed(evt);
            }
        });

        btdel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btdel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/delete.png"))); // NOI18N
        btdel.setText("حذف");
        btdel.setName("del"); // NOI18N
        btdel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btdelActionPerformed(evt);
            }
        });

        btexit.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btexit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/exit.png"))); // NOI18N
        btexit.setText("خروج");
        btexit.setName("exit"); // NOI18N
        btexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btexitActionPerformed(evt);
            }
        });

        btprint.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btprint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/printer.png"))); // NOI18N
        btprint.setText("طباعة");
        btprint.setName("print"); // NOI18N
        btprint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btprintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout btPanelLayout = new javax.swing.GroupLayout(btPanel);
        btPanel.setLayout(btPanelLayout);
        btPanelLayout.setHorizontalGroup(
            btPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btexit, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btprint, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btdel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btedit, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btsave, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnew, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btPanelLayout.setVerticalGroup(
            btPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(btPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btsave, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btedit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btdel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btexit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btprint, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnew, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(btPanel);
        btPanel.setBounds(10, 488, 830, 61);

        background.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(background);
        background.setBounds(0, 0, 0, 0);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comb_recharge_typeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comb_recharge_typeItemStateChanged
        // TODO add your handling code here:
        int index = comb_recharge_type.getSelectedIndex();
        switch (index) {
            case 0:
                suppliers.FillComboCash(comb_to_account);
                break;
            case 1:
                suppliers.FillComboAllNameSupplierByType(comb_to_account);
                break;
            default:
                vcash.fillCombo(comb_to_account);
                break;
        }
    }//GEN-LAST:event_comb_recharge_typeItemStateChanged

    private void btnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnewActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnewActionPerformed

    private void btsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsaveActionPerformed
        boolean isSave = false;
        String name_recharge_type = comb_recharge_type.getSelectedItem().toString();
        double value = 0 ; 
        try{
            value = Double.parseDouble(txt_value.getText());
        }catch(NumberFormatException ex){
            Logger.getLogger("Sell error").log(Level.SEVERE, null, ex);
        }
        double amount = 0 ; 
        try{
            amount = Double.parseDouble(txt_amount.getText());
        }catch(NumberFormatException ex){
            Logger.getLogger("Sell error").log(Level.SEVERE, null, ex);            
        }
        String to_account = comb_to_account.getSelectedItem().toString();
        switch(name_recharge_type){
            case "نقدي":
                Bee.SetDataAndgeter(value, amount, name_recharge_type, to_account, null);
                isSave = Bee.SaveCash();
                break;
            case "مندوب":
                Bee.SetDataAndgeter(value, amount, name_recharge_type, to_account, null);
                isSave = Bee.SaveAccountSupplier();
                break;
            default:
                Bee.SetDataAndgeter(value, amount, name_recharge_type, null, to_account);
                isSave = false; // method add to VF-Cash
                break;
        }
        if(isSave){
            Tools.showInfoMsg("تم الحفظ بنجاح", "حفظ");
            newSell();
        }
        
    }//GEN-LAST:event_btsaveActionPerformed

    private void bteditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bteditActionPerformed

    }//GEN-LAST:event_bteditActionPerformed

    private void btupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btupdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btupdateActionPerformed

    private void btdelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btdelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btdelActionPerformed

    private void btexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btexitActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btexitActionPerformed

    private void btprintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btprintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btprintActionPerformed

    private void newSell(){
        Tools.disableButOpen(btPanel);
        Bee.fillComboRechargeType(comb_recharge_type);
        comb_recharge_type.setSelectedIndex(1);
        Bee.fillTable(jTable1);
        
        suppliers.FillComboAllNameSupplierByType(comb_to_account);
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
            java.util.logging.Logger.getLogger(BeeSellFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BeeSellFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BeeSellFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BeeSellFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BeeSellFrame(0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JPanel btPanel;
    private javax.swing.JButton btdel;
    private javax.swing.JButton btedit;
    private javax.swing.JButton btexit;
    private javax.swing.JButton btnew;
    private javax.swing.JButton btprint;
    private javax.swing.JButton btsave;
    private javax.swing.JButton btupdate;
    private javax.swing.JComboBox<String> comb_recharge_type;
    private javax.swing.JComboBox<String> comb_to_account;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_amount;
    private javax.swing.JTextField txt_note;
    private javax.swing.JLabel txt_title;
    private javax.swing.JTextField txt_value;
    // End of variables declaration//GEN-END:variables
}
