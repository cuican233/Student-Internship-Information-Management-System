/*
 * Created by JFormDesigner on Tue Dec 21 15:09:44 CST 2021
 */

package shixi.views.teacher;

import shixi.utils.jdbcutil;
import shixi.views.RLUI.LoginUI;
import shixi.views.enterpriseviews.AssignmentfenpeiUI;
import shixi.views.enterpriseviews.ScoreUI;
import shixi.views.liaotian.ServicetaolunUI;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 * @author Brainrain
 */
public class TeacherUI extends JFrame {

    private DatagramSocket client;
    private String read = null;

    jdbcutil jdbcutil;
    public TeacherUI() {
        initComponents();
        setVisible(true);
        panel1.setOpaque(false);
        panel2.setOpaque(false);
        panel3.setOpaque(false);
        panel4.setOpaque(false);
        tabbedPane1.setOpaque(false);
        this.getLayeredPane().setLayout(null);
        textField1.setOpaque(false);
        textField5.setOpaque(false);
        textField6.setOpaque(false);
        button1.setContentAreaFilled(false);
        button2.setContentAreaFilled(false);
        button3.setContentAreaFilled(false);
        button5.setContentAreaFilled(false);
        button6.setContentAreaFilled(false);

    }

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here

        jdbcutil = new jdbcutil();
        jdbcutil.connect();
        String student_name = textField5.getText();
        Vector rowDate, columnNames;
        columnNames = new Vector();
        columnNames.add("学生姓名");
        columnNames.add("任务");
        columnNames.add("老师姓名");
        columnNames.add("分配任务");
        rowDate = new Vector();
        try {
                String sql2 = "SELECT * from teacherassignment where student_name = '" + student_name + "'";
                ResultSet rs1 = jdbcutil.getResultSet(sql2);
                while (rs1.next()) {
                    Vector hang = new Vector();
                    hang.add(rs1.getString(1));
                    hang.add(rs1.getString(2));
                    hang.add(rs1.getString(3));
                    hang.add("分配任务");
                    rowDate.add(hang);
                }
        } catch (SQLException d) {
            d.printStackTrace();
        }
        DefaultTableModel model = new DefaultTableModel(rowDate, columnNames);
        table1.setRowHeight(100);
        table1.setModel(model);

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
                new TassignmentUI();
                /*再new 一个新的专门用来完善用户简历信息和修改申请状态的界面，再在那个界面里添加监听器来实现*/
            }
        }
        ButtonColumn buttonColumn = new ButtonColumn(table1, 3);

    }

    private void button2MouseClicked(MouseEvent e) {
        // TODO add your code here

        jdbcutil = new jdbcutil();
        jdbcutil.connect();
        String student_name = textField6.getText();
        Vector rowDate, columnNames;
        columnNames = new Vector();
        columnNames.add("老师姓名");
        columnNames.add("学生姓名");
        columnNames.add("工作事项");
        columnNames.add("工作进度");
        columnNames.add("工作表现");
        columnNames.add("工作成果");
        columnNames.add("分数");
        columnNames.add("打分");
        rowDate = new Vector();
        try {
            String sql = "SELECT * from teacherscore where student_name = '"+student_name+"'";
            ResultSet rs = jdbcutil.getResultSet(sql);
            while (rs.next()) {
                Vector hang = new Vector();
                hang.add(rs.getString(1));
                hang.add(rs.getString(2));
                hang.add(rs.getString(3));
                hang.add(rs.getString(4));
                hang.add(rs.getString(5));
                hang.add(rs.getString(6));
                hang.add(rs.getString(7));
                hang.add("打分");
                rowDate.add(hang);
            }
        } catch (SQLException d) {
            d.printStackTrace();
        }
        DefaultTableModel model = new DefaultTableModel(rowDate, columnNames);
        table2.setRowHeight(100);
        table2.setModel(model);

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
                new TeacherscoreUI();
                /*再new 一个新的专门用来完善用户简历信息和修改申请状态的界面，再在那个界面里添加监听器来实现*/
            }
        }
        ButtonColumn buttonColumn = new ButtonColumn(table2, 7);

    }

    private void button4MouseClicked(MouseEvent e) {
        // TODO add your code here

        read = textField1.getText();
        //向服务端发送 用户输入的信息
        try {
            String string = "教师-"+LoginUI.username+"说："+read;
            byte[] data=string.getBytes();
            DatagramPacket packet1 =new DatagramPacket(data,data.length);
            client.send(packet1);
            textField1.setText("");
        }catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private void button5MouseClicked(MouseEvent e) {
        // TODO add your code here
        setVisible(false);
        new LoginUI();
    }

    private void button6MouseClicked(MouseEvent e) {
        // TODO add your code here
        System.exit(0);
    }

    private void button3MouseClicked(MouseEvent e) {
        // TODO add your code here
        button3.setVisible(false);
        label1.setText("教师用户："+ LoginUI.username);
        //发送信息
        Socket socket = null;
        try {
            //创建客户端
            client = new DatagramSocket();
            InetAddress address = InetAddress.getByName("127.0.0.1");
            client.connect(address,8899);
            JOptionPane.showMessageDialog(this,"连接成功");
            //发送上线信息给服务端
            String string = "教师："+LoginUI.username+"进入讨论区";
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
                            textArea1.append(string+"\n");
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

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        tabbedPane1 = new JTabbedPane();
        panel1 = new JPanel();
        textField5 = new JTextField();
        label6 = new JLabel();
        button1 = new JButton();
        scrollPane2 = new JScrollPane();
        table1 = new JTable();
        panel2 = new JPanel();
        textField6 = new JTextField();
        label7 = new JLabel();
        button2 = new JButton();
        scrollPane3 = new JScrollPane();
        table2 = new JTable();
        panel3 = new JPanel();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        button3 = new JButton();
        textField1 = new JTextField();
        button4 = new JButton();
        label1 = new JLabel();
        panel4 = new JPanel();
        button5 = new JButton();
        button6 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== tabbedPane1 ========
        {
            tabbedPane1.setFont(tabbedPane1.getFont().deriveFont(tabbedPane1.getFont().getSize() + 21f));

            //======== panel1 ========
            {
                panel1.setLayout(null);
                panel1.add(textField5);
                textField5.setBounds(375, 30, 235, 40);

                //---- label6 ----
                label6.setText("\u8bf7\u8f93\u5165\u8981\u67e5\u8be2\u7684\u5b66\u751f\u540d\u79f0\uff1a");
                label6.setFont(label6.getFont().deriveFont(label6.getFont().getSize() + 8f));
                panel1.add(label6);
                label6.setBounds(115, 30, 275, 40);

                //---- button1 ----
                button1.setText("\u67e5\u770b\u5b66\u751f\u4efb\u52a1\u5206\u914d\u60c5\u51b5");
                button1.setFont(button1.getFont().deriveFont(button1.getFont().getSize() + 5f));
                button1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button1MouseClicked(e);
                    }
                });
                panel1.add(button1);
                button1.setBounds(375, 105, 235, 45);

                //======== scrollPane2 ========
                {
                    scrollPane2.setViewportView(table1);
                }
                panel1.add(scrollPane2);
                scrollPane2.setBounds(35, 185, 700, 249);

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
            tabbedPane1.addTab("\u5b66\u751f\u4efb\u52a1\u5206\u914d", panel1);

            //======== panel2 ========
            {
                panel2.setLayout(null);
                panel2.add(textField6);
                textField6.setBounds(375, 30, 235, 40);

                //---- label7 ----
                label7.setText("\u8bf7\u8f93\u5165\u8981\u67e5\u8be2\u7684\u5b66\u751f\u540d\u79f0\uff1a");
                label7.setFont(label7.getFont().deriveFont(label7.getFont().getSize() + 8f));
                panel2.add(label7);
                label7.setBounds(115, 30, 275, 40);

                //---- button2 ----
                button2.setText("\u67e5\u770b\u5b66\u751f\u5b9e\u4e60\u5de5\u4f5c\u60c5\u51b5");
                button2.setFont(button2.getFont().deriveFont(button2.getFont().getSize() + 5f));
                button2.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button2MouseClicked(e);
                        button2MouseClicked(e);
                    }
                });
                panel2.add(button2);
                button2.setBounds(375, 105, 235, 45);

                //======== scrollPane3 ========
                {
                    scrollPane3.setViewportView(table2);
                }
                panel2.add(scrollPane3);
                scrollPane3.setBounds(35, 185, 695, 220);

                { // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panel2.getComponentCount(); i++) {
                        Rectangle bounds = panel2.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel2.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel2.setMinimumSize(preferredSize);
                    panel2.setPreferredSize(preferredSize);
                }
            }
            tabbedPane1.addTab("\u5b66\u751f\u5b9e\u4e60\u60c5\u51b5\u6253\u5206", panel2);

            //======== panel3 ========
            {
                panel3.setLayout(null);

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(textArea1);
                }
                panel3.add(scrollPane1);
                scrollPane1.setBounds(0, 40, 765, 400);

                //---- button3 ----
                button3.setText("\u8fdb\u5165\u8ba8\u8bba\u533a");
                button3.setFont(button3.getFont().deriveFont(button3.getFont().getSize() + 4f));
                button3.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button3MouseClicked(e);
                    }
                });
                panel3.add(button3);
                button3.setBounds(315, 0, 130, 40);
                panel3.add(textField1);
                textField1.setBounds(0, 440, 640, 40);

                //---- button4 ----
                button4.setText("\u53d1\u9001");
                button4.setFont(button4.getFont().deriveFont(button4.getFont().getSize() + 4f));
                button4.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button4MouseClicked(e);
                    }
                });
                panel3.add(button4);
                button4.setBounds(645, 445, 98, 35);

                //---- label1 ----
                label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 4f));
                panel3.add(label1);
                label1.setBounds(265, 0, 245, 40);

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
            tabbedPane1.addTab("\u8ba8\u8bba\u533a", panel3);

            //======== panel4 ========
            {
                panel4.setLayout(null);

                //---- button5 ----
                button5.setText("\u8fd4\u56de\u767b\u9646\u754c\u9762");
                button5.setFont(button5.getFont().deriveFont(button5.getFont().getSize() + 5f));
                button5.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button5MouseClicked(e);
                    }
                });
                panel4.add(button5);
                button5.setBounds(180, 180, 135, 90);

                //---- button6 ----
                button6.setText("\u9000\u51fa\u7cfb\u7edf");
                button6.setFont(button6.getFont().deriveFont(button6.getFont().getSize() + 5f));
                button6.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button6MouseClicked(e);
                    }
                });
                panel4.add(button6);
                button6.setBounds(455, 180, 135, 90);

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
            tabbedPane1.addTab("\u5176\u4ed6", panel4);
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
        setSize(780, 580);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTextField textField5;
    private JLabel label6;
    private JButton button1;
    private JScrollPane scrollPane2;
    private JTable table1;
    private JPanel panel2;
    private JTextField textField6;
    private JLabel label7;
    private JButton button2;
    private JScrollPane scrollPane3;
    private JTable table2;
    private JPanel panel3;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    private JButton button3;
    private JTextField textField1;
    private JButton button4;
    private JLabel label1;
    private JPanel panel4;
    private JButton button5;
    private JButton button6;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public static void main(String[] args) {
        new TeacherUI();
    }
}
