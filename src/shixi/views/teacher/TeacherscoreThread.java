package shixi.views.teacher;

import shixi.views.service.ServiceUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * @author CC
 * @date 2021/12/21
 */
public class TeacherscoreThread implements Runnable{

    String s2 =null;
    String s3 =null;
    String s4 =null;
    String s5 =null;

    public TeacherscoreThread(String s2, String s3, String s4, String s5) {
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
        this.s5 = s5;
    }

    @Override
    public void run() {
        if (SQL1(s2,s3,s4,s5) == true) {
            System.out.println("服务端响应：打分成功！");
            ServiceUI.textArea1.append("收到一条老师用户打分信息："+"学生姓名："+s2+","+"任务表现："+s3+"成果："+s4+"得分："+s5+"\n");
        } else {
            System.out.println("服务端响应：打分失败！");
        }
    }

    public boolean SQL1(String student_name, String performance, String results, String score){
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
            String sql = "update teacherscore set performance=?,results=?,score=? where student_name =?";
            PreparedStatement ps1 = conn.prepareStatement(sql);
            ps1.setString(1, performance);
            ps1.setString(2, results);
            ps1.setString(3, score);
            ps1.setString(4, student_name);
            String sql2 = "update finalscore set teacher_score=? where name =?";
            PreparedStatement ps12 = conn.prepareStatement(sql2);
            ps12.setString(1, score);
            ps12.setString(2, student_name);

            String sql3 = "UPDATE finalscore SET finalscore = (0.7*qiye_score+0.3*teacher_score) WHERE NAME=?";
            PreparedStatement ps13 = conn.prepareStatement(sql3);
            ps13.setString(1,student_name);

            massage = true;
            ps1.executeUpdate();
            ps1.close();
            ps12.executeUpdate();
            ps12.close();
            ps13.executeUpdate();
            ps13.close();
            conn.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            return massage;
        }
    }

}
