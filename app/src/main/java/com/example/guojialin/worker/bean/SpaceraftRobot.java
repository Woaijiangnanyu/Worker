package com.example.guojialin.worker.bean;

public class SpaceraftRobot extends Guider{

    public void doGuidWork(String name){
        work(name);
    }

    public void doRepairerRobot(String name){
        new repairerRobot().work(name);
    }

    public class repairerRobot extends Repairer{
        public void doRepairerWork(String name){
            work(name);
        }
    }
}
