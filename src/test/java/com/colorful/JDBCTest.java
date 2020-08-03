package com.colorful;

import com.colorful.util.JDBCUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class JDBCTest {

    @Test
    public void testJDBC() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            // 获得链接
            connection = JDBCUtil.getConnection();
            // 编写 SQL 语句
            String sql = "SELECT * FROM `imooc_user` where `id` = ?";
            // 预编译 SQL
            preparedStatement = connection.prepareStatement(sql);
            // 设置参数
            preparedStatement.setInt(1, 1);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nickname = resultSet.getString("nickname");
                Timestamp createTime = resultSet.getTimestamp("create_time");
                System.out.println("id=" + id);
                System.out.println("nickname=" + nickname);
                System.out.println("createTime=" + createTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            JDBCUtil.release(resultSet, preparedStatement, connection);
        }
    }

}
