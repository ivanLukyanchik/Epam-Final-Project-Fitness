package by.epam.fitness.dao.impl;

import by.epam.fitness.builder.OrderInformationBuilder;
import by.epam.fitness.dao.OrderInformationDao;
import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.OrderInformation;
import by.epam.fitness.entity.User;
import by.epam.fitness.pool.ConnectionPool;
import by.epam.fitness.service.ServiceException;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class OrderInformationDaoImpl implements OrderInformationDao {
    private static final String SQL_SAVE_TABLE = "INSERT INTO order_information (cost, payment_data, membership_end_date, client_id, card_number) VALUES (?,?,?,?,?)";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM order_information WHERE client_id=?";

    @Override
    public Long save(OrderInformation orderInformation) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        BigDecimal cost = orderInformation.getCost();
        Timestamp paymentData = orderInformation.getPaymentData();
        Date membershipEndDate = orderInformation.getMembershipEndDate();
        Long clientId = orderInformation.getClientId();
        String cardNumber = orderInformation.getCardNumber();
        Long generatedId = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SAVE_TABLE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setBigDecimal(1, cost);
            preparedStatement.setTimestamp(2, paymentData);
            preparedStatement.setDate(3, membershipEndDate);
            preparedStatement.setLong(4, clientId);
            preparedStatement.setString(5, cardNumber);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                generatedId = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return generatedId;
    }

    @Override
    public Optional<OrderInformation> findByClientId(Long id) throws DaoException {
        boolean result = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        OrderInformation orderInformation = null;
        try{
            connection = ConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                OrderInformationBuilder builder = new OrderInformationBuilder();
                orderInformation = builder.build(resultSet);
                result = true;
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return result ? Optional.of(orderInformation) : Optional.empty();
    }

    @Override
    public List<OrderInformation> findAll() {
        return null;
    }

    @Override
    public List<OrderInformation> findEntityById(Long id) {
        return null;
    }

    @Override
    public boolean delete(OrderInformation orderInformation) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public OrderInformation update(OrderInformation orderInformation) {
        return null;
    }
}