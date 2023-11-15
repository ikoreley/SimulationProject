package ik.koresh.entites;


import ik.koresh.Color;
import ik.koresh.Coordinate;
import ik.koresh.util.PropertiesEntityUtil;

public class Herbivore extends Creature {

    public Herbivore(Coordinate coordinate, Color color) {
        super(coordinate, color);
        this.hp = PropertiesEntityUtil.get("HpHerbivore");
        this.speed = PropertiesEntityUtil.get("SpeedHerbivore");

    }

    @Override
    public void makeMove() {
        MoveService.move(this);
    }

    public Coordinate getCoordinate() {
        return super.coordinate;
    }

    @Override
    public <T>void setHP(T t) {
        if (t instanceof Integer hpP){
            hp += hpP;
        } else if (t instanceof Grass grass) {
            hp += grass.getHP();
        }
    }


    @Override
    public Integer getHP() {
        return hp;
    }
}
