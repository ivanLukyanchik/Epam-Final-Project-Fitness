package by.epam.fitness.service;

import by.epam.fitness.entity.OrderInformation;

import java.util.List;
import java.util.Optional;

/**
 * The interface Order information service.
 */
public interface OrderInformationService {
    /**
     * Save long.
     *
     * @param orderInformation the order information
     * @return the long
     * @throws ServiceException the service exception
     */
    Long save(OrderInformation orderInformation) throws ServiceException;

    /**
     * Find by client id optional.
     *
     * @param id the id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<OrderInformation> findByClientId(Long id) throws ServiceException;

    /**
     * Find orders by client id list.
     *
     * @param id the id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<OrderInformation> findOrdersByClientId(Long id) throws ServiceException;

    /**
     * Find all list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<OrderInformation> findAll() throws ServiceException;

    /**
     * Find asc price list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<OrderInformation> findAscPrice() throws ServiceException;

    /**
     * Find desc price list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<OrderInformation> findDescPrice() throws ServiceException;

    /**
     * Find asc payment data list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<OrderInformation> findAscPaymentData() throws ServiceException;

    /**
     * Find desc payment data list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<OrderInformation> findDescPaymentData() throws ServiceException;
}
