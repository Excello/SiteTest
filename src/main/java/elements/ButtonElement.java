package elements;

import logging.TestLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class ButtonElement extends BaseElement {
    public ButtonElement(WebDriver driver, By locator, String name) {
        super(driver, locator, name);
    }

    public void isCheckBoxSelected() {
        TestLogger.logMessage("Check that checkbox " + name + " is selected");

        Assert.assertTrue(element().isSelected());
    }

    public void isCheckBoxNotSelected() {
        TestLogger.logMessage("Check that checkbox " + name + " is not selected");

        Assert.assertFalse(element().isSelected());
    }
}
