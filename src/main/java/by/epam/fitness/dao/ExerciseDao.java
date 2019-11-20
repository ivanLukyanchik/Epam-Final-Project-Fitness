package by.epam.fitness.dao;

import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.Exercise;

import java.util.List;

public interface ExerciseDao extends BaseDao<Long, Exercise> {
    List<Exercise> findAll() throws DaoException;
}
