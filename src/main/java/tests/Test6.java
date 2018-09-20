package tests;

import helpers.LoginHelper;
import helpers.MessageListHelper;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test6 extends AbstractTest {
    @Test(description = "Case 6. Create 2 messages")
    @Parameters({"Login", "Password", "Headline1Value", "Text1Value", "Headline2Value", "Text2Value"})
    public void test(String login, String password, String headline1, String text1, String headline2, String text2) {
        MessageListHelper messageListHelper = new MessageListHelper();
        LoginHelper loginHelper = new LoginHelper();

        loginHelper.signInToUserController(login, password);

        if (headline1.equals("")) headline1 = null;
        if (text1.equals("")) text1 = null;
        if (headline2.equals("")) headline2 = null;
        if (text2.equals("")) text2 = null;

        messageListHelper.createTwoMessages(headline1, text1, headline2, text2);
    }
}
