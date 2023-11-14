package ik.koresh.entites;

import ik.koresh.Area;
import ik.koresh.AreaService;
import ik.koresh.Coordinate;

import java.util.*;

public class MoveService {

    private static final EntityService entityService = EntityService.getInstance();

    private static final Set<Coordinate> creatureMoves = getCreatureMoves();

    private static final Area area = entityService.getArea();

    public static void move() {

    }




    // Ход сущности
    public static void moveCreature(Creature creature, Coordinate to){
//        Entity creature = entityService.getInAllEntity(from, area);
//        entityService.removeEntity(creature.coordinate, area);
        entityService.setEntityOnAreaFill(to, creature);
    }

    private static Coordinate possibleMove(Coordinate from, Coordinate to){
        return new Coordinate(from.x()+ to.x(), from.y()+ to.y());
    }

    private static boolean isPossibleMove(Coordinate from, Coordinate to){
        return AreaService.mapCoord.containsValue(possibleMove(from, to));
    }



    private static Set<Coordinate> getCreatureMoves() {
        return new HashSet<>(Arrays.asList(
                //вверх-вниз
                new Coordinate(1, 0),
                new Coordinate(-1, 0),

                // влево вправо
                new Coordinate(0, 1),
                new Coordinate(0, -1)));

        // по диагонали
//                new CoordinatesShift(1, 1),
//                new CoordinatesShift(-1, 1),
//                new CoordinatesShift(1, -1),
//                new CoordinatesShift(-1, -1)));
    }

    public static boolean isSquareAvailableForMove(Creature creature, Coordinate coordinateTo){
        if (entityService.isSquareEmptyArea(coordinateTo)) return true;
        else if (creature.getClass() == Herbivore.class) return entityService.getInAllEntity(coordinateTo).getClass() == Grass.class;
        else return entityService.getInAllEntity(coordinateTo).getClass() == Herbivore.class;
    }


    public static Set<Coordinate> getSquareAvailableForMove(Creature creature, Coordinate coordinate){
        Set<Coordinate> result = new HashSet<>();

        for (Coordinate coord: getCreatureMoves()){
            if(isPossibleMove(coordinate, coord)){
                Coordinate newCoordinate = possibleMove(coordinate, coord);

                if (isSquareAvailableForMove(creature, newCoordinate)){
                    result.add(newCoordinate);
                }
            }
        }
        return result;
    }

    // Создаем очередь для хода сущностей
    public static Queue<Creature>  queueOfCreatureForMove(){
        Queue<Creature> queue = new LinkedList<>();
        final Map<Coordinate, Entity> coordinatesOfActiveAnimal = area.getMapCreatureEntity();
        final List<Creature> animals = coordinatesOfActiveAnimal.values().stream()
                .map(entity -> (Creature) entity)
                .toList();
        queue.addAll(animals);

        return queue;
    }


}
