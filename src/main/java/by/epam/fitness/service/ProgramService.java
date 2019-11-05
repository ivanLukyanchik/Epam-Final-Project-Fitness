package by.epam.fitness.service;

import by.epam.fitness.entity.Program;

public interface ProgramService {
    Long save(Program program) throws ServiceException;
}
