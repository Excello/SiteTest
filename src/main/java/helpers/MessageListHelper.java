package helpers;

import component.AbstractComponent;
import data.Message;
import data.User;
import logging.TestLogger;
import pages.LoginPage;
import pages.MessageList;

public class MessageListHelper extends AbstractComponent {

    public static void createNewMessage(Message message, MessageList messageList){

        //Message List is displayed
        messageList.assertPageOpened();

        //Create Message
        messageList = CreateMessageFormHelper.createNewMessage(message, messageList);

        //Check that Message is existed
        messageList.assertMessageIsInList(message);
    }

    public static void deleteMessage(Message message, MessageList messageList) {
        TestLogger.logMessage("Deleting from the list the message: " + message.getHeadline() + ", text: " + message.getText());

        //Message List is displayed
        messageList.assertPageOpened();

        //Delete Message
        messageList.deleteMessage(message);

        //Check that Message is removed
        messageList.assertMessageIsNotInList(message);
    }

    public static void signInAnotherUser(User user, MessageList messageList) {
        TestLogger.logMessage("Tap 'Logout' button");

        //Perform logout
        messageList.clickLogOutButton();

        //Sign in as another user
        LoginPage newLoginPage = new LoginPage();
        MessageList newMessageList = newLoginPage.signIn(user);
        newMessageList.assertUsername(user.getName());
    }
}
