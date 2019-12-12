package by.epam.fitness.dao;

import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.OrderInformation;

import java.util.List;
import java.util.Optional;

/**
 * The interface Order information dao.
 */
public interface OrderInformationDao extends BaseDao<Long, OrderInformation> {
    /**
     * Find by client id optional.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<OrderInformation> findByClientId(long id) throws DaoException;

    /**
     * Find orders by client id list.
     *
     * @param id the id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<OrderInformation> findOrdersByClientId(long id) throws DaoException;

    /**
     * Find all list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<OrderInformation> findAll() throws DaoException;

    /**
     * Find asc price list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<OrderInformation> findAscPrice() throws DaoException;

    /**
     * Find desc price list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<OrderInformation> findDescPrice() throws DaoException;

    /**
     * Find asc payment data list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<OrderInformation> findAscPaymentData() throws DaoException;

    /**
     * Find desc payment data list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<OrderInformation> findDescPaymentData() throws DaoException;
}
