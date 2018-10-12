package helpers;

import component.AbstractComponent;
import data.Message;
import logging.TestLogger;
import pages.MessageList;
import pages.ViewMessagePage;

public class ViewMessageHelper extends AbstractComponent {
    private ViewMessagePage viewMessagePage;

    public ViewMessageHelper() {
        viewMessagePage = new ViewMessagePage();
    }

    public MessageList assertMessageIsCorrect(Message message) {
        TestLogger.logMessage("Verify correctness of [headline] " + message.getHeadline() + " and [text] " + message.getText() + " values");

        //Assert Headline and Text values
        viewMessagePage.assertHeadlineValue(message.getHeadline());
        viewMessagePage.assertTextValue(message.getText());

        //Return to 'Message List' page
        viewMessagePage.clickMessageList();

        return new MessageList();
    }
}
