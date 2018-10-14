package tests;

import data.Message;
import data.User;
import helpers.LoginHelper;
import helpers.MessageListHelper;
import helpers.ViewMessageHelper;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test3 extends AbstractTest {
    @Test(description = "Case 3. Creating and editing of message")
    @Parameters({"Message", "NewMessage"})
    public void test(Message message, Message newMessage) {
        MessageListHelper messageListHelper = new MessageListHelper();
        LoginHelper loginHelper = new LoginHelper();
        ViewMessageHelper viewMessageHelper = new ViewMessageHelper();

        loginHelper.signInToUserController(User.USER_ADMIN);

        messageListHelper.tapCreateMessage();

        Message userMessage = messageListHelper.createMessage(message);

        viewMessageHelper.openMessageList(userMessage);

        messageListHelper.assertMessageIsCorrect(userMessage);

        messageListHelper.editMessage(userMessage, newMessage);
    }
}
