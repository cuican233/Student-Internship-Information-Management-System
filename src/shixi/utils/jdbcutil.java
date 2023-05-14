package shixi.utils;

import java.sql.*;

/**
 * @author CC
 * @date 2021/12/15
 */
public class jdbcutil {

    public static Connection connection;
    private String dbURL = "jdbc:mysql://localhost:3306/shixi?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";//数据库的地址+数据库名字
    // localhost：当前电脑的ip地址=127.0.0.1
    private String dbUser = "root";
    private String dbPasswd = "cuican233";
    /**
     * 与数据库连接
     */
    public void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver"); //注册MySQL驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//获取数据库连接对象
        try {
            connection = DriverManager.getConnection(dbURL,dbUser,dbPasswd);
            System.out.println("连接成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取执行sql语句的对象后，返回Statement执行后所产生的查询结果
     *
     */
    public ResultSet getResultSet(String sql){
        if (connection == null) return null;
        ResultSet rs = null;
        try {
            Statement statement = null;  //此处用的是statement对象有可能导致SQL注入问题
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);} catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    /**
     * 修改操作封装，增，删，改
     * @param
     * @return
     */
    public int update(String sql){
        int result = 0;
        try {
            Statement statement = connection.createStatement();
            result = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
