package by.epam.fitness.dao;

import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.Coach;

import java.util.List;
import java.util.Optional;

public interface CoachDao extends BaseDao<Long, Coach> {
    Optional<Coach> checkCoachByLoginPassword(String login, String password) throws DaoException;
    Optional<Coach> findByClientId(long clientId) throws DaoException;
    Optional<Coach> findById(Long id) throws DaoException;
    List<Coach> findAll() throws DaoException;
}
