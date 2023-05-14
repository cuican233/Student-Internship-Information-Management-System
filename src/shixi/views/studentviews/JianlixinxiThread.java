package shixi.views.studentviews;

import shixi.views.service.ServiceUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * @author CC
 * @date 2021/12/20
 */
public class JianlixinxiThread implements Runnable {


    String s2 =null;
    String s3 =null;
    String s4 =null;
    String s5 =null;
    String s6 =null;

    public JianlixinxiThread(String s2, String s3, String s4, String s5, String s6) {
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
        this.s5 = s5;
        this.s6 = s6;
    }

    @Override
    public void run() {
        if (SQL1(s2, s3,s4,s5,s6) == true) {
            System.out.println("服务端响应：简历信息添加成功！");
            ServiceUI.textArea1.append("收到一条用户填写的简历信息消息："+"姓名："+s2+","+"性别："+s3+","+"毕业院校："+s4+","+"自我评价年及所获荣誉："+s5+"\n");
        } else {
            System.out.println("服务端响应：简历信息填写失败！");
        }
    }
    public boolean SQL1(String name,String gender,String school,String text,String photo){
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
            String sql = "insert into resume (name, gender,school,text,photo) values (?,?,?,?,?)";
            PreparedStatement ps1 = conn.prepareStatement(sql);
            ps1.setString(1, name);
            ps1.setString(2, gender);
            ps1.setString(3, school);
            ps1.setString(4, text);
            ps1.setString(5, photo);
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
