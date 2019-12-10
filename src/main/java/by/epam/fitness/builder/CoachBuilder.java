package by.epam.fitness.builder;

import by.epam.fitness.entity.Coach;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.util.database.CoachTableConst;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Coach builder.
 */
public class CoachBuilder implements Builder<Coach> {
    @Override
    public Coach build(ResultSet resultSet) throws ServiceException {
        try {
            long id = resultSet.getLong(CoachTableConst.ID.getFieldName());
            String name = resultSet.getString(CoachTableConst.NAME.getFieldName());
            String surname = resultSet.getString(CoachTableConst.SURNAME.getFieldName());
            String patronymic = resultSet.getString(CoachTableConst.PATRONYMIC.getFieldName());
            String login = resultSet.getString(CoachTableConst.LOGIN.getFieldName());
            String password = resultSet.getString(CoachTableConst.PASSWORD.getFieldName());
            return new Coach(id, name, surname, patronymic, login, password);
        } catch (SQLException e) {
          throw new ServiceException(e.getMessage(), e);
        }
    }
}
