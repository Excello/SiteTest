package helpers;

import component.AbstractComponent;
import data.Message;
import pages.CreateMessagePage;
import pages.EditMessagePage;
import pages.MessageList;
import pages.ViewMessagePage;

public class CreateMessageFormHelper extends AbstractComponent {

    public MessageList createNewMessage(Message message, MessageList messageList) {

        //Tap 'New Message' button
        CreateMessagePage pageCreateMessagePage = messageList.clickNewMessageButton();

        //Tap 'Create' button
        ViewMessagePage viewMessagePage;
        viewMessagePage = pageCreateMessagePage.createMessage(message);

        //Verify that Show Message page is opened
        assertMessage(message, viewMessagePage);

        //Tap 'Message List' button
        messageList = viewMessagePage.clickMessageList();

        return messageList;
    }

    public MessageList editMessage(Message message, Message newMessage, MessageList messageList) {

        EditMessagePage pageEditMessagePage = messageList.openEditMessagePage(message);

        pageEditMessagePage.assertMessageIsCorrect(message);

        pageEditMessagePage.clearFields();

        ViewMessagePage viewMessagePage = pageEditMessagePage.createMessage(newMessage);
        assertMessage(newMessage, viewMessagePage);

        messageList = viewMessagePage.clickMessageList();

        return messageList;
    }

    private void assertMessage(Message message, ViewMessagePage viewMessagePage) {
//TODO Я бы просто в ViewMessagePage добавил метод assertMessage(Message).
        //TODO при  таком раскладе зачем нам этот метод? Просто вызывай viewMessagePage.assertMessage(message) там где надо
        viewMessagePage.assertMessage(message);
    }
}
