package shixi.views.enterpriseviews;
import shixi.views.service.ServiceUI;
import java.sql.*;

/**
 * @author CC
 * @date 2021/12/19
 */
public class ZhaopinfabuThread implements Runnable {

    String s2 =null;
    String s3 =null;
    String s4 =null;
    String s5 =null;
    String s6 =null;

    public ZhaopinfabuThread(String s2, String s3, String s4, String s5, String s6) {
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
        this.s5 = s5;
        this.s6 = s6;
    }


    @Override
    public void run() {
        if (SQL1(s2, s3,s4,s5,s6) == true) {
            System.out.println("服务端响应：招聘信息发布成功！");
            ServiceUI.textArea1.append("收到一条企业用户发布的招聘信息："+"公司单位名："+s2+","+"职位："+s3+","+"要求："+s4+","+"薪资待遇："+s5+","+"目前状态："+s6+"\n");
        } else {
            System.out.println("服务端响应：招聘信息发布失败！");
        }
    }

    public boolean SQL1(String company,String position,String requirement,String salary,String state) {//插入
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
            String sql = "insert into recruitment (company, position,requirement,salary,state) values (?,?,?,?,?)";
            PreparedStatement ps1 = conn.prepareStatement(sql);
            ps1.setString(1, company);
            ps1.setString(2, position);
            ps1.setString(3, requirement);
            ps1.setString(4, salary);
            ps1.setString(5, state);
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
