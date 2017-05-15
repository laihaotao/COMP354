import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.Test;

/**
 * Created by ERIC_LAI on 2017-05-15.
 */
public class Log4jTest {

    final static Logger logger = LogManager.getLogger(Log4jTest.class.getName());

    @Test
    public void logHelloWorld() {
        logger.debug("hello world");
    }
}
