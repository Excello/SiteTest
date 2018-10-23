package tests;

import data.Message;
import data.User;
import helpers.LoginHelper;
import helpers.MessageListHelper;
import org.testng.annotations.Test;
import pages.MessageList;
import pages.ViewMessagePage;

public class Test7 extends AbstractTest {
    @Test(description = "Case 7. Creation and view of 2 messages")
    //@Parameters({"AdminMessage", "JdoeMessage"})
    public void test() {
        MessageListHelper messageListHelper = new MessageListHelper();
        LoginHelper loginHelper = new LoginHelper();

        // Working as ADMIN

        loginHelper.signInToUserController(User.USER_ADMIN);

        Message adminMessage = Message.createRandom();

        messageListHelper.createNewMessage(adminMessage);

        MessageList messageList = new MessageList();

        ViewMessagePage viewMessagePage = messageList.openViewMessagePage(adminMessage);

        viewMessagePage.assertMessage(adminMessage);

        messageList = viewMessagePage.clickMessageList();

        messageList.assertMessageIsInList(adminMessage);

        // Working as JDOE

        messageListHelper.signInAnotherUser(User.USER_JDOE);

        Message jdoeMessage = Message.createRandom();

        messageListHelper.createNewMessage(jdoeMessage);

        viewMessagePage = messageList.openViewMessagePage(jdoeMessage);

        viewMessagePage.assertMessage(jdoeMessage);

        messageList = viewMessagePage.clickMessageList();

        messageList.assertMessageIsInList(jdoeMessage);


        // Working as ADMIN

        messageListHelper.signInAnotherUser(User.USER_ADMIN);

        messageList.selectAllUsersCheckBox();

        messageList.assertMessageIsInList(adminMessage, User.USER_ADMIN.getName());

        messageList.assertMessageIsInList(jdoeMessage, User.USER_JDOE.getName());

        messageList.removeAllUsersCheckBox();

        messageList.assertMessageIsInList(adminMessage, User.USER_ADMIN.getName());

        messageList.assertMessageIsNotInList(jdoeMessage);
    }
}
