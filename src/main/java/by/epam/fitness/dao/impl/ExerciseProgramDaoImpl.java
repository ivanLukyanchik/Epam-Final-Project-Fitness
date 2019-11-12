package by.epam.fitness.dao.impl;

import by.epam.fitness.builder.ExerciseProgramBuilder;
import by.epam.fitness.dao.ExerciseProgramDao;
import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.ExerciseProgram;
import by.epam.fitness.pool.ConnectionPool;
import by.epam.fitness.service.ServiceException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ExerciseProgramDaoImpl implements ExerciseProgramDao {
    private static final String SQL_FIND_BY_ID = "SELECT * FROM exercise_program WHERE program_id=?";
    ExerciseProgramBuilder builder = new ExerciseProgramBuilder();

    @Override
    public List<ExerciseProgram> findExercisesByProgramId(Long programId) throws DaoException {
        List<ExerciseProgram> exercisesList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ExerciseProgram exerciseProgram = null;
        try{
            connection = ConnectionPool.INSTANCE.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_FIND_BY_ID);
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
