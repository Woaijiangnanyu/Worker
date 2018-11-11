package com.example.guojialin.worker.ja;

import java.util.Observable;
import java.util.Observer;

public class BObserver implements Observer {
    
    public BObserver(ServerManager sm) {
        super();
        sm.addObserver(this);//注册加入观察者
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("BObserver receive:Data has changed to "+((ServerManager) o).getData());
    }

}