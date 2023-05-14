package shixi.views.enterpriseviews;

import shixi.views.service.ServiceUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * @author CC
 * @date 2021/12/20
 */
public class PinyongThread implements Runnable {

    String s2 =null;
    String s3 =null;

    public PinyongThread(String s2, String s3) {
        this.s2 = s2;
        this.s3 = s3;
    }
    @Override
    public void run() {
        if (SQL1(s2,s3)==true&&SQL2(s2,s3)==true&&SQL3(s3)==true) {
            ServiceUI.textArea1.append("收到一条企业用户聘用员工的信息："+"公司单位名："+s2+","+"学生姓名："+s3+"\n");
            System.out.println("服务端响应：聘用成功！");
        } else {
            System.out.println("服务端响应：聘用失败！");
        }
    }

    public boolean SQL1(String company,String employee) {//修改状态
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
            String sql = "update recruitment set state=? where company =? and employee=?";
            PreparedStatement ps1 = conn.prepareStatement(sql);
            ps1.setString(1,"已聘满");
            ps1.setString(2,company);
            ps1.setString(3,employee);
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

    public boolean SQL2(String company,String employee){//插入信息
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
            String sql = "insert into assignment (company,employee_name) value(?,?)";
            PreparedStatement ps1 = conn.prepareStatement(sql);
            ps1.setString(1, company);
            ps1.setString(2, employee);
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
    public boolean SQL3(String employee){//插入信息
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
            String sql = "INSERT INTO teacherassignment(student_name) VALUES(?)";
            PreparedStatement ps1 = conn.prepareStatement(sql);
            ps1.setString(1, employee);
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
