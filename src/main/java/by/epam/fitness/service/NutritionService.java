package by.epam.fitness.service;

import by.epam.fitness.entity.Nutrition;

import java.util.Optional;

/**
 * The interface Nutrition service.
 */
public interface NutritionService {
    /**
     * Save long.
     *
     * @param nutrition the nutrition
     * @return the long
     * @throws ServiceException the service exception
     */
    Long save(Nutrition nutrition) throws ServiceException;

    /**
     * Find by client id optional.
     *
     * @param clientId the client id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Nutrition> findByClientId(long clientId) throws ServiceException;

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Nutrition> findById(long id) throws ServiceException;
}
