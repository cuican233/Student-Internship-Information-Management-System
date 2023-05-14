package shixi.views.studentviews;

import shixi.views.service.ServiceUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * @author CC
 * @date 2021/12/20
 */
public class ApplicationThread implements Runnable {

    String s2 =null;
    String s3 =null;
    String s4 =null;
    String s5 =null;

    public ApplicationThread(String s2, String s3, String s4, String s5) {
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
        this.s5 = s5;
    }

    @Override
    public void run() {
        if (SQL1(s2, s5) == true&&SQL2(s2,s4)==true) {
            ServiceUI.textArea1.append("收到一条学生用户申请招聘的信息："+"公司编号："+s2+","+"公司单位名："+s3+","+"学生姓名："+s4+"\n");
            System.out.println("服务端响应：招聘信息申请成功！");
        } else {
            System.out.println("服务端响应：招聘申请失败！");
        }
    }

    public boolean SQL1(String id,String state) {//修改状态
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
            String sql = "update recruitment set state=? where id=?";
            PreparedStatement ps1 = conn.prepareStatement(sql);
            ps1.setString(1, state);
            ps1.setString(2, id);
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

    public boolean SQL2(String id,String name){//插入信息
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
            String sql = "update recruitment set employee=? where id=?";
            PreparedStatement ps1 = conn.prepareStatement(sql);
            ps1.setString(1, name);
            ps1.setString(2, id);
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
