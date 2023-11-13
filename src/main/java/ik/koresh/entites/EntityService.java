package ik.koresh.entites;

import ik.koresh.Area;
import ik.koresh.AreaService;
import ik.koresh.Coordinate;

public class EntityService {
    private final static EntityService instance = new EntityService();


    private EntityService(){}
    public static EntityService getInstance(){
        return instance;
    }

    private Area area;

    public void setArea(Area area) {
        this.area = area;
    }

    public Area getArea(){
        return area;
    }

    // ставим сущность на поле
    public void setEntity(Coordinate coordinate, Entity entity, Area area){
        entity.coordinate = coordinate;
        area.getMapAllEntity().put(coordinate, entity);
        if (entity instanceof Creature){
            area.getMapCreatureEntity().put(coordinate, entity);
        }
    }

    // ставим на созданное поле
    public void setEntityOnAreaFill(Coordinate coordinate, Entity entity, Area area){
        Coordinate temp = entity.coordinate;

        area.getMapAllEntity().remove(temp);
        entity.coordinate = coordinate;
        area.getMapAllEntity().put(entity.coordinate, entity);
        if (entity instanceof Creature){
            area.getMapCreatureEntity().remove(temp);
            area.getMapCreatureEntity().put(entity.coordinate, entity);
        }

    }


    public Entity getInAllEntity(Coordinate coordinate, Area area){
        return area.getMapAllEntity().get(coordinate);
    }


    public Entity getInCreatureEntity(Coordinate coordinate, Area area){
        return area.getMapCreatureEntity().get(coordinate);
    }


    // убираем сущность с поля
    public void removeEntity(Coordinate coordinate, Area area){
        area.getMapAllEntity().remove(coordinate);
        if (isSquareCreatureEntity(coordinate, area))
            area.getMapCreatureEntity().remove(coordinate);
    }

    // пустая ли ячейка на всем поле
    public boolean isSquareEmptyArea(Coordinate coord, Area area){
        return !area.getMapAllEntity().containsKey(coord) && AreaService.mapCoord.containsValue(coord);
    }


    // есть ли ячейка в мапе ходящих
    public boolean isSquareCreatureEntity(Coordinate coord, Area area){
        return !area.getMapCreatureEntity().containsKey(coord);
    }


}
