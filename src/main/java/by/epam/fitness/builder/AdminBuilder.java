package by.epam.fitness.builder;

import by.epam.fitness.entity.Admin;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.util.database.AdminTableConst;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Admin builder.
 */
public class AdminBuilder implements Builder {

    @Override
    public Admin build(ResultSet resultSet) throws ServiceException {
        try {
            long id = resultSet.getLong(AdminTableConst.ID.getFieldName());
            String name = resultSet.getString(AdminTableConst.NAME.getFieldName());
            String surname = resultSet.getString(AdminTableConst.SURNAME.getFieldName());
            String login = resultSet.getString(AdminTableConst.LOGIN.getFieldName());
            String password = resultSet.getString(AdminTableConst.PASSWORD.getFieldName());
            return new Admin(id, name, surname, login, password);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
