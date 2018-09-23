package pages;

import core.WebDriverFactory;
import elements.BaseElement;
import logging.TestLogger;
import org.openqa.selenium.WebDriver;

//TODO Страница не может наследоваться от компонента
public class AbstractPage {
    private final BaseElement identifyElementLocator;
    private String formName;
    protected WebDriver driver = WebDriverFactory.instance().get();

    protected AbstractPage(BaseElement identifyElementLocator, String formName) {
        this.identifyElementLocator = identifyElementLocator;
        this.formName = formName;
    }

    protected AbstractPage(String formName) {
        this.identifyElementLocator = null;
        this.formName = formName;
    }

    //TODO assertPageOpened
    //TODO Этот метод можно сделать public и без параметров
    public void assertPageOpened() {
        try {
            identifyElementLocator.assertExists();
            TestLogger.logMessage("Element " + formName + " is displayed");
        } catch (Exception ex) {
            TestLogger.logError("Element " + formName + " is not displayed");
        }
    }
}
