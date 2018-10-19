package tests;

import data.Message;
import data.User;
import helpers.LoginHelper;
import helpers.MessageListHelper;
import helpers.ViewMessageHelper;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.MessageList;

public class Test2 extends AbstractTest {
    @Test(description = "Case 2. Creation and view message")
    @Parameters({"Message"})
    public void test() {
        MessageListHelper messageListHelper = new MessageListHelper();
        LoginHelper loginHelper = new LoginHelper();
        MessageList messageList = new MessageList();
        ViewMessageHelper viewMessageHelper = new ViewMessageHelper();
        Message message = Message.createRandom();

        loginHelper.signInToUserController(User.USER_ADMIN);

        messageListHelper.createNewMessage(message);

        messageList.openViewMessagePage(message);

        viewMessageHelper.assertMessageIsCorrect(message);

        viewMessageHelper.goToMessageList();




        viewMessageHelper.openMessageList(userMessage);

        messageListHelper.viewMessage(userMessage);

        viewMessageHelper.openMessageList(userMessage);

        messageListHelper.assertMessageIsCorrect(userMessage);
    }
}
