package utils;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Класс для получения значений из файла properties
 * Файл должен содержать значения параметров в формате: название_параметра=значение
 */
public class PropertiesManager {

    /**
     * Загруженные параметры
     */
    private static Map<String, Properties> runProps = new HashMap<String, Properties>();

    /**
     * Получение параметра
     *
     * @param file      Название файла (путь к файлу с пареметрами)
     * @param parameter Название параметра в файле
     * @return значение параметра
     */
    public static String get(String file, String parameter) {
        if (!runProps.containsKey(file)) {
            try {
                runProps.put(file, getProperties(file));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return runProps.get(file).getProperty(parameter);
    }

    private static Properties getProperties(String file) throws Exception {
        Properties prop = new Properties();
        prop.load(new FileReader(file));
        return prop;
    }
}
