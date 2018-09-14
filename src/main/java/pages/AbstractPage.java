package pages;

import component.AbstractComponent;
import elements.BaseElement;
import logging.TestLogger;

public class AbstractPage extends AbstractComponent{

    protected void isPageOpened(BaseElement element, String formName) {
        try {
            element.verifyExist();
            TestLogger.logMessage("Element " + formName + " was displayed");
        } catch (Exception ex) {
            TestLogger.logError("Element " + formName + " was not displayed");
        }
    }
}
