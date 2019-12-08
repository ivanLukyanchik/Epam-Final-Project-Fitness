package by.epam.fitness.util;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Properties;

public class PropertyLoaderTest {
    private static Properties expectedProperties;
    private static final String TEST_PROPERTY_PATH = "property/test.properties";

    static {
        expectedProperties = new Properties();
        expectedProperties.put("test.key1", "text-value1");
        expectedProperties.put("test.key2", "text-value2");
        expectedProperties.put("test.key3", "text-value3");
    }

    @Test
    public void testLoadProperty() {
        Properties actualProperties = PropertyLoader.loadProperty(TEST_PROPERTY_PATH);
        Assert.assertEquals(actualProperties, expectedProperties);
    }

}