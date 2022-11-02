/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frams;

import Entity.*;
import Utilities.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JWindow;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;



/**
 *
 * @author mosta
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private final Font font = Tools.font(14f);
    private Thread thread;
    private double cost , price;
    private int id_pos = 1;
    private final  JWindow winCahse , winFastCashSend , windeficiencyExcess;
    private calcFrame cf = new calcFrame();
    private CounterFrame cuf = new CounterFrame(); 
    private FastCashSendPanel c;
    //private final Timer timer;
    // set Jframe variable 
    private  ClintFrame cnt ;
    private  itemsFrame itemsFrame ;
    private  ReciptFrame reciptF ;
    private  SuppliersFrame SuppliersF;
    private  PaymentReciptFrame payRecipt ;
    private  purchaseInvoiceFrame purchfrm ;
    private   RebortsFrame report;
    private DayFrame d = new DayFrame();
    public MainFrame() {
        initComponents();
        background.setLocation(0, 0);
        background.setSize(dim);
        background.setPreferredSize(dim);
        ImageIcon bg = new ImageIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/icons/bg4.jpg")).getImage().getScaledInstance(dim.width, dim.height, Image.SCALE_DEFAULT));
        background.setIcon(bg);
        int x = dim.width - 140;
        client.setLocation(x , 40);
        client.setFont(font);
        items.setLocation(x , 180);
        items.setFont(font);
        suppliers.setLocation(x  , 320);
        suppliers.setFont(font);
        x = x - 140;
        Purchases.setLocation(x, 40);
        Purchases.setFont(font);
        sales.setLocation(x, 180);
        sales.setFont(font);
        Reports.setLocation(x, 320);
        Reports.setFont(font);
        x = x - 140;
        Payments.setLocation(x, 40);
        Payments.setFont(font);
        Receipts.setLocation(x, 180);
        Receipts.setFont(font);
        pos.setLocation(x, 320);
        pos.setFont(font);
        x = x-140;
        fees.setLocation(x, 40);
        fees.setFont(font);
        loan.setLocation(x, 180);
        loan.setFont(font);
        VCash.setLocation(x, 320);
        VCash.setFont(font);
        x = x -140;
        goold.setLocation(x, 40);
        goold.setFont(font);
        selePanal.setLocation(10, 40);
        txtNowBalance.setBounds(10, dim.height - 180 , 300, 120);
      //  txtNowBalance.set
        txtIdItems.setFocusable(true);
        txtNowBalance.setText(new CasherClass().getNowBalanceCasher());
        txtMasaryBalance.setText(new MasaryPay().getfirstBalance()+"");
        new masary_Utilites(1).fillComboUtilites(comboNoteUtiltiy, "شحن");
        comboNoteUtiltiy.addItem("اختر نوع الشحن");
        comboNoteUtiltiy.setSelectedItem("اختر نوع الشحن");
        settxtnumer();
        txtvalue.setText("0");
        txtIdItems.requestFocus();
        Tools.txtNumberClient(txtIdItems,txtNmber, this, 0, 0);
        winFastCashSend = new JWindow(this);
        winCahse = new JWindow(this);
        windeficiencyExcess = new JWindow(this);
        String info = "User Name : "+saveData.getUserName();
        int x_info = -2;
        int y_info = txtNowBalance.getY() + txtNowBalance.getHeight()+5;
        int width_info = dim.width - x_info ;
        Barpanel.setBounds(x_info, y_info, width_info  , 30);
        Barpanel.setBorder(BorderFactory.createLoweredBevelBorder());
        Barpanel.setOpaque(false);
        txtinfoUser.setLocation(10, 0);
        txtTimer.setLocation(Barpanel.getWidth() / 2, 0);
        int x_timer = 300 ;
        txtTimer.setBounds(x_timer, 0, 160, 30);
        x_timer += 200;
        txtDate.setBounds(x_timer, 0, 200, 30);
        txtinfoUser.setText(info);
        //NotifcationClass notifcationClass = new NotifcationClass(this ,Butt_notification);
        //timer = new Timer();
        //timer.schedule(notifcationClass, 3000, 3000);
        clockTime();
        txtinformationPos.setBounds(Barpanel.getWidth() - 310 , 0, 300, 30);
        informationPosAndCasher();
    };
    
  private void clockTime(){
        Timer timeclock = new Timer();
        timeclock.schedule(new TimerTask() {
            @Override
            public void run() {
                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
                SimpleDateFormat ss = new SimpleDateFormat("SS");
                SimpleDateFormat sd = new SimpleDateFormat("YYYY-MM-dd");
                String timenow = sdf.format(d);
                String datenow = sd.format(d);
                int x_user = Integer.parseInt(ss.format(d)) / 100;
                txtTimer.setText(timenow);
                txtDate.setText(datenow);
                txtinfoUser.setLocation(x_user, x_user);
            }
        }, 10,10);
    }
    
  private void informationPosAndCasher(){
        
        Timer info = new Timer();
        info.schedule(new TimerTask() {
            int idPos = 0;
            String infoPos;
            @Override
            public void run() {
                getinfo();
            }
            
            private void getinfo(){
                switch (idPos) {
                    case 6:
                        infoPos = "الرصيد في الجرد الاول : "+new CasherClass().getNowBalanceCasher();
                        txtinformationPos.setText(infoPos);
                        txtinformationPos.setIcon(new ImageIcon(getClass().getResource("/icons/cashier.png")));
                        idPos = 0;
                        break;
                    case 0:
                        infoPos = "الرصيد في جرد الثاني = " + new CasherClass().getBalanceCasherTwo();
                        txtinformationPos.setText(infoPos);
                        txtinformationPos.setIcon(new ImageIcon(getClass().getResource("/icons/safe-box.png")));
                        idPos = 1;
                        break;
                    default:
                        infoPos = "الرصيد في ماكينة  ";
                        infoPos += ConnectDB.getIdFromName("SELECT name_pos As id FROM pos WHERE id_pos = " + idPos);
                        infoPos += " = "+new posPay(idPos) {}.getfirstBalance() + "";
                        txtinformationPos.setText(infoPos);
                        txtinformationPos.setIcon(new ImageIcon(getClass().getResource("/icons/pos24.png")));
                        idPos++;
                        break;
                }


            }
            
        }, 1000,4000); 
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
        selePanal = new javax.swing.JPanel();
        txtIdItems = new javax.swing.JTextField();
        txtnameItem = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtQut = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        pos = new javax.swing.JLabel();
        fees = new javax.swing.JLabel();
        loan = new javax.swing.JLabel();
        VCash = new javax.swing.JLabel();
        calc = new javax.swing.JLabel();
        counter = new javax.swing.JLabel();
        card_10 = new javax.swing.JLabel();
        txtMasaryBalance = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();
        comboNoteUtiltiy = new javax.swing.JComboBox();
        txtvalue = new javax.swing.JFormattedTextField();
        txtAmount = new javax.swing.JFormattedTextField();
        txt_discount = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        vf_cash = new javax.swing.JLabel();
        txtNmber = new javax.swing.JTextField();
        Butt_notification = new javax.swing.JLabel();
        Barpanel = new javax.swing.JPanel();
        txtinfoUser = new javax.swing.JLabel();
        txtTimer = new javax.swing.JLabel();
        txtinformationPos = new javax.swing.JLabel();
        txtDate = new javax.swing.JLabel();
        btn_balance = new javax.swing.JLabel();
        bt_deficiency_excess = new javax.swing.JLabel();
        btnRturn = new javax.swing.JLabel();
        btnItemsBalance = new javax.swing.JLabel();
        btnScann = new javax.swing.JLabel();
        txtNowBalance = new javax.swing.JLabel();
        goold = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(null);

        Receipts.setFont(new java.awt.Font("VIP Hala Bold", 1, 18)); // NOI18N
        Receipts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Receipts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/cash-register.png"))); // NOI18N
        Receipts.setText("تحصيلات");
        Receipts.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Receipts.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        Receipts.setBounds(760, 200, 130, 130);

        client.setFont(new java.awt.Font("VIP Hala Bold", 1, 18)); // NOI18N
        client.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        client.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/clients_64.png"))); // NOI18N
        client.setText("العملاء");
        client.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        client.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        client.setBounds(1100, 30, 130, 130);

        items.setFont(new java.awt.Font("VIP Hala Bold", 1, 18)); // NOI18N
        items.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        items.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/items_64.png"))); // NOI18N
        items.setText("الاصناف");
        items.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        items.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        items.setBounds(1100, 200, 130, 130);

        suppliers.setFont(new java.awt.Font("VIP Hala Bold", 1, 18)); // NOI18N
        suppliers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        suppliers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/delivery-truck.png"))); // NOI18N
        suppliers.setText("الموردين");
        suppliers.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        suppliers.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        suppliers.setBounds(1100, 370, 130, 130);

        Payments.setFont(new java.awt.Font("VIP Hala Bold", 1, 18)); // NOI18N
        Payments.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Payments.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/bank.png"))); // NOI18N
        Payments.setText("مدفوعات");
        Payments.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Payments.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        Payments.setBounds(760, 30, 130, 130);

        Purchases.setFont(new java.awt.Font("VIP Hala Bold", 1, 18)); // NOI18N
        Purchases.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Purchases.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/cross-selling.png"))); // NOI18N
        Purchases.setText("مشتريات");
        Purchases.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Purchases.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        Purchases.setBounds(930, 30, 130, 130);

        Reports.setFont(new java.awt.Font("VIP Hala Bold", 1, 18)); // NOI18N
        Reports.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Reports.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/business-report_1.png"))); // NOI18N
        Reports.setText("تقارير");
        Reports.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Reports.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        Reports.setBounds(930, 370, 130, 130);

        sales.setFont(new java.awt.Font("VIP Hala Bold", 1, 18)); // NOI18N
        sales.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/shopping-cart.png"))); // NOI18N
        sales.setText("مبيعات");
        sales.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        sales.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        sales.setBounds(930, 200, 130, 130);

        selePanal.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        selePanal.setOpaque(false);

        txtIdItems.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        txtIdItems.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdItems.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtIdItems.setFocusTraversalPolicyProvider(true);
        txtIdItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdItemsActionPerformed(evt);
            }
        });
        txtIdItems.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdItemsKeyReleased(evt);
            }
        });

        txtnameItem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtnameItem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtnameItem.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("الكمية");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtQut.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtQut.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("السعر");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtPrice.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtPrice.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnSave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/diskette (1).png"))); // NOI18N
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/recycle (2).png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout selePanalLayout = new javax.swing.GroupLayout(selePanal);
        selePanal.setLayout(selePanalLayout);
        selePanalLayout.setHorizontalGroup(
            selePanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selePanalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(selePanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIdItems)
                    .addComponent(txtnameItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, selePanalLayout.createSequentialGroup()
                        .addComponent(txtQut)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(selePanalLayout.createSequentialGroup()
                        .addComponent(txtPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(selePanalLayout.createSequentialGroup()
                        .addComponent(btnSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        selePanalLayout.setVerticalGroup(
            selePanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selePanalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtIdItems, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnameItem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(selePanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtQut, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(selePanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(selePanalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(selePanal);
        selePanal.setBounds(10, 30, 230, 230);

        pos.setFont(new java.awt.Font("VIP Hala Bold", 1, 18)); // NOI18N
        pos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/pos.png"))); // NOI18N
        pos.setText("مكن الرصيد");
        pos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        pos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                posMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                posMouseExited(evt);
            }
        });
        getContentPane().add(pos);
        pos.setBounds(760, 370, 130, 130);

        fees.setFont(new java.awt.Font("VIP Hala Bold", 1, 18)); // NOI18N
        fees.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fees.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/expense_64.png"))); // NOI18N
        fees.setText("المصروفات");
        fees.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        fees.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        fees.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fees.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        fees.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                feesMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                feesMouseExited(evt);
            }
        });
        getContentPane().add(fees);
        fees.setBounds(600, 30, 130, 130);

        loan.setFont(new java.awt.Font("VIP Hala Bold", 1, 18)); // NOI18N
        loan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/loan.png"))); // NOI18N
        loan.setText("سلف و عهد");
        loan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        loan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        loan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        loan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loanMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loanMouseExited(evt);
            }
        });
        getContentPane().add(loan);
        loan.setBounds(600, 200, 130, 130);

        VCash.setFont(new java.awt.Font("VIP Hala Bold", 1, 18)); // NOI18N
        VCash.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        VCash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/vf-cash.png"))); // NOI18N
        VCash.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        VCash.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        VCash.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        VCash.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        VCash.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VCashMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                VCashMouseExited(evt);
            }
        });
        getContentPane().add(VCash);
        VCash.setBounds(600, 370, 130, 130);

        calc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        calc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/calculator_1.png"))); // NOI18N
        calc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                calcMouseClicked(evt);
            }
        });
        getContentPane().add(calc);
        calc.setBounds(325, 600, 64, 64);

        counter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        counter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/counter2.png"))); // NOI18N
        counter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                counterMouseClicked(evt);
            }
        });
        counter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                counterKeyPressed(evt);
            }
        });
        getContentPane().add(counter);
        counter.setBounds(400, 600, 64, 64);

        card_10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        card_10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/card10.png"))); // NOI18N
        card_10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        card_10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card_10MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                card_10MouseExited(evt);
            }
        });
        getContentPane().add(card_10);
        card_10.setBounds(10, 425, 70, 70);

        txtMasaryBalance.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtMasaryBalance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtMasaryBalance.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtMasaryBalance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMasaryBalanceMouseClicked(evt);
            }
        });
        getContentPane().add(txtMasaryBalance);
        txtMasaryBalance.setBounds(10, 350, 205, 70);

        logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/masary-logo.png"))); // NOI18N
        logo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        logo.setPreferredSize(new java.awt.Dimension(200, 70));
        getContentPane().add(logo);
        logo.setBounds(10, 275, 200, 70);

        comboNoteUtiltiy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboNoteUtiltiy.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboNoteUtiltiyItemStateChanged(evt);
            }
        });
        getContentPane().add(comboNoteUtiltiy);
        comboNoteUtiltiy.setBounds(90, 425, 125, 32);

        txtvalue.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtvalue.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtvalue.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtvalue.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtvalue.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtvalueKeyReleased(evt);
            }
        });
        getContentPane().add(txtvalue);
        txtvalue.setBounds(115, 500, 100, 35);

        txtAmount.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtAmount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAmount.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtAmount.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(txtAmount);
        txtAmount.setBounds(115, 540, 100, 35);

        txt_discount.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_discount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_discount.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(txt_discount);
        txt_discount.setBounds(10, 500, 100, 35);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/save.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(10, 540, 100, 35);

        vf_cash.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vf_cash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/vf-cash.png"))); // NOI18N
        vf_cash.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vf_cashMouseClicked(evt);
            }
        });
        getContentPane().add(vf_cash);
        vf_cash.setBounds(470, 600, 64, 64);

        txtNmber.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtNmber.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNmber.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtNmber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNmberKeyPressed(evt);
            }
        });
        getContentPane().add(txtNmber);
        txtNmber.setBounds(90, 460, 125, 35);

        Butt_notification.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Butt_notification.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/bell.png"))); // NOI18N
        getContentPane().add(Butt_notification);
        Butt_notification.setBounds(540, 600, 64, 64);

        Barpanel.setLayout(null);

        txtinfoUser.setFont(new java.awt.Font("VIP Hala Bold", 0, 14)); // NOI18N
        txtinfoUser.setForeground(new java.awt.Color(0, 0, 255));
        txtinfoUser.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtinfoUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/account.png"))); // NOI18N
        txtinfoUser.setIconTextGap(10);
        Barpanel.add(txtinfoUser);
        txtinfoUser.setBounds(10, 0, 240, 30);

        txtTimer.setFont(new java.awt.Font("Felix Titling", 1, 18)); // NOI18N
        txtTimer.setForeground(new java.awt.Color(0, 51, 255));
        txtTimer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTimer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/clock.png"))); // NOI18N
        txtTimer.setText("00:00:00");
        txtTimer.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        txtTimer.setIconTextGap(20);
        Barpanel.add(txtTimer);
        txtTimer.setBounds(390, 0, 130, 30);

        txtinformationPos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtinformationPos.setForeground(new java.awt.Color(0, 51, 255));
        txtinformationPos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtinformationPos.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        txtinformationPos.setIconTextGap(10);
        Barpanel.add(txtinformationPos);
        txtinformationPos.setBounds(1020, 0, 250, 30);

        txtDate.setFont(new java.awt.Font("Felix Titling", 1, 18)); // NOI18N
        txtDate.setForeground(new java.awt.Color(0, 51, 255));
        txtDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtDate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/calendar1.png"))); // NOI18N
        txtDate.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        txtDate.setIconTextGap(20);
        Barpanel.add(txtDate);
        txtDate.setBounds(560, 0, 250, 30);

        getContentPane().add(Barpanel);
        Barpanel.setBounds(-5, 760, 1270, 30);

        btn_balance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_balance.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/balance.png"))); // NOI18N
        btn_balance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_balanceMouseClicked(evt);
            }
        });
        getContentPane().add(btn_balance);
        btn_balance.setBounds(610, 600, 64, 64);

        bt_deficiency_excess.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bt_deficiency_excess.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/bar-graph.png"))); // NOI18N
        bt_deficiency_excess.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_deficiency_excessMouseClicked(evt);
            }
        });
        bt_deficiency_excess.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bt_deficiency_excessKeyPressed(evt);
            }
        });
        getContentPane().add(bt_deficiency_excess);
        bt_deficiency_excess.setBounds(680, 600, 64, 64);

        btnRturn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnRturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/packa.png"))); // NOI18N
        btnRturn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRturnMouseClicked(evt);
            }
        });
        getContentPane().add(btnRturn);
        btnRturn.setBounds(750, 600, 64, 64);

        btnItemsBalance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnItemsBalance.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/search_1.png"))); // NOI18N
        btnItemsBalance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnItemsBalanceMouseClicked(evt);
            }
        });
        getContentPane().add(btnItemsBalance);
        btnItemsBalance.setBounds(820, 600, 64, 64);

        btnScann.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnScann.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/scanner.png"))); // NOI18N
        btnScann.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnScannMouseClicked(evt);
            }
        });
        getContentPane().add(btnScann);
        btnScann.setBounds(890, 600, 64, 64);

        txtNowBalance.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        txtNowBalance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNowBalance.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/safe-box.png"))); // NOI18N
        txtNowBalance.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtNowBalance.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        txtNowBalance.setIconTextGap(10);
        txtNowBalance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNowBalanceMouseClicked(evt);
            }
        });
        getContentPane().add(txtNowBalance);
        txtNowBalance.setBounds(20, 600, 230, 90);

        goold.setFont(new java.awt.Font("VIP Hala Bold", 1, 18)); // NOI18N
        goold.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        goold.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/gold-bars.png"))); // NOI18N
        goold.setText("الجبل");
        goold.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        goold.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        goold.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        goold.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        goold.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gooldMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                gooldMouseExited(evt);
            }
        });
        getContentPane().add(goold);
        goold.setBounds(440, 30, 130, 130);

        background.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        background.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(background);
        background.setBounds(0, 0, 0, 0);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientMouseClicked
        clickPanel(client);
        cnt = new ClintFrame();
        Tools.openJFram(cnt, "العملاء" , "consumer");
    }//GEN-LAST:event_clientMouseClicked

    private void clientMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientMouseExited
        exitPanel(client);
    }//GEN-LAST:event_clientMouseExited

    private void itemsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemsMouseClicked
        clickPanel(items);
        itemsFrame = new itemsFrame();
        Tools.openJFram(itemsFrame, "الاصناف" , "trolley");
    }//GEN-LAST:event_itemsMouseClicked

    private void itemsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemsMouseExited
         exitPanel(items);
    }//GEN-LAST:event_itemsMouseExited

    private void ReceiptsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReceiptsMouseClicked
        try {
            clickPanel(Receipts);
            reciptF = new ReciptFrame();
            Tools.openJFram(reciptF, "استلام نقدية","receipt");
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ReceiptsMouseClicked

    private void ReceiptsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReceiptsMouseExited
        exitPanel(Receipts);
    }//GEN-LAST:event_ReceiptsMouseExited

    private void suppliersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_suppliersMouseClicked
        clickPanel(suppliers);
        SuppliersF = new SuppliersFrame();
        Tools.openJFram(SuppliersF, "الموردين","boss");
    }//GEN-LAST:event_suppliersMouseClicked

    private void suppliersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_suppliersMouseExited

        exitPanel(suppliers);
    }//GEN-LAST:event_suppliersMouseExited

    private void PaymentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PaymentsMouseClicked
         clickPanel(Payments);
         payRecipt = new PaymentReciptFrame();
         Tools.openJFram(payRecipt, "ايصال دفع نقدية","receipt_1");
    }//GEN-LAST:event_PaymentsMouseClicked

    private void PaymentsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PaymentsMouseExited
        // TODO add your handling code here:
        exitPanel(Payments);
    }//GEN-LAST:event_PaymentsMouseExited

    private void PurchasesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PurchasesMouseClicked
        clickPanel(Purchases);
        purchfrm = new purchaseInvoiceFrame();
        Tools.openJFram(purchfrm, "فواتير المشتريات" , "procurement");
    }//GEN-LAST:event_PurchasesMouseClicked

    private void PurchasesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PurchasesMouseExited
        exitPanel(Purchases);
    }//GEN-LAST:event_PurchasesMouseExited

    private void ReportsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReportsMouseClicked
        clickPanel(Reports);
        report = new RebortsFrame();
        Tools.openJFram(report, "التقارير" , "business-report_1");    
    }//GEN-LAST:event_ReportsMouseClicked

    private void ReportsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReportsMouseExited
        exitPanel(Reports);
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

    private void txtIdItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdItemsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdItemsActionPerformed

    private void txtIdItemsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdItemsKeyReleased
        // TODO add your handling code here:
        if((txtIdItems.getText().length()) >= 13){
            Items item = new Items();
            String id_item = txtIdItems.getText();
            String name_item = item.getNameItemFromId(id_item);
            if(name_item.isEmpty()){
                txtnameItem.setText("الصنف غير موجود");
                txtIdItems.setSelectionStart(0);
                txtIdItems.setSelectionEnd(13);
                
            }else{
               txtnameItem.setText(name_item);
               txtQut.setText("1");
               txtPrice.setText(item.getSalesPriceLow(name_item));
               txtIdItems.setFocusable(false);
               txtQut.setSelectionStart(0);
               txtQut.setSelectionEnd(1);
            }
   
        }

    }//GEN-LAST:event_txtIdItemsKeyReleased

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
         Items item = new Items();
        String name_items = txtnameItem.getText();
        double Total = Double.parseDouble(txtQut.getText()) * Double.parseDouble(txtPrice.getText());
        SalesInvoic sales = new SalesInvoic();
        if(name_items != null){
         Vector<ItemsOnInvoice> valuesItems = new Vector<ItemsOnInvoice> ();
         String id_unit = item.getIdUnitFromNameItems(name_items);
         String[] values = new String[7]; 
      
         values[6] = "1";//مسلسل
         values[5] = name_items;//اسم الصنف
         values[4] = txtQut.getText();//الكمية
         values[3] = item.getlowUnitFromId(id_unit);//اسم الوحدة
         values[2] = txtPrice.getText();//سعر الوحدة
         values[1] = "0.00";//الخصم
         values[0] = Total+"";// الاجمالي
         int id = 1;
         String id_items = item.getIdItemsFromName(name_items);
         double qyt = Double.parseDouble(values[4]);
         String name_unit = values[3];
         double price_item = Double.parseDouble(values[2]);
         double discont = 0.00;
         double amount = Total;
         int id_Invoic = Integer.parseInt(sales.getLastId());
         valuesItems.add(new ItemsOnInvoice(id, id_items, name_items, qyt, name_unit, price_item, discont, amount, id_Invoic));
         
         // عدم توافر الكميات بالمخزن
         //double oldBalance = 
        sales.setValues(valuesItems);
        sales.setId_invoice(sales.getLastId());
        sales.setDate_invoice(new Date());
        sales.setPaymentMethod(Tools.getPayment("كاش"));
        sales.setId_client("1");
        sales.setAmount(Total);
        sales.setDiscont(Double.parseDouble("0.00"));
        sales.setCashAmount(Total);
        sales.setRemainingAmount(Double.parseDouble("0.00"));
        sales.setNote("فاتورة مبيعات نقدية" + id_Invoic);
        if(sales.Save()){
            txtPrice.setText("");
            txtQut.setText("");
            txtnameItem.setText("");
            txtIdItems.setText("");
            txtNowBalance.setText(new CasherClass().getNowBalanceCasher());
        }
          
         
        }
         txtIdItems.setRequestFocusEnabled(true);
        txtIdItems.requestFocus();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void posMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_posMouseClicked
        // TODO add your handling code here:
        clickPanel(pos);
        PosFrame posf = new PosFrame();
        Tools.openJFram(posf);
    }//GEN-LAST:event_posMouseClicked

    private void posMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_posMouseExited
        // TODO add your handling code here:
        exitPanel(pos);
    }//GEN-LAST:event_posMouseExited

    private void feesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_feesMouseClicked
        // TODO add your handling code here:
        clickPanel(fees);
        expensFrame cf = new expensFrame();
        Tools.openJFram(cf, "المصروفات","expense_64");
    }//GEN-LAST:event_feesMouseClicked

    private void feesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_feesMouseExited
        // TODO add your handling code here:
        exitPanel(fees);
    }//GEN-LAST:event_feesMouseExited

    private void loanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loanMouseClicked
        // TODO add your handling code here:
        MenusolfandOtherFrame solfa = new MenusolfandOtherFrame();
        Tools.openJFram(solfa , "سلف و عهد" ,"payment");
    }//GEN-LAST:event_loanMouseClicked

    private void loanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loanMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_loanMouseExited

    private void VCashMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VCashMouseClicked
        // TODO add your handling code here:
        clickPanel(VCash);
        menuCashFrame mcf = new menuCashFrame();
        Tools.openJFram(mcf , " فودافون كاش" ,"wallet_mob");
                
    }//GEN-LAST:event_VCashMouseClicked

    private void VCashMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VCashMouseExited
        // TODO add your handling code here:
        exitPanel(VCash);
    }//GEN-LAST:event_VCashMouseExited

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        txtNowBalance.setText(new CasherClass().getNowBalanceCasher());
        txtMasaryBalance.setText(new posPay(id_pos) {
        }.getfirstBalance()+"");
        settxtnumer();
        txtNmber.setText("");
        txtIdItems.requestFocusInWindow();

    }//GEN-LAST:event_formWindowActivated

    private void card_10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_card_10MouseClicked
        // TODO add your handling code here:
        posPay cardPay = new posPay(id_pos) {
        };
        SwingWorker<Boolean,Object> worker = new SwingWorker<Boolean, Object>() {

            @Override
            protected Boolean doInBackground() throws Exception {
                    clickPanel(card_10);
                    String idUm = ConnectDB.getIdFromName("SELECT id_utility_masary AS id FROM utility_masary WHERE note_utility ='كرت  _ 10' AND id_pos = "+id_pos);
                    int id_utility_masary = Integer.parseInt(idUm);
                    cardPay.setId_utility_masary(id_utility_masary);
                    cardPay.setPrice_masary_pay(10);
                    cardPay.setDiscount_of_balance(10);
                    cardPay.setAmount_masary_pay(10);
                    cardPay.setUtility_masary("كرت  _ 10");
                return cardPay.SaveCasher();
            }

            @Override
            protected void done() {
                boolean isSave = false;
                try {
                    isSave = get();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExecutionException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(isSave){
                    exitPanel(card_10);
                    txtNowBalance.setText(new CasherClass().getNowBalanceCasher());     
                    txtMasaryBalance.setText(cardPay.getNewbalance()+"");
                }
            
            }
        };   
        worker.execute();
    
    }//GEN-LAST:event_card_10MouseClicked

    private void card_10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_card_10MouseExited
        // TODO add your handling code here:
        //exitPanel(calc);
    }//GEN-LAST:event_card_10MouseExited

    private void calcMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calcMouseClicked

        if(!(cf.isVisible())){
             cf = new calcFrame();
             Tools.openJFram(cf,"حاسبة","calculator");
        }else{
            cf.dispose();
        }
    }//GEN-LAST:event_calcMouseClicked

    private void counterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_counterKeyPressed
       // new Tools().playSound("mc.mp3");
    }//GEN-LAST:event_counterKeyPressed

    private void counterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_counterMouseClicked
        // TODO add your handling code here:
        double casher = Double.parseDouble(txtNowBalance.getText());
        
       if(!(cuf.isVisible())){
//           cuf = new CounterFrame();
           cuf = new CounterFrame();
           cuf.setCasher(casher);
           Tools.openJFram(cuf, "عد الفلوس");          
       }else{
           //cuf.setDefaultCloseOperation(JFrame.);
           cuf.dispose();
           
       }
    }//GEN-LAST:event_counterMouseClicked

    private void comboNoteUtiltiyItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboNoteUtiltiyItemStateChanged
        // TODO add your handling code here:
        settxtnumer();
        
    }//GEN-LAST:event_comboNoteUtiltiyItemStateChanged

    private void txtvalueKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtvalueKeyReleased
        // TODO add your handling code here:
        String value_st = txtvalue.getText();
        double value;
        if(value_st.isEmpty()){
                value = 0;
        }else{
           value  = Double.parseDouble(value_st);
        }
        double discount = value * cost;
        txt_discount.setText(discount+"");
        double price_to_value = value * price;
        txtAmount.setText(price_to_value+"");
    }//GEN-LAST:event_txtvalueKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        posPay posp = new posPay(id_pos) {};
        String utility_masary = comboNoteUtiltiy.getSelectedItem().toString();
        int id_utility_masary = new masary_Utilites(id_pos).getIdByNote(utility_masary);
        //price_masary_pay   value
        double price_masary_pay = Double.parseDouble(txtvalue.getText());
        //discount_of_balance
        double discount_of_balance = Double.parseDouble(txt_discount.getText());
        //amount_masary_pay
        double amount_masary_pay = Double.parseDouble(txtAmount.getText());
        // phone number
        String phone = txtNmber.getText();
        posp.setId_utility_masary(id_utility_masary);
        posp.setPrice_masary_pay(price_masary_pay);
        posp.setDiscount_of_balance(discount_of_balance);
        posp.setAmount_masary_pay(amount_masary_pay);
        posp.setIs_requer_phone_num(true);
        if(phone.isEmpty()){
            posp.setUtility_masary(utility_masary);
        }else{
            posp.setUtility_masary(phone);
            posp.setPhone(phone);
        }
        
        boolean isSaved = posp.SaveCasher();
        if(isSaved){
            txtvalue.setText("0");
            txtAmount.setText("");
            txt_discount.setText("");
            txtNmber.setText("");
            txtMasaryBalance.setText(posp.getNewbalance()+"");
            txtNowBalance.setText(new CasherClass().getNowBalanceCasher());
            txtIdItems.requestFocus();
            settxtnumer();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtMasaryBalanceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMasaryBalanceMouseClicked
        try {
            // TODO add your handling code here:
            String [] selectionValues = ConnectDB.getColumn("pos", "name_pos");
            String master = selectionValues[0];
            ImageIcon icon = new ImageIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/icons/pos.png")).getImage());
            Object name_pos = JOptionPane.showInputDialog(this, "اختر ماكينة الشحن", "تغيير المكنة", JOptionPane.OK_CANCEL_OPTION, icon, selectionValues, master);
            String id_pos_st = new PosClass().getIdPosFromNamePos(name_pos.toString());
            id_pos = Integer.parseInt(id_pos_st);
            ImageIcon iconlogo = new ImageIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/icons/"+id_pos_st+".png")).getImage());
            logo.setIcon(iconlogo);
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtMasaryBalanceMouseClicked

    private void vf_cashMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vf_cashMouseClicked

       if(winFastCashSend.isVisible()){
             winFastCashSend.setVisible(false);
             //winFastCashSend = new JWindow(this);
        }else{
            FastCashSendPanel cash = new FastCashSendPanel();
            cash.setVisible(true);
            winFastCashSend.setSize(475, 125);
            winFastCashSend.setOpacity(0.75f);
            int x = vf_cash.getX() + vf_cash.getWidth() ;
            int y = vf_cash.getY() - vf_cash.getHeight();
            winFastCashSend.setLocation(x, y);
            winFastCashSend.getContentPane().add(cash);
            winFastCashSend.repaint();
            winFastCashSend.setVisible(true);       
        }
        txtNowBalance.setText(new CasherClass().getNowBalanceCasher());  

        
    }//GEN-LAST:event_vf_cashMouseClicked

    private void btn_balanceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_balanceMouseClicked
        // TODO add your handling code here:
        if(!(d.isVisible())){
            d = new DayFrame();
            Tools.openJFram(d, "التقرير اليومي", "balance");
        }else{
            d.dispose();
//            d = new DayFrame();
//            Tools.openJFram(d, "التقرير اليومي", "balance");
        }

    }//GEN-LAST:event_btn_balanceMouseClicked

    private void txtNmberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNmberKeyPressed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_txtNmberKeyPressed

    private void bt_deficiency_excessKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bt_deficiency_excessKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_bt_deficiency_excessKeyPressed

    private void bt_deficiency_excessMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_deficiency_excessMouseClicked
        // TODO add your handling code here:
       if(windeficiencyExcess.isVisible()){
             windeficiencyExcess.setVisible(false);
             //winFastCashSend = new JWindow(this);
        }else{
            deficiency_excessPanel cash = new deficiency_excessPanel();
            cash.setVisible(true);
            windeficiencyExcess.setSize(400,140);
            windeficiencyExcess.setOpacity(0.75f);
            int x = vf_cash.getX();
            int y = bt_deficiency_excess.getY() - 150;
            windeficiencyExcess.setLocation(x, y);
            windeficiencyExcess.getContentPane().add(cash);
            windeficiencyExcess.repaint();
            windeficiencyExcess.setVisible(true);       
        }
        
    }//GEN-LAST:event_bt_deficiency_excessMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        txtIdItems.setText("");
        txtIdItems.setFocusable(true);
        txtnameItem.setText("");
        txtQut.setText("");
        txtPrice.setText("");
        txtIdItems.setRequestFocusEnabled(true);
        txtIdItems.requestFocus();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnRturnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRturnMouseClicked
        // TODO add your handling code here:
        ReturnSalesFrame rsf = new ReturnSalesFrame();
//        Tools.openJFram(rsf , "" , "");
        Tools.openJFram(rsf, "مردودات المبيعات", "packa");
    }//GEN-LAST:event_btnRturnMouseClicked

    private void btnItemsBalanceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnItemsBalanceMouseClicked
        // TODO add your handling code here:
        balanceItems b = new balanceItems();
        Tools.openJFram(b, "جرد الاصناف", "search");
    }//GEN-LAST:event_btnItemsBalanceMouseClicked

    private void btnScannMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnScannMouseClicked
        // TODO add your handling code here:
        ItemsPrint ip = new ItemsPrint();
        Tools.openJFram(ip, "طباعة باركود", "scanner");
    }//GEN-LAST:event_btnScannMouseClicked

    private void gooldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gooldMouseClicked
        // TODO add your handling code here:
        clickPanel(goold);
        GoldFrame gf = new GoldFrame();
        Tools.openJFram(gf,"شغل الجبل" , "ingots");
    }//GEN-LAST:event_gooldMouseClicked

    private void gooldMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gooldMouseExited
        // TODO add your handling code here:
        exitPanel(goold);
    }//GEN-LAST:event_gooldMouseExited

    private void txtNowBalanceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNowBalanceMouseClicked
        // TODO add your handling code here:
//        String Balance = new CasherClass().getNowBalanceCasher();
//        Tools.showErrorMsg(Balance);
        
         if(winCahse.isVisible()){
             winCahse.setVisible(false);
             exitPanel(txtNowBalance);
             //winFastCashSend = new JWindow(this);
        }else{
             clickPanel(txtNowBalance);
            CasherTowPanel cash = new CasherTowPanel();
            cash.setVisible(true);
            winCahse.setSize(478, 176);
            winCahse.setOpacity(0.75f);
            int x = txtNowBalance.getX() + txtNowBalance.getWidth() ;
            int y = txtNowBalance.getY() - txtNowBalance.getHeight();
            winCahse.setLocation(x, y);
            winCahse.getContentPane().add(cash);
            winCahse.repaint();
            winCahse.setVisible(true);       
        }
        txtNowBalance.setText(new CasherClass().getNowBalanceCasher());  

    }//GEN-LAST:event_txtNowBalanceMouseClicked
    
    private void settxtnumer(){
        masary_Utilites utilit = new masary_Utilites(id_pos);
        String utility = comboNoteUtiltiy.getSelectedItem().toString();
        price = utilit.getPriceByNote(utility);
        cost = utilit.getCost(utility);
        switch(utility){
            case "شحن اتصالات":
                txtNmber.setText("011");
                break;
            case "شحن فودافون":
                txtNmber.setText("010");
                break;
            case "شحن اورنج":
                txtNmber.setText("012");
                break;
            case "شحن WE":
                txtNmber.setText("015");
                break;
            default:
                txtNmber.setText("");
                break;
                
        }
        Tools.txtNumberClient(txtIdItems,txtNmber, this, 0, 0);
        txtNmber.requestFocus();   
    }
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
    private javax.swing.JPanel Barpanel;
    private javax.swing.JLabel Butt_notification;
    private javax.swing.JLabel Payments;
    private javax.swing.JLabel Purchases;
    private javax.swing.JLabel Receipts;
    private javax.swing.JLabel Reports;
    private javax.swing.JLabel VCash;
    private javax.swing.JLabel background;
    private javax.swing.JLabel bt_deficiency_excess;
    private javax.swing.JLabel btnItemsBalance;
    private javax.swing.JLabel btnRturn;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel btnScann;
    private javax.swing.JLabel btn_balance;
    private javax.swing.JLabel calc;
    private javax.swing.JLabel card_10;
    private javax.swing.JLabel client;
    private javax.swing.JComboBox comboNoteUtiltiy;
    private javax.swing.JLabel counter;
    private javax.swing.JLabel fees;
    private javax.swing.JLabel goold;
    private javax.swing.JLabel items;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel loan;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel pos;
    private javax.swing.JLabel sales;
    private javax.swing.JPanel selePanal;
    private javax.swing.JLabel suppliers;
    private javax.swing.JFormattedTextField txtAmount;
    private javax.swing.JLabel txtDate;
    private javax.swing.JTextField txtIdItems;
    private javax.swing.JLabel txtMasaryBalance;
    private javax.swing.JTextField txtNmber;
    private javax.swing.JLabel txtNowBalance;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtQut;
    private javax.swing.JLabel txtTimer;
    private javax.swing.JLabel txt_discount;
    private javax.swing.JLabel txtinfoUser;
    private javax.swing.JLabel txtinformationPos;
    private javax.swing.JLabel txtnameItem;
    private javax.swing.JFormattedTextField txtvalue;
    private javax.swing.JLabel vf_cash;
    // End of variables declaration//GEN-END:variables

//    private void setBalanceCasher(){
//        Timer t = new Timer();
//        t.schedule(new TimerTask() {
//
//            @Override
//            public void run() {
//                try{
//                    txtNowBalance.setText(new CasherClass().getNowBalanceCasher());
//                    run();                
//                }catch(Exception ex){
//                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
//                }
//
//            }
//        }, 1000);
//    
//       
//    }



}
