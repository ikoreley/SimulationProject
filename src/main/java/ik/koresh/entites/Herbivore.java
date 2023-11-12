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

//    @Override
//    public void makeMove() {
//        MoveService.move();
//    }

    public Coordinate getCoordinate(){
        return super.coordinate;
    }

    public void setHP(Grass grass){
        hp = hp + grass.getHpPlus();
    }
    public Integer getHp(){
        return hp;
    }
}
