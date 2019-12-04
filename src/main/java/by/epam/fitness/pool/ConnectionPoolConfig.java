package by.epam.fitness.pool;

class ConnectionPoolConfig {
//    private static Logger log = LogManager.getLogger(ConnectionPoolConfig.class);
//    private final Properties properties;
//    private String url;
//
//    /**
//     * Default constructor.
//     */
//    ConnectionPoolConfig() {
//        try {
//            ResourceBundle resourceBundle = ResourceBundle.getBundle(DataBaseInfo.DB_PROPERTIES);
//            properties = new Properties();
//            Class.forName(resourceBundle.getString(DataBaseInfo.DRIVER));
//            url = resourceBundle.getString(DataBaseInfo.URL);
//            properties.put(DataBaseInfo.USER, resourceBundle.getString(DataBaseInfo.USER));
//            properties.put(DataBaseInfo.PASSWORD, resourceBundle.getString(DataBaseInfo.PASSWORD));
//            properties.put(DataBaseInfo.USE_UNICODE, resourceBundle.getString(DataBaseInfo.USE_UNICODE));
//            properties.put(DataBaseInfo.CHARACTER_ENCODING, resourceBundle.getString(DataBaseInfo.CHARACTER_ENCODING));
//            properties.put(DataBaseInfo.AUTO_RECONNECT, resourceBundle.getString(DataBaseInfo.AUTO_RECONNECT));
//            properties.put(DataBaseInfo.CAPACITY, resourceBundle.getString(DataBaseInfo.CAPACITY));
//            properties.put(DataBaseInfo.SERVER_TIMEZONE, resourceBundle.getString(DataBaseInfo.SERVER_TIMEZONE));
//            properties.put(DataBaseInfo.USE_SSL, resourceBundle.getString(DataBaseInfo.USE_SSL));
//        } catch (ClassNotFoundException e) {
//            log.fatal("Connection pool will nowhere create. ", e);
//            throw new RuntimeException("Driver not found. ", e);
//
//        } catch (MissingResourceException e) {
//            log.fatal("Connection pool will nowhere create. ", e);
//            throw new RuntimeException("Data base configuration file not found. ", e);
//
//        }
//    }
//
//    /**
//     * `get URL.
//     *
//     * @return url
//     */
//    String getUrl() {
//        return url;
//    }
//
//    /**
//     * Get properties.
//     *
//     * @return properties
//     */
//    Properties getProperties() {
//        return properties;
//    }
//
//    /**
//     * Get pool capacity.
//     *
//     * @return pool capacity
//     */
//    int getPoolCapacity() {
//        return Integer.parseInt(properties.getProperty(DataBaseInfo.CAPACITY));
//    }

}
