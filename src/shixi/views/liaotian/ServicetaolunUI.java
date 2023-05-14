/*
 * Created by JFormDesigner on Tue Dec 21 11:01:01 CST 2021
 */

package shixi.views.liaotian;

import shixi.views.service.ServiceUI;

import java.awt.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.util.ArrayList;
import javax.swing.*;

/**
 * @author Brainrain
 */
public class ServicetaolunUI extends JFrame implements Runnable {

    private DatagramSocket server;
    //通过集合保存客户端的地址
    private ArrayList<SocketAddress> clients=new ArrayList<>();

    public ServicetaolunUI() {
        initComponents();
        try {
            //创建服务端
            server = new DatagramSocket(8899);
            //启动服务端线程
            new Thread(this).start();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(textArea1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(75, 80, 625, 380);

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
    private JScrollPane scrollPane1;
    private JTextArea textArea1;

    @Override
    public void run() {

        try {
            while(true) {
                //接收客户端发送的信息
                byte[] data=new byte[255];
                DatagramPacket packet=new DatagramPacket(data,data.length);
                server.receive(packet);
                String string=new String(packet.getData(),0,packet.getLength());
                String string2 = string;
                textArea1.append(string+"\n");
//                ServiceUI.textArea1.append(string2+"\n");
                //获取客户端的地址
                SocketAddress newAddress=packet.getSocketAddress();
                //判断是否为新的地址，不是则添加该地址到集合当中
                if(!clients.contains(newAddress)) {
                    clients.add(newAddress);
                }
                //服务端将接收的信息发送给所有客户端
                sendToAll(packet);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendToAll(DatagramPacket packet) throws Exception {
        for(SocketAddress address:clients) {
            DatagramPacket datagramPacket=new DatagramPacket(packet.getData(),packet.getLength(),address);
            server.send(datagramPacket);
        }


    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        new ServicetaolunUI();
    }
}
