package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkElement extends BaseElement {
    public LinkElement(WebDriver driver, By locator, String name) {
        super(driver, locator, name);
    }

    public LinkElement(By locator, String name, WebElement parentElement) {
        super(locator, name, parentElement);
    }
}
