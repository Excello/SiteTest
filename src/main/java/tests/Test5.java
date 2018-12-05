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

        Message message = Message.createRandom();

        MessageList messageList = loginHelper.signInToUserController(User.USER_ADMIN);

        messageList.assertPageOpened();

        MessagePage createMessagePage = messageList.clickNewMessageButton();

        createMessagePage.fillFieldsWithValues(message);

        messageList = createMessagePage.clickMessageList();

        messageList.assertMessageIsNotInList(message);
    }
}
