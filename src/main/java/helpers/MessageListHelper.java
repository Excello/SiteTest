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

    public String [] createMessage(String headline, String text){

        //������ ������ Message list
        messageList.isPageOpened();

        String [] message = createMessageFormHelper.createNewMessage(headline, text, messageList);
        headline = message[0];
        text = message[1];

        //� ������ ���������� ��������� ������, � �������� Headline �  Text ���������� ��������, ��������� �� ���� 4
        messageList.isMessageIsInList(headline, text);

        return new String[] {headline, text};
    }

    public void viewMessage(String headline, String text)
    {
        TestLogger.logMessage("Opening from the list to view the message with the values headline: " + headline + ", text: " + text);
        //������ ������ Message list
        messageList.isPageOpened();
        ShowMessage showMessage =  messageList.viewMessage(headline, text);
        showMessage.verifyHeadlineValue(headline);
        showMessage.verifyTextValue(text);
        showMessage.clickMessageList();
        messageList.isMessageIsInList(headline, text);
    }

    public void editMessage(String headline, String text, String newHeadline, String newText) {
        TestLogger.logMessage("Opening from the list to edit the message with the values headline: " + headline + ", text" + text);

        messageList.isPageOpened();
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
        messageList.isPageOpened();
        messageList.tapDeleteButton();
    }
}
