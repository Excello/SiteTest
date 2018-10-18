package pages;

import elements.LinkElement;
import logging.TestLogger;
import org.openqa.selenium.By;

public class MainPage extends AbstractPage {
    private static final By USER_CONTROLLER_LINK = By.linkText("qulixteachingsite.UserController");
    private static final By MESSAGE_CONTROLLER_LINK = By.linkText("qulixteachingsite.MessageController");

    public MainPage() {
        super(USER_CONTROLLER_LINK, "Home Page");
    }

    private LinkElement linkUserControllerPage() {
        return new LinkElement(driver, USER_CONTROLLER_LINK, "qulixteachingsite.UserController");
    }

    private LinkElement linkMessageControllerPage() {
        return new LinkElement(driver, MESSAGE_CONTROLLER_LINK, "qulixteachingsite.MessageController");
    }

    public LoginPage openUserControllerPage() {
        TestLogger.logMessage("Open qulixteachingsite.UserController link");
        linkUserControllerPage().click();
        LoginPage loginPage = new LoginPage();
        loginPage.isLoginPageOpened();
        return loginPage;
    }

    public LoginPage openMessageControllerPage() {
        TestLogger.logMessage("Open qulixteachingsite.MessageController link");
        linkMessageControllerPage().click();
        LoginPage loginPage = new LoginPage();
        loginPage.isLoginPageOpened();
        return loginPage;
    }

    //TODO Зачем? Хватит и assertPageOpened
    public void isMainPageOpened() {
        assertPageOpened();
    }
}