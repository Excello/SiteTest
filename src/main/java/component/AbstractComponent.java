package component;

import org.openqa.selenium.WebDriver;

public abstract class AbstractComponent {
    protected WebDriver driver = WebDriverFactory.instance().get();
}
