package helpers;

import component.AbstractComponent;
import data.Message;
import pages.*;

//TODO Общее замечание. Я бы во всех этих методах возвращал не сообщение, а страницу
//TODO Чисто технически все эти методы так или иначе меняют итоговую страницу (да, она остается та же что была изначально, но формально messageList на входе и страница списка на выходе - разные страницы)
public class CreateMessageFormHelper extends AbstractComponent {

    public ViewMessagePage createNewMessage(Message message, CreateMessagePage createMessagePage) {

        //Tap 'New Message' button
        //CreateMessagePage pageCreateMessagePage = messageList.clickNewMessageButton();

        //Fill 'Headline' ant 'Text' fields
        /*if (message == null) {
            message = Message.createRandom();
        }*/

        //Tap 'Create' button
        ViewMessagePage viewMessagePage;
        viewMessagePage = createMessagePage.createMessage(message);

        //Verify that Show Message page is opened
        assertMessage(message, viewMessagePage);

        //Tap 'Message List' button
        //viewMessagePage.clickMessageList();

        return new ViewMessagePage();
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

        pageCreateMessagePage.fillValues(message);

        //Tap 'Message List' button
        pageCreateMessagePage.clickMessageList();

        return new MessageList();
    }

    //TODO А смысл? Лучше дважды вызвать createNewMessage


    public MessageList editMessage(Message message, Message newMessage, MessageList messageList) {

        EditMessagePage pageEditMessagePage = messageList.openEditMessagePage(message);

        pageEditMessagePage.assertMessageIsCorrect(message);

        /*if (newMessage.getHeadline() == null && newMessage.getText() == null) {
            newMessage = Message.createRandom();
        }*/

        ViewMessagePage viewMessagePage = pageEditMessagePage.fillNewValues(newMessage);
        assertMessage(newMessage, viewMessagePage);

        goToMessageListPage(viewMessagePage);

        return new MessageList();
    }

    private MessageList goToMessageListPage(ViewMessagePage viewMessagePage) {
        viewMessagePage.clickMessageList();
        return new  MessageList();
    }

    //TODO Не нравится название метода, не отражает сути
    private void assertMessage(Message message, ViewMessagePage viewMessagePage) {
        //TODO Метод assert должен вызывать методы assert. Verify - это мягкая проверка, assert - жесткая
        viewMessagePage.assertHeadlineValue(message.getHeadline());
        viewMessagePage.assertTextValue(message.getText());
    }
}
