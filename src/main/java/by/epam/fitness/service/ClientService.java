package by.epam.fitness.service;

import by.epam.fitness.entity.Client;

import java.util.List;
import java.util.Optional;

/**
 * The interface Client service.
 */
public interface ClientService {
    /**
     * Check user by login password optional.
     *
     * @param login    the login
     * @param password the password
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Client> checkUserByLoginPassword(String login, String password) throws ServiceException;

    /**
     * Is login unique boolean.
     *
     * @param patternLogin the pattern login
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean isLoginUnique(String patternLogin) throws ServiceException;

    /**
     * Register user boolean.
     *
     * @param client the client
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean registerUser(Client client) throws ServiceException;

    /**
     * Restore user boolean.
     *
     * @param login     the login
     * @param userEmail the user email
     * @param userHash  the user hash
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean restoreUser(String login, String userEmail, String userHash) throws ServiceException;

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Client> findById(Long id) throws ServiceException;

    /**
     * Find by login hash optional.
     *
     * @param login the login
     * @param email the email
     * @param hash  the hash
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Client> findByLoginHash(String login, String email, String hash) throws ServiceException;

    /**
     * Save long.
     *
     * @param client the client
     * @return the long
     * @throws ServiceException the service exception
     */
    Long save(Client client) throws ServiceException;

    /**
     * Find by coach id list.
     *
     * @param coachId the coach id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Client> findByCoachId(long coachId) throws ServiceException;

    /**
     * Gets user by cookie data.
     *
     * @param login the login
     * @param hash  the hash
     * @return the user by cookie data
     * @throws ServiceException the service exception
     */
    Optional<Client> getUserByCookieData(String login, String hash) throws ServiceException;

    /**
     * Find all list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Client> findAll() throws ServiceException;

    /**
     * Find active by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Client> findActiveById(long id) throws  ServiceException;

    /**
     * Find by filter list.
     *
     * @param clientForData the client for data
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Client> findByFilter(Client clientForData) throws ServiceException;
}
