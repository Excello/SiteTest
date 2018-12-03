package helpers;

import component.AbstractComponent;
import data.Message;
import data.User;
import logging.TestLogger;
import pages.LoginPage;
import pages.MessageList;

public class MessageListHelper extends AbstractComponent {
    private MessageList messageList;//TODO Этой переменной быть не должно, всем методы сделать static
    private CreateMessageFormHelper createMessageFormHelper;

    public MessageListHelper() {
        messageList = new MessageList();
        createMessageFormHelper = new CreateMessageFormHelper();
    }

    public void createNewMessage(Message message, MessageList messageList){

        //Message List is displayed
        messageList.assertPageOpened();

        //Create Message
        messageList = createMessageFormHelper.createNewMessage(message, messageList);

        //Check that Message is existed
        messageList.assertMessageIsInList(message);
    }

    public void deleteMessage(Message message) {
        TestLogger.logMessage("Deleting from the list the message: " + message.getHeadline() + ", text: " + message.getText());

        //Message List is displayed
        messageList.assertPageOpened();

        //Delete Message
        messageList.deleteMessage(message);

        //Check that Message is removed
        messageList.assertMessageIsNotInList(message);
    }

    public void signInAnotherUser(User user) {
        TestLogger.logMessage("Tap 'Logout' button");

        //Perform logout
        messageList.clickLogOutButton();

        //Sign in as another user
        LoginPage newLoginPage = new LoginPage();
        MessageList newMessageList = newLoginPage.signIn(user);
        newMessageList.assertUsername(user.getName());
    }
}
