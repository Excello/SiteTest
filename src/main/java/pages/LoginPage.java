package pages;

import data.User;
import elements.ButtonElement;
import elements.InputElement;
import logging.TestLogger;
import org.openqa.selenium.By;

public class LoginPage extends AbstractPage {
    private static final By LOGIN_FIELD = By.id("login");
    private static final By PASSWORD_FIELD = By.id("password");
    private static final By SIGN_IN_BUTTON = By.cssSelector(".save");
    private static String loggedUser = null;

    public LoginPage() {
        super(LOGIN_FIELD, "Login Page");
    }

    private InputElement inputLogin() {
        return new InputElement(driver, LOGIN_FIELD, "login");
    }

    private InputElement inputPassword() {
        return new InputElement(driver, PASSWORD_FIELD, "password");
    }

    private ButtonElement signInButton() {
        return new ButtonElement(driver, SIGN_IN_BUTTON, "save");
    }

    public void isLoginPageOpened() {
        assertPageOpened();
    }

    public MessageList signIn(User user) {
        TestLogger.logMessage("Logging in to application: " + user.getUserName() + "\\" + user.getPassword());

        driver.switchTo().defaultContent();
        inputLogin().enterValue(user.getUserName());
        inputPassword().enterValue(user.getPassword());
        signInButton().click();

        MessageList pageMessageList = new MessageList();
        pageMessageList.assertMessageListPageOpened();

        loggedUser = user.getName();

        return new MessageList();
    }
}
