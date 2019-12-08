package by.epam.fitness.service.impl;

import by.epam.fitness.entity.ExerciseProgram;
import by.epam.fitness.service.ExerciseProgramService;
import by.epam.fitness.service.ServiceException;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ExerciseProgramServiceImplTest {
    private final ExerciseProgramService exerciseProgramService = new ExerciseProgramServiceImpl();

    @Test
    public void testFindExercisesByProgramId() throws ServiceException {
        List<ExerciseProgram> exerciseProgramList = exerciseProgramService.findExercisesByProgramId((long) 14);
        boolean actual = exerciseProgramList.size() > 0;
        assertTrue(actual);
    }

    @Test
    public void findByIdNegativeTest() throws ServiceException {
        boolean actual = exerciseProgramService.findById(-99).isPresent();
        assertFalse(actual);
    }

    @Test
    public void findByExerciseIdNegativeTest() throws ServiceException {
        boolean actual = exerciseProgramService.findByExerciseId(2, -99);
        assertFalse(actual);
    }
}