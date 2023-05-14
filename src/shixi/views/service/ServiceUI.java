/*
 * Created by JFormDesigner on Wed Dec 15 15:15:00 CST 2021
 */

package shixi.views.service;

import shixi.utils.jdbcutil;
import shixi.views.RLUI.LoginThread;
import shixi.views.RLUI.RegisterThread;
import shixi.views.enterpriseviews.*;
import shixi.views.studentviews.ApplicationThread;
import shixi.views.studentviews.JianlixinxiThread;
import shixi.views.teacher.TassignmentfenpeiThread;
import shixi.views.teacher.TeacherscoreThread;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * @author Brainrain
 */
public class ServiceUI extends JFrame{

    private ArrayList<SocketAddress> clients=new ArrayList<>();
    private DatagramSocket server;


    jdbcutil jdbcutil = new jdbcutil();
    public ServiceUI() {
        super("服务端");
        initComponents();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        textArea1.setLineWrap(true);
        //字边界换行
        textArea1.setWrapStyleWord(true);
        textArea1.setFont(new Font("幼圆", Font.PLAIN, 16));
        ByteArrayOutputStream baos = null;
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream in = null;
        OutputStream out = null;
        while (true){
            try {
                serverSocket = new ServerSocket(8080);
                socket = serverSocket.accept();
                in = socket.getInputStream();
                baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[20];
                int len;
                while ((len = in.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                String s = baos.toString();
                String[] s1 = s.split(",");
                System.out.println("服务器：" + s);
                System.out.println("服务端响应：数据读取成功！");
                if (s1[0].equals("login")){//登陆
                    String s2 = s1[1];
                    String s3 = s1[2];
                    login(out,socket,s2,s3);
                }
                if (s1[0].equals("register")){//注册
                    String s2 = s1[1];
                    String s3 = s1[2];
                    register(out,socket,s2,s3);
                }
                if(s1[0].equals("zhaopinfabu")){
                    String s2 = s1[1];
                    String s3 = s1[2];
                    String s4 = s1[3];
                    String s5 = s1[4];
                    String s6 = s1[5];
                    zhaopinfabu(out,socket,s2,s3,s4,s5,s6);
                }
                if(s1[0].equals("zhaopinshenqing")){
                    String s2 = s1[1];
                    String s3 = s1[2];
                    String s4 = s1[3];
                    String s5 = s1[4];
                    zhaopinshenqing(out,socket,s2,s3,s4,s5);
                }
                if(s1[0].equals("jianlixinxi")){
                    String s2 = s1[1];
                    String s3 = s1[2];
                    String s4 = s1[3];
                    String s5 = s1[4];
                    String s6 = s1[5];
                    jianlixinxi(out,socket,s2,s3,s4,s5,s6);
                }
                if(s1[0].equals("zhaopin")){
                    String s2 = s1[1];
                    String s3 = s1[2];
                    pinyong(out,socket,s2,s3);
                }
                if(s1[0].equals("assignmentfenpei")){
                    String s2 = s1[1];
                    String s3 = s1[2];
                    assignmentfenpei(out,socket,s2,s3);
                }
                if(s1[0].equals("rizhi")){
                    String s2 = s1[1];
                    String s3 = s1[2];
                    String s4 = s1[3];
                    String s5 = s1[4];
                    rizhi(out,socket,s2,s3,s4,s5);
                }
                if(s1[0].equals("score")){
                    String s2 = s1[1];
                    String s3 = s1[2];
                    String s4 = s1[3];
                    String s5 = s1[4];
                    score(out,socket,s2,s3,s4,s5);
                }
                if(s1[0].equals("teacherassignmentfenpei")){
                    String s2 = s1[1];
                    String s3 = s1[2];
                    String s4 = s1[3];
                    teacherassignmentfenpei(out,socket,s2,s3,s4);
                }
                if(s1[0].equals("teacherscore")){
                    String s2 = s1[1];
                    String s3 = s1[2];
                    String s4 = s1[3];
                    String s5 = s1[4];
                    teacherscore(out,socket,s2,s3,s4,s5);
                }
                if(s1[0].equals("jianlishangchuan")){
                    jianlishangchuan();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    baos.close();
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void login(OutputStream out,Socket socket,String s2, String s3){
        new Thread(new LoginThread(s2,s3)).start();
        if(true){
            try {
                out = socket.getOutputStream();
                out.write("yes".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            try {
                out = socket.getOutputStream();
                out.write("no".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void register(OutputStream out,Socket socket,String s2, String s3){
        new Thread(new RegisterThread(s2,s3)).start();
        if(true){
            try {
                out = socket.getOutputStream();
                out.write("yes".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            try {
                out = socket.getOutputStream();
                out.write("no".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void zhaopinfabu(OutputStream out,Socket socket,String s2, String s3,String s4,String s5,String s6){
        new Thread(new ZhaopinfabuThread(s2,s3,s4,s5,s6)).start();
        if(true){
            try {
                out = socket.getOutputStream();
                out.write("yes".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            try {
                out = socket.getOutputStream();
                out.write("no".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void  zhaopinshenqing(OutputStream out,Socket socket,String s2, String s3,String s4,String s5){
        new Thread(new ApplicationThread(s2,s3,s4,s5)).start();
        if(true){
            try {
                out = socket.getOutputStream();
                out.write("yes".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            try {
                out = socket.getOutputStream();
                out.write("no".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void jianlixinxi(OutputStream out,Socket socket,String s2, String s3, String s4, String s5, String s6){
        new Thread(new JianlixinxiThread(s2,s3,s4,s5,s6)).start();
        if(true){
            try {
                out = socket.getOutputStream();
                out.write("yes".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            try {
                out = socket.getOutputStream();
                out.write("no".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void pinyong(OutputStream out,Socket socket,String s2, String s3){
        PinyongThread p = new PinyongThread(s2,s3);
        Thread thread = new Thread(p);
        thread.start();
        if(true){
            try {
                out = socket.getOutputStream();
                out.write("yes".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            try {
                out = socket.getOutputStream();
                out.write("no".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void assignmentfenpei(OutputStream out,Socket socket,String s2, String s3){
        new Thread(new AssignmentfenpeiThread(s2,s3)).start();
        if(true){
            try {
                out = socket.getOutputStream();
                out.write("yes".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            try {
                out = socket.getOutputStream();
                out.write("no".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void rizhi(OutputStream out,Socket socket,String s2, String s3,String s4,String s5){
        new Thread(new RizhiThread(s2,s3,s4,s5)).start();
        if(true){
            try {
                out = socket.getOutputStream();
                out.write("yes".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            try {
                out = socket.getOutputStream();
                out.write("no".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void score(OutputStream out,Socket socket,String s2, String s3,String s4,String s5){
        new Thread(new ScoreThread(s2,s3,s4,s5)).start();
        if(true){
            try {
                out = socket.getOutputStream();
                out.write("yes".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            try {
                out = socket.getOutputStream();
                out.write("no".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void teacherassignmentfenpei(OutputStream out,Socket socket,String s2, String s3, String s4){
        new Thread(new TassignmentfenpeiThread(s2,s3,s4)).start();
        if(true){
            try {
                out = socket.getOutputStream();
                out.write("yes".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            try {
                out = socket.getOutputStream();
                out.write("no".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void teacherscore(OutputStream out,Socket socket,String s2, String s3,String s4,String s5){
        new Thread(new TeacherscoreThread(s2,s3,s4,s5)).start();
        if(true){
            try {
                out = socket.getOutputStream();
                out.write("yes".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            try {
                out = socket.getOutputStream();
                out.write("no".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void jianlishangchuan(){
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(8899);
            Socket s = ss.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\dest.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                // 读到886结束输入
                if ("886".equals(line)) {
                    break;
                }
                // 否则进行写数据操作
                bw.write(line);
                bw.newLine();
                bw.flush();
            }
            // 给出反馈
            BufferedWriter bwServer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            bwServer.write("文件上传成功");
            bwServer.newLine();
            bwServer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();

        //======== this ========
        JLayeredPane layer2;
        layer2 = new JLayeredPane() {
            public void paintComponent(Graphics g) {//重写绘制面板的方法
                super.paintComponent(g);
                ImageIcon image = new ImageIcon("D:\\233\\实训项目\\大二上java实训\\CC.jpg");
                image.setImage(image.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_AREA_AVERAGING));
                g.drawImage(image.getImage(), 0, 0, this); //重新绘制面板
            }
        };
        this.add(layer2);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(textArea1);
        }
        layer2.add(scrollPane1);
        scrollPane1.setBounds(75, 80, 625, 380);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < layer2.getComponentCount(); i++) {
                Rectangle bounds = layer2.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = layer2.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            layer2.setMinimumSize(preferredSize);
            layer2.setPreferredSize(preferredSize);
        }
        setSize(765, 565);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JScrollPane scrollPane1;
    public static JTextArea textArea1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public static void main(String[] args) {
        new ServiceUI();
    }
}
