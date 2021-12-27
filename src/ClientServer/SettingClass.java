package ClientServer;



import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;


public class SettingClass extends javax.swing.JFrame {
    private String select_icon1, select_icon2;
    private MainClass main;
    public SettingClass() {
        initComponents();
        list.setSelectedIndex(0);
    }
    public void setColorBoard(){
        MainClass.colorBoard = choose.getColor();
    }
    public void setColorO(){
        MainClass.colorO = choose.getColor();
    }
    public void setColorX(){
        MainClass.colorX = choose.getColor();
    }
    public void setEmptyIcon(){
        btnIcoO.setText("");
        btnIcoX.setText("");
        btnIcoO.setIcon(null);
        btnIcoX.setIcon(null);
    }
            
            
            
            
            
            
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        cBoard = new javax.swing.JCheckBox();
        cO = new javax.swing.JCheckBox();
        cX = new javax.swing.JCheckBox();
        choose = new javax.swing.JColorChooser();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        list = new javax.swing.JList<>();
        btnIcoX = new javax.swing.JButton();
        btnIcoO = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtIcoO = new javax.swing.JTextField();
        txtIcoX = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btnSet = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Setting");
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "COLOR SELECT", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lato", 1, 18))); // NOI18N

        cBoard.setBackground(new java.awt.Color(255, 255, 255));
        cBoard.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        cBoard.setText("Game board");

        cO.setBackground(new java.awt.Color(255, 255, 255));
        cO.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        cO.setText("Cross O");

        cX.setBackground(new java.awt.Color(255, 255, 255));
        cX.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        cX.setText("Cross X");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(choose, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cX)
                    .addComponent(cO)
                    .addComponent(cBoard))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(choose, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(cBoard)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cO)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cX)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ICON SELECT", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lato", 1, 18))); // NOI18N

        list.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Default", "Social Network", "Food", "Human", "Vehicle", "Superhero", "Search Engine", "Smartphone", " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        list.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(list);

        btnIcoX.setBackground(new java.awt.Color(255, 255, 255));
        btnIcoX.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(38, 208, 254)));

        btnIcoO.setBackground(new java.awt.Color(255, 255, 255));
        btnIcoO.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(38, 208, 254)));

        jLabel1.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jLabel1.setText("Cross O");

        jLabel2.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jLabel2.setText("Cross X");

        jLabel3.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jLabel3.setText("Icon pack: ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIcoO, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnIcoO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtIcoX, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnIcoX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(185, 185, 185))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtIcoO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnIcoO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtIcoX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnIcoX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(38, 208, 254));

        jLabel4.setFont(new java.awt.Font("Lato", 1, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("CARO - X");

        btnSet.setBackground(new java.awt.Color(255, 255, 255));
        btnSet.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        btnSet.setText("Set");
        btnSet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSetMouseClicked(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(255, 255, 255));
        btnReset.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        btnReset.setText("Reset");
        btnReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetMouseClicked(evt);
            }
        });

        btnBack.setBackground(new java.awt.Color(255, 255, 255));
        btnBack.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        btnBack.setText("Back");
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBackMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSet, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSet)
                .addGap(18, 18, 18)
                .addComponent(btnReset)
                .addGap(18, 18, 18)
                .addComponent(btnBack)
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSetMouseClicked
       if (cBoard.isSelected()){
           this.setColorBoard();
       }
       if (cO.isSelected()){
           this.setColorO();
           btnIcoO.setForeground(MainClass.colorO);
       }
       if (cX.isSelected()){
           this.setColorX();
           btnIcoX.setForeground(MainClass.colorX);
       }
       if (MainClass.ico){
           MainClass.icoO = new ImageIcon(getClass().getResource(select_icon1));
           MainClass.icoX = new ImageIcon(getClass().getResource(select_icon2));
       }else{
           if (!txtIcoO.getText().equals("")){
                MainClass.crossO = txtIcoO.getText();
                btnIcoO.setText(MainClass.crossO);
                
           }
           if (!txtIcoX.getText().equals("")){
                MainClass.crossX = txtIcoX.getText();
                btnIcoX.setText(MainClass.crossX);
                
           }
       }
    }//GEN-LAST:event_btnSetMouseClicked

    private void btnBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMouseClicked
       main = new MainClass();
       main.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_btnBackMouseClicked

    private void listValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listValueChanged
        String select = new String(list.getSelectedValue().toString());
        this.setEmptyIcon();
        switch (select){
            case "Default":{
                MainClass.ico=false;
                btnIcoO.setText(MainClass.crossO);
                btnIcoO.setFont(new Font("Lato",1,36));
                btnIcoO.setForeground(MainClass.colorO);
                btnIcoX.setText(MainClass.crossX);
                btnIcoX.setFont(new Font("Lato",1,36));
                btnIcoX.setForeground(MainClass.colorX);
                txtIcoO.setEnabled(true);
                txtIcoX.setEnabled(true);
                break;
            }
            case "Social Network":{
                MainClass.ico=true;
                select_icon1 = "/ICONPACK/002-facebook.png";
                select_icon2 = "/ICONPACK/004-instagram.png";
                btnIcoO.setIcon(new ImageIcon(getClass().getResource(select_icon1)));
                btnIcoX.setIcon(new ImageIcon(getClass().getResource(select_icon2)));
                txtIcoO.setEnabled(false);
                txtIcoX.setEnabled(false);
                break;
            }
            case "Food":{
                MainClass.ico=true;
                select_icon1 = "/ICONPACK/001-burger.png";
                select_icon2 = "/ICONPACK/002-vegetables.png";
                btnIcoO.setIcon(new ImageIcon(getClass().getResource(select_icon1)));
                btnIcoX.setIcon(new ImageIcon(getClass().getResource(select_icon2)));
                txtIcoO.setEnabled(false);
                txtIcoX.setEnabled(false);
                break;
            }
            case "Human":{
                MainClass.ico=true;
                select_icon1 = "/ICONPACK/004-man.png";
                select_icon2 = "/ICONPACK/003-woman.png";
                btnIcoO.setIcon(new ImageIcon(getClass().getResource(select_icon1)));
                btnIcoX.setIcon(new ImageIcon(getClass().getResource(select_icon2)));
                txtIcoO.setEnabled(false);
                txtIcoX.setEnabled(false);
                break;
            }
            case "Vehicle":{
                MainClass.ico=true;
                select_icon1 = "/ICONPACK/005-jeep.png";
                select_icon2 = "/ICONPACK/006-car.png";
                btnIcoO.setIcon(new ImageIcon(getClass().getResource(select_icon1)));
                btnIcoX.setIcon(new ImageIcon(getClass().getResource(select_icon2)));
                txtIcoO.setEnabled(false);
                txtIcoX.setEnabled(false);
                break;
            }
            case "Superhero":{
                MainClass.ico=true;
                select_icon1 = "/ICONPACK/007-superman.png";
                select_icon2 = "/ICONPACK/008-batman.png";
                btnIcoO.setIcon(new ImageIcon(getClass().getResource(select_icon1)));
                btnIcoX.setIcon(new ImageIcon(getClass().getResource(select_icon2)));
                txtIcoO.setEnabled(false);
                txtIcoX.setEnabled(false);
                break;
            }
            case "Search Engine":{
                MainClass.ico=true;
                select_icon1 = "/ICONPACK/009-chrome.png";
                select_icon2 = "/ICONPACK/010-firefox.png";
                btnIcoO.setIcon(new ImageIcon(getClass().getResource(select_icon1)));
                btnIcoX.setIcon(new ImageIcon(getClass().getResource(select_icon2)));
                txtIcoO.setEnabled(false);
                txtIcoX.setEnabled(false);
                break;
            }
            case "Smartphone":{
                MainClass.ico=true;
                select_icon1 = "/ICONPACK/009-windows.png";
                select_icon2 = "/ICONPACK/007-apple.png";
                btnIcoO.setIcon(new ImageIcon(getClass().getResource(select_icon1)));
                btnIcoX.setIcon(new ImageIcon(getClass().getResource(select_icon2)));
                txtIcoO.setEnabled(false);
                txtIcoX.setEnabled(false);
                break;
            }
        }
        if (MainClass.ico){
            cO.setEnabled(false);
            cX.setEnabled(false);
        }else{
            cO.setEnabled(true);
            cX.setEnabled(true);
        }
    }//GEN-LAST:event_listValueChanged

    private void btnResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseClicked
        MainClass.colorBoard = Color.white;
        MainClass.colorO = Color.GREEN;
        MainClass.colorX = Color.RED;
        MainClass.ico = false;
        MainClass.crossO = "O";
        MainClass.crossX = "X";
        list.setSelectedIndex(0);
        btnIcoO.setText("O");
        btnIcoO.setIcon(null);
        btnIcoX.setText("X");
        btnIcoX.setIcon(null);
        txtIcoO.setText("");
        txtIcoX.setText("");
    }//GEN-LAST:event_btnResetMouseClicked

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
            java.util.logging.Logger.getLogger(SettingClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SettingClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SettingClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SettingClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SettingClass().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnIcoO;
    private javax.swing.JButton btnIcoX;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSet;
    private javax.swing.JCheckBox cBoard;
    private javax.swing.JCheckBox cO;
    private javax.swing.JCheckBox cX;
    private javax.swing.JColorChooser choose;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> list;
    private javax.swing.JTextField txtIcoO;
    private javax.swing.JTextField txtIcoX;
    // End of variables declaration//GEN-END:variables
}
