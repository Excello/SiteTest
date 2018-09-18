package core;

import logging.TestLogger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class ChromeDriverManager extends DriverManager {

    private ChromeDriverService chService;

    private static ChromeDriverManager instance;

    private static void init() {
        if (instance == null) instance = new ChromeDriverManager();
    }

    public static DriverManager instance() {
        init();
        return instance;
    }

    @Override
    protected void startService() {
        if (null == chService) {
            try {
                chService = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File("D://automation/chromedriver.exe"))
                        .usingAnyFreePort()
                        .build();
                chService.start();
            } catch (Exception ex) {
                TestLogger.logError("Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    protected void stopService() {
        if (null != chService && chService.isRunning())
            chService.stop();
    }

    @Override
    public void createDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("start-maximized");
        options.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(chService, options);
        driver.manage().deleteAllCookies();
    }
}
