package by.epam.fitness.util;

import by.epam.fitness.pool.ConnectionPool;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * The type Property loader.
 */
public class PropertyLoader {

    private PropertyLoader() {}

    /**
     * Load property properties.
     *
     * @param propertyPath the property path
     * @return the properties
     */
    public static Properties loadProperty(String propertyPath) {
        Properties properties = new Properties();
        try {
            properties.load(Objects.requireNonNull(ConnectionPool.class.getClassLoader().getResourceAsStream(propertyPath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }
}
