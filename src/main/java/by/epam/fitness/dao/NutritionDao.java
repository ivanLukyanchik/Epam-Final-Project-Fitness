package by.epam.fitness.dao;

import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.Nutrition;

import java.util.Optional;

public interface NutritionDao extends BaseDao<Long, Nutrition> {
    Optional<Nutrition> findByClientId(long clientId) throws DaoException;
}
