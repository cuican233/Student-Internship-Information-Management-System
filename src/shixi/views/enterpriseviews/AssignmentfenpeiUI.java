/*
 * Created by JFormDesigner on Mon Dec 20 17:18:43 CST 2021
 */

package shixi.views.enterpriseviews;

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
public class AssignmentfenpeiUI extends JFrame {
    public AssignmentfenpeiUI() {
        initComponents();
        setVisible(true);
    }

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        String employee_name = textField1.getText();
        String assignment = textArea1.getText();
        String s = "assignmentfenpei,"+employee_name+","+assignment+",";
        OutputStream outputStream = null;
        Socket socket = null;
        InputStream inputStream = null;
        BufferedWriter bufferedReader  = null;
        try {
            InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inetAddress ,8080);

            outputStream = socket.getOutputStream();
            outputStream.write(s.getBytes());
            System.out.println("企业客户端任务分配页面："+s);
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
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        label2 = new JLabel();
        button1 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u5458\u5de5\u59d3\u540d\uff1a");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 5f));
        contentPane.add(label1);
        label1.setBounds(80, 30, 165, 40);
        contentPane.add(textField1);
        textField1.setBounds(190, 30, 200, 45);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(textArea1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(190, 105, 200, 140);

        //---- label2 ----
        label2.setText("\u5206\u914d\u7684\u5de5\u4f5c\u4e8b\u9879\uff1a");
        label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 5f));
        contentPane.add(label2);
        label2.setBounds(30, 95, 165, 45);

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
        button1.setBounds(230, 265, 110, 40);

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
        setSize(470, 360);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JTextField textField1;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    private JLabel label2;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
