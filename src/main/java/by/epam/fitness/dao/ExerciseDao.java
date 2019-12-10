package by.epam.fitness.dao;

import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.Exercise;

import java.util.List;

/**
 * The interface Exercise dao.
 */
public interface ExerciseDao extends BaseDao<Long, Exercise> {
    /**
     * Find all limited list.
     *
     * @param start the start
     * @param total the total
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Exercise> findAllLimited(int start, int total) throws DaoException;

    /**
     * Delete exercise int.
     *
     * @param exerciseId the exercise id
     * @return the int
     * @throws DaoException the dao exception
     */
    int deleteExercise(long exerciseId) throws DaoException;

    /**
     * Gets number of rows.
     *
     * @return the number of rows
     * @throws DaoException the dao exception
     */
    int getNumberOfRows() throws DaoException;
}
