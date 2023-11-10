package ik.koresh.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesAreaUtil {
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private PropertiesAreaUtil(){
    }

    private static void loadProperties(){
        final InputStream resource = PropertiesIconsUtil.class.getClassLoader().getResourceAsStream("fieldRowCol.properties");
        try {
            PROPERTIES.load(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Integer get(String key){
        return Integer.parseInt(PROPERTIES.getProperty(key, "10"));
//        return (Integer) PROPERTIES.getOrDefault(key, 10); // it worked and stopped
    }
    public static Properties getProperties(){
        return PROPERTIES;
    }
}
