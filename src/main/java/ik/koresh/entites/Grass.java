package ik.koresh.entites;

import ik.koresh.Color;
import ik.koresh.Coordinate;
import ik.koresh.util.PropertiesAllUtilTest;
import ik.koresh.util.PropertiesEntityUtil;

public class Grass extends Entity{
    private final Integer hp;
    public Grass(Coordinate coordinate, Color color) {
        super(coordinate, color);
        this.hp = PropertiesEntityUtil.get("EntityGrassHpPlus");
//        this.hp = PropertiesAllUtilTest.getEntity("EntityGrassHpPlus");  // общий файл настроек работает!!!
    }

    public Integer getHP() {
        return hp;
    }
}
