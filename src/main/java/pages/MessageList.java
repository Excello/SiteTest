package pages;

import component.TableManager;
import elements.ButtonElement;
import elements.LabelElement;
import elements.LinkElement;
import logging.TestLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Environment;

public class MessageList extends AbstractPage {

    private static final By TABLE = By.cssSelector(".list");
    private static final By LABEL_MESSAGE_LIST = By.xpath("//H1[text()='Message List']");
    private static final By NEW_MESSAGE_BUTTON = By.cssSelector(".create");
    private static final By NEXT_PAGE_BUTTON = By.cssSelector(".nextLink");
    private static final By PREV_PAGE_BUTTON = By.cssSelector(".prevLink");
    private static final By VIEW_BUTTON = By.linkText("View");
    private static final By EDIT_BUTTON = By.linkText("Edit");
    private static final By DELETE_BUTTON = By.linkText("Delete");
    private static final By LOGOUT_BUTTON = By.linkText("Logout");
    private static final By ALL_USERS_CHECKBOX = By.name("allUsers");
    private static final By USER_MESSAGE = By.cssSelector(".message");

    private static final int HEADLINE_COL = 2;
    private static final int TEXT_COL = 3;
    private static final int ACTIONS_COL = 1;
    private static final int AUTHOR_COL = 4;

    private static TableManager tableMessages() {
        return new TableManager(TABLE);
    }

    private LabelElement labelMessageList() {
        return new LabelElement(driver, LABEL_MESSAGE_LIST, "Message List Title");
    }

    private LabelElement userMessage() {
        return new LabelElement(driver, USER_MESSAGE, "User Message");
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

    //TODO assertMessageListOpened, методы is... должны возвращать булевый ответ и не вызовать никаких ошибок
    public void isMessageListPageOpened() {
        assertPageOpened(labelMessageList(), "Message List");
    }

    public void assertUsername(String expected) {
        userMessage().assertText(expected);
    }

    //TOdo getViewLink или createViewLink. viewLink - означает выполнить действие
    private LinkElement viewLink(int iRow) {
        WebElement cell = tableMessages().getCell(iRow, ACTIONS_COL);
        return new LinkElement(VIEW_BUTTON, "View Link", cell);
    }

    //TODo as above
    private LinkElement editLink(int iRow) {
        WebElement cell = tableMessages().getCell(iRow, ACTIONS_COL);
        return new LinkElement(EDIT_BUTTON, "Edit Link", cell);
    }


    //TODo as above
    private LinkElement deleteLink(int iRow) {
        WebElement cell = tableMessages().getCell(iRow, ACTIONS_COL);
        return new LinkElement(DELETE_BUTTON, "Delete Link", cell);
    }

    public CreateMessagePage clickNewMessageButton() {
        TestLogger.logMessage("Tap 'New Message' button");

        newMessage().click();
        CreateMessagePage createMessagePage = new CreateMessagePage();
        createMessagePage.isCreateMessagePageOpened();
        return createMessagePage;
    }

    public LoginPage clickLogOutButton() {
        TestLogger.logMessage("Tap 'Logout' button");

        logOut().click();
        LoginPage loginPage = new LoginPage();
        loginPage.isLoginPageOpened();
        return loginPage;
    }

    //TODO На вход класс Message
    private int findMessageRow(String headline, String text) {
        TestLogger.logMessage("Looking for line with [headline] " + headline + " and [text] " + text);

        TableManager.RowCondition cond = TableManager.createCondition();
        cond.addCondition(HEADLINE_COL, headline);
        cond.addCondition(TEXT_COL, text);

        selectFirstPage();
        int index;

        index = tableMessages().getIndexOfRow(cond);

        if (index > 1) {
            TestLogger.logMessage("Elements are displayed");
            return index;
        } else {
            TestLogger.logMessage("Elements are not displayed on page. Will be attempt to move to the next page");
        }

        while (isPagingEnabled()) {
            selectNextPage();
            index = tableMessages().getIndexOfRow(cond);
            if (index > 1) {
                TestLogger.logMessage("Element is displayed");
                return index;
            } else {
                TestLogger.logMessage("Element is not displayed on page. Will be attempt to move to the next page");
            }
        }
        return index;
    }

    //TODO assertMesageInlist
    public void assertMessageIsInList(String headline, String text) {
        TestLogger.logMessage("Check that there is an [headline] element in the table " + headline + " and [text] " + text);

        int index = findMessageRow(headline, text);

        if (index < 1) {
            TestLogger.logError("The element is not displayed");
        }
    }

    //TODO Что метод то делает?
/*
    public void isMessageIsNotInList(String headline, String text) {
        TestLogger.logMessage("Check that there is no an headline element " + headline + " and Text " + text + " in the table");

        int index = findMessageRow(headline, text);

    }
*/

    private void selectNextPage() {
        if (isPagingEnabled()) {
            Environment.setTimeOutForPageLoad(Environment.TIME_OUT_FOR_PAGE_LOAD);
            nextPage().click();
            TestLogger.logMessage("Next page is opened");
        } else {
            TestLogger.logError("It is impossible to switch to the next page");
        }
    }

    private boolean isPagingEnabled() {
        return nextPage().exists(0);
    }


    private void selectFirstPage() {

        Environment.setTimeOutForPageLoad(Environment.TIME_OUT_FOR_PAGE_LOAD);

        //TODO Разве там нет прямой ссылки на первую страницу?
        while (prevPage().exists(0)) {
            logDebug("Going to previous page");
            prevPage().click();
        }
    }

    public ViewMessagePage openViewMessagePage(String headline, String text) {
        int iRow = findMessageRow(headline, text);

        if (iRow < 1) {
            TestLogger.logError("Element is not displayed");
        }

        TestLogger.logMessage("Tap 'View' button");
        viewLink(iRow).click();

        ViewMessagePage page = new ViewMessagePage();
        page.assertViewMessagePageOpened();
        return page;
    }

    public EditMessagePage openEditMessagePage(String headline, String text) {
        int iRow = findMessageRow(headline, text);

        if (iRow < 1) {
            TestLogger.logError("Element is not displayed");
        }

        TestLogger.logMessage("Tap 'Edit' button");
        editLink(iRow).click();

        EditMessagePage page = new EditMessagePage();
        page.isEditPageOpened();
        page.assertMessage(headline, text);
        return page;
    }

    public MessageList deleteMessage(String headline, String text) {
        int iRow = findMessageRow(headline, text);
        deleteLink(iRow).click();
        return new MessageList();
    }

    public void selectAllUsersCheckBox() {
        TestLogger.logMessage("Tap 'All Users' checkbox");
//TODO А если бокс был уже выбран?
        allUsersCheckBox().click();
        allUsersCheckBox().isCheckBoxSelected();
    }

    public void removeAllUsersCheckBox() {
        TestLogger.logMessage("Tap 'All Users' checkbox");

        //TODO А если бокс был уже снят?
        allUsersCheckBox().click();
        allUsersCheckBox().isCheckBoxNotSelected();
    }
}
