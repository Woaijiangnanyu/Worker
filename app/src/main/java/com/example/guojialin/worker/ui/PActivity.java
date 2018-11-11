package com.example.guojialin.worker.ui;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.example.guojialin.worker.R;
import com.example.guojialin.worker.bean.AccessPoint;
import com.example.guojialin.worker.utils.WiFiUtils;

import java.util.List;

public class PActivity extends BActivity {
    private WifiManager wifiManager;
    private WifiBroadCast wifiBroadCast;
    private final String TAG = PActivity.class.getSimpleName();
    List<WifiConfiguration> wifiConfigurations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p);
        if (!hasPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)){
            requestPermission(0x10,Manifest.permission.ACCESS_FINE_LOCATION);
        }else {
            initWifiManager();
        }
        if (wifiBroadCast == null){
            wifiBroadCast = new WifiBroadCast();
            IntentFilter filter = new IntentFilter();
            filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);//网络变化通知
            filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);//Wi-Fi开关变化通知
            filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);//Wi-Fi扫描结果通知
            filter.addAction(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION);//Wi-Fi连接结果通知
            registerReceiver(wifiBroadCast,filter);
        }
    }

    private void initWifiManager() {
        wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  @NonNull String[] permissions,  @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 0x10:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    initWifiManager();
                }
                break;
        }
    }

    public void doScan(View view) {
        wifiManager.startScan();
    }

    public void getWiFiList(View view){
       Runnable runnable = new Runnable() {
           @Override
           public void run() {
               wifiConfigurations = wifiManager.getConfiguredNetworks();
               for (WifiConfiguration wifiConfiguration : wifiConfigurations){
                   System.out.println("手机连接过： " + wifiConfiguration.SSID + " , netWorkId: " + wifiConfiguration.networkId + " , stats: " + wifiConfiguration.status);
               }
           }
       };
       Thread thread = new Thread(runnable);
       thread.start();
    }
    public void connect(View view){
        AccessPoint accessPoint = new AccessPoint("AY_Soft.cn","20181012","wpa");
        WifiConfiguration config = WiFiUtils.createConfiguration(accessPoint);
        //如果你设置的wifi是设备已经存储过的，那么这个networkId会返回小于0的值。
        int networkId = networkId = wifiManager.addNetwork(config);
        //连接
        wifiManager.enableNetwork(networkId,true);
    }

    public void current(View view){
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if (wifiInfo == null) {Log.d(TAG,"phone has not connect hotspot");}else {
            Log.d(TAG,"SSID :" + wifiInfo.getSSID() + " netWorkID: " + wifiInfo.getNetworkId());
        }
    }

    class WifiBroadCast extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case WifiManager.WIFI_STATE_CHANGED_ACTION:
                    int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,
                            WifiManager.WIFI_STATE_DISABLED);
                    switch (wifiState) {
                        case WifiManager.WIFI_STATE_DISABLED:
                            break;
                        case WifiManager.WIFI_STATE_ENABLED:
                            break;
                    }
                    break;
                case WifiManager.SCAN_RESULTS_AVAILABLE_ACTION:
                    List<ScanResult> Results = wifiManager.getScanResults();
                    for (ScanResult Result : Results) {
                        Log.d(TAG, "wifi结果: " + Result.SSID + "  RSSI:" + Result.level + "  Time：" + System.currentTimeMillis());
                    }
                    break;
            }
        }
    }
}
