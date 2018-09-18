package component;

import core.WebDriverFactory;
import logging.TestLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class AbstractComponent {
    protected static WebDriver driver = WebDriverFactory.instance().get();

    protected static void logDebug(String message) {
        TestLogger.debug(Thread.currentThread().getStackTrace()[2].getClassName() + "." + Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + message);
    }
}
