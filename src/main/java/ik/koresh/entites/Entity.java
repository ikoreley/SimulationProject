package ik.koresh.entites;

import ik.koresh.Color;
import ik.koresh.Coordinate;


abstract public class Entity {

    public Coordinate coordinate;
    public Color color;

    public Entity(Coordinate coordinate, Color color) {
        this.coordinate = coordinate;
        this.color = color;
    }
}
