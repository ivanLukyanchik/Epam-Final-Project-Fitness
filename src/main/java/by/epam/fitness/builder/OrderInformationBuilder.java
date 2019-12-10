package by.epam.fitness.builder;

import by.epam.fitness.entity.OrderInformation;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.util.database.OrderInformationTableConst;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Date;

/**
 * The type Order information builder.
 */
public class OrderInformationBuilder implements Builder<OrderInformation> {
    @Override
    public OrderInformation build(ResultSet resultSet) throws ServiceException {
        try {
            Long orderID = resultSet.getLong(OrderInformationTableConst.ID.getFieldName());
            BigDecimal cost = resultSet.getBigDecimal(OrderInformationTableConst.COST.getFieldName());
            Timestamp paymentData = resultSet.getTimestamp(OrderInformationTableConst.PAYMENT_DATA.getFieldName());
            Date membership_end_date = resultSet.getDate(OrderInformationTableConst.MEMBERSHIP_END_DATE.getFieldName());
            Long clientId = resultSet.getLong(OrderInformationTableConst.CLIENT_ID.getFieldName());
            String cardNumber = resultSet.getString(OrderInformationTableConst.CARD_NUMBER.getFieldName());
            return new OrderInformation(orderID, cost, paymentData, membership_end_date, clientId, cardNumber) ;
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
