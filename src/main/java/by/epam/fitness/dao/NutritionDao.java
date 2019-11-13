package by.epam.fitness.dao;

import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.Nutrition;

import java.util.Optional;

public interface NutritionDao extends BaseDao<Long, Nutrition> {
    Long save(Nutrition nutrition) throws DaoException;
    Optional<Nutrition> findByClientId(long clientId) throws DaoException;
    Optional<Nutrition> findById(long id) throws DaoException;
}
