package by.epam.fitness.pool;


import by.epam.fitness.util.database.DataBaseInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public enum ConnectionPool {

    INSTANCE;

    private Logger log = LogManager.getLogger(ConnectionPool.class);
    private BlockingQueue<Connection> freeConnections;
    private Queue<Connection> givenAwayConnections;

    private final static int DEFAULT_POOL_SIZE = 32;

    ConnectionPool() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            freeConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
            givenAwayConnections = new ArrayDeque<>();
            for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
                try {
                    freeConnections.offer(DriverManager.getConnection(
                            DataBaseInfo.URL, DataBaseInfo.USER, DataBaseInfo.PASSWORD));
                } catch (SQLException e) {
                    log.fatal("Couldn't create connection pool");
                }
            }
        } catch (ClassNotFoundException e) {
            log.fatal("Couldn't create connection pool", e);
            throw new RuntimeException("Couldn't create connection pool", e);
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = freeConnections.take();
            givenAwayConnections.offer(connection);
        } catch (InterruptedException e) {
            log.error("Couldn't get connection from the pool");
            throw new RuntimeException("Couldn't get connection from the pool", e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        givenAwayConnections.remove(connection);
        freeConnections.offer(connection);
    }

    public void destroyPool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                ProxyConnection connection = (ProxyConnection) freeConnections.take();
                connection.reallyClose();
            } catch (InterruptedException e) {
                log.error("Couldn't destroy pool", e);
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                log.error("Couldn't deregister drivers");
            }
        });
    }

    private boolean isIntegrity() {
        return freeConnections.size() + givenAwayConnections.size() == DEFAULT_POOL_SIZE;
    }

}

