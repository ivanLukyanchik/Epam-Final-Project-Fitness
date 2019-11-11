package by.epam.fitness.dao;

import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.Entity;
import by.epam.fitness.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface BaseDao <K, T extends Entity> {
    Logger log = LogManager.getLogger(BaseDao.class);

    List<T> findAll() throws DaoException;
    List<T> findEntityById(K id);
    boolean delete(T t);
    boolean delete(K id);
    T update(T t);

    default void close(Statement statement) {
        if (statement  != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                log.error("SQL exception occurred here");
            }
        }
    }

    default void close (Connection connection) {
        if (connection != null) {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
    }
}
