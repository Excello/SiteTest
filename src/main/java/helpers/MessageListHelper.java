package helpers;

import component.AbstractComponent;
import data.Message;
import data.User;
import logging.TestLogger;
import pages.LoginPage;
import pages.MessageList;

public class MessageListHelper extends AbstractComponent {
    private MessageList messageList;
    private CreateMessageFormHelper createMessageFormHelper;

    public MessageListHelper() {
        messageList = new MessageList();
        createMessageFormHelper = new CreateMessageFormHelper();
    }

    //TODO Снова ты исходишь из того, что ты сейчас на странице MessageList. Но нигде нет такого ограничения
    //TODO Зачем возвращать Message который ты итак получил на входе?
    public void createNewMessage(Message message, MessageList messageList){

        //Message List is displayed
        messageList.assertPageOpened();

        //Create Message
        messageList = createMessageFormHelper.createNewMessage(message, messageList);

        //Check that Message is existed
        messageList.assertMessageIsInList(message);
    }

    //TODO Все равно не понимаю, к чему этот метод? Если у тебя все это можно делать в createMessageFormHelper

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
        //TODO Не очень корректно использовать снова тот же messageList, если ты уже выполнил logout.
        //TODO В силу разных причин страницы могут нечаянно хранить какую-то информацию о своем текущем состоянии на основании истории действий.
        // TODO Такой подход в перспективе может породить много проблем, сложно отлаваливаемых
        //TODO Так и не исправлено???
        newMessageList.assertUsername(user.getName());
    }
}
