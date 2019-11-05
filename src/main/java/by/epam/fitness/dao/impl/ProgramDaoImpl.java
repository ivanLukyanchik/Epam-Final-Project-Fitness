package by.epam.fitness.dao.impl;

import by.epam.fitness.dao.ProgramDao;
import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.Program;
import by.epam.fitness.pool.ConnectionPool;

import java.sql.*;
import java.util.List;

public class ProgramDaoImpl implements ProgramDao {
    private static final String SQL_SAVE_TABLE = "INSERT INTO program_table (nutrition_id, trains_per_week) VALUES (?,?)";

    @Override
    public Long save(Program program) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Long nutrition_id = program.getNutritionId();
        int trains_per_week = program.getTrainsPerWeek();
        Long generatedId = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SAVE_TABLE, Statement.RETURN_GENERATED_KEYS);
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
