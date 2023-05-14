package shixi.views.enterpriseviews;

import shixi.views.service.ServiceUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * @author CC
 * @date 2021/12/20
 */
public class RizhiThread implements Runnable{

    String s2 =null;
    String s3 =null;
    String s4 =null;
    String s5 =null;

    public RizhiThread(String s2, String s3, String s4, String s5) {
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
        this.s5 = s5;
    }

    public boolean SQL1(String name, String items, String progress){
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
            String sql = "update journal set progress=? where name =? and items=?";
            PreparedStatement ps1 = conn.prepareStatement(sql);
            ps1.setString(1, progress);
            ps1.setString(2, name);
            ps1.setString(3, items);
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


    public boolean SQL2(String employee_name, String items, String progress){
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
            String sql = "update score set progress=? where employee_name =? and items=?";
            PreparedStatement ps1 = conn.prepareStatement(sql);
            ps1.setString(1, progress);
            ps1.setString(2, employee_name);
            ps1.setString(3, items);

            String sql2 = "update teacherscore set progress=? where student_name =? and items=?";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setString(1, progress);
            ps2.setString(2, employee_name);
            ps2.setString(3, items);

            massage = true;
            ps1.executeUpdate();
            ps1.close();
            ps2.executeUpdate();
            ps2.close();
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
        if (SQL1(s3,s4,s5) == true&&SQL2(s3,s4,s5) == true) {
            System.out.println("服务端响应：日志写入成功！");
            ServiceUI.textArea1.append("收到一条学生用户更新日志信息："+"学生姓名："+s3+","+"工作事项："+s4+"更新进度："+s5+"\n");
        } else {
            System.out.println("服务端响应：日志写入失败！");
        }
    }
}
