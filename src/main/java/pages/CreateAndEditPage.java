package pages;


import data.Message;
import elements.BaseElement;
import elements.ButtonElement;
import elements.InputElement;
import logging.TestLogger;
import org.openqa.selenium.By;
import utils.Environment;

public class CreateAndEditPage extends AbstractPage {
    private static final By CREATE_BUTTON = By.cssSelector(".save");
    private static final By HEADLINE_FIELD = By.id("headline");
    private static final By TEXT_FIELD = By.id("text");
    private static final By MESSAGE_LIST_BUTTON = By.cssSelector(".list");

    public CreateAndEditPage(By identifyElementLocator, String formName) {
        super(identifyElementLocator, formName);
    }

    private ButtonElement createButton() {
        return new ButtonElement(driver, CREATE_BUTTON, "Create Button");
    }

    protected InputElement inputHeadline() {
        return new InputElement(driver, HEADLINE_FIELD, "Headline Field");
    }

    protected InputElement inputText() {
        return new InputElement(driver, TEXT_FIELD, "Text Field");
    }

    private ButtonElement messageList() {
        return new ButtonElement(driver, MESSAGE_LIST_BUTTON, "Message List Button");
    }

    private void clickCreate() {
        TestLogger.logMessage("Tap 'Create' button");

        createButton().click();
    }

    private MessageList clickMessageList() {
        TestLogger.logMessage("Tap 'Message List' button");

        messageList().click();
        MessageList messageList = new MessageList();
        messageList.assertMessageListPageOpened();
        return messageList;
    }

    public ViewMessagePage createMessage(String headline, String text) {

        if (headline == null) {
            headline = Message.createRandom();
        }

        if (text == null) {
            headline = Environment.generateUniqueString();
        }

        TestLogger.logMessage("Filling 'Create Message' form with value [headline] " + headline + " and [text] " + text);
        inputHeadline().enterValue(headline);
        inputHeadline().assertValue(headline);
        inputText().enterValue(text);
        inputText().assertValue(text);
        clickCreate();

        ViewMessagePage viewMessagePage = new ViewMessagePage();
        viewMessagePage.assertViewMessagePageOpened();

        return viewMessagePage;
    }

    public MessageList createMessageWithoutSaving(String headline, String text) {
        if (headline == null) {
            headline = "test";
        }

        if (text == null) {
            headline = "test";
        }

        TestLogger.logMessage("Filling 'Create Message' form with value [headline] " + headline + " and [text] " + text);
        inputHeadline().enterValue(headline);
        inputHeadline().assertValue(headline);
        inputText().enterValue(text);
        inputText().assertValue(text);
        clickMessageList();
        return new MessageList();
    }
}
