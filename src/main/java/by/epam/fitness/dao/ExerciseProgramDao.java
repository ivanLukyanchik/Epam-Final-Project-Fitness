package by.epam.fitness.dao;

import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.ExerciseProgram;

import java.util.List;

public interface ExerciseProgramDao extends BaseDao<Long, ExerciseProgram> {
    List<ExerciseProgram> findExercisesByProgramId(long programId) throws DaoException;
    boolean findByExerciseId(long exerciseId) throws DaoException;
    int deleteExercise(long exerciseId) throws DaoException;
}
