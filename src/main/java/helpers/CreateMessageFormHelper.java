package helpers;

import pages.*;
import utils.Environment;

//TODO Общее замечание. Я бы во всех этих методах возвращал не сообщение, а страницу
//TODO Чисто технически все эти методы так или иначе меняют итоговую страницу (да, она остается та же что была изначально, но формально messageList на входе и страница списка на выходе - разные страницы)
public class CreateMessageFormHelper extends AbstractPage {
    //TODO Используй класс Message
    public String[] createNewMessage(String headline, String text, MessageList messageList) {

        //Tap 'New Message' button
        CreateMessage pageCreateMessage = messageList.clickNewMessageButton();

        //Fill 'Headline' ant 'Text' fields
        if (headline == null) headline = "headline" + Environment.generateUniqueString();

        if (text == null) text = "text" + Environment.generateUniqueString();

        //Tap 'Create' button
        ShowMessage showMessage;
        showMessage = pageCreateMessage.createMessage(headline, text);

        //Verify that Show Message page is opened
        assertShowMessagePage(headline, text, showMessage);

        //Tap 'Message List' button
        showMessage.clickMessageList();

        return new String[]{headline, text};
    }

    //TODO Плохой метод. Делает слишком много. Хелпер должен помогать что-то создать удалить. А этот хелпер по сути сам в себе работает.
    //TODO Здесь выполнение должно прерываться на странице ShowMessage. Не должен этот метод возвращать на список
    public String[] createMessageWithoutSaving(String headline, String text, MessageList messageList) {

        //Tap 'New Message' button
        CreateMessage pageCreateMessage = messageList.clickNewMessageButton();

        //Fill 'Headline' ant 'Text' fields
        if (headline == null) headline = "headline" + Environment.generateUniqueString();
        if (text == null) text = "text" + Environment.generateUniqueString();

        //Tap 'Message List' button
        pageCreateMessage.createMessageWithoutSaving(headline, text);

        return new String[]{headline, text};
    }

    //TODO А смысл? Лучше дважды вызвать createNewMessage
    public String[] createTwoMessages(String headline1, String text1, String headline2, String text2, MessageList messageList) {

        //Tap 'New Message' button
        CreateMessage pageCreateMessage = messageList.clickNewMessageButton();

        //Fill 'Headline' ant 'Text' fields
        if (headline1 == null) headline1 = "headline" + Environment.generateUniqueString();
        if (text1 == null) text1 = "text" + Environment.generateUniqueString();

        //Tap 'Create' button
        ShowMessage showMessage;
        showMessage = pageCreateMessage.createMessage(headline1, text1);

        //Verify that Show Message page is opened
        assertShowMessagePage(headline1, text1, showMessage);

        //Tap 'New Message' button
        showMessage.clickNewMessage();

        //Fill 'Headline' ant 'Text' fields
        if (headline2 == null) headline2 = "headline" + Environment.generateUniqueString();
        if (text2 == null) text2 = "text" + Environment.generateUniqueString();

        //Tap 'Create' button
        pageCreateMessage.createMessage(headline2, text2);

        //Verify that Show Message page is opened
        assertShowMessagePage(headline2, text2, showMessage);

        //Tap 'Message List' button
        showMessage.clickMessageList();

        return new String[]{headline1, text1, headline2, text2};
    }

    //TODO Используй класс Message
    public String[] editMessage(String headline, String text, String newHeadline, String newText, MessageList messageList) {

        EditMessage pageEditMessage = messageList.openEditMessagePage(headline, text);


        if (newHeadline == null) newHeadline = "headline" + Environment.generateUniqueString();
        if (newText == null) newText = "text" + Environment.generateUniqueString();

        ShowMessage showMessage = pageEditMessage.editMessage(newHeadline, newText);
        assertShowMessagePage(newHeadline, newText, showMessage);

        showMessage.clickMessageList();

        return new String[]{newHeadline, newText};
    }

    //TODO Не нравится название метода, не отражает сути
    private void assertShowMessagePage(String headline, String text, ShowMessage showMessage) {
        //TODO Метод assert должен вызывать методы assert. Verify - это мягкая проверка, assert - жесткая
        showMessage.verifyHeadlineValue(headline);
        showMessage.verifyTextValue(text);
    }
}
