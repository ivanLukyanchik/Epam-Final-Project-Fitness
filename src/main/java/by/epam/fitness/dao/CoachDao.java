package by.epam.fitness.dao;

import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.Coach;

import java.util.List;
import java.util.Optional;

/**
 * The interface Coach dao.
 */
public interface CoachDao extends BaseDao<Long, Coach> {
    /**
     * Check coach by login password optional.
     *
     * @param login    the login
     * @param password the password
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Coach> checkCoachByLoginPassword(String login, String password) throws DaoException;

    /**
     * Find by client id optional.
     *
     * @param clientId the client id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Coach> findByClientId(long clientId) throws DaoException;

    /**
     * Find all list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Coach> findAll() throws DaoException;
}
