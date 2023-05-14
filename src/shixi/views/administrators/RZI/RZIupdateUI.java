/*
 * Created by JFormDesigner on Wed Dec 22 10:20:07 CST 2021
 */

package shixi.views.administrators.RZI;

import shixi.utils.jdbcutil;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author Brainrain
 */
public class RZIupdateUI extends JFrame {
    jdbcutil jdbcutil;
    public RZIupdateUI() {
        initComponents();
        setVisible(true);
        jdbcutil = new jdbcutil();
        jdbcutil.connect();
    }

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        textField4 = new JTextField();
        textField2 = new JTextField();
        textField1 = new JTextField();
        label1 = new JLabel();
        label2 = new JLabel();
        label4 = new JLabel();
        button1 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(textField4);
        textField4.setBounds(225, 255, 260, 40);
        contentPane.add(textField2);
        textField2.setBounds(225, 170, 260, 40);
        contentPane.add(textField1);
        textField1.setBounds(225, 85, 260, 40);

        //---- label1 ----
        label1.setText("\u59d3\u540d\uff1a");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 4f));
        contentPane.add(label1);
        label1.setBounds(155, 80, 95, 52);

        //---- label2 ----
        label2.setText("\u4fee\u6539\u540e\u7684\u4efb\u52a1\u4e8b\u9879\uff1a");
        label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 4f));
        contentPane.add(label2);
        label2.setBounds(65, 165, 150, 52);

        //---- label4 ----
        label4.setText("\u4fee\u6539\u540e\u7684\u8fdb\u5ea6\uff1a");
        label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 4f));
        contentPane.add(label4);
        label4.setBounds(95, 250, 145, 52);

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
        button1.setBounds(305, 335, 95, 35);

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
        setSize(640, 470);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JTextField textField4;
    private JTextField textField2;
    private JTextField textField1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label4;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
