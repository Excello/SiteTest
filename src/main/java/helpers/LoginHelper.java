package helpers;

import pages.AbstractPage;
import pages.LoginPage;
import pages.MainPage;

public class LoginHelper extends AbstractPage {

    private MainPage mainPage;

    public LoginHelper() {
        mainPage = new MainPage();
    }


    private LoginPage openUserControllerPage() {
        mainPage.isMainPageOpened();
        return mainPage.openUserControllerPage();
    }

    public void signInToUserController(String userName, String password) {
        LoginPage loginPage = openUserControllerPage();
        loginPage.signIn(userName, password);
    }

    private LoginPage openMessageControllerPage() {
        mainPage.isMainPageOpened();
        return mainPage.openMessageControllerPage();
    }

    public void signInToMessageController(String userName, String password) {
        LoginPage loginPage = openMessageControllerPage();
        loginPage.signIn(userName, password);
    }

    public void signInAnotherUser(String userName, String password) {
        LoginPage loginPage = new LoginPage();
        loginPage.signIn(userName, password);
    }
}
