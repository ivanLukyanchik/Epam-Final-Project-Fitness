package by.epam.fitness.dao;

import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.Exercise;

import java.util.List;
import java.util.Optional;

public interface ExerciseDao extends BaseDao<Long, Exercise> {
    List<Exercise> findAll() throws DaoException;
    Optional<Exercise> findById(long id) throws DaoException;
}
