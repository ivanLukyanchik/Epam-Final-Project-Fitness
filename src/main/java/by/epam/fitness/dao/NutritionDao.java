package by.epam.fitness.dao;

import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.Nutrition;

import java.util.Optional;

/**
 * The interface Nutrition dao.
 */
public interface NutritionDao extends BaseDao<Long, Nutrition> {
    /**
     * Find by client id optional.
     *
     * @param clientId the client id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Nutrition> findByClientId(long clientId) throws DaoException;
}
