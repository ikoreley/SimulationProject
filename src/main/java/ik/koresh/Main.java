package ik.koresh;


import ik.koresh.view.AreaConsoleRender;

public class Main {
    public static void main(String[] args) {

        Area area = new Area();
        AreaService.fillingAreaEntities(area);
        System.out.println(area.getMapAllEntity());
        System.out.println(area.getMapCreatureEntity());
        AreaConsoleRender areaConsoleRender = AreaConsoleRender.getInstance();
        areaConsoleRender.render(area);

    }

}