package helpers;

import component.AbstractComponent;
import data.Message;
import data.User;
import logging.TestLogger;
import pages.*;

public class MessageListHelper extends AbstractComponent {
    private MessageList messageList;
    private CreateMessageFormHelper createMessageFormHelper;

    public MessageListHelper() {
        messageList = new MessageList();
        createMessageFormHelper = new CreateMessageFormHelper();
    }

    //TODO Вместо String[] создай класс Message
    public CreateMessagePage tapCreateMessage() {
       //TODO Не костанатный текст, а генерируй случайную строку
       //TODO Same
        TestLogger.logMessage("Tap 'Create Message' button");

        messageList.clickNewMessageButton();

        return new CreateMessagePage();
    }

    //TODO Тоже возвращай класс Message,и на вход прнимай класс Message
    public Message createMessage(Message message){

        if (message == null) {
            message = Message.createRandom();
        }

        CreateMessagePage createMessagePage = new CreateMessagePage();

        //Create Message
        createMessageFormHelper.createNewMessage(message, createMessagePage);

        return new Message(message.getHeadline(), message.getText());
    }

    //TODO Не очень понимаю зачем этот хелпер. Только ради асертов? Логически стоило бы сделать наоборот.
    //TODO Асерт на существование должен быть в CreateMessageFormHelper. Этот метод и выше выглядят дубликатами аналогов в CreateMessageFormHelper

    //TODO почему void? Почему не ViewMessagePage?
    public ViewMessagePage viewMessage(Message message) {
        TestLogger.logMessage("Opening from the list to view the message with the values headline: " + message.getHeadline() + ", text: " + message.getText());

        //Message List is displayed
        messageList.assertMessageListPageOpened();

        //View Message
        messageList.openViewMessagePage(message);

        return new ViewMessagePage();
    }

        //TODO А зачем? Кто-то просил?
        // создан метод assertMessageIsCorrect в ViewMessageHelper

        //TODO C какой стати мы идем обратно?
        // the same as above

    public void assertMessageIsCorrect(Message message) {
        //TODO Это еще зачем?
        //Check that there is message in the list
        messageList.assertMessageIsInList(message);
    }

    public void assertMessageIsNotDisplayed(Message message) {
        //Check that there is no message in the list
        messageList.assertMessageIsNotInList(message);
    }


    //TODO Тоже дубликат с CreateMessageFormHelper
    public void editMessage(Message message, Message newMessage) {
        TestLogger.logMessage("Opening from the list to edit the message with the values headline: " + message.getHeadline() + ", text" + message.getText());

        if (newMessage == null) {
            newMessage = Message.createRandom();
        }

        //Message List is displayed
        messageList.assertMessageListPageOpened();

        //Enter new Headline and Text
        messageList = createMessageFormHelper.editMessage(message, newMessage, messageList);

        //There is a last created object and headline and text fields contains early filled values
        messageList.assertMessageIsInList(newMessage);
    }

    public void deleteMessage(Message message) {
        TestLogger.logMessage("Deleting from the list the message: " + message.getHeadline() + ", text: " + message.getText());

        //Message List is displayed
        messageList.assertMessageListPageOpened();

        //Delete Message
        messageList.deleteMessage(message);

        //Check that Message is removed
        messageList.assertMessageIsNotInList(message);
    }

    //TODO Тоже дубликат с CreateMessageFormHelper
    public Message createMessageWithoutSaving(Message message) {
        TestLogger.logMessage("Creating message without saving");

        if (message == null) {
            message = Message.createRandom();
        }

        //Message List is displayed
        messageList.assertMessageListPageOpened();

        //Create message without saving it
        createMessageFormHelper.createMessageWithoutSaving(message, messageList);

        return new Message(message.getHeadline(), message.getText());
    }

    public void signInAnotherUser(User user) {
        TestLogger.logMessage("Tap 'Logout' button");

        //Perform logout
        messageList.clickLogOutButton();

        //Sign in as another user
        LoginPage loginPage = new LoginPage();
        loginPage.signIn(user);
        //TODO Не очень корректно использовать снова тот же messageList, если ты уже выполнил logout.
        //TODO В силу разных причин страницы могут нечаянно хранить какую-то информацию о своем текущем состоянии на основании истории действий.
        // TODO Такой подход в перспективе может породить много проблем, сложно отлаваливаемых
        messageList.assertUsername(user.getName());
    }

    public MessageList selectCheckbox() {

        // Select checkbox
        messageList.selectAllUsersCheckBox();

        return new MessageList();
    }

    public MessageList removeCheckbox() {

        // Remove Checkbox
        messageList.removeAllUsersCheckBox();

        return new MessageList();
    }

    //TODO Метод называется All, но передавать может два и только два сообщения, да еще и логин какой-то непонятный
    public void verifyUserMessage(Message message, String author) {
        TestLogger.logMessage("Verify user " + author + " messages");

        //Verify All Messages
        messageList.assertMessageIsInList(message, author);
    }
}
