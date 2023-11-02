package ik.koresh.entites;

import ik.koresh.Color;
import ik.koresh.Coordinate;
import ik.koresh.SettingFileInput;

public class Predator extends Creature{

    public Predator(Coordinate coordinate, Color color) {
        super(coordinate, color);
        this.hp = SettingFileInput.getSettingsSimulation().get("CreatureHpPredator");
        this.speed = SettingFileInput.getSettingsSimulation().get("CreatureSpeedPredator");
    }

    @Override
    public void makeMove() {
    }

    public Coordinate getCoordinate(){
        return super.coordinate;
    }
}
