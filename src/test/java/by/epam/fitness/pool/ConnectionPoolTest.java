package by.epam.fitness.pool;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ConnectionPoolTest {

    @BeforeClass
    public void initPoolTest() {
        ConnectionPool.initPool();
    }

    @Test
    public void connectionPoolTest() {
        ConnectionPool instance = ConnectionPool.getInstance();
        Assert.assertEquals(instance.size(), 10);
    }

}