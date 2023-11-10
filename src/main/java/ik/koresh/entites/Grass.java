package ik.koresh.entites;

import ik.koresh.Color;
import ik.koresh.Coordinate;
import ik.koresh.util.PropertiesEntityUtil;

public class Grass extends Entity{
    private Integer hpPlus;
    public Grass(Coordinate coordinate, Color color) {
        super(coordinate, color);
        this.hpPlus = PropertiesEntityUtil.get("EntityGrassHpPlus");
    }

    public Integer getHpPlus() {
        return hpPlus;
    }
}
