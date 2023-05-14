/*
 * Created by JFormDesigner on Tue Dec 21 21:26:59 CST 2021
 */

package shixi.views.administrators.SIUI;

import shixi.utils.jdbcutil;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

/**
 * @author Brainrain
 */
public class SIupdateUI extends JFrame {
    jdbcutil jdbcutil;
    public SIupdateUI() {
        initComponents();
        setVisible(true);
        jdbcutil = new jdbcutil();
        jdbcutil.connect();
}

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here

        String name =textField1.getText();
        String faculty =textField2.getText();
        String major =textField3.getText();
        String classes =textField4.getText();
        String teacher =textField5.getText();
        String achievement =textField6.getText();
        String skill =textField7.getText();

        if (name != null && faculty != null && major != null && classes != null&& teacher != null&& achievement != null&& skill != null) {
            String sql1 = "select * from student where name='" + name + "'";
            ResultSet rs1 = jdbcutil.getResultSet(sql1);
            try {
                if (rs1.next()) {
                    System.out.println("查找到商品信息！");
                    int n = JOptionPane.showConfirmDialog(null, "已查找到学生信息，是否修改该学生信息？", "1", JOptionPane.YES_NO_OPTION);
                    if (n == 0){
                        String sql2 = "update student set faculty='" + faculty + "',major='" + major + "',classes='" + classes + "',teacher='" + teacher + "',achievement='" + achievement + "',skill='" + skill + "' where name='" + name + "'";
                        int update = jdbcutil.update(sql2);
                        if (update > 0) {
                            JOptionPane.showMessageDialog(null,"学生信息添加成功！");
                            textField1.setText("");
                            textField2.setText("");
                            textField3.setText("");
                            textField4.setText("");
                            textField5.setText("");
                            textField6.setText("");
                            textField7.setText("");
                            setVisible(false);
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

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        textField1 = new JTextField();
        label1 = new JLabel();
        label2 = new JLabel();
        textField2 = new JTextField();
        label4 = new JLabel();
        textField4 = new JTextField();
        label5 = new JLabel();
        textField5 = new JTextField();
        label3 = new JLabel();
        textField3 = new JTextField();
        label6 = new JLabel();
        textField6 = new JTextField();
        label7 = new JLabel();
        textField7 = new JTextField();
        button1 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(textField1);
        textField1.setBounds(295, 65, 260, 40);

        //---- label1 ----
        label1.setText("\u59d3\u540d\uff1a");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 4f));
        contentPane.add(label1);
        label1.setBounds(230, 60, 95, 52);

        //---- label2 ----
        label2.setText("\u4fee\u6539\u540e\u7684\u9662\u7cfb\uff1a");
        label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 4f));
        contentPane.add(label2);
        label2.setBounds(165, 110, 155, 52);
        contentPane.add(textField2);
        textField2.setBounds(295, 120, 260, 40);

        //---- label4 ----
        label4.setText("\u4fee\u6539\u540e\u7684\u4e13\u4e1a\uff1a");
        label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 4f));
        contentPane.add(label4);
        label4.setBounds(165, 170, 195, 52);
        contentPane.add(textField4);
        textField4.setBounds(295, 175, 260, 40);

        //---- label5 ----
        label5.setText("\u4fee\u6539\u540e\u7684\u73ed\u7ea7\uff1a");
        label5.setFont(label5.getFont().deriveFont(label5.getFont().getSize() + 4f));
        contentPane.add(label5);
        label5.setBounds(165, 225, 200, 52);
        contentPane.add(textField5);
        textField5.setBounds(295, 230, 260, 40);

        //---- label3 ----
        label3.setText("\u4fee\u6539\u540e\u7684\u8d1f\u8d23\u6559\u5e08\uff1a");
        label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 4f));
        contentPane.add(label3);
        label3.setBounds(135, 275, 165, 52);
        contentPane.add(textField3);
        textField3.setBounds(295, 285, 260, 40);

        //---- label6 ----
        label6.setText("\u4fee\u6539\u540e\u7684\u5b66\u751f\u6210\u7ee9\uff1a");
        label6.setFont(label6.getFont().deriveFont(label6.getFont().getSize() + 4f));
        contentPane.add(label6);
        label6.setBounds(135, 335, 165, 52);
        contentPane.add(textField6);
        textField6.setBounds(295, 340, 260, 40);

        //---- label7 ----
        label7.setText("\u4fee\u6539\u540e\u7684\u64c5\u957f\u6280\u80fd\uff1a");
        label7.setFont(label7.getFont().deriveFont(label7.getFont().getSize() + 4f));
        contentPane.add(label7);
        label7.setBounds(135, 390, 180, 52);
        contentPane.add(textField7);
        textField7.setBounds(295, 395, 260, 40);

        //---- button1 ----
        button1.setText("\u4fee\u6539");
        button1.setFont(button1.getFont().deriveFont(button1.getFont().getSize() + 4f));
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });
        contentPane.add(button1);
        button1.setBounds(370, 455, 100, 40);

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
    private JTextField textField1;
    private JLabel label1;
    private JLabel label2;
    private JTextField textField2;
    private JLabel label4;
    private JTextField textField4;
    private JLabel label5;
    private JTextField textField5;
    private JLabel label3;
    private JTextField textField3;
    private JLabel label6;
    private JTextField textField6;
    private JLabel label7;
    private JTextField textField7;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
