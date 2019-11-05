package by.epam.fitness.service.impl;

import by.epam.fitness.dao.ProgramDao;
import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.dao.impl.ProgramDaoImpl;
import by.epam.fitness.entity.Program;
import by.epam.fitness.service.ProgramService;
import by.epam.fitness.service.ServiceException;

public class ProgramServiceImpl implements ProgramService {
    private ProgramDao programDao = new ProgramDaoImpl();

    @Override
    public Long save(Program program) throws ServiceException {
        try {
            return programDao.save(program);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
