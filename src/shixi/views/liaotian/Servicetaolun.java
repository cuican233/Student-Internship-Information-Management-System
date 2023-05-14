package shixi.views.liaotian;

import shixi.views.RLUI.services;
import shixi.views.service.ServiceUI;

import javax.swing.*;
import java.awt.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.util.ArrayList;

/**
 * @author CC
 * @date 2021/12/21
 */
public class Servicetaolun extends JFrame implements Runnable {


    private JTextArea textArea = new JTextArea();
    private DatagramSocket server;
    //通过集合保存客户端的地址
    private ArrayList<SocketAddress> clients=new ArrayList<>();

    public Servicetaolun(){
        super("服务器");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300,   380);
        this.setVisible(true);
        add(textArea, BorderLayout.CENTER);
        try {
            //创建服务端
            server = new DatagramSocket(8080);
            //启动服务端线程
            new Thread(this).start();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while(true) {
                //接收客户端发送的信息
                byte[] data=new byte[255];
                DatagramPacket packet=new DatagramPacket(data,data.length);
                server.receive(packet);
                String string=new String(packet.getData(),0,packet.getLength());
                textArea.append(string+"\n");
//                ServiceUI.textArea1.append(string+"\n");
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

    public static void main(String[] args){
        new Servicetaolun();
    }

}
