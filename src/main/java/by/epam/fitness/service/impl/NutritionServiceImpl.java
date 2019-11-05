package by.epam.fitness.service.impl;

import by.epam.fitness.dao.NutritionDao;
import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.dao.impl.NutritionDaoImpl;
import by.epam.fitness.entity.Nutrition;
import by.epam.fitness.service.NutritionService;
import by.epam.fitness.service.ServiceException;

public class NutritionServiceImpl implements NutritionService {
    private NutritionDao nutritionDao = new NutritionDaoImpl();

    @Override
    public Long save(Nutrition nutrition) throws ServiceException {
        try {
            return nutritionDao.save(nutrition);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
