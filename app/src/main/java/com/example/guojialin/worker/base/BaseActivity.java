package com.example.guojialin.worker.base;


import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

public abstract class  BaseActivity extends Activity {
    private String Tag = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Tag = getLocalClassName();
        // 获得当前方法名
        String method = new Exception().getStackTrace()[0].getMethodName();
        Log.i(Tag,method);
    }

    @Override
    protected void onStart() {
        super.onStart();
        String method = new Exception().getStackTrace()[0].getMethodName();
        Log.i(Tag,method);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String method = new Exception().getStackTrace()[0].getMethodName();
        Log.i(Tag,method);
    }

    @Override
    protected void onPause() {
        super.onPause();
        String method = new Exception().getStackTrace()[0].getMethodName();
        Log.i(Tag,method);
    }

    @Override
    protected void onStop() {
        super.onStop();
        String method = new Exception().getStackTrace()[0].getMethodName();
        Log.i(Tag,method);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        String method = new Exception().getStackTrace()[0].getMethodName();
        Log.i(Tag,method);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String method = new Exception().getStackTrace()[0].getMethodName();
        Log.i(Tag,method);
    }


    /**
     * 为子类提权限请求方法
     *
     * @param code
     * @param permissions
     */
    public void requestPermission(int code, String... permissions) {
        ActivityCompat.requestPermissions(this, permissions, code);
    }

    /**
     * 为子类提供权限检查方法
     *
     * @param permissions
     * @return
     */
    public boolean hasPermission(Context context, String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
}
