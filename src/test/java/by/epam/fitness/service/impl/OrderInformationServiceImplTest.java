package by.epam.fitness.service.impl;

import by.epam.fitness.entity.OrderInformation;
import by.epam.fitness.service.OrderInformationService;
import by.epam.fitness.service.ServiceException;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class OrderInformationServiceImplTest {
    private final OrderInformationService orderInformationService = new OrderInformationServiceImpl();

    @Test
    public void testFindByClientId() throws ServiceException {
        boolean actual = orderInformationService.findByClientId(6L).isPresent();
        assertTrue(actual);
    }

    @Test
    public void testFindOrdersByClientId() throws ServiceException {
        List<OrderInformation> orders = orderInformationService.findOrdersByClientId(6L);
        boolean actual = orders.size() > 0;
        assertTrue(actual);
    }

    @Test
    public void testFindAll() throws ServiceException {
        boolean actual = orderInformationService.findAll().isEmpty();
        assertFalse(actual);
    }
}