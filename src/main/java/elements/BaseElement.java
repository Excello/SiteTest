package elements;

import component.AbstractComponent;
import logging.TestLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    public WebElement element() {
        WebElement result;

        if(driver == null) {
            result = parentElement.findElement(locator);
        } else {
            result = driver.findElement(locator);
        }
        return result;
    }

    public void click() {
        assertExist();
        element().click();
    }

    public boolean exist() {

        try {
            element();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean verifyExist() {
        TestLogger.debug("Verifying that the field " + name + " is existed");

        if (exist()) {
            TestLogger.debug("The field " + name + " is existed");
            return true;
        } else {
            TestLogger.debug("The field " + name + " is not existed");
            return false;
        }
    }


    public void assertExist() {
        TestLogger.debug("Verifying that the element " + name + " is displayed");

        if (!exist()) {
            TestLogger.logError("The element " + name + " is not displayed");
        } else {
            TestLogger.debug("The element " + name + " is displayed");
        }
    }



    protected String getName() {
        return name;
    }
}
