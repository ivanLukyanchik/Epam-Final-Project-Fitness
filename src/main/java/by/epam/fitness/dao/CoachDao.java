package by.epam.fitness.dao;

import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.Coach;

import java.util.Optional;

public interface CoachDao extends BaseDao<Long, Coach> {
    Optional<Coach> checkCoachByLoginPassword(String login, String password) throws DaoException;
}
