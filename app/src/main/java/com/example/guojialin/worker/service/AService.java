package com.example.guojialin.worker.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import com.example.guojialin.worker.R;

public class AService extends Service {

    private final String Tag = AService.class.getSimpleName();

    @Override
    public void onCreate() {
        Log.i(Tag,new Exception().getStackTrace()[0].getMethodName());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForeground(777, getNotification());
        }
        super.onCreate();
    }

    private Notification getNotification() {
        @SuppressLint("WrongConstant") Notification noti = new Notification.Builder(getApplicationContext())
                .setContentTitle("My Work is running")
                .setSmallIcon(R.mipmap.ic_launcher)
//                .setPriority(NotificationCompat.PRIORITY_MAX)
                .build();
        return noti;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(Tag,new Exception().getStackTrace()[0].getMethodName());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        IBinder iBinder = new ABinder();
        Log.i(Tag,new Exception().getStackTrace()[0].getMethodName());
        return iBinder;
    }

    @Override
    public void onDestroy() {
        Log.i(Tag,new Exception().getStackTrace()[0].getMethodName());
        super.onDestroy();
    }

    public class ABinder extends Binder {
        public AService getService(){
            return AService.this;
        }
    }
}
