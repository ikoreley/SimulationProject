package ik.koresh.util;

import ik.koresh.Color;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesIconsUtil {
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private PropertiesIconsUtil(){
    }

    private static void loadProperties(){
        final InputStream resource = PropertiesIconsUtil.class.getClassLoader().getResourceAsStream("icons.properties");
        try {
            PROPERTIES.load(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Color get(String key){
        return Color.valueOf((String) PROPERTIES.getOrDefault(key, "PURPLE"));

    }
    public static Properties getProperties(){
        return PROPERTIES;
    }
}
