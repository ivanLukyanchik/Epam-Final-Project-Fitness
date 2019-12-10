package by.epam.fitness.pool;


import by.epam.fitness.util.PropertyLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * The type Connection pool.
 */
public class ConnectionPool {
    private static Logger logger = LogManager.getLogger(ConnectionPool.class);

    private static final int DEFAULT_NUMBER_OF_CONNECTION = 5;
    private static final String PROPERTY_PATH = "db/mysql.properties";
    private static final String URL_PROPERTY_KEY = "url";
    private static final String NUMBER_OF_CONNECTIONS_KEY = "number.of.connections";

    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private static ConnectionPool instance;

    private int numberOfConnections;

    private BlockingQueue<ProxyConnection> awaitingConnections;
    private List<ProxyConnection> occupiedConnections = new ArrayList<>();
    private Driver driver;


    private ConnectionPool() {
        try {
            Properties property = PropertyLoader.loadProperty(PROPERTY_PATH);
            try {
                numberOfConnections = Integer.parseInt(property.getProperty(NUMBER_OF_CONNECTIONS_KEY));
            } catch (NumberFormatException e) {
                numberOfConnections = DEFAULT_NUMBER_OF_CONNECTION;
                logger.warn("Incorrect number of connections, set default = {}", numberOfConnections);
            }

            this.awaitingConnections = new ArrayBlockingQueue<>(numberOfConnections);

            driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
            for (int i = 0; i < numberOfConnections; i++) {
                ProxyConnection connection = new ProxyConnection(DriverManager.getConnection(property.getProperty(URL_PROPERTY_KEY), property));
                awaitingConnections.offer(connection);
            }

        } catch (SQLException e ) {
            logger.fatal("Missing or incorrect db configuration file.", e);
            throw new RuntimeException(e);
        }

    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ConnectionPool getInstance() {
        if (!isCreated.get()) {
            initPool();
            isCreated.set(true);
            logger.debug("Connection pool created, number of connections - {}", instance.numberOfConnections);
        }
        return instance;
    }

    /**
     * Init pool.
     */
    public static void initPool() {
        if (!isCreated.get()) {
            instance = new ConnectionPool();
        }
    }

    /**
     * Take connection connection.
     *
     * @return the connection
     */
    public Connection takeConnection() {
        ProxyConnection connection = null;
        try {
            connection = awaitingConnections.take();
            occupiedConnections.add(connection);
        } catch (InterruptedException e) {
            logger.error("In ConnectionPoll takeConnection interrupted.");
        }
        return connection;
    }

    /**
     * Size int.
     *
     * @return the int
     */
    public int size() {
        return instance.numberOfConnections;
    }

    /**
     * Release connection.
     *
     * @param connection the connection
     */
    public void releaseConnection(ProxyConnection connection) {
        awaitingConnections.offer(connection);
    }

    /**
     * Close all connections.
     */
    public void closeAllConnections() {
        for (int i = 0; i < numberOfConnections; i++) {
            try {
                ProxyConnection connection = awaitingConnections.take();
                connection.reallyClose();
            } catch (InterruptedException e) {
                logger.error(e);
            }
        }

        try {
            DriverManager.deregisterDriver(driver);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

}

