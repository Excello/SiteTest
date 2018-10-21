package tests;

import data.Message;
import data.User;
import helpers.LoginHelper;
import org.testng.annotations.Test;
import pages.MessageList;
import pages.MessagePage;
import pages.ViewMessagePage;

public class Test6 extends AbstractTest {
    @Test(description = "Case 6. Create 2 messages")
    //@Parameters({"Message", "NewMessage"})
    public void test() {
        LoginHelper loginHelper = new LoginHelper();

        loginHelper.signInToUserController(User.USER_ADMIN);

        MessageList messageListPage = new MessageList();

        messageListPage.assertPageOpened();

        //First Message

        MessagePage createMessagePage = messageListPage.clickNewMessageButton();

        Message firstMessage = Message.createRandom();

        ViewMessagePage viewMessagePage = createMessagePage.createMessage(firstMessage);

        viewMessagePage.assertMessage(firstMessage);

        //Second Message

        createMessagePage = viewMessagePage.clickNewMessage();

        Message secondMessage = Message.createRandom();

        viewMessagePage = createMessagePage.createMessage(secondMessage);

        viewMessagePage.assertMessage(secondMessage);

        messageListPage = viewMessagePage.clickMessageList();

        //Verify Messages

        messageListPage.assertMessageIsInList(firstMessage);

        messageListPage.assertMessageIsInList(secondMessage);
    }
}
