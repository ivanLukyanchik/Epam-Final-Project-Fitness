package by.epam.fitness.service;

import by.epam.fitness.entity.Admin;

import java.util.Optional;

/**
 * The interface Admin service.
 */
public interface AdminService {
    /**
     * Check admin by login password optional.
     *
     * @param login    the login
     * @param password the password
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Admin> checkAdminByLoginPassword(String login, String password) throws ServiceException;
}
