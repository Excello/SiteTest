package helpers;

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

    private void assertMessageIsCorrect(Message message) {
        TestLogger.logMessage("Verify correctness of [headline] " + message.getHeadline() + " and [text] " + message.getText() + " values");

        //Assert Headline and Text values
        viewMessagePage.assertHeadlineValue(message.getHeadline());
        viewMessagePage.assertTextValue(message.getText());
    }

    //TODO Зачем тут делать assert перед переходом?
    //TODo Вообще не логично
    public MessageList openMessageList(Message message) {
        assertMessageIsCorrect(message);

        TestLogger.logMessage("Tap 'Message List' button");
        viewMessagePage.clickMessageList();
        return new MessageList();
    }

    //TODO Аналогично методу выше
    public CreateMessagePage createNewMessage(Message message) {
        assertMessageIsCorrect(message);

        TestLogger.logMessage("Tap 'Create' button");
        viewMessagePage.clickNewMessage();
        return new CreateMessagePage();
    }
}
