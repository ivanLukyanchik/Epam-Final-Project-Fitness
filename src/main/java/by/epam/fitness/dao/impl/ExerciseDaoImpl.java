package by.epam.fitness.dao.impl;

import by.epam.fitness.dao.ExerciseDao;
import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.Exercise;

import java.util.List;

public class ExerciseDaoImpl implements ExerciseDao {
    @Override
    public List<Exercise> findAll() throws DaoException {
        return null;
    }

    @Override
    public List<Exercise> findEntityById(Long id) {
        return null;
    }

    @Override
    public boolean delete(Exercise exercise) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Exercise update(Exercise exercise) {
        return null;
    }
}
