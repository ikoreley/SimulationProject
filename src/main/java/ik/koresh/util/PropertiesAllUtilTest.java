package ik.koresh.util;

import ik.koresh.Color;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesAllUtilTest {
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private PropertiesAllUtilTest(){
    }

    private static void loadProperties(){
        final InputStream resource = PropertiesIconsUtil.class.getClassLoader().getResourceAsStream("allUtilTest.properties");
        try {
            PROPERTIES.load(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Integer getArea(String key){
        return Integer.parseInt(PROPERTIES.getProperty(key, "10"));
//        return (Integer) PROPERTIES.getOrDefault(key, 10); // it worked and stopped
    }

    public static Integer getEntity(String key){
        return Integer.parseInt(PROPERTIES.getProperty(key, "0"));

    }

    public static Color getColor(String key){
        return Color.valueOf((String) PROPERTIES.getOrDefault(key, "PURPLE"));

    }
    public static Properties getProperties(){
        return PROPERTIES;
    }
}
