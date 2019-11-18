package by.epam.fitness.dao;

import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.Entity;
import by.epam.fitness.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public interface BaseDao <K, T extends Entity> {
    Logger log = LogManager.getLogger(BaseDao.class);

    K save(T t) throws DaoException;
    Optional<T> findById(K id) throws DaoException;

    default void close(Statement statement) {
        if (statement  != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                log.error("SQL exception occurred here", e);
            }
        }
    }

    default void close (Connection connection) {
        if (connection != null) {
            ConnectionPool.INSTANCE.releaseConnection(connection);
        }
    }
}
