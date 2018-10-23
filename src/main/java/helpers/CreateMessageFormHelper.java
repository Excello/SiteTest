package helpers;

import component.AbstractComponent;
import data.Message;
import pages.*;

public class CreateMessageFormHelper extends AbstractComponent {

    public MessageList createNewMessage(Message message, MessageList messageList) {

        //Tap 'New Message' button
        CreateMessagePage pageCreateMessagePage = messageList.clickNewMessageButton();

        //Tap 'Create' button
        ViewMessagePage viewMessagePage = pageCreateMessagePage.createMessage(message);

        //Verify that Show Message page is opened
        viewMessagePage.assertMessage(message);

        //Tap 'Message List' button
        messageList = viewMessagePage.clickMessageList();

        return messageList;
    }

    public MessageList editMessage(Message message, Message newMessage, MessageList messageList) {

        EditMessagePage pageEditMessagePage = messageList.openEditMessagePage(message);

        pageEditMessagePage.assertMessageIsCorrect(message);

        ViewMessagePage viewMessagePage = pageEditMessagePage.createMessage(newMessage);
        viewMessagePage.assertMessage(newMessage);

        messageList = viewMessagePage.clickMessageList();

        return messageList;
    }
    //TODO Я бы просто в ViewMessagePage добавил метод assertMessage(Message).
    //TODO при  таком раскладе зачем нам этот метод? Просто вызывай viewMessagePage.assertMessage(message) там где надо
}
