package tests;

import data.Message;
import data.User;
import helpers.LoginHelper;
import helpers.MessageListHelper;
import org.testng.annotations.Test;

public class Test4 extends AbstractTest {
    @Test(description = "Case 4. Delete message")
    //@Parameters({"Message"})
    public void test() {
        MessageListHelper messageListHelper = new MessageListHelper();
        LoginHelper loginHelper = new LoginHelper();

        loginHelper.signInToUserController(User.USER_ADMIN);

        Message message = Message.createRandom();

        messageListHelper.createNewMessage(message);

        messageListHelper.deleteMessage(message);
    }
}
