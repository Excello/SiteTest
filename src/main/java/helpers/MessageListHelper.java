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
    public Message createNewMessage(Message message){

        //Message List is displayed
        messageList.assertPageOpened();

        //Create Message
        messageList = createMessageFormHelper.createNewMessage(message, messageList);

        //Check that message is in the list
        messageList.assertMessageIsInList(message);

        return message;
    }

    //TODO Все равно не понимаю, к чему этот метод? Если у тебя все это можно делать в createMessageFormHelper
    public void editMessage(Message message, Message newMessage) {
        TestLogger.logMessage("Opening from the list to edit the message with the values headline: " + message.getHeadline() + ", text" + message.getText());


        //Message List is displayed
        messageList.assertPageOpened();

        //Enter new Headline and Text
        messageList = createMessageFormHelper.editMessage(message, newMessage, messageList);

        //There is a last created object and headline and text fields contains early filled values
        messageList.assertMessageIsInList(newMessage);
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

    public MessageList signInAnotherUser(User user) {
        TestLogger.logMessage("Tap 'Logout' button");

        //Perform logout
        messageList.clickLogOutButton();

        //Sign in as another user
        LoginPage loginPage = new LoginPage();
        messageList = loginPage.signIn(user);
        //TODO Не очень корректно использовать снова тот же messageList, если ты уже выполнил logout.
        //TODO В силу разных причин страницы могут нечаянно хранить какую-то информацию о своем текущем состоянии на основании истории действий.
        // TODO Такой подход в перспективе может породить много проблем, сложно отлаваливаемых
        //TODO Так и не исправлено???
        messageList.assertUsername(user.getName());

        return messageList;
    }
}
