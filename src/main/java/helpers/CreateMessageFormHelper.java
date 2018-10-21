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

        return messageList;//TODO почему new? У тебя уже есть экземпляр
    }

    //TODO Плохой метод. Делает слишком много. Хелпер должен помогать что-то создать удалить. А этот хелпер по сути сам в себе работает.
    //TODO Здесь выполнение должно прерываться на странице ViewMessagePage. Не должен этот метод возвращать на список
    //TODO У тебя MessageList возвращается из pageCreateMessagePage.clickMessageList();

    public MessageList editMessage(Message message, Message newMessage, MessageList messageList) {

        EditMessagePage pageEditMessagePage = messageList.openEditMessagePage(message);

        pageEditMessagePage.assertMessageIsCorrect(message);

        pageEditMessagePage.clearFields();

        ViewMessagePage viewMessagePage = pageEditMessagePage.createMessage(newMessage);
        assertMessage(newMessage, viewMessagePage);

        messageList = viewMessagePage.clickMessageList();

        return messageList; //TODO У тебя MessageList возвращается из  goToMessageListPage(viewMessagePage);
    }

    //TODO Этот метод реально нужен? Просто нажатие кнопки, чего не вызывать его прямо там где требуется
    //TODO У тебя MessageList возвращается из viewMessagePage.clickMessageList();

    private void assertMessage(Message message, ViewMessagePage viewMessagePage) {
//TODO Я бы просто в ViewMessagePage добавил метод assertMessage(Message)
        viewMessagePage.assertMessage(message);
    }
}
