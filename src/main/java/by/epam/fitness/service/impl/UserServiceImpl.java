package by.epam.fitness.service.impl;

import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.dao.UserDao;
import by.epam.fitness.dao.impl.UserDaoImpl;
import by.epam.fitness.entity.User;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public Optional<User> checkUserByLoginPassword(String login, String password) throws ServiceException {
        try {
            String newPassword = DigestUtils.sha512Hex(password);
            return userDao.checkUserByLoginPassword(login, newPassword);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isLoginUnique(String patternLogin) throws ServiceException {
        try {
            return userDao.isLoginUnique(patternLogin);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean registerUser(User user) throws ServiceException {
        try {
            return userDao.registerUser(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean restoreUser(String login, String userEmail, String userHash) throws ServiceException {
        try {
            return userDao.restoreUser(login, userEmail, userHash);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> findById(Long id) throws ServiceException {
        try {
            return userDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> findByLoginHash(String login, String email, String hash) throws ServiceException {
        try {
            return userDao.findByLoginHash(login, email, hash);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Long save(User user) throws ServiceException {
        try {
            return userDao.save(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findByCoachId(long coachId) throws ServiceException {
        try {
            return userDao.findByCoachId(coachId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> getUserByCookieData(String login, String hash) throws ServiceException {
        try {
            return userDao.getUserByCookieData(login, hash);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
