package by.epam.fitness.dao;

import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientDao extends BaseDao<Long, Client> {
    Optional<Client> checkUserByLoginPassword(String login, String password) throws DaoException;
    boolean isLoginUnique(String patternLogin) throws DaoException;
    boolean registerUser(Client client) throws DaoException;
    boolean restoreUser(String login, String userEmail, String userHash) throws DaoException;
    Optional<Client> findByLoginHash(String login, String email, String hash) throws DaoException;
    List<Client> findByCoachId(long coachId) throws DaoException;
    Optional<Client> getUserByCookieData(String login, String hash) throws DaoException;
    List<Client> findAll() throws DaoException;
    Optional<Client> findActiveById(long id) throws DaoException;
}
