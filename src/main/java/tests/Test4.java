package tests;

import data.Message;
import data.User;
import helpers.LoginHelper;
import helpers.MessageListHelper;
import helpers.ViewMessageHelper;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test4 extends AbstractTest {
    @Test(description = "Case 4. Delete message")
    @Parameters({"Message"})
    public void test(Message message) {
        MessageListHelper messageListHelper = new MessageListHelper();
        LoginHelper loginHelper = new LoginHelper();
        ViewMessageHelper viewMessageHelper = new ViewMessageHelper();

        loginHelper.signInToUserController(User.USER_ADMIN);

        messageListHelper.tapCreateMessage();

        Message userMessage = messageListHelper.createNewMessage(message);

        viewMessageHelper.openMessageList(userMessage);

        messageListHelper.assertMessageIsCorrect(userMessage);

        messageListHelper.deleteMessage(userMessage);
    }
}
