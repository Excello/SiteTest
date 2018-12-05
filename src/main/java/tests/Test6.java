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

        MessageList messageList = loginHelper.signInToUserController(User.USER_ADMIN);

        messageList.assertPageOpened();

        //First Message

        MessagePage createMessagePage = messageList.clickNewMessageButton();

        Message firstMessage = Message.createRandom();

        ViewMessagePage viewMessagePage = createMessagePage.createMessage(firstMessage);

        viewMessagePage.assertMessage(firstMessage);

        //Second Message

        createMessagePage = viewMessagePage.clickNewMessage();

        Message secondMessage = Message.createRandom();

        viewMessagePage = createMessagePage.createMessage(secondMessage);

        viewMessagePage.assertMessage(secondMessage);

        messageList = viewMessagePage.clickMessageList();

        //Verify Messages

        messageList.assertMessageIsInList(firstMessage);

        messageList.assertMessageIsInList(secondMessage);
    }
}
