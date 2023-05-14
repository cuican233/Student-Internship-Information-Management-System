/*
 * Created by JFormDesigner on Wed Dec 15 09:29:46 CST 2021
 */

package shixi.views.RLUI;

import java.awt.*;
import java.awt.event.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.*;

/**
 * @author Brainrain
 */
public class RegisterUI extends JFrame {
    public RegisterUI() {
        super("注册界面");
        initComponents();
        setVisible(true);

        textField1.setOpaque(false);
        passwordField2.setOpaque(false);
        passwordField1.setOpaque(false);
        button1.setContentAreaFilled(false);
    }
    private int b = 0;

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
            //获得信息
            String s1 = textField1.getText();
            char[] ps = passwordField2.getPassword();
            String s2 = new String(ps);
            String s3 = "register,"+s1+","+s2+",";
            //发送信息
            OutputStream outputStream = null;
            Socket socket = null;
            InputStream is = null;
            ByteArrayOutputStream baos = null;
            try {
                InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
                socket = new Socket(inetAddress ,8080);

                outputStream = socket.getOutputStream();
                outputStream.write(s3.getBytes());
                socket.shutdownOutput();
                System.out.println("注册客户端："+s3);
                //接收回应
                is = socket.getInputStream();
                byte [] buffer = new byte [20];
                baos = new ByteArrayOutputStream();
                while((b=is.read(buffer))!=-1) {
                    baos.write(buffer, 0, b);
                }
                String string = baos.toString();
                if(string.equals("yes")) {
                    JOptionPane.showMessageDialog(this,"恭喜你完成注册！", "系统提示",JOptionPane.INFORMATION_MESSAGE);
                    new LoginUI();
                    setVisible(false);

                }else if(string.equals("no")) {
                    JOptionPane.showMessageDialog(label1, "注册失败，请重新输入！","系统提示",JOptionPane.ERROR_MESSAGE);
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
                if(baos!=null) {
                    try {
                        baos.close();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
                if(is!=null) {
                    try {
                        is.close();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        passwordField2 = new JPasswordField();
        label3 = new JLabel();
        passwordField1 = new JPasswordField();
        button1 = new JButton();

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

        //---- label1 ----
        label1.setText("\u8d26\u53f7\uff1a");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 5f));
        layer2.add(label1);
        label1.setBounds(185, 95, 125, 75);
        layer2.add(textField1);
        textField1.setBounds(280, 115, 315, 35);

        //---- label2 ----
        label2.setText("\u5bc6\u7801\uff1a");
        label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 5f));
        layer2.add(label2);
        label2.setBounds(190, 195, 125, 75);
        layer2.add(passwordField2);
        passwordField2.setBounds(280, 210, 315, 35);

        //---- label3 ----
        label3.setText("\u786e\u8ba4\u5bc6\u7801\uff1a");
        label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 5f));
        layer2.add(label3);
        label3.setBounds(160, 285, 125, 75);
        layer2.add(passwordField1);
        passwordField1.setBounds(280, 305, 315, 35);

        //---- button1 ----
        button1.setText("\u6ce8\u518c");
        button1.setFont(button1.getFont().deriveFont(button1.getFont().getSize() + 5f));
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });
        layer2.add(button1);
        button1.setBounds(390, 375, 110, 35);

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
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JPasswordField passwordField2;
    private JLabel label3;
    private JPasswordField passwordField1;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public static void main(String[] args) {
        new RegisterUI();
    }
}
