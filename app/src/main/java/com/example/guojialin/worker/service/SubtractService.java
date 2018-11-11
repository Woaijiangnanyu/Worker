package com.example.guojialin.worker.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class SubtractService extends Service {
    private Binder myBinder = new BinderPool.IMyBinderPoolImpl();
    public SubtractService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
       return myBinder;
    }
}
