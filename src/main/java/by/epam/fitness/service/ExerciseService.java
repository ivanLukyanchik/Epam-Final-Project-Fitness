package by.epam.fitness.service;

import by.epam.fitness.entity.Exercise;

import java.util.List;
import java.util.Optional;

public interface ExerciseService {
    List<Exercise> findAll() throws ServiceException;
    Optional<Exercise> findById(long id) throws ServiceException;
    Long save(Exercise exercise) throws ServiceException;
    int deleteExercise(long exerciseId) throws ServiceException;
}
