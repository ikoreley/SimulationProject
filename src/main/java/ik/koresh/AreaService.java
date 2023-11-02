package ik.koresh;


import ik.koresh.entites.*;

import java.util.*;

public class AreaService {

    public static final int row = SettingFileInput.getSettingsSimulation().get("sizeFieldRow");
    public static final int col = SettingFileInput.getSettingsSimulation().get("sizeFieldCol");

    public static final int countCellUsing = convertPersentInCount(SettingFileInput.getSettingsSimulation().get("percentageOfFilledCell"));

    public static final EntityService entityService = EntityService.getInstance();




    // наполняем поле сущностями
    public static void fillingAreaEntities(Area area){
        int countHerbivore = convertPersentInCount(SettingFileInput.getSettingsSimulation().get("percentHerbivore"));
        int countPredator = convertPersentInCount(SettingFileInput.getSettingsSimulation().get("percentPredator"));
        int countGrass = convertPersentInCount(SettingFileInput.getSettingsSimulation().get("percentGrass"));
        int countRock = (countCellUsing - (countHerbivore + countPredator + countGrass))/2;

        // создаем лист рандомных номеров координат в пределах всего поля для установки сущностей
        List<Integer> setInt = new ArrayList<>(generateRandomNumber().stream().toList());

        // создаем мапу с пронумированными координатами всего поля
        Map<Integer, Coordinate> mapCoord = mapNumberingOfCoordinates();

        for (int i=0; i < countPredator; i++){
            int temp = setInt.remove(0);
            entityService.setEntity(mapCoord.get(temp), new Predator(mapCoord.get(temp), Color.RED), area);
        }

        for (int i=0; i < countHerbivore; i++){
            int temp = setInt.remove(0);
            entityService.setEntity(mapCoord.get(temp), new Herbivore(mapCoord.get(temp), Color.BLUE), area);
        }

        for (int i=0; i < countGrass; i++){
            int temp = setInt.remove(0);
            entityService.setEntity(mapCoord.get(temp), new Grass(mapCoord.get(temp), Color.GREEN), area);
        }

        for (int i=0; i < countRock; i++){
            int temp = setInt.remove(0);
            entityService.setEntity(mapCoord.get(temp), new Rock(mapCoord.get(temp), Color.BLACK), area);
        }

        while (!setInt.isEmpty()){
            int temp = setInt.remove(0);
            entityService.setEntity(mapCoord.get(temp), new Tree(mapCoord.get(temp), Color.YELLOW), area);
        }

    }


    // список рандомых номеров координат для добавления сущностей на поле
    public static Set<Integer> generateRandomNumber() {

        Set<Integer> setInt = new LinkedHashSet<>(); // LinkedHashSet- как положил так и оставил, просто HashSet отсортирует

        Random random = new Random();

        while (setInt.size() < countCellUsing) {
            setInt.add(random.nextInt(row*col) +1);
        }

        return setInt;
    }

    // Нумеруем координаты поля
    public static Map<Integer, Coordinate> mapNumberingOfCoordinates(){
        Map<Integer, Coordinate> intCoord = new HashMap<>();
        int count = 1;
        for(int x = 0; x<=row; x++){
            for (int y = 0; y<=col; y++){
                intCoord.put(count, new Coordinate(x, y));
                count +=1;
            }
        }
        return intCoord;
    }

    // конвертируем процент в колличество в зависимости от размера поля
    public static int convertPersentInCount(int persent){
        return (row * col)*persent/100;
    }

}
