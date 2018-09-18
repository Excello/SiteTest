package elements;

import component.AbstractComponent;
import logging.TestLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.Environment;

import java.util.Date;

public class BaseElement extends AbstractComponent{

    protected final By locator;
    protected final WebDriver driver;
    protected final String name;
    protected final WebElement parentElement;

    public BaseElement(WebDriver driver, By locator, String name) {
        this.driver = driver;
        this.locator = locator;
        this.name = name;
        this.parentElement = null;
    }

    public BaseElement(By locator, String name, WebElement parentElement) {
        this.driver = null;
        this.locator = locator;
        this.name = name;
        this.parentElement = parentElement;
    }

    protected WebElement element() {
        WebElement result;

        if(driver == null) {
            result = parentElement.findElement(locator);
        } else {
            result = driver.findElement(locator);
        }
        return result;
    }

    public void click() {
        assertExists();
        element().click();
    }

    public void clear() {
        assertExists();
        element().clear();
    }

    public boolean exists(int timeout) {
        logDebug("Looking for element " + name);

        long start = new Date().getTime();
        boolean result = false;

        Environment.removeTimeOutForElements();

        try {
            element();
            result = true;
        } catch (Exception ex) { }

        while (!result && new Date().getTime() - start < timeout * 1000) {
            try {
                element();
                result = true;
            } catch (Exception e) { }
        }

        Environment.resetTimeOutForPageElements();

        if (result)
            logDebug("Element is displayed");
        else
            logDebug("Element is not displayed");

        return result;
    }

    public boolean exists() {
        return exists(Environment.TIME_OUT_FOR_ELEMENTS);
    }

    private void assertExists(int timeout) {
        TestLogger.debug("Verifying that the field " + name + " has been displaying for " + timeout + " seconds");

        if (!exists(timeout)) {
            TestLogger.logError("The element " + name + " is not displayed");
        } else {
            TestLogger.debug("The element " + name + " is displayed");
        }
    }

    public void assertExists() {
        assertExists(Environment.TIME_OUT_FOR_ELEMENTS);
    }


    /*private void assertNotExists(int timeout) {
        TestLogger.debug("Verifying that the element " + name + " has not been displaying for " + timeout + " seconds");

        if (exists(timeout)) {
            TestLogger.logError("The element " + name + " is not displayed");
        } else {
            TestLogger.debug("The element " + name + " is displayed");
        }
    }

    public void assertNotExists() {
        assertNotExists(Environment.TIME_OUT_FOR_ELEMENTS);
    }
*/
    public void assertText(String expected) {
        String actual = element().getText();
        Assert.assertEquals(actual, expected);

    }

    protected String getName() {
        return name;
    }
}
