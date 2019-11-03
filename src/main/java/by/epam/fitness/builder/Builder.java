package by.epam.fitness.builder;

import by.epam.fitness.entity.Entity;
import by.epam.fitness.service.ServiceException;

import java.sql.ResultSet;

public interface Builder <T extends Entity> {
    T build(ResultSet resultSet) throws ServiceException;
}
