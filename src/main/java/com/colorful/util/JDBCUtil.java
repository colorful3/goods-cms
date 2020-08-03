package com.colorful.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author colorful@TaleLin
 */
public class JDBCUtil {

    private static final String driverClass;
    private static final String url;
    private static final String username;
    private static final String password;

    static {
        // 加载属性文件并解析
        Properties props = new Properties();
        // 使用类的加载器的方式进行获取配置
        InputStream inputStream = JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            assert inputStream != null;
            props.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        driverClass = props.getProperty("driverClass");
        url = props.getProperty("url");
        username = props.getProperty("username");
        password = props.getProperty("password");
    }

    /**
     * 注册驱动
     */
    public static void loadDriver() throws ClassNotFoundException{
        Class.forName(driverClass);
    }

    /**
     * 获得连接
     */
    public static Connection getConnection() throws Exception{
        loadDriver();
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * 资源释放
     */
    public static void release(PreparedStatement statement, Connection connection){
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            statement = null;
        }
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connection = null;
        }
    }

    /**
     * 释放资源 重载方法
     */
    public static void release(ResultSet rs, PreparedStatement stmt, Connection conn){
        if(rs!= null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            stmt = null;
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }

}
