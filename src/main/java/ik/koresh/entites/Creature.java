package ik.koresh.entites;

import ik.koresh.Color;
import ik.koresh.Coordinate;
import ik.koresh.FindPathAlgorithm;

abstract public class Creature extends Entity{

    public Integer speed;
    public Integer hp;
    public Creature(Coordinate coordinate, Color color) {
        super(coordinate, color);

    }


    public void makeMove() {
//        MoveService.move();
//        Coordinate targetCoordinates = FindPathAlgorithm.pathMove(this, area);
//
//        System.out.println(targetCoordinates);
//
//        final EntityService entityService = EntityService.getInstance();
//        if (!entityService.isSquareEmptyArea(targetCoordinates, area)) {
//            Entity entity = entityService.getInAllEntity(targetCoordinates, area);
//            if (this.getClass() == Herbivore.class){
////                        ((Herbivore) creature).setHP((Grass) entity);
//            }
//            else {
////                        ((Predator)creature).setHP((Herbivore) entity);
//            }
//            entityService.removeEntity(targetCoordinates, area);
//            System.out.println("EAT " + entity.getClass().getSimpleName());
//        }
//        MoveService.moveCreature(creature, targetCoordinates, area);
////        method1();
////        method2();
////        method3();
////        method4();
////        method5();
    }
}
