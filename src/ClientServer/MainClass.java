package ClientServer;

import Set.*;
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

public class MainClass extends javax.swing.JFrame implements ActionListener {

    private JButton b[][];
    private boolean tick[][];
    private int iUndo[];
    private int jUndo[];
    private int sizeUndo;
    private Set.SettingClass setting;
    private IntroClass intro;
    private boolean flag = true;
    public static int r = 16, c = 20;
    public static int count = 0;
    public static boolean ico = false;
    public static String crossO = "O", crossX = "X";
    public static Color colorO = Color.GREEN, colorX = Color.RED, colorBoard = Color.WHITE;
    public static ImageIcon icoO = new ImageIcon(), icoX = new ImageIcon();

    //Chơi online 
    private boolean back[][];
    private boolean server = false, online = false;
    private ServerSocket listener = null;
    private Socket socket = null;
    private BufferedReader is = null;
    private BufferedWriter os = null;
    private String IP = "localhost";
    //"10.3.52.16"
    private int PORT = 3333;

    public MainClass() {
        initComponents();
        initBoard();
    }

    // khởi tạo bàn cờ
    public void initBoard() {
        b = new JButton[r][c];
        tick = new boolean[r][c];
        back = new boolean[r][c];//online
        iUndo = new int[r * c];
        jUndo = new int[r * c];
        sizeUndo = 0;
        int i, j;
        int x = 0, y = 0;
        for (i = 0; i < r; i++) {
            for (j = 0; j < c; j++) {
                // khởi tạo tick & caro
                b[i][j] = new JButton();
                b[i][j].setSize(50, 50);
                b[i][j].setVisible(true);
                b[i][j].setLocation(new Point(x, y));
                b[i][j].setBackground(Color.GRAY);
                b[i][j].setFont(new Font("Lato", 1, 28));
                b[i][j].setActionCommand(i + " " + j);
                b[i][j].addActionListener(this);
                pn1.add(b[i][j]);
                tick[i][j] = false;
                back[i][j] = false;
                x += 50;
                // khởi tạo biến mảng undo
                iUndo[j] = jUndo[j] = -1;
            }
            y += 50;
            x = 0;
        }
    }

    // xử lý trận đánh
    public void setCheckedCross(int i, int j) {
        if (!ico) {
            //Không dùng icon
            if (count == 0) {
                b[i][j].setText(crossO);
                b[i][j].setForeground(colorO);
                tick[i][j] = false;
                lbMove.setText("X player");
            } else {
                b[i][j].setText(crossX);
                b[i][j].setForeground(colorX);
                tick[i][j] = false;
                lbMove.setText("O player");
            }
        } else {
            //Nếu sử dung icon
            if (count == 0) {
                b[i][j].setIcon(MainClass.icoO);
                tick[i][j] = false; //online
            } else {
                b[i][j].setIcon(MainClass.icoX);
                tick[i][j] = false;
            }
        }
        iUndo[sizeUndo] = i;
        jUndo[sizeUndo] = j;
        sizeUndo++;
        count = 1 - count;
    }

    public void pauseGame() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                b[i][j].setBackground(Color.GRAY);
                tick[i][j] = false;
            }
        }
        this.btnSetting.setEnabled(true);
    }

    public void startGame() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                b[i][j].setBackground(Color.WHITE);
                tick[i][j] = true;
                b[i][j].setText("");
                b[i][j].setIcon(null);
//                if (!ico) {
//                    b[i][j].setText("");
//                } else {
//                    b[i][j].setIcon(null);
//                    b[i][j].setIcon(new ImageIcon());
//                }
            }
        }
        this.btnSetting.setEnabled(false);
        pn1.setBackground(colorBoard);
        this.flag = true;
        if (online) { //online
            if (!server) {
                this.blockPlayer();
                this.resetBack();
            }
            this.read();
        }

    }

    public void undoGame() {
        b[iUndo[sizeUndo - 1]][jUndo[sizeUndo - 1]].setText("");
        sizeUndo--;
    }

    public void undoGameIcon() {
        b[iUndo[sizeUndo - 1]][jUndo[sizeUndo - 1]].setIcon(null);
        sizeUndo--;
    }

    // xử lý chơi online
    public void connetor() { //online
        try {
            socket = new Socket(IP, PORT);
            System.out.println("Client: Connect Server OK");
            is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            os = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException ex) {
            try {
                listener = new ServerSocket(PORT);
                Thread wait = new Thread() {
                    public void run() {
                        try {
                            socket = listener.accept();
                            System.out.println("Server: Connect Client OK");
                            is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            os = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                            server = true;
                        } catch (IOException ex1) {
                            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }
                };
                wait.start();
            } catch (IOException ex1) {
                Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex1);
            }

        } finally {
            online = true;
        }

    }

    public void send(int i, int j, int who, boolean winner) { //online
        String kq = i + " " + j + "," + who + "-" + winner;
        try {
            os.write(kq);
            os.newLine();
            os.flush();
            System.out.println("Has sent: " + kq);
        } catch (IOException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void read() { //online
        Thread wait = new Thread() {
            private boolean winner = false;

            public void run() {
                int i, j, who = 0;
                while (true) {
                    try {
                        String st = is.readLine();
                        int vt1 = st.indexOf(" ");
                        int vt2 = st.indexOf(",");
                        int vt3 = st.indexOf("-");

                        boolean win = false;
                        try {
                            i = Integer.parseInt(st.substring(0, vt1));
                            j = Integer.parseInt(st.substring(vt1 + 1, vt2));
                            who = Integer.parseInt(st.substring(vt2 + 1, vt3));
                            win = Boolean.parseBoolean(st.substring(vt3 + 1));
                            winner = win;
                            setCheckedCross(i, j);
                            unBlockPlayer();
                        } catch (Exception ex1) {
                            System.out.println("Khong dich duoc chuoi");
                        }
                    } catch (IOException ex) {
                        // tắt trước một cái còn đâu mà đọc
                    }
                    if (winner == true) {
                        //JOptionPane.showMessageDialog(new JDialog(), winner + " is winner");
                        String t = new String();
                        if (who == 1) {
                            t = "X";
                        } else {
                            t = "O";
                        }
                        Win w = new Win(t);
                        w.setVisible(true);
                        pauseGame();
                        flag = false;
                        break;
                    }
                }

            }
        };
        wait.start();

    }

    public void blockPlayer() { //online
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (tick[i][j] == false) {
                    back[i][j] = true;
                }
                tick[i][j] = false;
            }
        }
    }

    public void unBlockPlayer() { //online
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (back[i][j] == false) {
                    tick[i][j] = true;
                }
            }
        }
    }

    public void resetBack() { //online
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                back[i][j] = false;
            }
        }
    }

    // xử lý chuyển form
    public void setMenuClass(JFrame a) {
        this.intro = (IntroClass) a;
    }
    // lấy nút newGame để form khác set Enabled
    public JButton getNewGame(){
        return this.btnNewGame;
    }

    // xử lý sự kiện trận đánh
    public void actionPerformed(ActionEvent e) {
        try {
            String evt = new String(e.getActionCommand());
            int flag = evt.indexOf(" ");
            int i = Integer.parseInt(evt.substring(0, flag));
            int j = Integer.parseInt(evt.substring(flag + 1));
            String winner = new String();
            if (count == 0) {
                winner = "O";
            } else {
                winner = "X";
            }
            if (tick[i][j]) {
                this.setCheckedCross(i, j);
                if (online == true) //online
                {
                    this.blockPlayer();
                }
                if (ico) {
                    if (ActionClass.checkWinIcon(b, i, j, 5)) {
                        //JOptionPane.showMessageDialog(new JDialog(), winner + " is winner");
                        Win w = new Win(winner);
                        w.setVisible(true);
                        this.pauseGame();
                        this.flag = false;
                    }
                } else {
                    if (ActionClass.checkWinText(b, i, j, 5)) {
                        //JOptionPane.showMessageDialog(new JDialog(), winner + " is winner");
                        Win w = new Win(winner);
                        w.setVisible(true);
                        this.pauseGame();
                        this.flag = false;
                    }
                }
                if (online) { //online
                    this.send(i, j, 1 - count, !this.flag);
                }
            }
        } catch (Exception ex) {
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
        btnUndo1 = new javax.swing.JButton();
        txtIP = new javax.swing.JTextField();
        lbConnect = new javax.swing.JLabel();

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

        btnUndo1.setBackground(new java.awt.Color(255, 255, 255));
        btnUndo1.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        btnUndo1.setForeground(new java.awt.Color(38, 208, 254));
        btnUndo1.setText("Menu");
        btnUndo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUndo1MouseClicked(evt);
            }
        });

        txtIP.setText("127.0.0.1");

        lbConnect.setText("Connect Status: None");

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
                                .addGap(10, 10, 10)
                                .addComponent(jLabel2))
                            .addGroup(pnBoardLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(pnBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbConnect)
                                    .addGroup(pnBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel4)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnBoardLayout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lbMove))
                                        .addComponent(btnConnect, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnSetting, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnUndo1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnNewGame, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtIP, javax.swing.GroupLayout.Alignment.LEADING))))))
                    .addGroup(pnBoardLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel6)))
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
                        .addGap(18, 18, 18)
                        .addComponent(btnNewGame)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUndo1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSetting)
                        .addGap(20, 20, 20)
                        .addGroup(pnBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lbMove))
                        .addGap(18, 18, 18)
                        .addComponent(txtIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbConnect)
                        .addGap(8, 8, 8)
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
        count = 0;
    }//GEN-LAST:event_btnNewGameMouseClicked

    private void btnSettingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSettingMouseClicked
        setting = new Set.SettingClass();
        setting.setVisible(true);
        Set.SettingClass.classReturn = 2;
        setting.setBackForm(this);
        this.setVisible(false);
//        setting = new SettingClass();
//        setting.setVisible(true);
//        this.dispose();
    }//GEN-LAST:event_btnSettingMouseClicked

    private void btnConnectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConnectMouseClicked
        this.IP = txtIP.getText();
        this.connetor(); //online
        lbConnect.setText("Connect Status: Connected");
        this.btnNewGame.setEnabled(true);
    }//GEN-LAST:event_btnConnectMouseClicked

    private void btnUndo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUndo1MouseClicked
        intro.setVisible(true);
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
    private javax.swing.JLabel lbConnect;
    private javax.swing.JLabel lbMove;
    private javax.swing.JPanel pn1;
    private javax.swing.JPanel pnBoard;
    private javax.swing.JTextField txtIP;
    // End of variables declaration//GEN-END:variables
}
