/*
 * Created by JFormDesigner on Wed Dec 22 09:17:55 CST 2021
 */

package shixi.views.administrators.RI;

import shixi.utils.jdbcutil;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

/**
 * @author Brainrain
 */
public class RIadd extends JFrame {
    jdbcutil jdbcutil;
    public RIadd() {
        initComponents();
        setVisible(true);
        jdbcutil= new jdbcutil();
        jdbcutil.connect();
    }

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        String name =textField1.getText();
        String gender =textField2.getText();
        String school =textField4.getText();
        String text =textArea1.getText();

        if (name != null && gender != null && school != null && text != null) {

            String sql1 = "select * from resume where name='" + name + "'";
            ResultSet rs1 = jdbcutil.getResultSet(sql1);
            try {
                if (rs1.next()) {
                    System.out.println("简历信息已存在！");
                    JOptionPane.showMessageDialog(null,"简历信息已存在！，请重新输入！");
                } else {
                    String sql2 = "insert into resume values('" + name + "','" + gender + "','" + school + "','" + text + "')";
                    int update = jdbcutil.update(sql2);
                    if (update > 0) {
                        JOptionPane.showMessageDialog(null,"简历信息添加成功！");
                        textField1.setText("");
                        textField2.setText("");
                        textField4.setText("");
                        textArea1.setText("");
                        setVisible(false);
                    }
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
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        button1 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(textField1);
        textField1.setBounds(300, 80, 260, 40);

        //---- label1 ----
        label1.setText("\u59d3\u540d\uff1a");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 4f));
        contentPane.add(label1);
        label1.setBounds(225, 70, 95, 52);

        //---- label2 ----
        label2.setText("\u6027\u522b\uff1a");
        label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 4f));
        contentPane.add(label2);
        label2.setBounds(225, 140, 95, 52);
        contentPane.add(textField2);
        textField2.setBounds(300, 150, 260, 40);

        //---- label4 ----
        label4.setText("\u6bd5\u4e1a\u9662\u6821\uff1a");
        label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 4f));
        contentPane.add(label4);
        label4.setBounds(195, 215, 95, 52);
        contentPane.add(textField4);
        textField4.setBounds(300, 220, 260, 40);

        //---- label5 ----
        label5.setText("\u8bc4\u4ef7\u53ca\u6240\u83b7\u8363\u8a89\uff1a");
        label5.setFont(label5.getFont().deriveFont(label5.getFont().getSize() + 4f));
        contentPane.add(label5);
        label5.setBounds(150, 270, 155, 52);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(textArea1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(305, 290, 250, 130);

        //---- button1 ----
        button1.setText("\u6dfb\u52a0");
        button1.setFont(button1.getFont().deriveFont(button1.getFont().getSize() + 4f));
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });
        contentPane.add(button1);
        button1.setBounds(385, 445, 90, 40);

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
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
