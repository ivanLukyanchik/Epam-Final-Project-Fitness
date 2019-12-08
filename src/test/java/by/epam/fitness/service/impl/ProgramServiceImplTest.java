package by.epam.fitness.service.impl;

import by.epam.fitness.service.ProgramService;
import by.epam.fitness.service.ServiceException;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ProgramServiceImplTest {
    private final ProgramService programService = new ProgramServiceImpl();

    @Test
    public void testFindProgramById() throws ServiceException {
        boolean actual = programService.findProgramById(14L).isPresent();
        assertTrue(actual);
    }
}