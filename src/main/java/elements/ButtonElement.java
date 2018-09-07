package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ButtonElement extends BaseElement {
    public ButtonElement(WebDriver driver, By locator, String name) {
        super(driver, locator, name);
    }
}
