package pages;

import elements.LabelElement;
import logging.TestLogger;
import org.openqa.selenium.By;

//TODO EditMessagePage
public class EditMessagePage extends CreateAndEditPage {
    private static final By EDIT_MESSAGE_LABEL = By.xpath("//H1[text()='Edit Message']");

    private LabelElement editMessageLabel() {
        return new LabelElement(driver, EDIT_MESSAGE_LABEL, "Save Button");
    }

    public void isEditPageOpened() {
        assertPageOpened(editMessageLabel(), "Edit Message");
    }

   /* public void verifyHeadlineValue()
    {
        inputHeadline().assertValue(headline);
    }

    public void verifyTextValue(String expected)
    {
        verifyFieldValue("Text", expected);
    }

    private void verifyFieldValue(String fieldName, String expected)
    {
        TestLogger.logMessage("���������,  ��� ���� " + fieldName + " ����� �������� " + expected);
        String value = getFieldValue(fieldName);

        if(value.equalsIgnoreCase(expected)){
            TestLogger.logMessage("Field \"" + fieldName +  "\" has expected value: " + expected);
        } else{
            TestLogger.logError("Field \"" + fieldName + "\" has value:" + value +", which is not expected( " + expected + ")");
        }
    }*/

    /*public void clearFields(){
        TestLogger.logMessage("Clearing fields");
        inputHeadline().clear();
        inputText().clear();
    }*/

    public void assertMessage(String headline, String text) {
        TestLogger.logMessage("Check that [Headline] " + headline + " and [Text] " + text + " values which entered in previous step were displayed");

        if (headline != null) {
            inputHeadline().assertValue(headline);
            TestLogger.logMessage("Clearing [Headline] " + headline + " value");
            inputHeadline().clear();
        }

        if (text != null) {
            inputText().assertValue(text);
            TestLogger.logMessage("Clearing [Text] " + text + " value");
            inputText().clear();
        }
    }

  /*  public ViewMessagePage editMessage(String newHeadline, String newText) {

        if (newHeadline == null) {
            newHeadline = "headline" + Environment.generateUniqueString();
        }
        if (newText == null) {
            newText = "text" + Environment.generateUniqueString();
        }

        inputHeadline().enterValue(newHeadline);
        inputHeadline().assertValue(newHeadline);
        inputText().enterValue(newText);
        inputText().assertValue(newText);
        clickSave();

        ViewMessagePage viewMessagePage = new ViewMessagePage();
        viewMessagePage.assertViewMessagePageOpened();

        return viewMessagePage;
    }*/

  /*  private String getFieldValue(String fieldName)
    {
        int fieldValue = tableOfFields().findRowIndexWithCellText(fieldNameCol, fieldName);

        return tableOfFields().getCellText(iRow, fieldValueCol);
    }*/
}
