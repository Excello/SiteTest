package tests;

import data.Message;
import data.User;
import helpers.LoginHelper;
import org.testng.annotations.Test;
import pages.MessageList;
import pages.MessagePage;

public class Test5 extends AbstractTest {
    @Test(description = "Case 5. Creation of message without saving")
    //@Parameters({"Message"})
    public void test() {
        LoginHelper loginHelper = new LoginHelper();

        loginHelper.signInToUserController(User.USER_ADMIN);

        Message message = Message.createRandom();

        MessageList messageListPage = new MessageList();

        messageListPage.assertPageOpened();

        MessagePage createMessagePage = messageListPage.clickNewMessageButton();

        createMessagePage.fillFieldsWithValues(message);

        messageListPage = createMessagePage.clickMessageList();

        messageListPage.assertMessageIsNotInList(message);
    }
}
