package tests;

import data.Message;
import data.User;
import helpers.LoginHelper;
import helpers.MessageListHelper;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test1 extends AbstractTest{
    @Test(description = "Case 1. Create Message")
    @Parameters({"User", "Message"})
    public void test(Message message){
        MessageListHelper messageListHelper = new MessageListHelper();
        LoginHelper loginHelper = new LoginHelper();

        loginHelper.signInToUserController(User.USER_ADMIN);

/*
        if(headline.equals("")) headline = null;
        if(text.equals("")) text = null;
*/

        messageListHelper.createMessage(message);
    }
}
