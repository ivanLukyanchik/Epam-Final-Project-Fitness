package by.epam.fitness.service;

import by.epam.fitness.entity.Program;

import java.util.Optional;

/**
 * The interface Program service.
 */
public interface ProgramService {
    /**
     * Save long.
     *
     * @param program the program
     * @return the long
     * @throws ServiceException the service exception
     */
    Long save(Program program) throws ServiceException;

    /**
     * Find program by id optional.
     *
     * @param programId the program id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Program> findProgramById(Long programId) throws ServiceException;
}
