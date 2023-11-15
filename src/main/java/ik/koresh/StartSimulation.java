package ik.koresh;

import ik.koresh.entites.*;
import ik.koresh.view.AreaConsoleRender;

import java.util.*;
import java.util.stream.Collectors;

public class StartSimulation {


    private final AreaConsoleRender areaConsoleRender = AreaConsoleRender.getInstance();
    private final EntityService entityService = EntityService.getInstance();
    private final Area area;

    public StartSimulation() {
        area = new Area();
        EntityService.getInstance().setArea(area);
        AreaService.fillingAreaEntities();
    }


    public void gameLoop() {

        System.out.println(area.getMapAllEntity());
        System.out.println(area.getMapCreatureEntity());


        int count = 0;

        while (count < 10) {
//        while (true) {
            // вывод в консоль заполненного поля
//            areaConsoleRender.render(area);

            //очередь для хода сущностей
            Queue<Creature> queue = MoveService.queueOfCreatureForMove();


            if (queue.size() == 0) break; // игра окончена


            // вывод в консоль заполненного поля
            areaConsoleRender.render(area);

            System.out.println(queue);

            while (!queue.isEmpty()) {

                Creature creature = queue.remove();
                System.out.println(creature.getHP());

                if (!area.getMapAllEntity().containsKey(creature.coordinate)) {
                    continue;
                }

                creature.makeMove();
                areaConsoleRender.render(area);
            }

            //***
            System.out.println("________________" + count);
            count++;

        }
    }
}
