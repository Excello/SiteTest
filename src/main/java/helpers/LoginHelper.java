package helpers;

import component.AbstractComponent;
import data.User;
import pages.LoginPage;
import pages.MainPage;
import pages.MessageList;

public class LoginHelper extends AbstractComponent {

    private MainPage mainPage;

    public LoginHelper() {
        mainPage = new MainPage();
    }


    private LoginPage openUserControllerPage() {
        mainPage.assertPageOpened();
        return mainPage.openUserControllerPage();
    }

    public MessageList signInToUserController(User user) {
        LoginPage loginPage = openUserControllerPage();
        return loginPage.signIn(user);
    }

    private LoginPage openMessageControllerPage() {
        mainPage.assertPageOpened();
        return mainPage.openMessageControllerPage();
    }

    public MessageList signInToMessageController(User user) {
        LoginPage loginPage = openMessageControllerPage();
        return loginPage.signIn(user);
    }
}
