package ik.koresh;


import ik.koresh.service.StartSimulation;

public class Main {
    public static void main(String[] args) {

        StartSimulation startSimulation = StartSimulation.getInstance();
        startSimulation.gameLoop();

    }

}