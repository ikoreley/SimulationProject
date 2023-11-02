package ik.koresh.entites;

import ik.koresh.Area;
import ik.koresh.Coordinate;

public class EntityService {
    private final static EntityService instance = new EntityService();
    private EntityService(){}
    public static EntityService getInstance(){
        return instance;
    }


    // ставим сущность на поле
    public void setEntity(Coordinate coordinate, Entity entity, Area area){
        entity.coordinate = coordinate;
        area.getMapAllEntity().put(coordinate, entity);
        if (entity instanceof Creature){
            area.getMapCreatureEntity().put(coordinate, entity);
        }
    }


    public Entity getEntity(Coordinate coordinate, Area area){
        return area.getMapAllEntity().get(coordinate);
    }


    // убираем сущность с поля
    public void removeEntity(Coordinate coordinate, Area area){
        area.getMapAllEntity().remove(coordinate);
        if (isSquareMovingEntity(coordinate, area))
            area.getMapCreatureEntity().remove(coordinate);
    }

    // пустая ли ячейка на всем поле
    public boolean isSquareEmpty(Coordinate coord, Area area){
        return !area.getMapAllEntity().containsKey(coord);
    }

    // пустая ли ячейка в мапе ходящих
    public boolean isSquareMovingEntity(Coordinate coord, Area area){
        return !area.getMapCreatureEntity().containsKey(coord);
    }

    // Ход сущности
    public void moveCreature(Coordinate from, Coordinate to, Area area){
        Entity creature = getEntity(from, area);
        setEntity(to, creature, area);
    }
}
