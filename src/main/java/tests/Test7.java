package tests;

import data.Message;
import data.User;
import helpers.LoginHelper;
import helpers.MessageListHelper;
import helpers.ViewMessageHelper;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test7 extends AbstractTest {
    @Test(description = "Case 7. Creation and view of 2 messages")
    @Parameters({"AdminMessage", "JdoeMessage"})
    public void test(Message message, Message anotherMessage) {
        MessageListHelper messageListHelper = new MessageListHelper();
        LoginHelper loginHelper = new LoginHelper();
        ViewMessageHelper viewMessageHelper = new ViewMessageHelper();

        // Working as ADMIN

        loginHelper.signInToUserController(User.USER_ADMIN);

        messageListHelper.tapCreateMessage();

        Message adminMessage = messageListHelper.createNewMessage(message);

        viewMessageHelper.openMessageList(adminMessage);

        messageListHelper.assertMessageIsCorrect(adminMessage);

        messageListHelper.viewMessage(adminMessage);

        viewMessageHelper.openMessageList(adminMessage);

        messageListHelper.assertMessageIsCorrect(adminMessage);

        // Working as JDOE

        messageListHelper.signInAnotherUser(User.USER_JDOE);

        messageListHelper.tapCreateMessage();

        Message jdoeMessage = messageListHelper.createNewMessage(anotherMessage);

        viewMessageHelper.openMessageList(jdoeMessage);

        messageListHelper.assertMessageIsCorrect(jdoeMessage);

        messageListHelper.viewMessage(jdoeMessage);

        viewMessageHelper.openMessageList(jdoeMessage);

        messageListHelper.assertMessageIsCorrect(jdoeMessage);

        // Working as ADMIN

        messageListHelper.signInAnotherUser(User.USER_ADMIN);

        messageListHelper.selectCheckbox();

        messageListHelper.verifyUserMessage(adminMessage, User.USER_ADMIN.getName());

        messageListHelper.verifyUserMessage(jdoeMessage, User.USER_JDOE.getName());

        messageListHelper.removeCheckbox();

        messageListHelper.verifyUserMessage(adminMessage, User.USER_ADMIN.getName());

        messageListHelper.assertMessageIsNotDisplayed(jdoeMessage);
    }
}
