package helpers;

import logging.TestLogger;
import pages.*;

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

        //Message List is displayed
        messageList.isMessageListPageOpened();

        //Create Message
        String [] message = createMessageFormHelper.createNewMessage(headline, text, messageList);
        headline = message[0];
        text = message[1];

        //There is a created object and headline and text fields contains early filled values
        messageList.isMessageIsInList(headline, text);

        return new String[] {headline, text};
    }

    public String[] createTwoMessages(String headline1, String text1, String headline2, String text2) {

        //Message List is displayed
        messageList.isMessageListPageOpened();

        //Create Two Messages
        String[] message = createMessageFormHelper.createTwoMessages(headline1, text1, headline2, text2, messageList);

        headline1 = message[0];
        text1 = message[1];
        headline2 = message[2];
        text2 = message[3];

        //There is a created object and headline and text fields contains early filled values
        messageList.isMessageIsInList(headline1, text1);

        //There is a created object and headline and text fields contains early filled values
        messageList.isMessageIsInList(headline2, text2);

        return new String[] {headline1, text1, headline2, text2};
    }

    public void viewMessage(String headline, String text)
    {
        TestLogger.logMessage("Opening from the list to view the message with the values headline: " + headline + ", text: " + text);

        //Message List is displayed
        messageList.isMessageListPageOpened();

        //View Message
        ShowMessage showMessage =  messageList.openViewMessagePage(headline, text);

        //Assert Headline and Text values
        showMessage.verifyHeadlineValue(headline);
        showMessage.verifyTextValue(text);

        //Tap 'Message List' button
        showMessage.clickMessageList();

        //There is a created object and headline and text fields contains early filled values
        messageList.isMessageIsInList(headline, text);
    }

    public void editMessage(String headline, String text, String newHeadline, String newText) {
        TestLogger.logMessage("Opening from the list to edit the message with the values headline: " + headline + ", text" + text);

        //Message List is displayed
        messageList.isMessageListPageOpened();

        //Enter new Headline and Text
        String[] message = createMessageFormHelper.editMessage(headline, text, newHeadline, newText, messageList);

        newHeadline = message[0];
        newText = message[1];

        //There is a last created object and headline and text fields contains early filled values
        messageList.isMessageIsInList(newHeadline, newText);
    }

    public void deleteMessage(String headline, String text) {
        TestLogger.logMessage("Deleting from the list the message: " + headline + ", text: " + text);

        //Message List is displayed
        messageList.isMessageListPageOpened();

        //Delete Message
        messageList.deleteMessage(headline, text);

        //Check that Message is removed
        messageList.isMessageIsNotInList(headline, text);
    }

    public void createMessageWithoutSaving(String headline, String text) {
        TestLogger.logMessage("Creating message without saving");

        //Message List is displayed
        messageList.isMessageListPageOpened();

        //Create message without saving it
        String[] message = createMessageFormHelper.createMessageWithoutSaving(headline, text, messageList);

        headline = message[0];
        text = message[1];

        //Check that Message is not saved
        messageList.isMessageIsNotInList(headline, text);
    }

    public void signInAnotherUser(String newUserName, String newPassWord, String expected) {
        TestLogger.logMessage("Tap 'Logout' button");

        //Perform logout
        messageList.clickLogOutButton();

        //Sign in as another user
        LoginPage loginPage = new LoginPage();
        loginPage.signIn(newUserName, newPassWord);
        messageList.assertUsername(expected);
    }

    public void verifyAllUsersMessages(String headline1, String text1, String headline2, String text2, String login) {
        TestLogger.logMessage("Verify all users messages");

        //Select checkbox
        messageList.selectAllUsersCheckBox();

        //Verify All Messages
        messageList.isMessageIsInList(headline1, text1);
        messageList.isMessageIsInList(headline2, text2);


        TestLogger.logMessage("Verify " + login + " messages");
        messageList.removeAllUsersCheckBox();

        messageList.isMessageIsInList(headline1, text1);
    }
}
