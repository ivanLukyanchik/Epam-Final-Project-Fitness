package by.epam.fitness.dao.impl;

import by.epam.fitness.builder.ProgramBuilder;
import by.epam.fitness.dao.ProgramDao;
import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.Program;
import by.epam.fitness.pool.ConnectionPool;
import by.epam.fitness.service.ServiceException;

import java.sql.*;
import java.util.Optional;

public class ProgramDaoImpl implements ProgramDao {
    private static final String SQL_CREATE_TABLE = "INSERT INTO program (nutrition_id, trains_per_week) VALUES (?,?)";
    private static final String SQL_UPDATE_TABLE = "UPDATE program SET nutrition_id=?, trains_per_week=? WHERE id_program=?";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM program WHERE id_program=?";
    private ProgramBuilder builder = new ProgramBuilder();

    @Override
    public Long save(Program program) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Long nutrition_id = program.getNutritionId();
        int trains_per_week = program.getTrainsPerWeek();
        Long generatedId = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            try {
                connection.setAutoCommit(false);
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
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new SQLException(e);
            } finally {
                close(preparedStatement);
                close(connection);
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return generatedId;
    }

    @Override
    public Optional<Program> findById(Long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public Optional<Program> findProgramById(long programId) throws DaoException {
        Program program = null;
        try (
                Connection connection = ConnectionPool.getInstance().takeConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
        ) {
            preparedStatement.setLong(1, programId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                program = builder.build(resultSet);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(program);
    }
}