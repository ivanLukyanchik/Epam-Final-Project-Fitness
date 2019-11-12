package by.epam.fitness.service;

import by.epam.fitness.entity.ExerciseProgram;

import java.util.List;
import java.util.Optional;

public interface ExerciseProgramService {
    List<ExerciseProgram> findExercisesByProgramId(Long programId) throws ServiceException;
    Optional<ExerciseProgram> findById(long id) throws ServiceException;
    int deleteExercise(long exerciseProgramId) throws ServiceException;
}
