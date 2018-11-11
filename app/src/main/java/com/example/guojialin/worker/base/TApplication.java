package com.example.guojialin.worker.base;

import android.app.Application;

public class TApplication extends Application {
    private static TApplication instance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
    //作用:全局调用
    public static TApplication getInstance() {
        if (instance == null) {
            instance = new TApplication();
        }
        return instance;
    }
}
