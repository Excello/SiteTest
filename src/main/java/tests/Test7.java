package tests;

import core.AbstractTest;
import helpers.LoginHelper;
import helpers.MessageListHelper;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test7 extends AbstractTest {
    @Test(description = "Case 7. Creation and view of 2 messages")
    @Parameters({"Login", "Password", "HeadlineValue", "TextValue"})
    public void test(String login1, String password1, String login2, String password2, String headline1, String text1, String headline2, String text2, String expected, String expected1) {
        MessageListHelper messageListHelper = new MessageListHelper();
        LoginHelper loginHelper = new LoginHelper();

        loginHelper.signInToUserController(login1, password1);

        if (headline1.equals("")) headline1 = null;
        if (text1.equals("")) text1 = null;

        String[] message1 = messageListHelper.createMessage(headline1, text1);

        headline1 = message1[0];
        text1 = message1[1];

        messageListHelper.viewMessage(headline1, text1);

        messageListHelper.signInAnotherUser(login2, password2, expected);

        if (headline2.equals("")) headline2 = null;
        if (text2.equals("")) text2 = null;

        String[] message2 = messageListHelper.createMessage(headline2, text2);

        headline2 = message2[0];
        text2 = message2[1];

        messageListHelper.viewMessage(headline2, text2);

        messageListHelper.signInAnotherUser(login1, password1, expected1);
    }
}
