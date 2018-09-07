package pages;

import component.TableManager;
import elements.ButtonElement;
import elements.LabelElement;
import elements.LinkElement;
import logging.TestLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MessageList extends AbstractPage {

    private static final By TABLE = By.cssSelector(".list");
    private static final By LABEL_MESSAGE_LIST = By.cssSelector(".message");
    private static final By NEW_MESSAGE_BUTTON = By.cssSelector(".create");
    private static final By NEXT_PAGE_BUTTON = By.cssSelector(".nextLink");
    private static final By PREV_PAGE_BUTTON = By.cssSelector(".prevLink");
    private static final By VIEW_BUTTON = By.linkText("View");
    private static final By EDIT_BUTTON = By.linkText("Edit");
    private static final By DELETE_BUTTON = By.linkText("Delete");

    private static final int _headlineCol = 2;
    private static final int _textCol = 3;
    private static final int actionsCol = 1;

    private static TableManager tableMessages(){
        return new TableManager(TABLE);
    }

    private LabelElement labelMessageList() {
        return new LabelElement(driver, LABEL_MESSAGE_LIST, "Message List Title");
    }

    private ButtonElement newMessage() {
        return new ButtonElement(driver, NEW_MESSAGE_BUTTON, "New Message Button");
    }

    private ButtonElement nextPage() {
        return new ButtonElement(driver, NEXT_PAGE_BUTTON, "Next Page Button");
    }

    private ButtonElement prevPage() {
        return new ButtonElement(driver, PREV_PAGE_BUTTON, "Previous Page Button");
    }

    public void isPageOpened() {
        isPageOpened(labelMessageList(), "Message List");
    }

    private LinkElement viewLink(int iRow) {
        WebElement cell = tableMessages().getCell(iRow, actionsCol);
        return new LinkElement(VIEW_BUTTON, "View Link", cell);
    }

    private LinkElement editLink(int iRow) {
        WebElement cell = tableMessages().getCell(iRow, actionsCol);
        return new LinkElement(EDIT_BUTTON, "Edit Link", cell);
    }

    private LinkElement DeleteLink(int iRow) {
        WebElement cell = tableMessages().getCell(iRow, actionsCol);
        return new LinkElement(DELETE_BUTTON, "Delete Link", cell);
    }

    public CreateMessage clickNewMessageButton() {
        TestLogger.logMessage("Tap 'New Message' button");

        newMessage().click();
        CreateMessage createMessage = new CreateMessage();
        createMessage.isPageOpened();
        return createMessage;
    }

    private int findMessageRow(String headline, String text) {
        TestLogger.logMessage("Looking for line with headline " + headline + " and text" + text);

        TableManager.RowCondition cond = TableManager.createCondition();
        cond.addCondition(_headlineCol, headline);
        cond.addCondition(_textCol, text);

        selectFirstPage();
        int index;

        index = tableMessages().getIndexOfRow(cond);

        if(index>1){
            TestLogger.logMessage("Element is displayed");
            return index;
        }else{
            TestLogger.logMessage("Element is not displayed on page. Will be attempt to move to the next page");
        }

        while (isPagingEnabled()){
            selectNextPage();
            index = tableMessages().getIndexOfRow(cond);
            if(index>1){
                TestLogger.logMessage("Element is displayed");
                return index;
            }else{
                TestLogger.logMessage("Element is not displayed on page. Will be attempt to move to the next page");
            }
        }
        return index;
    }

    public void isMessageIsInList(String headline, String text) {
        TestLogger.logMessage("Check that there is an headline element in the table " + headline + " and Text " +text);

        int index = findMessageRow(headline, text);
    }

    private void selectNextPage() {

    }

    private boolean isPagingEnabled() {
        return nextPage().exist();
    }

    private void selectFirstPage(){
        while(prevPage().exist()){
            logDebug("Going to previous page");
            prevPage().click();
        }
    }

    public ShowMessage viewMessage(String headline, String text)
    {
        int iRow = findMessageRow(headline, text);

        if(iRow<1)
        {
            TestLogger.logError("Element is displayed");
        }

        TestLogger.logMessage("Tapping 'View' button");
        viewLink(iRow).click();

        ShowMessage page = new ShowMessage();
        page.isPageOpened();
        return page;
    }
}
