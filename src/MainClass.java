
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class MainClass extends javax.swing.JFrame implements ActionListener{

    private JButton b[][];
    private boolean tick[][];
    private int iUndo[];
    private int jUndo[];
    private int sizeUndo;
    private SettingClass setting;
    private boolean flag=true;
    static int r = 16,c = 20;
    static int count=0;
    static boolean ico = false;
    static String crossO = "O",crossX = "X";
    static Color colorO = Color.GREEN, colorX = Color.RED, colorBoard = Color.WHITE;
    static ImageIcon icoO = new ImageIcon(), icoX = new ImageIcon();
    public MainClass() {
        initComponents();
        initBoard();
    }
    // khởi tạo bàn cờ
    public void initBoard(){
        b = new JButton[r][c];
        tick = new boolean[r][c];
        iUndo = new int[r*c];
        jUndo = new int[r*c];
        sizeUndo=0;
        this.btnUndo.setEnabled(false);
        int i,j;
        int x=0,y=0;
        for (i=0;i<r;i++){
            for (j=0;j<c;j++){
                // khởi tạo tick & caro
                b[i][j] = new JButton();
                b[i][j].setSize(50,50);
                b[i][j].setVisible(true);
                b[i][j].setLocation(new Point(x,y));
                b[i][j].setBackground(Color.GRAY);
                b[i][j].setFont(new Font("Lato",1,28));
                b[i][j].setActionCommand(i+" "+j);
                b[i][j].addActionListener(this);
                pn1.add(b[i][j]);
                tick[i][j]=false;
                x+=50;
                // khởi tạo biến mảng undo
                iUndo[j] = jUndo[j] = -1;
            }
            y+=50;
            x=0;
        }
    }
    // xử lý trận đánh
    public void setCheckedCross(int i,int j){
        if (!ico){
            //Không dùng icon
            if (count==0){
                b[i][j].setText(crossO);
                b[i][j].setForeground(colorO);
                tick[i][j]=false;
                lbMove.setText("X player");
            }else{
                b[i][j].setText(crossX);
                b[i][j].setForeground(colorX);
                tick[i][j]=false;
                lbMove.setText("O player");
            }
        }else{
            //Nếu sử dung icon
            if (count==0){
               b[i][j].setIcon(MainClass.icoO);
            }else{
               b[i][j].setIcon(MainClass.icoX);
            }
        }
        iUndo[sizeUndo] = i;
        jUndo[sizeUndo] = j;
        sizeUndo++;
        count = 1-count;
    }
    public void pauseGame(){
        for (int i=0;i<r;i++)
            for (int j=0;j<c;j++){
                b[i][j].setBackground(Color.GRAY);
                tick[i][j]=false;
            }
    }
    public void startGame(){
        for (int i=0;i<r;i++)
            for (int j=0;j<c;j++){
                b[i][j].setBackground(Color.WHITE);
                tick[i][j] =true;
                if (!ico){
                   b[i][j].setText("");
                }else{
                   b[i][j].setIcon(null);
                   b[i][j].setIcon(new ImageIcon());
                }
            }
        pn1.setBackground(colorBoard);
        this.flag = true;
    }
    public void undoGame(){
        b[iUndo[sizeUndo-1]][jUndo[sizeUndo-1]].setText("");
        sizeUndo--;
    }
    public void undoGameIcon(){
        b[iUndo[sizeUndo-1]][jUndo[sizeUndo-1]].setIcon(null);
        sizeUndo--;
    }
    // xử lý cross từ Setting
    
    // xử lý sự kiện trận đánh
    public void actionPerformed(ActionEvent e){
            try{
                String evt = new String(e.getActionCommand());
                int flag = evt.indexOf(" ");
                int i = Integer.parseInt(evt.substring(0,flag));
                int j = Integer.parseInt(evt.substring(flag+1));
                String winner = new String();
                if (count==0){
                    winner = "O";
                }else{
                    winner = "X";
                }
                if (tick[i][j]){
                    this.setCheckedCross(i, j);
                    if (ico){
                        if (ActionClass.checkWinIcon(b, i, j, 5)){
                            JOptionPane.showMessageDialog(new JDialog(),winner+ " is winner");
                            this.pauseGame();
                            this.flag = false;
                        }
                    }else{
                        if (ActionClass.checkWinText(b, i, j, 5)){
                            JOptionPane.showMessageDialog(new JDialog(),winner+ " is winner");
                            this.pauseGame();
                            this.flag = false;
                        }
                    }
                    this.btnUndo.setEnabled(true);
                }
            }catch(Exception ex){
                System.out.println(ex);
            }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnBoard = new javax.swing.JPanel();
        pn1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnNewGame = new javax.swing.JButton();
        btnUndo = new javax.swing.JButton();
        btnSetting = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbMove = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CARO - X");
        setBackground(new java.awt.Color(0, 0, 0));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnBoard.setBackground(new java.awt.Color(255, 255, 255));

        pn1.setBackground(new java.awt.Color(255, 255, 255));
        pn1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(38, 208, 254), 2));
        pn1.setPreferredSize(new java.awt.Dimension(1000, 800));

        javax.swing.GroupLayout pn1Layout = new javax.swing.GroupLayout(pn1);
        pn1.setLayout(pn1Layout);
        pn1Layout.setHorizontalGroup(
            pn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 996, Short.MAX_VALUE)
        );
        pn1Layout.setVerticalGroup(
            pn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 796, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Lato", 1, 64)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(38, 208, 254));
        jLabel1.setText("CARO - X");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONPACK/001-tic-tac-toe.png"))); // NOI18N

        btnNewGame.setBackground(new java.awt.Color(255, 255, 255));
        btnNewGame.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        btnNewGame.setForeground(new java.awt.Color(38, 208, 254));
        btnNewGame.setText("New Game");
        btnNewGame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNewGameMouseClicked(evt);
            }
        });

        btnUndo.setBackground(new java.awt.Color(255, 255, 255));
        btnUndo.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        btnUndo.setForeground(new java.awt.Color(38, 208, 254));
        btnUndo.setText("Undo");
        btnUndo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUndoMouseClicked(evt);
            }
        });

        btnSetting.setBackground(new java.awt.Color(255, 255, 255));
        btnSetting.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        btnSetting.setForeground(new java.awt.Color(38, 208, 254));
        btnSetting.setText("Setting");
        btnSetting.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSettingMouseClicked(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Lato", 1, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "One player", "Two player", "Multiplayer" }));

        jLabel3.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        jLabel3.setText("Player move:");

        jLabel4.setFont(new java.awt.Font("Lato", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(38, 208, 254));
        jLabel4.setText("5 in line to win ...");

        lbMove.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        lbMove.setText("Unknown");

        jLabel6.setText("Copy right ....");

        javax.swing.GroupLayout pnBoardLayout = new javax.swing.GroupLayout(pnBoard);
        pnBoard.setLayout(pnBoardLayout);
        pnBoardLayout.setHorizontalGroup(
            pnBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBoardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnBoardLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnBoardLayout.createSequentialGroup()
                                .addGroup(pnBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnSetting, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnUndo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnNewGame, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(12, 12, 12)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnBoardLayout.createSequentialGroup()
                                .addGroup(pnBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(pnBoardLayout.createSequentialGroup()
                                        .addGap(48, 48, 48)
                                        .addComponent(jLabel4))
                                    .addGroup(pnBoardLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel2)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(pnBoardLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(pnBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(pnBoardLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbMove)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnBoardLayout.setVerticalGroup(
            pnBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnBoardLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(pnBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnBoardLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addGroup(pnBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNewGame)
                            .addComponent(jComboBox1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUndo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSetting)
                        .addGap(18, 18, 18)
                        .addGroup(pnBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lbMove))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6))
                    .addComponent(pn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(pnBoard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1320, 830));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewGameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNewGameMouseClicked
        startGame();
        lbMove.setText("O player");
        count=0;
        this.btnUndo.setEnabled(false);
    }//GEN-LAST:event_btnNewGameMouseClicked

    private void btnUndoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUndoMouseClicked
        if (sizeUndo>0 && flag){
            if (!ico){
                this.undoGame();
            }else{
                this.undoGameIcon();
            }
            count=1-count;
            if (count==0){
                lbMove.setText("O");
            }else{
                lbMove.setText("X");
            }
        }
    }//GEN-LAST:event_btnUndoMouseClicked

    private void btnSettingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSettingMouseClicked
        setting = new SettingClass();
        setting.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSettingMouseClicked

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
            java.util.logging.Logger.getLogger(MainClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainClass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainClass().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNewGame;
    private javax.swing.JButton btnSetting;
    private javax.swing.JButton btnUndo;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lbMove;
    private javax.swing.JPanel pn1;
    private javax.swing.JPanel pnBoard;
    // End of variables declaration//GEN-END:variables
}
