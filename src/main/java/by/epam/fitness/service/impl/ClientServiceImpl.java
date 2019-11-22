package by.epam.fitness.service.impl;

import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.dao.ClientDao;
import by.epam.fitness.dao.impl.ClientDaoImpl;
import by.epam.fitness.entity.Client;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.service.ClientService;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;
import java.util.Optional;

public class ClientServiceImpl implements ClientService {
    private ClientDao clientDao = new ClientDaoImpl();

    @Override
    public Optional<Client> checkUserByLoginPassword(String login, String password) throws ServiceException {
        try {
            return clientDao.checkUserByLoginPassword(login, DigestUtils.sha512Hex(password));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isLoginUnique(String patternLogin) throws ServiceException {
        try {
            return clientDao.isLoginUnique(patternLogin);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean registerUser(Client client) throws ServiceException {
        try {
            return clientDao.registerUser(client);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean restoreUser(String login, String userEmail, String userHash) throws ServiceException {
        try {
            return clientDao.restoreUser(login, userEmail, userHash);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Client> findById(Long id) throws ServiceException {
        try {
            return clientDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Client> findByLoginHash(String login, String email, String hash) throws ServiceException {
        try {
            return clientDao.findByLoginHash(login, email, hash);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Long save(Client client) throws ServiceException {
        try {
            return clientDao.save(client);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Client> findByCoachId(long coachId) throws ServiceException {
        try {
            return clientDao.findByCoachId(coachId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Client> getUserByCookieData(String login, String hash) throws ServiceException {
        try {
            return clientDao.getUserByCookieData(login, hash);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Client> findAll() throws ServiceException {
        try {
            return clientDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Client> findActiveById(long id) throws ServiceException {
        try {
            return clientDao.findActiveById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
