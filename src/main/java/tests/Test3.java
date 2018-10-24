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
        MessageListHelper messageListHelper = new MessageListHelper();
        LoginHelper loginHelper = new LoginHelper();
        CreateMessageFormHelper createMessageFormHelper = new CreateMessageFormHelper();

        loginHelper.signInToUserController(User.USER_ADMIN);

        Message firstMessage = Message.createRandom();

        MessageList messageList = new MessageList();

        messageListHelper.createNewMessage(firstMessage, messageList);

        Message secondMessage = Message.createRandom();

        messageList = createMessageFormHelper.editMessage(firstMessage, secondMessage, messageList);
        //TODO Ну вот например тут совсем неочевидно, что messageListHelper проверит корректность операции редактирования, потому и совать туда асерт смысла не было

        messageList.assertMessageIsInList(secondMessage);
    }
}
