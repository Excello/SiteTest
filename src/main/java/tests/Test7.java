package tests;

import data.Message;
import data.User;
import helpers.LoginHelper;
import helpers.MessageListHelper;
import helpers.ViewMessageHelper;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static data.Message.createRandom;

public class Test7 extends AbstractTest {
    @Test(description = "Case 7. Creation and view of 2 messages")
    @Parameters({"AdminMessage", "JdoeMessage"})
    public void test() {
        MessageListHelper messageListHelper = new MessageListHelper();
        LoginHelper loginHelper = new LoginHelper();
        ViewMessageHelper viewMessageHelper = new ViewMessageHelper();
        Message message = createRandom();
        Message anotherMessage = createRandom();

        loginHelper.signInToUserController(User.USER_ADMIN);

       /* if (headline1.equals("")) headline1 = null;
        if (text1.equals("")) text1 = null;*/

        Message adminMessage = messageListHelper.createMessage(message);

        /*headline1 = message1[0];
        text1 = message1[1];*/

        messageListHelper.viewMessage(adminMessage);

        viewMessageHelper.assertMessageIsCorrect(adminMessage);

        messageListHelper.assertMessageIsCorrect(adminMessage);

        messageListHelper.signInAnotherUser(User.USER_JDOE);

        /*if (headline2.equals("")) headline2 = null;
        if (text2.equals("")) text2 = null;*/

        Message jdoeMessage = messageListHelper.createMessage(anotherMessage);

        messageListHelper.viewMessage(jdoeMessage);

        viewMessageHelper.assertMessageIsCorrect(jdoeMessage);

        messageListHelper.assertMessageIsCorrect(jdoeMessage);


        messageListHelper.signInAnotherUser(User.USER_ADMIN);

        messageListHelper.selectCheckbox();

        messageListHelper.verifyUserMessage(adminMessage, User.USER_ADMIN.getName());

        messageListHelper.verifyUserMessage(jdoeMessage, User.USER_JDOE.getName());

        messageListHelper.removeCheckbox();

        messageListHelper.verifyUserMessage(adminMessage, User.USER_ADMIN.getName());

        messageListHelper.assertMesseageIsNotDisplayed(jdoeMessage);
    }
}
