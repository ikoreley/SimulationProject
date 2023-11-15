package ik.koresh;

import ik.koresh.entites.*;

import java.util.*;

public class FindPathAlgorithm { // todo: не работает правильно

    public static final EntityService entityService = EntityService.getInstance();
    Area area = entityService.getArea();


    // все соседи доступных ячеек сущности ( Herbivore или Predator)
    public static Map<Coordinate, Set<Coordinate>> allNeighbourCell(Creature creature, Coordinate coordinate) {
        Map<Coordinate, Set<Coordinate>> result = new HashMap<>();

        result.put(coordinate, MoveService.getSquareAvailableForMove(creature, coordinate));

        AreaService.mapCoord.forEach((key, value) -> {
            if (MoveService.isSquareAvailableForMove(creature, value))
                result.put(value, MoveService.getSquareAvailableForMove(creature, value));
        });
        return result;
    }


    public static Coordinate pathMove(Creature creature) {

        Coordinate coordinateStart = creature.coordinate;

        Map<Coordinate, Set<Coordinate>> neighbour = allNeighbourCell(creature, coordinateStart);

        Map<Coordinate, Coordinate> visited = new LinkedHashMap<>();

        visited.put(coordinateStart, null);

        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(coordinateStart);

        Coordinate curNode;

        Coordinate endCoordinate = new Coordinate(0, 0);

        while (!queue.isEmpty()) {
            curNode = queue.poll();

//            Set<Coordinates> nextNodes = area.getCreature(coordinateStart).getAvailableMoveCell(area, curNode);
            Set<Coordinate> nextNodes = neighbour.get(curNode);
            for (Coordinate nextNode : nextNodes) {
                if (!visited.containsKey(nextNode)) {
                    queue.add(nextNode);
                    visited.put(nextNode, curNode);
                    endCoordinate = nextNode;
                    if (!entityService.isSquareEmptyArea(nextNode)) {
                        if (entityService.getInAllEntity(nextNode).getClass() == Herbivore.class && creature.getClass() == Predator.class) {
                            queue.clear(); // встречаем подходящю клетку очищаем очередь чтоб дальше не искать
                        }
                        if (entityService.getInAllEntity(nextNode).getClass() == Grass.class && creature.getClass() == Herbivore.class) {
                            queue.clear(); // встречаем подходящю клетку очищаем очередь чтоб дальше не искать
                        }
                    }

                }
            }

        }

        LinkedList<Coordinate> path = new LinkedList<>();
        Coordinate tempCoordinate = endCoordinate;

        while (coordinateStart != tempCoordinate) {
            path.add(tempCoordinate);
            tempCoordinate = visited.get(tempCoordinate);
        }
        System.out.println(path);
        return path.getLast();
    }


}
