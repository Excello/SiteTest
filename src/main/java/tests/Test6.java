package tests;

import data.Message;
import data.User;
import helpers.LoginHelper;
import helpers.MessageListHelper;
import helpers.ViewMessageHelper;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test6 extends AbstractTest {
    @Test(description = "Case 6. Create 2 messages")
    @Parameters({"Message", "NewMessage"})
    public void test(Message message, Message newMessage) {
        MessageListHelper messageListHelper = new MessageListHelper();
        LoginHelper loginHelper = new LoginHelper();
        ViewMessageHelper viewMessageHelper = new ViewMessageHelper();

        loginHelper.signInToUserController(User.USER_ADMIN);

        messageListHelper.tapCreateMessage();

        Message firstMessage = messageListHelper.createNewMessage(message);

        viewMessageHelper.createNewMessage(firstMessage);

        Message secondMessage = messageListHelper.createNewMessage(newMessage);

        viewMessageHelper.openMessageList(secondMessage);

        messageListHelper.assertMessageIsCorrect(firstMessage);

        messageListHelper.assertMessageIsCorrect(secondMessage);
    }
}
