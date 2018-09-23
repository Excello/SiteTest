package pages;

import elements.BaseElement;
import elements.LabelElement;
import org.openqa.selenium.By;

//TODO CreateMessagePage
//TODO Create и Edit message pages are almost same and should have own super class
public class CreateMessagePage extends CreateAndEditPage {

    private static final By CREATE_MESSAGE_LABEL = By.xpath("//H1[text()= 'Create Message']");

    protected CreateMessagePage(BaseElement identifyElementLocator, String formName) {
        super(identifyElementLocator, formName);
        this.createMessageLabel();
    }

    public LabelElement createMessageLabel() {
        return new LabelElement(driver, CREATE_MESSAGE_LABEL, "Create Message Label");
    }

    public void isCreateMessagePageOpened() {
        assertPageOpened();
    }
}
