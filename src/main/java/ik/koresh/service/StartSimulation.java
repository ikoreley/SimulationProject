package ik.koresh.service;

import ik.koresh.Area;
import ik.koresh.AreaService;
import ik.koresh.entites.Creature;
import ik.koresh.entites.EntityService;
import ik.koresh.entites.MoveService;
import ik.koresh.view.AreaConsoleRender;
import ik.koresh.view.ViewMenu;

import java.util.Queue;
import java.util.Scanner;

public class StartSimulation {
    private static final StartSimulation instance = new StartSimulation();
    private final AreaConsoleRender areaConsoleRender = AreaConsoleRender.getInstance();
    private final Area area;

    private StartSimulation() {
        area = new Area();
        EntityService.getInstance().setArea(area);
        AreaService.fillingAreaEntities();
    }

    public static StartSimulation getInstance(){return instance;}
    private final Menu  menu = Menu.getInstance();
    private final ViewMenu viewMenu = ViewMenu.getInstance();


    public void gameLoop() {

        viewMenu.printMenu();

        Integer flag = menu.menuStart();
        if (flag == 0) gameLoop();


        System.out.println(area.getMapAllEntity());
        System.out.println(area.getMapCreatureEntity());

        int count = 0;

        while (true) {

            if (flag == 2){
                Scanner scanner = new Scanner(System.in);
                scanner.next();
            }
            //очередь для хода сущностей
            Queue<Creature> queue = MoveService.queueOfCreatureForMove();

            System.out.println(queue);
            if (queue.size() == 1) break; // игра окончена

            // вывод в консоль заполненного поля
            areaConsoleRender.render();

            while (!queue.isEmpty()) {
                Creature creature = queue.remove();
                if (!area.getMapAllEntity().containsKey(creature.coordinate)) {
                    continue;
                }
                System.out.println(creature.getHP() + " - " + creature.getClass().getSimpleName());
                creature.makeMove();
                areaConsoleRender.render();
            }
            //***
            System.out.println("________________" + count);
            count++;
        }
    }
}
