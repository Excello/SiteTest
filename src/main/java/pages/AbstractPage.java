package pages;

import component.AbstractComponent;
import elements.BaseElement;
import logging.TestLogger;

//TODO Страница не может наследоваться от компонента
public class AbstractPage extends AbstractComponent {

    //TODO assertPageOpened
    //TODO Этот метод можно сделать public и без параметров
    protected void isPageOpened(BaseElement element, String formName) {
        try {
            element.assertExists();
            TestLogger.logMessage("Element " + formName + " is displayed");
        } catch (Exception ex) {
            TestLogger.logError("Element " + formName + " is not displayed");
        }
    }
}
