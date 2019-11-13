package by.epam.fitness.dao.impl;

import by.epam.fitness.builder.ExerciseProgramBuilder;
import by.epam.fitness.dao.ExerciseProgramDao;
import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.ExerciseProgram;
import by.epam.fitness.pool.ConnectionPool;
import by.epam.fitness.service.ServiceException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExerciseProgramDaoImpl implements ExerciseProgramDao {
    private static final String SQL_FIND_BY_PROGRAM_ID = "SELECT * FROM exercise LEFT JOIN exercise_program ON exercise.id_exercise = exercise_program.exercise_id WHERE program_id=?";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM exercise RIGHT JOIN exercise_program ON exercise.id_exercise = exercise_program.exercise_id WHERE exercise_program.id_exercise_program=?";
    private static final String SQL_DELETE = "DELETE FROM exercise_program WHERE id_exercise_program=?";
    private ExerciseProgramBuilder builder = new ExerciseProgramBuilder();

    @Override
    public List<ExerciseProgram> findExercisesByProgramId(Long programId) throws DaoException {
        List<ExerciseProgram> exercisesList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ExerciseProgram exerciseProgram;
        try{
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_FIND_BY_PROGRAM_ID);
            while (resultSet.next()) {
                exerciseProgram = builder.build(resultSet);
                exercisesList.add(exerciseProgram);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return exercisesList;
    }

    @Override
    public Optional<ExerciseProgram> findById(long id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ExerciseProgram exerciseProgram = null;
        try{
            connection = ConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                exerciseProgram = builder.build(resultSet);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return Optional.ofNullable(exerciseProgram);
    }

    @Override
    public int deleteExercise(long exerciseProgramId) throws DaoException {
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = ConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setLong(1, exerciseProgramId);
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
    public List<ExerciseProgram> findAll() throws DaoException {
        return null;
    }

    @Override
    public List<ExerciseProgram> findEntityById(Long id) {
        return null;
    }

    @Override
    public boolean delete(ExerciseProgram exerciseProgram) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public ExerciseProgram update(ExerciseProgram exerciseProgram) {
        return null;
    }

}
