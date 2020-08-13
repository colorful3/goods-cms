package com.colorful.dao;

import com.colorful.model.User;
import com.colorful.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * @author colorful@TaleLin
 */
public class UserDAO {

    public User selectByUserNameAndPassword(String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = new User();
        try {
            // 获得链接
            connection = JDBCUtil.getConnection();
            // 编写 SQL 语句
            String sql = "SELECT * FROM `imooc_user` where `username` = ? AND `password` = ? AND `delete_time` is null ";
            // 预编译 SQL
            preparedStatement = connection.prepareStatement(sql);
            // 设置参数
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                String nickname = resultSet.getString("nickname");
                if (nickname.equals("")) {
                    nickname = "匿名";
                }
                user.setNickName(nickname);
                user.setUserName(resultSet.getString("username"));
            } else {
                user = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            JDBCUtil.release(resultSet, preparedStatement, connection);
        }
        return user;
    }

}
