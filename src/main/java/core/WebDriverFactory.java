package core;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class WebDriverFactory {

    private RemoteWebDriver activeDriver;

    private Map<String, RemoteWebDriver> drivers = new HashMap<String, RemoteWebDriver>();

    private boolean allowMultipleBrowsers;

    private Browser defaultBrowser;

    private static WebDriverFactory instance;


    public static enum Browser {
        CHROME;
    }

    public static void init() {
        init(true);
    }

    public static void init(boolean allowMultiple) {
        init(allowMultiple, Browser.CHROME);
    }

    public static void init(boolean allowMultiple, Browser defaultBrowser) {
        if (instance == null) instance = new WebDriverFactory(allowMultiple, defaultBrowser);
    }

    private WebDriverFactory(boolean allowMultiple, Browser defaultBrowser) {
        this.allowMultipleBrowsers = allowMultiple;
        this.defaultBrowser = defaultBrowser;
    }

    public static void init(Browser defaultBrowser) {
        init(true, defaultBrowser);
    }

    public static WebDriverFactory instance() {
        init();
        return instance;
    }

    public RemoteWebDriver openNewBrowser(String name) {
        return openNewBrowser(name, defaultBrowser);
    }

    public RemoteWebDriver get() {
        return activeDriver;
    }

    public RemoteWebDriver openNewBrowser(String name, Browser browser) {

        if (activeDriver == null || !allowMultipleBrowsers) {

            switch (browser) {
                case CHROME:
                    File file = new File("utils" + File.separator + "chromedriver.exe");
                    System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
                    activeDriver = new ChromeDriver();
                    break;
                default:
                    file = new File("utils" + File.separator + "chromedriver.exe");
                    System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
                    activeDriver = new ChromeDriver();
            }

            activeDriver.manage().deleteAllCookies();

            drivers.put(name.toLowerCase(), activeDriver);
        }

        return get();
    }

    public RemoteWebDriver get(String name) {
        return drivers.get(name.toLowerCase());
    }
}

