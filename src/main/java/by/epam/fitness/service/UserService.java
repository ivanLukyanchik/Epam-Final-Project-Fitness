package by.epam.fitness.service;

import by.epam.fitness.entity.User;

public interface UserService {

    boolean checkUserByLoginPassword(String login, String password) throws ServiceException;
    boolean registerUser1(User user) throws ServiceException;
    boolean registerUser2(String userEmail, String userHash) throws ServiceException;
    boolean restoreUser1(String login, String userEmail) throws ServiceException;
    boolean restoreUser2(String email, String newPassword, String login) throws ServiceException;
}
