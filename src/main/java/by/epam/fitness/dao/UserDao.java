package by.epam.fitness.dao;

import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.User;

import java.util.Optional;

public interface UserDao extends BaseDao<Long, User> {
    Optional<User> checkUserByLoginPassword(String login, String password) throws DaoException;
    boolean registerUser1(User user) throws DaoException;
    boolean registerUser2(String userEmail, String userHash) throws DaoException;
    boolean restoreUser1(String login, String userEmail, String userHash) throws DaoException;
    boolean restoreUser2(String email, String newPassword, String login, String userHash) throws DaoException;
    Optional<User> findById(Long id) throws DaoException;
    boolean save(User user) throws DaoException;
}
