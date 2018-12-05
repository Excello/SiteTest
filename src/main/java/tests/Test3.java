package tests;

import data.Message;
import data.User;
import helpers.CreateMessageFormHelper;
import helpers.LoginHelper;
import helpers.MessageListHelper;
import org.testng.annotations.Test;
import pages.MessageList;

public class Test3 extends AbstractTest {
    @Test(description = "Case 3. Creating and editing of message")
    //@Parameters({"Message", "NewMessage"})
    public void test() {
        LoginHelper loginHelper = new LoginHelper();

        Message firstMessage = Message.createRandom();

        MessageList messageList = loginHelper.signInToUserController(User.USER_ADMIN); //TODO Нет. Это должен быть результат loginHelper.signInToUserController

        MessageListHelper.createNewMessage(firstMessage, messageList);

        Message secondMessage = Message.createRandom();

        messageList = CreateMessageFormHelper.editMessage(firstMessage, secondMessage, messageList);
        messageList.assertMessageIsInList(secondMessage);
    }
}
