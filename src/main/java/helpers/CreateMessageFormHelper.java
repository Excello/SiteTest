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
    public MessageList createMessageWithoutSaving(Message message, MessageList messageList) {

        //Tap 'New Message' button
        CreateMessagePage pageCreateMessagePage = messageList.clickNewMessageButton();

        //Fill 'Headline' ant 'Text' fields
       /* if (message == null) {
            message = Message.createRandom();
        }*/

        pageCreateMessagePage.fillFieldsWithValues(message);

        //Tap 'Message List' button
        messageList = pageCreateMessagePage.clickMessageList();

        return messageList;//TODO У тебя MessageList возвращается из pageCreateMessagePage.clickMessageList();
    }

    public MessageList editMessage(Message message, Message newMessage, MessageList messageList) {

        EditMessagePage pageEditMessagePage = messageList.openEditMessagePage(message);

        pageEditMessagePage.assertMessageIsCorrect(message);

        /*if (newMessage.getHeadline() == null && newMessage.getText() == null) {
            newMessage = Message.createRandom();
        }*/

        ViewMessagePage viewMessagePage = pageEditMessagePage.fillNewValues(newMessage);
        assertMessage(newMessage, viewMessagePage);

        goToMessageListPage(viewMessagePage);

        return new MessageList(); //TODO У тебя MessageList возвращается из  goToMessageListPage(viewMessagePage);
    }

    //TODO Этот метод реально нужен? Просто нажатие кнопки, чего не вызывать его прямо там где требуется
    private MessageList goToMessageListPage(ViewMessagePage viewMessagePage) {
        viewMessagePage.clickMessageList();
        return new MessageList(); //TODO У тебя MessageList возвращается из viewMessagePage.clickMessageList();
    }


    private void assertMessage(Message message, ViewMessagePage viewMessagePage) {
//TODO Я бы просто в ViewMessagePage добавил метод assertMessage(Message)
        viewMessagePage.assertHeadlineValue(message.getHeadline());
        viewMessagePage.assertTextValue(message.getText());
    }
}
