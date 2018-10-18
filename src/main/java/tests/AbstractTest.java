package tests;


import core.WebDriverFactory;
import logging.TestLogger;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.Environment;

import java.io.File;
import java.io.IOException;
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
    private static File criticalErrorFile = new File("CRITICAL_ERROR.TXT");
    protected static WebDriver driver; //TODO Не статик

    @BeforeSuite
    public static void beforeSuite(ITestContext context) {


        WebDriverFactory.init(false, WebDriverFactory.Browser.CHROME);
        driver = WebDriverFactory.instance().openNewBrowser("default");
        markTestCriticalError();
    }

    @BeforeMethod
    public static void openStartPage(Method method) {
        TestLogger.logMessage("Start method " + method.getName());
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.get(Environment.URL);
    }

    @AfterSuite(alwaysRun = true)
    public static void closeBrowser() {
        driver.close();
        TestLogger.logMessage("Suite ended");
    }

    @AfterMethod(alwaysRun = true)
    public static void verifyTestStatus(ITestResult result) {

        if (TestLogger.hasErrors()) {
            Reporter.log("Test ended with errors.");
        }
    }

    //TODO А что это делает и зачем?
    private static void markTestCriticalError() {
        if (!criticalErrorFile.exists()) {
            boolean result = false;

            try {
                result = criticalErrorFile.createNewFile();
            } catch (IOException e) {
                TestLogger.logError("Can't create file " + criticalErrorFile + ". " + e.getMessage() + "\n" +
                        StringUtils.join(e.getStackTrace(), "\n"));
            }

            if (!result) {
                TestLogger.logError("Can't create file " + criticalErrorFile + ". File has write protection/");
            }
        }
    }
}
