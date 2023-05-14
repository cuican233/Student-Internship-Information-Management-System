/*
 * Created by JFormDesigner on Wed Dec 15 17:24:16 CST 2021
 */

package shixi.views.studentviews;

import javax.swing.plaf.*;
import shixi.utils.jdbcutil;
import shixi.views.RLUI.LoginUI;
import shixi.views.liaotian.ServicetaolunUI;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 * @author Brainrain
 */
public class StudentUI extends JFrame {
    private JFileChooser chooser;
    private File file = null;
    private jdbcutil jdbcutil;
    private String path = null;
    private int b = 0;
    private DatagramSocket client;
    private String read = null;

    

    public StudentUI() {
        super("学生界面");
        initComponents();
        setVisible(true);
        //可改变窗体大小
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        button4.setBackground(Color.white);
        button1.setBackground(Color.white);
        jdbcutil = new jdbcutil();
        jdbcutil.connect();
        tabbedPane1.setOpaque(false);
        panel1.setOpaque(false);
        panel2.setOpaque(false);
        panel3.setOpaque(false);
        panel4.setOpaque(false);
        panel5.setOpaque(false);
        panel6.setOpaque(false);
        button1.setContentAreaFilled(false);
        button2.setContentAreaFilled(false);
        button3.setContentAreaFilled(false);
        button4.setContentAreaFilled(false);
        button5.setContentAreaFilled(false);
        button6.setContentAreaFilled(false);
        button7.setContentAreaFilled(false);
        button8.setContentAreaFilled(false);
        button9.setContentAreaFilled(false);
        button10.setContentAreaFilled(false);
        button11.setContentAreaFilled(false);
        button12.setContentAreaFilled(false);
        button13.setContentAreaFilled(false);
        textField1.setOpaque(false);
        textField2.setOpaque(false);
        textField3.setOpaque(false);
        textField4.setOpaque(false);
        textField5.setOpaque(false);
        textField6.setOpaque(false);
        textField7.setOpaque(false);
        textField8.setOpaque(false);
        textArea1.setOpaque(false);
        textArea2.setOpaque(false);
        textField6.setOpaque(false);
        radioButtonMenuItem1.setContentAreaFilled(false);
        radioButtonMenuItem2.setContentAreaFilled(false);
        radioButtonMenuItem1.setOpaque(false);
        radioButtonMenuItem2.setOpaque(false);
        this.getLayeredPane().setLayout(null);		//把分层面板的布局置空
    }

    private void button5MouseClicked(MouseEvent e) {
        // TODO add your code here
        //使用给定的文件作为路径构造JFileChooser实例
        chooser = new JFileChooser("D:\\");
        //切换当前进程的当前工作目录
        chooser.setCurrentDirectory(new File("D:\\"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg", "png");
        chooser.setFileFilter(filter);
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
            String name = file.getPath();
            path = name;
            ImageIcon II = new ImageIcon(name);
            Image image=II.getImage().getScaledInstance(button4.getWidth(),button4.getHeight(),Image.SCALE_DEFAULT);
            II=new ImageIcon(image);
            button4.setIcon(II);
        }
    }

    private void button6MouseClicked(MouseEvent e) {
        // TODO add your code here
        String name = textField1.getText();
        String school = textField2.getText();
        String text = textArea1.getText();
        String photo = path;
        System.out.println(photo);
        String gender = null;
        if (radioButtonMenuItem1.isSelected()) {
            gender = radioButtonMenuItem1.getText().toString();
        }
        if (radioButtonMenuItem2.isSelected()) {
            gender = radioButtonMenuItem2.getText();
        }
        String s = "jianlixinxi,"+name+","+gender+","+school+","+text+","+photo+",";
        //发送信息
        OutputStream outputStream = null;
        Socket socket = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inetAddress ,8080);

            outputStream = socket.getOutputStream();
            outputStream.write(s.getBytes());
            socket.shutdownOutput();
            System.out.println("学生客户端："+s);
            //接收回应
            is = socket.getInputStream();
            byte [] buffer = new byte [20];
            baos = new ByteArrayOutputStream();
            while((b=is.read(buffer))!=-1) {
                baos.write(buffer, 0, b);
            }
            String string = baos.toString();
            if(string.equals("yes")) {
                JOptionPane.showMessageDialog(this,"简历填写成功！", "系统提示",JOptionPane.INFORMATION_MESSAGE);
                textField1.setText("");
                textField2.setText("");
                textArea1.setText("");

                FileOutputStream fos = new FileOutputStream("../studentresume.txt");
                byte[] bt = new byte[512];
                String str = name+","+gender+","+school+","+text+","+photo;
                bt = str.getBytes();
                int b = bt.length;
                fos.write(bt,0,b);
                fos.close();


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
    private void button2MouseClicked(MouseEvent e) {
        // TODO add your code here

        Vector rowDate, columnNames;
        String name = textField3.getText();
        columnNames = new Vector();
        columnNames.add("姓名");
        columnNames.add("性别");
        columnNames.add("毕业院校及专业");
        columnNames.add("自我评价及所获荣誉");
        rowDate = new Vector();

        try {
            String sql = "SELECT *FROM resume where name ='" + name + "'";
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
            textField3.setText("");

        } catch (SQLException d) {
            d.printStackTrace();
        }
        DefaultTableModel model = new DefaultTableModel(rowDate,columnNames);
        table1.setModel(model);
        table1.setRowHeight(30);

    }

    private void tabbedPane1MouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void tabbedPane1MouseWheelMoved(MouseWheelEvent e) {
        // TODO add your code here
    }

    private void button10MouseClicked(MouseEvent e) {
        // TODO add your code here
        new LoginUI();
        setVisible(false);
    }

    private void button11MouseClicked(MouseEvent e) {
        // TODO add your code here
        System.exit(0);
    }

    private void button12MouseClicked(MouseEvent e) {
        // TODO add your code here
        jdbcutil = new jdbcutil();
        jdbcutil.connect();
        Vector rowDate, columnNames;
        columnNames = new Vector();
        columnNames.add("公司编号");
        columnNames.add("公司名称");
        columnNames.add("职位");
        columnNames.add("要求");
        columnNames.add("薪资待遇");
        columnNames.add("状态");
        columnNames.add("申请");
        rowDate = new Vector();
        try {
            String sql = "SELECT id,company,position,requirement,Salary,state from recruitment";
            ResultSet rs = jdbcutil.getResultSet(sql);
            while (rs.next()) {
                Vector hang = new Vector();
                hang.add(rs.getString(1));
                hang.add(rs.getString(2));
                hang.add(rs.getString(3));
                hang.add(rs.getString(4));
                hang.add(rs.getString(5));
                hang.add(rs.getString(6));
                hang.add("申请");
                rowDate.add(hang);
            }
        } catch (SQLException d) {
            d.printStackTrace();
        }
        DefaultTableModel model = new DefaultTableModel(rowDate, columnNames);
        table2.setRowHeight(40);
        table2.setModel(model);
        ButtonColumn buttonColumn = new ButtonColumn(table2, 6);
    }

    private void button8MouseClicked(MouseEvent e) {
        // TODO add your code here

        //获得信息
        String s1 = textField8.getText();
        String s2 = textField5.getText();
        String s3 = textField6.getText();
        String s4 = textArea2.getText();
        String s = "rizhi,"+s1+","+s2+","+s3+","+s4+",";
        //发送信息
        OutputStream outputStream = null;
        Socket socket = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inetAddress ,8080);

            outputStream = socket.getOutputStream();
            outputStream.write(s.getBytes());
            socket.shutdownOutput();
            System.out.println("学生客户端日志写入界面："+s);
            //接收回应
            is = socket.getInputStream();
            byte [] buffer = new byte [20];
            baos = new ByteArrayOutputStream();
            while((b=is.read(buffer))!=-1) {
                baos.write(buffer, 0, b);
            }
            String string = baos.toString();
            if(string.equals("yes")) {
                JOptionPane.showMessageDialog(this,"日志更新成功！", "系统提示",JOptionPane.INFORMATION_MESSAGE);
                textField8.setText("");
                textField5.setText("");
                textField6.setText("");
                textArea2.setText("");
            }else if(string.equals("no")) {
                JOptionPane.showMessageDialog(label1, "日志更新失败！","系统提示",JOptionPane.ERROR_MESSAGE);
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

    private void button13MouseClicked(MouseEvent e) {
        // TODO add your code here
        new ServicetaolunUI();
        button13.setVisible(false);
        label6.setText("学生用户："+LoginUI.username);
        //发送信息
        Socket socket = null;
        try {
            //创建客户端
            client = new DatagramSocket();
            InetAddress address = InetAddress.getByName("127.0.0.1");
            client.connect(address,8899);
            JOptionPane.showMessageDialog(this,"连接成功");
            //发送上线信息给服务端
            String string = "学生："+LoginUI.username+"进入讨论区";
            byte[] data = string.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length);
            client.send(packet);
            new Thread(new Runnable() {
                public void run() {
                    // TODO Auto-generated method stub
                    try {
                        while (true) {
                            //接收服务端发送的信息，显示在面板上
                            byte[] data=new byte[1024];
                            DatagramPacket packet3=new DatagramPacket(data,data.length);
                            client.receive(packet3);
                            String string=new String(packet3.getData(),0,packet3.getLength());
                            textArea3.append(string+"\n");
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }finally {
            if(socket!=null) {
                try {
                    socket.close();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
    }

    private void button9MouseClicked(MouseEvent e) {
        // TODO add your code here
        read = textField7.getText();
            //向服务端发送 用户输入的信息
            try {
                String string = "学生-"+LoginUI.username+"说："+read;
                byte[] data=string.getBytes();
                DatagramPacket packet1 =new DatagramPacket(data,data.length);
                client.send(packet1);
                textField7.setText("");
            }catch (Exception e1) {
                e1.printStackTrace();
            }
    }

    private void button3MouseClicked(MouseEvent e) {
        // TODO add your code here
        jdbcutil = new jdbcutil();
        jdbcutil.connect();
        String company = textField4.getText();
        Vector rowDate, columnNames;
        columnNames = new Vector();
        columnNames.add("公司编号");
        columnNames.add("公司名称");
        columnNames.add("职位");
        columnNames.add("要求");
        columnNames.add("薪资待遇");
        columnNames.add("状态");
        columnNames.add("申请");
        rowDate = new Vector();
        try {
            String sql = "SELECT id,company,position,requirement,Salary,state from recruitment where company = '"+company+"'";
            ResultSet rs = jdbcutil.getResultSet(sql);
            while (rs.next()) {
                Vector hang = new Vector();
                hang.add(rs.getString(1));
                hang.add(rs.getString(2));
                hang.add(rs.getString(3));
                hang.add(rs.getString(4));
                hang.add(rs.getString(5));
                hang.add(rs.getString(6));
                hang.add("申请");
                rowDate.add(hang);
            }
        } catch (SQLException d) {
            d.printStackTrace();
        }
        DefaultTableModel model = new DefaultTableModel(rowDate, columnNames);
        table2.setRowHeight(40);
        table2.setModel(model);
        ButtonColumn buttonColumn = new ButtonColumn(table2, 6);

    }

    private void button7MouseClicked(MouseEvent e) {
        // TODO add your code here
        String s = "jianlishangchuan,";
        //发送信息
        OutputStream outputStream = null;
        Socket socket = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        BufferedReader br = null;
        try {
            InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inetAddress ,8080);

            outputStream = socket.getOutputStream();
            outputStream.write(s.getBytes());
            socket.shutdownOutput();
            System.out.println("学生客户端："+s);


            Socket sss = new Socket("127.0.0.1", 8899);
            br = new BufferedReader(new FileReader("../studentresume.txt"));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sss.getOutputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
                bw.flush();
            }
                // 数据传输完毕后关闭Socket
                // shutdownOutput​() 禁用此套接字的输出流
            sss.shutdownOutput();
                // 接收服务器的反馈
            BufferedReader brClient = new BufferedReader(new InputStreamReader(sss.getInputStream()));
            String data = brClient.readLine();
            System.out.println("服务器给出的反馈：" + data);
                // 释放资源
            br.close();
            sss.close();

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



//        // 封装文本中的数据
//        BufferedReader br = null;
//        try {
//            Socket s = new Socket("127.0.0.1", 8080);
//            br = new BufferedReader(new FileReader("../studentresume.txt"));
//
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
//            String line;
//            while ((line = br.readLine()) != null) {
//                bw.write(line);
//                bw.newLine();
//                bw.flush();
//            }
//
//            // 数据传输完毕后关闭Socket
//            // shutdownOutput​() 禁用此套接字的输出流
//            s.shutdownOutput();
//
//            // 接收服务器的反馈
//            BufferedReader brClient = new BufferedReader(new InputStreamReader(s.getInputStream()));
//            String data = brClient.readLine();
//            System.out.println("服务器给出的反馈：" + data);
//
//            // 释放资源
//            br.close();
//            s.close();
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
//        // 封装输出流写数据



    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        tabbedPane1 = new JTabbedPane();
        panel2 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        textField1 = new JTextField();
        textField2 = new JTextField();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        button4 = new JButton();
        button5 = new JButton();
        radioButtonMenuItem1 = new JRadioButtonMenuItem();
        radioButtonMenuItem2 = new JRadioButtonMenuItem();
        button6 = new JButton();
        button7 = new JButton();
        panel3 = new JPanel();
        label5 = new JLabel();
        textField3 = new JTextField();
        scrollPane2 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        button2 = new JButton();
        panel1 = new JPanel();
        label7 = new JLabel();
        textField4 = new JTextField();
        button3 = new JButton();
        button12 = new JButton();
        scrollPane3 = new JScrollPane();
        table2 = new JTable();
        panel4 = new JPanel();
        textField5 = new JTextField();
        label8 = new JLabel();
        label9 = new JLabel();
        textField6 = new JTextField();
        label10 = new JLabel();
        button8 = new JButton();
        scrollPane4 = new JScrollPane();
        textArea2 = new JTextArea();
        label11 = new JLabel();
        textField8 = new JTextField();
        panel5 = new JPanel();
        scrollPane5 = new JScrollPane();
        textArea3 = new JTextArea();
        textField7 = new JTextField();
        button9 = new JButton();
        label6 = new JLabel();
        button13 = new JButton();
        panel6 = new JPanel();
        button10 = new JButton();
        button11 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== tabbedPane1 ========
        {
            tabbedPane1.setFont(tabbedPane1.getFont().deriveFont(tabbedPane1.getFont().getSize() + 4f));
            tabbedPane1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    tabbedPane1MouseClicked(e);
                }
            });
            tabbedPane1.addMouseWheelListener(e -> tabbedPane1MouseWheelMoved(e));

            //======== panel2 ========
            {
                panel2.setLayout(null);

                //---- label1 ----
                label1.setText("\u59d3\u540d\uff1a");
                label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 5f));
                panel2.add(label1);
                label1.setBounds(130, 30, 90, 60);

                //---- label2 ----
                label2.setText("\u6027\u522b\uff1a");
                label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 5f));
                panel2.add(label2);
                label2.setBounds(130, 130, 90, 60);

                //---- label3 ----
                label3.setText("\u6bd5\u4e1a\u9662\u6821\u53ca\u4e13\u4e1a\uff1a");
                label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 5f));
                panel2.add(label3);
                label3.setBounds(55, 80, 145, 60);

                //---- label4 ----
                label4.setText("\u81ea\u6211\u8bc4\u4ef7\u53ca\u6240\u83b7\u8363\u8a89\uff1a");
                label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 5f));
                panel2.add(label4);
                label4.setBounds(15, 180, 190, 60);
                panel2.add(textField1);
                textField1.setBounds(205, 40, 255, 40);
                panel2.add(textField2);
                textField2.setBounds(205, 90, 255, 40);

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(textArea1);
                }
                panel2.add(scrollPane1);
                scrollPane1.setBounds(205, 200, 260, 245);

                //---- button4 ----
                button4.setText("\u4e0a\u4f20\u56fe\u7247\u5904");
                button4.setFont(button4.getFont().deriveFont(button4.getFont().getSize() + 3f));
                panel2.add(button4);
                button4.setBounds(505, 35, 190, 185);

                //---- button5 ----
                button5.setText("\u4e0a\u4f20\u56fe\u7247");
                button5.setFont(button5.getFont().deriveFont(button5.getFont().getSize() + 2f));
                button5.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button5MouseClicked(e);
                    }
                });
                panel2.add(button5);
                button5.setBounds(555, 245, 100, 45);

                //---- radioButtonMenuItem1 ----
                radioButtonMenuItem1.setText("\u7537");
                radioButtonMenuItem1.setFont(radioButtonMenuItem1.getFont().deriveFont(radioButtonMenuItem1.getFont().getSize() + 4f));
                panel2.add(radioButtonMenuItem1);
                radioButtonMenuItem1.setBounds(230, 140, 80, 40);

                //---- radioButtonMenuItem2 ----
                radioButtonMenuItem2.setText("\u5973");
                radioButtonMenuItem2.setFont(radioButtonMenuItem2.getFont().deriveFont(radioButtonMenuItem2.getFont().getSize() + 4f));
                panel2.add(radioButtonMenuItem2);
                radioButtonMenuItem2.setBounds(370, 140, 85, 40);

                //---- button6 ----
                button6.setText("\u4fdd\u5b58");
                button6.setFont(button6.getFont().deriveFont(button6.getFont().getSize() + 5f));
                button6.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button6MouseClicked(e);
                    }
                });
                panel2.add(button6);
                button6.setBounds(505, 365, 85, 45);

                //---- button7 ----
                button7.setText("\u4e0a\u4f20");
                button7.setFont(button7.getFont().deriveFont(button7.getFont().getSize() + 5f));
                button7.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button7MouseClicked(e);
                    }
                });
                panel2.add(button7);
                button7.setBounds(630, 365, 85, 45);
            }
            tabbedPane1.addTab("\u7b80\u5386\u4fe1\u606f\u7ba1\u7406\u754c\u9762", panel2);

            //======== panel3 ========
            {
                panel3.setLayout(null);

                //---- label5 ----
                label5.setText("\u8bf7\u8f93\u5165\u67e5\u8be2\u7684\u5b66\u751f\u59d3\u540d\uff1a");
                label5.setFont(label5.getFont().deriveFont(label5.getFont().getSize() + 5f));
                panel3.add(label5);
                label5.setBounds(130, 50, 215, 55);
                panel3.add(textField3);
                textField3.setBounds(340, 60, 255, 40);

                //======== scrollPane2 ========
                {
                    scrollPane2.setViewportView(table1);
                }
                panel3.add(scrollPane2);
                scrollPane2.setBounds(95, 180, 575, 60);

                //---- button1 ----
                button1.setText("\u56fe\u7247\u663e\u793a\u5904");
                button1.setFont(button1.getFont().deriveFont(button1.getFont().getSize() + 4f));
                panel3.add(button1);
                button1.setBounds(290, 265, 190, 185);

                //---- button2 ----
                button2.setText("\u67e5\u8be2");
                button2.setFont(button2.getFont().deriveFont(button2.getFont().getSize() + 4f));
                button2.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button2MouseClicked(e);
                    }
                });
                panel3.add(button2);
                button2.setBounds(340, 120, 105, 35);

                { // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panel3.getComponentCount(); i++) {
                        Rectangle bounds = panel3.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel3.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel3.setMinimumSize(preferredSize);
                    panel3.setPreferredSize(preferredSize);
                }
            }
            tabbedPane1.addTab("\u7b80\u5386\u67e5\u8be2\u754c\u9762", panel3);

            //======== panel1 ========
            {
                panel1.setLayout(null);

                //---- label7 ----
                label7.setText("\u8bf7\u8f93\u5165\u60f3\u8981\u7533\u8bf7\u7684\u516c\u53f8\u540d\u79f0\uff1a");
                label7.setFont(label7.getFont().deriveFont(label7.getFont().getSize() + 7f));
                panel1.add(label7);
                label7.setBounds(105, 35, 355, 45);
                panel1.add(textField4);
                textField4.setBounds(375, 35, 280, 45);

                //---- button3 ----
                button3.setText("\u67e5\u8be2\u62db\u8058\u4fe1\u606f");
                button3.setFont(button3.getFont().deriveFont(button3.getFont().getSize() + 4f));
                button3.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button3MouseClicked(e);
                    }
                });
                panel1.add(button3);
                button3.setBounds(440, 95, 170, 55);

                //---- button12 ----
                button12.setText("\u67e5\u770b\u6240\u6709\u62db\u8058\u4fe1\u606f");
                button12.setFont(button12.getFont().deriveFont(button12.getFont().getSize() + 4f));
                button12.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button12MouseClicked(e);
                    }
                });
                panel1.add(button12);
                button12.setBounds(180, 95, 170, 55);

                //======== scrollPane3 ========
                {
                    scrollPane3.setViewportView(table2);
                }
                panel1.add(scrollPane3);
                scrollPane3.setBounds(30, 160, 705, 315);

                { // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panel1.getComponentCount(); i++) {
                        Rectangle bounds = panel1.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel1.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel1.setMinimumSize(preferredSize);
                    panel1.setPreferredSize(preferredSize);
                }
            }
            tabbedPane1.addTab("\u67e5\u8be2\u5e76\u7533\u8bf7\u5b9e\u4e60\u5de5\u4f5c\u754c\u9762", panel1);

            //======== panel4 ========
            {
                panel4.setLayout(null);
                panel4.add(textField5);
                textField5.setBounds(305, 85, 255, 40);

                //---- label8 ----
                label8.setText("\u5b66\u751f\u59d3\u540d\uff1a");
                label8.setFont(label8.getFont().deriveFont(label8.getFont().getSize() + 5f));
                panel4.add(label8);
                label8.setBounds(200, 85, 270, 35);

                //---- label9 ----
                label9.setText("\u5de5\u4f5c\u4e8b\u9879\uff1a");
                label9.setFont(label9.getFont().deriveFont(label9.getFont().getSize() + 5f));
                panel4.add(label9);
                label9.setBounds(200, 150, 270, 35);
                panel4.add(textField6);
                textField6.setBounds(305, 145, 255, 40);

                //---- label10 ----
                label10.setText("\u4efb\u52a1\u8fdb\u5ea6\uff1a");
                label10.setFont(label10.getFont().deriveFont(label10.getFont().getSize() + 5f));
                panel4.add(label10);
                label10.setBounds(200, 210, 270, 35);

                //---- button8 ----
                button8.setText("\u63d0\u4ea4\u8fdb\u5ea6");
                button8.setFont(button8.getFont().deriveFont(button8.getFont().getSize() + 5f));
                button8.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button8MouseClicked(e);
                    }
                });
                panel4.add(button8);
                button8.setBounds(375, 430, 110, 45);

                //======== scrollPane4 ========
                {
                    scrollPane4.setViewportView(textArea2);
                }
                panel4.add(scrollPane4);
                scrollPane4.setBounds(305, 215, 255, 200);

                //---- label11 ----
                label11.setText("\u5355\u4f4d\u540d\u79f0\uff1a");
                label11.setFont(label11.getFont().deriveFont(label11.getFont().getSize() + 5f));
                panel4.add(label11);
                label11.setBounds(200, 25, 270, 35);
                panel4.add(textField8);
                textField8.setBounds(305, 25, 255, 40);

                { // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panel4.getComponentCount(); i++) {
                        Rectangle bounds = panel4.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel4.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel4.setMinimumSize(preferredSize);
                    panel4.setPreferredSize(preferredSize);
                }
            }
            tabbedPane1.addTab("\u65e5\u5fd7\u7ba1\u7406\u754c\u9762", panel4);

            //======== panel5 ========
            {
                panel5.setLayout(null);

                //======== scrollPane5 ========
                {
                    scrollPane5.setViewportView(textArea3);
                }
                panel5.add(scrollPane5);
                scrollPane5.setBounds(0, 45, 765, 410);
                panel5.add(textField7);
                textField7.setBounds(0, 455, 640, 40);

                //---- button9 ----
                button9.setText("\u53d1\u9001");
                button9.setFont(button9.getFont().deriveFont(button9.getFont().getSize() + 4f));
                button9.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button9MouseClicked(e);
                    }
                });
                panel5.add(button9);
                button9.setBounds(645, 458, 98, 35);

                //---- label6 ----
                label6.setFont(label6.getFont().deriveFont(label6.getFont().getSize() + 4f));
                panel5.add(label6);
                label6.setBounds(305, 5, 185, 35);

                //---- button13 ----
                button13.setText("\u8fdb\u5165\u8ba8\u8bba\u533a");
                button13.setFont(button13.getFont().deriveFont(button13.getFont().getSize() + 5f));
                button13.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button13MouseClicked(e);
                    }
                });
                panel5.add(button13);
                button13.setBounds(320, 5, 125, 35);

                { // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panel5.getComponentCount(); i++) {
                        Rectangle bounds = panel5.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel5.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel5.setMinimumSize(preferredSize);
                    panel5.setPreferredSize(preferredSize);
                }
            }
            tabbedPane1.addTab("\u8ba8\u8bba\u533a", panel5);

            //======== panel6 ========
            {
                panel6.setLayout(null);

                //---- button10 ----
                button10.setText("\u8fd4\u56de\u4e3b\u9875\u9762");
                button10.setFont(button10.getFont().deriveFont(button10.getFont().getSize() + 4f));
                button10.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button10MouseClicked(e);
                    }
                });
                panel6.add(button10);
                button10.setBounds(190, 195, 125, 80);

                //---- button11 ----
                button11.setText("\u9000\u51fa\u7cfb\u7edf");
                button11.setFont(button11.getFont().deriveFont(button11.getFont().getSize() + 4f));
                button11.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button11MouseClicked(e);
                    }
                });
                panel6.add(button11);
                button11.setBounds(460, 195, 125, 80);

                { // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panel6.getComponentCount(); i++) {
                        Rectangle bounds = panel6.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel6.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel6.setMinimumSize(preferredSize);
                    panel6.setPreferredSize(preferredSize);
                }
            }
            tabbedPane1.addTab("\u5176\u4ed6", panel6);
        }
        contentPane.add(tabbedPane1);
        tabbedPane1.setBounds(0, 0, 765, 535);

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
        setSize(760, 560);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTabbedPane tabbedPane1;
    private JPanel panel2;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JTextField textField1;
    private JTextField textField2;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    private JButton button4;
    private JButton button5;
    private JRadioButtonMenuItem radioButtonMenuItem1;
    private JRadioButtonMenuItem radioButtonMenuItem2;
    private JButton button6;
    private JButton button7;
    private JPanel panel3;
    private JLabel label5;
    private JTextField textField3;
    private JScrollPane scrollPane2;
    private JTable table1;
    private JButton button1;
    private JButton button2;
    private JPanel panel1;
    private JLabel label7;
    private JTextField textField4;
    private JButton button3;
    private JButton button12;
    private JScrollPane scrollPane3;
    private JTable table2;
    private JPanel panel4;
    private JTextField textField5;
    private JLabel label8;
    private JLabel label9;
    private JTextField textField6;
    private JLabel label10;
    private JButton button8;
    private JScrollPane scrollPane4;
    private JTextArea textArea2;
    private JLabel label11;
    private JTextField textField8;
    private JPanel panel5;
    private JScrollPane scrollPane5;
    private JTextArea textArea3;
    private JTextField textField7;
    private JButton button9;
    private JLabel label6;
    private JButton button13;
    private JPanel panel6;
    private JButton button10;
    private JButton button11;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    class ButtonColumn extends AbstractCellEditor
            implements TableCellRenderer, TableCellEditor, ActionListener
    {
        JTable table;
        JButton renderButton;
        JButton editButton;
        String text;

        public ButtonColumn(JTable table, int column)
        {
            super();
            this.table = table;
            renderButton = new JButton();

            editButton = new JButton();
            editButton.setFocusPainted( false );
            editButton.addActionListener( this );

            TableColumnModel columnModel = table.getColumnModel();
            columnModel.getColumn(column).setCellRenderer( this );
            columnModel.getColumn(column).setCellEditor( this );
        }

        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            if (hasFocus)
            {
                renderButton.setForeground(table.getForeground());
                renderButton.setBackground(UIManager.getColor("Button.background"));
            }
            else if (isSelected)
            {
                renderButton.setForeground(table.getSelectionForeground());
                renderButton.setBackground(table.getSelectionBackground());
            }
            else
            {
                renderButton.setForeground(table.getForeground());
                renderButton.setBackground(UIManager.getColor("Button.background"));
            }

            renderButton.setText( (value == null) ? "" : value.toString() );
            return renderButton;
        }

        public Component getTableCellEditorComponent(
                JTable table, Object value, boolean isSelected, int row, int column)
        {
            text = (value == null) ? "" : value.toString();
            editButton.setText( text );
            return editButton;
        }

        public Object getCellEditorValue()
        {
            return text;
        }

        public void actionPerformed(ActionEvent e)
        {
            fireEditingStopped();
            System.out.println( e.getActionCommand() + " : " + table.getSelectedRow());
            new ApplicationUI();
            /*再new 一个新的专门用来完善用户简历信息和修改申请状态的界面，再在那个界面里添加监听器来实现*/
        }
    }
    public static void main(String[] args) {
        new StudentUI();
    }
}
