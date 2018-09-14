package pages;

import elements.BaseElement;
import elements.LinkElement;
import logging.TestLogger;
import org.openqa.selenium.By;

public class MainPage extends AbstractPage {
    private static final By USER_CONTROLLER_LINK = By.linkText("qulixteachingsite.UserController");
    private static final By MESSAGE_CONTROLLER_LINK = By.linkText("qulixteachingsite.MessageController");
    private static final By DIALOG_FORM = By.cssSelector(".controller");

    private BaseElement controller() {
        return new BaseElement(driver, DIALOG_FORM, ".controller");
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

    public void isMainPageOpened() {
        isPageOpened(linkUserControllerPage(), "Home Page");
    }
}