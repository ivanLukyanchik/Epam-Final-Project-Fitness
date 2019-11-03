package by.epam.fitness.service;

import by.epam.fitness.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> checkUserByLoginPassword(String login, String password) throws ServiceException;
    boolean registerUser1(User user) throws ServiceException;
    boolean registerUser2(String userEmail, String userHash) throws ServiceException;
    boolean restoreUser1(String login, String userEmail, String userHash) throws ServiceException;
    boolean restoreUser2(String email, String newPassword, String login, String userHash) throws ServiceException;
    boolean updateUser(User user, String oldLogin) throws ServiceException;
}
