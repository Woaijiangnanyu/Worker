package com.example.guojialin.worker.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.guojialin.worker.IAdditionService;

public class AdditionService extends Service {
    public AdditionService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
       return new IAdditionService.Stub() {
           @Override
           public int add(int value1, int value2) throws RemoteException {
               return value1 + value2;
           }
       };
    }
}
