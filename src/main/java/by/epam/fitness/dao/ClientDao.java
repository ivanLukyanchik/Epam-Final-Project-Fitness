package by.epam.fitness.dao;

import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.Client;

import java.util.List;
import java.util.Optional;

/**
 * The interface Client dao.
 */
public interface ClientDao extends BaseDao<Long, Client> {
    /**
     * Check user by login password optional.
     *
     * @param login    the login
     * @param password the password
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Client> checkUserByLoginPassword(String login, String password) throws DaoException;

    /**
     * Is login unique boolean.
     *
     * @param patternLogin the pattern login
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean isLoginUnique(String patternLogin) throws DaoException;

    /**
     * Register user boolean.
     *
     * @param client the client
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean registerUser(Client client) throws DaoException;

    /**
     * Restore user boolean.
     *
     * @param login     the login
     * @param userEmail the user email
     * @param userHash  the user hash
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean restoreUser(String login, String userEmail, String userHash) throws DaoException;

    /**
     * Find by login hash optional.
     *
     * @param login the login
     * @param email the email
     * @param hash  the hash
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Client> findByLoginHash(String login, String email, String hash) throws DaoException;

    /**
     * Find by coach id list.
     *
     * @param coachId the coach id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Client> findByCoachId(long coachId) throws DaoException;

    /**
     * Gets user by cookie data.
     *
     * @param login the login
     * @param hash  the hash
     * @return the user by cookie data
     * @throws DaoException the dao exception
     */
    Optional<Client> getUserByCookieData(String login, String hash) throws DaoException;

    /**
     * Find all list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Client> findAll() throws DaoException;

    /**
     * Find active by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Client> findActiveById(long id) throws DaoException;

    /**
     * Find by filter list.
     *
     * @param clientForData the client for data
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Client> findByFilter(Client clientForData) throws DaoException;
}
