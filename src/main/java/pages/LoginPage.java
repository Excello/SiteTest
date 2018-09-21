package pages;

import elements.ButtonElement;
import elements.InputElement;
import logging.TestLogger;
import org.openqa.selenium.By;

public class LoginPage extends AbstractPage {

    private static final By LOGIN_FIELD = By.id("login");
    private static final By PASSWORD_FIELD = By.id("password");
    private static final By SIGN_IN_BUTTON = By.cssSelector(".save");
    private static String loggedUser = null;

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
        assertPageOpened(inputLogin(), "Login");
    }

    public void signIn(String userName, String userPassword) {
        TestLogger.logMessage("Logging in to application: " + userName + "\\" + userPassword);

        driver.switchTo().defaultContent();
        inputLogin().enterValue(userName);
        inputPassword().enterValue(userPassword);
        signInButton().click();

        MessageList pageMessageList = new MessageList();
        pageMessageList.isMessageListPageOpened();

        loggedUser = userName;
    }
}
