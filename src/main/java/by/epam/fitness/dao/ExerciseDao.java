package by.epam.fitness.dao;

import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.Exercise;

import java.util.List;

public interface ExerciseDao extends BaseDao<Long, Exercise> {
    List<Exercise> findAllLimited(int start, int total) throws DaoException;
    int deleteExercise(long exerciseId) throws DaoException;
    int getNumberOfRows() throws DaoException;
}
