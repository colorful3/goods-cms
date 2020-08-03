package com.colorful.dao;

import com.colorful.model.Goods;
import com.colorful.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GoodsDAO {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    boolean executeResult;

    public boolean insertGoods(Goods goods) {
        try {
            // 获得链接
            connection = JDBCUtil.getConnection();
            // 编写 SQL 语句
            String sql = "INSERT INTO `imooc_goods`" +
                    " (`name`, `description`, `category_id`, `price`, `stock`)" +
                    " VALUES(?, ?, ?, ?, ?)";
            // 预编译 SQL
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, goods.getName());
            preparedStatement.setString(2, goods.getDescription());
            preparedStatement.setInt(3, goods.getCategoryId());
            preparedStatement.setDouble(4, goods.getPrice());
            preparedStatement.setInt(5, goods.getStock());
            executeResult = preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            JDBCUtil.release(preparedStatement, connection);
        }
        return executeResult;
    }

    public boolean deleteGoodsById(Integer id) {
        try {
            // 获得链接
            connection = JDBCUtil.getConnection();
            // 编写 SQL 语句
            String sql = "UPDATE `imooc_goods` set `delete_time` = ? WHERE id = ?";
            // 预编译 SQL
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setInt(2, id);
            executeResult = preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            JDBCUtil.release(preparedStatement, connection);
        }
        return executeResult;
    }

    public boolean updateGoodsById(Goods goods) {
        try {
            // 获得链接
            connection = JDBCUtil.getConnection();
            // 编写 SQL 语句
            String sql = "UPDATE `imooc_goods` SET " +
                    "`name` = ?, `description` = ?, `category_id` = ?," +
                    " `price` = ?, `stock` = ?, `update_time` = ? WHERE id = ?";
            // 预编译 SQL
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, goods.getName());
            preparedStatement.setString(2, goods.getDescription());
            preparedStatement.setInt(3, goods.getCategoryId());
            preparedStatement.setDouble(4, goods.getPrice());
            preparedStatement.setInt(5, goods.getStock());
            preparedStatement.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setInt(7, goods.getId());
            executeResult = preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            JDBCUtil.release(preparedStatement, connection);
        }
        return executeResult;
    }

    public List<Goods> selectGoodsList() {
        List<Goods> goodsList = new ArrayList<>();
        try {
            // 获得链接
            connection = JDBCUtil.getConnection();
            // 编写 SQL 语句
            String sql = "SELECT `id`, `name`, `price` FROM `imooc_goods` where `delete_time` is null";
            // 预编译 SQL
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Goods goods = new Goods();
                goods.setId(resultSet.getInt("id"));
                goods.setName(resultSet.getString("name"));
                goods.setPrice(resultSet.getDouble("price"));
                goodsList.add(goods);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            JDBCUtil.release(resultSet, preparedStatement, connection);
        }
        return goodsList;
    }

    public List<Goods> selectGoodsListByCategoryId(Integer categoryId) {
        List<Goods> goodsList = new ArrayList<>();
        try {
            // 获得链接
            connection = JDBCUtil.getConnection();
            // 编写 SQL 语句
            String sql = "SELECT `id`, `name`, `price` FROM " +
                    "`imooc_goods` where `delete_time` is null AND `category_id` = ?";
            // 预编译 SQL
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, categoryId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Goods goods = new Goods();
                goods.setId(resultSet.getInt("id"));
                goods.setName(resultSet.getString("name"));
                goods.setPrice(resultSet.getDouble("price"));
                goodsList.add(goods);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            JDBCUtil.release(resultSet, preparedStatement, connection);
        }
        return goodsList;
    }

    public Goods selectGoodsById(Integer id) {
        Goods goods = new Goods();
        try {
            // 获得链接
            connection = JDBCUtil.getConnection();
            // 编写 SQL 语句
            String sql = "SELECT * FROM `imooc_goods` where `delete_time` is null AND `id` = ?";
            // 预编译 SQL
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                goods.setId(resultSet.getInt("id"));
                goods.setName(resultSet.getString("name"));
                goods.setDescription(resultSet.getString("description"));
                goods.setCategoryId(resultSet.getInt("category_id"));
                goods.setPrice(resultSet.getDouble("price"));
                goods.setStock(resultSet.getInt("stock"));
                goods.setCreateTime(resultSet.getTimestamp("create_time"));
                goods.setUpdateTime(resultSet.getTimestamp("update_time"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            JDBCUtil.release(resultSet, preparedStatement, connection);
        }
        return goods;
    }


}
