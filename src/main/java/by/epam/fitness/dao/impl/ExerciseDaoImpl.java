package by.epam.fitness.dao.impl;

import by.epam.fitness.builder.ExerciseBuilder;
import by.epam.fitness.dao.ExerciseDao;
import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.Exercise;
import by.epam.fitness.pool.ConnectionPool;
import by.epam.fitness.service.ServiceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExerciseDaoImpl implements ExerciseDao {
    private static final String SQL_FIND_BY_ID = "SELECT * FROM exercise WHERE id_exercise=?";
    private static final String SQL_FIND_ALL = "SELECT * FROM exercise";
    private ExerciseBuilder builder = new ExerciseBuilder();

    @Override
    public List<Exercise> findAll() throws DaoException {
        List<Exercise> exercises = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Exercise exercise = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                exercise = builder.build(resultSet);
                exercises.add(exercise);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return exercises;
    }

    @Override
    public Long save(Exercise exercise) throws DaoException {
        return null;
    }

    @Override
    public Optional<Exercise> findById(Long id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Exercise exercise = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                exercise = builder.build(resultSet);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return Optional.ofNullable(exercise);
    }
}
