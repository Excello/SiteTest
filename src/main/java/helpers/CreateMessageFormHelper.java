package helpers;

import pages.AbstractPage;
import pages.CreateMessage;
import pages.MessageList;
import pages.ShowMessage;

public class CreateMessageFormHelper extends AbstractPage {
    public String[] createNewMessage(String headline, String text, MessageList messageList){

        //������ New Message
        CreateMessage pageCreateMessage = messageList.clickNewMessageButton();

        //��������� ���� Headline � Text
        //������ Create
        if(headline == null)  headline = "headline" + "test";
        if(text == null)  text = "text" + "test";

        ShowMessage showMessage;
        showMessage = pageCreateMessage.createMessage(headline, text);

        assertShowMessagePage(headline, text, showMessage);

        showMessage.clickMessageList();

        return new String[] {headline, text};
    }

    public String[] createMessageWithoutSaving(String headline, String text, MessageList messageList) {

        CreateMessage pageCreateMessage = messageList.clickNewMessageButton();

        if(headline == null) headline = "headline" + "test";
        if(text == null) text = "text" + "test";

        messageList = pageCreateMessage.createMessageWithoutSaving(headline, text);

        return new String[] {headline, text};
    }

    public String[] createTwoMessages(String headline1, String text1, String headline2, String text2, MessageList messageList) {
        //������ New Message
        CreateMessage pageCreateMessage = messageList.clickNewMessageButton();

        //��������� ���� Headline � Text
        //������ Create
        if(headline1 == null)  headline1 = "headline" + "test1";
        if(text1 == null)  text1 = "text" + "test1";

        ShowMessage showMessage;
        showMessage = pageCreateMessage.createMessage(headline1, text1);

        assertShowMessagePage(headline1, text1, showMessage);

        showMessage.clickNewMessage();

        if(headline2 == null)  headline2 = "headline" + "test2";
        if(text2 == null)  text2 = "text" + "test2";

        pageCreateMessage.createMessage(headline2, text2);

        assertShowMessagePage(headline2, text2, showMessage);
        showMessage.clickMessageList();

        return new String[] {headline1, text1};
    }



    private void assertShowMessagePage(String headline, String text, ShowMessage showMessage){
        showMessage.verifyHeadlineValue(headline);
        showMessage.verifyTextValue(text);
    }
}
