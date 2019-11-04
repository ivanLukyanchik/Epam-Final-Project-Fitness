package by.epam.fitness.service;

import by.epam.fitness.entity.Coach;

import java.util.Optional;

public interface CoachService {
    Optional<Coach> checkCoachByLoginPassword(String login, String password) throws ServiceException;
}
