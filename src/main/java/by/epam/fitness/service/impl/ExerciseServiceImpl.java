package by.epam.fitness.service.impl;

import by.epam.fitness.dao.ExerciseDao;
import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.dao.impl.ExerciseDaoImpl;
import by.epam.fitness.entity.Exercise;
import by.epam.fitness.service.ExerciseService;
import by.epam.fitness.service.ServiceException;

import java.util.List;
import java.util.Optional;

public class ExerciseServiceImpl implements ExerciseService {
    private ExerciseDao exerciseDao = new ExerciseDaoImpl();

    @Override
    public List<Exercise> findAll(int start, int total) throws ServiceException {
        try {
            return exerciseDao.findAllLimited(start, total);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<Exercise> findById(long id) throws ServiceException {
        try {
            return exerciseDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Long save(Exercise exercise) throws ServiceException {
        try {
            return exerciseDao.save(exercise);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int deleteExercise(long exerciseId) throws ServiceException {
        try {
            return exerciseDao.deleteExercise(exerciseId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getNumberOfPages(int totalPerPage) throws ServiceException {
        try {
            int rows = exerciseDao.getNumberOfRows();
            int numberOfPages = rows / totalPerPage;
            if (numberOfPages % totalPerPage > 0) {
                numberOfPages++;
            }
            return numberOfPages;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}