package helpers;

import pages.AbstractPage;
import pages.CreateMessage;
import pages.MessageList;
import pages.ShowMessage;
import utils.Environment;

public class CreateMessageFormHelper extends AbstractPage {
    public String[] createNewMessage(String headline, String text, MessageList messageList){

        //Tap 'New Message' button
        CreateMessage pageCreateMessage = messageList.clickNewMessageButton();

        //Fill 'Headline' ant 'Text' fields
        if(headline == null)  headline = "headline" + Environment.generateUniqueString();

        if(text == null)  text = "text" + Environment.generateUniqueString();

        //Tap 'Create' button
        ShowMessage showMessage;
        showMessage = pageCreateMessage.createMessage(headline, text);

        //Verify that Show Message page is opened
        assertShowMessagePage(headline, text, showMessage);

        //Tap 'Message List' button
        showMessage.clickMessageList();

        return new String[] {headline, text};
    }

    public String[] createMessageWithoutSaving(String headline, String text, MessageList messageList) {

        //Tap 'New Message' button
        CreateMessage pageCreateMessage = messageList.clickNewMessageButton();

        //Fill 'Headline' ant 'Text' fields
        if(headline == null) headline = "headline" + Environment.generateUniqueString();
        if(text == null) text = "text" + Environment.generateUniqueString();

        //Tap 'Message List' button
        pageCreateMessage.createMessageWithoutSaving(headline, text);

        return new String[] {headline, text};
    }

    public String[] createTwoMessages(String headline1, String text1, String headline2, String text2, MessageList messageList) {

        //Tap 'New Message' button
        CreateMessage pageCreateMessage = messageList.clickNewMessageButton();

        //Fill 'Headline' ant 'Text' fields
        if(headline1 == null)  headline1 = "headline" + "test1";
        if(text1 == null)  text1 = "text" + "test1";

        //Tap 'Create' button
        ShowMessage showMessage;
        showMessage = pageCreateMessage.createMessage(headline1, text1);

        //Verify that Show Message page is opened
        assertShowMessagePage(headline1, text1, showMessage);

        //Tap 'New Message' button
        showMessage.clickNewMessage();

        //Fill 'Headline' ant 'Text' fields
        if(headline2 == null)  headline2 = "headline" + "test2";
        if(text2 == null)  text2 = "text" + "test2";

        //Tap 'Create' button
        pageCreateMessage.createMessage(headline2, text2);

        //Verify that Show Message page is opened
        assertShowMessagePage(headline2, text2, showMessage);

        //Tap 'Message List' button
        showMessage.clickMessageList();

        return new String[] {headline1, text1};
    }



    private void assertShowMessagePage(String headline, String text, ShowMessage showMessage){
        showMessage.verifyHeadlineValue(headline);
        showMessage.verifyTextValue(text);
    }
}
