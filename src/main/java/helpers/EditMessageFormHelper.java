/*
package helpers;

import pages.*;

public class EditMessageFormHelper extends AbstractPage {
    public String[] editMessage(String headline, String text){

        //�������� ����
        EditMessage editMessage = new EditMessage();
        editMessage.clearFields();

        //��������� ���� Headline � Text
        //������ Create
        if(headline ==null)  headline = "headline"+ "headline";
        if(text ==null)  text = "text" + "text";

        ShowMessage showMessage;
        showMessage = pageCreateMessage.createMessage(headline, text);

        assertShowMessagePage(headline, text, showMessage);

        showMessage.clickMessageList();

        return new String[] {headline, text};
    }

    public void assertShowMessagePage(String headline, String text, EditMessage editMessage){
        editMessage.verifyHeadlineValue(headline);
        editMessage.verifyTextValue(text);
    }
}
*/
