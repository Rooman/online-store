package com.anabol.onlineshop.dao.jdbc;

import com.anabol.onlineshop.dao.UserDao;
import com.anabol.onlineshop.dao.jdbc.connection.DBConnectionFactory;
import com.anabol.onlineshop.entity.User;
import com.anabol.onlineshop.dao.jdbc.mapper.UserMapper;

import java.sql.*;

public class JdbcUserDao implements UserDao {
    private DBConnectionFactory dbConnectionFactory;

    private static final String FIND_BY_NAME_QUERY = "SELECT name, password, role FROM user WHERE name = ?";

    @Override
    public User getByName(String name) {
        try (Connection connection = dbConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME_QUERY)) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                User user = new User();
                if (resultSet.next()) {
                    user = UserMapper.mapRow(resultSet);
                }
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("We got SQLException", e);
        }
    }

    public JdbcUserDao(DBConnectionFactory dbConnectionFactory) {
        this.dbConnectionFactory = dbConnectionFactory;
    }
}
