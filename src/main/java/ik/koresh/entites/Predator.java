package ik.koresh.entites;

import ik.koresh.Color;
import ik.koresh.Coordinate;
import ik.koresh.util.PropertiesEntityUtil;

public class Predator extends Creature {

    public Predator(Coordinate coordinate, Color color) {
        super(coordinate, color);
        this.hp = PropertiesEntityUtil.get("HpPredator");
        this.speed = PropertiesEntityUtil.get("SpeedPredator");
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
        } else if (t instanceof Herbivore herbivore) {
            hp += herbivore.getHP();
        }
    }

    public Integer getHP(){
        return hp;
    }
}
