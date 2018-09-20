package helpers;

import logging.TestLogger;
import pages.*;
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
        if(headline1 == null)  headline1 = "headline" + Environment.generateUniqueString();
        if(text1 == null)  text1 = "text" + Environment.generateUniqueString();

        //Tap 'Create' button
        ShowMessage showMessage;
        showMessage = pageCreateMessage.createMessage(headline1, text1);

        //Verify that Show Message page is opened
        assertShowMessagePage(headline1, text1, showMessage);

        //Tap 'New Message' button
        showMessage.clickNewMessage();

        //Fill 'Headline' ant 'Text' fields
        if(headline2 == null)  headline2 = "headline" + Environment.generateUniqueString();
        if(text2 == null)  text2 = "text" + Environment.generateUniqueString();

        //Tap 'Create' button
        pageCreateMessage.createMessage(headline2, text2);

        //Verify that Show Message page is opened
        assertShowMessagePage(headline2, text2, showMessage);

        //Tap 'Message List' button
        showMessage.clickMessageList();

        return new String[] {headline1, text1, headline2, text2};
    }

    public String[] editMessage(String headline, String text, String newHeadline, String newText, MessageList messageList) {

        EditMessage pageEditMessage = messageList.openEditMessagePage(headline, text);


        if(newHeadline == null)  newHeadline = "headline" + Environment.generateUniqueString();
        if(newText == null)  newText = "text" + Environment.generateUniqueString();

        ShowMessage showMessage = pageEditMessage.editMessage(newHeadline, newText);
        assertShowMessagePage(newHeadline, newText, showMessage);

        showMessage.clickMessageList();

        return new String[] {newHeadline, newText};
    }



    private void assertShowMessagePage(String headline, String text, ShowMessage showMessage){
        showMessage.verifyHeadlineValue(headline);
        showMessage.verifyTextValue(text);
    }
}
