package com.example.guojialin.worker.service;

import android.os.RemoteException;

import com.example.guojialin.worker.IMyAidlInterface;

public class IMyAidlImpl extends IMyAidlInterface.Stub {
    @Override
    public int subtract(int num1, int num2) throws RemoteException {
        return num1 - num2;
    }
}
