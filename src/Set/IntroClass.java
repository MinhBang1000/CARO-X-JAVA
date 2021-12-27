package Set;

import ClientServer.MainClass;

public class IntroClass extends javax.swing.JFrame {

    private SettingClass setting;
    private MainClassTwo mainTwo;
    private MainClass mainOnline;
    private User user;

    public IntroClass() {
        initComponents();
    }

    public void createSettingClass() { //tạo lớp setting để chuyển form qua lại
        setting = new SettingClass();
        SettingClass.classReturn = 0;
        setting.setBackForm(this);
        setting.setVisible(true);
        this.setVisible(false);
    }

    public void createMainClass(boolean kt) { // tạo lớp main bao gồm mainClassTwo và mainClassOnline để chuyển form qua lại tùy trường hợp
        if (kt == false) {
            mainTwo = new MainClassTwo();
            //MainClassTwo.onl=kt;
            mainTwo.setMenuClass(this);
            mainTwo.setVisible(true);
            user = new User();
            mainTwo.setUserClass(user);
            user.setVisible(true);
            user.setMainClass(mainTwo);
            mainTwo.getNewGame().setEnabled(false);
            this.setVisible(false);
        } else {
            mainOnline = new MainClass();
            //MainClassTwo.onl=kt;
            mainOnline.setMenuClass(this);
            mainOnline.setVisible(true);
            mainOnline.getNewGame().setEnabled(false);
            this.setVisible(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnTwo = new javax.swing.JButton();
        btnLAN = new javax.swing.JButton();
        btnSetting = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CARO-X:MENU");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnTwo.setBackground(new java.awt.Color(255, 255, 255));
        btnTwo.setFont(new java.awt.Font("Calibri", 1, 30)); // NOI18N
        btnTwo.setText("Two Player");
        btnTwo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(38, 208, 254), 2));
        btnTwo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTwoMouseClicked(evt);
            }
        });

        btnLAN.setBackground(new java.awt.Color(255, 255, 255));
        btnLAN.setFont(new java.awt.Font("Calibri", 1, 30)); // NOI18N
        btnLAN.setText("Play on LAN");
        btnLAN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(38, 208, 254), 2));
        btnLAN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLANMouseClicked(evt);
            }
        });

        btnSetting.setBackground(new java.awt.Color(255, 255, 255));
        btnSetting.setFont(new java.awt.Font("Calibri", 1, 30)); // NOI18N
        btnSetting.setText("Setting");
        btnSetting.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(38, 208, 254), 2));
        btnSetting.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSettingMouseClicked(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Lato", 1, 68)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(38, 208, 254));
        jLabel1.setText("CARO - X");

        jLabel3.setFont(new java.awt.Font("Lato", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(38, 208, 254));
        jLabel3.setText("5 in line to win ...");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnLAN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnTwo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(btnSetting, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jLabel3))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jLabel1)))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(38, 38, 38)
                .addComponent(btnTwo)
                .addGap(31, 31, 31)
                .addComponent(btnLAN)
                .addGap(31, 31, 31)
                .addComponent(btnSetting)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSettingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSettingMouseClicked
        this.createSettingClass();
    }//GEN-LAST:event_btnSettingMouseClicked

    private void btnTwoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTwoMouseClicked
        this.createMainClass(false); // chơi offline
    }//GEN-LAST:event_btnTwoMouseClicked

    private void btnLANMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLANMouseClicked
        this.createMainClass(true); // chơi online
    }//GEN-LAST:event_btnLANMouseClicked

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
            java.util.logging.Logger.getLogger(IntroClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IntroClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IntroClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IntroClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IntroClass().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLAN;
    private javax.swing.JButton btnSetting;
    private javax.swing.JButton btnTwo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
