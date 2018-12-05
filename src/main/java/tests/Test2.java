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
        LoginHelper loginHelper = new LoginHelper();

        Message message = Message.createRandom();

        MessageList messageList = loginHelper.signInToUserController(User.USER_ADMIN); //TODO Нет. Это должен быть результат loginHelper.signInToUserController

        MessageListHelper.createNewMessage(message, messageList);

        ViewMessagePage viewMessagePage = messageList.openViewMessagePage(message);

        viewMessagePage.assertMessage(message);

        messageList = viewMessagePage.clickMessageList();

        messageList.assertMessageIsInList(message);
    }
}
