package ik.koresh;

import ik.koresh.entites.*;
import ik.koresh.view.AreaConsoleRender;

import java.util.*;

public class StartSimulation {


    private final AreaConsoleRender areaConsoleRender = AreaConsoleRender.getInstance();
    private final EntityService entityService = EntityService.getInstance();
    private Area area;

    public StartSimulation() {
        area = new Area();
        EntityService.getInstance().setArea(area);
        AreaService.fillingAreaEntities();
    }


    public void gameLoop() {
        System.out.println(area.getMapAllEntity());
        System.out.println(area.getMapCreatureEntity());

        int count = 0;

        while (count<10) {
//        while (true) {
            // вывод в консоль заполненного поля
            areaConsoleRender.render(area);

            //очередь для хода сущностей
            Queue<Creature> queue = MoveService.queueOfCreatureForMove();

            if(queue.size() == 1 || queue.size() == 0) return;
            while (!queue.isEmpty()) {

                Creature creature = queue.remove();

//                creature.makeMove(); // todo: реализовать этот метод

//                Coordinate coordinateCreatureForRemove = creature.coordinate;


                // todo: убрать отсюда логику хода в creature.makeMove()
                Coordinate targetCoordinates = FindPathAlgorithm.pathMove(creature, area);

                System.out.println(targetCoordinates + " " + creature.coordinate + " " + creature.getClass().getSimpleName());
//                System.out.println(coordinateCreatureForRemove);

                if (!entityService.isSquareEmptyArea(targetCoordinates)) {

                    Entity entity = entityService.getInAllEntity(targetCoordinates);
                    if (creature.getClass() == Herbivore.class){
//                        ((Herbivore) creature).setHP((Grass) entity);
                    }
                    else {
//                        ((Predator)creature).setHP((Herbivore) entity);
                        queue.remove((Creature)entity);

                    }
//                    entityService.removeEntity(targetCoordinates, area);
                    System.out.println("EAT " + entity.getClass().getSimpleName());
                }
                MoveService.moveCreature(creature, targetCoordinates);
                areaConsoleRender.render(area);
            }
            System.out.println("________________" + count);
//            if (area.getMapCreatureEntity().size() == 1) return; // не нужно, это решает  if(queue.size() == 1 || queue.size() == 0) return;

            count++;

        }
    }
}
