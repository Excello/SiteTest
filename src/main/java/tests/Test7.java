package tests;

import core.AbstractTest;
import helpers.LoginHelper;
import helpers.MessageListHelper;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test7 extends AbstractTest {
    @Test(description = "Case 7. Creation and view of 2 messages")
    @Parameters({"Login", "Password", "HeadlineValue", "TextValue"})
    public void test(String login, String password, String headline1, String text1, String headline2, String text2) {
        MessageListHelper messageListHelper = new MessageListHelper();
        LoginHelper loginHelper = new LoginHelper();

        loginHelper.signInToUserController(login, password);

        if (headline1.equals("")) headline1 = null;
        if (text1.equals("")) text1 = null;
        if (headline2.equals("")) text1 = null;
        if (text2.equals("")) text1 = null;

        messageListHelper.createTwoMessages(headline1, text1, headline2, text2);
    }
}
