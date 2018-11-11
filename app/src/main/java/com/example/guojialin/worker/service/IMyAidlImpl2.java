package com.example.guojialin.worker.service;

import android.os.RemoteException;

import com.example.guojialin.worker.IMyAidlInterface2;

public class IMyAidlImpl2 extends IMyAidlInterface2.Stub {
    @Override
    public String print_A() throws RemoteException {
        return "A";
    }

    @Override
    public String print_B() throws RemoteException {
        return "B";
    }
}
