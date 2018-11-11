package com.example.guojialin.worker.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.view.View;

import com.example.guojialin.worker.R;
import com.example.guojialin.worker.base.BaseActivity;

public class EActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e);
    }
    public void onIgnore(View view){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //电量优化
                batteryOptimisation();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private void batteryOptimisation(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            Intent intent = new Intent();
            String packageName = getPackageName();
            PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
            if (powerManager != null && powerManager.isIgnoringBatteryOptimizations(packageName)){
                intent.setAction(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
            }else {
                intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                intent.setData(Uri.parse("package:"+packageName));
                startActivity(intent);
            }
        }
    }
}
