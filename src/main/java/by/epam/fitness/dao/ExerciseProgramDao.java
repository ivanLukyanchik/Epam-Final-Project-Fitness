package by.epam.fitness.dao;

import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.ExerciseProgram;

import java.util.List;

/**
 * The interface Exercise program dao.
 */
public interface ExerciseProgramDao extends BaseDao<Long, ExerciseProgram> {
    /**
     * Find exercises by program id list.
     *
     * @param programId the program id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<ExerciseProgram> findExercisesByProgramId(long programId) throws DaoException;

    /**
     * Find by exercise id boolean.
     *
     * @param exerciseId the exercise id
     * @param programId  the program id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean findByExerciseId(long exerciseId, long programId) throws DaoException;

    /**
     * Delete exercise int.
     *
     * @param exerciseId the exercise id
     * @return the int
     * @throws DaoException the dao exception
     */
    int deleteExercise(long exerciseId) throws DaoException;
}
