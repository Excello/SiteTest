package pages;

import component.TableManager;
import elements.ButtonElement;
import elements.InputElement;
import elements.LabelElement;
import logging.TestLogger;
import org.openqa.selenium.By;

import java.util.Objects;

public class EditMessage extends AbstractPage{
    private static final By EDIT_MESSAGE_LABEL = By.linkText("Edit Message");
    private static final By SAVE_BUTTON = By.cssSelector(".save");
    private static final By DELETE_BUTTON = By.cssSelector(".delete");
    private static final By HEADLINE_FIELD = By.id("headline");
    private static final By TEXT_FIELD = By.id("text");


    private LabelElement editMessageLabel() {
        return new LabelElement(driver, EDIT_MESSAGE_LABEL, "Save Button");
    }

    private ButtonElement saveButton() {
        return new ButtonElement(driver, SAVE_BUTTON, "Save Button");
    }

    private ButtonElement deleteButton() {
        return new ButtonElement(driver, DELETE_BUTTON, "Delete Button");
    }

    private InputElement inputHeadline() {
        return new InputElement(driver, HEADLINE_FIELD, "Headline Field");
    }

    private InputElement inputText() {
        return new InputElement(driver, TEXT_FIELD, "Text Field");
    }

    public void isEditPageOpened() {
        isPageOpened(editMessageLabel(), "Edit Message");
    }

    private void clickSave(){
        TestLogger.logMessage("Tap 'Save Button'");
        saveButton().click();
    }

    /*public void verifyHeadlineValue()
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

    public ShowMessage editMessage(String newHeadline, String newText) {
        TestLogger.logMessage("Clearing fields");

        if(Objects.equals(newHeadline, "test")) {
            inputHeadline().clear();
        }

        if(Objects.equals(newHeadline, "test")) {
            inputText().clear();
        }
        inputHeadline().enterValue(newHeadline);
        inputHeadline().assertValue(newHeadline);
        inputText().enterValue(newText);
        inputText().assertValue(newText);
        clickSave();

        ShowMessage showMessage = new ShowMessage();
        showMessage.isShowMessagePageOpened();

        return showMessage;
    }

    /*private String getFieldValue(String fieldName)
    {
        int fieldValue = tableOfFields().findRowIndexWithCellText(fieldNameCol, fieldName);

        return tableOfFields().getCellText(iRow, fieldValueCol);
    }*/
}
