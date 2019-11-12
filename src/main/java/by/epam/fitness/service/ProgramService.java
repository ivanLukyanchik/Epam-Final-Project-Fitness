package by.epam.fitness.service;

import by.epam.fitness.entity.Program;

import java.util.Optional;

public interface ProgramService {
    Long save(Program program) throws ServiceException;
    Optional<Program> findProgramById(Long programId) throws ServiceException;
}
