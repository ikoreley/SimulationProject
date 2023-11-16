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


    // ставим на созданное поле
    public void setEntity(Coordinate coordinateTo, Entity entity){
        Coordinate temp = entity.coordinate;

        removeEntity(temp);

        entity.coordinate = coordinateTo;
        area.getMapAllEntity().put(entity.coordinate, entity);
//        if (entity.getClass() == Herbivore.class || entity.getClass() == Predator.class){
        if (entity instanceof Creature){
            area.getMapCreatureEntity().put(entity.coordinate, entity);
        }
    }


    public Entity getInAllEntity(Coordinate coordinate){
        return area.getMapAllEntity().get(coordinate);
    }


    // убираем сущность с поля
    public void removeEntity(Coordinate coordinate){
        area.getMapAllEntity().remove(coordinate);
        area.getMapCreatureEntity().remove(coordinate);
    }

    // пустая ли ячейка на всем поле
    public boolean isSquareEmptyArea(Coordinate coord){
        return !area.getMapAllEntity().containsKey(coord) && AreaService.mapCoord.containsValue(coord);
    }


    // есть ли ячейка в мапе ходящих
    public boolean isSquareCreatureEntity(Coordinate coord){
        return !area.getMapCreatureEntity().containsKey(coord);
    }


}
