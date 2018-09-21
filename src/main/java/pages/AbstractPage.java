package pages;

import component.AbstractComponent;
import elements.BaseElement;
import logging.TestLogger;

public class AbstractPage extends AbstractComponent {

    protected void isPageOpened(BaseElement element, String formName) {
        try {
            element.assertExists();
            TestLogger.logMessage("Element " + formName + " is displayed");
        } catch (Exception ex) {
            TestLogger.logError("Element " + formName + " is not displayed");
        }
    }
}
