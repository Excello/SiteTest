package tests;

import data.Message;
import data.User;
import helpers.LoginHelper;
import helpers.MessageListHelper;
import helpers.ViewMessageHelper;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.MessageList;

public class Test1 extends AbstractTest{
    @Test(description = "Case 1. Create Message")
    public void test(){
        MessageListHelper messageListHelper = new MessageListHelper();
        LoginHelper loginHelper = new LoginHelper();
        Message message = Message.createRandom();

        loginHelper.signInToUserController(User.USER_ADMIN);

        messageListHelper.createNewMessage(message);
    }
}
