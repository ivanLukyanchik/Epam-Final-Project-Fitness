package by.epam.fitness.dao;

import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.Nutrition;

public interface NutritionDao extends BaseDao<Long, Nutrition> {
    Long save(Nutrition nutrition) throws DaoException;
}
