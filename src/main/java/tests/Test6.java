package tests;

import data.Message;
import data.User;
import helpers.LoginHelper;
import helpers.MessageListHelper;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test6 extends AbstractTest {
    @Test(description = "Case 6. Create 2 messages")
    @Parameters({"Message", "NewMessage"})
    public void test(Message message, Message newMessage) {
        MessageListHelper messageListHelper = new MessageListHelper();
        LoginHelper loginHelper = new LoginHelper();

        loginHelper.signInToUserController(User.USER_ADMIN);

        messageListHelper.createMessage(message);

        messageListHelper.createMessage(newMessage);
    }
}
