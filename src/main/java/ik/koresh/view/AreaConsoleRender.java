package ik.koresh.view;

import ik.koresh.service.AreaService;
import ik.koresh.Color;
import ik.koresh.Coordinate;
import ik.koresh.service.EntityService;

public class AreaConsoleRender {
    private final static AreaConsoleRender instance = new AreaConsoleRender();
    private AreaConsoleRender(){}
    public static AreaConsoleRender getInstance(){return instance;}
    EntityService entityService = EntityService.getInstance();

    public void render(){

        for(int x = 0; x<AreaService.row; x++){
            StringBuilder sb = new StringBuilder();
            for (int y = 0; y<AreaService.col; y++){
                Coordinate coordinate = new Coordinate(x, y);
                if (entityService.isSquareEmptyArea(coordinate)) {
                    sb.append(Color.WHITE.stringColor).append("   ");
                }else {
                    sb.append(entityService.getInAllEntity(coordinate).color.stringColor).append("   ");
                }
            }
            sb.append(Color.ANSI_RESET.stringColor);
            System.out.println(sb);
        }
    }

}
