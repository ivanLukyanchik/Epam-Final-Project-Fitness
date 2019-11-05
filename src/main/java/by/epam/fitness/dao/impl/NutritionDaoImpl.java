package by.epam.fitness.dao.impl;

import by.epam.fitness.dao.NutritionDao;
import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.Nutrition;
import by.epam.fitness.pool.ConnectionPool;

import java.sql.*;
import java.util.List;

public class NutritionDaoImpl implements NutritionDao {
    private static final String SQL_SAVE_TABLE = "INSERT INTO nutrition_table (name, morning_nutrition, dinner_nutrition, lunch_nutrition) VALUES (?,?,?,?)";

    @Override
    public Long save(Nutrition nutrition) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String name = nutrition.getName();
        String morning_nutrition = nutrition.getMorningNutrition();
        String dinner_nutrition = nutrition.getDinnerNutrition();
        String lunch_nutrition = nutrition.getLunchNutrition();
        Long generatedId = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SAVE_TABLE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, morning_nutrition);
            preparedStatement.setString(3, dinner_nutrition);
            preparedStatement.setString(4, lunch_nutrition);
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
    public List<Nutrition> findAll() {
        return null;
    }

    @Override
    public List<Nutrition> findEntityById(Long id) {
        return null;
    }

    @Override
    public boolean delete(Nutrition nutrition) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Nutrition update(Nutrition nutrition) {
        return null;
    }
}
