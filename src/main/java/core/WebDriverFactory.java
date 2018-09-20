package core;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Factory ��� ���������� ����� ������������� web ���������� (����������)
 * ��������� Singleton
 */
public class WebDriverFactory {

    /**
     * ������� �������� �������
     */
    private RemoteWebDriver activeDriver;
    /**
     * ��� ������������ ��������
     */
    private Map<String, RemoteWebDriver> drivers = new HashMap<String, RemoteWebDriver>();
    /**
     * ��������� �� �������� (���������) ���������� ���������
     * //TODO �� ������ ������ ���� ���������� web driver �� ��������� �������� ��������� ����.
     */
    private boolean allowMultipleBrowsers;

    private Browser defaultBrowser;
    /**
     * ��������� ���������� WebDriverFactory
     */
    private static WebDriverFactory _instance;

    /**
     * ���� ���������
     */
    public static enum Browser {
        /**
         * Internet Explorer
         */
        CHROME;
    }

    /**
     * ������������� ������ �� ��������� (allowMultipleBrowsers =true, defaultBrowser = IE)
     */
    public static void init() {
        init(true);
    }

    /**
     * ������������� ������ � ��������� �� ��������� IE
     *
     * @param allowMultiple ��������� �� �������� ���������� ���������
     */
    public static void init(boolean allowMultiple) {
        init(allowMultiple, Browser.CHROME);
    }

    /**
     * ������������� ������
     *
     * @param allowMultiple  ��������� �� �������� ���������� ���������
     * @param defaultBrowser ������� �� ���������
     */
    public static void init(boolean allowMultiple, Browser defaultBrowser) {
        if (_instance == null) _instance = new WebDriverFactory(allowMultiple, defaultBrowser);
    }

    /**
     * �����������
     *
     * @param allowMultiple  ��������� �� �������� ���������� ���������
     * @param defaultBrowser ������� �� ���������
     */
    private WebDriverFactory(boolean allowMultiple, Browser defaultBrowser) {
        this.allowMultipleBrowsers = allowMultiple;
        this.defaultBrowser = defaultBrowser;
    }

    /**
     * ������������� ������ � ����������� ��������� ��������� ���������
     *
     * @param defaultBrowser ������� �� ���������
     */
    public static void init(Browser defaultBrowser) {
        init(true, defaultBrowser);
    }

    /**
     * ��������� ���������� ������
     *
     * @return ��������� ������
     */
    public static WebDriverFactory instance() {
        init();
        return _instance;
    }

    /**
     * ��������� ����� ������� �� ���������, ���������� � ����� �������� ������������ ����� ����� �� �����
     *
     * @param name ���������� ��� ��� ������������ ��������
     * @return ������ ��������� ��������
     * @see #openNewBrowser(String, Browser)
     */
    public RemoteWebDriver openNewBrowser(String name) {
        return openNewBrowser(name, defaultBrowser);
    }

    /**
     * ���������� ������� �������� ������� (��������� ��������)
     *
     * @return ������� �������� �������
     */
    public RemoteWebDriver get() {
        return activeDriver;
    }

    /**
     * �������� ������ ��������
     *
     * @param name    ���������� ��� ������������ ��������
     * @param browser ��� ��������
     * @return ������ ��������
     * @remarks ����� ������� ����� ������ ������, ���� ��� �� ������ �������� �� ������� ��� ���� �������� ��������� ��������� ���������. ��������� ������� ���������� ��� ��������
     */
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

    /**
     * ���������� ������ �������� ��� ���������� �����
     *
     * @param name ��� ���������� ��������
     * @return ������ �������� �� ����� (null ���� ������� � ����� ������ �� ����������)
     * @remarks ����� �� ����� �������� ������� �� ������� ����, ��� ������� �������� ���� ����� �� ������
     * @see #openNewBrowser(String, Browser)
     */
    public RemoteWebDriver get(String name) {
        return drivers.get(name.toLowerCase());
    }
}

