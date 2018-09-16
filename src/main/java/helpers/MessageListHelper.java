package helpers;

import logging.TestLogger;
import pages.AbstractPage;
import pages.EditMessage;
import pages.MessageList;
import pages.ShowMessage;

public class MessageListHelper extends AbstractPage {
    private MessageList messageList;
    private CreateMessageFormHelper createMessageFormHelper;

    public MessageListHelper() {
        messageList = new MessageList();
        createMessageFormHelper = new CreateMessageFormHelper();
    }

    public String[] createMessage() {
        String text = "text";
        String headline = "headline";
        createMessage(headline, text);
        return new String[] {headline, text};
    }

    public String[] createMessage(String headline, String text){

        //������ ������ Message list
        messageList.isMessageListPageOpened();

        String [] message = createMessageFormHelper.createNewMessage(headline, text, messageList);
        headline = message[0];
        text = message[1];

        //� ������ ���������� ��������� ������, � �������� Headline �  Text ���������� ��������, ��������� �� ���� 4
        messageList.isMessageIsInList(headline, text);

        return new String[] {headline, text};
    }

    public String[] createTwoMessages(String headline1, String text1, String headline2, String text2) {
        messageList.isMessageListPageOpened();

        String[] message = createMessageFormHelper.createTwoMessages(headline1, text1, headline2, text2, messageList);

        headline1 = message[0];
        text1 = message[1];
        headline2 = message[2];
        text2 = message[3];

        messageList.isMessageIsInList(headline1, text1);
        messageList.isMessageIsInList(headline2, text2);

        return new String[] {headline1, text1, headline2, text2};
    }

    public void viewMessage(String headline, String text)
    {
        TestLogger.logMessage("Opening from the list to view the message with the values headline: " + headline + ", text: " + text);
        //������ ������ Message list
        messageList.isMessageListPageOpened();
        ShowMessage showMessage =  messageList.viewMessage(headline, text);
        showMessage.verifyHeadlineValue(headline);
        showMessage.verifyTextValue(text);
        showMessage.clickMessageList();
        messageList.isMessageIsInList(headline, text);
    }

    public void editMessage(String headline, String text, String newHeadline, String newText) {
        TestLogger.logMessage("Opening from the list to edit the message with the values headline: " + headline + ", text" + text);

        messageList.isMessageListPageOpened();
        messageList.isMessageIsInList(headline, text);


        EditMessage editMessage = messageList.editMessage(headline, text);
        /*editMessage.verifyHeadlineValue(headline);
        editMessage.verifyHeadlineValue(text);*/

        editMessage.editMessage(newHeadline, newText);

        ShowMessage showMessage = new ShowMessage();
        showMessage.verifyHeadlineValue(newHeadline);
        showMessage.verifyTextValue(newText);
        showMessage.clickMessageList();
        messageList.isMessageIsInList(newHeadline, newText);


        //удалить поля и заполнить их заново
        /*editMessage.clickSave();*/
        /*viewMessage(headline, text);*/
    }

    public void deleteMessage(String headline, String text) {
        TestLogger.logMessage("Deleting from the list the message: " + headline + ", text: " + text);
        //������ ������ Message list
        messageList.isMessageListPageOpened();
        messageList.deleteMessage(headline, text);
        messageList.isMessageIsNotInList(headline, text);
    }

    public void createMessageWithoutSaving(String headline, String text) {
        TestLogger.logMessage("Creating message without saving");

        messageList.isMessageListPageOpened();

        String[] message = createMessageFormHelper.createMessageWithoutSaving(headline, text, messageList);

        messageList.isMessageIsNotInList(headline, text);
    }
}
