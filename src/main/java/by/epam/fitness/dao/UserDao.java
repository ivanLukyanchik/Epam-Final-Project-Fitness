package by.epam.fitness.dao;

import by.epam.fitness.entity.User;

public interface UserDao extends BaseDao<Long, User> {
    boolean checkUserByLoginPassword(String login, String password) throws DaoException;
    boolean registerUser1(User user) throws DaoException;
    boolean registerUser2(String userEmail, String userHash) throws DaoException;
    boolean restoreUser1(String login, String userEmail) throws DaoException;
    boolean restoreUser2(String email, String newPassword, String login) throws DaoException;
}
