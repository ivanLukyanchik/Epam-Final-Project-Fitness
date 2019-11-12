package by.epam.fitness.dao;

import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.Program;

import java.util.Optional;

public interface ProgramDao extends BaseDao<Long, Program> {
    Long save(Program program) throws DaoException;
    Optional<Program> findProgramById(Long programId) throws DaoException;
}
