package component;

import core.WebDriverFactory;
import logging.TestLogger;
import org.openqa.selenium.WebDriver;

public abstract class AbstractComponent {

    protected WebDriver driver = WebDriverFactory.instance().get();
    //protected static WebDriver driver = DriverManagerFactory.getManager(DriverType.CHROME);

    protected static void logDebug(String message) {
        TestLogger.debug(Thread.currentThread().getStackTrace()[2].getClassName() + "." + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + message);
    }
}
