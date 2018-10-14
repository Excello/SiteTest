package helpers;

import component.AbstractComponent;
import data.User;
import pages.LoginPage;
import pages.MainPage;

public class LoginHelper extends AbstractComponent {

    private MainPage mainPage;

    public LoginHelper() {
        mainPage = new MainPage();
    }


    private LoginPage openUserControllerPage() {
        mainPage.isMainPageOpened();
        return mainPage.openUserControllerPage();
    }

    public void signInToUserController(User user) {
        LoginPage loginPage = openUserControllerPage();
        loginPage.signIn(user);
    }

    private LoginPage openMessageControllerPage() {
        mainPage.isMainPageOpened();
        return mainPage.openMessageControllerPage();
    }

    public void signInToMessageController(User user) {
        LoginPage loginPage = openMessageControllerPage();
        loginPage.signIn(user);
    }
}
