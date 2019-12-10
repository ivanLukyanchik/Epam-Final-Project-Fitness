package by.epam.fitness.service;

import by.epam.fitness.entity.Exercise;

import java.util.List;
import java.util.Optional;

/**
 * The interface Exercise service.
 */
public interface ExerciseService {
    /**
     * Find all list.
     *
     * @param start the start
     * @param total the total
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Exercise> findAll(int start, int total) throws ServiceException;

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Exercise> findById(long id) throws ServiceException;

    /**
     * Save long.
     *
     * @param exercise the exercise
     * @return the long
     * @throws ServiceException the service exception
     */
    Long save(Exercise exercise) throws ServiceException;

    /**
     * Delete exercise int.
     *
     * @param exerciseId the exercise id
     * @return the int
     * @throws ServiceException the service exception
     */
    int deleteExercise(long exerciseId) throws ServiceException;

    /**
     * Gets number of pages.
     *
     * @param totalPerPage the total per page
     * @return the number of pages
     * @throws ServiceException the service exception
     */
    int getNumberOfPages(int totalPerPage) throws ServiceException;
}
