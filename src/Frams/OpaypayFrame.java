/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frams;

import Entity.Amanpay;
import Entity.Beepay;
import Entity.ClientPerson;
import Entity.Fawrypay;
import Entity.MasaryPay;
import Entity.OPaypay;
import Entity.masary_Utilites;
import Utilities.Tools;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxEditor;

/**
 *
 * @author mosta
 */
public class OpaypayFrame extends javax.swing.JFrame {

    /**
     * Creates new form Masary
     */
    Font font;
    ClientPerson client;
    masary_Utilites Bee_util;
    OPaypay opay;
     private final int id_pos = 4;
    private double costService;
    private boolean is_requier_phone_number = false;
    private final DecimalFormat decf;
    Dimension dim = new Dimension(870, 530);
    public OpaypayFrame() {
        initComponents();
        comUtility.addActionListener((ActionEvent e) -> {
            if(comUtility.getSelectedItem().equals("فودافون كاش")){
                  this.dispose();
                  Tools.openJFram(new chargingWalletFrame(id_pos), "شحن محفظة");
            }
        });
        setSize(dim);
        Tools.setBackground(background, dim, "5630974.jpg");
        decf = new DecimalFormat("0.00");
        font = Tools.font(24f);
        title.setFont(font);
        Bee_util = new masary_Utilites(id_pos);
        client = new ClientPerson();
        opay  = new OPaypay();
        newMasaray();
    }

    private void newMasaray(){
        Tools.disableButOpen(btPanel);
        Tools.txtNumberClient(txtAmount,txtPhone, this, 275, 290);
        client.FillComboAllNameClient(combClient);
        Bee_util.fillComboCategoryUtilites(comUtility);
        String nameUtility = comUtility.getSelectedItem().toString();
        Bee_util.fillComboUtilites(combnote , nameUtility);
        String note = combnote.getSelectedItem().toString();
        String balance = String.valueOf(opay.getfirstBalance());
        is_requier_phone_number = Bee_util.isRequierPhoneNumber(nameUtility);
        lb_phone.setEnabled(is_requier_phone_number);
        txtPhone.setEnabled(is_requier_phone_number);
        txtbalance.setText(balance);
        opay.fillTable(jTable1);
        String price = Bee_util.getPriceByNote(note)+"";
        txtAmount.setText(price);
        txtCount.setText("1");
        txtPhone.setText("");
        txtdiscount.setText(String.valueOf(Bee_util.getCost(note)));
        txtAmount.requestFocus();
        txtAmount.selectAll();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtPanal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        comUtility = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCount = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        combClient = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtdiscount = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtbalance = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        combnote = new javax.swing.JComboBox<>();
        lb_phone = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        title = new javax.swing.JLabel();
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

        txtPanal.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtPanal.setOpaque(false);
        txtPanal.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("الخدمة");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtPanal.add(jLabel1);
        jLabel1.setBounds(725, 13, 100, 35);

        comUtility.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comUtility.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comUtilityItemStateChanged(evt);
            }
        });
        txtPanal.add(comUtility);
        comUtility.setBounds(560, 13, 160, 35);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("بيان");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtPanal.add(jLabel2);
        jLabel2.setBounds(420, 13, 130, 35);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("القيمة");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtPanal.add(jLabel3);
        jLabel3.setBounds(141, 13, 95, 35);

        txtCount.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtCount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCount.setText("1");
        txtCount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCountKeyReleased(evt);
            }
        });
        txtPanal.add(txtCount);
        txtCount.setBounds(12, 13, 123, 35);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("العميل");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtPanal.add(jLabel4);
        jLabel4.setBounds(725, 55, 100, 35);

        combClient.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        txtPanal.add(combClient);
        combClient.setBounds(560, 55, 160, 35);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("المخصوم من الرصيد");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtPanal.add(jLabel5);
        jLabel5.setBounds(420, 95, 130, 35);

        txtdiscount.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtdiscount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPanal.add(txtdiscount);
        txtdiscount.setBounds(240, 95, 175, 35);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("الرصيد السابق");
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtPanal.add(jLabel6);
        jLabel6.setBounds(141, 95, 95, 35);

        txtbalance.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtbalance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtbalance.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtPanal.add(txtbalance);
        txtbalance.setBounds(12, 95, 123, 35);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("المبلغ المدفوع");
        jLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtPanal.add(jLabel8);
        jLabel8.setBounds(725, 95, 100, 35);

        txtAmount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtAmount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAmount.setText("0.00");
        txtPanal.add(txtAmount);
        txtAmount.setBounds(560, 95, 160, 35);

        combnote.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        combnote.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combnoteItemStateChanged(evt);
            }
        });
        txtPanal.add(combnote);
        combnote.setBounds(240, 13, 175, 35);

        lb_phone.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb_phone.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_phone.setText("رقم العميل");
        lb_phone.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtPanal.add(lb_phone);
        lb_phone.setBounds(420, 55, 130, 35);

        txtPhone.setFont(new java.awt.Font("Lucida Calligraphy", 1, 12)); // NOI18N
        txtPhone.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPanal.add(txtPhone);
        txtPhone.setBounds(12, 55, 402, 35);

        getContentPane().add(txtPanal);
        txtPanal.setBounds(10, 67, 830, 139);

        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("مبيعات  OPay");
        getContentPane().add(title);
        title.setBounds(10, 11, 830, 50);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "الرصيد", "المدفوع", "م الرصيد", "العميل", "القيمة", "البيان", "الخدمة", "الوقت", "التاريخ", "م"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 212, 830, 199);

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
        btPanel.setBounds(10, 422, 830, 61);

        background.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(background);
        background.setBounds(0, 0, 0, 0);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnewActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnewActionPerformed

    private void btsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsaveActionPerformed
        double price_masary_pay = 0 , discount_of_balance = 0, amount_masary_pay = 0 ;
        int id_client = 1;
        boolean isSave = false;
        String not_utilitiy = combnote.getSelectedItem().toString();
        try{
            price_masary_pay = Double.parseDouble(txtCount.getText());
            discount_of_balance = Double.parseDouble(txtdiscount.getText());
            amount_masary_pay = Double.parseDouble(txtAmount.getText());
            String client_s = new ClientPerson().getIdByName(combClient.getSelectedItem().toString());
            id_client = Integer.parseInt(client_s);
        }catch(NumberFormatException ex){  
            Logger.getLogger("Masary").log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OpaypayFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        opay.setAmount_masary_pay(amount_masary_pay);
        opay.setDiscount_of_balance(discount_of_balance);
        opay.setPrice_masary_pay(price_masary_pay);
        opay.setId_client(id_client);
        if(txtPhone.isEnabled()){
            opay.setPhone(txtPhone.getText());
            opay.setUtility_masary(txtPhone.getText());
            opay.setId_utility_masary(not_utilitiy);
        }
        else{
            opay.setPhone(null);
            opay.setId_utility_masary(not_utilitiy);
            opay.setUtility_masary(not_utilitiy);
        }
        if(id_client == 1){
            isSave = opay.SaveCasher();
        }else{
            isSave = opay.SaveClients();
        }
                    
        if(isSave){
            Tools.showInfoMsg("تم الحفظ", "حفظ");
            newMasaray();
        }
        else{
            Tools.showErrorMsg("خطأ");
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

    private void comUtilityItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comUtilityItemStateChanged
        // TODO add your handling code here:
        String nameCategoray = comUtility.getSelectedItem().toString();
        Bee_util.fillComboUtilites(combnote, nameCategoray);
        String note = combnote.getSelectedItem().toString();
        String price = Bee_util.getPriceByNote(note)+"";
        String disc = decf.format(Bee_util.getCost(note));
        txtdiscount.setText(disc);
        txtAmount.setText(price);
        txtAmount.requestFocus();
        txtAmount.selectAll();
        is_requier_phone_number = Bee_util.isRequierPhoneNumber(nameCategoray);
        lb_phone.setEnabled(is_requier_phone_number);
        txtPhone.setEnabled(is_requier_phone_number);
    }//GEN-LAST:event_comUtilityItemStateChanged

    private void txtCountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCountKeyReleased
        try{
            double val = Double.parseDouble(txtCount.getText());
            String note = combnote.getSelectedItem().toString();
            double CostOfService = Bee_util.getCost(note);
            double discount = CostOfService * val;
            double price = Bee_util.getPriceByNote(note) * val;
            txtAmount.setText(price+"");
            txtdiscount.setText(decf.format(discount));
        }catch(NumberFormatException ex){
        
        }
    }//GEN-LAST:event_txtCountKeyReleased

    private void combnoteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combnoteItemStateChanged
        // TODO add your handling code here:
        String note = combnote.getSelectedItem().toString() ;
        String price = Bee_util.getPriceByNote(note)+"";
        txtdiscount.setText(String.valueOf(Bee_util.getCost(note)));
        txtAmount.setText(price);
        txtAmount.requestFocus();
        txtAmount.selectAll();
    }//GEN-LAST:event_combnoteItemStateChanged

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
            java.util.logging.Logger.getLogger(OpaypayFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OpaypayFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OpaypayFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OpaypayFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OpaypayFrame().setVisible(true);
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
    private javax.swing.JComboBox<String> comUtility;
    private javax.swing.JComboBox<String> combClient;
    private javax.swing.JComboBox<String> combnote;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lb_phone;
    private javax.swing.JLabel title;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtCount;
    private javax.swing.JPanel txtPanal;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JLabel txtbalance;
    private javax.swing.JTextField txtdiscount;
    // End of variables declaration//GEN-END:variables
}
