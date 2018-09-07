import core.AbstractTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test3 extends AbstractTest {
    @Test(description = "Case 3. Creating and editing of message")
    @Parameters({"Login", "Password", "HeadlineValue", "TextValue"})
    public void test(String login, String password, String headline, String text) {

    }
}
