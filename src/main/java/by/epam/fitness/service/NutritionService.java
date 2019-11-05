package by.epam.fitness.service;

import by.epam.fitness.entity.Nutrition;

public interface NutritionService {
    Long save(Nutrition nutrition) throws ServiceException;
}
