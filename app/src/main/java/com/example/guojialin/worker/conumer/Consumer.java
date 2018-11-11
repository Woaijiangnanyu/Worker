package com.example.guojialin.worker.conumer;

import com.example.guojialin.worker.bean.SpaceraftRobot;
import com.example.guojialin.worker.factory.ProduceSpaceCraftRobot;

public class Consumer {
    public void crateRobot(){
        SpaceraftRobot spaceraftRobot = ProduceSpaceCraftRobot.robotFactory.getSpaceCraftRobot();
        spaceraftRobot.doGuidWork("xiaoming");
        spaceraftRobot.doRepairerRobot("lilei");
    }
}
