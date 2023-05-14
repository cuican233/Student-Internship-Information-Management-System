package shixi.views.RLUI;
import shixi.views.service.ServiceUI;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

/**
 * @author CC
 * @date 2021/12/17
 */
public class LoginThread implements Runnable {
    String s2 =null;
    String s3 =null;

    public LoginThread(String s2, String s3) {
        this.s2 = s2;
        this.s3 = s3;
    }

    @Override
    public void run() {
        if (SQL1(s2, s3) == true) {
            System.out.println("服务端响应：登陆成功！");
            ServiceUI.textArea1.append("收到一条用户登陆消息："+"登陆用户名："+s2+","+"登陆密码："+s3+"\n");
        } else {
            System.out.println("服务端响应：登陆失败！");
        }
    }

    public boolean SQL1(String username,String ps) {//登录
        boolean massage =false;
        try {
            //加载MySQL驱动
            Class.forName("com.mysql.jdbc.Driver");
//			System.out.println("驱动加载成功");
            //连接数据库，获得连接对象
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/shixi?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC","root","cuican233");
//	    	Syste m.out.println("数据库连接成功");
            //创建执行环境,可用该连接发送 SQL 语句
            Statement sta = conn.createStatement();
            //执行SQL语句，得到结果集
            ResultSet result = sta.executeQuery("select * from user");
            //开始对比
            while(result.next()) {
                String a1 = result.getString("username");
                String a2 = result.getString("password");
                if((username.equals(a1))&&(ps.equals(a2))) {
                    massage = true;
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
