package myTest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by jsflz
 */
public class TestLog {

    private static final Logger logger = LogManager.getLogger(TestLog.class);

    public static void testLog1() {
        logger.info("info测试......");
        logger.error("error测试......");
    }

    public static void main(String[] args) {
        TestLog.testLog1();


        /**
         13:50:43.318 [main] INFO myTest.TestLog - info测试......
         13:50:43.322 [main] ERROR myTest.TestLog - error测试......
         */
    }
}
