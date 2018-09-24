package pages;

import org.openqa.selenium.By;

//TODO CreateMessagePage
//TODO Create и Edit message pages are almost same and should have own super class
public class CreateMessagePage extends CreateAndEditPage {
    private static final By CREATE_MESSAGE_LABEL = By.xpath("//H1[text()= 'Create Message']");

    public CreateMessagePage() {
        super(CREATE_MESSAGE_LABEL, "Create Message");
    }

    public void isCreateMessagePageOpened() {
        assertPageOpened();
    }
}
/*public LabelElement createMessageLabel() {
        return new LabelElement(driver, CREATE_MESSAGE_LABEL, "Create Message Label");
}*/


