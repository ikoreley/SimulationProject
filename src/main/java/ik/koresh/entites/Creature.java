package ik.koresh.entites;

import ik.koresh.Color;
import ik.koresh.Coordinate;
import ik.koresh.FindPathAlgorithm;

abstract public class Creature extends Entity {

    public Integer speed;
    public Integer hp;

    public Creature(Coordinate coordinate, Color color) {
        super(coordinate, color);

    }

    public abstract void makeMove();

    public abstract Integer getHP();

    public abstract <T> void  setHP(T hp);
}
