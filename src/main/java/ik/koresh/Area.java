package ik.koresh;

import ik.koresh.entites.Creature;
import ik.koresh.entites.Entity;

import java.util.HashMap;
import java.util.Map;

public class Area {
    private Map<Coordinate, Entity> mapAllEntity;
    private Map<Coordinate, Entity> mapCreatureEntity;


    public Area() {
        this.mapAllEntity = new HashMap<>();
        this.mapCreatureEntity = new HashMap<>();
    }


    public Map<Coordinate, Entity> getMapAllEntity() {
        return mapAllEntity;
    }

    public Map<Coordinate, Entity> getMapCreatureEntity() {
        return mapCreatureEntity;
    }


}
