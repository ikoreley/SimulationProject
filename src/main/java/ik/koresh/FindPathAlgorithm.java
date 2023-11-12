package ik.koresh;

import ik.koresh.entites.*;

import java.util.*;

public class FindPathAlgorithm { // todo Неправильно работает выбирает ход или весь путь. Надо думать.

    public static final EntityService entityService = EntityService.getInstance();


    // все соседи доступных ячеек сущности ( Herbivore или Predator)
    public static Map<Coordinate, Set<Coordinate>> allNeighbourCell(Creature creature, Coordinate coordinate, Area area){
        Map<Coordinate, Set<Coordinate>> result = new HashMap<>();

        result.put(coordinate, MoveService.getSquareAvailableForMove(creature, coordinate, area));

        AreaService.mapCoord.forEach((key, value) -> {
            if (MoveService.isSquareAvailableForMove(creature, value, area))
                result.put(value, MoveService.getSquareAvailableForMove(creature, value, area));
        });
        return result;
    }


    public static Coordinate pathMove (Creature creature, Area area){

        Coordinate coordinateStart = creature.coordinate;

        Map<Coordinate, Set<Coordinate>>  neighbour = allNeighbourCell(creature, coordinateStart, area);

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
            for (Coordinate nextNode: nextNodes){
                if (!visited.containsKey(nextNode)){
                    queue.add(nextNode);
                    visited.put(nextNode, curNode);
                    endCoordinate = nextNode;
                    if (!entityService.isSquareEmptyArea(nextNode, area)){
                        if (entityService.getInAllEntity(nextNode, area).getClass() == Herbivore.class && creature.getClass() == Predator.class){

                            break;
                        }
                        if (entityService.getInAllEntity(nextNode, area).getClass() == Grass.class && creature.getClass() == Herbivore.class){

                            break;
                        }
                    }
                }
            }

        }

        LinkedList<Coordinate> path = new LinkedList<>();
        Coordinate tempCoordinate = endCoordinate;

        while (coordinateStart != tempCoordinate){
            path.add(tempCoordinate);
            tempCoordinate = visited.get(tempCoordinate);
        }

        return path.getLast();
    }


}
