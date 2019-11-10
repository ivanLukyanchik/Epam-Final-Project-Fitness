package by.epam.fitness.service;

import by.epam.fitness.entity.OrderInformation;

import java.util.List;
import java.util.Optional;

public interface OrderInformationService {
    Long save(OrderInformation orderInformation) throws ServiceException;
    Optional<OrderInformation> findByClientId(Long id) throws ServiceException;
    List<OrderInformation> findOrdersByClientId(Long id) throws ServiceException;
}
