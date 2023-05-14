/*
 * Created by JFormDesigner on Tue Dec 21 20:56:25 CST 2021
 */

package shixi.views.administrators;

import shixi.utils.jdbcutil;
import shixi.views.administrators.QI.QIaddUI;
import shixi.views.administrators.QI.QIupdateUI;
import shixi.views.administrators.REI.REIaddUI;
import shixi.views.administrators.REI.REIupdateUI;
import shixi.views.administrators.RI.RIadd;
import shixi.views.administrators.RI.RIupdateUI;
import shixi.views.administrators.RZI.RZIaddUI;
import shixi.views.administrators.RZI.RZIupdateUI;
import shixi.views.administrators.SIUI.SIaddUI;
import shixi.views.administrators.SIUI.SIupdateUI;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author Brainrain
 */
public class AdministratorsUI extends JFrame {
    jdbcutil jdbcutil;
    public AdministratorsUI() {
        initComponents();
        setVisible(true);
        jdbcutil = new jdbcutil();
        jdbcutil.connect();

        panel1.setOpaque(false);
        panel2.setOpaque(false);
        panel3.setOpaque(false);
        panel4.setOpaque(false);
        panel5.setOpaque(false);
        panel6.setOpaque(false);
        tabbedPane1.setOpaque(false);
        this.getLayeredPane().setLayout(null);
        textField1.setOpaque(false);
        textField2.setOpaque(false);
        textField3.setOpaque(false);
        textField4.setOpaque(false);
        textField5.setOpaque(false);
        textField7.setOpaque(false);

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
        button14.setContentAreaFilled(false);
        button15.setContentAreaFilled(false);
        button16.setContentAreaFilled(false);
        button17.setContentAreaFilled(false);
        button18.setContentAreaFilled(false);
        button19.setContentAreaFilled(false);
        button20.setContentAreaFilled(false);
        button21.setContentAreaFilled(false);
        button22.setContentAreaFilled(false);

    }

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        new SIaddUI();

    }

    private void button2MouseClicked(MouseEvent e) {
        // TODO add your code here
        String name = textField1.getText();

        if (name != null) {

            String sql1 = "select * from student where name='" + name + "'";
            ResultSet rs1 = jdbcutil.getResultSet(sql1);
            try {
                if (rs1.next()) {
                    System.out.println("查找到学生信息！");
                    int n = JOptionPane.showConfirmDialog(null, "已查找到学生，是否删除该学生信息？", "1", JOptionPane.YES_NO_OPTION);
                    if (n == 0) {
                        String sql2 = "delete from student where name ='" + name + "'";
                        int update = jdbcutil.update(sql2);
                        if (update > 0) {
                            System.out.println("该学生信息已删除！");
                            JOptionPane.showMessageDialog(null,"学生信息已全部删除！");
                            textField1.setText("");
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"未查找到学生信息！");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    private void button3MouseClicked(MouseEvent e) {
        // TODO add your code here
        new SIupdateUI();
    }

    private void button4MouseClicked(MouseEvent e) {
        // TODO add your code here
        Vector rowDate, columnNames;
        String name = textField1.getText();
        columnNames = new Vector();
        columnNames.add("姓名");
        columnNames.add("院系");
        columnNames.add("专业");
        columnNames.add("班级");
        columnNames.add("老师");
        columnNames.add("成绩");
        columnNames.add("擅长技能");
        rowDate = new Vector();

        try {
            String sql = "SELECT *FROM student where name ='" + name + "'";
            ResultSet rs = jdbcutil.getResultSet(sql);
            if (rs.next()) {
                Vector hang = new Vector();
                hang.add(rs.getString(1));
                hang.add(rs.getString(2));
                hang.add(rs.getString(3));
                hang.add(rs.getString(4));
                hang.add(rs.getString(5));
                hang.add(rs.getString(6));
                hang.add(rs.getString(7));
                rowDate.add(hang);
            }
        } catch (SQLException d) {
            d.printStackTrace();
        }
        DefaultTableModel model = new DefaultTableModel(rowDate,columnNames);
        table1.setModel(model);
        table1.setRowHeight(80);
    }

    private void button9MouseClicked(MouseEvent e) {
        // TODO add your code here
        new QIaddUI();
    }

    private void button10MouseClicked(MouseEvent e) {
        // TODO add your code here
        String company = textField2.getText();

        if (company != null) {

            String sql1 = "select * from enterprise where company='" + company + "'";
            ResultSet rs1 = jdbcutil.getResultSet(sql1);
            try {
                if (rs1.next()) {
                    System.out.println("查找到企业信息！");
                    int n = JOptionPane.showConfirmDialog(null, "已查找到企业，是否删除该企业信息？", "1", JOptionPane.YES_NO_OPTION);
                    if (n == 0) {
                        String sql2 = "delete from enterprise where company ='" + company + "'";
                        int update = jdbcutil.update(sql2);
                        if (update > 0) {
                            System.out.println("该企业信息已删除！");
                            JOptionPane.showMessageDialog(null,"企业信息已全部删除！");
                            textField1.setText("");
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"未查找到企业信息！");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    private void button11MouseClicked(MouseEvent e) {
        // TODO add your code here
        new QIupdateUI();
    }

    private void button12MouseClicked(MouseEvent e) {
        // TODO add your code here

        Vector rowDate, columnNames;
        String company = textField2.getText();
        columnNames = new Vector();
        columnNames.add("单位名称");
        columnNames.add("地址");
        columnNames.add("招聘要求");
        columnNames.add("招聘规模");
        rowDate = new Vector();

        try {
            String sql = "SELECT *FROM enterprise where company ='" + company + "'";
            ResultSet rs = jdbcutil.getResultSet(sql);
            if (rs.next()) {
                Vector hang = new Vector();
                hang.add(rs.getString(1));
                hang.add(rs.getString(2));
                hang.add(rs.getString(3));
                hang.add(rs.getString(4));
                rowDate.add(hang);
            }
        } catch (SQLException d) {
            d.printStackTrace();
        }
        DefaultTableModel model = new DefaultTableModel(rowDate,columnNames);
        table2.setModel(model);
        table2.setRowHeight(80);
    }

    private void button5MouseClicked(MouseEvent e) {
        // TODO add your code here
        new RIadd();
    }

    private void button6MouseClicked(MouseEvent e) {
        // TODO add your code here
        String name = textField3.getText();

        if (name != null) {

            String sql1 = "select * from resume where name='" + name + "'";
            ResultSet rs1 = jdbcutil.getResultSet(sql1);
            try {
                if (rs1.next()) {
                    System.out.println("查找到简历信息！");
                    int n = JOptionPane.showConfirmDialog(null, "已查找到简历，是否删除该简历信息？", "1", JOptionPane.YES_NO_OPTION);
                    if (n == 0) {
                        String sql2 = "delete from resume where name ='" + name + "'";
                        int update = jdbcutil.update(sql2);
                        if (update > 0) {
                            System.out.println("该企业信息已删除！");
                            JOptionPane.showMessageDialog(null,"简历信息已全部删除！");
                            textField1.setText("");
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"未查找到简历信息！");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void button7MouseClicked(MouseEvent e) {
        // TODO add your code here
        new RIupdateUI();
    }

    private void button8MouseClicked(MouseEvent e) {
        // TODO add your code here
        Vector rowDate, columnNames;
        String name = textField3.getText();
        columnNames = new Vector();
        columnNames.add("姓名");
        columnNames.add("性别");
        columnNames.add("毕业院校");
        columnNames.add("自我评价及所获荣誉");
        columnNames.add("图片路径");

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
                hang.add(rs.getString(5));
                rowDate.add(hang);
            }
        } catch (SQLException d) {
            d.printStackTrace();
        }
        DefaultTableModel model = new DefaultTableModel(rowDate,columnNames);
        table3.setModel(model);
        table3.setRowHeight(80);
    }

    private void button13MouseClicked(MouseEvent e) {
        // TODO add your code here
        new REIaddUI();
    }

    private void button14MouseClicked(MouseEvent e) {
        // TODO add your code here
        String company = textField4.getText();

        if (company != null) {

            String sql1 = "select * from recruitment where company='" + company + "'";
            ResultSet rs1 = jdbcutil.getResultSet(sql1);
            try {
                if (rs1.next()) {
                    System.out.println("查找到招聘信息！");
                    int n = JOptionPane.showConfirmDialog(null, "已查找到招聘信息，是否删除该招聘信息？", "1", JOptionPane.YES_NO_OPTION);
                    if (n == 0) {
                        String sql2 = "delete from recruitment where company ='" + company + "'";
                        int update = jdbcutil.update(sql2);
                        if (update > 0) {
                            System.out.println("该招聘信息已删除！");
                            JOptionPane.showMessageDialog(null,"招聘信息已全部删除！");
                            textField5.setText("");
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"未查找到招聘信息！");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    private void button15MouseClicked(MouseEvent e) {
        // TODO add your code here
        new REIupdateUI();
    }

    private void button16MouseClicked(MouseEvent e) {
        // TODO add your code here
        Vector rowDate, columnNames;
        String company = textField4.getText();
        columnNames = new Vector();
        columnNames.add("公司编号");
        columnNames.add("单位名称");
        columnNames.add("招聘职位");
        columnNames.add("招聘要求");
        columnNames.add("薪资待遇");
        columnNames.add("状态");
        columnNames.add("申请人");
        rowDate = new Vector();

        try {
            String sql = "SELECT *FROM recruitment where company ='" + company + "'";
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
                rowDate.add(hang);
            }
        } catch (SQLException d) {
            d.printStackTrace();
        }
        DefaultTableModel model = new DefaultTableModel(rowDate,columnNames);
        table4.setModel(model);
        table4.setRowHeight(80);
    }

    private void button17MouseClicked(MouseEvent e) {
        // TODO add your code here
        new RZIaddUI();
    }

    private void button18MouseClicked(MouseEvent e) {
        // TODO add your code here
        String name = textField5.getText();

        if (name != null) {

            String sql1 = "select * from journal where name='" + name + "'";
            ResultSet rs1 = jdbcutil.getResultSet(sql1);
            try {
                if (rs1.next()) {
                    int n = JOptionPane.showConfirmDialog(null, "已查找到日志信息，是否删除该日志信息？", "1", JOptionPane.YES_NO_OPTION);
                    if (n == 0) {
                        String sql2 = "delete from journal where name ='" + name + "'";
                        int update = jdbcutil.update(sql2);
                        if (update > 0) {
                            System.out.println("该招聘信息已删除！");
                            JOptionPane.showMessageDialog(null,"日志信息已全部删除！");
                            textField5.setText("");
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"未查找到日志信息！");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void button19MouseClicked(MouseEvent e) {
        // TODO add your code here
        new RZIupdateUI();
    }

    private void button20MouseClicked(MouseEvent e) {
        // TODO add your code here
        Vector rowDate, columnNames;
        String name = textField5.getText();
        columnNames = new Vector();
        columnNames.add("姓名");
        columnNames.add("任务事项");
        columnNames.add("进度");

        rowDate = new Vector();

        try {
            String sql = "SELECT *FROM journal where name ='" + name + "'";
            ResultSet rs = jdbcutil.getResultSet(sql);
            while (rs.next()) {
                Vector hang = new Vector();
                hang.add(rs.getString(1));
                hang.add(rs.getString(2));
                hang.add(rs.getString(3));
                rowDate.add(hang);
            }
        } catch (SQLException d) {
            d.printStackTrace();
        }
        DefaultTableModel model = new DefaultTableModel(rowDate,columnNames);
        table5.setModel(model);
        table5.setRowHeight(80);
    }

    private void button21MouseClicked(MouseEvent e) {
        // TODO add your code here

        Vector rowDate, columnNames;
        columnNames = new Vector();
        columnNames.add("姓名");
        columnNames.add("企业打分");
        columnNames.add("老师打分");
        columnNames.add("总分");
        rowDate = new Vector();
        try {
            String sql = "SELECT *FROM finalscore order by finalscore DESC";
            ResultSet rs = jdbcutil.getResultSet(sql);
            while (rs.next()) {
                Vector hang = new Vector();
                hang.add(rs.getString(1));
                hang.add(rs.getString(2));
                hang.add(rs.getString(3));
                hang.add(rs.getString(4));
                rowDate.add(hang);
            }
        } catch (SQLException d) {
            d.printStackTrace();
        }
        DefaultTableModel model = new DefaultTableModel(rowDate,columnNames);
        table6.setModel(model);
        table6.setRowHeight(80);
    }

    private void button22MouseClicked(MouseEvent e) {
        // TODO add your code here

        Vector rowDate, columnNames;
        String name = textField7.getText();
        columnNames = new Vector();
        columnNames.add("姓名");
        columnNames.add("企业打分");
        columnNames.add("老师打分");
        columnNames.add("总分");
        rowDate = new Vector();

        try {
            String sql = "SELECT *FROM finalscore where name ='" + name + "'";
            ResultSet rs = jdbcutil.getResultSet(sql);
            while (rs.next()) {
                Vector hang = new Vector();
                hang.add(rs.getString(1));
                hang.add(rs.getString(2));
                hang.add(rs.getString(3));
                hang.add(rs.getString(4));
                rowDate.add(hang);
            }
        } catch (SQLException d) {
            d.printStackTrace();
        }
        DefaultTableModel model = new DefaultTableModel(rowDate,columnNames);
        table6.setModel(model);
        table6.setRowHeight(80);

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        tabbedPane1 = new JTabbedPane();
        panel1 = new JPanel();
        label1 = new JLabel();
        textField1 = new JTextField();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        panel2 = new JPanel();
        panel7 = new JPanel();
        label2 = new JLabel();
        textField2 = new JTextField();
        scrollPane2 = new JScrollPane();
        table2 = new JTable();
        button9 = new JButton();
        button10 = new JButton();
        button11 = new JButton();
        button12 = new JButton();
        panel3 = new JPanel();
        label3 = new JLabel();
        textField3 = new JTextField();
        button5 = new JButton();
        button6 = new JButton();
        button7 = new JButton();
        button8 = new JButton();
        scrollPane3 = new JScrollPane();
        table3 = new JTable();
        panel4 = new JPanel();
        label4 = new JLabel();
        textField4 = new JTextField();
        button13 = new JButton();
        button14 = new JButton();
        button15 = new JButton();
        button16 = new JButton();
        scrollPane4 = new JScrollPane();
        table4 = new JTable();
        panel5 = new JPanel();
        label5 = new JLabel();
        textField5 = new JTextField();
        button17 = new JButton();
        button18 = new JButton();
        button19 = new JButton();
        button20 = new JButton();
        scrollPane5 = new JScrollPane();
        table5 = new JTable();
        panel6 = new JPanel();
        label7 = new JLabel();
        textField7 = new JTextField();
        button21 = new JButton();
        button22 = new JButton();
        scrollPane6 = new JScrollPane();
        table6 = new JTable();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== tabbedPane1 ========
        {
            tabbedPane1.setFont(tabbedPane1.getFont().deriveFont(tabbedPane1.getFont().getSize() + 4f));

            //======== panel1 ========
            {
                panel1.setLayout(null);

                //---- label1 ----
                label1.setText("\u8f93\u5165\u5b66\u751f\u59d3\u540d\uff1a");
                label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 5f));
                panel1.add(label1);
                label1.setBounds(190, 55, 180, 40);
                panel1.add(textField1);
                textField1.setBounds(315, 55, 225, 40);

                //---- button1 ----
                button1.setText("\u6dfb\u52a0");
                button1.setFont(button1.getFont().deriveFont(button1.getFont().getSize() + 4f));
                button1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button1MouseClicked(e);
                    }
                });
                panel1.add(button1);
                button1.setBounds(90, 130, 125, 35);

                //---- button2 ----
                button2.setText("\u5220\u9664");
                button2.setFont(button2.getFont().deriveFont(button2.getFont().getSize() + 4f));
                button2.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button2MouseClicked(e);
                    }
                });
                panel1.add(button2);
                button2.setBounds(250, 130, 125, 35);

                //---- button3 ----
                button3.setText("\u4fee\u6539");
                button3.setFont(button3.getFont().deriveFont(button3.getFont().getSize() + 4f));
                button3.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button3MouseClicked(e);
                    }
                });
                panel1.add(button3);
                button3.setBounds(410, 130, 125, 35);

                //---- button4 ----
                button4.setText("\u67e5\u8be2");
                button4.setFont(button4.getFont().deriveFont(button4.getFont().getSize() + 4f));
                button4.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button4MouseClicked(e);
                    }
                });
                panel1.add(button4);
                button4.setBounds(565, 130, 125, 35);

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(table1);
                }
                panel1.add(scrollPane1);
                scrollPane1.setBounds(40, 215, 680, 135);

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
            tabbedPane1.addTab("\u5b66\u751f\u4fe1\u606f\u7ba1\u7406", panel1);

            //======== panel2 ========
            {
                panel2.setLayout(null);

                //======== panel7 ========
                {
                    panel7.setLayout(null);

                    //---- label2 ----
                    label2.setText("\u8f93\u5165\u4f01\u4e1a\u540d\u79f0\uff1a");
                    label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 5f));
                    panel7.add(label2);
                    label2.setBounds(190, 55, 180, 40);
                    panel7.add(textField2);
                    textField2.setBounds(315, 55, 225, 40);

                    //======== scrollPane2 ========
                    {
                        scrollPane2.setViewportView(table2);
                    }
                    panel7.add(scrollPane2);
                    scrollPane2.setBounds(40, 215, 680, 135);

                    //---- button9 ----
                    button9.setText("\u6dfb\u52a0");
                    button9.setFont(button9.getFont().deriveFont(button9.getFont().getSize() + 4f));
                    button9.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            button9MouseClicked(e);
                        }
                    });
                    panel7.add(button9);
                    button9.setBounds(85, 130, 125, 35);

                    //---- button10 ----
                    button10.setText("\u5220\u9664");
                    button10.setFont(button10.getFont().deriveFont(button10.getFont().getSize() + 4f));
                    button10.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            button10MouseClicked(e);
                        }
                    });
                    panel7.add(button10);
                    button10.setBounds(250, 130, 125, 35);

                    //---- button11 ----
                    button11.setText("\u4fee\u6539");
                    button11.setFont(button11.getFont().deriveFont(button11.getFont().getSize() + 4f));
                    button11.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            button11MouseClicked(e);
                        }
                    });
                    panel7.add(button11);
                    button11.setBounds(410, 130, 125, 35);

                    //---- button12 ----
                    button12.setText("\u67e5\u8be2");
                    button12.setFont(button12.getFont().deriveFont(button12.getFont().getSize() + 4f));
                    button12.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            button12MouseClicked(e);
                        }
                    });
                    panel7.add(button12);
                    button12.setBounds(565, 130, 125, 35);

                    { // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel7.getComponentCount(); i++) {
                            Rectangle bounds = panel7.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel7.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel7.setMinimumSize(preferredSize);
                        panel7.setPreferredSize(preferredSize);
                    }
                }
                panel2.add(panel7);
                panel7.setBounds(0, 0, 765, 503);

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
            tabbedPane1.addTab("\u4f01\u4e1a\u7684\u4fe1\u606f\u7ba1\u7406", panel2);

            //======== panel3 ========
            {
                panel3.setLayout(null);

                //---- label3 ----
                label3.setText("\u8f93\u5165\u59d3\u540d\uff1a");
                label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 5f));
                panel3.add(label3);
                label3.setBounds(220, 55, 180, 40);
                panel3.add(textField3);
                textField3.setBounds(330, 55, 225, 40);

                //---- button5 ----
                button5.setText("\u6dfb\u52a0");
                button5.setFont(button5.getFont().deriveFont(button5.getFont().getSize() + 5f));
                button5.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button5MouseClicked(e);
                    }
                });
                panel3.add(button5);
                button5.setBounds(85, 130, 125, 35);

                //---- button6 ----
                button6.setText("\u5220\u9664");
                button6.setFont(button6.getFont().deriveFont(button6.getFont().getSize() + 5f));
                button6.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button6MouseClicked(e);
                    }
                });
                panel3.add(button6);
                button6.setBounds(245, 130, 125, 35);

                //---- button7 ----
                button7.setText("\u4fee\u6539");
                button7.setFont(button7.getFont().deriveFont(button7.getFont().getSize() + 5f));
                button7.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button7MouseClicked(e);
                    }
                });
                panel3.add(button7);
                button7.setBounds(410, 130, 125, 35);

                //---- button8 ----
                button8.setText("\u67e5\u8be2");
                button8.setFont(button8.getFont().deriveFont(button8.getFont().getSize() + 5f));
                button8.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button8MouseClicked(e);
                    }
                });
                panel3.add(button8);
                button8.setBounds(565, 130, 125, 35);

                //======== scrollPane3 ========
                {
                    scrollPane3.setViewportView(table3);
                }
                panel3.add(scrollPane3);
                scrollPane3.setBounds(40, 215, 680, 135);

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
            tabbedPane1.addTab("\u7b80\u5386\u4fe1\u606f\u7ba1\u7406", panel3);

            //======== panel4 ========
            {
                panel4.setLayout(null);

                //---- label4 ----
                label4.setText("\u8f93\u5165\u516c\u53f8\u5355\u4f4d\u540d\uff1a");
                label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 5f));
                panel4.add(label4);
                label4.setBounds(195, 55, 180, 40);
                panel4.add(textField4);
                textField4.setBounds(330, 55, 225, 40);

                //---- button13 ----
                button13.setText("\u6dfb\u52a0");
                button13.setFont(button13.getFont().deriveFont(button13.getFont().getSize() + 5f));
                button13.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button13MouseClicked(e);
                    }
                });
                panel4.add(button13);
                button13.setBounds(85, 130, 125, 35);

                //---- button14 ----
                button14.setText("\u5220\u9664");
                button14.setFont(button14.getFont().deriveFont(button14.getFont().getSize() + 5f));
                button14.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button14MouseClicked(e);
                    }
                });
                panel4.add(button14);
                button14.setBounds(255, 130, 125, 35);

                //---- button15 ----
                button15.setText("\u4fee\u6539");
                button15.setFont(button15.getFont().deriveFont(button15.getFont().getSize() + 5f));
                button15.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button15MouseClicked(e);
                    }
                });
                panel4.add(button15);
                button15.setBounds(415, 130, 125, 35);

                //---- button16 ----
                button16.setText("\u67e5\u8be2");
                button16.setFont(button16.getFont().deriveFont(button16.getFont().getSize() + 5f));
                button16.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button16MouseClicked(e);
                    }
                });
                panel4.add(button16);
                button16.setBounds(575, 130, 125, 35);

                //======== scrollPane4 ========
                {
                    scrollPane4.setViewportView(table4);
                }
                panel4.add(scrollPane4);
                scrollPane4.setBounds(40, 215, 680, 135);

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
            tabbedPane1.addTab("\u62db\u8058\u4fe1\u606f\u7ba1\u7406", panel4);

            //======== panel5 ========
            {
                panel5.setLayout(null);

                //---- label5 ----
                label5.setText("\u8f93\u5165\u5b66\u751f\u59d3\u540d\uff1a");
                label5.setFont(label5.getFont().deriveFont(label5.getFont().getSize() + 5f));
                panel5.add(label5);
                label5.setBounds(190, 55, 180, 40);
                panel5.add(textField5);
                textField5.setBounds(315, 55, 225, 40);

                //---- button17 ----
                button17.setText("\u6dfb\u52a0");
                button17.setFont(button17.getFont().deriveFont(button17.getFont().getSize() + 5f));
                button17.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button17MouseClicked(e);
                    }
                });
                panel5.add(button17);
                button17.setBounds(85, 130, 125, 35);

                //---- button18 ----
                button18.setText("\u5220\u9664");
                button18.setFont(button18.getFont().deriveFont(button18.getFont().getSize() + 5f));
                button18.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button18MouseClicked(e);
                    }
                });
                panel5.add(button18);
                button18.setBounds(255, 130, 125, 35);

                //---- button19 ----
                button19.setText("\u4fee\u6539");
                button19.setFont(button19.getFont().deriveFont(button19.getFont().getSize() + 5f));
                button19.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button19MouseClicked(e);
                    }
                });
                panel5.add(button19);
                button19.setBounds(415, 130, 125, 35);

                //---- button20 ----
                button20.setText("\u67e5\u8be2");
                button20.setFont(button20.getFont().deriveFont(button20.getFont().getSize() + 5f));
                button20.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button20MouseClicked(e);
                    }
                });
                panel5.add(button20);
                button20.setBounds(575, 130, 125, 35);

                //======== scrollPane5 ========
                {
                    scrollPane5.setViewportView(table5);
                }
                panel5.add(scrollPane5);
                scrollPane5.setBounds(40, 215, 680, 135);

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
            tabbedPane1.addTab("\u5b66\u751f\u65e5\u5fd7\u7ba1\u7406", panel5);

            //======== panel6 ========
            {
                panel6.setLayout(null);

                //---- label7 ----
                label7.setText("\u8bf7\u8f93\u5165\u5b66\u751f\u59d3\u540d\uff1a");
                label7.setFont(label7.getFont().deriveFont(label7.getFont().getSize() + 7f));
                panel6.add(label7);
                label7.setBounds(165, 35, 355, 45);
                panel6.add(textField7);
                textField7.setBounds(335, 35, 280, 45);

                //---- button21 ----
                button21.setText("\u663e\u793a\u6240\u6709\u5b66\u751f\u6210\u7ee9");
                button21.setFont(button21.getFont().deriveFont(button21.getFont().getSize() + 5f));
                button21.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button21MouseClicked(e);
                    }
                });
                panel6.add(button21);
                button21.setBounds(180, 95, 180, 55);

                //---- button22 ----
                button22.setText("\u67e5\u8be2\u5b66\u751f\u6210\u7ee9");
                button22.setFont(button22.getFont().deriveFont(button22.getFont().getSize() + 5f));
                button22.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button22MouseClicked(e);
                    }
                });
                panel6.add(button22);
                button22.setBounds(430, 95, 185, 55);

                //======== scrollPane6 ========
                {
                    scrollPane6.setViewportView(table6);
                }
                panel6.add(scrollPane6);
                scrollPane6.setBounds(30, 160, 705, 315);

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
            tabbedPane1.addTab("\u5b9e\u4e60\u6210\u7ee9\u7ba1\u7406", panel6);
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
        setSize(765, 565);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JLabel label1;
    private JTextField textField1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JPanel panel2;
    private JPanel panel7;
    private JLabel label2;
    private JTextField textField2;
    private JScrollPane scrollPane2;
    private JTable table2;
    private JButton button9;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JPanel panel3;
    private JLabel label3;
    private JTextField textField3;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JScrollPane scrollPane3;
    private JTable table3;
    private JPanel panel4;
    private JLabel label4;
    private JTextField textField4;
    private JButton button13;
    private JButton button14;
    private JButton button15;
    private JButton button16;
    private JScrollPane scrollPane4;
    private JTable table4;
    private JPanel panel5;
    private JLabel label5;
    private JTextField textField5;
    private JButton button17;
    private JButton button18;
    private JButton button19;
    private JButton button20;
    private JScrollPane scrollPane5;
    private JTable table5;
    private JPanel panel6;
    private JLabel label7;
    private JTextField textField7;
    private JButton button21;
    private JButton button22;
    private JScrollPane scrollPane6;
    private JTable table6;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public static void main(String[] args) {
        new AdministratorsUI();
    }
}
