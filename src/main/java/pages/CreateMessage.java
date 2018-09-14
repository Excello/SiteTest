package pages;

import elements.ButtonElement;
import elements.InputElement;
import elements.LabelElement;
import logging.TestLogger;
import org.openqa.selenium.By;

public class CreateMessage extends AbstractPage {

    private static final By CREATE_MESSAGE_LABEL = By.linkText("Create Message");
    private static final By CREATE_BUTTON = By.cssSelector("create");
    private static final By HEADLINE_FIELD = By.id("headline");
    private static final By TEXT_FIELD = By.id("text");

    private LabelElement createMessageLabel() {
        return new LabelElement(driver, CREATE_MESSAGE_LABEL, "Create Message Label");
    }

    private ButtonElement createButton() {
        return new ButtonElement(driver, CREATE_BUTTON, "Create Button");
    }

    private InputElement inputHeadline() {
        return new InputElement(driver, HEADLINE_FIELD, "Headline Field");
    }

    private InputElement inputText() {
        return new InputElement(driver, TEXT_FIELD, "Text Field");
    }

    public void isCreateMessagePageOpened() {
        isPageOpened(createMessageLabel(), "Create Message");
    }

    private void clickCreate() {
        TestLogger.logMessage("Tap 'Create' button");

        createButton().click();
    }

    public ShowMessage createMessage(String headline, String text) {

        if (headline == null) {
            headline = "test";
        }

        if (text == null) {
            headline = "test";
        }

        TestLogger.logMessage("Filling 'Create Message' form with value headline " + headline + " and text" + text);
        inputHeadline().enterValue(headline);
        inputHeadline().assertValue(headline);
        inputText().enterValue(text);
        inputText().assertValue(text);
        clickCreate();

        ShowMessage showMessage = new ShowMessage();
        showMessage.isShowMessagePageOpened();

        return showMessage;
    }
}
