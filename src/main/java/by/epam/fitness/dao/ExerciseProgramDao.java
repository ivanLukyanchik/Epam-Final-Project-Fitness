package by.epam.fitness.dao;

import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.ExerciseProgram;

import java.util.List;
import java.util.Optional;

public interface ExerciseProgramDao extends BaseDao<Long, ExerciseProgram> {
    Long save(ExerciseProgram exerciseProgram) throws DaoException;
    List<ExerciseProgram> findExercisesByProgramId(Long programId) throws DaoException;
    Optional<ExerciseProgram> findById(long id) throws DaoException;
    boolean findByExerciseId(long exerciseId) throws DaoException;
    int deleteExercise(long exerciseId) throws DaoException;
}
