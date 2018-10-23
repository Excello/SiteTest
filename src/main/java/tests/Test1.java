package tests;

import data.Message;
import data.User;
import helpers.LoginHelper;
import helpers.MessageListHelper;
import org.testng.annotations.Test;
import pages.MessageList;
import pages.ViewMessagePage;

public class Test1 extends AbstractTest{
    @Test(description = "Case 1. Create Message")
    public void test(){
        MessageListHelper messageListHelper = new MessageListHelper();
        LoginHelper loginHelper = new LoginHelper();

        loginHelper.signInToUserController(User.USER_ADMIN);

        Message message = Message.createRandom();

        MessageList messageList = new MessageList();

        messageList.clickNewMessageButton();

        messageListHelper.createNewMessage(message);
    }
}
