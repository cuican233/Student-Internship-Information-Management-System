/*
 * Created by JFormDesigner on Tue Dec 21 20:25:33 CST 2021
 */

package shixi.views.teacher;

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
public class TeacherscoreUI extends JFrame {
    public TeacherscoreUI() {
        initComponents();
        setVisible(true);
    }

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here

        String employee_name = textField1.getText();
        String performance = textField2.getText();
        String results = textField3.getText();
        String score = textField4.getText();

        String s = "teacherscore,"+employee_name+","+performance+","+results+","+score+",";
        OutputStream outputStream = null;
        Socket socket = null;
        InputStream inputStream = null;
        BufferedWriter bufferedReader  = null;
        try {
            InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inetAddress ,8080);

            outputStream = socket.getOutputStream();
            outputStream.write(s.getBytes());
            System.out.println("老师用户端打分页面："+s);
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
                JOptionPane.showMessageDialog(this,"分配成功！", "系统提示",JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);

            }else if(string.equals("no")) {
                JOptionPane.showMessageDialog(this, "分配失败！","系统提示",JOptionPane.ERROR_MESSAGE);
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

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        textField1 = new JTextField();
        textField2 = new JTextField();
        label2 = new JLabel();
        label3 = new JLabel();
        textField3 = new JTextField();
        textField4 = new JTextField();
        label4 = new JLabel();
        button1 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u5b66\u751f\u59d3\u540d\uff1a");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 5f));
        contentPane.add(label1);
        label1.setBounds(85, 35, 145, 60);
        contentPane.add(textField1);
        textField1.setBounds(190, 45, 290, 40);
        contentPane.add(textField2);
        textField2.setBounds(190, 115, 290, 40);

        //---- label2 ----
        label2.setText("\u4efb\u52a1\u8868\u73b0\uff1a");
        label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 5f));
        contentPane.add(label2);
        label2.setBounds(85, 105, 145, 60);

        //---- label3 ----
        label3.setText("\u6210\u679c\uff1a");
        label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 5f));
        contentPane.add(label3);
        label3.setBounds(115, 175, 145, 60);
        contentPane.add(textField3);
        textField3.setBounds(190, 185, 290, 40);
        contentPane.add(textField4);
        textField4.setBounds(190, 255, 95, 45);

        //---- label4 ----
        label4.setText("\u5f97\u5206\uff1a");
        label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 5f));
        contentPane.add(label4);
        label4.setBounds(115, 245, 55, 60);

        //---- button1 ----
        button1.setText("\u6253\u5206");
        button1.setFont(button1.getFont().deriveFont(button1.getFont().getSize() + 5f));
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });
        contentPane.add(button1);
        button1.setBounds(375, 255, 95, 45);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        setSize(540, 405);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel label2;
    private JLabel label3;
    private JTextField textField3;
    private JTextField textField4;
    private JLabel label4;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
