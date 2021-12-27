package Set;



import static Set.User.ten1;
import jaco.mp3.player.MP3Player;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.BorderFactory.createLineBorder;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.border.LineBorder;


public class MainClassTwo extends javax.swing.JFrame implements ActionListener{
    //Khởi tạo các đối tượng cơ bản
    private JButton b[][];
    private boolean tick[][];
    private int iUndo[];
    private int jUndo[];
    private int sizeUndo;
    private SettingClass setting;
    private User user;
    private boolean choosePlayer=false;
    private boolean start=false;
    private boolean flag=true;
    
    //chuyển form
    private IntroClass intro;
    
    //chơi online
    static boolean onl;
    private boolean back[][];
    private boolean server = false, online=false;
    private ServerSocket listener = null;
    private Socket socket = null;
    private BufferedReader is = null;
    private BufferedWriter os = null;
    private String IP = "localhost";
    //"10.3.52.16"
    private int PORT  = 3333;
    
    
    
    
    //thời gian và âm thanh
    private boolean time=true; //để xem người ta có muốn chơi theo thời gian không
    private Timer timer1,timer2;
    private int minute1,minute2,second1,second2,miniSecond1,miniSecond2;
    private boolean music=true; //để xem người ta có muốn chơi có âm thanh không
    private MP3Player audio;
    
    //Các biến dùng để truyền dữ liệu giữa 2 Frame
    static int r = 16,c = 20;
    static int count=0;
    static boolean ico = false;
    static String crossO = "O",crossX = "X";
    static Color colorO = Color.GREEN, colorX = Color.RED, colorBoard = Color.WHITE;
    static ImageIcon icoO = new ImageIcon(), icoX = new ImageIcon();
    public MainClassTwo() {
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
        start=false;
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
        this.resetTimer();
        audio = new MP3Player(getClass().getResource("/Audio/tiengTing.mp3"));
    }
    // xử lý trận đánh
    public void setCheckedCross(int i,int j){ //check lên trên ô
        if (!ico){
            //Không dùng icon
            if (count==0){
                b[i][j].setText(crossO);
                b[i][j].setForeground(colorO);
                tick[i][j]=false;
                
            }else{
                b[i][j].setText(crossX);
                b[i][j].setForeground(colorX);
                tick[i][j]=false;
               
            }
        }else{
            //Nếu sử dung icon
            if (count==0){
               b[i][j].setIcon(MainClassTwo.icoO);
               tick[i][j]=false;
            }else{
               b[i][j].setIcon(MainClassTwo.icoX);
               tick[i][j]=false;
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
        //this.resetTimer();
        this.start = false;
        timer1.stop();
        timer2.stop();
        this.resetAllBorder();
        this.setButtonEnabled(false); //tắt chức năng không sử dụng được
        this.btnSetting.setEnabled(true);
    }
    public void startGame(){
        for (int i=0;i<r;i++)
            for (int j=0;j<c;j++){
                b[i][j].setBackground(Color.WHITE);
                tick[i][j] =true;
                b[i][j].setText("");
                b[i][j].setIcon(null);
            }
        pn1.setBackground(colorBoard);
        this.btnSetting.setEnabled(false);
        this.flag = true;
        this.start=true;
        timer1.stop();
        timer2.stop();
        this.resetTimer();
        if (time==true){
            timer1.start();
        }
        this.resetAllBorder(); //O đánh trước
        if (count==0){
            btnPlayer1.setBorder(createLineBorder(new Color(38,208,254),3));
        }else{
            btnPlayer2.setBorder(createLineBorder(new Color(38,208,254),3));
        }
        this.setButtonEnabled(true);
    }
    public void undoGame(){ //undo đối với khi sử dụng chữ
        b[iUndo[sizeUndo-1]][jUndo[sizeUndo-1]].setText("");
        tick[iUndo[sizeUndo-1]][jUndo[sizeUndo-1]]=true;
        sizeUndo--;
    }
    public void undoGameIcon(){ //undo đối với khi sử dụng icon
        b[iUndo[sizeUndo-1]][jUndo[sizeUndo-1]].setIcon(null);
        tick[iUndo[sizeUndo-1]][jUndo[sizeUndo-1]]=true;
        sizeUndo--;
    }
    public String whoIsWinner(boolean t){ //xem coi ai thắng trong từng trường hợp có thể là trường hợp này A thắng trường hợp khác thì B thắng
        if (t){
            if (count==0){
                return lbName1.getText();
            }else{
                return lbName2.getText();
            }
        }else{
            if (count==1){
                return lbName1.getText();
            }else{
                return lbName2.getText();
            }
        }
    }
    public void setButtonEnabled(boolean kt){
       
        this.btnUndo.setEnabled(kt);
    }
    // hàm để chuyển dữ liệu vào trong trận đấu
    public void setPlayerData(){ //set các thông tin của người chơi
        this.choosePlayer = true;
        lbName1.setText(User.ten1);
        lbName2.setText(User.ten2);
        lbPoint1.setText("Score: "+ User.diem1);
        lbPoint2.setText("Score: "+ User.diem2);
    }
    public User getUser(){ //lấy JFrame User của MainClassTwo để giúp ẩn hiện phù hợp
        return this.user;
    }
    public boolean getChoosePlayer(){ //lấy giá trị boolean xem người ta đã chọn player chưa giúp ẩn hiện getUser phù hợp
        return this.choosePlayer;
    }
    
    // xử lý xem có sử dụng music với time hay không 
    public void setMusic(boolean a){
        this.music = a;
    }
    public void setTime(boolean a){
        this.time = a;
    }
    
    // xử lý thời gian trận đánh
    public Timer setTimePlayer(int mili, int m, int s, int k, JLabel lb){
        Timer timer = new Timer(mili,new ActionListener(){
            private int minute=m, second=s, miniSecond = k;
            private JLabel lbTimer=lb;
            private int dem=0;
            public void actionPerformed(ActionEvent ex){
                String phut = "0", giay = "", khac="";
                if (minute==0 && second==0){ //khi hết giờ
                    lbTimer.setText("00:00:00"); 
                    String winner; // hết giờ thì tuyên bố người kia thắng và cộng điểm
                    winner = whoIsWinner(false);
                    Win w = new Win(winner);
                    if (winner.equals(lbName1.getText())) {
                        scorePlus(true);
                    } else {
                        scorePlus(false);
                    }
                    //JOptionPane.showMessageDialog(new JDialog(),whoIsWinner(false) +" is winner"); //hết giờ thì người kia thắng
                    pauseGame();
                    flag = false;
                    return;
                }else{
                    if (miniSecond==0){ // khi số khắc đủ đã chạy hết 100 lần thì giảm giây
                        second--;
                        miniSecond=100;
                    }
                    miniSecond--;
                    if (second==0){ // khi số giây đã chạy đủ 60 lần thì giảm phút
                        minute--;
                        second=59;
                    }
                    phut = phut+minute; // xử lý để gán cho JLabel
                    if (miniSecond<10){
                        khac = "0"+miniSecond;
                    }else{
                        khac =Integer.toString(miniSecond);
                    }
                    if (second<10){
                        giay = "0"+second;
                    }else{
                        giay = Integer.toString(second);
                    }
                    lbTimer.setText(phut+":"+giay+":"+khac);
                }
            }
        });
        return timer; //dùng cách này để quản lý đối tượng Timer như thuộc tính có thể sử dụng mọi nơi trong lớp
    }
    
    
    public void resetTimer(){ // reset lại số thời gian ban đầu để bắt đầu lại trận đánh
        lbTimer1.setText("05:00:00");
        lbTimer2.setText("05:00:00");
        minute1 = minute2 = 5;
        second1 = second2 = 0;
        miniSecond1 = miniSecond2 = 100;
        timer1 = this.setTimePlayer(10,minute1,second1,miniSecond1,lbTimer1);
        timer2 = this.setTimePlayer(10,minute2,second2,miniSecond2,lbTimer2);
    }
    // xử lý phe đánh
    public void setBorderPlayer(){ // đổi border khi đến lượt người khác
        if (count==1){
            btnPlayer1.setBorder(createLineBorder(new Color(38,208,254),3));
        }else{
            btnPlayer2.setBorder(createLineBorder(new Color(38,208,254),3));
        }
    }
    public void resetBorderPlayer(){ // chuyển trạng thái ban đầu border khi hết lượt
        if (count==0){
            btnPlayer1.setBorder(createLineBorder(new Color(0,0,0),3));
        }else{
            btnPlayer2.setBorder(createLineBorder(new Color(0,0,0),3));
        }
    }
    public void resetAllBorder(){ // chuyển tất cả border về trạng thái ban đầu
        btnPlayer1.setBorder(createLineBorder(new Color(0,0,0),3));
        btnPlayer2.setBorder(createLineBorder(new Color(0,0,0),3));
    }
    // xử lý chuyển form - Mục tiêu là nhận vào cái đồi tượng form bên kia để thao tac setVisible
    public void setMenuClass(JFrame a){ 
        this.intro = (IntroClass) a;
    }
    public void setUserClass(JFrame a){
        this.user = (User) a;
    }
    public void continueTimer(){
        if (count==0){
            this.timer1.start();
        }else{
            this.timer2.start();
        }
    }
    // lấy các nút để set Enabled từ form khác
    public JButton getNewGame(){
        return this.btnNewGame;
    }
    
    // xử lý cộng điểm khi thắng cuộc - Vừa cộng điểm Label vừa cộng vào CSDL
    public void scorePlus(boolean kt){
        if (kt){
            User.diem1++;
            lbPoint1.setText("Score: "+User.diem1);
        }else{
            User.diem2++;
            lbPoint2.setText("Score: "+User.diem2);
        }
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try {
                Connection conn = DriverManager.getConnection("jdbc:sqlserver://JARVIS\\JARVIS:1433;databaseName=caro;user=sa;password=sa");
                System.out.println("Connection Successful");
                Statement st = conn.createStatement();
                st.executeUpdate("Update DB  set win1 = "+User.diem1+",win2 = "+User.diem2 + "where id = "+User.id+";");
                System.out.println("Cap nhat thanh cong");
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // xử lý xem bắt đầu hay chưa
    public boolean startThisGame(){
        return start;
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
                    winner = lbName1.getText();
                }else{
                    winner = lbName2.getText();
                }
                if (tick[i][j]){
                    this.setBorderPlayer();
                    this.resetBorderPlayer();
                    this.btnUndo.setEnabled(true);
                    Thread play = new Thread(){ //phát âm thanh bằng luồng khác giảm lag
                        public void run(){
                            audio.play();
                        }
                    };
                    if (music){
                        play.start();
                    }
                    this.setCheckedCross(i, j);
                    if (ico){
                        if (ActionClass.checkWinIcon(b, i, j, 5)){
                            //JOptionPane.showMessageDialog(new JDialog(),winner+ " is winner");
                            Win w = new Win(winner);
                            if (winner.equals(lbName1.getText()))
                                this.scorePlus(true);
                            else
                                 this.scorePlus(false);
                            w.setVisible(true);
                            this.pauseGame();
                            this.flag = false;
                        }
                    }else{
                        if (ActionClass.checkWinText(b, i, j, 5)){
                            //JOptionPane.showMessageDialog(new JDialog(),winner+ " is winner");
                            Win w = new Win(winner);
                            if (winner.equals(lbName1.getText()))
                                this.scorePlus(true);
                            else
                                 this.scorePlus(false);
                            w.setVisible(true);
                            this.pauseGame();
                            this.flag = false;
                        }
                    }
                    if (this.flag==true && time==true) //flag == true la chưa thắng còn đánh tiếp
                        if (count==0 ){
                            timer1.start();
                            timer2.stop();
                        }else{
                            timer1.stop();
                            timer2.start();
                        }
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
        btnNewGame = new javax.swing.JButton();
        btnUndo = new javax.swing.JButton();
        btnSetting = new javax.swing.JButton();
        lbPoint2 = new javax.swing.JLabel();
        lbTimer2 = new javax.swing.JLabel();
        lbPoint1 = new javax.swing.JLabel();
        lbTimer1 = new javax.swing.JLabel();
        btnPlayer2 = new javax.swing.JButton();
        btnPlayer1 = new javax.swing.JButton();
        btnMenu = new javax.swing.JButton();
        lbName1 = new javax.swing.JLabel();
        lbName2 = new javax.swing.JLabel();
        btnList = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CARO - X");
        setBackground(new java.awt.Color(0, 0, 0));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnBoard.setBackground(new java.awt.Color(255, 255, 255));
        pnBoard.setPreferredSize(new java.awt.Dimension(1589, 900));
        pnBoard.setRequestFocusEnabled(false);
        pnBoard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pn1.setBackground(new java.awt.Color(255, 255, 255));
        pn1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(38, 208, 254), 2));
        pn1.setName(""); // NOI18N
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

        pnBoard.add(pn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, -1, -1));

        btnNewGame.setBackground(new java.awt.Color(255, 255, 255));
        btnNewGame.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        btnNewGame.setForeground(new java.awt.Color(38, 208, 254));
        btnNewGame.setText("New Game");
        btnNewGame.setName(""); // NOI18N
        btnNewGame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNewGameMouseClicked(evt);
            }
        });
        pnBoard.add(btnNewGame, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 161, -1));

        btnUndo.setBackground(new java.awt.Color(255, 255, 255));
        btnUndo.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        btnUndo.setForeground(new java.awt.Color(38, 208, 254));
        btnUndo.setText("Undo");
        btnUndo.setName(""); // NOI18N
        btnUndo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUndoMouseClicked(evt);
            }
        });
        pnBoard.add(btnUndo, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 161, -1));

        btnSetting.setBackground(new java.awt.Color(255, 255, 255));
        btnSetting.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        btnSetting.setForeground(new java.awt.Color(38, 208, 254));
        btnSetting.setText("Setting");
        btnSetting.setName(""); // NOI18N
        btnSetting.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSettingMouseClicked(evt);
            }
        });
        pnBoard.add(btnSetting, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 161, -1));

        lbPoint2.setFont(new java.awt.Font("Lato", 1, 24)); // NOI18N
        lbPoint2.setText("Point");
        pnBoard.add(lbPoint2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 410, -1, -1));

        lbTimer2.setFont(new java.awt.Font("Lato", 1, 24)); // NOI18N
        lbTimer2.setText("05:00:00");
        pnBoard.add(lbTimer2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1400, 60, -1, -1));

        lbPoint1.setFont(new java.awt.Font("Lato", 1, 24)); // NOI18N
        lbPoint1.setText("Point");
        pnBoard.add(lbPoint1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, -1, -1));

        lbTimer1.setFont(new java.awt.Font("Lato", 1, 24)); // NOI18N
        lbTimer1.setText("05:00:00");
        pnBoard.add(lbTimer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, -1, -1));

        btnPlayer2.setBackground(new java.awt.Color(255, 255, 255));
        btnPlayer2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Avatar/001-user.png"))); // NOI18N
        btnPlayer2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        pnBoard.add(btnPlayer2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1323, 177, 250, 216));

        btnPlayer1.setBackground(new java.awt.Color(255, 255, 255));
        btnPlayer1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Avatar/001-user.png"))); // NOI18N
        btnPlayer1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        btnPlayer1.setPreferredSize(new java.awt.Dimension(250, 216));
        pnBoard.add(btnPlayer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        btnMenu.setBackground(new java.awt.Color(255, 255, 255));
        btnMenu.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        btnMenu.setForeground(new java.awt.Color(38, 208, 254));
        btnMenu.setText("Menu");
        btnMenu.setName(""); // NOI18N
        btnMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMenuMouseClicked(evt);
            }
        });
        pnBoard.add(btnMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 10, 161, -1));

        lbName1.setFont(new java.awt.Font("Lato", 1, 24)); // NOI18N
        lbName1.setText("Player 1");
        pnBoard.add(lbName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, -1, -1));

        lbName2.setFont(new java.awt.Font("Lato", 1, 24)); // NOI18N
        lbName2.setText("Player 2");
        pnBoard.add(lbName2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1400, 140, -1, -1));

        btnList.setBackground(new java.awt.Color(255, 255, 255));
        btnList.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        btnList.setForeground(new java.awt.Color(38, 208, 254));
        btnList.setText("List Player");
        btnList.setName(""); // NOI18N
        btnList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnListMouseClicked(evt);
            }
        });
        pnBoard.add(btnList, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 10, 161, -1));

        getContentPane().add(pnBoard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1600, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewGameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNewGameMouseClicked
        count=0;
        startGame();
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
            
        }
    }//GEN-LAST:event_btnUndoMouseClicked

    private void btnSettingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSettingMouseClicked
        setting = new SettingClass();
        setting.setVisible(true);
        this.user.setVisible(false);
        SettingClass.classReturn=1;
        setting.setBackForm(this);
        this.setVisible(false);
        this.timer1.stop(); // dừng tính giờ
        this.timer2.stop();
    }//GEN-LAST:event_btnSettingMouseClicked

    private void btnMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMenuMouseClicked
        intro.setVisible(true);
        this.setVisible(false);
        this.user.setVisible(false);
        this.timer1.stop(); // dừng tính giờ
        this.timer2.stop();
    }//GEN-LAST:event_btnMenuMouseClicked

    private void btnListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnListMouseClicked
       this.user.setTable();
       this.user.setVisible(true);
       this.timer1.stop();
       this.timer2.stop();
    }//GEN-LAST:event_btnListMouseClicked

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
            java.util.logging.Logger.getLogger(MainClassTwo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainClassTwo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainClassTwo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainClassTwo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainClassTwo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnList;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnNewGame;
    private javax.swing.JButton btnPlayer1;
    private javax.swing.JButton btnPlayer2;
    private javax.swing.JButton btnSetting;
    private javax.swing.JButton btnUndo;
    private javax.swing.JLabel lbName1;
    private javax.swing.JLabel lbName2;
    private javax.swing.JLabel lbPoint1;
    private javax.swing.JLabel lbPoint2;
    private javax.swing.JLabel lbTimer1;
    private javax.swing.JLabel lbTimer2;
    private javax.swing.JPanel pn1;
    private javax.swing.JPanel pnBoard;
    // End of variables declaration//GEN-END:variables
}
