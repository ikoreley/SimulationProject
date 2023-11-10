package ik.koresh.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesEntityUtil {
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private PropertiesEntityUtil(){
    }

    private static void loadProperties(){
        final InputStream resource = PropertiesIconsUtil.class.getClassLoader().getResourceAsStream("settingEntity.properties");
        try {
            PROPERTIES.load(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Integer get(String key){
        return Integer.parseInt(PROPERTIES.getProperty(key, "0"));

    }
    public static Properties getProperties(){
        return PROPERTIES;
    }
}
