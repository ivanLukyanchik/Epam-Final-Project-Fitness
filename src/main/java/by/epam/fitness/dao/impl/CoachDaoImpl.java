package by.epam.fitness.dao.impl;

import by.epam.fitness.builder.CoachBuilder;
import by.epam.fitness.dao.CoachDao;
import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.Coach;
import by.epam.fitness.pool.ConnectionPool;
import by.epam.fitness.service.ServiceException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CoachDaoImpl implements CoachDao {
    private static final String SQL_CHECK_COACH_BY_LOGIN_PASSWORD = "SELECT * FROM coach WHERE login=? AND password=?";
    private static final String SQL_FIND_BY_CLIENT_ID = "SELECT * FROM coach JOIN client ON coach.id_coach = client.coach_id WHERE id_client=?";
    private static final String SQL_FIND_BY_COACH_ID = "SELECT * FROM coach WHERE id_coach=?";
    private static final String SQL_FIND_ALL = "SELECT * FROM coach";
    private CoachBuilder builder = new CoachBuilder();

    @Override
    public Optional<Coach> checkCoachByLoginPassword(String login, String password) throws DaoException {
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
                coach = builder.build(resultSet);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return Optional.ofNullable(coach);
    }

    @Override
    public Optional<Coach> findByClientId(long clientId) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Coach coach = null;
        try{
            connection = ConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_CLIENT_ID);
            preparedStatement.setLong(1, clientId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                coach = builder.build(resultSet);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return Optional.ofNullable(coach);
    }

    @Override
    public Long save(Coach coach) {
        return null;
    }

    @Override
    public Optional<Coach> findById(Long id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Coach coach = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_COACH_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                coach = builder.build(resultSet);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return Optional.ofNullable(coach);
    }

    @Override
    public List<Coach> findAll() throws DaoException {
        List<Coach> coachesList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        Coach coach = null;
        try{
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL);
            while (resultSet.next()) {
                coach = builder.build(resultSet);
                coachesList.add(coach);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return coachesList;
    }
}
