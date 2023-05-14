/*
 * Created by JFormDesigner on Tue Dec 21 17:51:30 CST 2021
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
public class TassignmentUI extends JFrame {
    public TassignmentUI() {
        initComponents();
        setVisible(true);
    }

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here

        String student_name = textField1.getText();
        String teacher_name = textField2.getText();
        String assignment = textArea1.getText();
        String s = "teacherassignmentfenpei,"+student_name+","+assignment+","+teacher_name+",";
        OutputStream outputStream = null;
        Socket socket = null;
        InputStream inputStream = null;
        BufferedWriter bufferedReader  = null;
        try {
            InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inetAddress ,8080);

            outputStream = socket.getOutputStream();
            outputStream.write(s.getBytes());
            System.out.println("老师客户端任务分配页面："+s);
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
        label2 = new JLabel();
        textField2 = new JTextField();
        label3 = new JLabel();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        button1 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u5b66\u751f\u59d3\u540d\uff1a");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 4f));
        contentPane.add(label1);
        label1.setBounds(120, 30, 100, 45);
        contentPane.add(textField1);
        textField1.setBounds(225, 30, 175, 40);

        //---- label2 ----
        label2.setText("\u5206\u914d\u8001\u5e08\u59d3\u540d\uff1a");
        label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 4f));
        contentPane.add(label2);
        label2.setBounds(90, 95, 180, 45);
        contentPane.add(textField2);
        textField2.setBounds(225, 95, 175, 40);

        //---- label3 ----
        label3.setText("\u4efb\u52a1\u4e8b\u9879\uff1a");
        label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 4f));
        contentPane.add(label3);
        label3.setBounds(120, 150, 100, 45);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(textArea1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(225, 160, 170, 130);

        //---- button1 ----
        button1.setText("\u786e\u5b9a");
        button1.setFont(button1.getFont().deriveFont(button1.getFont().getSize() + 4f));
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });
        contentPane.add(button1);
        button1.setBounds(260, 315, 100, 35);

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
        setSize(535, 410);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JLabel label3;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
