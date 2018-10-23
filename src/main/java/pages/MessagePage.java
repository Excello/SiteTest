package pages;


import data.Message;
import elements.ButtonElement;
import elements.InputElement;
import logging.TestLogger;
import org.openqa.selenium.By;

public class MessagePage extends AbstractPage {
    private static final By CREATE_BUTTON = By.cssSelector(".save");
    private static final By HEADLINE_FIELD = By.id("headline");
    private static final By TEXT_FIELD = By.id("text");
    private static final By MESSAGE_LIST_BUTTON = By.cssSelector(".list");

    public MessagePage(By identifyElementLocator, String formName) {
        super(identifyElementLocator, formName);
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

    private ButtonElement messageList() {
        return new ButtonElement(driver, MESSAGE_LIST_BUTTON, "Message List Button");
    }

    private void clickCreate() {
        TestLogger.logMessage("Tap 'Create' button");

        createButton().click();
    }

    public MessageList clickMessageList() {
        TestLogger.logMessage("Tap 'Message List' button");

        messageList().click();
        MessageList messageList = new MessageList();
        messageList.assertPageOpened();
        return messageList;
    }

    public void assertMessageIsCorrect(Message message) {
        inputHeadline().assertValue(message.getHeadline());
        inputText().assertValue(message.getText());
    }

    //TODO В целом это конечно можно делать, но в данном случае это избыточно
    //TODO По хорошему при вводе любых значений в поля их надо сначала чистить
    //TODO Твой InputElement к слову именно так и делает
    public ViewMessagePage createMessage(Message message) {
        TestLogger.logMessage("Filling 'Message' form with value [headline] " + message.getHeadline() + " and [text] " + message.getText());

        inputHeadline().enterValue(message.getHeadline());
        inputText().enterValue(message.getText());
        clickCreate();

        ViewMessagePage viewMessagePage = new ViewMessagePage();
        viewMessagePage.assertPageOpened();

        return viewMessagePage;
    }

    public void fillFieldsWithValues(Message message) {
        TestLogger.logMessage("Filling 'Create Message' form with value [headline] " + message.getHeadline() + " and [text] " + message.getText());

        inputHeadline().enterValue(message.getHeadline());
        inputText().enterValue(message.getText());
    }
}
