package by.epam.fitness.service;

import by.epam.fitness.entity.Coach;

import java.util.List;
import java.util.Optional;

public interface CoachService {
    Optional<Coach> checkCoachByLoginPassword(String login, String password) throws ServiceException;
    Optional<Coach> findByClientId(long clientId) throws ServiceException; // FIXME: 11.11.2019 все id на примитивный long поменять
    Optional<Coach> findById(long id) throws ServiceException;
    List<Coach> findAll() throws ServiceException;
}
