/*
 * Created by JFormDesigner on Wed Dec 22 09:45:27 CST 2021
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
public class RIupdateUI extends JFrame {
    jdbcutil jdbcutil;
    public RIupdateUI() {
        initComponents();
        setVisible(true);
        jdbcutil = new jdbcutil();
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
                    int n = JOptionPane.showConfirmDialog(null, "已查找到简历信息，是否修改该简历信息？", "1", JOptionPane.YES_NO_OPTION);
                    if (n == 0){
                        String sql2 = "update resume set gender='" + gender + "',school='" + school + "',text='" + text + "' where name='" + name + "'";
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
                }
                else{
                    JOptionPane.showMessageDialog(null,"未查找到简历信息！");
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
        button1 = new JButton();
        textArea1 = new JTextArea();
        label5 = new JLabel();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(textField1);
        textField1.setBounds(305, 65, 260, 40);

        //---- label1 ----
        label1.setText("\u59d3\u540d\uff1a");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 4f));
        contentPane.add(label1);
        label1.setBounds(225, 60, 95, 52);

        //---- label2 ----
        label2.setText("\u6027\u522b\uff1a");
        label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 4f));
        contentPane.add(label2);
        label2.setBounds(225, 125, 130, 52);
        contentPane.add(textField2);
        textField2.setBounds(305, 130, 260, 40);

        //---- label4 ----
        label4.setText("\u6bd5\u4e1a\u9662\u6821\uff1a");
        label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 4f));
        contentPane.add(label4);
        label4.setBounds(195, 190, 190, 52);
        contentPane.add(textField4);
        textField4.setBounds(305, 195, 260, 40);

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
        button1.setBounds(390, 430, 90, 40);
        contentPane.add(textArea1);
        textArea1.setBounds(310, 265, 248, 128);

        //---- label5 ----
        label5.setText("\u8bc4\u4ef7\u53ca\u6240\u83b7\u8363\u8a89\uff1a");
        label5.setFont(label5.getFont().deriveFont(label5.getFont().getSize() + 4f));
        contentPane.add(label5);
        label5.setBounds(150, 250, 155, 52);

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
    private JButton button1;
    private JTextArea textArea1;
    private JLabel label5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
