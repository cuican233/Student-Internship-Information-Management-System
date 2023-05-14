/*
 * Created by JFormDesigner on Mon Dec 20 15:11:31 CST 2021
 */

package shixi.views.enterpriseviews;

import shixi.utils.jdbcutil;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author Brainrain
 */
public class JianlichakanUI extends JFrame {
    jdbcutil jdbcutil;
    public JianlichakanUI() {
        initComponents();
        setVisible(true);
        jdbcutil = new jdbcutil();
        jdbcutil.connect();
        button1.setBackground(Color.white);
    }

    private void button2MouseClicked(MouseEvent e) {
        // TODO add your code here
        Vector rowDate, columnNames;
        String company = textField1.getText();
        String employee = textField2.getText();
        columnNames = new Vector();
        columnNames.add("姓名");
        columnNames.add("性别");
        columnNames.add("毕业院校及专业");
        columnNames.add("自我评价及所获荣誉");
        rowDate = new Vector();
        try {
            String sql = "SELECT *FROM resume where name = (select employee from recruitment where company='"+company+"' and employee = '"+employee+"')";
            ResultSet rs = jdbcutil.getResultSet(sql);
            if (rs.next()) {
                Vector hang = new Vector();
                hang.add(rs.getString(1));
                hang.add(rs.getString(2));
                hang.add(rs.getString(3));
                hang.add(rs.getString(4));
                rowDate.add(hang);
            }
            String s = rs.getString(5);
            System.out.println(s);
            ImageIcon II = new ImageIcon(s);
            Image image=II.getImage().getScaledInstance(button1.getWidth(),button1.getHeight(),Image.SCALE_DEFAULT);
            II=new ImageIcon(image);
            button1.setIcon(II);
        } catch (SQLException d) {
            d.printStackTrace();
        }
        DefaultTableModel model = new DefaultTableModel(rowDate,columnNames);
        table1.setRowHeight(100);
        table1.setModel(model);

    }

    private void button3MouseClicked(MouseEvent e) {
        // TODO add your code here
        String company = textField1.getText();
        String employee = textField2.getText();
        String s = "zhaopin,"+company+","+employee+",";
        OutputStream outputStream = null;
        Socket socket = null;
        InputStream inputStream = null;
        BufferedWriter bufferedReader  = null;
        try {
            InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inetAddress ,8080);

            outputStream = socket.getOutputStream();
            outputStream.write(s.getBytes());
            System.out.println("企业客户端聘用页面："+s);
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
                JOptionPane.showMessageDialog(this,"聘用成功！", "系统提示",JOptionPane.INFORMATION_MESSAGE);
            }else if(string.equals("no")) {
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
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        button1 = new JButton();
        textField1 = new JTextField();
        label1 = new JLabel();
        label2 = new JLabel();
        textField2 = new JTextField();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button2 = new JButton();
        button3 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- button1 ----
        button1.setText("\u56fe\u7247\u663e\u793a\u5904");
        button1.setFont(button1.getFont().deriveFont(button1.getFont().getSize() + 4f));
        contentPane.add(button1);
        button1.setBounds(530, 45, 190, 185);
        contentPane.add(textField1);
        textField1.setBounds(235, 50, 255, 40);

        //---- label1 ----
        label1.setText("\u8f93\u5165\u516c\u53f8\u540d\uff1a");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 6f));
        contentPane.add(label1);
        label1.setBounds(95, 40, 175, 55);

        //---- label2 ----
        label2.setText("\u8f93\u5165\u7533\u8bf7\u4eba\u540d\u79f0\uff1a");
        label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 6f));
        contentPane.add(label2);
        label2.setBounds(60, 120, 180, 55);
        contentPane.add(textField2);
        textField2.setBounds(235, 130, 255, 40);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(40, 255, 685, 125);

        //---- button2 ----
        button2.setText("\u67e5\u770b");
        button2.setFont(button2.getFont().deriveFont(button2.getFont().getSize() + 5f));
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button2MouseClicked(e);
            }
        });
        contentPane.add(button2);
        button2.setBounds(300, 200, 120, 40);

        //---- button3 ----
        button3.setText("\u8058\u7528");
        button3.setFont(button3.getFont().deriveFont(button3.getFont().getSize() + 5f));
        button3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button3MouseClicked(e);
            }
        });
        contentPane.add(button3);
        button3.setBounds(300, 400, 120, 40);

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
        setSize(765, 565);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton button1;
    private JTextField textField1;
    private JLabel label1;
    private JLabel label2;
    private JTextField textField2;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button2;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        new JianlichakanUI();
    }
}
