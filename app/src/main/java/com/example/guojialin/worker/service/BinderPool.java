package com.example.guojialin.worker.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.guojialin.worker.IMyBinderPoolInterface;

public class BinderPool {
    //获取AIDL代理对象标识
    private static final int BINDER_CODE_1 = 1;
    private static final int BINDER_CODE_2 = 2;

    public static class IMyBinderPoolImpl extends IMyBinderPoolInterface.Stub{

        @Override
        public IBinder queryBinder(int binderCode) throws RemoteException {
            Binder binder = null;
            switch (binderCode){
                case BINDER_CODE_1:
                    binder = new IMyAidlImpl();
                    break;
                case BINDER_CODE_2:
                    binder = new IMyAidlImpl2();
                    break;
                    default:
                        break;
            }
            return binder;
        }
    }
}
