package by.epam.fitness.service;

import by.epam.fitness.entity.Coach;

import java.util.List;
import java.util.Optional;

/**
 * The interface Coach service.
 */
public interface CoachService {
    /**
     * Check coach by login password optional.
     *
     * @param login    the login
     * @param password the password
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Coach> checkCoachByLoginPassword(String login, String password) throws ServiceException;

    /**
     * Find by client id optional.
     *
     * @param clientId the client id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Coach> findByClientId(long clientId) throws ServiceException;

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Coach> findById(long id) throws ServiceException;

    /**
     * Find all list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Coach> findAll() throws ServiceException;

    /**
     * Save long.
     *
     * @param coach the coach
     * @return the long
     * @throws ServiceException the service exception
     */
    Long save(Coach coach) throws ServiceException;
}
