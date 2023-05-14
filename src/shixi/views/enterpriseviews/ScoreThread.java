package shixi.views.enterpriseviews;

import shixi.views.service.ServiceUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * @author CC
 * @date 2021/12/20
 */
public class ScoreThread implements Runnable {

    String s2 =null;
    String s3 =null;
    String s4 =null;
    String s5 =null;

    public ScoreThread(String s2, String s3, String s4, String s5) {
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
        this.s5 = s5;
    }

    @Override
    public void run() {
        if (SQL1(s2,s3,s4,s5) == true) {
            System.out.println("服务端响应：打分成功！");
            ServiceUI.textArea1.append("收到一条企业用户打分信息："+"员工姓名："+s2+","+"工作表现："+s3+"工作成果："+s4+"得分："+s5+"\n");
        } else {
            System.out.println("服务端响应：打分失败！");
        }
    }



    public boolean SQL1(String employee_name, String performance, String results, String score){
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
            String sql = "update score set performance=?,results=?,score=? where employee_name =?";
            PreparedStatement ps1 = conn.prepareStatement(sql);
            ps1.setString(1, performance);
            ps1.setString(2, results);
            ps1.setString(3, score);
            ps1.setString(4, employee_name);

            String sql2 = "insert into finalscore (name,qiye_score) values (?,?)";
            PreparedStatement ps12 = conn.prepareStatement(sql2);
            ps12.setString(1, employee_name);
            ps12.setString(2, score);

            massage = true;
            ps1.executeUpdate();
            ps1.close();
            ps12.executeUpdate();
            ps12.close();
            conn.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            return massage;
        }
    }

}
