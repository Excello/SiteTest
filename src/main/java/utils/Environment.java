package utils;

import java.util.Date;

/**
 * ��������� �����
 */
public class Environment {

    public static final String PROP_FILE = "settings.properties";

    public static final String URL = getURL();

    public static final int TIME_OUT_FOR_ELEMENTS = Integer.valueOf(PropertiesManager.get(PROP_FILE, "timeout_for_page_elements"));

    public static final int TIME_OUT_FOR_PAGE_LOAD = Integer.valueOf(PropertiesManager.get(PROP_FILE, "timeout_for_page_load"));

    private static String getURL() {
        String url = PropertiesManager.get(PROP_FILE, "url");

        if (url == null || url.equals("")) {
            throw new RuntimeException("URL not specified! Current value: " + url);
        }

        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }

        return url;
    }

    public static String generateUniqueString() {
        return new Date().getTime() + "_" + System.getenv("COMPUTERNAME");
    }
}
