package by.epam.fitness.service;

import by.epam.fitness.entity.ExerciseProgram;

import java.util.List;
import java.util.Optional;

/**
 * The interface Exercise program service.
 */
public interface ExerciseProgramService {
    /**
     * Save long.
     *
     * @param exerciseProgram the exercise program
     * @return the long
     * @throws ServiceException the service exception
     */
    Long save(ExerciseProgram exerciseProgram) throws ServiceException;

    /**
     * Find exercises by program id list.
     *
     * @param programId the program id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<ExerciseProgram> findExercisesByProgramId(Long programId) throws ServiceException;

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<ExerciseProgram> findById(long id) throws ServiceException;

    /**
     * Find by exercise id boolean.
     *
     * @param exerciseId the exercise id
     * @param programId  the program id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean findByExerciseId(long exerciseId, long programId) throws ServiceException;

    /**
     * Delete exercise int.
     *
     * @param exerciseId the exercise id
     * @return the int
     * @throws ServiceException the service exception
     */
    int deleteExercise(long exerciseId) throws ServiceException;
}
