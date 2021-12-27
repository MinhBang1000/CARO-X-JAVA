package Final;


import Set.SettingClass;
import Set.IntroClass;
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

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
public class MainClass extends javax.swing.JFrame implements ActionListener{
    // <thuộc tính khởi tạo>
    private JButton b[][];
    private boolean tick[][];
    private int iUndo[];
    private int jUndo[];
    private int sizeUndo;
    private IntroClass intro;
    private SettingClass setting;
    private boolean flag=true;
    static int r = 16,c = 20;
    static int count=0;
    static boolean ico = false;
    static String crossO = "O",crossX = "X";
    static Color colorO = Color.GREEN, colorX = Color.RED, colorBoard = Color.WHITE;
    static ImageIcon icoO = new ImageIcon(), icoX = new ImageIcon();
    // </thuộc tính khởi tạo>
    
    // <thuộc tính của multiplayer>
    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private boolean ser = false;
    private boolean win = false;
    private String letter = ""; // gửi từ đối thủ
    private boolean[][] backup;
    private boolean online=false;
    private Thread waitLetter;
    // </thuộc tính của multipalyer>
    
    public MainClass() {
        initComponents();
        initBoard();
    }
    // khởi tạo bàn cờ
    public void initBoard(){
        b = new JButton[r][c];
        tick = new boolean[r][c];
        backup = new boolean[r][c];
        iUndo = new int[r*c];
        jUndo = new int[r*c];
        sizeUndo=0;
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
                backup[i][j]=false;
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
                backup[i][j] = true;
                lbMove.setText("X player");
            }else{
                b[i][j].setText(crossX);
                b[i][j].setForeground(colorX);
                tick[i][j]=false;
                backup[i][j] = true;
                lbMove.setText("O player");
            }
        }else{
            //Nếu sử dung icon
            if (count==0){
               b[i][j].setIcon(MainClass.icoO);
               tick[i][j]=false;
               backup[i][j] = true;
            }else{
               b[i][j].setIcon(MainClass.icoX);
               tick[i][j]=false;
               backup[i][j] = true;
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
                backup[i][j] = false;
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
        if (online){
            if (ser){
                count=0;
            }else{
                count=1;
            }
            this.crossOnline();
        }
    }
    public void undoGame(){
        b[iUndo[sizeUndo-1]][jUndo[sizeUndo-1]].setText("");
        sizeUndo--;
    }
    public void undoGameIcon(){
        b[iUndo[sizeUndo-1]][jUndo[sizeUndo-1]].setIcon(null);
        sizeUndo--;
    }
    // xử lý multiplayer
    public void checkConnectServer(){
        try {
            socket = new Socket("localhost",9999);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            System.out.println("I am Client");
        } catch (IOException ex) {
            ser = true;
            System.out.println("I am Server");
        }
    }
    
    public void createServer() throws IOException{
        if (ser){
            serverSocket = new ServerSocket(9999); // giả sử lúc nào cũng đợi đến khi kết nối
            Thread waitServer = new Thread(){
                public void run(){
                    try {
                        socket = serverSocket.accept();
                        in = new DataInputStream(socket.getInputStream());
                        out = new DataOutputStream(socket.getOutputStream()); // cái mà sẽ gửi đi
                        System.out.println("Accept succesfull!");
                    } catch (IOException ex) {
                        Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
            waitServer.start();
        }
    }
    
    public void send(int i, int j, int who, boolean winner){
        String mess = new String(i+" "+j+","+who+"-"+winner);
        try {
            out.writeUTF(mess);
            out.flush();
            System.out.println("Message has sent: "+mess);
        } catch (IOException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void received(){
        try {
            letter = new String(in.readUTF());
            System.out.println("Have received: "+letter);
        } catch (IOException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void crossOnline(){
        waitLetter = new Thread(){
            public void run(){
                int flag1, flag2, flag3;
                int i,j,who=0;
                boolean winner=false;
                while (win==false){
                    received(); //set cái letter
                    if (!letter.equals("")){
                        if (ser && count==1){
                            flag1 = letter.indexOf(" ");
                            flag2 = letter.indexOf(",");
                            flag3 = letter.indexOf("-");
                            try{
                                i = Integer.parseInt(letter.substring(0,flag1));
                                j = Integer.parseInt(letter.substring(flag1+1, flag2));
                                who = Integer.parseInt(letter.substring(flag2+1,flag3));
                                winner = Boolean.parseBoolean(letter.substring(flag3+1));
                                win=winner;
                                setCheckedCross(i,j);
                            }catch (Exception e){
                                System.out.println("Khong doi duoc chuoi");
                            }
                        }
                        if (!ser && count==0){
                            flag1 = letter.indexOf(" ");
                            flag2 = letter.indexOf(",");
                            flag3 = letter.indexOf("-");
                            try{
                                i = Integer.parseInt(letter.substring(0,flag1));
                                j = Integer.parseInt(letter.substring(flag1+1, flag2));
                                who = Integer.parseInt(letter.substring(flag2+1,flag3));
                                winner = Boolean.parseBoolean(letter.substring(flag3+1));
                                win=winner;
                                setCheckedCross(i,j);
                            }catch (Exception e){
                                System.out.println("Khong doi duoc chuoi");
                            }
                        }
                        unBlockMove();
                    }
                }
                // in ra ai thắng cho mỗi bàn dựa vào who
                String nameWhoWin="";
                if (who==0){
                    nameWhoWin = "O";
                }else{
                    nameWhoWin = "X";
                }
                JOptionPane.showMessageDialog(new JDialog(),nameWhoWin+ " is winner");
                pauseGame();
            }
        };
        waitLetter.start();
//        if (win==true){
//            waitLetter.destroy();
//        }
    }
    
    public void blockMove(){
        for (int i=0;i<r;i++)
            for (int j=0;j<c;j++){
                tick[i][j] = false;
            }
    }
    public void unBlockMove(){
        for (int i=0;i<r;i++)
            for(int j=0;j<c;j++){
                if (backup[i][j]==false){
                    tick[i][j]=true;
                }
            }
    }
    
    // xử lý chuyển form
    public void setMenuClass(JFrame a){
        this.intro = (IntroClass) a;
    }
    
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
                    send(i,j,1-count,!this.flag);
                    this.blockMove();
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
        btnSetting = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbMove = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnConnect = new javax.swing.JButton();
        txtNotify = new javax.swing.JTextField();
        btnUndo1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

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

        btnSetting.setBackground(new java.awt.Color(255, 255, 255));
        btnSetting.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        btnSetting.setForeground(new java.awt.Color(38, 208, 254));
        btnSetting.setText("Setting");
        btnSetting.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSettingMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        jLabel3.setText("Player move:");

        jLabel4.setFont(new java.awt.Font("Lato", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(38, 208, 254));
        jLabel4.setText("5 in line to win ...");

        lbMove.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        lbMove.setText("Unknown");

        jLabel6.setText("Copy right ....");

        btnConnect.setBackground(new java.awt.Color(255, 255, 255));
        btnConnect.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        btnConnect.setForeground(new java.awt.Color(38, 208, 254));
        btnConnect.setText("Connect");
        btnConnect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnConnectMouseClicked(evt);
            }
        });

        txtNotify.setText("None");

        btnUndo1.setBackground(new java.awt.Color(255, 255, 255));
        btnUndo1.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        btnUndo1.setForeground(new java.awt.Color(38, 208, 254));
        btnUndo1.setText("Menu");
        btnUndo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUndo1MouseClicked(evt);
            }
        });

        jTextField1.setText("127.0.0.1");

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
                            .addComponent(jLabel1)
                            .addGroup(pnBoardLayout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(jLabel4))
                            .addGroup(pnBoardLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel2))))
                    .addGroup(pnBoardLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(pnBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6)
                            .addGroup(pnBoardLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbMove))
                            .addComponent(txtNotify)
                            .addComponent(btnConnect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSetting, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNewGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnUndo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField1))))
                .addContainerGap(27, Short.MAX_VALUE))
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
                        .addGap(23, 23, 23)
                        .addComponent(btnNewGame)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUndo1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSetting)
                        .addGap(18, 18, 18)
                        .addGroup(pnBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lbMove))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNotify, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnConnect)
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
        if (online){
            if (!ser){
                this.blockMove();
            }
            if (win){
                win = false;
            }
        }
        if (count==0){
            lbMove.setText("O player");
        }else{
            lbMove.setText("X player");
        }
    }//GEN-LAST:event_btnNewGameMouseClicked

    private void btnSettingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSettingMouseClicked
        setting = new SettingClass();
        setting.setVisible(true);
        Set.SettingClass.classReturn=2;
        setting.setBackForm(this);
        this.setVisible(false);
//        setting = new SettingClass();
//        setting.setVisible(true);
//        this.dispose();
    }//GEN-LAST:event_btnSettingMouseClicked

    private void btnConnectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConnectMouseClicked
        this.checkConnectServer(); //kết nối client hoặc thông báo là server cập nhật lại ser
        try {
            this.createServer(); //sẽ là server nếu ser = true không thì bỏ qua
        } catch (IOException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        online = true;
        if (ser){
            txtNotify.setText("First move");
        }else{
            txtNotify.setText("Second move");
        }
    }//GEN-LAST:event_btnConnectMouseClicked

    private void btnUndo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUndo1MouseClicked
        this.intro.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnUndo1MouseClicked

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
    private javax.swing.JButton btnConnect;
    private javax.swing.JButton btnNewGame;
    private javax.swing.JButton btnSetting;
    private javax.swing.JButton btnUndo1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbMove;
    private javax.swing.JPanel pn1;
    private javax.swing.JPanel pnBoard;
    private javax.swing.JTextField txtNotify;
    // End of variables declaration//GEN-END:variables
}
