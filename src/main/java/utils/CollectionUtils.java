package utils;

public class CollectionUtils {
    public static boolean contains(String[] array, String what){
        for (String element: array){
            if (element.equalsIgnoreCase(what)){
                return true;
            }
        }
        return false;
    }
}

