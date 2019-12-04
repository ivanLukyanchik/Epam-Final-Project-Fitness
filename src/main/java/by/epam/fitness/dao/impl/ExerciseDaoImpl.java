package by.epam.fitness.dao.impl;

import by.epam.fitness.builder.ExerciseBuilder;
import by.epam.fitness.dao.ExerciseDao;
import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.Exercise;
import by.epam.fitness.pool.ConnectionPool;
import by.epam.fitness.service.ServiceException;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExerciseDaoImpl implements ExerciseDao {
    private static final String SQL_FIND_BY_ID = "SELECT * FROM exercise WHERE id_exercise=?";
    private static final String SQL_FIND_ALL_LIMITED = "SELECT * FROM exercise LIMIT ?, ?";
    private static final String SQL_CREATE_EXERCISE = "INSERT INTO exercise (name, description, image) VALUES (?,?,?)";
    private static final String SQL_DELETE = "DELETE FROM exercise WHERE id_exercise=?";
    private static final String SQL_NUMBER_OF_ROWS = "SELECT COUNT(id_exercise) FROM exercise";
    private ExerciseBuilder builder = new ExerciseBuilder();

    @Override
    public List<Exercise> findAllLimited(int start, int total) throws DaoException {
        List<Exercise> exercises = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Exercise exercise = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_LIMITED);
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, total);
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
    public int deleteExercise(long exerciseId) throws DaoException {
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = ConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setLong(1, exerciseId);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return result;
    }

    @Override
    public int getNumberOfRows() throws DaoException {
        int result = 1;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = ConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_NUMBER_OF_ROWS);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return result;
    }

    @Override
    public Long save(Exercise exercise) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String name = exercise.getName();
        String description = exercise.getDescription();
        InputStream is = exercise.getIs();
        Long generatedId = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_CREATE_EXERCISE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setBlob(3, is);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                generatedId = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return generatedId;

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