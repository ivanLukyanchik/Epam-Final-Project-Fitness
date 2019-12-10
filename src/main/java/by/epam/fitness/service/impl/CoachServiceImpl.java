package by.epam.fitness.service.impl;

import by.epam.fitness.dao.CoachDao;
import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.dao.impl.CoachDaoImpl;
import by.epam.fitness.entity.Coach;
import by.epam.fitness.service.CoachService;
import by.epam.fitness.service.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * The type Coach service.
 */
public class CoachServiceImpl implements CoachService {
    private CoachDao coachDao = new CoachDaoImpl();

    @Override
    public Optional<Coach> checkCoachByLoginPassword(String login, String password) throws ServiceException {
        try {
            return coachDao.checkCoachByLoginPassword(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Coach> findByClientId(long clientId) throws ServiceException {
        try {
            return coachDao.findByClientId(clientId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Coach> findById(long id) throws ServiceException {
        try {
            return coachDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Coach> findAll() throws ServiceException {
        try {
            return coachDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Long save(Coach coach) throws ServiceException {
        try {
            return coachDao.save(coach);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
