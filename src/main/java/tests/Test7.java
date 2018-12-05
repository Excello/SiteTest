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
        LoginHelper loginHelper = new LoginHelper();

        // Working as ADMIN

        Message adminMessage = Message.createRandom();

        MessageList messageList = loginHelper.signInToUserController(User.USER_ADMIN); //TODO Нет. Это должен быть результат loginHelper.signInToUserController

        MessageListHelper.createNewMessage(adminMessage, messageList);

        ViewMessagePage viewMessagePage = messageList.openViewMessagePage(adminMessage);

        viewMessagePage.assertMessage(adminMessage);

        messageList = viewMessagePage.clickMessageList();

        messageList.assertMessageIsInList(adminMessage);

        // Working as JDOE

        MessageListHelper.signInAnotherUser(User.USER_JDOE, messageList);

        Message jdoeMessage = Message.createRandom();

        MessageListHelper.createNewMessage(jdoeMessage, messageList);

        viewMessagePage = messageList.openViewMessagePage(jdoeMessage);

        viewMessagePage.assertMessage(jdoeMessage);

        messageList = viewMessagePage.clickMessageList();

        messageList.assertMessageIsInList(jdoeMessage);


        // Working as ADMIN

        MessageListHelper.signInAnotherUser(User.USER_ADMIN, messageList);

        messageList.selectAllUsersCheckBox();

        messageList.assertMessageIsInList(adminMessage, User.USER_ADMIN.getName());

        messageList.assertMessageIsInList(jdoeMessage, User.USER_JDOE.getName());

        messageList.removeAllUsersCheckBox();

        messageList.assertMessageIsInList(adminMessage, User.USER_ADMIN.getName());

        messageList.assertMessageIsNotInList(jdoeMessage);
    }
}
