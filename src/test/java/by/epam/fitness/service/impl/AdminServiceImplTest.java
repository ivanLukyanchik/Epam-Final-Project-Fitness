package by.epam.fitness.service.impl;

import by.epam.fitness.service.AdminService;
import by.epam.fitness.service.ServiceException;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

public class AdminServiceImplTest {
    private final AdminService adminService = new AdminServiceImpl();

    @Test
    public void checkAdminByLoginPasswordPositiveTest() throws ServiceException {
        boolean actual = adminService.checkAdminByLoginPassword("admin", "admin").isPresent();
        assertTrue(actual);
    }

    @Test
    public void checkAdminByLoginPasswordNegativeTest() throws ServiceException {
        boolean actual = adminService.checkAdminByLoginPassword("unknown", "admin").isPresent();
        assertFalse(actual);
    }
}