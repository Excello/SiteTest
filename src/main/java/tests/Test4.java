package tests;

import data.Message;
import data.User;
import helpers.LoginHelper;
import helpers.MessageListHelper;
import org.testng.annotations.Test;
import pages.MessageList;

public class Test4 extends AbstractTest {
    @Test(description = "Case 4. Delete message")
    //@Parameters({"Message"})
    public void test() {
        LoginHelper loginHelper = new LoginHelper();

        Message message = Message.createRandom();

        MessageList messageList = loginHelper.signInToUserController(User.USER_ADMIN);

        MessageListHelper.createNewMessage(message, messageList);

        MessageListHelper.deleteMessage(message, messageList);
    }
}
