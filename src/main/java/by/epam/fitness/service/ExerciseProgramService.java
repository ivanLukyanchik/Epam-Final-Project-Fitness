package by.epam.fitness.service;

import by.epam.fitness.entity.ExerciseProgram;

import java.util.List;
import java.util.Optional;

public interface ExerciseProgramService {
    Long save(ExerciseProgram exerciseProgram) throws ServiceException;
    List<ExerciseProgram> findExercisesByProgramId(Long programId) throws ServiceException;
    Optional<ExerciseProgram> findById(long id) throws ServiceException;
    boolean findByExerciseId(long exerciseId) throws ServiceException;
    int deleteExercise(long exerciseId) throws ServiceException;
}
