package by.epam.fitness.service.impl;

import by.epam.fitness.dao.CoachDao;
import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.dao.impl.CoachDaoImpl;
import by.epam.fitness.entity.Coach;
import by.epam.fitness.service.CoachService;
import by.epam.fitness.service.ServiceException;

import java.util.Optional;

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
}
