package helpers;

import pages.AbstractPage;
import pages.CreateMessage;
import pages.MessageList;
import pages.ShowMessage;

public class CreateMessageFormHelper extends AbstractPage {
    public String[] createNewMessage(String headline, String text, MessageList messageList){

        //Нажать New Message
        CreateMessage pageCreateMessage = messageList.clickNewMessageButton();

        //Заполнить поля Headline и Text
        //Нажать Create
        if(headline == null)  headline = "headline" + "test";
        if(text == null)  text = "text" + "test";

        ShowMessage showMessage;
        showMessage = pageCreateMessage.createMessage(headline, text);

        assertShowMessagePage(headline, text, showMessage);

        showMessage.clickMessageList();

        return new String[] {headline, text};
    }



    public void assertShowMessagePage(String headline, String text, ShowMessage showMessage){
        showMessage.verifyHeadlineValue(headline);
        showMessage.verifyTextValue(text);
    }
}
