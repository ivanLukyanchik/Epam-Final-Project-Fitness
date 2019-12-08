package by.epam.fitness.service.impl;

import by.epam.fitness.service.ClientService;
import by.epam.fitness.service.ServiceException;
import org.junit.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ClientServiceImplTest {
    private final ClientService clientService = new ClientServiceImpl();

//    @BeforeClass
//    public void setUp() {
//        clientService = new ClientServiceImpl();
//    }

    @Test
    public void checkUserByLoginPasswordPositiveTest() throws ServiceException {
        boolean actual = clientService.checkUserByLoginPassword("lol", "lol").isPresent();
        assertTrue(actual);
    }

    @Test
    public void checkUserByLoginPasswordNegativeTest() throws ServiceException {
        boolean actual = clientService.checkUserByLoginPassword("unknown", "user").isPresent();
        assertFalse(actual);
    }

    @Test
    public void isLoginUniqueTest() throws ServiceException {
        boolean actual = clientService.isLoginUnique("lol");
        assertFalse(actual);
    }

    @Test
    public void findByIdTest() throws ServiceException {
        boolean actual = clientService.findById(6L).isPresent();
        assertTrue(actual);
    }

    @Test
    public void findByInvalidIdTest() throws ServiceException {
        long testId = -999;
        boolean actual = clientService.findById(testId).isPresent();
        assertFalse(actual);
    }

    @Test
    public void findByCoachIdTest() throws ServiceException {
        boolean actual = clientService.findByCoachId(2).isEmpty();
        assertTrue(actual);
    }

    @Test
    public void findActiveByIdTest() throws ServiceException {
        boolean actual = clientService.findActiveById(10).isPresent();
        assertTrue(actual);
    }
}