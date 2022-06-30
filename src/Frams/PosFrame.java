/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frams;

import Utilities.Tools;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

/**
 *
 * @author mosta
 */
public class PosFrame extends javax.swing.JFrame {

    /**
     * Creates new form PosFrame
     */
    Font font;
    Dimension dim = new Dimension(780, 690);
    public PosFrame() {
        initComponents();
        font = Tools.font(16f);
        
        setSize(dim);
        background.setLocation(0, 0);
        background.setSize(dim);
        background.setPreferredSize(dim);
        ImageIcon bg = new ImageIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/icons/17249.jpg")).getImage().getScaledInstance(dim.width, dim.height, Image.SCALE_DEFAULT));
        background.setIcon(bg);
        
        Btn_masary_pay.setFont(font);
        Btn_utilitesMasary.setFont(font);
        Btn_masary_sell.setFont(font);
        
        Btn_Bee_pay.setFont(font);
        Btn_Bee_sell.setFont(font);
        Btn_utilitesBee.setFont(font);
        
        Btn_Aman_pay.setFont(font);
        Btn_Aman_sell.setFont(font);
        Btn_utilitesAman.setFont(font);
        
        Btn_Fawry_pay.setFont(font);
        Btn_Fawry_sell.setFont(font);
        Btn_utilitesFawry.setFont(font);
        
        Btn_Opay_pay.setFont(font);
        Btn_Opay_sell.setFont(font);
        Btn_utilitesOpay.setFont(font);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Btn_utilitesMasary = new javax.swing.JButton();
        Btn_masary_pay = new javax.swing.JButton();
        Btn_masary_sell = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        Btn_utilitesBee = new javax.swing.JButton();
        Btn_Bee_pay = new javax.swing.JButton();
        Btn_Bee_sell = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        Btn_utilitesFawry = new javax.swing.JButton();
        Btn_Fawry_pay = new javax.swing.JButton();
        Btn_Fawry_sell = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        Btn_utilitesAman = new javax.swing.JButton();
        Btn_Aman_pay = new javax.swing.JButton();
        Btn_Aman_sell = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        Btn_utilitesOpay = new javax.swing.JButton();
        Btn_Opay_pay = new javax.swing.JButton();
        Btn_Opay_sell = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(220, 290));

        Btn_utilitesMasary.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/utility.png"))); // NOI18N
        Btn_utilitesMasary.setText("خدمات مصاري");
        Btn_utilitesMasary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_utilitesMasaryActionPerformed(evt);
            }
        });

        Btn_masary_pay.setForeground(new java.awt.Color(0, 0, 255));
        Btn_masary_pay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/pos32.png"))); // NOI18N
        Btn_masary_pay.setText("مبيعات مصاري");
        Btn_masary_pay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_masary_payActionPerformed(evt);
            }
        });

        Btn_masary_sell.setForeground(new java.awt.Color(255, 51, 102));
        Btn_masary_sell.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/posPay.png"))); // NOI18N
        Btn_masary_sell.setText("مشتريات مصاري");
        Btn_masary_sell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_masary_sellActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/masary-logo.png"))); // NOI18N
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setIconTextGap(1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addComponent(Btn_utilitesMasary, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Btn_masary_pay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Btn_masary_sell, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Btn_utilitesMasary, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Btn_masary_pay, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Btn_masary_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(510, 30, 230, 290);

        jPanel2.setOpaque(false);

        Btn_utilitesBee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/utility.png"))); // NOI18N
        Btn_utilitesBee.setText("Bee خدمات ");
        Btn_utilitesBee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_utilitesBeeActionPerformed(evt);
            }
        });

        Btn_Bee_pay.setForeground(new java.awt.Color(0, 0, 255));
        Btn_Bee_pay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/pos32.png"))); // NOI18N
        Btn_Bee_pay.setText("Bee مبيعات");
        Btn_Bee_pay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Bee_payActionPerformed(evt);
            }
        });

        Btn_Bee_sell.setForeground(new java.awt.Color(255, 51, 102));
        Btn_Bee_sell.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/posPay.png"))); // NOI18N
        Btn_Bee_sell.setText("Bee مشتريات");
        Btn_Bee_sell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Bee_sellActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/bee-logo.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Btn_utilitesBee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(Btn_Bee_pay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Btn_Bee_sell, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Btn_utilitesBee, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Btn_Bee_pay, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(Btn_Bee_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(30, 30, 230, 290);

        jPanel3.setOpaque(false);

        Btn_utilitesFawry.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/utility.png"))); // NOI18N
        Btn_utilitesFawry.setText(" خدمات فوري ");
        Btn_utilitesFawry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_utilitesFawryActionPerformed(evt);
            }
        });

        Btn_Fawry_pay.setForeground(new java.awt.Color(0, 0, 255));
        Btn_Fawry_pay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/pos32.png"))); // NOI18N
        Btn_Fawry_pay.setText(" مبيعات فوري");
        Btn_Fawry_pay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Fawry_payActionPerformed(evt);
            }
        });

        Btn_Fawry_sell.setForeground(new java.awt.Color(255, 51, 102));
        Btn_Fawry_sell.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/posPay.png"))); // NOI18N
        Btn_Fawry_sell.setText(" مشتريات فوري");
        Btn_Fawry_sell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Fawry_sellActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/fawrylogo.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Btn_Fawry_sell, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(Btn_utilitesFawry, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                        .addComponent(Btn_Fawry_pay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Btn_utilitesFawry, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Btn_Fawry_pay, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Btn_Fawry_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel3);
        jPanel3.setBounds(270, 350, 230, 280);

        jPanel4.setOpaque(false);

        Btn_utilitesAman.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/utility.png"))); // NOI18N
        Btn_utilitesAman.setText(" خدمات امان ");
        Btn_utilitesAman.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_utilitesAmanActionPerformed(evt);
            }
        });

        Btn_Aman_pay.setForeground(new java.awt.Color(0, 0, 255));
        Btn_Aman_pay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/pos32.png"))); // NOI18N
        Btn_Aman_pay.setText(" مبيعات امان");
        Btn_Aman_pay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Aman_payActionPerformed(evt);
            }
        });

        Btn_Aman_sell.setForeground(new java.awt.Color(255, 51, 102));
        Btn_Aman_sell.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/posPay.png"))); // NOI18N
        Btn_Aman_sell.setText(" مشتريات امان");
        Btn_Aman_sell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Aman_sellActionPerformed(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/aman-logo.png"))); // NOI18N
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(Btn_Aman_pay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Btn_utilitesAman, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Btn_Aman_sell, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
                .addGap(49, 49, 49))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Btn_utilitesAman, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Btn_Aman_pay, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Btn_Aman_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4);
        jPanel4.setBounds(510, 350, 230, 290);

        jPanel5.setOpaque(false);

        Btn_utilitesOpay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/utility.png"))); // NOI18N
        Btn_utilitesOpay.setText("OPay خدمات ");
        Btn_utilitesOpay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_utilitesOpayActionPerformed(evt);
            }
        });

        Btn_Opay_pay.setForeground(new java.awt.Color(0, 0, 255));
        Btn_Opay_pay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/pos32.png"))); // NOI18N
        Btn_Opay_pay.setText("OPay مبيعات");
        Btn_Opay_pay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Opay_payActionPerformed(evt);
            }
        });

        Btn_Opay_sell.setForeground(new java.awt.Color(255, 51, 102));
        Btn_Opay_sell.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/posPay.png"))); // NOI18N
        Btn_Opay_sell.setText("OPay مشتريات");
        Btn_Opay_sell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Opay_sellActionPerformed(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/opay_logo.png"))); // NOI18N
        jLabel5.setPreferredSize(new java.awt.Dimension(200, 100));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Btn_utilitesOpay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(Btn_Opay_pay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Btn_Opay_sell, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Btn_utilitesOpay, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Btn_Opay_pay, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(Btn_Opay_sell, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel5);
        jPanel5.setBounds(270, 30, 230, 290);
        getContentPane().add(background);
        background.setBounds(0, 0, 0, 0);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_utilitesMasaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_utilitesMasaryActionPerformed
        // TODO add your handling code here:
        UtilitiesMasary u = new UtilitiesMasary(1);
        Tools.openJFram(u , "خدمات مصاري");
        dispose();
    }//GEN-LAST:event_Btn_utilitesMasaryActionPerformed

    private void Btn_masary_payActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_masary_payActionPerformed
        // TODO add your handling code here:
        MasarypayFrame mf = new MasarypayFrame();
        Tools.openJFram(mf , "مبيعات مصاري");
        dispose();
    }//GEN-LAST:event_Btn_masary_payActionPerformed

    private void Btn_masary_sellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_masary_sellActionPerformed
        // TODO add your handling code here:
        MasarySellFrame msf = new MasarySellFrame(4);
        Tools.openJFram(msf, "مشتريات مصاري");
    }//GEN-LAST:event_Btn_masary_sellActionPerformed

    private void Btn_utilitesBeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_utilitesBeeActionPerformed
        // TODO add your handling code here:
        UtilitiesMasary uB = new UtilitiesMasary(2);
        Tools.openJFram(uB, "Bee خدمات");
        dispose();
        
    }//GEN-LAST:event_Btn_utilitesBeeActionPerformed

    private void Btn_Bee_payActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Bee_payActionPerformed
        // TODO add your handling code here:
        BeepayFrame bpay = new BeepayFrame();
        Tools.openJFram(bpay, "Bee");
        dispose();
    }//GEN-LAST:event_Btn_Bee_payActionPerformed

    private void Btn_Bee_sellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Bee_sellActionPerformed
        // TODO add your handling code here:
        BeeSellFrame bsf = new BeeSellFrame(5);
        Tools.openJFram(bsf, "  Bee مشتريات");
        dispose();
    }//GEN-LAST:event_Btn_Bee_sellActionPerformed

    private void Btn_utilitesFawryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_utilitesFawryActionPerformed
        // TODO add your handling code here:
        UtilitiesMasary uB = new UtilitiesMasary(3);
        Tools.openJFram(uB, "Fawry خدمات");
        dispose();
    }//GEN-LAST:event_Btn_utilitesFawryActionPerformed

    private void Btn_Fawry_payActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Fawry_payActionPerformed
        // TODO add your handling code here:
        FawrypayFrame fpf = new FawrypayFrame();
        Tools.openJFram(fpf, "Fawry مبيعات");
        dispose();
    }//GEN-LAST:event_Btn_Fawry_payActionPerformed

    private void Btn_Fawry_sellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Fawry_sellActionPerformed
        // TODO add your handling code here:
        FawrySellFrame fsf = new FawrySellFrame(3);
        Tools.openJFram(fsf, "مشتريات فوري");
        dispose();
    }//GEN-LAST:event_Btn_Fawry_sellActionPerformed

    private void Btn_utilitesAmanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_utilitesAmanActionPerformed
        // TODO add your handling code here:
        UtilitiesMasary uB = new UtilitiesMasary(5);
        Tools.openJFram(uB, "Fawry خدمات");
        dispose();
    }//GEN-LAST:event_Btn_utilitesAmanActionPerformed

    private void Btn_Aman_payActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Aman_payActionPerformed
        // TODO add your handling code here:
        AmanpayFrame aman = new AmanpayFrame();
        Tools.openJFram(aman, "Aman مبيعات");
        dispose();
    }//GEN-LAST:event_Btn_Aman_payActionPerformed

    private void Btn_Aman_sellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Aman_sellActionPerformed
        // TODO add your handling code here:
        AmanSellFrame aman = new AmanSellFrame(2);
        Tools.openJFram(aman, "مشتريات أمان");
        dispose();       
    }//GEN-LAST:event_Btn_Aman_sellActionPerformed

    private void Btn_utilitesOpayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_utilitesOpayActionPerformed
        // TODO add your handling code here:
        UtilitiesMasary uB = new UtilitiesMasary(4);
        Tools.openJFram(uB, "OPay خدمات");
        dispose();
    }//GEN-LAST:event_Btn_utilitesOpayActionPerformed

    private void Btn_Opay_payActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Opay_payActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Btn_Opay_payActionPerformed

    private void Btn_Opay_sellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Opay_sellActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Btn_Opay_sellActionPerformed

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
            java.util.logging.Logger.getLogger(PosFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PosFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PosFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PosFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PosFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Aman_pay;
    private javax.swing.JButton Btn_Aman_sell;
    private javax.swing.JButton Btn_Bee_pay;
    private javax.swing.JButton Btn_Bee_sell;
    private javax.swing.JButton Btn_Fawry_pay;
    private javax.swing.JButton Btn_Fawry_sell;
    private javax.swing.JButton Btn_Opay_pay;
    private javax.swing.JButton Btn_Opay_sell;
    private javax.swing.JButton Btn_masary_pay;
    private javax.swing.JButton Btn_masary_sell;
    private javax.swing.JButton Btn_utilitesAman;
    private javax.swing.JButton Btn_utilitesBee;
    private javax.swing.JButton Btn_utilitesFawry;
    private javax.swing.JButton Btn_utilitesMasary;
    private javax.swing.JButton Btn_utilitesOpay;
    private javax.swing.JLabel background;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    // End of variables declaration//GEN-END:variables
}
