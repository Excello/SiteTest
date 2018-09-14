package tests;

import core.AbstractTest;
import helpers.LoginHelper;
import helpers.MessageListHelper;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test1 extends AbstractTest{
    @Test(description = "Case 1. Create Message")
    @Parameters({"Login", "Password", "HeadlineValue", "TextValue"})
    public void test(String login, String password, String headline, String text){
        MessageListHelper messageListHelper = new MessageListHelper();
        LoginHelper loginHelper = new LoginHelper();

        loginHelper.signInToUserController(login, password);
        if(headline.equals("")) headline = null;
        if(text.equals("")) text = null;

        messageListHelper.createMessage(headline, text);
    }
}
