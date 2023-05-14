/*
 * Created by JFormDesigner on Wed Dec 22 10:00:33 CST 2021
 */

package shixi.views.administrators.REI;

import shixi.utils.jdbcutil;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

/**
 * @author Brainrain
 */
public class REIupdateUI extends JFrame {
    jdbcutil jdbcutil;
    public REIupdateUI() {
        initComponents();
        setVisible(true);
        jdbcutil = new jdbcutil();
        jdbcutil.connect();
    }

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        String company =textField1.getText();
        String position =textField2.getText();
        String requirement =textField4.getText();
        String Salary =textField5.getText();
        String state =textField3.getText();
        String employee =textField6.getText();

        if (company != null && position != null && requirement != null && Salary != null&& state != null&& employee != null) {
            String sql1 = "select * from recruitment where company='" + company + "'";
            ResultSet rs1 = jdbcutil.getResultSet(sql1);
            try {
                if (rs1.next()) {
                    int n = JOptionPane.showConfirmDialog(null, "已查找到招聘信息，是否修改该招聘信息？", "1", JOptionPane.YES_NO_OPTION);
                    if (n == 0){
                        String sql2 = "update recruitment set position='" + position + "',requirement='" + requirement + "',Salary='" + Salary + "' ,employee='" + employee + "'where company='" + company + "'";
                        int update = jdbcutil.update(sql2);
                        if (update > 0) {
                            JOptionPane.showMessageDialog(null,"招聘信息添加成功！");
                            textField1.setText("");
                            textField2.setText("");
                            textField3.setText("");
                            textField4.setText("");
                            textField5.setText("");
                            textField6.setText("");
                            setVisible(false);
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
        button1 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(textField1);
        textField1.setBounds(295, 60, 260, 40);

        //---- label1 ----
        label1.setText("\u5355\u4f4d\u540d\u79f0\uff1a");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 4f));
        contentPane.add(label1);
        label1.setBounds(210, 50, 95, 52);

        //---- label2 ----
        label2.setText("\u4fee\u6539\u540e\u7684\u62db\u8058\u804c\u4f4d\uff1a");
        label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 4f));
        contentPane.add(label2);
        label2.setBounds(150, 120, 155, 52);
        contentPane.add(textField2);
        textField2.setBounds(295, 125, 260, 40);

        //---- label4 ----
        label4.setText("\u4fee\u6539\u540e\u7684\u8981\u6c42\uff1a");
        label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 4f));
        contentPane.add(label4);
        label4.setBounds(180, 180, 195, 52);
        contentPane.add(textField4);
        textField4.setBounds(295, 185, 260, 40);

        //---- label5 ----
        label5.setText("\u4fee\u6539\u540e\u7684\u85aa\u8d44\u5f85\u9047\uff1a");
        label5.setFont(label5.getFont().deriveFont(label5.getFont().getSize() + 4f));
        contentPane.add(label5);
        label5.setBounds(145, 240, 165, 52);
        contentPane.add(textField5);
        textField5.setBounds(295, 250, 260, 40);

        //---- label3 ----
        label3.setText("\u4fee\u6539\u540e\u7684\u72b6\u6001\uff1a");
        label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 4f));
        contentPane.add(label3);
        label3.setBounds(175, 305, 180, 52);
        contentPane.add(textField3);
        textField3.setBounds(295, 315, 260, 40);

        //---- label6 ----
        label6.setText("\u4fee\u6539\u540e\u7684\u7533\u8bf7\u4eba\uff1a");
        label6.setFont(label6.getFont().deriveFont(label6.getFont().getSize() + 4f));
        contentPane.add(label6);
        label6.setBounds(160, 375, 160, 52);
        contentPane.add(textField6);
        textField6.setBounds(295, 385, 260, 40);

        //---- button1 ----
        button1.setText("\u4fee\u6539");
        button1.setFont(button1.getFont().deriveFont(button1.getFont().getSize() + 5f));
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });
        contentPane.add(button1);
        button1.setBounds(375, 445, 95, 40);

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
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
