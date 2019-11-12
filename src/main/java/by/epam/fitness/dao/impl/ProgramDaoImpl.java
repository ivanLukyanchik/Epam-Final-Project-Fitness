package by.epam.fitness.dao.impl;

import by.epam.fitness.builder.ProgramBuilder;
import by.epam.fitness.dao.ProgramDao;
import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.Program;
import by.epam.fitness.pool.ConnectionPool;
import by.epam.fitness.service.ServiceException;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class ProgramDaoImpl implements ProgramDao {
    private static final String SQL_CREATE_TABLE = "INSERT INTO program (nutrition_id, trains_per_week) VALUES (?,?)";
    private static final String SQL_UPDATE_TABLE = "UPDATE program SET nutrition_id=?, trains_per_week=? WHERE id_program=?";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM program WHERE id_program=?";

    @Override
    public Long save(Program program) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Long nutrition_id = program.getNutritionId();
        int trains_per_week = program.getTrainsPerWeek();
        Long generatedId = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            if (program.getId() != null) {
                preparedStatement = connection.prepareStatement(SQL_UPDATE_TABLE, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setLong(3, program.getId());
            } else {
                preparedStatement = connection.prepareStatement(SQL_CREATE_TABLE, Statement.RETURN_GENERATED_KEYS);
            }
            preparedStatement.setLong(1, nutrition_id);
            preparedStatement.setInt(2, trains_per_week);
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
    public Optional<Program> findProgramById(Long programId) throws DaoException {
        boolean result = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Program program = null;
        try{
            connection = ConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setLong(1, programId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ProgramBuilder builder = new ProgramBuilder();
                program = builder.build(resultSet);
                result = true;
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return result ? Optional.of(program) : Optional.empty();
    }

    @Override
    public List<Program> findAll() {
        return null;
    }

    @Override
    public List<Program> findEntityById(Long id) {
        return null;
    }

    @Override
    public boolean delete(Program program) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Program update(Program program) {
        return null;
    }
}
