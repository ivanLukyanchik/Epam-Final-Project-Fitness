package by.epam.fitness.service.impl;

import by.epam.fitness.dao.ProgramDao;
import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.dao.impl.ProgramDaoImpl;
import by.epam.fitness.entity.Program;
import by.epam.fitness.service.ProgramService;
import by.epam.fitness.service.ServiceException;

import java.util.Optional;

/**
 * The type Program service.
 */
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

    @Override
    public Optional<Program> findProgramById(Long programId) throws ServiceException {
        try {
            return programDao.findProgramById(programId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
