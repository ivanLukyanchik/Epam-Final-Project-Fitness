package by.epam.fitness.dao;

import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.Admin;

import java.util.Optional;

public interface AdminDao extends BaseDao<Long, Admin> {
    Optional<Admin> checkAdminByLoginPassword(String login, String password) throws DaoException;
}
