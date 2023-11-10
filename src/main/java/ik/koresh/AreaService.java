package ik.koresh;


import ik.koresh.entites.*;
import ik.koresh.util.PropertiesAreaUtil;
import ik.koresh.util.PropertiesEntityUtil;
import ik.koresh.util.PropertiesIconsUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class AreaService {

    public static final int row = PropertiesAreaUtil.get("row");
    public static final int col = PropertiesAreaUtil.get("col");

    private static final int countCellUsing = PropertiesEntityUtil.get("percent_ageOfFilledCell");

    public static final EntityService entityService = EntityService.getInstance();


    // генерируем колличества сущностей на поле
    public static Map<String, Integer> generateCountEntityOnArea(){
        Map<String, Integer> countEntities = new HashMap<>();
        int countNull = 0;
        int sumCountEntity = 0;
        for(String entity: PropertiesIconsUtil.getProperties().stringPropertyNames()){
            int temp = PropertiesEntityUtil.get("percent_" + entity);
            if(temp == 0) countNull++;

            countEntities.put(entity, convertPercentInCount(temp));
            sumCountEntity +=temp;
        }

        int finalCountNull = countNull;
        int finalSumCountEntity = sumCountEntity;
        // расчитываем количество сущностям которых нет в настройках по количеству
        countEntities.entrySet().forEach(x-> {
            if (x.getValue() == 0){
                x.setValue(convertPercentInCount((countCellUsing - finalSumCountEntity)/finalCountNull));
            }
        });

        return countEntities;
    }


    // наполняем поле сущностями
    public static void fillingAreaEntities(Area area){
        // создаем мапу с пронумированными координатами всего поля
        Map<Integer, Coordinate> mapCoord = mapNumberingOfCoordinates();
        // создаем лист рандомных номеров координат в пределах всего поля для установки сущностей
        List<Integer> setInt = new ArrayList<>(generateRandomNumber().stream().toList());

        Map<String, Integer> countEntityOnArea = generateCountEntityOnArea();

        countEntityOnArea.forEach((key, value) -> {
            for (int i = 0; i < value; i++) {
                int temp = setInt.remove(0);
                entityService.setEntity(
                        mapCoord.get(temp),
                        createEntityOfReflection(key, mapCoord.get(temp), PropertiesIconsUtil.get(key)),
                        area);
            }
        });

    }


    // список рандомых номеров координат для добавления сущностей на поле
    public static Set<Integer> generateRandomNumber() {

        Set<Integer> setInt = new LinkedHashSet<>(); // LinkedHashSet- как положил так и оставил, просто HashSet отсортирует

        Random random = new Random();

        while (setInt.size() < convertPercentInCount(countCellUsing)) {
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
    private static int convertPercentInCount(int percent){
        return (row * col)*percent/100;
    }


    private static Entity createEntityOfReflection(String className, Coordinate coordinate, Color color){
                try {
                    return (Entity) Class.forName("ik.koresh.entites." + className).getConstructors()[0].newInstance(coordinate, color);
                } catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
                         IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
    }

}
