package tests;

import core.AbstractTest;
import helpers.LoginHelper;
import helpers.MessageListHelper;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test3 extends AbstractTest {
    @Test(description = "Case 3. Creating and editing of message")
    @Parameters({"Login", "Password", "HeadlineValue", "TextValue", "NewHeadlineValue", "NewTextValue"})
    public void test(String login, String password, String headline, String text, String newHeadline, String newText) {
        MessageListHelper messageListHelper = new MessageListHelper();
        LoginHelper loginHelper = new LoginHelper();

        loginHelper.signInToUserController(login, password);

        if (headline.equals("")) headline = null;
        if (text.equals("")) text = null;

        String[] message = messageListHelper.createMessage(headline, text);

        headline = message[0];
        text = message[1];

        messageListHelper.editMessage(headline, text, newHeadline, newText);
    }
}
