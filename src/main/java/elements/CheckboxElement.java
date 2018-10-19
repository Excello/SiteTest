package elements;

import logging.TestLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckboxElement extends BaseElement {
    public CheckboxElement(WebDriver driver, By locator, String name) {
        super(driver, locator, name);
    }

    public boolean isCheckBoxSelected() {
        TestLogger.logMessage("Check that checkbox " + name + " is selected");
        return element().isSelected();
    }
}
