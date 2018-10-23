/*package helpers;

import component.AbstractComponent;
import data.Message;
import logging.TestLogger;
import pages.CreateMessagePage;
import pages.MessageList;
import pages.ViewMessagePage;

public class ViewMessageHelper extends AbstractComponent {
    private ViewMessagePage viewMessagePage;

    public ViewMessageHelper() {
        viewMessagePage = new ViewMessagePage();
    }

    public void assertMessageIsCorrect(Message message) {
        TestLogger.logMessage("Verify correctness of [headline] " + message.getHeadline() + " and [text] " + message.getText() + " values");

        //Assert Headline and Text values
        viewMessagePage.assertMessage(message);
    }

    //TODO Зачем тут делать assert перед переходом?
    //TODo Вообще не логично
    public MessageList openMessageList() {
        viewMessagePage.clickMessageList();
        return new MessageList();
    }

    //TODO Аналогично методу выше
    *//*public CreateMessagePage editMessage() {
        viewMessagePage.clickNewMessage();
        return new CreateMessagePage();
    }*//*
}*/
