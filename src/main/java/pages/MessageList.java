package pages;

import component.TableManager;
import elements.ButtonElement;
import elements.LabelElement;
import elements.LinkElement;
import logging.TestLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Environment;

import static org.testng.Assert.assertEquals;

public class MessageList extends AbstractPage {

    private static final By TABLE = By.cssSelector(".list");
    private static final By LABEL_MESSAGE_LIST = By.cssSelector(".message");
    private static final By NEW_MESSAGE_BUTTON = By.cssSelector(".create");
    private static final By NEXT_PAGE_BUTTON = By.cssSelector(".nextLink");
    private static final By PREV_PAGE_BUTTON = By.cssSelector(".prevLink");
    private static final By VIEW_BUTTON = By.linkText("View");
    private static final By EDIT_BUTTON = By.linkText("Edit");
    private static final By DELETE_BUTTON = By.linkText("Delete");
    private static final By LOGOUT_BUTTON = By.linkText("Logout");
    private static final By ALL_USERS_CHECKBOX = By.name("allUsers");

    private static final int _headlineCol = 2;
    private static final int _textCol = 3;
    private static final int actionsCol = 1;
    private static final int authorCol = 4;

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

    private ButtonElement logOut() {
        return new ButtonElement(driver, LOGOUT_BUTTON, "Logout");
    }

    private ButtonElement allUsersCheckBox() {
        return new ButtonElement(driver, ALL_USERS_CHECKBOX, "All Users Checkbox");
    }

    public void isMessageListPageOpened() {
        isPageOpened(labelMessageList(), "Message List");
    }

    public void assertUsername(String expected) {
        isPageOpened(labelMessageList(), "Message List");
        labelMessageList().assertText(expected);
    }

    private LinkElement viewLink(int iRow) {
        WebElement cell = tableMessages().getCell(iRow, actionsCol);
        return new LinkElement(VIEW_BUTTON, "View Link", cell);
    }

    private LinkElement editLink(int iRow) {
        WebElement cell = tableMessages().getCell(iRow, actionsCol);
        return new LinkElement(EDIT_BUTTON, "Edit Link", cell);
    }

    private LinkElement deleteLink(int iRow) {
        WebElement cell = tableMessages().getCell(iRow, actionsCol);
        return new LinkElement(DELETE_BUTTON, "Delete Link", cell);
    }

    public CreateMessage clickNewMessageButton() {
        TestLogger.logMessage("Tap 'New Message' button");

        newMessage().click();
        CreateMessage createMessage = new CreateMessage();
        createMessage.isCreateMessagePageOpened();
        return createMessage;
    }

    public LoginPage clickLogOutButton() {
        TestLogger.logMessage("Tap 'Logout' button");

        logOut().click();
        LoginPage loginPage = new LoginPage();
        loginPage.isLoginPageOpened();
        return loginPage;
    }

    private int findMessageRow(String headline, String text) {
        TestLogger.logMessage("Looking for line with headline " + headline + " and text" + text);

        TableManager.RowCondition cond = TableManager.createCondition();
        cond.addCondition(_headlineCol, headline);
        cond.addCondition(_textCol, text);

        selectFirstPage();
        int index;

        index = tableMessages().getIndexOfRow(cond);

        if(index > 1){
            TestLogger.logMessage("Element is displayed");
            return index;
        }else{
            TestLogger.logMessage("Element is not displayed on page. Will be attempt to move to the next page");
        }

        while (isPagingEnabled()){
            selectNextPage();
            index = tableMessages().getIndexOfRow(cond);
            if(index > 1){
                TestLogger.logMessage("Element is displayed");
                return index;
            }else{
                TestLogger.logMessage("Element is not displayed on page. Will be attempt to move to the next page");
            }
        }
        return index;
    }

    public void isMessageIsInList(String headline, String text) {
        TestLogger.logMessage("Check that there is an headline element in the table " + headline + " and Text " + text);

        int index = findMessageRow(headline, text);

        if(index < 1) {
            TestLogger.logError("The element is not displayed");
        }
    }

    public void isMessageIsNotInList(String headline, String text) {
        TestLogger.logMessage("Check that there is no an headline element " + headline + " and Text " + text + " in the table");

        int index = findMessageRow(headline, text);

    }

    private void selectNextPage() {
        if(isPagingEnabled()){
            Environment.setTimeOutForPageLoad(Environment.TIME_OUT_FOR_PAGE_LOAD);
            nextPage().click();
            TestLogger.logMessage("Next page is opened");
        }else{
            TestLogger.logError("It is impossible to switch to the next page");
        }
    }

    private boolean isPagingEnabled() {
        return nextPage().exists(0);
    }

    private void selectFirstPage(){

        Environment.setTimeOutForPageLoad(Environment.TIME_OUT_FOR_PAGE_LOAD);

        while(prevPage().exists(0)){
            logDebug("Going to previous page");
            prevPage().click();
        }
    }

    public ShowMessage viewMessage(String headline, String text)
    {
        int iRow = findMessageRow(headline, text);

        if(iRow < 1)
        {
            TestLogger.logError("Element is not displayed");
        }

        TestLogger.logMessage("Tapping 'View' button");
        viewLink(iRow).click();

        ShowMessage page = new ShowMessage();
        page.isShowMessagePageOpened();
        return page;
    }

    public EditMessage editMessage(String headline, String text) {
        int iRow = findMessageRow(headline, text);

        if (iRow < 1) {
            TestLogger.logError("Element is not displayed");
        }

        TestLogger.logMessage("Tapping 'Edit' button");
        editLink(iRow).click();

        EditMessage page = new EditMessage();
        page.isEditPageOpened();
        return page;
    }

    public MessageList deleteMessage(String headline, String text) {
        int iRow = findMessageRow(headline, text);
        deleteLink(iRow).click();
        return new MessageList();
    }

    public void selectAllUsersCheckBox() {
        TestLogger.logMessage("Tap 'All Users' checkbox");

        allUsersCheckBox().click();
        allUsersCheckBox().isCheckBoxSelected();
    }
}
