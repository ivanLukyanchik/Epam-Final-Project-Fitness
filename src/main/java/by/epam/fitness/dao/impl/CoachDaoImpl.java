package by.epam.fitness.dao.impl;

import by.epam.fitness.builder.CoachBuilder;
import by.epam.fitness.dao.CoachDao;
import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.Coach;
import by.epam.fitness.pool.ConnectionPool;
import by.epam.fitness.service.ServiceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CoachDaoImpl implements CoachDao {
    private static final String SQL_CHECK_COACH_BY_LOGIN_PASSWORD = "SELECT * FROM coach WHERE login=? AND password=?";

    @Override
    public Optional<Coach> checkCoachByLoginPassword(String login, String password) throws DaoException {
        boolean result = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Coach coach = null;
        try{
            connection = ConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_CHECK_COACH_BY_LOGIN_PASSWORD);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                CoachBuilder builder = new CoachBuilder();
                coach = builder.build(resultSet);
                result = true;
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return result ? Optional.of(coach) : Optional.empty();
    }

    @Override
    public List<Coach> findAll() {
        return null;
    }

    @Override
    public List<Coach> findEntityById(Long id) {
        return null;
    }

    @Override
    public boolean delete(Coach coach) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Coach update(Coach coach) {
        return null;
    }
}
