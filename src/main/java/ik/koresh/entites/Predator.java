package ik.koresh.entites;

import ik.koresh.Color;
import ik.koresh.Coordinate;
import ik.koresh.util.PropertiesEntityUtil;

public class Predator extends Creature{

    public Predator(Coordinate coordinate, Color color) {
        super(coordinate, color);
        this.hp = PropertiesEntityUtil.get("HpPredator");
        this.speed = PropertiesEntityUtil.get("SpeedPredator");
    }

    @Override
    public void makeMove() {
    }

    public Coordinate getCoordinate(){
        return super.coordinate;
    }
}
