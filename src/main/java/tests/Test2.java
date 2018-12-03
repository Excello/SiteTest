package tests;

import data.Message;
import data.User;
import helpers.LoginHelper;
import helpers.MessageListHelper;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.MessageList;
import pages.ViewMessagePage;

public class Test2 extends AbstractTest {
    @Test(description = "Case 2. Creation and view message")
    @Parameters({"Message"})
    public void test() {
        MessageListHelper messageListHelper = new MessageListHelper();
        LoginHelper loginHelper = new LoginHelper();

        loginHelper.signInToUserController(User.USER_ADMIN);

        Message message = Message.createRandom();

        MessageList messageList = new MessageList(); //TODO Нет. Это должен быть результат loginHelper.signInToUserController

        messageListHelper.createNewMessage(message, messageList);

        ViewMessagePage viewMessagePage = messageList.openViewMessagePage(message);

        viewMessagePage.assertMessage(message);

        messageList = viewMessagePage.clickMessageList();

        messageList.assertMessageIsInList(message);
    }
}
