package tests;


import core.WebDriverFactory;
import logging.TestLogger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.Environment;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public abstract class AbstractTest {
    /*private DriverManager driverManager;
    private WebDriver driver;

    @BeforeTest
    public void beforeTest() {
        driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = driverManager.getDriver();
        driver.get("http://localhost:8080/QulixTeachingSite");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void afterMethod() {
        driverManager.quitDriver();
    }*/

    protected WebDriver driver;

    @BeforeSuite
    public void beforeSuite(ITestContext context) {
        WebDriverFactory.init(false, WebDriverFactory.Browser.CHROME);
        driver = WebDriverFactory.instance().openNewBrowser("default");
    }

    @BeforeMethod
    public void openStartPage(Method method) {
        TestLogger.logMessage("Start method " + method.getName());
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.get(Environment.URL);
    }

    @AfterSuite(alwaysRun = true)
    public void closeBrowser() {
        driver.close();
        TestLogger.logMessage("Suite ended");
    }

    @AfterMethod(alwaysRun = true)
    public void verifyTestStatus(ITestResult result) {

        if (TestLogger.hasErrors()) {
            Reporter.log("Test ended with errors.");
        }
    }

}
