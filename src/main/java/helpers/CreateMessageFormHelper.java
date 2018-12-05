package helpers;

import component.AbstractComponent;
import data.Message;
import pages.CreateMessagePage;
import pages.EditMessagePage;
import pages.MessageList;
import pages.ViewMessagePage;

public class CreateMessageFormHelper extends AbstractComponent {

    public static MessageList createNewMessage(Message message, MessageList messageList) {

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

    public static MessageList editMessage(Message message, Message newMessage, MessageList messageList) {

        EditMessagePage pageEditMessagePage = messageList.openEditMessagePage(message);

        pageEditMessagePage.assertMessageIsCorrect(message);

        ViewMessagePage viewMessagePage = pageEditMessagePage.createMessage(newMessage);
        viewMessagePage.assertMessage(newMessage);

        messageList = viewMessagePage.clickMessageList();

        return messageList;
    }
}
