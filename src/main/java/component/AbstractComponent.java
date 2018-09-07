package component;

import logging.TestLogger;
import org.openqa.selenium.WebDriver;

public abstract class AbstractComponent {
    protected WebDriver driver;

    protected static void logDebug(String message) {
        TestLogger.debug(Thread.currentThread().getStackTrace()[2].getClassName() + "." + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + message);
    }
}
