/*
 * Created by JFormDesigner on Wed Dec 15 09:09:49 CST 2021
 */

package shixi.views.RLUI;

import shixi.views.administrators.AdministratorsUI;
import shixi.views.enterpriseviews.EnterpriseUI;
import shixi.views.studentviews.StudentUI;
import shixi.views.teacher.TeacherUI;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.*;

/**
 * @author Brainrain
 */
public class LoginUI extends JFrame {
    public static String username;
    public LoginUI() {
        super("登陆界面");
        initComponents();
        setVisible(true);
        textField2.setOpaque(false);
        passwordField2.setOpaque(false);
        button1.setContentAreaFilled(false) ;
        button2.setContentAreaFilled(false) ;
        radioButton1.setContentAreaFilled(false);
        radioButton2.setContentAreaFilled(false);
        radioButton3.setContentAreaFilled(false);
        radioButton4.setContentAreaFilled(false);

        this.getLayeredPane().setLayout(null);		//把分层面板的布局置空

    }

    private void button2MouseClicked(MouseEvent e) {
        // TODO add your code here
        if(e.getSource()==button2) {
            //进入注册界面
            new RegisterUI();
            setVisible(false);
        }
    }

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        if(e.getSource()==button1) {
            String s1 = textField2.getText();
            char[] ps = passwordField2.getPassword();
            String s2 = new String(ps);
            String s3 = "login,"+s1+","+s2+",";
            //发送信息
            OutputStream outputStream = null;
            Socket socket = null;
            InputStream inputStream = null;
            BufferedWriter bufferedReader  = null;
            try {
                InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
                socket = new Socket(inetAddress ,8080);

                outputStream = socket.getOutputStream();
                outputStream.write(s3.getBytes());
                System.out.println("登录客户端："+s3);
                socket.shutdownOutput();
                //接收信息
                inputStream = socket.getInputStream();
                byte [] buffer = new byte[20];
                int len;
                String string = null ;
                while ((len = inputStream.read(buffer))!=-1) {
                    string = new String(buffer,0,len);
                }
                if(string.equals("yes")) {
                    JOptionPane.showMessageDialog(this,"登陆成功", "系统提示",JOptionPane.INFORMATION_MESSAGE);
                    if(radioButton1.isSelected()){
                        new StudentUI();
                        username = s1;
                    }
                    if(radioButton2.isSelected()){
                        new EnterpriseUI();
                    }
                    if(radioButton3.isSelected()){
                        new TeacherUI();
                    }
                    if(radioButton4.isSelected()){
                        new AdministratorsUI();
                    }
                    this.setVisible(false);
                }else if(string.equals("no1")) {
                    JOptionPane.showMessageDialog(this, "您输入的账号或密码有误，请重新输入！","系统提示",JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }finally {
                if(outputStream !=null) {
                    try {
                        outputStream.close();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
                if(socket!=null) {
                    try {
                        socket.close();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
                if(bufferedReader!=null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                }
            }

        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        textField2 = new JTextField();
        label5 = new JLabel();
        label6 = new JLabel();
        passwordField2 = new JPasswordField();
        button1 = new JButton();
        button2 = new JButton();
        radioButton1 = new JRadioButton();
        radioButton2 = new JRadioButton();
        radioButton3 = new JRadioButton();
        radioButton4 = new JRadioButton();
        label7 = new JLabel();

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
        layer2.setLayout(null);
        layer2.add(textField2);
        textField2.setBounds(250, 160, 315, 35);

        //---- label5 ----
        label5.setText("\u8d26\u53f7\uff1a");
        label5.setFont(label5.getFont().deriveFont(label5.getFont().getSize() + 5f));
        label5.setBackground(Color.white);
        layer2.add(label5);
        label5.setBounds(170, 145, 90, 60);

        //---- label6 ----
        label6.setText("\u5bc6\u7801\uff1a");
        label6.setFont(label6.getFont().deriveFont(label6.getFont().getSize() + 5f));
        layer2.add(label6);
        label6.setBounds(175, 235, 90, 60);
        layer2.add(passwordField2);
        passwordField2.setBounds(250, 245, 315, 35);

        //---- button1 ----
        button1.setText("\u767b\u9646");
        button1.setFont(button1.getFont().deriveFont(button1.getFont().getSize() + 7f));
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });
        layer2.add(button1);
        button1.setBounds(255, 390, 105, 40);

        //---- button2 ----
        button2.setText("\u6ce8\u518c");
        button2.setFont(button2.getFont().deriveFont(button2.getFont().getSize() + 7f));
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button2MouseClicked(e);
            }
        });
        layer2.add(button2);
        button2.setBounds(450, 390, 105, 40);

        //---- radioButton1 ----
        radioButton1.setText("\u5b66\u751f");
        radioButton1.setFont(radioButton1.getFont().deriveFont(radioButton1.getFont().getSize() + 5f));
        layer2.add(radioButton1);
        radioButton1.setBounds(250, 320, 85, 40);

        //---- radioButton2 ----
        radioButton2.setText("\u4f01\u4e1a");
        radioButton2.setFont(radioButton2.getFont().deriveFont(radioButton2.getFont().getSize() + 5f));
        layer2.add(radioButton2);
        radioButton2.setBounds(335, 325, 65, 33);

        //---- radioButton3 ----
        radioButton3.setText("\u8001\u5e08");
        radioButton3.setFont(radioButton3.getFont().deriveFont(radioButton3.getFont().getSize() + 5f));
        layer2.add(radioButton3);
        radioButton3.setBounds(new Rectangle(new Point(415, 330), radioButton3.getPreferredSize()));

        //---- radioButton4 ----
        radioButton4.setText("\u7ba1\u7406\u5458");
        radioButton4.setFont(radioButton4.getFont().deriveFont(radioButton4.getFont().getSize() + 5f));
        layer2.add(radioButton4);
        radioButton4.setBounds(new Rectangle(new Point(495, 330), radioButton4.getPreferredSize()));

        //---- label7 ----
        label7.setText("\u9009\u62e9\u8eab\u4efd\uff1a");
        label7.setFont(label7.getFont().deriveFont(label7.getFont().getSize() + 5f));
        layer2.add(label7);
        label7.setBounds(140, 310, 90, 60);

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
    private JTextField textField2;
    private JLabel label5;
    private JLabel label6;
    private JPasswordField passwordField2;
    private JButton button1;
    private JButton button2;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JLabel label7;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
