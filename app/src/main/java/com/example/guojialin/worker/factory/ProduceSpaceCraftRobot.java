package com.example.guojialin.worker.factory;

import com.example.guojialin.worker.bean.SpaceraftRobot;

public class ProduceSpaceCraftRobot {
    private ProduceSpaceCraftRobot() {
    }
    public static SpaceCraftRobotFactory robotFactory = new SpaceCraftRobotFactory() {
        @Override
        public SpaceraftRobot getSpaceCraftRobot() {
            return new SpaceraftRobot();
        }
    };
}
