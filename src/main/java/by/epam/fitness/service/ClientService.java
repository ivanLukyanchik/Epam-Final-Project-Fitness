package by.epam.fitness.service;

import by.epam.fitness.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Optional<Client> checkUserByLoginPassword(String login, String password) throws ServiceException;
    boolean isLoginUnique(String patternLogin) throws ServiceException;
    boolean registerUser(Client client) throws ServiceException;
    boolean restoreUser(String login, String userEmail, String userHash) throws ServiceException;
    Optional<Client> findById(Long id) throws ServiceException;
    Optional<Client> findByLoginHash(String login, String email, String hash) throws ServiceException;
    Long save(Client client) throws ServiceException;
    List<Client> findByCoachId(long coachId) throws ServiceException;
    Optional<Client> getUserByCookieData(String login, String hash) throws ServiceException;
    List<Client> findAll() throws ServiceException;
    Optional<Client> findActiveById(long id) throws  ServiceException;
    List<Client> findByFilter(Client clientForData) throws ServiceException;
}
