package by.epam.fitness.dao;

import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.OrderInformation;

import java.util.List;
import java.util.Optional;

public interface OrderInformationDao extends BaseDao<Long, OrderInformation> {
    Long save(OrderInformation orderInformation) throws DaoException;
    Optional<OrderInformation> findByClientId(Long id) throws DaoException;
    List<OrderInformation> findOrdersByClientId(Long id) throws DaoException;
}
