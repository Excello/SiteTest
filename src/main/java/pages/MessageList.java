package pages;

import component.TableManager;
import data.Message;
import elements.ButtonElement;
import elements.LabelElement;
import elements.LinkElement;
import logging.TestLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MessageList extends AbstractPage {
    private static final By TABLE = By.cssSelector(".list");
    private static final By LABEL_MESSAGE_LIST = By.xpath("//H1[text()='Message List']");
    private static final By NEW_MESSAGE_BUTTON = By.cssSelector(".create");
    private static final By NEXT_PAGE_BUTTON = By.cssSelector(".nextLink");
    private static final By FIRST_PAGE_BUTTON = By.cssSelector(".step");
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
    private static final String EMPTY_AUTHOR = "";

    public MessageList() {
        super(LABEL_MESSAGE_LIST, "Message List Page");
    }

    public MessageList(String headline, String text) {
        super(LABEL_MESSAGE_LIST, "Message List Page");
        String headline1 = headline;
        String text1 = text;
    }

    private static TableManager tableMessages() {
        return new TableManager(TABLE);
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

    private ButtonElement firstPage() {
        return new ButtonElement(driver, FIRST_PAGE_BUTTON, "Previous Page Button");
    }

    private ButtonElement logOut() {
        return new ButtonElement(driver, LOGOUT_BUTTON, "Logout");
    }

    private ButtonElement allUsersCheckBox() {
        return new ButtonElement(driver, ALL_USERS_CHECKBOX, "All Users Checkbox");
    }

    //TODO assertMessageListOpened, методы is... должны возвращать булевый ответ и не вызовать никаких ошибок
    public void assertMessageListPageOpened() {
        assertPageOpened();
    }

    public void assertUsername(String expected) {
        userMessage().assertText("Hello " + expected + "!");
    }

    private boolean isCheckBoxSelected() {
        return allUsersCheckBox().isCheckBoxSelected();
    }

    //TOdo getViewLink или createViewLink. getViewLink - означает выполнить действие
    private LinkElement getViewLink(int iRow) {
        WebElement cell = tableMessages().getCell(iRow, ACTIONS_COL);
        return new LinkElement(VIEW_BUTTON, "View Link", cell);
    }

    //TODo as above
    private LinkElement getEditLink(int iRow) {
        WebElement cell = tableMessages().getCell(iRow, ACTIONS_COL);
        return new LinkElement(EDIT_BUTTON, "Edit Link", cell);
    }

    //TODo as above
    private LinkElement getDeleteLink(int iRow) {
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
    private int findMessageRow(Message message, String author) {
        TestLogger.logMessage("Looking for line with [headline] " + message.getHeadline() + " and [text] " + message.getText());

        TableManager.RowCondition cond = TableManager.createCondition();
        cond.addCondition(HEADLINE_COL, message.getHeadline());
        cond.addCondition(TEXT_COL, message.getText());

        if (!author.equals(EMPTY_AUTHOR)) {
            cond.addCondition(AUTHOR_COL, author);
        }

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
    public void assertMessageIsInList(Message message) {
        TestLogger.logMessage("Check that there is an [headline] " + message.getHeadline() + " and [text] " + message.getText() + " elements are in the table");

        assertMessageIsInList(message, EMPTY_AUTHOR);
    }

    public void assertMessageIsInList(Message message, String author) {
        if(!author.equals(EMPTY_AUTHOR)) {
            TestLogger.logMessage("Check that there is an [headline] " + message.getHeadline() + " and [text] " + message.getText() + " elements are in the table with correct [author] " + author);
        }

        int index = findMessageRow(message, author);

        if (index > 1) {
            TestLogger.logMessage("The element is displayed");
        } else {
            TestLogger.logError("The element is not displayed");
        }
    }

    //TODO Что метод то делает?
    public void assertMessageIsNotInList(Message message) {
        TestLogger.logMessage("Check that there is an [headline] " + message.getHeadline() + " and [text] " + message.getText() + " elements are not in the table");
        assertMessageIsNotInList(message, EMPTY_AUTHOR);
    }

    public void assertMessageIsNotInList(Message message, String author) {
        if(!author.equals(EMPTY_AUTHOR)) {
            TestLogger.logMessage("Check that there is an [headline] " + message.getHeadline() + " [text] " + message.getText() + " and [author] " + author + " are not in the table");
        }

        int index = findMessageRow(message, author);

        if (index > 1) {
            TestLogger.logError("The element is displayed");
        } else {
            TestLogger.logMessage("The element is not displayed");
        }
    }

    private void selectNextPage() {
        if (isPagingEnabled()) {
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
        //TODO Разве там нет прямой ссылки на первую страницу?
        while (firstPage().exists(0)) {
            TestLogger.logMessage("Going to previous page");
            firstPage().click();
        }
    }

    public ViewMessagePage openViewMessagePage(Message message) {
        int iRow = findMessageRow(message, EMPTY_AUTHOR);

        if (iRow < 1) {
            TestLogger.logError("Element is not displayed");
        }

        TestLogger.logMessage("Tap 'View' button");
        getViewLink(iRow).click();

        ViewMessagePage page = new ViewMessagePage();
        page.assertViewMessagePageOpened();
        return page;
    }

    public EditMessagePage openEditMessagePage(Message message) {
        int iRow = findMessageRow(message, EMPTY_AUTHOR);

        if (iRow < 1) {
            TestLogger.logError("Element is not displayed");
        }

        TestLogger.logMessage("Tap 'Edit' button");
        getEditLink(iRow).click();

        EditMessagePage page = new EditMessagePage();
        page.isEditPageOpened();
        //page.assertMessage(message);
        return page;
    }

    public MessageList deleteMessage(Message message) {
        TestLogger.logMessage("Tapping 'Delete' button for [headline] " + message.getHeadline() + " and [text] " + message.getText());

        deleteMessage(message, EMPTY_AUTHOR);

        return new MessageList();
    }

    public MessageList deleteMessage(Message message, String author) {
        if (!author.equals(EMPTY_AUTHOR)) {
            TestLogger.logMessage("Tapping 'Delete' button for [headline] " + message.getHeadline() + " and [text] " + message.getText() + " with [author] " + author);
        }

        int iRow = findMessageRow(message, author);

        if (iRow > 1) {
            TestLogger.logMessage("Tap 'Delete' button");
            getDeleteLink(iRow).click();
        } else {
            TestLogger.logError("It is impossible to tap 'Delete' button");
        }
        return new MessageList();
    }

    public void selectAllUsersCheckBox() {
        //TODO А если бокс был уже выбран?
        if(!isCheckBoxSelected()) {
            allUsersCheckBox().click();
            TestLogger.logMessage("Tap 'All Users' checkbox");
        } else {
            TestLogger.logMessage("Checkbox has already selected");
        }
    }

    public void removeAllUsersCheckBox() {
        //TODO А если бокс был уже снят?
        if(isCheckBoxSelected()) {
            allUsersCheckBox().click();
            TestLogger.logMessage("Tap 'All Users' checkbox");
        } else {
            TestLogger.logMessage("Checkbox has not already selected");
        }
    }
}


  /*private LabelElement messageListLabel() {
        return new LabelElement(driver, LABEL_MESSAGE_LIST, "Message List Title");
    }*/

  /*
    public void isMessageIsNotInList(String headline, String text) {
        TestLogger.logMessage("Check that there is no an headline element " + headline + " and Text " + text + " in the table");

        int index = findMessageRow(headline, text);

    }
*/
