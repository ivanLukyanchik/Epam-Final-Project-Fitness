package by.epam.fitness.builder;

import by.epam.fitness.entity.Entity;
import by.epam.fitness.service.ServiceException;

import java.sql.ResultSet;

/**
 * The interface Builder.
 *
 * @param <T> the type parameter
 */
public interface Builder <T extends Entity> {
    /**
     * Build t.
     *
     * @param resultSet the result set
     * @return the t
     * @throws ServiceException the service exception
     */
    T build(ResultSet resultSet) throws ServiceException;
}
