package logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLogger {
    private static int testFailsCount = 0;
    private static Logger logger = LoggerFactory.getLogger("test");
    public static boolean DEBUG = false;
    public static boolean hasErrors() {
        return testFailsCount > 0;
    }

    private static void info(String message) {
        logger.info(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }

    private static void warn(String message) {
        logger.warn(message);
    }

    private static void error(String message) {
        logger.error(message);
    }

    public static void logMessage(String message) {
        TestLogger.info(message);
    }

    public static void logFail(String message) {
        TestLogger.error(message);
        testFailsCount++;
    }

    public static void logError(String message) {
        logFail(message);

        if (DEBUG) {
            System.out.print(" ");
            try {
                System.in.read();
            } catch (Exception e) {
                logFail(" " + e.getMessage());
            }
        }
        throw new RuntimeException("Test ended with critical error: " + message);
    }
}
