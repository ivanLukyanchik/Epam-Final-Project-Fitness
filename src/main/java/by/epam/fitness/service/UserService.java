package by.epam.fitness.service;

import by.epam.fitness.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> checkUserByLoginPassword(String login, String password) throws ServiceException;
    boolean isLoginUnique(String patternLogin) throws ServiceException;
    boolean registerUser(User user) throws ServiceException;
    boolean restoreUser(String login, String userEmail, String userHash) throws ServiceException;
    Optional<User> findById(Long id) throws ServiceException;
    Optional<User> findByLoginHash(String login, String email, String hash) throws ServiceException;
    Long save(User user) throws ServiceException;
    List<User> findByCoachId(long coachId) throws ServiceException;
    Optional<User> getUserByCookieData(String login, String hash) throws ServiceException;
}
