package elements;

import logging.TestLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InputElement extends BaseElement {
    public InputElement(WebDriver driver, By locator, String name) {
        super(driver, locator, name);
    }

    public void enterValue(String value) {
        TestLogger.logMessage("Filling input field " + name + " value " + value);

        element().clear();
        element().sendKeys(value);

        TestLogger.debug("The field " + name + " was filled");
    }

    public void assertValue(String expected) {
        TestLogger.logMessage("Check that the input field "+ name + " has value" + expected);

        String actual = getValue();
        if(actual.equalsIgnoreCase(expected)){
            TestLogger.logMessage("The input field has the expected value: " +actual);
        } else{
            TestLogger.logError("The input field has the value: " + actual +", which is not match with expected( " + expected + ")");
        }
    }

    public String getValue() {
        TestLogger.debug("Retrieving the value of the input field " + getName());
        return element().getAttribute("value");
    }

}
