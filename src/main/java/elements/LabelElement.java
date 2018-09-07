package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LabelElement extends BaseElement {
    public LabelElement(WebDriver driver, By locator, String name) {
        super(driver, locator, name);
    }
}
