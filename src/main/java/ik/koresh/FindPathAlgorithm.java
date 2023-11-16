package ik.koresh;

import ik.koresh.entites.*;

import java.util.*;

public class FindPathAlgorithm {

    public static final EntityService entityService = EntityService.getInstance();


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

        LinkedList<Coordinate> entityForEat = new LinkedList<>();

        Coordinate coordinateStart = creature.coordinate;

        Map<Coordinate, Set<Coordinate>> neighbour = allNeighbourCell(creature, coordinateStart);

        Map<Coordinate, Coordinate> visited = new LinkedHashMap<>();

        visited.put(coordinateStart, null);

        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(coordinateStart);

        Coordinate curNode;

        while (!queue.isEmpty()) {
            curNode = queue.poll();

            Set<Coordinate> nextNodes = neighbour.get(curNode);
            for (Coordinate nextNode : nextNodes) {
                if (!visited.containsKey(nextNode)) {
                    queue.add(nextNode);
                    visited.put(nextNode, curNode);

                    if (!entityService.isSquareEmptyArea(nextNode)) {
                        if (creature.getClass() == Predator.class && entityService.getInAllEntity(nextNode).getClass() == Herbivore.class) {
                            entityForEat.add(nextNode);
                        }
                        if (creature.getClass() == Herbivore.class && entityService.getInAllEntity(nextNode).getClass() == Grass.class) {
                            entityForEat.add(nextNode);
                        }
                    }

                }
            }

        }

        LinkedList<Coordinate> path = new LinkedList<>();
        int flag = 0;
        for (Coordinate coordEnd : entityForEat){
            LinkedList<Coordinate> tempPath = new LinkedList<>();
            while (coordinateStart != coordEnd){
                if (flag == 0){
                    path.add(coordEnd);
                }
                tempPath.add(coordEnd);
                coordEnd = visited.get(coordEnd);
            }
            if (tempPath.size() < path.size()) path = tempPath;
            flag ++;
        }

        System.out.println(path);
        return path.isEmpty() ? coordinateStart : path.getLast();
    }

}
