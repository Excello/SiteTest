package pages;

import org.openqa.selenium.By;

public class CreateMessagePage extends MessagePage {
    private static final By CREATE_MESSAGE_LABEL = By.xpath("//H1[text()= 'Create Message']");

    public CreateMessagePage() {
        super(CREATE_MESSAGE_LABEL, "Create Message");
    }

}


