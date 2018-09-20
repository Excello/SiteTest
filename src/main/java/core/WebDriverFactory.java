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
 * Factory для управления всеми используемыми web драйверами (браузерами)
 * Реализует Singleton
 */
public class WebDriverFactory {

    /**
     * Текущий активный драйвер
     */
    private RemoteWebDriver activeDriver;
    /**
     * Все используемые драйверы
     */
    private Map<String, RemoteWebDriver> drivers = new HashMap<String, RemoteWebDriver>();
    /**
     * Разрешено ли создание (откртытие) нескольких браузеров
     * //TODO На данный момент сама технология web driver не позволяет открытия множества окон.
     */
    private boolean allowMultipleBrowsers;

    private Browser defaultBrowser;
    /**
     * Получение экземпляра WebDriverFactory
     */
    private static WebDriverFactory _instance;

    /**
     * Типы браузеров
     */
    public static enum Browser {
        /**
         * Internet Explorer
         */
        CHROME;
    }

    /**
     * Инициализация класса по умолчанию (allowMultipleBrowsers =true, defaultBrowser = IE)
     */
    public static void init() {
        init(true);
    }

    /**
     * Инициализация класса с браузером по умолчанию IE
     *
     * @param allowMultiple Разрешить ли открытие нескольких браузеров
     */
    public static void init(boolean allowMultiple) {
        init(allowMultiple, Browser.CHROME);
    }

    /**
     * Инициализация класса
     *
     * @param allowMultiple  Разрешить ли открытие нескольких браузеров
     * @param defaultBrowser Браузер по умолчанию
     */
    public static void init(boolean allowMultiple, Browser defaultBrowser) {
        if (_instance == null) _instance = new WebDriverFactory(allowMultiple, defaultBrowser);
    }

    /**
     * Конструктор
     *
     * @param allowMultiple  Разрешить ли открытие нескольких браузеров
     * @param defaultBrowser Браузер по умолчанию
     */
    private WebDriverFactory(boolean allowMultiple, Browser defaultBrowser) {
        this.allowMultipleBrowsers = allowMultiple;
        this.defaultBrowser = defaultBrowser;
    }

    /**
     * Инициализация класса с разрешением открывать несколько браузеров
     *
     * @param defaultBrowser Браузер по умолчанию
     */
    public static void init(Browser defaultBrowser) {
        init(true, defaultBrowser);
    }

    /**
     * Получение экземпляра класса
     *
     * @return Экземпляр класса
     */
    public static WebDriverFactory instance() {
        init();
        return _instance;
    }

    /**
     * Открывает новый браузер по умолчанию, обратиться к этому браузеру впоследствии можно будет по имени
     *
     * @param name Уникальное имя для создаваемого браузера
     * @return Объект открытого браузера
     * @see #openNewBrowser(String, Browser)
     */
    public RemoteWebDriver openNewBrowser(String name) {
        return openNewBrowser(name, defaultBrowser);
    }

    /**
     * Возвращает текущий активный браузер (последний открытый)
     *
     * @return текущий активный браузер
     */
    public RemoteWebDriver get() {
        return activeDriver;
    }

    /**
     * Открытие нового браузера
     *
     * @param name    Уникальное имя открываемого браузера
     * @param browser Тип браузера
     * @return Объект браузера
     * @remarks Новый браузер будет создан только, если еще ни одного браузера не открыто или если открытие множества браузеров разрешено. Созданный браузер помечается как активный
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
     * Возвращает объект браузера для указанного имени
     *
     * @param name Имя требуемого браузера
     * @return Объект браузера по имени (null если браузер с таким именем не создавался)
     * @remarks Класс не ведет никакого конроля на предмет того, что однажды открытое окно никто не закрыл
     * @see #openNewBrowser(String, Browser)
     */
    public RemoteWebDriver get(String name) {
        return drivers.get(name.toLowerCase());
    }
}

