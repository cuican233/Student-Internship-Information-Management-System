/*
 * Created by JFormDesigner on Tue Dec 21 21:42:17 CST 2021
 */

package shixi.views.administrators.QI;

import shixi.utils.jdbcutil;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

/**
 * @author Brainrain
 */
public class QIaddUI extends JFrame {
    jdbcutil jdbcutil;
    public QIaddUI() {
        initComponents();
        setVisible(true);
        jdbcutil= new jdbcutil();
        jdbcutil.connect();
    }

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        String company =textField1.getText();
        String address =textField2.getText();
        String require =textField4.getText();
        String scale =textField5.getText();

        if (company != null && address != null && require != null && scale != null) {

            String sql1 = "select * from enterprise where company='" + company + "'";
            ResultSet rs1 = jdbcutil.getResultSet(sql1);
            try {
                if (rs1.next()) {
                    System.out.println("企业信息已存在！");
                    JOptionPane.showMessageDialog(null,"企业信息已存在！，请重新输入！");
                } else {
                    String sql2 = "insert into enterprise values('" + company + "','" + address + "','" + require + "','" + scale + "')";
                    int update = jdbcutil.update(sql2);
                    if (update > 0) {
                        JOptionPane.showMessageDialog(null,"企业信息添加成功！");
                        textField1.setText("");
                        textField2.setText("");
                        textField4.setText("");
                        textField5.setText("");
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
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField2 = new JTextField();
        label4 = new JLabel();
        textField4 = new JTextField();
        label5 = new JLabel();
        textField5 = new JTextField();
        button1 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u5355\u4f4d\u540d\u79f0\uff1a");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 4f));
        contentPane.add(label1);
        label1.setBounds(195, 65, 95, 52);
        contentPane.add(textField1);
        textField1.setBounds(295, 75, 260, 40);

        //---- label2 ----
        label2.setText("\u5730\u5740\uff1a");
        label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 4f));
        contentPane.add(label2);
        label2.setBounds(230, 155, 95, 52);
        contentPane.add(textField2);
        textField2.setBounds(295, 160, 260, 40);

        //---- label4 ----
        label4.setText("\u62db\u8058\u8981\u6c42\uff1a");
        label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 4f));
        contentPane.add(label4);
        label4.setBounds(200, 230, 95, 52);
        contentPane.add(textField4);
        textField4.setBounds(295, 245, 260, 40);

        //---- label5 ----
        label5.setText("\u62db\u8058\u89c4\u6a21\uff1a");
        label5.setFont(label5.getFont().deriveFont(label5.getFont().getSize() + 4f));
        contentPane.add(label5);
        label5.setBounds(200, 325, 95, 52);
        contentPane.add(textField5);
        textField5.setBounds(295, 330, 260, 40);

        //---- button1 ----
        button1.setText("\u6dfb\u52a0");
        button1.setFont(button1.getFont().deriveFont(button1.getFont().getSize() + 4f));
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
                button1MouseClicked(e);
                button1MouseClicked(e);
            }
        });
        contentPane.add(button1);
        button1.setBounds(380, 425, 100, 40);

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
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JLabel label4;
    private JTextField textField4;
    private JLabel label5;
    private JTextField textField5;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
