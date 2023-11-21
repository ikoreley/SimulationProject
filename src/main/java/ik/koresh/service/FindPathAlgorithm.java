package ik.koresh.service;

import ik.koresh.Coordinate;
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


    public static Coordinate coordinateMove(Creature creature) {

        final StartSettingsAlgorithm startSettingsAlgorithm = new StartSettingsAlgorithm(creature);

        LinkedList<Coordinate> path = getPath(startSettingsAlgorithm);

        System.out.println(path);
        return path.isEmpty() ? startSettingsAlgorithm.coordinateStart : path.getLast();
    }


    private static LinkedList<Coordinate> getPath(StartSettingsAlgorithm startSettingsAlgorithm) {
        LinkedList<Coordinate> targetPath = new LinkedList<>();

        boolean isFirstAddition = true;

        for (Coordinate coordEnd : startSettingsAlgorithm.entityForEat) {
            LinkedList<Coordinate> tempPath = new LinkedList<>();

            while (startSettingsAlgorithm.coordinateStart != coordEnd) {
                if(isFirstAddition){
                    targetPath.add(coordEnd);
                }
                tempPath.add(coordEnd);
                coordEnd = startSettingsAlgorithm.visited.get(coordEnd);
            }

            isFirstAddition = false;

            if (tempPath.size() < targetPath.size()) {
                targetPath = tempPath;
            }
        }
        return targetPath;
    }

    private static void addEntityForEat(Creature creature, LinkedList<Coordinate> entityForEat, Coordinate nextNode) {
        if (creature.getClass() == Predator.class && entityService.getInAllEntity(nextNode).getClass() == Herbivore.class) {
            entityForEat.add(nextNode);
        } else if (creature.getClass() == Herbivore.class && entityService.getInAllEntity(nextNode).getClass() == Grass.class) {
            entityForEat.add(nextNode);
        }
    }

    private static class StartSettingsAlgorithm {
        private final LinkedList<Coordinate> entityForEat;
        private final Queue<Coordinate> queue;
        private final Map<Coordinate, Coordinate> visited;
        private final Map<Coordinate, Set<Coordinate>> neighbour;
        private final Coordinate coordinateStart;

        public StartSettingsAlgorithm(Creature creature) {
            this.entityForEat = new LinkedList<>();
            this.queue = new LinkedList<>();
            this.visited = new LinkedHashMap<>();
            this.coordinateStart = creature.coordinate;
            this.neighbour = allNeighbourCell(creature, coordinateStart);
            fillingCollectionAlgorithm();
            fillingVisitCells(creature);
        }

        private void fillingCollectionAlgorithm(){
            visited.put(coordinateStart, null);
            queue.add(coordinateStart);
        }

        private void visitCells(Creature creature) {
            Coordinate curNode = queue.poll();

            Set<Coordinate> nextNodes = neighbour.get(curNode);
            for (Coordinate nextNode : nextNodes) {
                if (visited.containsKey(nextNode)) {
                    continue;
                }
                queue.add(nextNode);
                visited.put(nextNode, curNode);

                if (!entityService.isSquareEmptyArea(nextNode)) {
                    addEntityForEat(creature, entityForEat, nextNode);
                }

            }
        }

        private void fillingVisitCells(Creature creature){
            while (!queue.isEmpty()) {
                visitCells(creature);
            }
        }
    }

}
