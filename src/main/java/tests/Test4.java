package tests;

import helpers.LoginHelper;
import helpers.MessageListHelper;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test4 extends AbstractTest {
    @Test(description = "Case 4. Delete message")
    @Parameters({"Login", "Password", "HeadlineValue", "TextValue"})
    public void test(String login, String password, String headline, String text) {
        MessageListHelper messageListHelper = new MessageListHelper();
        LoginHelper loginHelper = new LoginHelper();

        loginHelper.signInToUserController(login, password);

        if (headline.equals("")) headline = null;
        if (text.equals("")) text = null;

        String[] message = messageListHelper.createMessage(headline, text);

        headline = message[0];
        text = message[1];

        messageListHelper.deleteMessage(headline, text);
    }
}
