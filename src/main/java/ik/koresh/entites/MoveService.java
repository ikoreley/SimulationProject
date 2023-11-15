package ik.koresh.entites;

import ik.koresh.Area;
import ik.koresh.AreaService;
import ik.koresh.Coordinate;
import ik.koresh.FindPathAlgorithm;

import java.util.*;

public class MoveService {

    private static final EntityService entityService = EntityService.getInstance();

    private static final Set<Coordinate> creatureMoves = getCreatureMoves();

    private static final Area area = entityService.getArea();


    // Ход сущности
    public static void moveCreature(Creature creature, Coordinate to) {
        entityService.setEntity(to, creature);
    }

    private static Coordinate possibleMove(Coordinate from, Coordinate to) {
        return new Coordinate(from.x() + to.x(), from.y() + to.y());
    }

    private static boolean isPossibleMove(Coordinate from, Coordinate to) {
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

    public static boolean isSquareAvailableForMove(Creature creature, Coordinate coordinateTo) {
        if (entityService.isSquareEmptyArea(coordinateTo)) return true;
        else if (creature.getClass() == Herbivore.class)
            return entityService.getInAllEntity(coordinateTo).getClass() == Grass.class;
        else return entityService.getInAllEntity(coordinateTo).getClass() == Herbivore.class;
    }


    public static Set<Coordinate> getSquareAvailableForMove(Creature creature, Coordinate coordinate) {
        Set<Coordinate> result = new HashSet<>();

        for (Coordinate coord : getCreatureMoves()) {
            if (isPossibleMove(coordinate, coord)) {
                Coordinate newCoordinate = possibleMove(coordinate, coord);

                if (isSquareAvailableForMove(creature, newCoordinate)) {
                    result.add(newCoordinate);
                }
            }
        }
        return result;
    }

    // Создаем очередь для хода Creature
    public static Queue<Creature> queueOfCreatureForMove() {
        Map<Coordinate, Entity> coordinatesOfActiveAnimal = area.getMapCreatureEntity();
        List<Creature> animals = coordinatesOfActiveAnimal.values().stream()
                .map(entity -> (Creature) entity)
                .toList();

        return new LinkedList<>(animals);
    }

    // ход Creature
    public static void move(Creature creature) {
        if (creature.getHP()==0){
            System.out.println("DEAD " + creature.getClass().getSimpleName());
            entityService.removeEntity(creature.coordinate);
        } else {
            Coordinate targetCoordinates = FindPathAlgorithm.pathMove(creature);

            System.out.println(targetCoordinates + " " + creature.coordinate + " " + creature.getClass().getSimpleName());

            if (!entityService.isSquareEmptyArea(targetCoordinates)) {

                Entity entity = entityService.getInAllEntity(targetCoordinates);

                creature.setHP(entity);

                //***
                System.out.println("EAT " + entity.getClass().getSimpleName());
            }
            creature.setHP(-1);
            MoveService.moveCreature(creature, targetCoordinates);
        }
    }


}
