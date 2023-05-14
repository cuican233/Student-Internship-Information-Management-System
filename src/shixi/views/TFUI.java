/*
 * Created by JFormDesigner on Wed Dec 22 14:26:41 CST 2021
 */

package shixi.views;

import java.awt.*;
import javax.swing.*;

/**
 * @author Brainrain
 */
public class TFUI extends JFrame {
    //定义组件
    ImageIcon background;
    JLabel label;//用于放标签
    JLabel label2;
    JButton button;
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new TFUI();
    }
    public TFUI() {
        initComponents();
        setVisible(true);

        button = new JButton("图片");   //创建一个按钮
        label2=new JLabel("风景");     //创建一个标签

        background = new ImageIcon("D:/233/图/1.jpg");	//创建一个背景图片
        label = new JLabel(background);		//把背景图片添加到标签里
        label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());	//把标签设置为和图片等高等宽
//        myPanel.setOpaque(false);					//把我的面板设置为不可视
//        myPanel.setLayout(new FlowLayout());		//把我的面板设置为流动布局
        this.getLayeredPane().setLayout(null);		//把分层面板的布局置空
//        myPanel.add(label2);
//        myPanel.add(button);	//把按钮添加到我的面板里
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));		//把标签添加到分层面板的最底层
        //设置界面属性
        setTitle("测试背景图");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

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
        setSize(660, 525);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
