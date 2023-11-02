package ik.koresh.entites;


import ik.koresh.Color;
import ik.koresh.Coordinate;
import ik.koresh.SettingFileInput;

public class Herbivore extends Creature{

    public Herbivore(Coordinate coordinate, Color color) {
        super(coordinate, color);
        this.hp = SettingFileInput.getSettingsSimulation().get("CreatureHpHerbivore");
        this.speed = SettingFileInput.getSettingsSimulation().get("CreatureSpeedHerbivore");
    }

    @Override
    public void makeMove() {

    }

    public Coordinate getCoordinate(){
        return super.coordinate;
    }
}
