/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frams;

import Entity.ClientPerson;
import Entity.Send_receiveCash;
import Entity.VFCashClass;
import Utilities.Tools;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author mosta
 */
public class SendCashFrame extends javax.swing.JFrame {

    /**
     * Creates new form SendCashFrame
     */
    Dimension dim = new Dimension(715, 690);
    private Font font;
    private VFCashClass vf;
    private ClientPerson client;
    private double balance , cost , value , amount , discount_from_balance;
    Send_receiveCash send;// = new Send_receiveCash();
    public SendCashFrame() {
        initComponents();
        setSize(715, 690);
        Tools.setBackground(background, dim, "1230.jpg");
        font = Tools.font(16f);
        titel.setFont(font);
        vf = new VFCashClass();
        client = new ClientPerson();
        combVFNumber.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                String vfNumbe = combVFNumber.getSelectedItem().toString();
                balance = vf.getNowBalance(vfNumbe);
                txtBalance.setText(balance+"");
            }
        });
        client.FillComboAllNameClient(comboClient);
        setNewTransSend();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titel = new javax.swing.JLabel();
        Paneltxt = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        combVFNumber = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        txtBalance = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_valu = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_cost = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_discont = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        comboClient = new javax.swing.JComboBox();
        txt_amount = new javax.swing.JFormattedTextField();
        txtNumberClient = new javax.swing.JTextField();
        btPanel = new javax.swing.JPanel();
        btnew = new javax.swing.JButton();
        btsave = new javax.swing.JButton();
        btedit = new javax.swing.JButton();
        btupdate = new javax.swing.JButton();
        btdel = new javax.swing.JButton();
        btexit = new javax.swing.JButton();
        btprint = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        titel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titel.setText("ارسال كـــــــاش");
        getContentPane().add(titel);
        titel.setBounds(10, 11, 680, 60);

        Paneltxt.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Paneltxt.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("رقم المحفظة");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        combVFNumber.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("رصيد المحفظة");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtBalance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtBalance.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("المرسل اليه");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("القيمة");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_valu.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        txt_valu.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_valu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_valu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_valuKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("تكلفة الخدمة");
        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_cost.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        txt_cost.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_cost.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("خصم من الرصيد");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("المبلغ المدفوع");
        jLabel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_discont.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        txt_discont.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_discont.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_discont.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("العميل");
        jLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        comboClient.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txt_amount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_amount.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_amount.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        txtNumberClient.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtNumberClient.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout PaneltxtLayout = new javax.swing.GroupLayout(Paneltxt);
        Paneltxt.setLayout(PaneltxtLayout);
        PaneltxtLayout.setHorizontalGroup(
            PaneltxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PaneltxtLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(PaneltxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboClient, 0, 199, Short.MAX_VALUE)
                    .addComponent(txt_valu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(txtBalance, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(txt_discont, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PaneltxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PaneltxtLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(2, 2, 2))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PaneltxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(combVFNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cost, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumberClient, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PaneltxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                .addGap(11, 11, 11))
        );
        PaneltxtLayout.setVerticalGroup(
            PaneltxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PaneltxtLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PaneltxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combVFNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PaneltxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PaneltxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_valu)
                    .addComponent(txtNumberClient, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PaneltxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cost, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_discont, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PaneltxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PaneltxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboClient, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(Paneltxt);
        Paneltxt.setBounds(10, 77, 682, 184);

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
                .addGap(17, 17, 17)
                .addComponent(btexit, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btprint, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btdel, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btupdate, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btedit, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btsave, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnew, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                .addGap(17, 17, 17))
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
        btPanel.setBounds(10, 580, 680, 61);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "المبلغ", "القيمة", "المحفظة", "الوقت", "التاريخ", "م"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class, java.lang.Double.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

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
            jTable1.getColumnModel().getColumn(5).setResizable(false);
        }

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 266, 680, 310);

        background.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background.setToolTipText("");
        getContentPane().add(background);
        background.setBounds(0, 0, 0, 0);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnewActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnewActionPerformed

    private void btsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsaveActionPerformed
        // TODO add your handling code here:
        balance = Double.parseDouble(txtBalance.getText());
        discount_from_balance = Double.parseDouble(txt_discont.getText());
        if(discount_from_balance > balance){
            Tools.showErrorMsg("رصيد المحفظة لا يكفي");
        }else{
            
           amount = Double.parseDouble(txt_amount.getText());
           String Number_VF = combVFNumber.getSelectedItem().toString();
           String Number_client = txtNumberClient.getText();
           String name_client = comboClient.getSelectedItem().toString();
            try {
                send.SetDataSend(discount_from_balance, amount, Number_VF, Number_client, name_client);
                if(send.SaveSendTransaction()){
                    Tools.showInfoMsg("تم الاسال", "تحويل كاش");
                    setNewTransSend();
                }else{
                    Tools.showErrorMsg("خطأ في الارسال");       
                }}
            catch (SQLException ex) {
                Logger.getLogger(SendCashFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        
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

    private void txt_valuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_valuKeyReleased
        // TODO add your handling code here:
        value = Double.parseDouble(txt_valu.getText());
        String NumberClient = txtNumberClient.getText();
        String ClientNetwork = NumberClient.substring(0, 3);
        String NumberVF = combVFNumber.getSelectedItem().toString();
        String Network = NumberVF.substring(0, 3);
        if(ClientNetwork.equals(Network)){
            cost = 1;
        }else{
            cost = value * 0.005;
            if(cost > 10){
                cost = 10;
            }
        }
        txt_cost.setText(cost+"");
        discount_from_balance = value + cost;
        txt_discont.setText(discount_from_balance+"");
        amount = discount_from_balance + (discount_from_balance*0.010);
        DecimalFormat format = new DecimalFormat("0");
        String st_amount = format.format(amount);
        txt_amount.setText(st_amount);
        
    }//GEN-LAST:event_txt_valuKeyReleased

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
            java.util.logging.Logger.getLogger(SendCashFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SendCashFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SendCashFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SendCashFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SendCashFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Paneltxt;
    private javax.swing.JLabel background;
    private javax.swing.JPanel btPanel;
    private javax.swing.JButton btdel;
    private javax.swing.JButton btedit;
    private javax.swing.JButton btexit;
    private javax.swing.JButton btnew;
    private javax.swing.JButton btprint;
    private javax.swing.JButton btsave;
    private javax.swing.JButton btupdate;
    private javax.swing.JComboBox combVFNumber;
    private javax.swing.JComboBox comboClient;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel titel;
    private javax.swing.JLabel txtBalance;
    private javax.swing.JTextField txtNumberClient;
    private javax.swing.JFormattedTextField txt_amount;
    private javax.swing.JFormattedTextField txt_cost;
    private javax.swing.JFormattedTextField txt_discont;
    private javax.swing.JFormattedTextField txt_valu;
    // End of variables declaration//GEN-END:variables

    private void setNewTransSend(){
        send = new Send_receiveCash();
        vf.fillCombo(combVFNumber);
        balance = vf.getNowBalance(combVFNumber.getSelectedItem().toString());
        txtBalance.setText(balance+"");
        Tools.disableButOpen(btPanel);
        txtNumberClient.setText("");
        txt_valu.setText("");
        txt_discont.setText("");
        txt_cost.setText("");
        txt_amount.setText("");
        Tools.txtNumberClient(txt_valu,txtNumberClient, this ,680 ,222);
        send.fillTableSend(jTable1);
    }

}
