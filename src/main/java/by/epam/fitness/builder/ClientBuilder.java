package by.epam.fitness.builder;

import by.epam.fitness.entity.User;
import by.epam.fitness.service.ServiceException;
import by.epam.fitness.util.database.ClientTableConst;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientBuilder implements Builder<User>{

    @Override
    public User build(ResultSet resultSet) throws ServiceException {
        try {
            String name = resultSet.getString(ClientTableConst.NAME.getFieldName());
            String surname = resultSet.getString(ClientTableConst.SURNAME.getFieldName());
            String login = resultSet.getString(ClientTableConst.LOGIN.getFieldName());
            String password = resultSet.getString(ClientTableConst.PASSWORD.getFieldName());
            String email = resultSet.getString(ClientTableConst.EMAIL.getFieldName());
            return new User(name, surname, login, password, email);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
