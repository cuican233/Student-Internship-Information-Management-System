package shixi.views.RLUI;

import shixi.views.service.ServiceUI;

import java.sql.*;

/**
 * @author CC
 * @date 2021/12/19
 */
public class RegisterThread implements Runnable {

    String s2 =null;
    String s3 =null;

    public RegisterThread(String s2, String s3) {
        this.s2 = s2;
        this.s3 = s3;
    }



    public boolean SQL1(String username,String psw){
        boolean massage =false;
        try {
            //加载MySQL驱动
            Class.forName("com.mysql.jdbc.Driver");
//			System.out.println("驱动加载成功");
            //连接数据库，获得连接对象
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/shixi?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC","root","cuican233");
            //创建执行环境,可用该连接发送 SQL 语句
            //执行SQL语句，得到结果集
            //开始添加
            String sql = "insert into user (username, password) values (?,?)";
            PreparedStatement ps1 = conn.prepareStatement(sql);
            ps1.setString(1, username);
            ps1.setString(2, psw);
            massage = true;
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

    @Override
    public void run() {
        if (SQL1(s2, s3) == true) {
            System.out.println("服务端响应：注册成功！");
            ServiceUI.textArea1.append("收到一条用户注册消息："+"注册用户名："+s2+","+"注册密码："+s3+"\n");
        } else {
            System.out.println("服务端响应：注册失败！");
        }
    }
}
