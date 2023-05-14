/*
 * Created by JFormDesigner on Fri Dec 17 14:56:11 CST 2021
 */

package shixi.views.enterpriseviews;

import shixi.utils.jdbcutil;
import shixi.views.studentviews.ApplicationUI;
import shixi.views.studentviews.StudentUI;

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
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 * @author Brainrain
 */
public class EnterpriseUI extends JFrame {
    private jdbcutil jdbcutil;
    public EnterpriseUI() {
        initComponents();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jdbcutil = new jdbcutil();
        jdbcutil.connect();
        panel1.setOpaque(false);
        panel2.setOpaque(false);
        panel3.setOpaque(false);
        panel4.setOpaque(false);
        tabbedPane1.setOpaque(false);
        this.getLayeredPane().setLayout(null);
        textField1.setOpaque(false);
        textField2.setOpaque(false);
        textField3.setOpaque(false);
        textField4.setOpaque(false);
        textField5.setOpaque(false);
        textField6.setOpaque(false);
        textArea1.setOpaque(false);
        button1.setContentAreaFilled(false);
        button2.setContentAreaFilled(false);
        button3.setContentAreaFilled(false);
        button4.setContentAreaFilled(false);


    }

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        String company = textField1.getText();
        String position = textField2.getText();
        String requirement = textField3.getText();
        String salary = textArea1.getText();
        String state = "已发布";
        String s = "zhaopinfabu,"+company+","+position+","+requirement+","+salary+","+state+",";

        OutputStream outputStream = null;
        Socket socket = null;
        InputStream inputStream = null;
        BufferedWriter bufferedReader  = null;
        try {
            InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inetAddress ,8080);

            outputStream = socket.getOutputStream();
            outputStream.write(s.getBytes());
            System.out.println("企业客户端招聘页面："+s);
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
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textArea1.setText("");

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

    private void button2MouseClicked(MouseEvent e) {
        // TODO add your code here
        jdbcutil = new jdbcutil();
        jdbcutil.connect();
        String company = textField4.getText();
        Vector rowDate, columnNames;
        columnNames = new Vector();
        columnNames.add("公司名称");
        columnNames.add("职位");
        columnNames.add("要求");
        columnNames.add("薪资待遇");
        columnNames.add("状态");
        columnNames.add("申请人");
        columnNames.add("查看简历");
        rowDate = new Vector();
        try {
            String sql = "SELECT company,position,requirement,Salary,state,employee from recruitment where company = '"+company+"' and state = '申请中'";
            ResultSet rs = jdbcutil.getResultSet(sql);
            while (rs.next()) {
                Vector hang = new Vector();
                hang.add(rs.getString(1));
                hang.add(rs.getString(2));
                hang.add(rs.getString(3));
                hang.add(rs.getString(4));
                hang.add(rs.getString(5));
                hang.add(rs.getString(6));
                hang.add("查看简历");
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
                new JianlichakanUI();
                /*再new 一个新的专门用来完善用户简历信息和修改申请状态的界面，再在那个界面里添加监听器来实现*/
            }
        }

        ButtonColumn buttonColumn = new ButtonColumn(table1, 6);

    }

    private void button3MouseClicked(MouseEvent e) {
        // TODO add your code here

        jdbcutil = new jdbcutil();
        jdbcutil.connect();
        String company = textField5.getText();
        Vector rowDate, columnNames;
        columnNames = new Vector();
        columnNames.add("公司名称");
        columnNames.add("员工姓名");
        columnNames.add("工作项目");
        columnNames.add("分配工作");
        rowDate = new Vector();
        try {
            String sql = "SELECT * from assignment where company = '"+company+"'";
            ResultSet rs = jdbcutil.getResultSet(sql);
            while (rs.next()) {
                Vector hang = new Vector();
                hang.add(rs.getString(1));
                hang.add(rs.getString(2));
                hang.add(rs.getString(3));
                hang.add("分配工作");
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
                new AssignmentfenpeiUI();
                /*再new 一个新的专门用来完善用户简历信息和修改申请状态的界面，再在那个界面里添加监听器来实现*/
            }
        }
        ButtonColumn buttonColumn = new ButtonColumn(table2, 3);

    }

    private void button4MouseClicked(MouseEvent e) {
        // TODO add your code here

        jdbcutil = new jdbcutil();
        jdbcutil.connect();
        String company = textField6.getText();
        Vector rowDate, columnNames;
        columnNames = new Vector();
        columnNames.add("单位名称");
        columnNames.add("员工姓名");
        columnNames.add("工作事项");
        columnNames.add("目前进度");
        columnNames.add("工作表现");
        columnNames.add("工作成果");
        columnNames.add("分数");
        columnNames.add("打分");
        rowDate = new Vector();
        try {
            String sql = "SELECT * from score where company = '"+company+"'";
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
        table3.setRowHeight(100);
        table3.setModel(model);

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
                new ScoreUI();
                /*再new 一个新的专门用来完善用户简历信息和修改申请状态的界面，再在那个界面里添加监听器来实现*/
            }
        }
        ButtonColumn buttonColumn = new ButtonColumn(table3, 7);
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        tabbedPane1 = new JTabbedPane();
        panel1 = new JPanel();
        textField1 = new JTextField();
        label1 = new JLabel();
        textField2 = new JTextField();
        label2 = new JLabel();
        textField3 = new JTextField();
        label3 = new JLabel();
        label4 = new JLabel();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        button1 = new JButton();
        panel2 = new JPanel();
        textField4 = new JTextField();
        label5 = new JLabel();
        button2 = new JButton();
        scrollPane2 = new JScrollPane();
        table1 = new JTable();
        panel3 = new JPanel();
        textField5 = new JTextField();
        label6 = new JLabel();
        button3 = new JButton();
        scrollPane3 = new JScrollPane();
        table2 = new JTable();
        panel4 = new JPanel();
        label7 = new JLabel();
        textField6 = new JTextField();
        button4 = new JButton();
        scrollPane4 = new JScrollPane();
        table3 = new JTable();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== tabbedPane1 ========
        {
            tabbedPane1.setFont(tabbedPane1.getFont().deriveFont(tabbedPane1.getFont().getSize() + 12f));

            //======== panel1 ========
            {
                panel1.setLayout(null);
                panel1.add(textField1);
                textField1.setBounds(285, 35, 290, 40);

                //---- label1 ----
                label1.setText("\u5355\u4f4d\u540d\u79f0\uff1a");
                label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 5f));
                panel1.add(label1);
                label1.setBounds(200, 20, 90, 60);
                panel1.add(textField2);
                textField2.setBounds(285, 100, 290, 40);

                //---- label2 ----
                label2.setText("\u62db\u8058\u804c\u4f4d\uff1a");
                label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 5f));
                panel1.add(label2);
                label2.setBounds(200, 90, 90, 60);
                panel1.add(textField3);
                textField3.setBounds(285, 170, 290, 40);

                //---- label3 ----
                label3.setText("\u62db\u8058\u8981\u6c42\uff1a");
                label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 5f));
                panel1.add(label3);
                label3.setBounds(200, 155, 90, 60);

                //---- label4 ----
                label4.setText("\u85aa\u8d44\u5f85\u9047\uff1a");
                label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 5f));
                panel1.add(label4);
                label4.setBounds(200, 220, 90, 60);

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(textArea1);
                }
                panel1.add(scrollPane1);
                scrollPane1.setBounds(285, 240, 290, 145);

                //---- button1 ----
                button1.setText("\u53d1\u5e03\u62db\u8058");
                button1.setFont(button1.getFont().deriveFont(button1.getFont().getSize() + 5f));
                button1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button1MouseClicked(e);
                    }
                });
                panel1.add(button1);
                button1.setBounds(290, 410, 115, 45);

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
            tabbedPane1.addTab("\u5b9e\u4e60\u5de5\u4f5c\u53d1\u5e03 ", panel1);

            //======== panel2 ========
            {
                panel2.setLayout(null);
                panel2.add(textField4);
                textField4.setBounds(325, 60, 315, 40);

                //---- label5 ----
                label5.setText("\u8bf7\u8f93\u5165\u60a8\u7684\u516c\u53f8\u540d\u79f0\uff1a");
                label5.setFont(label5.getFont().deriveFont(label5.getFont().getSize() + 8f));
                panel2.add(label5);
                label5.setBounds(110, 60, 275, 40);

                //---- button2 ----
                button2.setText("\u67e5\u770b\u7533\u8bf7\u4fe1\u606f");
                button2.setFont(button2.getFont().deriveFont(button2.getFont().getSize() + 5f));
                button2.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button2MouseClicked(e);
                    }
                });
                panel2.add(button2);
                button2.setBounds(325, 135, 155, 55);

                //======== scrollPane2 ========
                {
                    scrollPane2.setViewportView(table1);
                }
                panel2.add(scrollPane2);
                scrollPane2.setBounds(50, 215, 685, 230);

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
            tabbedPane1.addTab("\u5b9e\u4e60\u5de5\u4f5c\u767b\u8bb0", panel2);

            //======== panel3 ========
            {
                panel3.setLayout(null);
                panel3.add(textField5);
                textField5.setBounds(350, 60, 235, 40);

                //---- label6 ----
                label6.setText("\u8bf7\u8f93\u5165\u60a8\u7684\u516c\u53f8\u540d\u79f0\uff1a");
                label6.setFont(label6.getFont().deriveFont(label6.getFont().getSize() + 8f));
                panel3.add(label6);
                label6.setBounds(125, 60, 275, 40);

                //---- button3 ----
                button3.setText("\u67e5\u770b\u5458\u5de5\u4efb\u52a1\u5206\u914d\u60c5\u51b5");
                button3.setFont(button3.getFont().deriveFont(button3.getFont().getSize() + 5f));
                button3.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button3MouseClicked(e);
                    }
                });
                panel3.add(button3);
                button3.setBounds(350, 135, 235, 45);

                //======== scrollPane3 ========
                {
                    scrollPane3.setViewportView(table2);
                }
                panel3.add(scrollPane3);
                scrollPane3.setBounds(40, 230, 680, 170);

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
            tabbedPane1.addTab("\u5458\u5de5\u4efb\u52a1\u5206\u914d", panel3);

            //======== panel4 ========
            {
                panel4.setLayout(null);

                //---- label7 ----
                label7.setText("\u8bf7\u8f93\u5165\u60a8\u7684\u516c\u53f8\u540d\u79f0\uff1a");
                label7.setFont(label7.getFont().deriveFont(label7.getFont().getSize() + 8f));
                panel4.add(label7);
                label7.setBounds(125, 60, 275, 40);
                panel4.add(textField6);
                textField6.setBounds(350, 60, 235, 40);

                //---- button4 ----
                button4.setText("\u67e5\u770b\u6240\u6709\u5458\u5de5\u7684\u5de5\u4f5c\u60c5\u51b5");
                button4.setFont(button4.getFont().deriveFont(button4.getFont().getSize() + 5f));
                button4.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button4MouseClicked(e);
                        button4MouseClicked(e);
                    }
                });
                panel4.add(button4);
                button4.setBounds(350, 135, 235, 45);

                //======== scrollPane4 ========
                {
                    scrollPane4.setViewportView(table3);
                }
                panel4.add(scrollPane4);
                scrollPane4.setBounds(20, 215, 725, 230);

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
            tabbedPane1.addTab("\u5458\u5de5\u5de5\u4f5c\u60c5\u51b5", panel4);
        }
        contentPane.add(tabbedPane1);
        tabbedPane1.setBounds(0, 0, 775, 550);

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
        setSize(775, 575);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTextField textField1;
    private JLabel label1;
    private JTextField textField2;
    private JLabel label2;
    private JTextField textField3;
    private JLabel label3;
    private JLabel label4;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    private JButton button1;
    private JPanel panel2;
    private JTextField textField4;
    private JLabel label5;
    private JButton button2;
    private JScrollPane scrollPane2;
    private JTable table1;
    private JPanel panel3;
    private JTextField textField5;
    private JLabel label6;
    private JButton button3;
    private JScrollPane scrollPane3;
    private JTable table2;
    private JPanel panel4;
    private JLabel label7;
    private JTextField textField6;
    private JButton button4;
    private JScrollPane scrollPane4;
    private JTable table3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables




    public static void main(String[] args) {
        new EnterpriseUI();
    }
}
