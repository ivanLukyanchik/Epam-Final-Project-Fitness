package by.epam.fitness.dao.impl;

import by.epam.fitness.builder.AdminBuilder;
import by.epam.fitness.dao.AdminDao;
import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.Admin;
import by.epam.fitness.pool.ConnectionPool;
import by.epam.fitness.service.ServiceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * The type Admin dao.
 */
public class AdminDaoImpl implements AdminDao {
    private static final String SQL_CHECK_ADMIN_BY_LOGIN_PASSWORD = "SELECT * FROM admin WHERE login=? AND password=?";
    private AdminBuilder builder = new AdminBuilder();

    @Override
    public Optional<Admin> checkAdminByLoginPassword(String login, String password) throws DaoException {
        Admin admin = null;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_CHECK_ADMIN_BY_LOGIN_PASSWORD)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                admin = builder.build(resultSet);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(admin);
    }

    @Override
    public Long save(Admin admin) {
        return null;
    }

    @Override
    public Optional<Admin> findById(Long id) {
        return Optional.empty();
    }
}