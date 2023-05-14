package shixi.views.teacher;

import shixi.views.service.ServiceUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * @author CC
 * @date 2021/12/21
 */
public class TassignmentfenpeiThread implements Runnable{


    String s2 =null;
    String s3 =null;
    String s4 =null;

    public TassignmentfenpeiThread(String s2, String s3, String s4) {
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
    }

    @Override
    public void run() {
        if (SQL1(s2,s3,s4)==true&&SQL2(s2,s3)==true&&SQL3(s2,s3,s4)==true) {
            ServiceUI.textArea1.append("收到一条老师用户分配任务的信息："+"员工名："+s2+","+"工作事项："+s3+"\n");
            System.out.println("服务端响应：分配任务成功！");
        } else {
            System.out.println("服务端响应：分配任务失败！");
        }
    }

    public boolean SQL1(String student_name,String items,String teacher_name) {//修改状态
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
            String sql = "update teacherassignment set items=?,teacher_name=? where student_name=?";
            PreparedStatement ps1 = conn.prepareStatement(sql);
            ps1.setString(1,items);
            ps1.setString(2,teacher_name);
            ps1.setString(3,student_name);
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

    public boolean SQL2(String student_name,String items){//插入信息
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
            String sql = "insert into journal (name,items) value(?,?)";
            PreparedStatement ps1 = conn.prepareStatement(sql);
            ps1.setString(1, student_name);
            ps1.setString(2, items);
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
    public boolean SQL3(String student_name,String items,String teacher_name){//插入信息
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
            String sql = "insert into teacherscore(teacher_name,student_name,items) values (?,?,?)";
            PreparedStatement ps1 = conn.prepareStatement(sql);
            ps1.setString(1, teacher_name);
            ps1.setString(2, student_name);
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

}
