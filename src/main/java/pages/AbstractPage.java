package pages;

import core.WebDriverFactory;
import logging.TestLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class AbstractPage {
    private final By identifyElementLocator;
    private final String formName;
    protected WebDriver driver = WebDriverFactory.instance().get();

    public AbstractPage(By identifyElementLocator, String formName) {
        this.identifyElementLocator = identifyElementLocator;
        this.formName = formName;
    }

    public void assertPageOpened() {
        try {
            WebElement pageElement = driver.findElement(identifyElementLocator);
            Assert.assertTrue(pageElement.isDisplayed());
            TestLogger.logMessage("Element " + formName + " is displayed");
        } catch (Exception ex) {
            TestLogger.logError("Element " + formName + " is not displayed");
        }
    }
}
