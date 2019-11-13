package by.epam.fitness.service;

import by.epam.fitness.entity.Nutrition;

import java.util.Optional;

public interface NutritionService {
    Long save(Nutrition nutrition) throws ServiceException;
    Optional<Nutrition> findByClientId(long clientId) throws ServiceException;
    Optional<Nutrition> findById(long id) throws ServiceException;
}
