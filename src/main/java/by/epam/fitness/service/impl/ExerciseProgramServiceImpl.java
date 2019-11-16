package by.epam.fitness.service.impl;

import by.epam.fitness.dao.ExerciseProgramDao;
import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.dao.impl.ExerciseProgramDaoImpl;
import by.epam.fitness.entity.ExerciseProgram;
import by.epam.fitness.service.ExerciseProgramService;
import by.epam.fitness.service.ServiceException;

import java.util.List;
import java.util.Optional;

public class ExerciseProgramServiceImpl implements ExerciseProgramService {
    private ExerciseProgramDao exerciseProgramDao = new ExerciseProgramDaoImpl();

    @Override
    public Long save(ExerciseProgram exerciseProgram) throws ServiceException {
        try {
            return exerciseProgramDao.save(exerciseProgram);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<ExerciseProgram> findExercisesByProgramId(Long programId) throws ServiceException {
        try {
            return exerciseProgramDao.findExercisesByProgramId(programId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<ExerciseProgram> findById(long id) throws ServiceException {
        try {
            return exerciseProgramDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean findByExerciseId(long exerciseId) throws ServiceException {
        try {
            return exerciseProgramDao.findByExerciseId(exerciseId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int deleteExercise(long exerciseId) throws ServiceException {
        try {
            return exerciseProgramDao.deleteExercise(exerciseId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
