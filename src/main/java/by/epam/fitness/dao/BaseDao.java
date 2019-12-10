package by.epam.fitness.dao;

import by.epam.fitness.dao.exception.DaoException;
import by.epam.fitness.entity.Entity;
import by.epam.fitness.pool.ConnectionPool;
import by.epam.fitness.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

/**
 * The interface Base dao.
 *
 * @param <K> the type parameter
 * @param <T> the type parameter
 */
public interface BaseDao <K, T extends Entity> {
    /**
     * The constant log.
     */
    Logger log = LogManager.getLogger(BaseDao.class);

    /**
     * Save k.
     *
     * @param t the t
     * @return the k
     * @throws DaoException the dao exception
     */
    K save(T t) throws DaoException;

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<T> findById(K id) throws DaoException;

    /**
     * Close.
     *
     * @param statement the statement
     */
    default void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                log.error("SQL exception occurred here", e);
            }
        }
    }

    /**
     * Close.
     *
     * @param connection the connection
     */
    default void close (Connection connection) {
        if (connection != null) {
            ConnectionPool.getInstance().releaseConnection((ProxyConnection) connection);
        }
    }
}
