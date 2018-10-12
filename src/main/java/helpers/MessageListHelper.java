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
    public Message createMessage() {
       //TODO Не костанатный текст, а генерируй случайную строку
       //TODO Same
        Message message = Message.createRandom();
        createMessage(message);
        return new Message(message.getHeadline(), message.getText());
    }

    //TODO Тоже возвращай класс Message,и на вход прнимай класс Message
    public Message createMessage(Message message){

        //Message List is displayed
        messageList.assertMessageListPageOpened();

        //Create Message
        messageList = createMessageFormHelper.createNewMessage(message, messageList);

        //There is a created object and headline and text fields contains early filled values
        messageList.assertMessageIsInList(message);

        return new Message(message.getHeadline(), message.getText());
    }

    public Message createnewMessage(Message message){

        //Message List is displayed
        messageList.assertMessageListPageOpened();

        //Create Message
        messageList = createMessageFormHelper.createNewMessage(message, messageList);

        return new Message(message.getHeadline(), message.getText());
    }

    //TODO Не очень понимаю зачем этот хелпер. Только ради асертов? Логически стоило бы сделать наоборот.
    //TODO Асерт на существование должен быть в CreateMessageFormHelper. Этот метод и выше выглядят дубликатами аналогов в CreateMessageFormHelper
    /*public String[] createTwoMessages(String headline1, String text1, String headline2, String text2) {

        //Message List is displayed
        messageList.assertMessageListPageOpened();

        //Create Two Messages
        String[] message = createMessageFormHelper.createTwoMessages(headline1, text1, headline2, text2, messageList);

        headline1 = message[0];
        text1 = message[1];
        headline2 = message[2];
        text2 = message[3];

        //There is a created object and headline and text fields contains early filled values
        messageList.assertMessageIsInList(headline1, text1);

        //There is a created object and headline and text fields contains early filled values
        messageList.assertMessageIsInList(headline2, text2);

        return new String[] {headline1, text1, headline2, text2};
    }*/

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
        //There is a created object and headline and text fields contains early filled values
        messageList.assertMessageIsInList(message);
    }

    public void assertMesseageIsNotDisplayed(Message message) {
        messageList.assertMessageIsNotInList(message);
    }


    //TODO Тоже дубликат с CreateMessageFormHelper
    public void editMessage(Message message, Message newMessage) {
        TestLogger.logMessage("Opening from the list to edit the message with the values headline: " + message.getHeadline() + ", text" + message.getText());

        //Message List is displayed
        messageList.assertMessageListPageOpened();

        //Enter new Headline and Text
        messageList = createMessageFormHelper.editMessage(message, newMessage, messageList);

      /*  newHeadline = message[0];
        newText = message[1];*/

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
    public void createMessageWithoutSaving(Message message) {
        TestLogger.logMessage("Creating message without saving");

        //Message List is displayed
        messageList.assertMessageListPageOpened();

        //Create message without saving it
        createMessageFormHelper.createMessageWithoutSaving(message, messageList);

        //Check that Message is not saved
        messageList.assertMessageIsNotInList(message);
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
        messageList.selectAllUsersCheckBox();

        return new MessageList();
    }

    public MessageList removeCheckbox() {

        messageList.removeAllUsersCheckBox();

        return new MessageList();
    }


    //TODO Метод называется All, но передавать может два и только два сообщения, да еще и логин какой-то непонятный
    public void verifyUserMessage(Message message, String author) {
        TestLogger.logMessage("Verify user " + author + " messages");

        //Select checkbox
        messageList.selectAllUsersCheckBox();

        //Verify All Messages
        messageList.assertMessageIsInList(message, author);
    }
}
