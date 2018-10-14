package tests;

import data.Message;
import data.User;
import helpers.LoginHelper;
import helpers.MessageListHelper;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test5 extends AbstractTest {
    @Test(description = "Case 5. Creation of message without saving")
    @Parameters({"Message"})
    public void test(Message message) {
        MessageListHelper messageListHelper = new MessageListHelper();
        LoginHelper loginHelper = new LoginHelper();

        loginHelper.signInToUserController(User.USER_ADMIN);

        Message userMessage = messageListHelper.createMessageWithoutSaving(message);

        messageListHelper.assertMessageIsNotDisplayed(userMessage);
    }
}
