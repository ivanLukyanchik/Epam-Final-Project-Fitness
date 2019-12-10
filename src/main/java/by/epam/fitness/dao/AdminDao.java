package by.epam.fitness.dao;

import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.Admin;

import java.util.Optional;

/**
 * The interface Admin dao.
 */
public interface AdminDao extends BaseDao<Long, Admin> {
    /**
     * Check admin by login password optional.
     *
     * @param login    the login
     * @param password the password
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Admin> checkAdminByLoginPassword(String login, String password) throws DaoException;
}
