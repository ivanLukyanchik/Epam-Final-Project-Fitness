package by.epam.fitness.service.impl;

import by.epam.fitness.entity.Exercise;
import by.epam.fitness.service.ExerciseService;
import by.epam.fitness.service.ServiceException;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class ExerciseServiceImplTest {
    private final ExerciseService exerciseService = new ExerciseServiceImpl();

    @Test
    public void testFindAll() throws ServiceException {
        List<Exercise> exercises = exerciseService.findAll(0, 2);
        boolean actual = exercises.size() > 0;
        assertTrue(actual);
    }

    @Test
    public void testFindById() throws ServiceException {
        boolean actual = exerciseService.findById(1).isPresent();
        assertTrue(actual);
    }
}