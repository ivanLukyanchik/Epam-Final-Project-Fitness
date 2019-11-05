package by.epam.fitness.dao;

import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.Program;

public interface ProgramDao extends BaseDao<Long, Program> {
    Long save(Program program) throws DaoException;
}
