package ik.koresh.entites;


import ik.koresh.Color;
import ik.koresh.Coordinate;
import ik.koresh.util.PropertiesEntityUtil;

public class Herbivore extends Creature{

    public Herbivore(Coordinate coordinate, Color color) {
        super(coordinate, color);
        this.hp = PropertiesEntityUtil.get("HpHerbivore");
        this.speed = PropertiesEntityUtil.get("SpeedHerbivore");

    }

    @Override
    public void makeMove() {

    }

    public Coordinate getCoordinate(){
        return super.coordinate;
    }
}
