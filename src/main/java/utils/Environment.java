/*
package utils;

import java.io.File;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Environment {
    */
/**
     * ���� ��������
     *//*

    public static final String PROP_FILE = "settings.properties";
    */
/**
     * URL ����������
     *//*

    public static final String URL = getURL();
    */
/**
     * ������� �� ��������� ��� �������� ��������� ��������� ��������
     *//*

    public static final int TIME_OUT_FOR_ELEMENTS = Integer.valueOf(PropertiesManager.get(PROP_FILE, "timeout_for_page_elements"));
    */
/**
     * ������� �� ��������� ��� �������� �������� ��������
     *//*

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
    */
/**
     * ���������� ������� � 0
     *//*

    public static void removeTimeOutForElemets() {
        setTimeOutForElements(0);
    }

    */
/**
     * ���������� ������� � 0
     *//*

    public static void removeTimeOutForPageLoad() {
        setTimeOutForPageLoad(0);
    }
    */
/**
     * ����� ���������� ������� � ������������ � �������� � ����������
     *//*

    public static void resetTimeOutForPageElemets() {
        setTimeOutForElements(TIME_OUT_FOR_ELEMENTS);
    }

    */
/**
     * ����� ���������� ������� � ������������ � �������� � ����������
     *//*

    public static void resetTimeOutForPageLoad() {
        setTimeOutForPageLoad(TIME_OUT_FOR_PAGE_LOAD);
    }
    */
/**
     * ����� ���������� �������
     *
     * @param seconds ������� � ��������
     *//*

    public static void setTimeOutForElements(int seconds) {
        WebDriverFactory.instance().get().manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    */
/**
     * ����� ���������� �������
     *
     * @param seconds ������� � ��������
     *//*

    public static void setTimeOutForPageLoad(int seconds) {
        WebDriverFactory.instance().get().manage().timeouts().pageLoadTimeout(seconds, TimeUnit.SECONDS);
    }
    */
/**
     * ���������� ���������������� ������
     *
     * @return ���������������� ������
     *//*

    public static String generateUniqueString() {
        return new Date().getTime() + "_" + System.getenv("COMPUTERNAME");
    }
}

*/
