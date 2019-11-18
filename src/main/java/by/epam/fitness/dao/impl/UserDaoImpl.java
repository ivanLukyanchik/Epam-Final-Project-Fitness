package by.epam.fitness.dao.impl;

import by.epam.fitness.builder.ClientBuilder;
import by.epam.fitness.dao.UserDao;
import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.User;
import by.epam.fitness.pool.ConnectionPool;
import by.epam.fitness.service.ServiceException;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final String SQL_CHECK_USER_BY_LOGIN_PASSWORD = "SELECT * FROM client WHERE login=? AND password=? AND active='1'";
    private static final String SQL_FIND_USER = "SELECT email, hash, active FROM client WHERE email=? AND hash=? AND active='0'";
    private static final String SQL_ACTIVATE_USER = "UPDATE client SET active='1' WHERE email=? AND hash=?";
    private static final String SQL_IS_LOGIN_UNIQUE = "SELECT login FROM client WHERE login=?";
    private static final String SQL_RESTORE_USER1 = "SELECT hash FROM client WHERE email=? AND login=? AND active='1'";
    private static final String SQL_DEACTIVATE_AND_HASH = "UPDATE client SET active='0', hash=? WHERE email=? AND login=?";
    private static final String SQL_RESTORE_USER2 = "UPDATE client SET password=?, active='1' WHERE email=? AND login=? AND hash=? AND active='0'";
    private static final String SQL_UPDATE_USER = "UPDATE client SET coach_id=?, name=?, surname=?, login=?, password=?, email=?, hash=?, membership_purchased_number=?, personal_discount=?, program_id=?, image=?, active=? WHERE id_client=?";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM client WHERE id_client=?";
    private static final String SQL_CREATE_USER = "INSERT INTO client (coach_id, name, surname, login, password, email, hash, membership_purchased_number, personal_discount, program_id, image) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_FIND_BY_COACH_ID = "SELECT * FROM client WHERE coach_id=?";
    private static final String SQL_FIND_USER_BY_COOKIE = "SELECT * FROM client WHERE login=? AND hash=?";
    private ClientBuilder builder = new ClientBuilder();

    @Override
    public Optional<User> checkUserByLoginPassword(String login, String newPassword) throws DaoException {
        boolean result = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        User user = null;
        try{
            connection = ConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_CHECK_USER_BY_LOGIN_PASSWORD);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, newPassword);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = builder.build(resultSet);
                result = true;
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return result ? Optional.of(user) : Optional.empty();
    }

    private boolean isLoginUnique(String patternLogin) throws DaoException {
        boolean result = true;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_IS_LOGIN_UNIQUE);
            preparedStatement.setString(1, patternLogin);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = false;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return result;
    }

    @Override
    public boolean registerUser1(User user) throws DaoException {
        if (!isLoginUnique(user.getLogin())) {
            return false;
        }
        Long generatedId = save(user);
        return generatedId != null;
    }

    @Override
    public boolean registerUser2(String userEmail, String userHash) throws DaoException {
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_USER);
            preparedStatement.setString(1, userEmail);
            preparedStatement.setString(2, userHash);
            if (preparedStatement.execute()) {
                preparedStatement = connection.prepareStatement(SQL_ACTIVATE_USER);
                preparedStatement.setString(1, userEmail);
                preparedStatement.setString(2, userHash);
                result = preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
        close(preparedStatement);
        close(connection);
    }
        return result != 0;
    }

    @Override
    public boolean restoreUser1(String login, String userEmail, String userHash) throws DaoException {
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_RESTORE_USER1);
            preparedStatement.setString(1, userEmail);
            preparedStatement.setString(2, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                preparedStatement = connection.prepareStatement(SQL_DEACTIVATE_AND_HASH);
                preparedStatement.setString(1, userHash);
                preparedStatement.setString(2, userEmail);
                preparedStatement.setString(3, login);
                result = preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return result != 0;
    }

    @Override
    public boolean restoreUser2(String email, String newPassword, String login, String userHash) throws DaoException {
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_RESTORE_USER2);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, login);
            preparedStatement.setString(4, userHash);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return result != 0;
    }

    @Override
    public Optional<User> findById(Long id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        User user = null;
        try{
            connection = ConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = builder.build(resultSet);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Long save(User user) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Long coachId = user.getCoachId();
        String name = user.getName();
        String surname = user.getSurname();
        String login = user.getLogin();
        String password = user.getPassword();
        String email = user.getEmail();
        boolean active = user.isActive();
        String userHash = user.getUserHash();
        Integer membershipNumber = user.getMembershipNumber();
        Float personalDiscount = user.getPersonalDiscount();
        Long programId = user.getProgramId();
        InputStream is = user.getIs();
        Long generatedId = null;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            if (user.getId() != null) {
                preparedStatement = connection.prepareStatement(SQL_UPDATE_USER, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setBoolean(12, active);
                preparedStatement.setLong(13, user.getId());
            } else {
                preparedStatement = connection.prepareStatement(SQL_CREATE_USER, Statement.RETURN_GENERATED_KEYS);
            }
            preparedStatement.setObject(1, coachId);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, surname);
            preparedStatement.setString(4, login);
            preparedStatement.setString(5, password);
            preparedStatement.setString(6, email);
            preparedStatement.setString(7, userHash);
            preparedStatement.setInt(8, membershipNumber);
            preparedStatement.setFloat(9, personalDiscount);
            preparedStatement.setLong(10, programId);
            preparedStatement.setBlob(11, is);
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
    public boolean save1(User user) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Long coachId = user.getCoachId();
        String name = user.getName();
        String surname = user.getSurname();
        String login = user.getLogin();
        String password = user.getPassword();
        String email = user.getEmail();
        boolean active = user.isActive();
        String userHash = user.getUserHash();
        Integer membershipNumber = user.getMembershipNumber();
        Float personalDiscount = user.getPersonalDiscount();
        Long programId = user.getProgramId();
        InputStream is = user.getIs();
        int result;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            if (user.getId() != null) {
                preparedStatement = connection.prepareStatement(SQL_UPDATE_USER, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setBoolean(12, active);
                preparedStatement.setLong(13, user.getId());
            } else {
                preparedStatement = connection.prepareStatement(SQL_CREATE_USER, Statement.RETURN_GENERATED_KEYS);
            }
            preparedStatement.setObject(1, coachId);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, surname);
            preparedStatement.setString(4, login);
            preparedStatement.setString(5, password);
            preparedStatement.setString(6, email);
            preparedStatement.setString(7, userHash);
            preparedStatement.setInt(8, membershipNumber);
            preparedStatement.setFloat(9, personalDiscount);
            preparedStatement.setLong(10, programId);
            preparedStatement.setBlob(11, is);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return result != 0;
    }

    @Override
    public List<User> findByCoachId(long coachId) throws DaoException {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        User user;
        try {
            connection = ConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_COACH_ID);
            preparedStatement.setLong(1, coachId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = builder.build(resultSet);
                users.add(user);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return users;
    }

    @Override
    public Optional<User> getUserByCookieData(String login, String hash) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        User user = null;
        try{
            connection = ConnectionPool.INSTANCE.getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_COOKIE);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, hash);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = builder.build(resultSet);
            }
        } catch (SQLException | ServiceException e) {
            throw new DaoException(e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return Optional.ofNullable(user);
    }
}
