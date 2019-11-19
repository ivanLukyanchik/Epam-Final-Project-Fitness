package by.epam.fitness.dao;

import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends BaseDao<Long, User> {
    Optional<User> checkUserByLoginPassword(String login, String password) throws DaoException;
    boolean isLoginUnique(String patternLogin) throws DaoException;
    boolean registerUser(User user) throws DaoException;
    boolean restoreUser(String login, String userEmail, String userHash) throws DaoException;
    Optional<User> findById(Long id) throws DaoException;
    Optional<User> findByLoginHash(String login, String email, String hash) throws DaoException;
    Long save(User user) throws DaoException;
    List<User> findByCoachId(long coachId) throws DaoException;
    Optional<User> getUserByCookieData(String login, String hash) throws DaoException;
}
