package by.epam.fitness.dao;

import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.Program;

import java.util.Optional;

/**
 * The interface Program dao.
 */
public interface ProgramDao extends BaseDao<Long, Program> {
    /**
     * Find program by id optional.
     *
     * @param programId the program id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Program> findProgramById(long programId) throws DaoException;
}
