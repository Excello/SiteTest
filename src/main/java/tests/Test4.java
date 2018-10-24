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
        MessageListHelper messageListHelper = new MessageListHelper();
        LoginHelper loginHelper = new LoginHelper();

        loginHelper.signInToUserController(User.USER_ADMIN);

        Message message = Message.createRandom();

        MessageList messageList = new MessageList();

        messageListHelper.createNewMessage(message, messageList);

        messageListHelper.deleteMessage(message);
    }
}
