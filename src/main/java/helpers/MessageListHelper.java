package helpers;

import logging.TestLogger;
import pages.AbstractPage;
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

        //Открыт список Message list
        messageList.isPageOpened();

        String [] message = createMessageFormHelper.createNewMessage(headline, text, messageList);
        headline = message[0];
        text = message[1];

        //В списке содержится созданный объект, в колонках Headline и  Text отображены значения, введенные на шаге 4
        messageList.isMessageIsInList(headline, text);

        return new String[] {headline, text};
    }

    public void viewMessage(String headline, String text)
    {
        TestLogger.logMessage("Opening from the list to view the message with the values headline:" + headline + ", text: " + text);
        //Открыт список Message list
        messageList.isPageOpened();
        ShowMessage showMessage =  messageList.viewMessage(headline, text);
        showMessage.verifyHeadlineValue(headline);
        showMessage.verifyTextValue(text);
        showMessage.clickMessageList();
        messageList.isMessageIsInList(headline, text);
    }
}
