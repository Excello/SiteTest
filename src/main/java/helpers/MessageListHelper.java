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

    //TODO Этот метод по сути ничего не делает. Ничто не мешает в тестах так и писать  messageList.clickNewMessageButton();

    //TODO Ну вот посмотри на логику своего метода. Он же странный до нельзя:
    //1. Создать рандомное сообщение, если оно не Null. А почему оно должно быть null???
    //2. Создать на ровном месте страницу CreateMessagePage. Т.е. это предполагает, что метод должен быть вызван, когда открыта страница CreateNewMessagePage. Но это вообще не очевидно и неправильно так писать
    //3. По факту ты снова просто дергаешь один метод у createMessageFormHelper. Т.е. снова этот метод вообще не пойми зачем нужен
    public Message createNewMessage(Message message){

        //Message List is displayed
        messageList.assertPageOpened();

        //Create Message
        messageList = createMessageFormHelper.createNewMessage(message, messageList);

        //Check that message is in the list
        messageList.assertMessageIsInList(message);

        return message; //TODO Ну зачем??? у тебя уже есть message, зачем создавать дубликат
    }

    //TODO Я все равно не понимаю необходимость в этом методе. Ради одного асерта создавать метод хелпера? Попробуй вообще отказаться от хелперов, не думаю, что ты много потеряешь
    //TODO Зачем new????  messageList.openViewMessagePage(message) возвращает страницу

    //TODO Ну вот снова. Один единственный метод вызывается. Зачем его оборачивать
    //TODO Это еще зачем?

    //TODO Ну вот снова. Один единственный метод вызывается. Зачем его оборачивать

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

    //TODO Метод снова ничего не делает по сути. Он тут не нужен
    //TODO Ну зачем??? у тебя уже есть message, зачем создавать дубликат

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
        //TODO Так и не исправлено
        messageList.assertUsername(user.getName());

        return messageList;
    }

    //TODO Ну вот у тебя в странице метод называется selectAllUsersCheckBox, а тут какой-то хер пойми selectCheckbox. И он тут тоже не нужен, можно дернуть напрямую метод у страницы, зачем это делегировать хелперу
    //TODO То же что и выше
    //TODO Опять. Ну на кой это совать в хелпер
}
