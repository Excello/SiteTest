package tests;

import data.Message;
import data.User;
import helpers.LoginHelper;
import helpers.MessageListHelper;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test3 extends AbstractTest {
    @Test(description = "Case 3. Creating and editing of message")
    @Parameters({"Message", "NewMessage"})
    public void test(Message message, Message newMessage) {
        MessageListHelper messageListHelper = new MessageListHelper();
        LoginHelper loginHelper = new LoginHelper();

        loginHelper.signInToUserController(User.USER_ADMIN);

        /*if (headline.equals("")) headline = null;
        if (text.equals("")) text = null;*/

        Message userMessage = messageListHelper.createMessage(message);

/*
        headline = message[0];
        text = message[1];
*/

/*
        if (newHeadline.equals("")) newHeadline = null;
        if (newText.equals("")) newText = null;
*/

        messageListHelper.editMessage(userMessage, newMessage);
    }
}
