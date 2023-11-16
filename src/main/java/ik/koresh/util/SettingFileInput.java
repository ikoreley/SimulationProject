package ik.koresh.util;


import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SettingFileInput {

    private static Map<String, Integer> createMapSetting() {
        Map<String, Integer> settings = new HashMap<>();
        try (InputStream inputStream = SettingFileInput.class.getClassLoader().getResourceAsStream("settings_simulation.txt")) {
            assert inputStream != null;
            Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8);
            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                settings.put(nextLine.split(":")[0], Integer.valueOf(nextLine.split(":")[1]));
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return settings;
    }

    public static Map<String, Integer> getSettingsSimulation(){
        return createMapSetting();
    }

}
