package pages;

import component.TableManager;
import elements.ButtonElement;
import elements.LabelElement;
import logging.TestLogger;
import org.openqa.selenium.By;

public class ShowMessage extends AbstractPage{

    private static final By SHOW_MESSAGE_LABEL = By.name("Show Message");
    private static final By MESSAGE_LIST_BUTTON = By.cssSelector(".list");
    private static final By TABLE = By.cssSelector(".table");
    private static final By NEW_MESSAGE_BUTTON = By.cssSelector(".create");

    private static final int fieldNameCol = 1;
    private static final int fieldValueCol = 2;

    private ButtonElement messageList() {
        return new ButtonElement(driver, MESSAGE_LIST_BUTTON, "Message List");
    }

    private LabelElement showMessage() {
        return new LabelElement(driver, SHOW_MESSAGE_LABEL, "Show Message");
    }

    private ButtonElement newMessage() {
        return new ButtonElement(driver, NEW_MESSAGE_BUTTON, "New Message");
    }

    public void isShowMessagePageOpened() {
        isPageOpened(showMessage(), "Show Message");
    }

    public MessageList clickMessageList() {
        TestLogger.logMessage("Tap 'Message List' button");

        messageList().click();
        MessageList messageList = new MessageList();
        messageList.isMessageListPageOpened();
        return messageList;
    }

    private static TableManager tableOfFields(){
        return new TableManager(TABLE);
    }

    public void verifyHeadlineValue(String expected)
    {
        verifyFieldValue("Headline", expected);
    }

    public void verifyTextValue(String expected)
    {
        verifyFieldValue("Text", expected);
    }

    private void verifyFieldValue(String fieldName, String expected)
    {
        TestLogger.logMessage("Check that field " + fieldName + " has value " + expected);
        String value = getFieldValue(fieldName);

        if(value.equalsIgnoreCase(expected)){
            TestLogger.logMessage("Field \"" + fieldName +  "\" has expected value: " + expected);
        } else{
            TestLogger.logError("Field \"" + fieldName + "\" has value:" + value +", which is not expected( " + expected + ")");
        }
    }

    private String getFieldValue(String fieldName)
    {
        int iRow = tableOfFields().findRowIndexWithCellText(fieldNameCol, fieldName);

        return tableOfFields().getCellText(iRow, fieldValueCol);
    }

    public CreateMessage clickNewMessage() {
        TestLogger.logMessage("Tap 'Create Message' button");

        newMessage().click();
        CreateMessage createMessage = new CreateMessage();
        createMessage.isCreateMessagePageOpened();
        return createMessage;
    }
}
