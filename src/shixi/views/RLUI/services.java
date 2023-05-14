package shixi.views.RLUI;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

/**
 * @author CC
 * @date 2021/12/15
 */
public class services implements Runnable {

    public void run() {
        // TODO Auto-generated method stub
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream inputStream = null;
        ByteArrayOutputStream baos = null;
        OutputStream op = null;
        try {//创建服务器
            serverSocket = new ServerSocket(8080);
            socket = serverSocket.accept();//获取客户端的socket

            inputStream = socket.getInputStream();
            baos = new ByteArrayOutputStream();
            byte [] buffer = new byte[20];
            int len;
            //接受数据
            while((len = inputStream.read(buffer))!=-1) {
                baos.write(buffer,0,len);
            }
            String s = baos.toString();
            //提取数据中的关键点
            String [] s1 = s.split(",");
            String s2 = s1[1];
            String s3 = s1[2];
            System.out.println("服务器："+s);
            for(int i=0;i<3;i++) {
                System.out.println(s1[i]);
            }
            //转化为int
            int a;
            a = Integer.parseInt(s1[0]);
            //反馈给客户端
            System.out.println("服务端：读取数据成功");
            if(a==1) {//注册
                //连接数据库操作，保存数据，新增
                if(SQL1(s2, s3)==1) {
                    //保存成功后返回成功。
                    System.out.println("服务端：注册成功");
                    op = socket.getOutputStream();
                    op.write("yes".getBytes());
                }else {
                    System.out.println("服务端：注册失败");
                    op = socket.getOutputStream();
                    op.write("no1".getBytes());
                }
            }else if (a==0) {//登录
                //连接数据库，读取数据库进行比较
                if(SQL2(s2, s3)==1) {
                    //若比较一致，则返回登录成功
                    System.out.println("服务端：登录成功");
                    op = socket.getOutputStream();
                    op.write("yes".getBytes());
                }else {
                    System.out.println("服务端：登录失败");
                    op = socket.getOutputStream();
                    op.write("no1".getBytes());
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            if(baos !=null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(inputStream !=null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(socket !=null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(serverSocket !=null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(op!=null) {
                try {
                    op.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public int SQL1(String id,String ps) {//注册
        int massage =1;
        try {
            //加载MySQL驱动
            Class.forName("com.mysql.jdbc.Driver");
//			System.out.println("驱动加载成功");
            //连接数据库，获得连接对象
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/shixi?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC","root","cuican233");
            //创建执行环境,可用该连接发送 SQL 语句
            //执行SQL语句，得到结果集
            //开始添加
            String sql = "insert into user (username, password) values (?,?)";
            PreparedStatement ps1 = conn.prepareStatement(sql);
            ps1.setString(1, id);
            ps1.setString(2, ps);
            ps1.executeUpdate();
            ps1.close();
            conn.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            return massage;
        }
    }

    public int SQL2(String id,String ps) {//登录
        int massage =0;
        try {
            //加载MySQL驱动
            Class.forName("com.mysql.jdbc.Driver");
//			System.out.println("驱动加载成功");
            //连接数据库，获得连接对象
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/shixi?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC","root","cuican233");
//	    	System.out.println("数据库连接成功");
            //创建执行环境,可用该连接发送 SQL 语句
            Statement sta = conn.createStatement();
            //执行SQL语句，得到结果集
            ResultSet result = sta.executeQuery("select * from user");
            //开始对比
            while(result.next()) {
                String a1 = result.getString("id");
                String a2 = result.getString("password");
                if((id.equals(a1))&&(ps.equals(a2))) {
                    massage = 1;
                    break;
                }
            }
            result.close();
            sta.close();
            conn.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            return massage;
        }
    }

}
